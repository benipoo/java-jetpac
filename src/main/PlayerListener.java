package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * 
 * Listener class which handles keyboard actions which affect the 
 * player character
 * 
 * @author ludlowbj & feastebj
 *
 */
public class PlayerListener implements KeyListener {

	private Player player;
	private int key;

	public PlayerListener() {

		this.key = KeyEvent.VK_LEFT;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		this.player.setXDirection(0);
		this.player.setYDirection(0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		int input = arg0.getKeyCode();

		switch (input) {

		case KeyEvent.VK_UP:
			this.key = KeyEvent.VK_UP;
			this.player.setYDirection(-5);
			break;	

		case KeyEvent.VK_RIGHT:
			this.key = KeyEvent.VK_RIGHT;
			this.player.setXDirection(5);
			this.player.setFacingRight(true);
			break;

		case KeyEvent.VK_LEFT:
			this.key = KeyEvent.VK_LEFT;
			this.player.setXDirection(-5);
			this.player.setFacingRight(false);
			break;

		case KeyEvent.VK_SPACE:
			this.key = KeyEvent.VK_SPACE;
			this.player.shoot();

		default:
			break;
		}
	}

	public void spawnPlayer(Player p1) {

		this.player = p1;
	}

}
