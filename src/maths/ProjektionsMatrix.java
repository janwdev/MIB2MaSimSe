package maths;

import app.Constants;
import objectClasses.Matrix;

public class ProjektionsMatrix {

	double s = 2.0; 			// Standardeinstellungen axonometrische Angaben
	double a = 3.0;							// Winkel in Grad
	double[][] pm;							// Projektionsmatrix in 2Dim-Array
	Matrix m;								// Matrix-Objekt

	public ProjektionsMatrix() {
		try {
			m = new Matrix(2, 3); 			// Konstruktor erstellt eine 2x3 Matrix für die Projektionsmatrix
			proMatrixErstellen();
		} catch (Exception e) {

		}

	}

	protected void proMatrixErstellen() throws Exception {
		try {
			pm = new double[2][3];
			m.init(-s * Math.sin(a), 1.0, 0.0, -s * Math.cos(a), 0.0, -1.0); 		// Inhalt der Projektionsmatrix wird gesetzt (args ...)
			pm = m.getMatrix();
		} catch (Exception e) {
			System.out.println("Ein Fehler bei der ProjektionsMatrixerzeugeung: " + e);
		}
	}

	protected double[][] getProjektionsMatrix() { // brauchen wir das ????
		if (pm == null) {
			try {
				proMatrixErstellen();
			} catch (Exception e) {
			}
			return pm;
		} else {
			return pm;
		}

	}

	
	public Matrix getProMatrix() { // von Protected zu public
		return m;
	}
	
	public double getS() {
		return s;
	}
	
	public double getA() {
		return a;
	}


	protected void setProjektionsMatrix(double s, double a) { // eventuelle überprüfung einfügen, falls Parameter nicht
																// zulässig sind.
		this.s = s%2.0;				// s nicht größer als 2
		this.a = a%360.0;			// a max 360 Grad
		try {
			proMatrixErstellen();
		} catch (Exception e) {
		}

	}

}
