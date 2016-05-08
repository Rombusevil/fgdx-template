package com.rombus.evilbones.template;

import org.flixel.FlxAndroidApplication;

import com.rombus.evilbones.template.game.RmbsGame;

public class MainActivity extends FlxAndroidApplication {
    public MainActivity(){
		super(new RmbsGame());
	}
}
