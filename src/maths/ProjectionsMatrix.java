package maths;

import gui.MainGUI;
import objectClasses.Matrix;

/**
 * ProjectionsMatrix
 * 
 * @author Luca
 *
 */
public class ProjectionsMatrix {

	double s = (1 / Math.sqrt(2)); // Default axonometric settings
	double a = 3.0; // (3*Math.PI)/4; // in Degree
	double[][] pm; // Projectionsmatrix in 2Dim-Array
	Matrix m; // Matrix-Object

	/**
	 * Constructor
	 */
	public ProjectionsMatrix() {
		try {
			m = new Matrix(2, 3); // Constructor creates a 2x3 matrix for the projection matrix
			createProMatrix();
		} catch (Exception e) {

		}

	}

	/**
	 * create a ProjectionsMatrix
	 */
	protected void createProMatrix() {
		try {
			pm = new double[2][3];
			m.init(-s * Math.sin(a), 1.0, 0.0, -s * Math.cos(a), 0.0, -1.0); // content of the projection matrix is set
																				// (args ...)
			pm = m.getMatrix();
		} catch (Exception e) {
			MainGUI.showErrorMessage("An error in the projection matrix generation");
		}
	}

	/**
	 * 
	 * @return ProjectionsMatrix Object
	 */
	protected double[][] getProjectionsMatrix() {
		if (pm == null) {
			try {
				createProMatrix();
			} catch (Exception e) {
			}
			return pm;
		} else {
			return pm;
		}
	}

	/**
	 * 
	 * @return Matrix Object
	 */
	public Matrix getProMatrix() {
		return m;
	}

	/**
	 * 
	 * @return double value
	 */
	public double getS() {
		return s;
	}

	/**
	 * 
	 * @return double value
	 */
	public double getA() {
		return a;
	}

	/**
	 * set s and a for the ProjectionsMatrix
	 * 
	 * @param s double value
	 * @param a double value
	 */
	protected void setProjectionsMatrix(double s, double a) {
		this.s = s % 2.0; // s not over 2
		this.a = a % 360.0; // a max 360 Degree
		try {
			createProMatrix();
		} catch (Exception e) {
		}

	}

}
