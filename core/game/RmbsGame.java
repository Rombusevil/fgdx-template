package com.rombus.evilbones.template.game;

import org.flixel.FlxG;
import org.flixel.FlxGame;
import flash.events.Event;
import com.rombus.evilbones.template.utils.Constants;
import aurelienribon.tweenengine.Tween;
import org.flixel.plugin.tweens.TweenPlugin;

/**
 * This class allows the PC version not to pause when not in focus.
 * It has to be compiled with the "allowMobile" flag with false value.
 * It also configures TweenEngine and leaves it ready for use.
 *
 * Use the Constants class for game specific configurations.
 *
 * @author Rombus
 *
 * 10/04/2016 02:26:35
 */
public class RmbsGame extends FlxGame {
    private static final float ZOOM = 1;
    private static final int FPS = 60;
    private final boolean pcBuild;

	public RmbsGame() {
		super(Constants.WIDTH_RESOLUTION, Constants.HEIGHT_RESOLUTION, Constants.INIT_STATE, ZOOM, FPS, FPS, Constants.USE_SYSTEM_CURSOR, Constants.SCALE_MODE);

		this.pcBuild = Constants.PC_BUILD;

        Tween.setWaypointsLimit(Constants.TWEEN_ENGINE_WAYPOINTS);
        FlxG.addPlugin(new TweenPlugin());
	}

	@Override
	protected void onFocusLost(Event FlashEvent) {
		super.onFocus(FlashEvent);

		if(this.pcBuild) {
		    _lostFocus = false;
		}
	}
}
