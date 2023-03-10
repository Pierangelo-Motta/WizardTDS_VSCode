package wizardtds_vscode.com.wizardtdshooter.view;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 563;
	public static final String operatingSystem = System.getProperty("os.name");
	public static int hp = 80;
	public static int ammo = 30;
	public static boolean magicShot = false;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.setMinimumSize(new Dimension(width, height));
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		System.out.println(operatingSystem);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		new Window(WIDTH, HEIGHT, "Wizard Top Down Shooter", game);

	}

}
