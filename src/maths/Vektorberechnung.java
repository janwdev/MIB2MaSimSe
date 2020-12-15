package maths;

import app.Constants;
import objectClasses.Matrix;
import objectClasses.Vector;

public class Vektorberechnung { // erbt von Vector.java
	VektorMatrixBerechnung vektorMatrix = new VektorMatrixBerechnung();

	protected double skalarProdukt(Vector u, Vector v) {
		double skalarprodukt = 0;
		for (int i = 0; i < 3; i++) { // �ndern
			skalarprodukt = skalarprodukt + u.getVector()[i] * v.getVector()[i];
		}
		return skalarprodukt;
	}

	protected double vecLength(Vector v) {//norm oder length
		// Fehlermeldung hinzuf�gen, falls zu wenig werte im Array (oder zu viel)
		return Math.sqrt(Math.pow(v.getVector()[0], 2) + Math.pow(v.getVector()[1], 2) + Math.pow(v.getVector()[2], 2));
	}

	public double winkelBerechnen(Vector u, Vector v) {// hier muss noch bedingung hin wann -arccos oder +arccos, f�r Kugelkoordinaten p (y>=0) und O (z>=0) => Also noch eine Dreh�bergabeparameter (pan,tilt,roll) Nicht n�tig
			// radiant wird zur�ckgegebene 0.90 zB
			return (double) Math.acos(skalarProdukt(u, v) / (vecLength(v) * vecLength(u)));
	
	}

	protected Vector drehen(Vector vektor, Matrix drehMatrix) throws Exception {
		double oldZ = vektor.getVectorZ();
		vektor.setVector(vektor.getVectorX(), vektor.getVectorY(), 0);
		vektor = vektorMatrix.multiply(vektor, drehMatrix);
		vektor.setVector(vektor.getVectorX(), vektor.getVectorY(), oldZ);
		return vektor;
	}
	
	protected Vector vecDiv(Vector vector, double wert) {
		Vector vec = new Vector(0,0,0);
		vec.setVector(vector.getVectorX()/wert, vector.getVectorY()/wert, vector.getVectorZ()/wert);
		return vec;
	}
	
	protected Vector vecMulti(Vector vector, double wert) {
		Vector vec = vector;
		vec.setVector(vec.getVectorX()*wert, vec.getVectorY()*wert, vec.getVectorZ()*wert);
		return vec;
	}
	
	protected Vector vecAddition(Vector v, Vector u) {
		
		Vector vec = new Vector(0,0,0);
		
		vec.setVector(v.getVectorX()+u.getVectorX(), v.getVectorY()+u.getVectorY(), v.getVectorZ()+u.getVectorZ());
		return vec;
		
	}


	protected Vector kreuzprodukt(Vector u, Vector v) {
		
		
		double x = (u.getVectorY()*v.getVectorZ())-(u.getVectorZ()*v.getVectorY());
		double y = (u.getVectorZ()*v.getVectorX())-(u.getVectorX()*v.getVectorZ());
		double z = (u.getVectorX()*v.getVectorY())-(u.getVectorY()*v.getVectorX());
		Vector vec = new Vector(x,y,z);
		return vec;
		
	}
	
	protected double abstandVector(Vector u, Vector v) {
		
		double r = Constants.earthRadius;//rdius r=konstante Erdradius
		return (2*Math.PI*r*Math.toDegrees(winkelBerechnen(u, v)))/360;
		
	}
	
	
}
