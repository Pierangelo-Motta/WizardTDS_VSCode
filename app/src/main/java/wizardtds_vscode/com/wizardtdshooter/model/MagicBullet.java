package wizardtds_vscode.com.wizardtdshooter.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import wizardtds_vscode.com.wizardtdshooter.controller.SpriteSheet;

public class MagicBullet extends GameObject {

	private BufferedImage magicBulletImage;

	public MagicBullet(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		this.magicBulletImage = ss.grabImage(3, 2, 32, 32);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(this.magicBulletImage, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
