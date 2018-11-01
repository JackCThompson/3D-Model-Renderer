# 3D-Model-Renderer

This program loads point, polygon, and color data from a text file and renders a model in 3D perspective using 2D rendering methods.

Controls:
  Use the mouse to rotate the camera
  W, A, S, and D keys to move forward, left, backward, and right repectively
  Space and shift keys to move up and down respectively
  Escape key to close the program
  
Instructions for creating custom models and loading them into the program can be found in the README files in /3DRendering/res/models/ and /3DRendering/src/com/weebly/jackthompsonjava/render3D/
  
WARNING: The program takes control of the mouse even if you minimize the window. Ending the program with the escape key is the only way to regain control

WARNING: There is a bug with the Java.awt.Robot class so the mouse controls glitch out on high resolution monitors. For now, this program should work on an HD screen but not a 4k screen. the bug is described here https://bugs.openjdk.java.net/browse/JDK-8190898
