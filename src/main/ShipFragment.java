package main;

import java.awt.Color;
import java.awt.Dimension;

/**
 * 
 * Data class which holds the information for the ShipFragment items
 * 
 * @author ludlowbj & feastebj
 *
 */
public class ShipFragment extends Item {

	public ShipFragment(int x, int y, Dimension worldSize, Player player) {
		super(x, y, worldSize, player);

		this.setisFuel(false);
		this.color = Color.WHITE;
	}

}
