package main;

import java.awt.Color;
import java.awt.Dimension;
/**
 * 
 * Data class which ensures that platforms are 
 * created correctly.
 * 
 * @author ludlowbj & feastebj
 *
 */
public class Platform extends Mover {

	public Platform(int x, int y, Dimension worldSize) {

		this.x = x;
		this.y = y;
		this.worldSize = worldSize;
		this.scaledX = 40;
		this.scaledY = 40;
		this.isActive = true;
		this.color = Color.GREEN;
	}

}
