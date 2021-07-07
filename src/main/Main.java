package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start by running
 * main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author ludlowbj  & feastebj
 *
 */

public class Main {

	public static final Dimension WINDOW_SIZE = new Dimension(800, 800);
	public static final int TIME_DELAY = 15;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JButton start = new JButton("Start Game");
		JPanel menu = new JPanel();

		menu.add(start);

		JFrame menuFrame = new JFrame();
		JFrame levelFrame = new JFrame();

		levelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		levelFrame.setSize(WINDOW_SIZE);

		PlayerListener playerListener = new PlayerListener();
		MenuListener menuListener = new MenuListener(menuFrame, levelFrame);
		WorldComponent component = new WorldComponent(WINDOW_SIZE, 1, playerListener, levelFrame);
		GameListener gameListener = new GameListener(component);
		KeyboardMenu keyboardListener = new KeyboardMenu(component);

		levelFrame.getContentPane().setBackground(Color.BLACK);
		// menuFrame.getContentPane().setBackground(Color.GRAY);

		levelFrame.add(component);
		levelFrame.addKeyListener(playerListener);

		menuFrame.add(menu, BorderLayout.CENTER);
		menuFrame.setTitle("JetPac");
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setSize(WINDOW_SIZE);
		menuFrame.setVisible(true);

		levelFrame.addKeyListener(keyboardListener);
		start.addActionListener(menuListener);

		Timer timer = new Timer(TIME_DELAY, gameListener);
		timer.start();
	}

}
