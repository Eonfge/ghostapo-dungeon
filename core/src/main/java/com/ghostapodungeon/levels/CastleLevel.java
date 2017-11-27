/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2017 Evan Debenham
 *
 * Ghostapo Dungeon
 * Copyright (C) 2017 Kevin Degeling
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.ghostapodungeon.levels;

import com.ghostapodungeon.Assets;
import com.ghostapodungeon.actors.mobs.npcs.Wandmaker;
import com.ghostapodungeon.levels.painters.Painter;
import com.ghostapodungeon.levels.painters.PrisonPainter;
import com.ghostapodungeon.levels.rooms.Room;
import com.ghostapodungeon.levels.traps.AlarmTrap;
import com.ghostapodungeon.levels.traps.BurningTrap;
import com.ghostapodungeon.levels.traps.ConfusionTrap;
import com.ghostapodungeon.levels.traps.FlockTrap;
import com.ghostapodungeon.levels.traps.GrippingTrap;
import com.ghostapodungeon.levels.traps.OozeTrap;
import com.ghostapodungeon.levels.traps.PoisonDartTrap;
import com.ghostapodungeon.levels.traps.ShockingTrap;
import com.ghostapodungeon.levels.traps.SummoningTrap;
import com.ghostapodungeon.levels.traps.TeleportationTrap;
import com.ghostapodungeon.levels.traps.ToxicTrap;
import com.ghostapodungeon.levels.traps.ChillingTrap;
import com.ghostapodungeon.messages.Messages;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class CastleLevel  extends RegularLevel{


    {
        color1 = 0x6a723d;
        color2 = 0x88924c;
    }

    @Override
    protected ArrayList<Room> initRooms() {
        return Wandmaker.Quest.spawnRoom(super.initRooms());
    }

    @Override
    protected int standardRooms() {
        //6 to 8, average 6.66
        return 6+ Random.chances(new float[]{4, 2, 2});
    }

    @Override
    protected int specialRooms() {
        //1 to 3, average 1.83
        return 1+Random.chances(new float[]{3, 4, 3});
    }

    @Override
    protected Painter painter() {
        return new PrisonPainter()
                .setWater(feeling == Feeling.WATER ? 0.90f : 0.30f, 4)
                .setGrass(feeling == Feeling.GRASS ? 0.80f : 0.20f, 3)
                .setTraps(nTraps(), trapClasses(), trapChances());
    }

    @Override
    public String tilesTex() {
        return Assets.TILES_SEWERS;
    }

    @Override
    public String waterTex() {
        return Assets.WATER_SEWERS;
    }

    @Override
    protected Class<?>[] trapClasses() {
        return new Class[]{ ChillingTrap.class, ShockingTrap.class, ToxicTrap.class, BurningTrap.class, PoisonDartTrap.class,
                AlarmTrap.class, OozeTrap.class, GrippingTrap.class,
                ConfusionTrap.class, FlockTrap.class, SummoningTrap.class, TeleportationTrap.class, };
    }

    @Override
    protected float[] trapChances() {
        return new float[]{ 8, 8, 8, 8, 8,
                4, 4, 4,
                2, 2, 2, 2 };
    }

    @Override
    public String tileName( int tile ) {
        switch (tile) {
            case Terrain.WATER:
                return Messages.get(PrisonLevel.class, "water_name");
            default:
                return super.tileName( tile );
        }
    }

    @Override
    public String tileDesc(int tile) {
        switch (tile) {
            case Terrain.EMPTY_DECO:
                return Messages.get(PrisonLevel.class, "empty_deco_desc");
            case Terrain.BOOKSHELF:
                return Messages.get(PrisonLevel.class, "bookshelf_desc");
            default:
                return super.tileDesc( tile );
        }
    }
}
