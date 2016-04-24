package com.rombus.evilbones.template.game;

import org.flixel.FlxGame;
import org.flixel.FlxState;
import flash.events.Event;

/**
 * This class allows the PC version not to pause when not in focus.
 * It has to be compiled with the "allowMobile" flag with false value.
 *
 * @author Rombus
 *
 * 10/04/2016 02:26:35
 */
public class RmbsGame extends FlxGame {
    private boolean allowMobile;

	public RmbsGame(boolean allowMobile, int GameSizeX, int GameSizeY, Class<? extends FlxState> InitialState, float Zoom, int GameFramerate, int FlashFramerate, boolean UseSystemCursor, int ScaleMode) {
		super(GameSizeX, GameSizeY, InitialState, Zoom, GameFramerate, FlashFramerate, UseSystemCursor, ScaleMode);
		this.allowMobile = allowMobile;
	}

	@Override
	protected void onFocusLost(Event FlashEvent) {
		super.onFocus(FlashEvent);

		if(!this.allowMobile){
		    _lostFocus = false;
		}
	}
}
