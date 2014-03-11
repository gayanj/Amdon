package com.amdonrun;

import com.badlogic.androidgames.framework.DynamicGameObject;

public class AmdonHimself extends DynamicGameObject {
	public static final int AMDON_STATE_JUMP = 0;
	public static final int AMDON_STATE_FALL = 1;
	public static final int AMDON_STATE_HIT = 2;
	public static final float AMDON_JUMP_VELOCITY = 11;
	public static final float AMDON_MOVE_VELOCITY = 20;
	public static final float AMDON_WIDTH = 0.94f;
	public static final float AMDON_HEIGHT = 2f;

	int state;
	float stateTime;

	public AmdonHimself(float x, float y) {
		super(x, y, AMDON_WIDTH, AMDON_HEIGHT);
		state = AMDON_STATE_FALL;
		stateTime = 0;
	}
}
