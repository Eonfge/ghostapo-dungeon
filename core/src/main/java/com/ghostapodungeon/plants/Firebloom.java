/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2017 Evan Debenham
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

package com.ghostapodungeon.plants;

import com.ghostapodungeon.Dungeon;
import com.ghostapodungeon.actors.blobs.Blob;
import com.ghostapodungeon.actors.blobs.Fire;
import com.ghostapodungeon.effects.CellEmitter;
import com.ghostapodungeon.effects.particles.FlameParticle;
import com.ghostapodungeon.items.potions.PotionOfLiquidFlame;
import com.ghostapodungeon.scenes.GameScene;
import com.ghostapodungeon.sprites.ItemSpriteSheet;

public class Firebloom extends Plant {
	
	{
		image = 0;
	}
	
	@Override
	public void activate() {
		
		GameScene.add( Blob.seed( pos, 2, Fire.class ) );
		
		if (Dungeon.level.heroFOV[pos]) {
			CellEmitter.get( pos ).burst( FlameParticle.FACTORY, 5 );
		}
	}
	
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_FIREBLOOM;

			plantClass = Firebloom.class;
			alchemyClass = PotionOfLiquidFlame.class;
		}
	}
}
