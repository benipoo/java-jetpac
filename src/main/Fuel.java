package main;

import java.awt.Color;
import java.awt.Dimension;
/**
 * 
 * Basic data class which holds the information about the fuel class.
 * 
 * @author ludlowbj & feastebj
 *
 */
public class Fuel extends Item {

	public Fuel(int x, int y, Dimension worldSize, Player player) {
		super(x, y, worldSize, player);

		this.scaledX = 30;
		this.scaledY = 30;
		this.setisFuel(true);
	}

}
