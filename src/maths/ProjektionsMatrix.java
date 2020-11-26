package maths;


import app.Constants;
import objectClasses.Matrix;

public class ProjektionsMatrix {
	
	double s = (1/Math.sqrt(2));	// Standarteinstellungen axonometrische Angaben
	double a = (135); 				//in Grad
	double[][] pm;
	Matrix m = new Matrix(2,4);
	
	
	public ProjektionsMatrix() throws Exception{
		try {
			proMatrixErstellen();
		}catch (Exception e){
			
		}

	}
	
	public void proMatrixErstellen() throws Exception{
		try {
			pm = new double[2][4] ;
			m.init(-s*Math.sin(a), 1.0, 0.0, (double)Constants.drawSizeXPixels, s*Math.cos(a), 0.0, 1.0, (double)Constants.drawSizeYPixels); // Nachschauen überprüfen (minus bei cos hab ich weggemacht
			pm = m.getMatrix();
		}catch (Exception e){
			System.out.println("Ein Fehler bei der Matrixerzeugeung: "+e);
		}
	}
	
	public double[][] getProjektionsMatrix() {
		if(pm == null) {
			try {
				proMatrixErstellen();
			}catch (Exception e){
			}
			return pm;
		}else {
			return pm;
		}
		
	}
	
	public Matrix getProMatrix() {
		
		return m;
		
	}
	
	public void setProjektionsMatrix(double s , double a) { // eventuelle überprüfung einfügen, falls Parameter nicht zulässig sind.
		this.s = s;
		this.a = a;
		try {
			proMatrixErstellen();
		}catch (Exception e){
		}
		
		
		
	}
	

}
