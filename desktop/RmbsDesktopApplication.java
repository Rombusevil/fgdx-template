package com.rombus.evilbones.template;

import org.flixel.FlxGame;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * LwjglApplication wrapper for configuring desktop's icon, title and size.
 *
 * @author Rombus
 *
 * 03/04/2016 17:52:18
 */
public class RmbsDesktopApplication { //extends LwjglApplication{
	public RmbsDesktopApplication (FlxGame Game, int width, int height, String title){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.addIcon("icon/ricon16.png", FileType.Internal);
		config.addIcon("icon/ricon32.png", FileType.Internal);
		config.addIcon("icon/ricon128.png", FileType.Internal);
		config.height = height;
		config.width = width;
		config.title = title;

		new LwjglApplication((ApplicationListener)Game.stage, config);
	}
}
