package com.amdonrun;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Vector2;

public class World {
	public interface WorldListener {
		// public void jump();
	}

	public static final float WORLD_WIDTH = 9.6f;
	public static final float WORLD_HEIGHT = 6.4f;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_GAME_OVER = 1;
	public static final Vector2 gravity = new Vector2(0, -13);

	public final AmdonHimself amdon;
	public final List<Ground> grounds;
	public final WorldListener listener;
	public float distanceSoFar;
	public int score;
	public int state;
	public float groundX = 4.8f;
	public float groundY = 0.8f;

	public World(WorldListener listener) {
		this.amdon = new AmdonHimself(1, 4f);
		this.grounds = new ArrayList<Ground>();
		Ground ground = new Ground(groundX, groundY);
		this.grounds.add(ground);
		this.generateGround();
		this.listener = listener;
		this.distanceSoFar = 0;
		this.score = 0;
		this.state = WORLD_STATE_RUNNING;
	}

	public void generateGround() {
		groundX += 9.6f;
		Ground ground = new Ground(groundX, groundY);
		this.grounds.add(ground);
	}

	public void update(float deltaTime, boolean isTouched) {
		updateAmdon(deltaTime, isTouched);
		updateGrounds();
		checkCollisions(isTouched);
		// checkGameOver();
	}

	private void updateGrounds() {
		int len = grounds.size();
		if (len > 1) {
			if (amdon.position.x > groundX) {
				grounds.remove(0);
				len = grounds.size();
			}
		}
	}

	private void updateAmdon(float deltaTime, boolean isTouched) {
		amdon.update(deltaTime, isTouched);
		distanceSoFar = Math.max(amdon.position.y, distanceSoFar);
	}

	private void checkCollisions(boolean isTouched) {
		checkGroundCollisions(isTouched);
	}

	private void checkGroundCollisions(boolean isTouched) {
		int len = grounds.size();
		Ground ground = grounds.get(0);
		for (int i = 0; i < len; i++) {
			ground = grounds.get(i);
			if (OverlapTester.overlapRectangles(ground.bounds, amdon.bounds)) {
				break;
			}
		}
		if (OverlapTester.overlapRectangles(ground.bounds, amdon.bounds)) {
			amdon.hitGround(isTouched);
		} else {
			amdon.nonGrounded();
		}
	}
}
