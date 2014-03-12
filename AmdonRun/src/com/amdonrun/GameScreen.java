package com.amdonrun;

import java.util.List;
import javax.microedition.khronos.opengles.GL10;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;
import com.amdonrun.World.WorldListener;

public class GameScreen extends GLScreen {
	static final int GAME_RUNNING = 0;
	static final int GAME_PAUSED = 1;

	int state;
	Camera2D guiCam;
	Vector2 touchPoint;
	SpriteBatcher batcher;
	World world;
	WorldListener worldListener;
	WorldRenderer renderer;
	Rectangle pauseBounds;
	Rectangle playBounds;
	Rectangle menuBounds;
	Rectangle scoreBounds;
	boolean touched = false;
	// Rectangle quitBounds;
	int lastScore;
	String scoreString;

	public GameScreen(Game game) {
		super(game);
		state = GAME_RUNNING;
		guiCam = new Camera2D(glGraphics, 960, 640);
		touchPoint = new Vector2();
		batcher = new SpriteBatcher(glGraphics, 1000);
		worldListener = new WorldListener() {

			/*
			 * @Override public void jump() { // Play the appropriate sound :) -
			 * to be implemented }
			 */

		};
		world = new World(worldListener);
		renderer = new WorldRenderer(glGraphics, batcher, world);
		pauseBounds = new Rectangle(960 - 80, 640 - 90, 80, 90);
		playBounds = new Rectangle(960 - 54, 640 - 90, 54, 90);
		menuBounds = new Rectangle(480 - 173, 200, 346, 90);
		scoreBounds = new Rectangle(480 - 173, 200 - 90, 346, 90);
		// quitBounds = new Rectangle(160 - 96, 240 - 36, 192, 36);
		lastScore = 0;
		scoreString = "score: 0";
	}

	@Override
	public void update(float deltaTime) {
		if (deltaTime > 0.1f)
			deltaTime = 0.1f;
		switch (state) {
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		}
	}

	private void updateRunning(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type != TouchEvent.TOUCH_UP)
				continue;
			//jump event
			touched = true;
			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);
			if (OverlapTester.pointInRectangle(pauseBounds, touchPoint)) {
				// play sound
				state = GAME_PAUSED;
				touched = false;
				return;
			} 
		}
		world.update(deltaTime, touched);
		touched = false;
		if (world.score != lastScore) {
			lastScore = world.score;
			scoreString = "" + lastScore;
		}
	}

	private void updatePaused() {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type != TouchEvent.TOUCH_UP)
				continue;
			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);
			if (OverlapTester.pointInRectangle(playBounds, touchPoint)) {
				// play sound
				state = GAME_RUNNING;
				return;
			}
			if (OverlapTester.pointInRectangle(menuBounds, touchPoint)) {
				// play sound
				game.setScreen(new MainMenuScreen(game));
				return;
			}
			if (OverlapTester.pointInRectangle(scoreBounds, touchPoint)) {
				// play sound
				game.setScreen(new HighscoresScreen(game));
				return;
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		renderer.render();
		guiCam.setViewportAndMatrices();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		batcher.beginBatch(Assets.textureMap);
		switch (state) {
		case GAME_RUNNING:
			presentRunning();
			break;
		case GAME_PAUSED:
			presentPaused();
			break;
		/*
		 * case GAME_OVER: presentGameOver(); break;
		 */
		}
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}

	private void presentRunning() {
		batcher.drawSprite(960 - 40, 640 - 45, 80, 90, Assets.pause);
		// Assets.font.drawText(batcher, scoreString, 16, 640 - 20);
	}

	private void presentPaused() {
		batcher.drawSprite(480, 200, 346, 182, Assets.pauseMenu);
		batcher.drawSprite(960 - 40, 640 - 45, 54, 90, Assets.play);
		// Assets.font.drawText(batcher, scoreString, 16, 640 - 20);
	}

	@Override
	public void pause() {
		if (state == GAME_RUNNING)
			state = GAME_PAUSED;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
