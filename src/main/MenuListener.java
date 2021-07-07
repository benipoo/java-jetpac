package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
/**
 * 
 * Checks to see if the start game button has been pressed and
 * starts the game if it has been.
 * 
 * @author ludlowbj & feastebj
 *
 */
public class MenuListener implements ActionListener {

	private JFrame frame;
	private JFrame menu;

	public MenuListener(JFrame menu, JFrame frame) {

		this.frame = frame;
		this.menu = menu;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		this.frame.setVisible(true);
		this.menu.setVisible(false);
	}

}
