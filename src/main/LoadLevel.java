package main;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class which loads in the level depending on the input level requested
 * and the respective file in the levels folder. 
 * 
 * 
 * @param int level, the level to be loaded
 * @author ludlowbj & feastebj
 *
 */
public class LoadLevel {

	private Dimension windowSize;
	private int unitWidth = 20;
	private int unitHeight = 20;
	private int platformWidth = 40;

	private ArrayList<Platform> platforms;
	private ArrayList<Enemy> enemies;
	private ArrayList<Item> items;
	private Player player;
	private SpaceShip spaceship;
	private WorldComponent component;

	LoadLevel(ArrayList<Platform> platforms, Player player, Dimension worldSize, ArrayList<Enemy> enemies,
			ArrayList<Item> items, SpaceShip spaceship, WorldComponent worldCom) {

		this.platforms = platforms;
		this.windowSize = worldSize;
		this.player = player;
		this.enemies = enemies;
		this.items = items;
		this.spaceship = spaceship;
		this.component = worldCom;
	}

	public void loadLevel(int level) {

		clear();
		String levelStr = "src/levels/level" + Integer.toString(level);
		File file = new File(levelStr);
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}

		String levelString = scanner.nextLine();
		int x, y = 0;

		for (int i = 0; i < this.unitHeight - 1; i++) {

			x = 0;
			for (int j = 0; j < levelString.length(); j++) {

				switch (levelString.charAt(j)) {
				case 'P':
					this.platforms.add(new Platform(x, y, this.windowSize));
					this.platforms
							.add(new Platform(x + this.windowSize.width / this.platformWidth, y, this.windowSize));

					x += this.windowSize.width / this.unitWidth;
					break;
				case 'I':
					this.player.setPosition(x, y);
					x += this.windowSize.width / this.unitWidth;
					break;
				case 'F':
					Follower follower = new Follower(x, y, this.windowSize, this.player);
					follower.setPosition(x, y);
					this.enemies.add(follower);
					x += this.windowSize.width / this.unitWidth;
					break;
				case 'M':
					MedusaHead head = new MedusaHead(x, y, this.windowSize, this.player, this.component);
					head.setPosition(x, y);
					this.enemies.add(head);
					x += this.windowSize.width / this.unitWidth;
					break;
				case 'S':
					ShipFragment fragment = new ShipFragment(x, y, this.windowSize, player);
					fragment.setPosition(x, y);
					this.items.add(fragment);
					x += this.windowSize.width / this.unitWidth;
					break;
				case 'A':
					this.spaceship.setPosition(x, y);
					x += this.windowSize.width / this.unitWidth;
					break;
				case 'L':
					Fuel fuel = new Fuel(x, y, this.windowSize, player);
					fuel.setPosition(x, y);
					this.items.add(fuel);
					x += this.windowSize.width / this.unitWidth;
					break;

				default:
					x += this.windowSize.width / this.unitWidth;
					break;
				}
			}

			y += this.windowSize.height / this.unitHeight;

			if (scanner.hasNextLine()) {
				levelString = scanner.nextLine();
			}
		}

		scanner.close();
		return;
	}

	public void clear() {
		if (this.platforms.size() != 0) {
			this.platforms.clear();
		}
	}

}
