package battleship;

import battleship.ui.FrameManageship;
import battleship.ui.FrameSplashscreen;

public class BattleShip {

	public static void main(String[] args) {
		FrameSplashscreen intro = new FrameSplashscreen();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
		intro.setVisible(false);
		// FrameManageship2PlClient manage = new FrameManageship2PlClient();
		// FrameManageship2PlServer manage = new FrameManageship2PlServer();
		FrameManageship manage = new FrameManageship();
		// FrameMenu menu = new FrameMenu();
		manage.setVisible(true);
		// menu.setVisible(true);
	}
}