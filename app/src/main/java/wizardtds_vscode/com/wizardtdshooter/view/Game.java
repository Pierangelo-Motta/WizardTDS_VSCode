package wizardtds_vscode.com.wizardtdshooter.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import wizardtds_vscode.com.wizardtdshooter.controller.BufferedImageLoader;
import wizardtds_vscode.com.wizardtdshooter.controller.Handler;
import wizardtds_vscode.com.wizardtdshooter.controller.KeyInput;
import wizardtds_vscode.com.wizardtdshooter.controller.MouseInput;
import wizardtds_vscode.com.wizardtdshooter.controller.SpriteSheet;
import wizardtds_vscode.com.wizardtdshooter.model.Block;
import wizardtds_vscode.com.wizardtdshooter.model.Crate;
import wizardtds_vscode.com.wizardtdshooter.model.Door;
import wizardtds_vscode.com.wizardtdshooter.model.Enemy;
import wizardtds_vscode.com.wizardtdshooter.model.ID;
import wizardtds_vscode.com.wizardtdshooter.model.MagicBullet;
import wizardtds_vscode.com.wizardtdshooter.model.Wizard;

public class Game extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private Timer timer;
	private Handler handler;
	private SpriteSheet ss;
	private BufferedImage level = null;
	private BufferedImage spriteSheet = null;
	private BufferedImage floor = null;
	private Camera camera;
	private int lifeLenght = Window.hp * 4;

	public Game() {
		////////////////////////
		handler = new Handler();
		camera = new Camera();

		BufferedImageLoader loader = new BufferedImageLoader();
		this.level = loader.loadImage("/wizard_level.png");
		this.spriteSheet = loader.loadImage("/wizard_images.png");
		this.ss = new SpriteSheet(spriteSheet);
		this.floor = ss.grabImage(4, 2, 32, 32);
		loadLevel(level);
		/////////////////////////
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		// inizializzo l'input da tastiera
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, camera, ss));
		this.start();

	}

	private void start() {
		isRunning = true;
		timer = new Timer(10, this);
		timer.start();
	}

	private void stop() {
		timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isRunning) {
			tick();
			repaint();
		} else {
			stop();
		}
	}

	/** uses class Handler as a wrapper to reach each object */
	public void tick() {
		int enemy = 0;
		// Update Camera
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				camera.tick(handler.object.get(i));
			}
			if (handler.object.get(i).getId() == ID.Enemy) {
				enemy++;
			}
		}
		handler.tick();
		if (enemy <= 0) {
			this.isRunning = false;
		}
		enemy = 0;
	}

	public void paint(Graphics g) {
		osSupport();
		Graphics2D g2 = (Graphics2D) g;
		////////////////////////////////
		// everything beetween the 2 translate is translated!
		g2.translate(-camera.getX(), -camera.getY());

		for (int xx = 0; xx < 30 * 72; xx += 32) {
			for (int yy = 0; yy < 30 * 72; yy += 32) {
				g.drawImage(this.floor, xx, yy, null);
			}
		}
		handler.render(g);
		g2.translate(camera.getX(), camera.getY());

		g.setColor(Color.red.darker());
		// g.fillRect(20, 20, 200, 25);
		g.fillRect(20, 20, lifeLenght, 25);
		g.setColor(Color.green.darker());
		g.fillRect(20, 20, Window.hp * 4, 25);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 0, 16));
		g.drawString("Life: " + Window.hp, 20, 40);

		int ammo = Window.ammo % 16;
		int onHand = Window.ammo / 16;
		g.drawString("" + onHand, 105, 60);
		g.setFont(new Font("Arial", 0, 16));
		g.drawString("Ammo:  " + ammo, 20, 60);
		////////////////////////////////
		g.dispose();
	}

	// Loading the level
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		System.out.println("loadLevel: w " + w + " x h " + h);

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);

				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255 && blue == 0 && green == 0) {
					handler.addObject(new Block(xx * 32, yy * 32, ID.Block, ss));
				}
				if (green == 255 && blue != 255 && red != 255) {
					handler.addObject(new Enemy(xx * 32, yy * 32, ID.Enemy, handler, ss));
				}
				if (blue == 255 && green != 255 && red != 255) {
					handler.addObject(new Wizard(xx * 32, yy * 32, ID.Player, handler, ss));
				}
				if (blue == 255 && green == 255 && red != 255) {
					handler.addObject(new Crate(xx * 32, yy * 32, ID.Crate, ss));
				}
				if (blue == 255 && green == 255 && red == 255) {
					handler.addObject(new Door(xx * 32, yy * 32, ID.Door, ss));
				}
				if (blue == 0 && green == 216 && red == 255) {
					handler.addObject(new MagicBullet(xx * 32, yy * 32, ID.MagicBullet, ss));
				}
			}
		}
	}

	private void osSupport() {
		if (Window.operatingSystem.equals("Linux")) {
			Toolkit.getDefaultToolkit().sync();
		}
	}
}
