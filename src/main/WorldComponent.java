package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import javafx.scene.paint.Color;
/**
 *
 * Primary class aside from main
 * The class handles all on screen functions, including:
 * moving the player and enemies
 * displaying game won and over screens
 * removing unneeded objects
 * and moving between levels.
 * @author ludlowbj  & feastebj
 *
 *
 *
 */
@SuppressWarnings("serial")
public class WorldComponent extends JComponent {

	// Adding all necessary variables
	private Dimension worldSize;
	private int level;
	protected Player player;
	protected SpaceShip spaceship;
	private LoadLevel loader;
	private ArrayList<Platform> platform;
	private ArrayList<Enemy> enemies;
	ArrayList<Item> itemsToRemove;
	ArrayList<Enemy> enemiesToRemove;
	ArrayList<Bullet> bullets;
	ArrayList<Bullet> bulletsToRemove;
	ArrayList<Item> items;
	private JFrame frame;
	private boolean playerIsDead;
	private int numLevels = 10;
	private int score = 0;
	protected int newItemY;
	private boolean gameBeaten;
	private boolean gameOver;

	public WorldComponent(Dimension size, int level, PlayerListener listener, JFrame frame) {

		this.player = new Player(0, 0, size, this);
		this.spaceship = new SpaceShip(0, 0, size);
		this.worldSize = size;
		this.level = level;
		this.playerIsDead = false;
		listener.spawnPlayer(this.player);
		this.platform = new ArrayList<>();
		this.itemsToRemove = new ArrayList<>();
		this.enemiesToRemove = new ArrayList<>();
		this.bulletsToRemove = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.bullets = new ArrayList<>();
		this.items = new ArrayList<>();
		this.loader = new LoadLevel(this.platform, this.player, this.worldSize, this.enemies, this.items,
				this.spaceship, this);
		this.frame = frame;
		this.gameBeaten = false;
		this.gameOver = false;
		loadNewLevel();
	}

	// Load the same level again
	public void loadNewLevel() {

		this.loader.loadLevel(this.level);

	}

	// Load the next level in the game
	public void nextLevel() {

		// If the last level has not been beaten, increment level
		if (this.level < this.numLevels) {
			this.enemies.clear();
			this.bullets.clear();
			this.items.clear();
			this.spaceship.setFuelcount(0);
			this.spaceship.setPartCount(0);
			this.level += 1;
			this.updateLevel(this.level);
		}
	}
	// Load in the previous level
	public void previousLevel() {

		if(this.level > 1) {
			this.enemies.clear();
			this.bullets.clear();
			this.items.clear();
			this.spaceship.setFuelcount(0);
			this.spaceship.setPartCount(0);
			this.level -= 1;
			this.updateLevel(this.level);
						
		}
	}
	
	public void restartGame() {
		this.enemies.clear();
		this.bullets.clear();
		this.items.clear();
		this.spaceship.setFuelcount(0);
		this.spaceship.setPartCount(0);
		this.player.setLifeCount(3);
		this.gameOver = false;
		this.gameBeaten = false;
		this.updateLevel(1);
	}

	// Draw the graphics at the updated positions
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2 = (Graphics2D) graphics;

		if(!(this.gameBeaten) && !(this.gameOver)) {
			
			for (Platform platforms : platform) {
				platforms.drawOn(g2);
			}
			
			this.spaceship.drawOn(g2);
			
			for (Enemy enemy : enemies) {
				enemy.drawOn(g2);
			}
			
			for (Bullet bullet : bullets) {
				bullet.drawOn(g2);
			}
			
			for (Item i : items) {
				i.drawOn(g2);
			}
			
			this.player.drawOn(g2);
			
		} else if(!(this.gameOver)){
			g2.setColor(java.awt.Color.RED);
			g2.drawString("You Win!!!", this.worldSize.width / 2 - 55, this.worldSize.height / 4);
			g2.setColor(java.awt.Color.ORANGE);
			g2.drawString("Press the 0 key to play again", this.worldSize.width / 2 - 105, this.worldSize.height / 4 + 20);
		}
		
