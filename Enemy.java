package com.akul.gaming.sprites;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Enemy extends Sprite {
	private boolean visible= true;
	public Enemy(int x, int speed) {
		w=140;
		h=150;
		this.x= x; //350
		this.speed= speed;
		y=5;
		image= new ImageIcon(Enemy.class.getResource("zombie-2.gif"));
	}
	public void move() {
		if(y>600) {
			y=20;
		}
		y= y+speed;
	}
	 public void setVisible(boolean visible) {
	        this.visible = visible;
	    }
	 public boolean isVisible() {
	        return visible;
	    }
}
