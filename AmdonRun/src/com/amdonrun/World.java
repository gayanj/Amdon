package com.amdonrun;

import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Vector2;

public class World{
	public interface WorldListener {
		//public void jump();
	}

	public static final float WORLD_WIDTH = 9.6f;
	public static final float WORLD_HEIGHT = 6.4f;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_GAME_OVER = 1;
	public static final Vector2 gravity = new Vector2(0, -12);

	public final AmdonHimself amdon;
	public final Ground ground;
	public final WorldListener listener;
	public float distanceSoFar;
	public int score;
	public int state;

	public World(WorldListener listener) {
		this.amdon = new AmdonHimself(1, 4f);
		this.ground = new Ground(4.8f, 0.8f);
		this.listener = listener;
		this.distanceSoFar = 0;
		this.score = 0;
		this.state = WORLD_STATE_RUNNING;
	}

	public void update(float deltaTime, boolean isTouched){
		updateAmdon(deltaTime,isTouched);
		checkCollisions(isTouched);
		//checkGameOver();
	}

	private void updateAmdon(float deltaTime,boolean isTouched) {
		amdon.update(deltaTime,isTouched);
		distanceSoFar = Math.max(amdon.position.y, distanceSoFar);
	}

	private void checkCollisions(boolean isTouched) {
		checkGroundCollisions(isTouched);
	}

	private void checkGroundCollisions(boolean isTouched) {
		if (OverlapTester.overlapRectangles(amdon.bounds, ground.bounds)) {
			amdon.hitGround(isTouched);
		}else{
			amdon.nonGrounded();
		}
	}
}
