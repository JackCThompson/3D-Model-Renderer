
package com.weebly.jackthompsonjava.render3D;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

public class Model3D {

	private Point3D[] points;
	private ArrayList<Polygon3D> polygons;
	private Comparator<Polygon3D> polygonSorter;
	
	public Model3D(String file, Handler handler) {
		polygonSorter = new Comparator<Polygon3D>() {
			public int compare(Polygon3D a, Polygon3D b) {
				
				if (a.getDistanceToCameraNear() < b.getDistanceToCameraNear()) {
					return -1;
				} else if (a.getDistanceToCameraNear() == b.getDistanceToCameraNear()) {
					if (a.getDistanceToCameraFar() < b.getDistanceToCameraFar()) {
						return -1;
					}
				}
				return 1;
			}
				
		};
		
		String modelText = Utils.loadFileAsString(file);
		String[] sections = modelText.split(";");
		String[] pointText = sections[0].split(",");
		String[] polygonText = sections[1].split(",");
		
		points = new Point3D[pointText.length];
		for (int i = 0; i < points.length; i ++) {
			String[] coordinateText = pointText[i].split(" ");
			points[i] = new Point3D(Utils.parseInt(coordinateText[0]), Utils.parseInt(coordinateText[1]), Utils.parseInt(coordinateText[2]), handler);
		}
		
		polygons = new ArrayList<Polygon3D>();
		for (int i = 0; i < polygonText.length; i ++) {
			String[] polygonData = polygonText[i].split(" ");
			Point3D[] pointList = new Point3D[polygonData.length - 3];
			
			for (int j = 0; j < pointList.length; j ++) {
				pointList[j] = points[Utils.parseInt(polygonData[j + 3])]; 
			}
			
			Color color = new Color(Utils.parseInt(polygonData[0]), Utils.parseInt(polygonData[1]), Utils.parseInt(polygonData[2]));
			
			polygons.add(new Polygon3D(pointList, color));
		}
	}
	
	public void tick() {
		for (Point3D p : points) {
			p.tick();
		}
		
		for (Polygon3D p : polygons) {
			p.tick();
		}
		
		polygons.sort(polygonSorter);
	}
	
	public void render(Graphics g) {

		for (int i = polygons.size() - 1; i >= 0; i--) {
			polygons.get(i).render(g);
		}
	}
}
