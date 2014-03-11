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
	public static Font font;

	public static void load(GLGame game) {
		textureMap = new Texture(game, "AmdonTextureMap.png");
		background = new TextureRegion(textureMap, 2, 2, 960, 640);
		mainMenu = new TextureRegion(textureMap, 186, 644, 518, 306);
		pauseMenu = new TextureRegion(textureMap, 2, 644, 346, 182);
		play = new TextureRegion(textureMap, 964, 2, 54, 90);
		pause = new TextureRegion(textureMap, 908, 902, 80, 90);
		amdon = new TextureRegion(textureMap, 706, 902, 94, 200);
		font = new Font(textureMap, 706, 644, 8, 256, 256);
	}

	public static void reload() {
		textureMap.reload();
		/*
		 * if(Settings.soundEnabled) music.play(); }
		 */
	}
}
