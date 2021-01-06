package objectClasses;

import gui.MainGUI;

/**
 * Matrix Object Class
 * 
 * @author Luca
 *
 */
public class Matrix {

	private double[][] matrix;

	public final int rows;
	public final int cols;

	/**
	 * Matrix instantiation with column and row passing
	 * 
	 * @param rows
	 * @param cols
	 * @throws Exception
	 */
	public Matrix(int rows, int cols) throws Exception {
		if (rows < 0 || cols < 0) {
			throw new Exception("Negative index values.");
		}
		this.rows = rows;
		this.cols = cols;
	}

	/**
	 * Assign matrix values
	 * 
	 * @param args [double,double,double,...]
	 */
	public void init(double... args) {
		// Check if too many values have been passed
		if (args.length > this.cols * this.rows) {
			MainGUI.showErrorMessage("Transfer count is greater than column*row");
		} else {
			this.matrix = new double[rows][cols];
			int index = 0;
			for (int i = 0; i < this.rows; i++) {
				for (int j = 0; j < this.cols; j++) {
					// if e.g. only three elements were entered for a 3x3 matrix
					if (index + 1 > args.length) {
						break;
					}
					this.matrix[i][j] = args[index];
					index++;
				}
			}
		}
	}

	// Matrix setter/getter Method
	// *********************************************************************
	/**
	 * get Matrix as an 2 Dimensional Array
	 * 
	 * @return Array[][]
	 */
	public double[][] getMatrix() {
		if (matrix == null) {
			MainGUI.showErrorMessage("The matrix has not yet been declared");
		}
		return matrix;
	}

	/**
	 * get value in a specific column,row
	 * 
	 * @param rows
	 * @param cols
	 * @return double value
	 * @throws Exception
	 */
	public double get(int rows, int cols) throws Exception {
		check(rows, cols);
		return this.matrix[rows - 1][cols - 1];
	}

	/**
	 * set a value in a specific column,row
	 * 
	 * @param rows
	 * @param cols
	 * @param value double
	 * @throws Exception
	 */
	public void set(int rows, int cols, double value) throws Exception {
		check(rows, cols);
		this.matrix[rows - 1][cols - 1] = value;
	}

	/**
	 * set a value in a specific column,row and initialize
	 * 
	 * @param rows
	 * @param cols
	 * @param value double
	 * @throws Exception
	 */
	public void setWithInit(int rows, int cols, double value) throws Exception {
		check(rows, cols);
		if (this.matrix == null)
			this.matrix = new double[this.rows][this.cols];
		this.matrix[rows - 1][cols - 1] = value;
	}

	// ******************************************************************************************************
	/**
	 * Check Matrix rows and Cols
	 * 
	 * @param rows
	 * @param cols
	 * @throws Exception
	 */
	public void check(int rows, int cols) throws Exception {
		if (rows < 0 || cols < 0) {
			MainGUI.showErrorMessage("Negative index values.");
		}
		if (rows > this.rows || cols > this.cols) {
			MainGUI.showErrorMessage("too high index for the matrix");
		}
	}
}
