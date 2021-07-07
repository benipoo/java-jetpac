package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/**
 * 
 * Abstract class which holds all of the generic methods for all
 * objects which are drawn on screen.
 * 
 * @author ludlowbj & feastebj
 *
 */
public abstract class Mover {

	protected int x;
	protected int y;
	protected int xDirection;
	protected int yDirection;
	protected int scaledX;
	protected int scaledY;
	protected Dimension worldSize;
	protected boolean isActive;
	protected boolean canBeMoved;
	protected Color color;
	boolean isMovingRight;
	boolean isMovingUp;
	boolean isFacingRight;
	protected WorldComponent worldComponent;
	protected Rectangle hitBox;

	public void drawOn(Graphics2D g2) {

		Rectangle object = new Rectangle(this.x, this.y, (int) this.worldSize.getWidth() / this.scaledX,
				(int) this.worldSize.getHeight() / this.scaledY);
		this.hitBox = object;

		g2.setColor(this.color);
		g2.fill(object);
		g2.draw(object);
	}
	
	public void shoot() {
		this.worldComponent.bullets.add(new Bullet(this));
	}

	protected boolean isActivated() {
		return this.isActive;
	}

	protected void changeActive(boolean a) {
		this.isActive = a;
	}

	public boolean touches(Mover object) {
		return this.getBounds().intersects(object.getBounds());
	}

	public boolean died() {

		// reduce lives by 1, if 0 handle it
		return false;
	}

	public Rectangle getHitBox() {
		return this.hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}

	public Color getColor(Color color) {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	protected int getY() {
		return this.y;
	}

	protected int getX() {
		return this.x;
	}

	protected Dimension getWorldSize() {
		return this.worldSize;
	}

	protected int getXDirection() {
		return this.xDirection;
	}

	protected int getYDirection() {
		return this.yDirection;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	protected int getScaledX() {
		return this.scaledX;
	}

	protected int getScaledY() {
		return this.scaledY;
	}

	protected void setXDirection(int i) {
		this.xDirection = i;
	}

	protected void setYDirection(int i) {
		this.yDirection = i;
	}

	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, (int) this.worldSize.getWidth() / this.scaledX,
				(int) this.worldSize.getHeight() / this.scaledY);

	}

	public boolean isFacingRight() {
		if(this.getXDirection() > 0 ) {
			return true;
		}
		return false;
	}
}
