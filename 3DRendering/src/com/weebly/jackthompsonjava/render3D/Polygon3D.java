package com.weebly.jackthompsonjava.render3D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Polygon3D {

	private Color color;
	private Point3D[] points;
	private Polygon polygon;
	
	private boolean visible;

	private double distanceToCameraFar;
	private double distanceToCameraNear;
	
	public Polygon3D(Point3D[] points, Color color) {
		this.points = points;
		this.color = color;
	}
	
	public void tick() {
		createPolygonInPerspective();
		updateVisibility();
		updateDistanceToCamera();
	}
	
	public void render(Graphics g) {
		if (visible) {
			g.setColor(color);
			g.fillPolygon(polygon);
		}
	}
	
	public void createPolygonInPerspective() {
		polygon = new Polygon();
		for (Point3D p : points) {
			polygon.addPoint(p.getPerspectiveX(), p.getPerspectiveY());
		}
	}
	
	public void updateVisibility() {
		visible = false;
		for (Point3D p : points) {
			if (p.isVisible()) {
				visible = true;
			}
		}
		if (!getFacingCamera()) {
			visible = false;
		}
		if (getBehindCamera()) {
			visible = false;
		}
	}
	
	public boolean getBehindCamera() {
		if (distanceToCameraNear < 0 && distanceToCameraFar < 0) {
			return true;
		}
		return false;
	}
	
	public boolean getFacingCamera() {
		double twiceArea = 0;
		for (int i = 0; i < points.length; i++) {
			if (i == points.length - 1) {
				twiceArea += (points[i].getPerspectiveX() + points[0].getPerspectiveX()) * (points[i].getPerspectiveY() - points[0].getPerspectiveY());
			} else {
				twiceArea += (points[i].getPerspectiveX() + points[i + 1].getPerspectiveX()) * (points[i].getPerspectiveY() - points[i + 1].getPerspectiveY());
			}
		}
		return twiceArea < 0;
	}
	
	public void updateDistanceToCamera() {
		distanceToCameraFar = 0;
		for (Point3D p : points) {
			distanceToCameraFar = Math.max(distanceToCameraFar, p.getDistanceToCamera());
		}
		
		distanceToCameraNear = distanceToCameraFar;
		for (Point3D p : points) {
			distanceToCameraNear = Math.min(distanceToCameraNear, p.getDistanceToCamera());
		}
	}
	
	public double getDistanceToCameraFar() {
		return distanceToCameraFar;
	}
	
	public double getDistanceToCameraNear() {
		return distanceToCameraNear;
	}
}
