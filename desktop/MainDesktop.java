package com.rombus.evilbones.template;

import com.rombus.evilbones.template.utils.Constants;
import com.rombus.evilbones.template.game.RmbsGame;

public class MainDesktop {
	public static void main(String[] args) {
		new RmbsDesktopApplication(new RmbsGame(), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, Constants.WINDOW_TITLE);
	}
}
