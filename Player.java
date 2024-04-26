package com.akul.gaming.sprites;

import javax.swing.ImageIcon;

public class Player extends Sprite {
	public Player() {
		w=175;
		h=175;
		x=20;
		y=500;
		image= new ImageIcon(Player.class.getResource("goku-flying-2.gif"));
	}
	public void move() {
		x= x+speed;
	}
	public boolean outofScreen() {
		return x+150>1500;
	}
}
