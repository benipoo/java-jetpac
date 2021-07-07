package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * 
 * Class which handles the SpaceShip class, which is mostly
 * just data, but also handles how many part the player has collected 
 * and how much fuel they have.
 * 
 * @author ludlowbj & feastebj
 *
 */
public class SpaceShip extends Mover {

	private Color emptyColor;
	private boolean isfull;
	private int fuelcount;
	private int partCount;

	public SpaceShip(int x, int y, Dimension worldSize) {

		this.x = x;
		this.y = y;
		this.worldSize = worldSize;
		this.scaledX = 15;
		this.scaledY = 4;
		this.isActive = true;
		this.emptyColor = Color.GRAY;
		this.color = Color.WHITE;
		this.isfull = false;
		this.fuelcount = 0;
		this.partCount = 0;
	}

	@Override
	public void drawOn(Graphics2D g2) {

		Rectangle object1 = new Rectangle(this.x, (this.y), ((int) this.worldSize.getWidth() / this.scaledX), 
				((int) this.worldSize.getHeight() / this.scaledY) / 3);

		
		if(this.partCount >= 1) {
			g2.setColor(color);
		} else {
			g2.setColor(emptyColor);
		}
		
		g2.fill(object1);
		g2.draw(object1);
		
		Rectangle object2 = new Rectangle(this.x, (this.y) - (((int) this.worldSize.getHeight() / this.scaledY) / 3), 
				((int) this.worldSize.getWidth() / this.scaledX), 
				((int) this.worldSize.getHeight() / this.scaledY) / 3);
		
		if(this.partCount >= 2) {
			g2.setColor(color);
		} else {
			g2.setColor(emptyColor);
		}
		
		g2.fill(object2);
		g2.draw(object2);
		
		Rectangle object3 = new Rectangle(this.x, (this.y) - (((int) this.worldSize.getHeight() / this.scaledY) / 3) * 2, ((int) this.worldSize.getWidth() / this.scaledX), 
				((int) this.worldSize.getHeight() / this.scaledY) / 3);

		if(this.partCount >= 3) {
			g2.setColor(color);
		} else {
			g2.setColor(emptyColor);
		}
		
		g2.fill(object3);
		g2.draw(object3);
		
		Rectangle hbox = new Rectangle(this.x, (this.y) - (int) this.worldSize.getHeight() / this.scaledY * 2 / 3, ((int) this.worldSize.getWidth() / this.scaledX), 
				((int) this.worldSize.getHeight() / this.scaledY));
		this.hitBox = hbox;
	}
	
	public boolean isFull() {

		return this.fuelcount >= 3;
	}
	
	public void addPart() {
		this.partCount += 1;
	}

	public void addFuel() {

		this.fuelcount += 1;
	}

	public void takeoff() {

		this.yDirection += 3;
	}
	
	public Color getEmptyColor() {
		return emptyColor;
	}

	public void setEmptyColor(Color emptyColor) {
		this.emptyColor = emptyColor;
	}

	public boolean isIsfull() {
		return isfull;
	}

	public void setIsfull(boolean isfull) {
		this.isfull = isfull;
	}

	public int getFuelcount() {
		return fuelcount;
	}

	public void setFuelcount(int fuelcount) {
		this.fuelcount = fuelcount;
	}

	public int getPartCount() {
		return partCount;
	}

	public void setPartCount(int partCount) {
		this.partCount = partCount;
	}

}	
