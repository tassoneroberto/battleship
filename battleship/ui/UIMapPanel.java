package battleship.ui;

import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UIMapPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	static int X = 570;
	static int Y = 630;
	int numC = 10;
	int dimC = 48;
	int oroff = 1;
	int veroff = 1;
	int c1Off = 0;
	int c2Off = 0;
	JButton[][] bottoni;
	JLabel[] COr;
	JLabel[] CVer;
	protected JLabel label;
	UIJPanelBG sea;
	Cursor cursorHand = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	Cursor cursorDefault = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	public UIMapPanel(String etichetta) {

		this.setSize(X, Y);
		this.setLayout(null);
		this.setOpaque(false);
		// Etichetta
		label = new JLabel();
		label.setIcon(new ImageIcon(getClass().getResource(("/res/images/" + etichetta + ".png"))));
		this.add(label);
		label.setBounds(50, 0, 550, 60);
		// Pannello contenente le caselle
		sea = new UIJPanelBG(
				Toolkit.getDefaultToolkit().createImage(FrameManageship.class.getResource("/res/images/sea.png")));
		sea.setBounds(34, 45, 550, 550);
		bottoni = new JButton[numC][numC];
		ImageIcon gray = new ImageIcon(getClass().getResource("/res/images/grayButtonOpaque.png"));
		for (int i = 0; i < numC; i++) {
			for (int j = 0; j < numC; j++) {
				bottoni[i][j] = new JButton(gray);
				bottoni[i][j].setSize(dimC, dimC);
				sea.add(bottoni[i][j]);
				bottoni[i][j].setCursor(cursorHand);
				bottoni[i][j].setBorder(null);
				bottoni[i][j].setOpaque(false);
				bottoni[i][j].setBorderPainted(false);
				bottoni[i][j].setContentAreaFilled(false);
				bottoni[i][j].setBounds(oroff, veroff, dimC, dimC);
				if (etichetta.equals("player")) {
					bottoni[i][j].setCursor(cursorDefault);
					bottoni[i][j].setDisabledIcon(gray);
					bottoni[i][j].setEnabled(false);
				} else {
					bottoni[i][j].setCursor(cursorHand);
				}
				oroff += dimC + 2;
			}
			veroff += dimC + 2;
			oroff = 1;
		}
		oroff = 40;
		veroff = 0;
		JPanel grid = new JPanel(null);
		grid.setOpaque(false);
		grid.add(sea);
		COr = new JLabel[10];
		CVer = new JLabel[10];
		// For che caricare le immagini delle coordinate
		for (int i = 0; i < 10; i++) {
			COr[i] = new JLabel();
			CVer[i] = new JLabel();
			grid.add(COr[i]);
			grid.add(CVer[i]);
			CVer[i].setIcon(new ImageIcon(getClass().getResource((("/res/images/coord/" + (i + 1) + ".png")))));
			CVer[i].setBounds(veroff, oroff, dimC, dimC);
			COr[i].setIcon(new ImageIcon(getClass().getResource((("/res/images/coord/" + (i + 11) + ".png")))));
			COr[i].setBounds(oroff, veroff, dimC, dimC);
			oroff += 50;
		}

		this.add(grid);
		grid.setBounds(0, 45, 550, 660);

	}

	void disegnaNave(int[] dati) {
		int x = dati[0];
		int y = dati[1];
		int dim = dati[2];
		int dir = dati[3];
		ImageIcon shipDim1orizz = new ImageIcon(
				getClass().getResource("/res/images/shipDim1orizz.png"));
		ImageIcon shipDim1vert = new ImageIcon(getClass().getResource("/res/images/shipDim1vert.png"));
		if (dim == 1) {
			bottoni[x][y].setEnabled(false);
			if (dir == 0)
				bottoni[x][y].setDisabledIcon(shipDim1orizz);
			else if (dir == 1)
				bottoni[x][y].setDisabledIcon(shipDim1vert);
		} else {
			ImageIcon shipHeadLeft = new ImageIcon(
					getClass().getResource("/res/images/shipHeadLeft.png"));
			ImageIcon shipHeadTop = new ImageIcon(
					getClass().getResource("/res/images/shipHeadTop.png"));
			ImageIcon shipBodyLeft = new ImageIcon(
					getClass().getResource("/res/images/shipBodyLeft.png"));
			ImageIcon shipBodyTop = new ImageIcon(
					getClass().getResource("/res/images/shipBodyTop.png"));
			ImageIcon shipFootLeft = new ImageIcon(
					getClass().getResource("/res/images/shipFootLeft.png"));
			ImageIcon shipFootTop = new ImageIcon(
					getClass().getResource("/res/images/shipFootTop.png"));
			if (dir == 0) {// direzione orizzontale
				// Ship Head
				bottoni[x][y].setDisabledIcon(shipHeadLeft);
				bottoni[x][y].setEnabled(false);
				// Ship Body
				for (int i = 1; i < dim - 1; i++) {
					bottoni[x][y + i].setDisabledIcon(shipBodyLeft);
					bottoni[x][y + i].setEnabled(false);
				}
				// Ship Foot
				bottoni[x][y + dim - 1].setDisabledIcon(shipFootLeft);
				bottoni[x][y + dim - 1].setEnabled(false);
			} else { // direzione verticale
				// Ship Head
				bottoni[x][y].setDisabledIcon(shipHeadTop);
				bottoni[x][y].setEnabled(false);
				// Ship Body
				for (int i = 1; i < dim - 1; i++) {
					bottoni[x + i][y].setDisabledIcon(shipBodyTop);
					bottoni[x + i][y].setEnabled(false);
				}
				// Ship Foot
				bottoni[x + dim - 1][y].setDisabledIcon(shipFootTop);
				bottoni[x + dim - 1][y].setEnabled(false);
			}
		}
	}

}
