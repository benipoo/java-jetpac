package main;

import java.awt.Color;
import java.awt.Dimension;
/**
 * 
 * Class for medusaHead enemy, which moves back and forth in
 * a sine-esque arc as it does so. Is also capable of shooting 
 * bullets which harm the player.
 * 
 * @author ludlowbj  & feastebj
 *
 */
public class MedusaHead extends Enemy {

	protected int shootTimer = 60;
	protected int shootCounter = 0;
	
	public MedusaHead(int x, int y, Dimension worldSize, Player player, WorldComponent worldCom) {
		super(x, y, worldSize, player);
		this.color = Color.YELLOW;
		this.scoreOnKill = 750;
		this.isFollower = false;
		this.xDirection = 4;
		this.yDirection = 4;
		this.isFollower = false;
		this.centerPoint = this.y;
		this.maxDistance = this.scaledY * 3;
		this.worldComponent = worldCom;
		this.isFacingRight = true;
	}

	public void shoot() {
		if(this.shootCounter >= this.shootTimer) {
			super.shoot();
			shootCounter = 0;
		} else {
			shootCounter++;
		}
		//System.out.println(this.shootCounter);
	}
}
