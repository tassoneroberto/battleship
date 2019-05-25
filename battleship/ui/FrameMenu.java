package battleship.ui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FrameMenu extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = 2196160748036537248L;
	Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	JButton PvCPU;
	JButton PvP;

	public FrameMenu() {
		super("Battaglia Navale - Pirate Edition");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(630, 954);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);
		this.setLayout(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/res/images/icon.png")));
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

		UIJPanelBG container = new UIJPanelBG(Toolkit.getDefaultToolkit()
				.createImage(getClass().getResource("/res/images/menu2.jpg")));
		container.setBounds(0,0,630,954);
		this.add(container);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}