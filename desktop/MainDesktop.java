package com.rombus.evilbones.template;

public class MainDesktop {
	public static void main(String[] args) {
		int winWidth = 512;
        int winHeight = 512;
        String title = "Rombosaur Game";

		new RmbsDesktopApplication(new Main(), winWidth, winHeight, title);
	}
}
