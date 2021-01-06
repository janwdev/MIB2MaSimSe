package maths;

import app.Constants;
import objectClasses.Matrix;
import objectClasses.Vector;

/**
 * Math Calculations
 * 
 * @author Luca,Jannik
 *
 */
public class Maths {
	private MatrixCalculation bMatrix = new MatrixCalculation();
	private ProjectionsMatrix projectionsMatrix = new ProjectionsMatrix();
	private VectorCalculation bVector = new VectorCalculation();
	private VectorMatrixCalculation bVectorMatrix = new VectorMatrixCalculation();

	/**
	 * 
	 * @return Matrix Object
	 */
	public Matrix getProjectionsMatrix() {
		return projectionsMatrix.getProMatrix();
	}

	/**
	 * 
	 * @return 2-Dimensional-Array (ProjectionsMatrix)
	 */
	public double[][] getProjectionsMatrixArray() {
		return projectionsMatrix.getProjectionsMatrix();
	}

	/**
	 * 
	 * @return ProjectionsMatrix Object
	 */
	public ProjectionsMatrix getProjectionsMatrixClass() {
		return projectionsMatrix;
	}

	/**
	 * Multiply Matrix * Matrix
	 * 
	 * @param ma1 Matrix Object
	 * @param ma2 Matrix Object
	 * @return Matrix Object
	 */
	public Matrix multiply(Matrix ma1, Matrix ma2) {
		return bMatrix.multipy(ma1, ma2);
	}

	/**
	 * scalarProduct
	 * 
	 * @param u Vector Object
	 * @param v Vector Object
	 * @return double value
	 */
	public double scalarProduct(Vector u, Vector v) {
		return bVector.scalarProduct(u, v);
	}

	/**
	 * calculate Vector length
	 * 
	 * @param v Vector Objects
	 * @return double value
	 */
	public double vectorLength(Vector v) {
		return bVector.vecLength(v);
	}

	/**
	 * calculate the angel Between 2 Vectors
	 * 
	 * @param u Vector Object
	 * @param v Vector Object
	 * @return double value
	 */
	public double getAngelBetween(Vector u, Vector v) {
		return bVector.angelCalculation(u, v);
	}

	/**
	 * rotate Vector
	 * 
	 * @param v  Vector Object
	 * @param wz double rotation
	 * @return Vector Object
	 * @throws Exception
	 */
	public Vector rotateVectorZ(Vector v, double wz) throws Exception {
		Vector vec = bVector.rotate(v, bMatrix.getRotateMatrixZ(wz));
		return vec;
	}

	/**
	 * divide Vector with a value
	 * 
	 * @param v     Vector Object
	 * @param value double
	 * @return Vector Object
	 */
	public Vector vectorDivision(Vector v, double value) {
		return bVector.vecDiv(v, value);
	}

	/**
	 * multiply Vector with a value
	 * 
	 * @param v     Vector Object
	 * @param value double
	 * @return Vector Object
	 */
	public Vector vectorMultiplication(Vector v, double value) {
		return bVector.vecMulti(v, value);
	}

	/**
	 * addition between 2 Vectors
	 * 
	 * @param v Vector Object
	 * @param u Vector Object
	 * @return Vector Object
	 */
	public Vector vectorAddition(Vector v, Vector u) {
		return bVector.vecAddition(v, u);
	}

	/**
	 * crossProdukt between 2 Vectors
	 * 
	 * @param u Vector Object
	 * @param v Vector Object
	 * @return Vector Object
	 */
	public Vector crossProduct(Vector u, Vector v) {
		return bVector.crossProduct(u, v);
	}

	// Point Calculation
	/**
	 * 
	 * @param u
	 * @param v
	 * @return
	 */
	public Vector calculateOrtho(Vector u, Vector v) {
		return bVector.calculateOrtho(u, v);
	}

	/**
	 * Calculate Points
	 * 
	 * @param p Vector Object
	 * @param u Vector Object
	 * @param a double value
	 * @return Vector Object
	 */
	public Vector calculatePoint(Vector p, Vector u, double a) {
		return bVector.calculatePoint(p, u, a);
	}

	// ***********************
	/**
	 * calculate the Distance between 2 Vectors on a circlepath
	 * 
	 * @param u Vector Object
	 * @param v Vector Object
	 * @return Vector Object
	 */
	public double getDistance(Vector u, Vector v) {
		return bVector.distanceVector(u, v);
	}

	/**
	 * Multiply a Vector with Matrix
	 * 
	 * @param v Vector Object
	 * @param m Matrix Object
	 * @return Vector Object
	 * @throws Exception
	 */
	public Vector multiply(Vector v, Matrix m) throws Exception {
		return bVectorMatrix.multiply(v, m);
	}

	/**
	 * Move 2D Vector into the center
	 * 
	 * @param v Vector Object
	 * @return integer Array
	 */
	public int[] vector2DToDisplayKoordinates(Vector v) {
		int[] ret;
		double x = v.getVectorX();
		double y = v.getVectorY();
		x = Constants.xOffsetDraw + x * 100 * Constants.drawScaleFactor;
		y = Constants.yOffsetDraw + y * 100 * Constants.drawScaleFactor;
		int xDisp = (int) Math.round(x);
		int yDisp = (int) Math.round(y);
		ret = new int[] { xDisp, yDisp };
		return ret;
	}
}
