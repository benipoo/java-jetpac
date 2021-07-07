package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * 
 * Class which handles all of the player character's 
 * functions including movement and shooting.
 * 
 * @param int x handles horizontal position
 * @param int y handles vertical position
 * @param Dimension worldsize handles size of the player which is relative to the screen
 * 
 * @author ludlowbj & feastebj
 *
 */
public class Player extends Mover {

	private boolean canMoveUp = true;
	private boolean canMoveDown = true;
	private boolean canMoveRight = true;
	private boolean canMoveLeft = true;
	private boolean isFacingRight = false;
	private int lifeCount;
	protected boolean isCarryingItem;
	protected Item itemCarried;
	private int maskScaledXInitial = 0;
	private int maskScaledXFinal = 60;
	private int maskScaledYInitial = 5;
	private int maskScaledYFinal = 30;
	private Color maskColor;
	private WorldComponent worldCom;

	public Player(int x, int y, Dimension worldSize, WorldComponent worldComp) {

		this.x = x;
		this.y = y;
		this.xDirection = 0;
		this.yDirection = 0;
		this.worldSize = worldSize;
		this.scaledX = 20;
		this.scaledY = 20;
		this.isActive = true;
		this.canBeMoved = true;
		this.lifeCount = 3;
		this.color = Color.BLUE;
		this.maskColor = Color.PINK;
		this.isCarryingItem = false;
		this.worldCom = worldComp;
	}

	public Player() {

	}

	@Override
	public void drawOn(Graphics2D g2) {

		Rectangle object1 = new Rectangle(this.x, this.y, (int) this.worldSize.getWidth() / this.scaledX,
				(int) this.worldSize.getHeight() / this.scaledY);
		this.hitBox = object1;

		Rectangle object2 = new Rectangle(this.x + maskScaledXInitial, this.y + maskScaledYInitial,
				(int) this.worldSize.getWidth() / this.maskScaledXFinal,
				(int) this.worldSize.getHeight() / maskScaledYFinal);

		if (this.isFacingRight()) {

			object2.x += 2 * object2.width;
		}

		g2.setColor(this.color);
		g2.fill(object1);
		g2.draw(object1);
		g2.setColor(this.maskColor);
		g2.fill(object2);
		g2.draw(object2);

	}

	@Override
	public void shoot() {

		Bullet bullet = new Bullet(this);
		bullet.badBullet = false;
		bullet.color = Color.cyan;
		this.worldCom.bullets.add(bullet);
		
	}

	public void dropItem(Item item) {

		this.itemCarried = null;
		item.isCarried = false;

		if (this.isFacingRight()) {

			item.setPosition(this.x + 50, this.y - 50);

		} else {

			item.setPosition(this.x - 50, this.y - 50);
		}
	}

	@Override
	public void setXDirection(int x) {

		if (x > 0 && this.canMoveRight) {
			this.xDirection = x;
		}

		if (x < 0 && this.canMoveLeft) {
			this.xDirection = x;
		}

		this.xDirection = x;
	}

	@Override
	public void setYDirection(int y) {

		if (y > 0 && this.canMoveDown) {

			this.yDirection = y;
		}

		if (y < 0 && this.canMoveUp) {

			this.yDirection = y;
		}

		this.yDirection = y;
	}

	public void subtractLife() {
		this.lifeCount--;
	}

	public void setCanMoveDown(boolean bool) {
		this.canMoveDown = bool;
	}

	public boolean getCanMoveRight() {
		return this.canMoveRight;
	}

	public void setCanMoveRight(boolean bool) {
		this.canMoveRight = bool;
	}

	public boolean getCanMoveLeft() {
		return this.canMoveLeft;
	}

	public boolean getCanMoveUp() {
		return this.canMoveUp;
	}

	public void setCanMoveUp(boolean bool) {
		this.canMoveUp = bool;
	}

	public boolean getCanMoveDown() {
		return this.canMoveDown;
	}

	public void setCanMoveLeft(boolean bool) {
		this.canMoveLeft = bool;
	}

	public boolean isFacingRight() {
		return isFacingRight;
	}

	public void setFacingRight(boolean isFacingRight) {
		this.isFacingRight = isFacingRight;
	}

	public int getLifeCount() {
		return lifeCount;
	}

	public void setLifeCount(int lifeCount) {
		this.lifeCount = lifeCount;
	}

	public void setItemCarried(Item item) {

		if (this.itemCarried != null) {

			this.dropItem(this.itemCarried);
			this.itemCarried = item;

		} else {

			this.itemCarried = item;
		}
	}

	public Item getItemCarried() {

		if (this.itemCarried != null) {
			return this.itemCarried;
		}
		return this.itemCarried;
	}
}
