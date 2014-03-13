package com.amdonrun;

import com.badlogic.androidgames.framework.DynamicGameObject;

public class AmdonHimself extends DynamicGameObject {
	public static final int AMDON_STATE_JUMP = 0;
	public static final int AMDON_STATE_FALL = 1;
	public static final int AMDON_STATE_GROUNDED = 2;
	public static final float AMDON_JUMP_VELOCITY = 5;
	public static final float AMDON_MOVE_VELOCITY = 3;
	public static final float AMDON_WIDTH = 0.94f;
	public static final float AMDON_HEIGHT = 2f;

	int state;
	float stateTime;

	public AmdonHimself(float x, float y) {
		super(x, y, AMDON_WIDTH, AMDON_HEIGHT);
		state = AMDON_STATE_FALL;
		this.velocity.x = AMDON_MOVE_VELOCITY;
		this.velocity.y = 0.1f;
		stateTime = 0;
	}

	public void update(float deltaTime, boolean isTouched) {
		if (isTouched && state == AMDON_STATE_GROUNDED) {
			jump();
		}
		if(state == AMDON_STATE_GROUNDED){
			velocity.y = 0;
		}
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);

		velocity.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);

		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
		if (velocity.y > 0 && state != AMDON_STATE_GROUNDED) {
			if (state != AMDON_STATE_JUMP) {
				state = AMDON_STATE_JUMP;
				stateTime = 0;
			}
		}

		if (velocity.y < 0 && state != AMDON_STATE_GROUNDED) {
			if (state != AMDON_STATE_FALL) {
				state = AMDON_STATE_FALL;
				stateTime = 0;
			}
		}
	}

	public void hitGround(boolean isTouched) {
		if (!isTouched)
			velocity.y = 0;
		state = AMDON_STATE_GROUNDED;
		stateTime = 0;
	}

	public void jump() {
		velocity.y = AMDON_JUMP_VELOCITY;
		state = AMDON_STATE_JUMP;
		stateTime = 0;
	}

	public void nonGrounded() {
		state = AMDON_STATE_FALL;
	}
}