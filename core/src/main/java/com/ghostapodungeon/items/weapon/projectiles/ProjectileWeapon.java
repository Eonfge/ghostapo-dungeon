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

package com.ghostapodungeon.items.weapon.projectiles;

import android.util.Log;

import com.ghostapodungeon.Assets;
import com.ghostapodungeon.actors.Actor;
import com.ghostapodungeon.actors.Char;
import com.ghostapodungeon.actors.hero.Hero;
import com.ghostapodungeon.effects.MagicMissile;
import com.ghostapodungeon.items.bags.Bag;
import com.ghostapodungeon.items.weapon.Weapon;
import com.ghostapodungeon.mechanics.Ballistica;
import com.ghostapodungeon.scenes.CellSelector;
import com.ghostapodungeon.scenes.GameScene;
import com.ghostapodungeon.ui.QuickSlotButton;
import com.ghostapodungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

import java.util.ArrayList;

public abstract class ProjectileWeapon extends Weapon {
    {
        defaultAction = AC_FIRE;
        usesTargeting = true;
    }

    public static final String AC_FIRE	= "FIRE";

    protected int collisionProperties = Ballistica.PROJECTILE;

    public int tier;
    public Class munitionType;

    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.add( AC_FIRE );
        return actions;
    }

    protected abstract void onImpact( Ballistica attack );

    protected abstract Boolean hasMunition(Bag container);

    protected void fx( Ballistica bolt, Callback callback ) {
        MagicMissile.boltFromChar( curUser.sprite.parent,
                MagicMissile.MAGIC_MISSILE,
                curUser.sprite,
                bolt.collisionPos,
                callback);
        Sample.INSTANCE.play( Assets.SND_ZAP );
    }

    public abstract void onHit( ProjectileWeapon weapon, Char attacker, Char defender, int damage);

    @Override
    public void execute( Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals( AC_FIRE )) {
            curUser = hero;
            curItem = this;
            GameScene.selectCell( firingTarget );
        }
    }

    @Override
    public int min(int lvl) {
        return  tier +  //base
                lvl;    //level scaling
    }

    @Override
    public int max(int lvl) {
        return  5*(tier+1) +    //base
                lvl*(tier+1);   //level scaling
    }

    public int STRReq(int lvl){
        return 10;
    }

    protected static CellSelector.Listener firingTarget = new  CellSelector.Listener() {

        @Override
        public void onSelect( Integer target ) {

            if (target != null) {

                final ProjectileWeapon currentWeapon;
                if (curItem instanceof ProjectileWeapon) {
                    currentWeapon = (ProjectileWeapon) ProjectileWeapon.curItem;
                } else {
                    return;
                }

                final Ballistica shot = new Ballistica( curUser.pos, target, currentWeapon.collisionProperties);
                int cell = shot.collisionPos;

                if (target == curUser.pos || cell == curUser.pos) {
                    GLog.i( "SHOOTING YOURSELF IS A WASTE OF GOVERNMENT RESOURCES!" );
                    return;
                }

                //attempts to target the cell aimed at if something is there, otherwise targets the collision pos.
                if (Actor.findChar(target) != null) {
                    QuickSlotButton.target(Actor.findChar(target));
                } else {
                    QuickSlotButton.target(Actor.findChar(cell));
                }

                if ( currentWeapon.hasMunition(curUser.belongings.backpack) ) {

                    curUser.busy();

                    currentWeapon.fx(shot, new Callback() {
                            public void call() {
                                currentWeapon.onImpact(shot);
                            }
                        });

                } else {

                    GLog.w( "CLICK, CLICK..." );

                }
            }
        }

        @Override
        public String prompt() {
            return "SELECT TO FIRE";
        }
    };
}
