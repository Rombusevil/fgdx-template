package com.rombus.evilbones.template.tiled;

import java.util.HashMap;
import org.flixel.FlxState;
import org.flixel.FlxTilemap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by Rombus on 02/04/16.
 */
public abstract class RmbsLayerParser {
	protected static String spriteSheet;
	protected static int tileSize;         // Asume tiles are squares

	public RmbsLayerParser(String spriteSheet, Integer tileSize){
		RmbsLayerParser.spriteSheet = spriteSheet;
		RmbsLayerParser.tileSize = tileSize;
	}

	protected FlxTilemap handleLayer(HashMap<String, String> layers, String layer, FlxState gameState){
		FlxTilemap tm = new FlxTilemap();
		tm.loadMap(layers.get(layer), RmbsLayerParser.spriteSheet, RmbsLayerParser.tileSize, RmbsLayerParser.tileSize, 0,1);
		gameState.add(tm);
		return tm;
	}

	public abstract void parseLayers(FlxState gameState, HashMap<String, String> layers, HashMap<String, Array<JsonValue>> objects);
}
