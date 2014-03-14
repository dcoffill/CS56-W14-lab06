package edu.ucsb.cs56.w14.drawings.dcoffill.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimatedPictureViewer {

	private AnimationPanel panel = new AnimationPanel();
	private LaptopWithScreen lws = new LaptopWithScreen(400, 400, 300, 200);
	private int x = 200, y = 0;
	private double xCoord, yCoord;

	Thread animation;

	public static void main(String[] args) {
		AnimatedPictureViewer apv = new AnimatedPictureViewer();
		apv.go();
	}


	public void go() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.setSize(800, 600);
		frame.setVisible(true);
		animation = new Animation();
		animation.start();
		panel.repaint();
	}

	class AnimationPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;

			// Reset panel background
			g2.setColor(Color.white);
			g2.fillRect(0,0, this.getWidth(), this.getHeight());

			// Draw laptop
			g2.setColor(Color.CYAN);
			LaptopWithScreen lwsDraw = new LaptopWithScreen(xCoord, yCoord, 300, 200);
			g2.draw(lwsDraw);

		}
	}

	class Animation extends Thread {
		public void run() {
			try {
				while(true) {
					xCoord = 250 + 200*Math.cos(Math.toRadians(x));
					yCoord = 350 + 150*Math.sin(Math.toRadians(y));
					panel.repaint();
					Thread.sleep(30);
					++x;
					++y;
				}
			} catch(Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}

		}
	}

}
