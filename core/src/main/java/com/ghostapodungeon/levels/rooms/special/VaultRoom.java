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

package com.ghostapodungeon.levels.rooms.special;

import com.ghostapodungeon.Dungeon;
import com.ghostapodungeon.items.Heap;
import com.ghostapodungeon.items.keys.IronKey;
import com.ghostapodungeon.items.Generator;
import com.ghostapodungeon.items.Item;
import com.ghostapodungeon.items.keys.CrystalKey;
import com.ghostapodungeon.levels.Level;
import com.ghostapodungeon.levels.Terrain;
import com.ghostapodungeon.levels.painters.Painter;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

public class VaultRoom extends SpecialRoom {

	public void paint( Level level ) {

		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY_SP );
		Painter.fill( level, this, 2, Terrain.EMPTY );
		
		int cx = (left + right) / 2;
		int cy = (top + bottom) / 2;
		int c = cx + cy * level.width();
		
		Random.shuffle(prizeClasses);
		
		Item i1, i2;
		do {
			i1 = prize( level );
			i2 = prize( level );
		} while (i1.getClass() == i2.getClass());
		level.drop( i1, c ).type = Heap.Type.CRYSTAL_CHEST;
		level.drop( i2, c + PathFinder.NEIGHBOURS8[Random.Int( 8 )]).type = Heap.Type.CRYSTAL_CHEST;
		level.addItemToSpawn( new CrystalKey( Dungeon.depth ) );
		
		entrance().set( Door.Type.LOCKED );
		level.addItemToSpawn( new IronKey( Dungeon.depth ) );
	}
	
	private Item prize( Level level ) {
		return Generator.random( prizeClasses.remove(0) );
	}
	
	private ArrayList<Generator.Category> prizeClasses = new ArrayList<>(
			Arrays.asList(Generator.Category.WAND,
					Generator.Category.RING,
					Generator.Category.ARTIFACT));
}
