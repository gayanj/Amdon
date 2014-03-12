package com.amdonrun;

import com.badlogic.androidgames.framework.gl.Font;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLGame;

public class Assets {
	public static Texture textureMap;
	public static TextureRegion background;
	public static TextureRegion mainMenu;
	public static TextureRegion pauseMenu;
	public static TextureRegion play;
	public static TextureRegion pause;
	public static TextureRegion amdon;
	public static TextureRegion backArrow;
	public static TextureRegion highScoresRegion;
	public static TextureRegion groundRegion;
	public static Font font;

	public static void load(GLGame game) {
		textureMap = new Texture(game, "AmdonTextureMap.png");
		background = new TextureRegion(textureMap, 2, 2, 960, 640);
		mainMenu = new TextureRegion(textureMap, 2, 644, 518, 306);
		pauseMenu = new TextureRegion(textureMap, 522, 644, 346, 182);
		highScoresRegion = new TextureRegion(textureMap, 0, 858, 518, 90);
		groundRegion = new TextureRegion(textureMap, 2, 482, 960, 160);
		play = new TextureRegion(textureMap, 964, 2, 54, 90);
		pause = new TextureRegion(textureMap, 780, 828, 80, 90);
		amdon = new TextureRegion(textureMap, 870, 644, 94, 200);
		backArrow = new TextureRegion(textureMap, 964, 94, 44, 42);
		font = new Font(textureMap, 522, 828, 8, 256, 256);
	}

	public static void reload() {
		textureMap.reload();
		/*
		 * if(Settings.soundEnabled) music.play(); }
		 */
	}
}
