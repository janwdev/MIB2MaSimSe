package maths;

import app.Constants;
import objectClasses.Matrix;

public class ProjektionsMatrix {

	double s = (1 / Math.sqrt(2)); // Standardeinstellungen axonometrische Angaben
	double a = (3*Math.PI)/4; // in Grad
	double[][] pm;
	Matrix m;

	public ProjektionsMatrix() {
		try {
			m = new Matrix(2, 3); // zuz 2,3 ändern
			proMatrixErstellen();
		} catch (Exception e) {

		}

	}

	protected void proMatrixErstellen() throws Exception {
		try {
			// pm = new double[2][4] ;
			// m.init(-s*Math.sin(a), 1.0, 0.0, (double)Constants.drawSizeXPixels,
			// s*Math.cos(a), 0.0, 1.0, (double)Constants.drawSizeYPixels); // Nachschauen
			// überprüfen (minus bei cos hab ich weggemacht
			pm = new double[2][3];
			m.init(-s * Math.sin(a), 1.0, 0.0, -s * Math.cos(a), 0.0, -1.0); // Nachschauen überprüfen (minus bei cos hab
																			// ich weggemacht
			pm = m.getMatrix();
		} catch (Exception e) {
			System.out.println("Ein Fehler bei der Matrixerzeugeung: " + e);
		}
	}

	protected double[][] getProjektionsMatrix() {
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


	protected void setProjektionsMatrix(double s, double a) { // eventuelle überprüfung einfügen, falls Parameter nicht
																// zulässig sind.
		this.s = s;
		this.a = a;
		try {
			proMatrixErstellen();
		} catch (Exception e) {
		}

	}

}
