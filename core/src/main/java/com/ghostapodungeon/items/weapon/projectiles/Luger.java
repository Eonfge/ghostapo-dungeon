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
import com.ghostapodungeon.effects.MagicMissile;
import com.ghostapodungeon.items.Item;
import com.ghostapodungeon.items.bags.Bag;
import com.ghostapodungeon.items.weapon.munition.Parabellum;
import com.ghostapodungeon.mechanics.Ballistica;
import com.ghostapodungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;


public class Luger extends ProjectileWeapon {

    {
        image = ItemSpriteSheet.LUGER;
        tier = 1;

        munitionType = Parabellum.class;

        DLY = 1.2f;
        ACC = 1.5f;
    }

    @Override
    public void onHit(ProjectileWeapon weapon, Char attacker, Char defender, int damage){
        Log.d("MISC", "onHit: execute!");

        return;
    }

    @Override
    public void onImpact( Ballistica bullet ){
        int cell = bullet.collisionPos;
        Char ch = Actor.findChar( cell );

        if(ch != null){ //TODO: CUSTOM PROJECTILE DMAGE
            ch.damage( Random.Int( min(), max() ), this);
        }

        for (Item item : curUser.belongings.backpack.items){
            if (item instanceof Parabellum) {
                item.quantity(  item.quantity() -1 );

                if(item.quantity() == 0){
                   item.detach(curUser.belongings.backpack);
                }
            }
        }

        curUser.spendAndNext( 1f );
    }

    @Override
    public Boolean hasMunition(Bag container){

        for (Item item : container.items){
            if (item instanceof Parabellum) {
                if(item.quantity() > 0){
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean isUpgradable() {
        return false;
    }

    @Override
    public boolean isIdentified() {
        return true;
    }

    protected void fx(Ballistica bolt, Callback callback ) {
        MagicMissile.boltFromChar( curUser.sprite.parent,
                MagicMissile.FORCE,
                curUser.sprite,
                bolt.collisionPos,
                callback);
        Sample.INSTANCE.play( Assets.SND_ZAP );
    }
}
