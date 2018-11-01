package com.weebly.jackthompsonjava.render3D;

import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class MouseHandler implements MouseListener, MouseMotionListener{

	private Handler handler;
	
	private int centerX;
	private int centerY;
	private int centerXOnScreen;
	private int centerYOnScreen;
	
	private int xMove;
	private int yMove;
	
	private Robot r;
	
	public MouseHandler(GraphicsDevice gd, Handler handler) {
		this.handler = handler;
		
		try {
			r = new Robot(gd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		centerX = handler.getProgram().getWidth() / 2;
		centerY = handler.getProgram().getHeight() / 2;
		
		centerXOnScreen = centerX + (int) handler.getDisplay().getJFrame().getLocationOnScreen().getX();
		centerYOnScreen = centerY + (int) handler.getDisplay().getJFrame().getLocationOnScreen().getX();
		
		handler.getDisplay().getJFrame().getContentPane().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor"));
		r.mouseMove(centerXOnScreen, centerYOnScreen);
	}

	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		xMove = e.getXOnScreen() - centerXOnScreen;
		yMove = -(e.getYOnScreen() - centerYOnScreen);
		
		
	//	System.out.println(xMove + " " + yMove);
	}

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public Point getPoint(MouseEvent e) {
		return e.getPoint();
	}
	
	public Point getMove() {
		r.mouseMove(centerXOnScreen, centerYOnScreen);
		return new Point(xMove, yMove);
	}
}