		if(!(this.gameBeaten) && this.gameOver) {
			g2.setColor(java.awt.Color.RED);
			g2.drawString("Game Over!", this.worldSize.width / 2 - 55, this.worldSize.height / 4);
			g2.setColor(java.awt.Color.ORANGE);
			g2.drawString("Press the 0 key to play again", this.worldSize.width / 2 - 105, this.worldSize.height / 4 + 20);
			
		} 

	}

	// Change to new level
	public void updateLevel(int nextLevel) {

		this.level = nextLevel;
		loadNewLevel();
	}

	// update all the graphics objects
	public void update() {

		updateMoverPosition();
		updateFollowerPosition();
		updateMedusaHeadPosition();
		updateBulletPosition();
		updateItemPosition();
		if(this.spaceship.isFull()) {
			this.spaceship.y -= 5;
			if(this.spaceship.y <= 0 && this.level < 10) {
				this.bullets.clear();
				this.enemies.clear();
				this.items.clear();
				this.nextLevel();
			} else {
				this.bullets.clear();
				this.enemies.clear();
				this.items.clear();
				this.gameBeaten = true;
			}
		}
		this.repaint();

		// Add a title to the window
		this.frame.setTitle(
				"Points: " + Integer.toString(this.score) + " Lives: " + Integer.toString(this.player.getLifeCount()));

		// perform necessary actions once player loses a life
		if (playerIsDead) {

			// subtract one life from player
			this.player.subtractLife();
			this.enemies.clear();
			this.bullets.clear();
			this.items.clear();
			this.spaceship.setFuelcount(0);
			this.spaceship.setPartCount(0);

			// handle the event of no lives left
			if (this.player.getLifeCount() <= 0) {
				
				this.gameOver = true;
			}
			
			// reset the level to beginning
			updateLevel(this.level);

			// change boolean flag to false again
			this.playerIsDead = false;
		}
	}

	// Updates the player and item movements
	public void updateMoverPosition() {

		int gravity = 5;
		int newX = this.player.getX();
		int newY = this.player.getY();

		// Iterate through all platform objects to find if player touches any
		for (Platform platforms : platform) {

			// If touching platform, player cant move down
			if (this.player.touches(platforms)) {

				this.player.setCanMoveDown(false);
				break;

			} else {

				// If not hitting a platform, player can move down
				this.player.setCanMoveDown(true);
			}
		}

		// Create gravity effect for all items dropped or spawned in the air. No, this
		// is not efficient. Yes, it does work.

		for (Item i : items) {

			for (Platform p : platform) {

				if (i.touches(p)) {

					i.setCanMoveDown(false);
					break;

				} else {

					i.setCanMoveDown(true);
				}
			}

			if (i.getCanMoveDown()) {

				// If item can move down, fall slower than gravity
				newItemY = i.getY() + gravity;
				newItemY += i.getYDirection();
				i.setPosition(i.getX(), newItemY);
			}
		}

		// If the player can move down, fall at gravity speed
		if (this.player.getCanMoveDown()) {

			newY += gravity;
		}

		if (this.player.isActivated() && this.player.canBeMoved) {

			if (this.player.getXDirection() > 0 && this.player.getCanMoveRight()) {

				newX += this.player.getXDirection();
			}

			if (this.player.getXDirection() < 0 && this.player.getCanMoveLeft()) {

				newX += this.player.getXDirection();
			}

			if (this.player.getYDirection() < 0 && this.player.getCanMoveUp()) {

				newY += this.player.getYDirection() - gravity;

			}

			if (this.player.getYDirection() > 0 && this.player.getCanMoveDown()) {

				newY += this.player.getYDirection();

			}

			if (newX > 0 && newX < this.worldSize.width - (4 * this.player.getScaledX()) && newY > 0
					&& newY < this.worldSize.height - (4 * this.player.getScaledY())) {

				this.player.setPosition(newX, newY);
			}
		}
		
		//Checks to see if the player is being shot at
		for(Bullet bullet: bullets) {
			
			if(this.player.touches(bullet)) {
				
				this.player.subtractLife();
				this.bulletsToRemove.add(bullet);
				
				if(this.player.getLifeCount() <= 0) {
					this.playerIsDead = true;
				}
			}
		}
		for(Bullet b: bulletsToRemove) {
			this.bullets.remove(b);
		}
	}

	public void updateFollowerPosition() {

		// Apply these conditions to every follower that currently exists
		for (Enemy follower : this.enemies) {

			// enemies that are shot by the player are added to an arraylist for removal
			// after the for loop
			for (Bullet b : this.bullets) {

				if (follower.touches(b) && !(b.badBullet)) {

					this.enemiesToRemove.add(follower);
				}
			}

			// If a given follower touches the player, make the follower and player
			// disappear
			if (follower.touches(this.player)) {

				// Let the updater know the player has died
				playerIsDead = true;

				// Prevent more enemies from spawning
				this.enemies.remove(follower);
				break;

			} else if (follower.isFollower) {

				// Adjust enemy directions to get closer to player
				if (follower.getX() < this.player.getX() && follower.xDirection < 0) {

					follower.xDirection = Math.abs(follower.xDirection);

				} else if (follower.getX() > this.player.getX() && follower.xDirection > 0) {

					follower.xDirection *= -1;
				}

				if (follower.getY() < this.player.getY() && follower.yDirection < 0) {

					follower.yDirection = Math.abs(follower.yDirection);

				} else if (follower.getY() > this.player.getY() && follower.yDirection > 0) {

					follower.yDirection *= -1;
				}
			}

			// Set the updated position of the follower enemy
			follower.setPosition(follower.getX() + follower.getXDirection(),
					follower.getY() + follower.getYDirection());
		}

		// remove all enemies shot by player
		for (Enemy e : this.enemiesToRemove) {

			this.enemies.remove(e);
		}
	}

	// Updates the positon of all Medusa head enemies
	public void updateMedusaHeadPosition() {

		// Iterate through all enemies
		for (Enemy head : this.enemies) {

			// Iterate through all bullets
			for (Bullet b : this.bullets) {

				// If the Medusa head touches a bullet, it should die
				if (head.touches(b) && !(b.badBullet)) {

					// Add the enemy object to an arraylist used to store objects to be deleted
					this.enemiesToRemove.add(head);
				}
			}

			// If the enemy is not a follower
			if (!(head.isFollower)) {
				
				
				if (Math.abs(head.centerPoint - head.getY()) > head.maxDistance) {

					head.setYDirection(head.getYDirection() * -1);
				}
				if (head.getX() > this.worldSize.width || head.getX() < 0) {

					head.setXDirection(head.getXDirection() * -1);
				}
				head.shoot();
				
			}

			// Update the enemy position from its velocity
			head.setPosition(head.getX() + head.getXDirection(), head.getY() + head.getYDirection());
		}

		// remove all enemies shot by player
		for (Enemy e : this.enemiesToRemove) {

			this.enemies.remove(e);
		}
	}

	// Gives the updated position of all bullets
	private void updateBulletPosition() {

		// Iterate through all bullets being shot
		for (Bullet bullet : this.bullets) {

			// update the bullet position
			bullet.setPosition(bullet.getX() + bullet.getXDirection(), bullet.getY() + bullet.getYDirection());
		}
	}

	// Updates item positions. This is not working yet. The KeyboardMenu.java file
	// handles the "d" button press but doesnt drop correctly yet.
	private void updateItemPosition() {

		// Iterate through all items added from the level loader
		for (Item item : this.items) {

			// Only move the item if the player is not currently carrying an item
			if (!(item.isCarried)) {

				if (item.touches(this.player)) {

					// Give the item object to the player
					this.player.setItemCarried(item);

					// Set the item rectangle box on the top right of the player so it is visible
					item.setPosition(this.player.getX() + 15, this.player.getY() - 15);
					
				}
				
				if(item.touches(this.spaceship) && !(item.isFuel())) {
					this.spaceship.addPart();

					this.itemsToRemove.add(item);
				}
				
				if (item.touches(this.spaceship) && this.spaceship.getPartCount() >= 3 && item.isFuel()) {

					this.spaceship.addFuel();

					this.itemsToRemove.add(item);

				} else if (item.touches(this.spaceship) && (spaceship.isFull()) && item.isFuel()) {

					this.spaceship.takeoff();

		

				} 

			} else {
				
				

				
			}
			
			//Makes sure that the items cannot be thrown offscreen and softlock the game
			if(item.x + item.scaledX * 3 > this.worldSize.width ) {
				
				item.x--;
				
			} else if(item.x < 0) {
				
				item.x++;
			}
		}

		for (Item i : this.itemsToRemove) {

			this.items.remove(i);
		}
	}
}
