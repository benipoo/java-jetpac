package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * Class which handles the inputs from the keyboard which are not related
 * to player input or movement.
 * 
 * @author ludlowbj & feastebj
 *
 */
public class KeyboardMenu implements KeyListener, ActionListener {

	private WorldComponent worldComponent;
	private Player player;

	public KeyboardMenu(WorldComponent comp) {

		this.worldComponent = comp;
		this.player = comp.player;
	}

	public KeyboardMenu(ArrayList<JFrame> levels, int x) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub.
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		int value1 = arg0.getKeyCode();
		if (value1 == KeyEvent.VK_U) {
			this.worldComponent.nextLevel();
		}

		int value2 = arg0.getKeyCode();
		if (value2 == KeyEvent.VK_D) {

			if (player.itemCarried != null) {
				player.dropItem(player.itemCarried);
			}

		}
		int value3 = arg0.getKeyCode();
		if(value3 == KeyEvent.VK_DOWN) {
			this.worldComponent.previousLevel();
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_0) {
			this.worldComponent.restartGame();
		}
	}

}
