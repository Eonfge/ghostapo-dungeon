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

package com.ghostapodungeon.actors.buffs;

import com.ghostapodungeon.actors.blobs.ConfusionGas;
import com.ghostapodungeon.actors.blobs.Electricity;
import com.ghostapodungeon.actors.blobs.Fire;
import com.ghostapodungeon.actors.blobs.Freezing;
import com.ghostapodungeon.actors.blobs.ParalyticGas;
import com.ghostapodungeon.actors.blobs.Regrowth;
import com.ghostapodungeon.actors.blobs.StenchGas;
import com.ghostapodungeon.actors.blobs.ToxicGas;
import com.ghostapodungeon.actors.blobs.VenomGas;
import com.ghostapodungeon.actors.blobs.Web;
import com.ghostapodungeon.ui.BuffIndicator;
import com.ghostapodungeon.messages.Messages;
import com.watabou.noosa.Image;

public class BlobImmunity extends FlavourBuff {
	
	public static final float DURATION	= 20f;
	
	@Override
	public int icon() {
		return BuffIndicator.IMMUNITY;
	}
	
	@Override
	public void tintIcon(Image icon) {
		greyIcon(icon, 5f, cooldown());
	}
	
	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

	{
		immunities.add( ParalyticGas.class );
		immunities.add( ToxicGas.class );
		immunities.add( ConfusionGas.class );
		immunities.add( StenchGas.class );
		immunities.add( VenomGas.class );
		immunities.add( Fire.class );
		immunities.add( Freezing.class );
		immunities.add( Electricity.class );
		immunities.add( Regrowth.class );
		immunities.add( Web.class );
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns());
	}
}