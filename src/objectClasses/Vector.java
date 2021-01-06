package objectClasses;

import maths.VectorCalculation;
import app.Constants;

/**
 * Vecotr Object class
 * 
 * @author Luca, Jannik
 *
 */
public class Vector {

	private double x; // X-Coordinate
	private double y; // Y-Coordinate
	private double z; // Z-Coordinate
	private double r = Constants.earthRadius; // Earthradius
	private double wPhi; // Longitude xRoof and v' Phi
	private double wTheta; // Latitude v and v' Theta

	/**
	 * Instantiate vector with x,y,z and calculate angle
	 * 
	 * @param x double value
	 * @param y double value
	 * @param z double value
	 */
	public Vector(double x, double y, double z) {
		initVector(true, x, y, z);
	}

	/**
	 * instantiate vector with x,y,z and/or without angle calculation
	 * 
	 * @param withAngel boolean
	 * @param x         double value
	 * @param y         double value
	 * @param z         double value
	 */
	private Vector(boolean withAngel, double x, double y, double z) {
		initVector(withAngel, x, y, z);
	}

	/**
	 * Assign vector values and/or calculate angles
	 * 
	 * @param withAngel boolean
	 * @param x         double value
	 * @param y         double value
	 * @param z         double value
	 */
	private void initVector(boolean withAngel, double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		if (withAngel)
			angelCalculation();
	}

	/**
	 * instantiate vector with 2 angles (Phi,Theta) and calculate coordinates
	 * 
	 * @param wP double value
	 * @param wO double value
	 */
	public Vector(double wP, double wO) {
		this.wPhi = wP;
		this.wTheta = wO;
		coordinateCalculation();
	}

	/**
	 * Angle calculation with given coordinates
	 */
	public void angelCalculation() {
		VectorCalculation vecCa = new VectorCalculation();
		wTheta = vecCa.angelCalculation(this, new Vector(false, x, y, 0));
		wPhi = vecCa.angelCalculation(new Vector(false, x, y, 0), new Vector(false, x, 0, 0));
	}

	/**
	 * Coordinate calculation with given angles
	 */
	public void coordinateCalculation() {
		this.x = r * Math.cos(wTheta) * Math.cos(wPhi);
		this.y = r * Math.cos(wTheta) * Math.sin(wPhi);
		this.z = r * Math.sin(wTheta);
	}

	// Vector setter/getter-Method
	// ***********************************************************
	/**
	 * 
	 * @return double[] Array
	 */
	public double[] getVector() {
		return new double[] { x, y, z };
	}

	/**
	 * 
	 * @return double value
	 */
	public double getVectorX() {
		return x;
	}

	/**
	 * 
	 * @return double value
	 */
	public double getVectorY() {
		return y;
	}

	/**
	 * 
	 * @return double value
	 */
	public double getVectorZ() {
		return z;
	}

	/**
	 * 
	 * @param value double[] array
	 */
	public void setVector(Double[] value) {
		if (value[0] != null)
			this.x = value[0];
		if (value[1] != null)
			this.y = value[1];
		if (value[2] != null)
			this.z = value[2];
		angelCalculation();
	}

	/**
	 * 
	 * @param x double value
	 * @param y double value
	 * @param z double value
	 */
	public void setVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		angelCalculation();
	}

	/**
	 * set Angel with Phi and Theta
	 * 
	 * @param wP double value
	 * @param wO double value
	 */
	public void setAngel(double wP, double wO) {
		this.wPhi = wP;
		this.wTheta = wO;
		coordinateCalculation();
	}

	/**
	 * 
	 * @return double value
	 */
	public double getPAngel() {
		return wPhi;
	}

	/**
	 * 
	 * @return double value
	 */
	public double getOAngel() {
		return wTheta;
	}

	// **********************************************************************************************
	/**
	 * toString method for simplified console output
	 */
	public String toString() {
		return "X: " + x + " Y: " + y + " Z: " + z + " Phi: " + wPhi + " Theta: " + wTheta;
	}

}
