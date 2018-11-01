package com.weebly.jackthompsonjava.render3D;

import java.awt.Color;
import java.awt.Graphics;

public class Point3D {

	private double x;
	private double y;
	private double z;

	private int perspectiveX;
	private int perspectiveY;
	private double distanceToCamera;

	private boolean visible;
	
	private Handler handler;
	
	public Point3D(double x, double y, double z, Handler handler) {
		this.handler = handler;
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void tick() {
		updatePerspectiveCoords();
	}
	
	public void render(Graphics g) {
		if (visible) {
			g.setColor(Color.white);
			g.fillRect(perspectiveX, perspectiveY, 4, 4);
		}
	}
	
	private void updatePerspectiveCoords() {
		double sinY = handler.getCamera().getSinAngleY();
		double cosY = handler.getCamera().getCosAngleY();
		double sinX = handler.getCamera().getSinAngleX();
		double cosX = handler.getCamera().getCosAngleX();
		
		//translate
		double translatedX = x - handler.getCamera().getX();
		double translatedY = y - handler.getCamera().getY();
		double translatedZ = z - handler.getCamera().getZ();
		//distanceToCamera = handler.getCamera().getDistanceTo(new double[] {translatedX, translatedY, translatedZ});
		distanceToCamera = Math.sqrt(Math.pow(translatedX, 2) + Math.pow(translatedY, 2) + Math.pow(translatedZ, 2));
		
		//rotate on Y-Axis
		double rotatedX1 = (translatedX * cosY) - (translatedZ * sinY);
		double rotatedZ1 = (translatedZ * cosY) + (translatedX * sinY);
		double rotatedY1 = translatedY;
		
		//rotate on X-axis
		double rotatedZ2 = (rotatedZ1 * cosX) - (rotatedY1 * sinX); //Why is this not used??
		double rotatedY2 = (rotatedY1 * cosX) + (rotatedZ1 * sinX);
		
		visible = true;
		if (rotatedZ2 < 0) {
			visible = false;
		}
		perspectiveX = (int) ((0.5 + rotatedX1 / (handler.getCamera().getFovX() * distanceToCamera * 0.5)) * handler.getProgram().getWidth());
		perspectiveY = (int) ((0.5 + rotatedY2 / (handler.getCamera().getFovY() * distanceToCamera * 0.5)) * handler.getProgram().getHeight());
		
		perspectiveY = handler.getProgram().getHeight() - perspectiveY;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
	
	public int getPerspectiveX() {
		return perspectiveX;
	}
	
	public int getPerspectiveY() {
		return perspectiveY;
	}
	
	public double getDistanceToCamera() {
		return distanceToCamera;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
}
