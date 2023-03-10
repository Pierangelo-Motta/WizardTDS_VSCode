package wizardtds_vscode.com.wizardtdshooter.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import wizardtds_vscode.com.wizardtdshooter.model.Bullet;
import wizardtds_vscode.com.wizardtdshooter.model.GameObject;
import wizardtds_vscode.com.wizardtdshooter.model.ID;
import wizardtds_vscode.com.wizardtdshooter.view.Camera;
import wizardtds_vscode.com.wizardtdshooter.view.Window;

public class MouseInput extends MouseAdapter {

	private Handler handler;
	private Camera camera;
	private SpriteSheet ss;
	private Audio audio;
	private int magicShotCounter = 0;

	public MouseInput(Handler handler, Camera camera, SpriteSheet ss) {
		this.handler = handler;
		this.camera = camera;
		this.ss = ss;
		this.audio = new Audio("resources\\magic-chime-01.wav");
	}

	public void mousePressed(MouseEvent e) {
		if (Window.magicShot) {
			magicShotCounter++;
		}
		if (magicShotCounter > 10) {
			Window.magicShot = false;
		}
		int mx = (int) (e.getX() + camera.getX()); // getting mouse x position
		int my = (int) (e.getY() + camera.getY()); // getting mouse y position
		System.out.println("Mouse click: [" + mx + "," + my + "]");
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				if (Window.ammo > 0) {
					handler.addObject(
							// 16 e 24 sono due valori che determinano da dove spara il wizard
							new Bullet(tempObject.getX() + 16, tempObject.getY() + 20, ID.Bullet, handler, ss, mx, my));
					Window.ammo -= 1;

					// Play magic effect
					// audio.play();
				}
			}
		}
	}

}
