package maths;

import objectClasses.Matrix;

public class MatrixBerechnung {

	protected Matrix multipy(Matrix ma1, Matrix ma2) {
		try {
			double[][] m1 = ma1.getMatrix();
			double[][] m2 = ma2.getMatrix();

			// double[][] ergebnismatrix = null;

			Matrix retMat = null;
			if (m1[0].length == m2.length) {
				int zeilenm1 = m1.length;
				int spaltenm1 = m1[0].length;
				int spalenm2 = m2[0].length;

				// ergebnismatrix = new double[zeilenm1][spalenm2];
				retMat = new Matrix(zeilenm1, spalenm2);
				

				for (int i = 1; i <= zeilenm1; i++) {
					for (int j = 1; j <= spalenm2; j++) {
						// ergebnismatrix[i][j] = 0;
						retMat.setWithInit(i, j, 0);
						for (int k = 1; k <= spaltenm1; k++) {
							// ergebnismatrix[i][j] += m1[i][k] * m2[k][j];
							retMat.setWithInit(i, j, retMat.get(i, j) + m1[i-1][k-1] * m2[k-1][j-1]);
						}
					}
				}
			} else {
//				int zeilen = m1.length;
//				int spalten = m1[0].length;
//
//				// ergebnismatrix = new double[zeilen][spalten];
//				retMat = new Matrix(zeilen, spalten);
//				for (int i = 0; i < m1.length; i++) {
//					for (int j = 0; j < m1[0].length; j++) {
//						// ergebnismatrix[i][j] = 0;
//						retMat.set(i, j, 0);
//					}
//				}
				throw new Exception("Multiply nicht moeglich");
			}
			return retMat;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected Matrix getDrehMatrixZ(double winkelZ) throws Exception {
		Matrix zMatrix = new Matrix(3, 3);
		zMatrix.init(Math.cos(winkelZ), -Math.sin(winkelZ), 0, Math.sin(winkelZ), Math.cos(winkelZ), 0, 0, 0, 1);
		return zMatrix;
	}

}
