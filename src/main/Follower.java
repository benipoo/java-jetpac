package main;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Holds information which correlates to the follower enemy
 * which follows the player around the map dependent on their
 * relative position to one another.
 * 
 * @author ludlowbj & feastebj
 *
 */
public class Follower extends Enemy {

	public Follower(int x, int y, Dimension worldSize, Player player) {
		super(x, y, worldSize, player);
		this.setColor(Color.ORANGE);
		this.scoreOnKill = 500;
		this.isFollower = true;
		this.xDirection = (int) (1 + Math.random());
		this.yDirection = (int) (1 + Math.random());

	}
}
