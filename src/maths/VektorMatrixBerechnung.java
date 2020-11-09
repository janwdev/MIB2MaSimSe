package maths;

import objectClasses.Matrix;
import objectClasses.Vector;

public class VektorMatrixBerechnung {

	public Vector multiply(Vector vector, Matrix matrix) throws Exception {
		try {
			matrix.check(1, vector.dim);
			Vector returnVector = new Vector(0, 0, 0);
			Double[] werte = new Double[matrix.cols];
			for (int i = 0; i < matrix.rows; i++) {
				double reihenErgebnis = 0;
				for (int j = 0; j < matrix.cols; j++) {
					int matrixValue = matrix.get(i + 1, j + 1);
					double vectorValue = vector.getVector()[i];
					reihenErgebnis = reihenErgebnis + matrix.get(i + 1, j + 1) * vector.getVector()[j];
				}
				werte[i] = reihenErgebnis;
				returnVector.setVector(werte);
			}
			return returnVector;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Matrix-Vektor-Multiplikation fehlgeschlagen");
		}
	}

}
