package com.amdonrun;

import com.badlogic.androidgames.framework.GameObject;

public class Ground extends GameObject {
	public static float GROUND_WIDTH = 8.2f;
	public static float GROUND_HEIGHT = 1.35f;

	public Ground(float x, float y) {
		super(x, y, GROUND_WIDTH, GROUND_HEIGHT);
	}
}
