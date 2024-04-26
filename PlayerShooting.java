package com.akul.gaming.sprites;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class PlayerShooting extends Player {
	public PlayerShooting() {
		w=250;
		h=225;
		x=20;
		y=500;
		image= new ImageIcon(Player.class.getResource("goku-blast2.gif"));
	}
	public void draw(Graphics pen) {
		pen.drawImage(image.getImage(), super.x, y, w, h, null);
	}
	public void move(int x) {
		super.x= x+speed;
	}
	public boolean outofScreen() {
		return x+150>1500;
	}
}
