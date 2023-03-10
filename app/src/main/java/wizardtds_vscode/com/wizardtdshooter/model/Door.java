package wizardtds_vscode.com.wizardtdshooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import wizardtds_vscode.com.wizardtdshooter.controller.SpriteSheet;

public class Door extends GameObject {

	public Door(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 32);
	}

}
