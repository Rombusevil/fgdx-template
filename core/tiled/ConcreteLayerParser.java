package com.rombus.evilbones.template.tiled;

import java.util.HashMap;
import org.flixel.FlxState;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Modify this class with game specific tiled layer parsing.
 *
 * @author Rombus
 *
 * 24/04/2016 12:38:49
 */
public class ConcreteLayerParser extends RmbsLayerParser {
	public ConcreteLayerParser(String spriteSheet, Integer tileSize) {
		super(spriteSheet, tileSize);
	}

	@Override
	public void parseLayers(FlxState gameState, HashMap<String, String> layers, HashMap<String, Array<JsonValue>> objects) {
		/*
		FlxTilemap background1, background2, collidable;

		background1 = handleLayer(layers, "background-1", gameState);
		background2 = handleLayer(layers, "background-2", gameState);
		collidable  = handleLayer(layers, "collidable", gameState);

		parseObjects(gameState, objects);
		*/
	}
}
