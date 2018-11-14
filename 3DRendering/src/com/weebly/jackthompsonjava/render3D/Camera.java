package com.weebly.jackthompsonjava.render3D;

import java.awt.Point;

public class Camera {

	private double x;
	private double y;
	private double z;
	
	private double angleX;
	private double angleY;

	private double sinAngleY;
	private double sinAngleX;
	private double cosAngleY;
	private double cosAngleX;
	
	private double fovX;
	private double fovY;
	
	private Handler handler;
	
	private double speed;
	
	public Camera(double x, double y, double z, Handler handler) {
		this.handler = handler;
		
		this.x = x;
		this.y = y;
		this.z = z;

		setAngleX(0);
		setAngleY(0);
		
		setFovX(1.22);
		
		speed = 0.4;
		
	}
	
	public void tick() {
		Point p = handler.getMouseHandler().getMove();
		
		setAngleX(getAngleX() - p.getY() * 0.0005);
		setAngleY(getAngleY() + p.getX() * 0.0005);
		doTrig();
		
		if (handler.getKeyHandler().w) {
			z += speed * Math.cos(angleY);
			x += speed * Math.sin(angleY);
		} else if (handler.getKeyHandler().a) {
			z += speed * Math.cos(angleY - 1.570796);
			x += speed * Math.sin(angleY - 1.570796);
		} else if (handler.getKeyHandler().s) {
			z += speed * -Math.cos(angleY);
			x += speed * -Math.sin(angleY);
		} else if (handler.getKeyHandler().d) {
			z += speed * Math.cos(angleY + 1.570796);
			x += speed * Math.sin(angleY + 1.570796);
		} else if (handler.getKeyHandler().space) {
			y += speed;
		} else if (handler.getKeyHandler().shift) {
			y -= speed;
		}
	}
	
	public void doTrig() {
		sinAngleY = Math.sin(angleY);
		sinAngleX = Math.sin(angleX);
		cosAngleY = Math.cos(angleY);
		cosAngleX = Math.cos(angleX);
	}
	
	public void setFovX(double fov) {
		this.fovX = fov;
		updateFovY();
	}
	
	private void updateFovY() {
		fovY = (2 * Math.atan(((double) handler.getProgram().getHeight() / handler.getProgram().getWidth()) * Math.tan(0.5 * fovX)));
	}
	
	//public double getDistanceTo(double[] coords) {
	//	return Math.sqrt(Math.pow(x - coords[0], 2) + Math.pow(y - coords[1], 2) + Math.pow(z - coords[2], 2));
	//}
	
	private void setAngleX(double angle) {
		angleX = angle;
		
		if(angleX > 1.570796326) {
			angleX = 1.570796326;
		}
		if(angleX < -1.570796326) {
			angleX = -1.570796326;
		}
		
	}
	
	private void setAngleY(double angle) {
		angleY = angle;
		
		if(angleY > 3.14) {
			angleY -= 6.28;
		}
		if(angleY < -3.14) {
			angleY += 6.28;
		}
		
	}
	
	public double getFovX() {
		return fovX;
	}
	
	public double getFovY() {
		return fovY;
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
	
	public double getAngleX() {
		return angleX;
	}
	
	public double getAngleY() {
		return angleY;
	}

	public double getSinAngleY() {
		return sinAngleY;
	}

	public double getSinAngleX() {
		return sinAngleX;
	}

	public double getCosAngleY() {
		return cosAngleY;
	}

	public double getCosAngleX() {
		return cosAngleX;
	}
	
	
}
