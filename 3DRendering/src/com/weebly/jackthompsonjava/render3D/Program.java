package com.weebly.jackthompsonjava.render3D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Program implements Runnable{

	private int width;
	private int height;
	
	private boolean running;
	
	private Display display;
	private BufferStrategy bs;
	private BufferedImage scene3D;
	private Graphics2D g;
	
	private Handler handler;
	private MouseHandler mouseHandler;
	private KeyHandler keyHandler;
	
	private Camera camera;
	
	
	private ArrayList<Model3D> models;
	
	public Program(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void start() {
		running = true;
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		
		long timer = 0;
		int ticks = 0;
		int frames = 0;
		long now;
		
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1) {
				tick();
				ticks ++;
				
				if (delta <= 2) {
					render();
					frames ++;
				}
				
				delta --;
			}
			if (timer >= 1000000000) {
				System.out.println("FPS: " + frames + ", TPS: " + ticks);
				
				timer = 0;
				ticks = 0;
				frames = 0;
			}
		}
	}
	
	private void init() {
		System.out.println("initializing display");
		display = new Display(width, height);
				
		System.out.println("initializing handler");
		handler = new Handler(this);

		this.camera = new Camera(0, 0, 0, handler);

		this.mouseHandler = new MouseHandler(display.getJFrame().getGraphicsConfiguration().getDevice(), handler);
		display.getJFrame().addMouseListener(mouseHandler);
		display.getJFrame().addMouseMotionListener(mouseHandler);
		display.getCanvas().addMouseListener(mouseHandler);
		display.getCanvas().addMouseMotionListener(mouseHandler);
		
		this.keyHandler = new KeyHandler(handler);
		display.getJFrame().addKeyListener(keyHandler);
		
		models = new ArrayList<Model3D>();
		models.add(new Model3D("res/models/cube.txt", handler));
	}

	private void tick() {
		camera.tick();
		keyHandler.tick();
		for (Model3D m : models) {
			m.tick();
		}
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = (Graphics2D) bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		//draw here
		for (Model3D m : models) {
			m.render(g);
		}
		
		bs.show();
		g.dispose();
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public MouseHandler getMouseHandler() {
		return mouseHandler;
	}
	
	public KeyHandler getKeyHandler() {
		return keyHandler;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
