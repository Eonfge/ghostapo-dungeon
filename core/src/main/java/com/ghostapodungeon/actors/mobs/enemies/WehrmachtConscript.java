/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2017 Evan Debenham
 *
 * Ghostapo  Dungeon
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

package com.ghostapodungeon.actors.mobs.enemies;

import com.ghostapodungeon.actors.Char;
import com.ghostapodungeon.actors.mobs.Mob;
import com.ghostapodungeon.items.HandGrenade;
import com.ghostapodungeon.items.Item;
import com.ghostapodungeon.items.weapon.missiles.Dart;
import com.ghostapodungeon.items.weapon.munition.Parabellum;
import com.ghostapodungeon.mechanics.Ballistica;
import com.ghostapodungeon.sprites.WehrmachtConscriptSprite;
import com.watabou.utils.Random;

public class WehrmachtConscript extends Mob {

    {
        spriteClass = WehrmachtConscriptSprite.class;

        HP = HT = 8;
        defenseSkill = 2;

        maxLvl = 5;

        lootChance = 1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( 1, 4 );
    }

    @Override
    public int attackSkill( Char target ) {
        return 8;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 1);
    }

    @Override
    protected boolean canAttack( Char enemy ) {
        Ballistica attack = new Ballistica( pos, enemy.pos, Ballistica.PROJECTILE);
        return attack.collisionPos == enemy.pos;
    }

    @Override
    protected Item createLoot() {
        if(Random.Int(1, 10) < 3){
            return new HandGrenade();
        }
        return new Parabellum().quantity( Random.Int(2, 6) );
    }
}
