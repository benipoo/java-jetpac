package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * 
 * 
 * Abstract class which holds the generic data for the more complex items.
 * 
 * @author ludlowbj  & feastebj
 *
 */
public abstract class Item {

	protected int x;
	protected int y;
	protected Dimension worldSize;
	protected int scaledX;
	protected int scaledY;
	protected Rectangle hitBox;
	protected Color color;
	protected boolean isCarried = false;
	private boolean canMoveDown;
	protected int yDirection;
	private boolean canMoveUp;
	private boolean isFuel = false;

	public Item(int x, int y, Dimension worldSize, Player player) {

		this.x = x;
		this.y = y;
		this.worldSize = worldSize;
		this.scaledX = 20;
		this.scaledY = 20;
		this.color = Color.MAGENTA;
		this.yDirection = 0;
		this.canMoveDown = true;
		this.canMoveUp = true;

	}


	public void drawOn(Graphics2D g2) {

		Rectangle object = new Rectangle(this.x, this.y, (int) this.worldSize.getWidth() / this.scaledX,
				(int) this.worldSize.getHeight() / this.scaledY);
		this.hitBox = object;

		g2.setColor(this.color);
		g2.fill(object);
		g2.draw(object);

	}

	public void setYDirection(int y) {

		if (y > 0 && this.canMoveDown) {

			this.yDirection = y;
		}

		if (y < 0 && this.canMoveUp) {

			this.yDirection = y;
		}

		this.yDirection = y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean touches(Mover mover) {

		return (this.getHitBox().intersects(mover.getBounds()) && (!(this.isCarried())));

	}

	public Rectangle getHitBox() {
		return new Rectangle(this.x, this.y, (int) this.worldSize.getWidth() / this.scaledX,
				(int) this.worldSize.getHeight() / this.scaledY);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean isCarried() {
		return this.isCarried;
	}

	public void setCanMoveDown(boolean bool) {
		this.canMoveDown = bool;

	}

	public boolean getCanMoveDown() {
		return this.canMoveDown;
	}

	protected int getYDirection() {
		return this.yDirection;
	}

	public boolean isFuel() {
		return this.isFuel;
	}
	
	public void setisFuel(boolean bool) {
		this.isFuel = bool;
	}

}
