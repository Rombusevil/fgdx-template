package com.rombus.evilbones.template.utils;

import org.flixel.FlxCamera;
import org.flixel.FlxState;
import com.rombus.evilbones.template.game.RmbsSplash;

public class Constants {
	public static final boolean DEBUG = false;

	// FlxGame configurations
	public static final int WIDTH_RESOLUTION = 800;
	public static final int HEIGHT_RESOLUTION = 640;
	public static final Class<? extends FlxState> INIT_STATE = RmbsSplash.class;
	public static final int TWEEN_ENGINE_WAYPOINTS = 1;
	public static final boolean PC_BUILD = false;
	public static final boolean USE_SYSTEM_CURSOR = true;
	public static final int SCALE_MODE = FlxCamera.FILL;// FlxCamera.NO_SCALE | FlxCamera.FIT | FlxCamera.STRETCH

	// Desktop configurations
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final String WINDOW_TITLE = "Rmbs Template";


	// Tiled configurations
    public static final int TILE_SIZE = 16;
    public static final String OBJECTS_LAYER_NAME = "objects";
}
