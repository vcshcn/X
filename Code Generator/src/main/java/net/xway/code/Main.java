package net.xway.code;

import net.xway.code.ui.MainFrame;

public class Main {

	public static void main(String[] args) {
		if (args.length == 0) {
			startupUI();
		}
		else {
			startupCMD();
		}
	}
	
	
	public static void startupUI() {
		new MainFrame();
	}
	
	public static void startupCMD() {
		
	}
}
