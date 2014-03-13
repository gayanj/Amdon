package com.amdonrun;

import javax.microedition.khronos.opengles.GL10;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLGraphics;

public class WorldRenderer {
	static final float FRUSTUM_WIDTH = 9.6f;
	static final float FRUSTUM_HEIGHT = 6.4f;
	GLGraphics glGraphics;
	World world;
	Camera2D cam;
	SpriteBatcher batcher;

	public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher,
			World world) {
		this.glGraphics = glGraphics;
		this.world = world;
		this.cam = new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.batcher = batcher;
	}

	public void render() {
		if (world.amdon.position.x > cam.position.x)
			cam.position.x = world.amdon.position.x;
		cam.setViewportAndMatrices();
		renderBackground();
		renderObjects();
	}

	public void renderBackground() {
		batcher.beginBatch(Assets.textureMap);
		batcher.drawSprite(cam.position.x, cam.position.y, FRUSTUM_WIDTH,
				FRUSTUM_HEIGHT, Assets.background);
		batcher.endBatch();
	}

	public void renderObjects() {
		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		batcher.beginBatch(Assets.textureMap);
		renderAmdon();
		renderGround();
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}

	private void renderAmdon() {
		TextureRegion amdonFrame;
		amdonFrame = Assets.amdon;
		if (world.amdon.velocity.y == 0) {
			 world.amdon.position.y = 2.6f;
		}
		batcher.drawSprite(world.amdon.position.x, world.amdon.position.y, 1,
				2, amdonFrame);
	}

	private void renderGround() {
		int len = world.grounds.size();
		for (int i = 0; i < len; i++) {
			Ground ground = world.grounds.get(i);
			if (world.amdon.position.x > world.groundX) {
				world.generateGround();
			}
			batcher.drawSprite(ground.position.x, ground.position.y, 9.6f,
					1.6f, Assets.groundRegion);
		}
	}
}
