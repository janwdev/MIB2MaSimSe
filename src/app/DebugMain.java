package app;

import com.formdev.flatlaf.FlatDarculaLaf;

import gui.MainGUI;
import maths.VektorMatrixBerechnung;
import objectClasses.Matrix;
import objectClasses.Vector;

public class DebugMain {
	public static void main(String[] args) {

		FlatDarculaLaf.install();

		Control control = new Control();
		MainGUI gui = new MainGUI(control);
		control.setGUI(gui);
		
		// Vector malen
		Vector v = new Vector(Math.PI, Math.PI);
		gui.drawVector(v);
		// gemalte Vektoren loeschen, nur Drahtgitter bleibt
		// gui.clearDrawedVectors();

		// Test Matrix Vektor Multiplikation
//		try {
//			Matrix matrix = new Matrix(2, 3);
//			matrix.init(new int[] { 4, 3, 2, 1, 2, 3 });
//			Vector vector = new Vector(2, 3, 4);
//			Vector returnVec = new VektorMatrixBerechnung().multiply(vector, matrix);
//			System.out.println("x: " + returnVec.getVectorX() + ", y: " + returnVec.getVectorY() + ", z: "
//					+ returnVec.getVectorZ());
//			//Erwartetes Ergebnis; x: 25.0, y: 20.0, z: 0.0
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			Matrix matrix = new Matrix(3, 3);
//			matrix.init(new int[] { 2, 5, 7, 8, 9, 10, 1, 2, 3 });
//			Vector vector = new Vector(2, 4, 5);
//			Vector returnVec = new VektorMatrixBerechnung().multiply(vector, matrix);
//			System.out.println("x: " + returnVec.getVectorX() + ", y: " + returnVec.getVectorY() + ", z: "
//					+ returnVec.getVectorZ());
//			// Erwartetes Ergebnis; x: 59.0, y: 102.0, z: 25.0
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
