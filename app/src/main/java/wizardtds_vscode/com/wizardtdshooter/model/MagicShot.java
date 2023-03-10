package wizardtds_vscode.com.wizardtdshooter.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import wizardtds_vscode.com.wizardtdshooter.controller.SpriteSheet;

public class MagicShot extends GameObject {

	private BufferedImage magicShot_image;

	public MagicShot(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		this.magicShot_image = ss.grabImage(3, 2, 32, 32);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(this.magicShot_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
