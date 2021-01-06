package maths;

import objectClasses.Matrix;
import objectClasses.Vector;

/**
 * @author Luca Jannik
 *
 */
public class VectorMatrixCalculation {

	/**
	 * Multiply Vector with Matrix
	 * 
	 * @param vector
	 * @param matrix
	 * @return resulting Vector
	 * @throws Exception
	 */
	protected Vector multiply(Vector vector, Matrix matrix) throws Exception {
		try {
			Vector returnVector = new Vector(0, 0, 0);
			Double[] values = new Double[matrix.cols];
			for (int i = 0; i < matrix.rows; i++) {
				double rowResult = 0;
				for (int j = 0; j < matrix.cols; j++) {
					double matrixValue = matrix.get(i + 1, j + 1);
					double vectorValue = vector.getVector()[i];
					rowResult = rowResult + matrix.get(i + 1, j + 1) * vector.getVector()[j];
				}
				values[i] = rowResult;
				returnVector.setVector(values);
			}
			return returnVector;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Matrix-Vektor-Multiplication went wrong");
		}
	}

}
