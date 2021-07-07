package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class which updates the instance of worldComponent whenever the game is updated.
 * 
 * @author ludlowbj  & feastebj
 *
 */
public class GameListener implements ActionListener {

	private WorldComponent worldComponent;

	public GameListener(WorldComponent comp) {
		this.worldComponent = comp;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.worldComponent.update();

	}

}
