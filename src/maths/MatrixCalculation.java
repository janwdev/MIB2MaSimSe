package maths;

import gui.MainGUI;
import objectClasses.Matrix;

/**
 * 
 * @author Luca Jannik
 *
 */
public class MatrixCalculation {

	/**
	 * Multiply two Matrices
	 * 
	 * @param ma1 Matrix1
	 * @param ma2 Matrix2
	 * @return Result Matrix
	 */
	protected Matrix multipy(Matrix ma1, Matrix ma2) {
		try {
			double[][] m1 = ma1.getMatrix();
			double[][] m2 = ma2.getMatrix();

			Matrix retMat = null;
			if (m1[0].length == m2.length) {
				int zeilenm1 = m1.length;
				int spaltenm1 = m1[0].length;
				int spalenm2 = m2[0].length;

				retMat = new Matrix(zeilenm1, spalenm2);

				for (int i = 1; i <= zeilenm1; i++) {
					for (int j = 1; j <= spalenm2; j++) {
						retMat.setWithInit(i, j, 0);
						for (int k = 1; k <= spaltenm1; k++) {
							retMat.setWithInit(i, j, retMat.get(i, j) + m1[i - 1][k - 1] * m2[k - 1][j - 1]);
						}
					}
				}
			} else {
				throw new Exception("Can't Multiply");
			}
			return retMat;
		} catch (Exception e) {
			e.printStackTrace();
			MainGUI.showErrorMessage(e.getMessage());
			return null;
		}
	}

	/**
	 * Get rotation matrix around z-axis with given angle
	 * 
	 * @param angleZ
	 * @return rotation Matrix
	 * @throws Exception
	 */
	protected Matrix getRotateMatrixZ(double angleZ) throws Exception {
		Matrix zMatrix = new Matrix(3, 3);
		zMatrix.init(Math.cos(angleZ), -Math.sin(angleZ), 0, Math.sin(angleZ), Math.cos(angleZ), 0, 0, 0, 1);
		return zMatrix;
	}

}
