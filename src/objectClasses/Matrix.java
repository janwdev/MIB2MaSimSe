package objectClasses;

import java.awt.Dimension;

public class Matrix {

	private int[][]matrix;
	
	private int rows = 0; // Zeilen
    private int cols = 0; // Spalten
	
	
    public Matrix(int rows, int cols) throws Exception {
        if (rows < 0 || cols < 0) {
            throw new Exception("Negative Matrix");
        }
        this.rows = rows;
        this.cols = cols;
    }
    
	public void init(int... args) throws Exception {
        if (args.length > this.cols*this.rows) {
            throw new Exception("Übergabe-Anzahl ist größer als spalte*zeile");
        }
        int index = 0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                // wenn z.b. nur drei Elemente eingegeben wurden bei einer 3x3 Matrix
                if (index+1 > args.length) {
                    break;
                }
                this.matrix[i][j] = args[index];
                index++;
            }
        }
    }
	
}
