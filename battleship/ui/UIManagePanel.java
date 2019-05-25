package battleship.ui;

import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

public class UIManagePanel extends UIJPanelBG {
	private static final long serialVersionUID = 1L;
	Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	JRadioButtonMenuItem[] ship;
	JLabel[] counterLabel = new JLabel[4];
	JLabel[] xLabel = new JLabel[4];
	ButtonGroup radioButtonShip;
	JRadioButton[] direction;
	JButton random;
	JButton reset;
	JButton gioca;

	public UIManagePanel() {

		super(Toolkit.getDefaultToolkit()
				.createImage(FrameManageship.class.getResource("/res/images/managePanel.png")));
		this.setLayout(null);
		this.setOpaque(false);
		// Etichetta "Posiziona le navi"
		JLabel managePanelLabel = new JLabel();
		managePanelLabel.setIcon(new ImageIcon(getClass().getResource("/res/images/managePanelLabel.png")));
		managePanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		managePanelLabel.setBounds(0, 30, 280, 35);
		// RadioButton per la selezione delle navi
		ship = new JRadioButtonMenuItem[4];
		radioButtonShip = new ButtonGroup();
		JPanel shipSelect = new JPanel(null);
		shipSelect.setOpaque(false);
		shipSelect.setBounds(30, 90, 200, 300);
		ImageIcon ship1 = new ImageIcon(getClass().getResource("/res/images/ship1.png"));
		ImageIcon ship2 = new ImageIcon(getClass().getResource("/res/images/ship2.png"));
		ImageIcon ship3 = new ImageIcon(getClass().getResource("/res/images/ship3.png"));
		ImageIcon ship4 = new ImageIcon(getClass().getResource("/res/images/ship4.png"));
		ship[0] = new JRadioButtonMenuItem(ship4);
		ship[1] = new JRadioButtonMenuItem(ship3);
		ship[2] = new JRadioButtonMenuItem(ship2);
		ship[3] = new JRadioButtonMenuItem(ship1);
		counterLabel[0] = new JLabel("1");
		counterLabel[1] = new JLabel("2");
		counterLabel[2] = new JLabel("3");
		counterLabel[3] = new JLabel("4");
		for (int i = 0; i < ship.length; i++) {
			ship[i].setBounds(0, 25 + (i * 60), 160, 40);
			radioButtonShip.add(ship[i]);
			shipSelect.add(ship[i]);
			ship[i].setOpaque(false);
			counterLabel[i].setBounds(220, 125 + (i * 60), 23, 19);
			counterLabel[i].setOpaque(false);
			this.add(counterLabel[i]);
			xLabel[i] = new JLabel("x");
			xLabel[i].setBounds(205, 125 + (i * 60), 23, 19);
			xLabel[i].setOpaque(false);
			this.add(xLabel[i]);
		}
		ship[0].setSelected(true);
		// RadioButton per la selezione della direzione
		// Verticale/Orizzontale
		direction = new JRadioButton[2];
		ButtonGroup radioButtonDirection = new ButtonGroup();
		direction[0] = new JRadioButton("Orizzontale");
		direction[0].setBounds(0, 260, 105, 20);
		radioButtonDirection.add(direction[0]);
		direction[0].setSelected(true);
		direction[0].setOpaque(false);
		shipSelect.add(direction[0]);
		direction[1] = new JRadioButton("Verticale");
		direction[1].setBounds(110, 260, 105, 20);
		direction[1].setOpaque(false);
		radioButtonDirection.add(direction[1]);
		shipSelect.add(direction[1]);
		// Bottone Random
		ImageIcon randomImg = new ImageIcon(getClass().getResource("/res/images/random.png"));
		ImageIcon randomImgOver = new ImageIcon(getClass().getResource("/res/images/randomOver.png"));
		random = new JButton(randomImg);
		random.setRolloverIcon(randomImgOver);
		random.setBorder(null);
		random.setOpaque(false);
		random.setBorderPainted(false);
		random.setContentAreaFilled(false);
		random.setBounds(30, 380, 200, 30);
		random.setCursor(cursor);
		random.setText("random");
		// Bottone Reset
		ImageIcon resetImg = new ImageIcon(getClass().getResource("/res/images/reset.png"));
		ImageIcon resetImgOver = new ImageIcon(getClass().getResource("/res/images/resetOver.png"));
		reset = new JButton(resetImg);
		reset.setRolloverIcon(resetImgOver);
		reset.setBorder(null);
		reset.setOpaque(false);
		reset.setBorderPainted(false);
		reset.setContentAreaFilled(false);
		reset.setBounds(10, 500, 137, 102);
		reset.setCursor(cursor);
		reset.setText("reset");

		// Bottone Gioca
		ImageIcon giocaImg = new ImageIcon(getClass().getResource("/res/images/gioca.png"));
		ImageIcon giocaImgOver = new ImageIcon(getClass().getResource("/res/images/giocaOver.png"));
		gioca = new JButton(giocaImg);
		gioca.setRolloverIcon(giocaImgOver);
		gioca.setBorder(null);
		gioca.setOpaque(false);
		gioca.setBorderPainted(false);
		gioca.setContentAreaFilled(false);
		gioca.setBounds(150, 500, 137, 102);
		gioca.setCursor(cursor);
		gioca.setText("gioca");
		gioca.setEnabled(false);

		this.add(managePanelLabel);
		this.add(shipSelect);
		this.add(random);
		this.add(gioca);
		this.add(reset);

	}
}