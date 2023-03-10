package wizardtds_vscode.com.wizardtdshooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import wizardtds_vscode.com.wizardtdshooter.controller.Handler;
import wizardtds_vscode.com.wizardtdshooter.controller.SpriteSheet;
import wizardtds_vscode.com.wizardtdshooter.view.Window;

public class Bullet extends GameObject {

	private Handler handler;
	private int VELOCITY_DIV = 20;

	public Bullet(int x, int y, ID id, Handler handler, SpriteSheet ss, int mx, int my) {
		super(x, y, id, ss);
		this.handler = handler;
		this.velX = (mx - x) / VELOCITY_DIV;
		this.velY = (my - y) / VELOCITY_DIV;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			// distrugge il proiettile se tocca un blocco
			if (tempObject.getId() == ID.Block && !Window.magicShot) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow.darker());
		g.fillOval(x, y, 15, 15);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 15, 15);
	}

}
