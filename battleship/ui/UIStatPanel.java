package battleship.ui;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UIStatPanel extends UIJPanelBG {
	private static final long serialVersionUID = 1L;
	JLabel[] ships = new JLabel[10];

	public UIStatPanel() {
		super(Toolkit.getDefaultToolkit()
				.createImage(FrameManageship.class.getResource("/res/images/battlePaper.png")));
		for (int i = 0; i < ships.length; i++) {
			ships[i] = new JLabel();
			this.add(ships[i]);
		}
		ships[0].setIcon(new ImageIcon(getClass().getResource("/res/images/ship4.png")));
		ships[1].setIcon(new ImageIcon(getClass().getResource("/res/images/ship3.png")));
		ships[2].setIcon(new ImageIcon(getClass().getResource("/res/images/ship3.png")));
		ships[3].setIcon(new ImageIcon(getClass().getResource("/res/images/ship2.png")));
		ships[4].setIcon(new ImageIcon(getClass().getResource("/res/images/ship2.png")));
		ships[5].setIcon(new ImageIcon(getClass().getResource("/res/images/ship2.png")));
		ships[6].setIcon(new ImageIcon(getClass().getResource("/res/images/ship1.png")));
		ships[7].setIcon(new ImageIcon(getClass().getResource("/res/images/ship1.png")));
		ships[8].setIcon(new ImageIcon(getClass().getResource("/res/images/ship1.png")));
		ships[9].setIcon(new ImageIcon(getClass().getResource("/res/images/ship1.png")));

		ships[0].setBounds(25, 5, 135, 35);
		ships[1].setBounds(215, 5, 103, 35);
		ships[2].setBounds(375, 5, 103, 35);
		ships[3].setBounds(25, 40, 70, 35);
		ships[4].setBounds(105, 40, 70, 35);
		ships[5].setBounds(185, 40, 70, 35);
		ships[6].setBounds(265, 40, 47, 35);
		ships[7].setBounds(322, 40, 47, 35);
		ships[8].setBounds(379, 40, 47, 35);
		ships[9].setBounds(436, 40, 47, 35);

	}
}