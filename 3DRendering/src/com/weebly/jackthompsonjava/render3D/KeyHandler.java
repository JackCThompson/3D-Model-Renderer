package com.weebly.jackthompsonjava.render3D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	Handler handler;
	
	boolean[] keys;

	public boolean w;
	public boolean a;
	public boolean s;
	public boolean d;
	
	public boolean shift;
	public boolean space;
	
	public KeyHandler(Handler handler) {
		this.handler = handler;
		
		keys = new boolean[500];
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		//handler.getProgram().keyPressed(e);		
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		//handler.getProgram().keyReleased(e);		
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void tick() {
		w = keys[KeyEvent.VK_W];
		a = keys[KeyEvent.VK_A];
		s = keys[KeyEvent.VK_S];
		d = keys[KeyEvent.VK_D];

		space = keys[KeyEvent.VK_SPACE];
		shift = keys[KeyEvent.VK_SHIFT];
	}

}
