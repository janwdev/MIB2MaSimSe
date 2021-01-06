package app;

import com.formdev.flatlaf.FlatDarculaLaf;
import gui.MainGUI;

public class Main {
	public static void main(String[] args) {

		FlatDarculaLaf.install();
		//FlatLightLaf.install();

		Control control = new Control();
		MainGUI gui = new MainGUI(control);
		control.setGUI(gui); // Noetig damit auf GUI zugegriffen werden kann
	}

}