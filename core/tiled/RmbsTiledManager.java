package com.rombus.evilbones.template.tiled;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxTilemap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Class for managing tiled map json exported maps.
 * This class works with RmbsLayerParser to delegate the parsing of the specific layers and objects. 
 *
 * @author Rombus
 *
 * 24/04/2016 00:31:43
 */
public class RfTiledManager {
	private static int tileSize;
	private static String objectsLayerName;
	private RfLayerParser layerParser;

	/**
	 * Parser de mapas Tiled exportados en JSON.
	 * Se configura una vez por proyecto. La idea es que cada proyecto use el mismo tilesize,
	 * spritesheet y objects layer name. El objectsLayerName es la capa que tiene los objetos
	 * a parsear y convertir en objetos java.
	 * Soporta una sola capa de objetos.
	 *
	 * @param tileSize
	 * @param spriteSheetPath
	 * @param objectsLayerName	El nombre de la capa de tiled donde están definidos los objetos.
	 * @param layerParser		La clase que implementa la interfaz RfLayerParser que se va a utilizar para parsear las capas de este mapa. Típicamente es una por proyecto.
	 */
	public RfTiledManager(int tileSize, String spriteSheetPath, String objectsLayerName, Class<? extends RfLayerParser> layerParser){
		RfTiledManager.objectsLayerName = objectsLayerName;
		RfTiledManager.tileSize = tileSize;

		// Strategy patter for parsing objects layer
		try {
			this.layerParser = layerParser.getConstructor(String.class, Integer.class).newInstance(spriteSheetPath, tileSize);
		} catch (ClassCastException 		| InstantiationException 	|
				 IllegalAccessException 	| IllegalArgumentException	|
				 InvocationTargetException 	| NoSuchMethodException 	|SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga el mapa que le pidas. Lo agrega al state para que se renderice.
	 *
	 * @param gameState		El gamestate donde se va a renderizar el mapa
	 * @param jsonMapPath	El export de tiled en json con el mapa
	 */
	public void loadMap(FlxState gameState, String jsonMapPath){
		HashMap<String, String> layers = new HashMap<String, String>();
		HashMap<String, Array<JsonValue>> objectsJson = new HashMap<String, Array<JsonValue>>();

		// Cargo las layers y objectsJson. También seteo la FlxG.camera para que tenga los bounds del mapa.
		parseMap(jsonMapPath, RfTiledManager.objectsLayerName, layers, objectsJson);

		// Delego el parseo de layers a algún RmbsLayerParser
		this.layerParser.parseLayers(gameState, layers, objectsJson);
	}

	/**
	 * Este método lee un mapa creado en TILED y exportado como JSON y copia todas las "layers" en el hashmap mapLayers.
	 * Después se puede acceder a cada layer por su nombre.
	 * También copia todos los objetos de la objects layer al array objectsJson.
	 * Esto permite acceder a todos los grupos de objetos.
	 * Si tenés 20 enemigos, estarán todos en el arreglo de JsonValue bajo la clave "enemigo" (o lo que sea).
	 *
	 * @param mapJson recurso formato JSON con el mapa exportado desde TILED.
	 * @param objectsLayer Nombre de la capa que tiene los objetos.
	 * @param mapLayers HashMap en el cual se guardarán todas las layers con key= nombre layer; value= layer data
	 * @param objectsJson HashMap en el cual se guardan todos los objetos. Si hay multiples objetos con el mismo tipo se agregan en el mismo key del hash pero en el siguiente elemento del value array.
	 *
	 * @author Rombus 02/06/2015
	 *
	 * TODO no usar hasmaps. usar sparse arrays o alguna otra estructura más eficiente en android.
	 */
	private void parseMap(String mapJsonPath, String objectsLayer, HashMap<String, String> mapLayers, HashMap<String, Array<JsonValue>> objectsJson){
		// Parseo el mapa json y cargo los layers en el HashMap.
		JsonValue value = new JsonReader().parse(Gdx.files.internal(mapJsonPath));
		JsonValue layer = value.get("layers").child(); // First layer
		int cantLayers = value.get("layers").size;

		// I asume all layers are the same width and all tiles are square
		int widthInTiles = layer.getInt("width");
		int heightInTiles = layer.getInt("height");

		// Setup the camera to the map's size
		FlxG.camera.setBounds(0, 0, widthInTiles * RfTiledManager.tileSize, heightInTiles * RfTiledManager.tileSize, true);


		// Inspecciono el json y me cargo las layers en el hashmap layerData, y los objetos en el array objects.
		for(int i=0; i<cantLayers; i++){
			String name = layer.getString("name");

			if(name.equals(objectsLayer)){	// Si es la capa OBJECTS_LAYER entonces agarro todos los objetos. ASUMO 1 SOLA CAPA DE OBJETOS
				JsonValue objTodos = layer.get("objects");
				JsonValue obj = objTodos.child();

				// Armo el HashMap con key(por ejemplo "batería") y el array de valores (por ejemplo 20 baterías) como objetos json.
				for(int j=0; j<objTodos.size; j++){
					String type = obj.getString("type");


					Array<JsonValue> objectData = null; // Este es el array que contiene los JsonValues de todos los objetos de un tipo particular, el tipo obj.getString("type");
					try {
						objectData = objectsJson.get(type); // Esto no tira nullpointer exception... le asigna null a objectData
						objectData.add(obj);				// esto sí tira null pointer exception ;)
					} catch (NullPointerException e) {
						objectData = new Array<JsonValue>();
						objectData.add(obj);
					}
					objectsJson.put(obj.getString("type"),objectData);


					// Avanzo en el json al siguiente objeto
					try {
						obj = obj.next();
					} catch (NullPointerException e) {	break; }
				}

			} else try {
				mapLayers.put(name, FlxTilemap.arrayToCSV(layer.get("data").asIntArray(), widthInTiles));
			} catch (NullPointerException e) { /* No tiene data */ }


			try {
				if(layer == null){
					break;
				}
                layer = layer.next();
			} catch (NullPointerException e) {}
		}
	}
}
