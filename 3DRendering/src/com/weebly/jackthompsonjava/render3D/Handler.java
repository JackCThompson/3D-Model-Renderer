package com.weebly.jackthompsonjava.render3D;

public class Handler {

	private Program program;
	
	public Handler(Program program) {
		this.program = program;
	}
	
	public Program getProgram() {
		return program;
	}
	
	public Camera getCamera() {
		return program.getCamera();
	}
	
	public Display getDisplay() {
		return program.getDisplay();
	}
	
	public MouseHandler getMouseHandler() {
		return program.getMouseHandler();
	}
	
	public KeyHandler getKeyHandler() {
		return program.getKeyHandler();
	}
}
