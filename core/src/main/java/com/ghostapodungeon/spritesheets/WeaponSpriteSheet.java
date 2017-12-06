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

package com.ghostapodungeon.spritesheets;

import com.ghostapodungeon.Assets;
import com.watabou.noosa.TextureFilm;

//TODO: No spritesheet hardcoding in the sprite renderer
public class WeaponSpriteSheet {

    private static final int WIDTH = 16;

    public static TextureFilm film = new TextureFilm( Assets.WEAPONS, 16, 16 );

    private static int xy(int x, int y){
        x -= 1; y -= 1;
        return x + WIDTH*y;
    }

    private static void assignItemRect( int item, int width, int height){
        int x = (item % WIDTH) * WIDTH;
        int y = (item / WIDTH) * WIDTH;
        film.add( item, x, y, x+width, y+height);
    }

    private static final int WEAPONS   =                               xy(1, 1);   //16 slots
    //null warning occupies space 0, should only show up if there's a bug.
    public static final int LUGER        = WEAPONS+0;
    static{
        assignItemRect(LUGER, 16, 16);
    }
}
