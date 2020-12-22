package app;

import java.util.ArrayList;

import com.formdev.flatlaf.FlatDarculaLaf;

import gui.MainGUI;
import maths.Maths;
import maths.ProjektionsMatrix;
import maths.VektorMatrixBerechnung;
import objectClasses.Matrix;
import objectClasses.Vector;

public class DebugMain {
	static Maths ma = new Maths();

	public static void main(String[] args) {

		FlatDarculaLaf.install();

		Control control = new Control();
		MainGUI gui = new MainGUI(control);
		control.setGUI(gui); // Noetig damit auf GUI zugegriffen werden kann

		// Vector malen
		// Vector v = new Vector(2,2,2);
		// gui.drawVector(v);
		// gemalte Vektoren loeschen, nur Drahtgitter bleibt
		// gui.clearDrawedVectors();

		// !! Winkel radiant oder Grad

		// Deklaration

		// Vector mit Winkel
		Vector p = new Vector(0, 0);
		// p.setWinkel(0, 3.2);
		// Vector q = new Vector(Math.PI/3, 0); // minus geht nicht und über 90 grad
		Vector q = new Vector(2 * Math.PI * 3 / 4, 0);
		// Zeichnen der Vektoren
		gui.drawVector(p);
		gui.drawVector(q);

		formelBerechnung(p, q, gui);
		// mitWinkelBerechnung1(p,q,gui);
		// mitWinkelBerechnungTest(p,q,gui);

	}

	private static void mitWinkelBerechnungTest(Vector p, Vector q, MainGUI gui) {

		int schritte = 20;
		double dif = ma.getWinkelZwischen(p, q);
		double anfZustand = dif;
		double schrittePunkte = dif / schritte;

		for (int i = 0; i <= schritte; i++) {
			gui.drawVector(new Vector(anfZustand, 0));
			anfZustand = anfZustand + schrittePunkte;
		}
	}

	private static void mitWinkelBerechnung1(Vector p, Vector q, MainGUI gui) {
		double pOW = p.getOWinkel();
		double pPW = p.getPWinkel();
		double actO = pOW;
		double actP = pPW;

		double qOW = q.getOWinkel();
		double qPW = q.getPWinkel();

		int schritte = 100;

		double difOW = pOW - qOW;
		double difPW = pPW - qPW;

		double pWPerTime = difPW / schritte;
		double oWPerTime = difOW / schritte;

		for (int i = 0; i <= schritte; i++) {
			gui.drawVector(new Vector(actP, actO));
			actP = actP + pWPerTime; // Das hier minus weil sonst falschherum läuft
			actO = actO + oWPerTime;
		}
	}

	// muss man am Ende nicht noch zurück drehen ?
	private static void formelBerechnung(Vector p, Vector q, MainGUI gui) {

		// Vectorlänge (=radius) ist bei Berechnung mit Winkel immer 1
		Vector pDach = ma.vektorDivision(p, ma.vektorLaenge(p)); // Einheitsvektor hat immer die länge 1
		// ******

		System.out.println("länge p:" + ma.vektorLaenge(p));
		System.out.println("P = " + p.getVectorX() + " , " + p.getVectorY() + " , " + p.getVectorZ());
		// System.out.println("Q = "+q.getVectorX()+" , "+q.getVectorY()+" ,
		// "+q.getVectorZ());
		System.out.println("PDach = " + pDach.toString());

		// ma.getAbstand(p,q);
		// Vectorberechnung der Orthogonalen n und u
		Vector n = ma.berechneOrtho(p, q);
		Vector u = ma.berechneOrtho(n, pDach);

		System.out.println("Kreuz :" + ma.kreuzprodukt(p, q).getVectorZ());
		System.out.println("lange :" + ma.vektorLaenge(ma.kreuzprodukt(n, p)));
		// gui.drawVector(n);
		// gui.drawVector(u);
		System.out.println("n: " + n.getVectorX() + n.getVectorY() + n.getVectorZ());
		System.out.println("ux: " + u.getVectorX() + "uy: " + u.getVectorY() + "uZ: " + u.getVectorZ());
		System.out.println("u-kreuz: " + ma.kreuzprodukt(n, p).getVectorX());

		// Deklaration zur Punktbewegung
		double schritte = 360.0;
		double gschw = 1.0;
		double winkel = ma.getWinkelZwischen(p, q);
		double degreeWinkel = Math.toDegrees(winkel);
		double r = ma.vektorLaenge(p);

		// schrittzaehler in der Strecke zwischen p und q (Phi)
		for (double t = 0; gschw * t < winkel; t = t + ((Math.PI * 2) / schritte)) { // muss später in Echtzeit
																						// realisiert werden
			gui.drawVector(ma.berechnePunkt(pDach, u, t));
		}
	}

	private static void toRun() {

		// Test Matrix Vektor Multiplikation
		// try {
		// Matrix matrix = new Matrix(2, 3);
		// matrix.init(new int[] { 4, 3, 2, 1, 2, 3 });
		// Vector vector = new Vector(2, 3, 4);
		// Vector returnVec = new VektorMatrixBerechnung().multiply(vector, matrix);
		// System.out.println("x: " + returnVec.getVectorX() + ", y: " +
		// returnVec.getVectorY() + ", z: "
		// + returnVec.getVectorZ());
		// //Erwartetes Ergebnis; x: 25.0, y: 20.0, z: 0.0
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// Matrix matrix = new Matrix(3, 3);
		// matrix.init(new int[] { 2, 5, 7, 8, 9, 10, 1, 2, 3 });
		// Vector vector = new Vector(2, 4, 5);
		// Vector returnVec = new VektorMatrixBerechnung().multiply(vector, matrix);
		// System.out.println("x: " + returnVec.getVectorX() + ", y: " +
		// returnVec.getVectorY() + ", z: "
		// + returnVec.getVectorZ());
		// // Erwartetes Ergebnis; x: 59.0, y: 102.0, z: 25.0
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

}
