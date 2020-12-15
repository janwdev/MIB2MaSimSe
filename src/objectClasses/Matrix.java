package objectClasses;

public class Matrix {

	private double[][] matrix;

	public final int rows; // Zeilen
	public final int cols; // Spalten

	public Matrix(int rows, int cols) throws Exception {
		if (rows < 0 || cols < 0) {
			throw new Exception("Negative Matrix");
		}
		this.rows = rows;
		this.cols = cols;
	}

	public void init(double... args) throws Exception {
		if (args.length > this.cols * this.rows) {
			throw new Exception("Übergabe-Anzahl ist größer als spalte*zeile");
		}else {
			this.matrix = new double[rows][cols];
			int index = 0;
			for (int i = 0; i < this.rows; i++) {
				for (int j = 0; j < this.cols; j++) {
					// wenn z.b. nur drei Elemente eingegeben wurden bei einer 3x3 Matrix
					if (index + 1 > args.length) {
						break;
					}
					this.matrix[i][j] = args[index];
					index++;
				}
			}
		}
	}

	public double[][] getMatrix() throws Exception{
		if(matrix == null) {
			throw new Exception("Die Matrix wurde noch nicht deklariert");
		}
		return matrix;
	}
	// Warum -1
	public double get(int rows, int cols) throws Exception {
		check(rows, cols);
		return this.matrix[rows -1 ][cols -1];
	}
	// Warum -1 wenn i oder j in Matrix.java 0 sein kann (-1 könnte entstehen);
	public void set(int rows, int cols, double value) throws Exception {
		check(rows, cols);
		this.matrix[rows -1 ][cols -1 ] = value;
	}

	public void check(int rows, int cols) throws Exception {
		if (rows < 0 || cols < 0) {
			throw new Exception("Negative index werte.");
		}
		if (rows > this.rows || cols > this.cols) {
			throw new Exception("zu hoher index für die Matrix");
		}
	}
}
