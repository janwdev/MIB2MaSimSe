package app;

import com.formdev.flatlaf.FlatDarculaLaf;

import gui.MainGUI;
import maths.VektorMatrixBerechnung;
import objectClasses.Matrix;
import objectClasses.Vector;

public class Main {

	public static void main(String[] args) {
		
		FlatDarculaLaf.install();
		
		Control control = new Control();
		MainGUI gui = new MainGUI(control);
		control.setGUI(gui);
	}

}
