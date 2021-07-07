package main;

import java.awt.Dimension;
import java.awt.Graphics2D;
/**
 * 
 * Class which holds methods and most instance variables of more complex classes
 * 
 * @author ludlowbj & feastebj
 *
 */
public class Enemy extends Mover {

	protected int scoreOnKill;
	protected Player target;
	protected int centerPoint;
	protected int maxDistance;
	protected boolean isFollower;
	protected int shootCounter;
	protected int shootTimer;
	
	public Enemy(int x, int y, Dimension worldSize, Player player) {
		this.x = x;
		this.y = y;
		this.scaledX = 20;
		this.scaledY = 20;
		this.worldSize = worldSize;
		this.isActive = true;
		this.target = player;
	}

	public void updateScore() {

	}

}
