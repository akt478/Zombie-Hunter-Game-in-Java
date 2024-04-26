package com.akul.gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.accessibility.*;

import com.akul.gaming.sprites.Enemy;
import com.akul.gaming.sprites.Player;
import com.akul.gaming.sprites.PlayerShooting;

public class Board extends JPanel {
	Timer timer;
	BufferedImage backgroundimage;
	BufferedImage backgroundimage2;
	PlayerShooting playershoot;
	Player playerimage;
	Enemy enemyimages[]= new Enemy[7];
	private boolean isPlayerImage= true;
	private int playerX;
	private int playerY;
	private boolean[] enemyCollided = new boolean[7];
	public Board() {
		setSize(1500,820);
		loadBackgroundImage();
		playerimage= new Player();	
	    playershoot= new PlayerShooting();
		loadEnemies();
		playerLoop();
		bindEvents();
		printNewPlayerGif();
		setFocusable(true);
	}
	private void gameOver(Graphics pen) {
		boolean allEnemiesEliminated = true;
	    if (playerimage.outofScreen() || playershoot.outofScreen()) {
	        pen.setFont(new Font("times", Font.BOLD, 30));
	        pen.setColor(Color.red);
	        pen.drawString("Congratulations, You have Passed", 550, 820 / 2);
	        timer.stop();
	    } else {
	    	 for (int i = 0; i < enemyimages.length; i++) {
	             if (isCollidePlayerImage(enemyimages[i])) {
	                 pen.setFont(new Font("times", Font.BOLD, 30));
	                 pen.setColor(Color.red);
	                 pen.drawString("Game Over", 700, 820 / 2);
	                 timer.stop();
	                 return; // Exit the method immediately after displaying the game over message
	             } else if (enemyimages[i].isVisible()) {
	                 allEnemiesEliminated = false;
	             }
	         }
	        if (allEnemiesEliminated) {
	            pen.setFont(new Font("times", Font.BOLD, 30));
	            pen.setColor(Color.red);
	            pen.drawString("Congratulations, You have Eliminated All Enemies!", 400, 820 / 2);
	            timer.stop();
	        }
	    }
	}

	private boolean isCollide(Enemy enemy) {
		int xDistance= Math.abs(playershoot.x- enemy.x);
		int yDistance= Math.abs(playershoot.y- enemy.y);
		int maxW= Math.max(playershoot.w, enemy.w);
		int maxH= Math.max(playershoot.h, enemy.h);
		return xDistance < maxW-70 && yDistance < maxH-70;
	}
	private boolean isCollidePlayerImage(Enemy enemy) {
	    int xDistance = Math.abs(playerimage.x - enemy.x);
	    int yDistance = Math.abs(playerimage.y - enemy.y);
	    int maxW = Math.max(playerimage.w, enemy.w);
	    int maxH = Math.max(playerimage.h, enemy.h);
	    return xDistance < maxW - 90 && yDistance < maxH - 90;
	}
	private void bindEvents() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					playerimage.speed= 12;
					}
				else if(e.getKeyCode()==KeyEvent.VK_LEFT){
					playerimage.speed=-12;
				}
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				playerimage.speed= 0;
				// TODO Auto-generated method stub
			}
			
		});
	}
	private void loadEnemies() {
		int x= 350;
		int gap= 160;
		int speed= 5;
		for(int i=0;i<enemyimages.length;i++) {
			enemyimages[i]= new Enemy(x, speed);
			x= x+gap;
			speed = speed+5;
		}
	}
	private void playerLoop() {
		timer= new Timer(50, (e)->repaint());
		timer.start();
	}
	private void loadBackgroundImage() {
		try {
			backgroundimage= ImageIO.read(Board.class.getResource("game-bg-transformed.jpeg"));
			backgroundimage2= ImageIO.read(Board.class.getResource("R.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Background Image not found.....");
			System.exit(1);
			e.printStackTrace();
		}
	}
	private void printNewPlayerGif() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				 //TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					playershoot.speed =playerimage.speed;
					//repaint();
				}
				else if(e.getKeyCode()==KeyEvent.VK_LEFT){
					playershoot.speed =playerimage.speed;
					//repaint();
				// TODO Auto-generated method stub
			}
				else if(e.getKeyCode()==KeyEvent.VK_SPACE){
					playershoot.move();
					toggleImage();
					playerX= playerimage.x;
					playerY= playerimage.y;
					//repaint();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				playershoot.speed= 0;
				repaint();
				// TODO Auto-generated method stub
			}
		});
	}
	private void toggleImage() {
		isPlayerImage= !isPlayerImage;
	}
	private void printEnemies(Graphics pen) {
		 for (int i = 0; i < enemyimages.length; i++) {
	            Enemy enemy = enemyimages[i];
	            if (!enemyCollided[i] && enemy.isVisible()) {
	                enemy.draw(pen);
	                enemy.move();
	                if (!isPlayerImage && isCollide(enemy)) {
	                    enemyCollided[i] = true; // Mark this enemy as collided
	                    enemy.setVisible(false);
	                }
	            }
	        }
	    }
	@Override
	public void paintComponent(Graphics pen) {
		// all printing logic will be here
		super.paintComponent(pen); //clean up
		pen.drawImage(backgroundimage,0,0,1530,830,null);
		pen.drawImage(backgroundimage2,325,40,1150,710,null);
		if(isPlayerImage) {
			playerimage.draw(pen);
			playerimage.move();
		}
		else {
			playershoot.x= playerX;
			playershoot.y= playerY;
			playershoot.draw(pen);
			playershoot.move();
		}
		printEnemies(pen);
		gameOver(pen);
	}
}
