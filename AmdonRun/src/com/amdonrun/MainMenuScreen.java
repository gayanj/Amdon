package com.amdonrun;

import java.util.List;
import javax.microedition.khronos.opengles.GL10;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;

public class MainMenuScreen extends GLScreen {
	Camera2D guiCam;
	SpriteBatcher batcher;
	Rectangle start;
	Rectangle score;
	Vector2 touchPoint;

	public MainMenuScreen(Game game) {
		super(game);
		batcher = new SpriteBatcher(glGraphics, 100);
		guiCam = new Camera2D(glGraphics, 960, 640);
		start = new Rectangle(480 - 274, 100, 518, 306);
		score = new Rectangle(480 - 274, 100 + 20, 518, 306);
		touchPoint = new Vector2();
	}

	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				if (OverlapTester.pointInRectangle(start, touchPoint)) {
					game.setScreen(new GameScreen(game));
					return;
				}
				if (OverlapTester.pointInRectangle(score, touchPoint)) {
					game.setScreen(new HighscoresScreen(game));
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(Assets.textureMap);
		batcher.drawSprite(0, 0, 960, 640, Assets.background);
		batcher.drawSprite(480, 320 , 518, 306, Assets.mainMenu);

		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public void pause() {
		Settings.save(game.getFileIO());
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
