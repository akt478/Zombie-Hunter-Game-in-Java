package com.akul.gaming;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	public GameFrame() {
		Board board= new Board();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1530,830);
		setLocationRelativeTo(null);
		setTitle("Zombie Shooter");
		setResizable(false);
		add(board);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameFrame();
	}
}
