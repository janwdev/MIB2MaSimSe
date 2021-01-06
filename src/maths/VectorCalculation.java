package maths;

import app.Constants;
import objectClasses.Matrix;
import objectClasses.Vector;

/**
 * All Vector Calculations
 * 
 * @author Luca
 *
 */
public class VectorCalculation { // inherit from Vector.java
	VectorMatrixCalculation vectorMatrix = new VectorMatrixCalculation();

	/**
	 * scalar Product
	 * 
	 * @param u Vector Object
	 * @param v Vector Object
	 * @return double value
	 */
	protected double scalarProduct(Vector u, Vector v) {
		double scalarproduct = 0;
		for (int i = 0; i < 3; i++) {
			scalarproduct = scalarproduct + u.getVector()[i] * v.getVector()[i];
		}
		return scalarproduct;
	}

	/**
	 * Calculate length of a vector
	 * 
	 * @param v Vector Object
	 * @return double value
	 */
	protected double vecLength(Vector v) {
		return Math.sqrt(Math.pow(v.getVectorX(), 2) + Math.pow(v.getVectorY(), 2) + Math.pow(v.getVectorZ(), 2));
	}

	/**
	 * Rotate a vector by multiplication with a rotate matrix
	 * 
	 * @param vector       Vector Object
	 * @param rotateMatrix Matrix Object
	 * @return Vector Object
	 * @throws Exception
	 */
	protected Vector rotate(Vector vector, Matrix rotateMatrix) throws Exception {
		vector = vectorMatrix.multiply(vector, rotateMatrix);
		return vector;
	}

	/**
	 * Division of vector and value
	 * 
	 * @param vector Vector Object
	 * @param value  double
	 * @return Vector Object
	 */
	protected Vector vecDiv(Vector vector, double value) {
		Vector vec = new Vector(vector.getVectorX() / value, vector.getVectorY() / value, vector.getVectorZ() / value);
		return vec;
	}

	/**
	 * Multiplication of vector and value
	 * 
	 * @param vector Vector Object
	 * @param value  double
	 * @return Vector Object
	 */
	protected Vector vecMulti(Vector vector, double value) {
		Vector vec = new Vector(vector.getVectorX() * value, vector.getVectorY() * value, vector.getVectorZ() * value);
		return vec;
	}

	/**
	 * Addition of vectors
	 * 
	 * @param v Vector Object
	 * @param u Vector Object
	 * @return Vector Object
	 */
	protected Vector vecAddition(Vector v, Vector u) {
		Vector vecNew = new Vector(v.getVectorX() + u.getVectorX(), v.getVectorY() + u.getVectorY(),
				v.getVectorZ() + u.getVectorZ());
		return vecNew;
	}

	/**
	 * cross product: result is a vector that is perpendicular to both of the two
	 * vectors.
	 * 
	 * @param u Vector Object
	 * @param v Vector Object
	 * @return Vector Object
	 */
	protected Vector crossProduct(Vector u, Vector v) {

		double x = (u.getVectorY() * v.getVectorZ()) - (u.getVectorZ() * v.getVectorY());
		double y = (u.getVectorZ() * v.getVectorX()) - (u.getVectorX() * v.getVectorZ());
		double z = (u.getVectorX() * v.getVectorY()) - (u.getVectorY() * v.getVectorX());

		return new Vector(x, y, z);
	}

	/**
	 * Distance between 2 vectors on the circular path
	 * 
	 * @param u Vector Object
	 * @param v Vector Object
	 * @return double value
	 */
	protected double distanceVector(Vector u, Vector v) {

		double r = Constants.earthRadius;
		return (2 * Math.PI * r * Math.toDegrees(angelCalculation(u, v))) / 360;
	}

	// Pointcalculation
	// *****************************************************************
	/**
	 * 
	 * @param u Vector Object
	 * @param v Vector Object
	 * @return Vector Object
	 */
	protected Vector calculateOrtho(Vector u, Vector v) {
		Vector vec = vecDiv(crossProduct(u, v), vecLength(crossProduct(u, v)));
		return vec;
	}

	/**
	 * Curve parameters
	 * 
	 * @param pRoof Vector Object
	 * @param u     Vector Object
	 * @param a     double value
	 * @return Vector Object
	 */
	protected Vector calculatePoint(Vector pRoof, Vector u, double a) {
		double r = Constants.earthRadius;
		return vecAddition(vecMulti(pRoof, r * Math.cos(a)), vecMulti(u, r * Math.sin(a))); // pRoof*r*cos(a) +
																							// u*r*sin(a);
	}
	// *********************************************************************************

	/**
	 * Angel between 2 Vectors
	 * 
	 * @param u Vector Object
	 * @param v Vector Object
	 * @return Vector Object
	 */
	public double angelCalculation(Vector u, Vector v) {
		return (double) Math.acos(scalarProduct(u, v) / (vecLength(v) * vecLength(u)));

	}

}
