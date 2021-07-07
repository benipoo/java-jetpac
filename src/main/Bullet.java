package main;

import java.awt.Color;
/**
 * Holds the information for the bullet class
 * 
 * @author ludlowbj & feastebj
 *
 */
public class Bullet extends Mover {

	protected boolean badBullet;
	
	public Bullet(Mover shooter) {

		this.scaledX = 10;
		this.scaledY = 200;
		this.color = Color.RED;
		if (shooter.isFacingRight()) {
			this.xDirection = 10;
			this.x = shooter.getX() + this.scaledX * 5;
		} else {
			this.xDirection = -10;
			this.x = shooter.getX() - shooter.getScaledX() * 5;
		}
		this.yDirection = 0;
		this.y = shooter.getY() + shooter.getScaledY();
		this.worldSize = shooter.getWorldSize();
		this.badBullet = true;
	}

}
