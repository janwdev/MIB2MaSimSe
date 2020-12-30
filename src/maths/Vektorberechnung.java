package maths;

import app.Constants;
import objectClasses.Matrix;
import objectClasses.Vector;

public class Vektorberechnung { // erbt von Vector.java
	VektorMatrixBerechnung vektorMatrix = new VektorMatrixBerechnung();

	protected double skalarProdukt(Vector u, Vector v) {
		double skalarprodukt = 0;
		for (int i = 0; i < 3; i++) { // ändern
			skalarprodukt = skalarprodukt + u.getVector()[i] * v.getVector()[i];
		}
		return skalarprodukt;
	}

	protected double vecLength(Vector v) {//norm oder length
		// Fehlermeldung hinzufügen, falls zu wenig werte im Array (oder zu viel)
		return Math.sqrt(Math.pow(v.getVectorX(), 2) + Math.pow(v.getVectorY(), 2) + Math.pow(v.getVectorZ(), 2));
	}

	public double winkelBerechnen(Vector u, Vector v) {// hier muss noch bedingung hin wann -arccos oder +arccos, für Kugelkoordinaten p (y>=0) und O (z>=0) => Also noch eine Drehübergabeparameter (pan,tilt,roll) Nicht nötig
			// radiant wird zurückgegebene 0.90 zB
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
		Vector vec = new Vector(vector.getVectorX()/wert, vector.getVectorY()/wert, vector.getVectorZ()/wert);
		return vec;
	}
	
	protected Vector vecMulti(Vector vector, double wert) {
		Vector vec = new Vector(vector.getVectorX()*wert, vector.getVectorY()*wert, vector.getVectorZ()*wert);
		return vec;
	}
	
	protected Vector vecAddition(Vector v, Vector u) {
		Vector vecNew = new Vector(v.getVectorX()+u.getVectorX(), v.getVectorY()+u.getVectorY(), v.getVectorZ()+u.getVectorZ());					
		return vecNew;
	}


	protected Vector kreuzprodukt(Vector u, Vector v) {
		
		double x = (u.getVectorY()*v.getVectorZ())-(u.getVectorZ()*v.getVectorY());
		double y = (u.getVectorZ()*v.getVectorX())-(u.getVectorX()*v.getVectorZ());
		double z = (u.getVectorX()*v.getVectorY())-(u.getVectorY()*v.getVectorX());
		
		return new Vector(x,y,z);
	}
	
	protected double abstandVector(Vector u, Vector v) {
		
		double r = Constants.earthRadius;//radius r=konstante Erdradius
		return (2*Math.PI*r*Math.toDegrees(winkelBerechnen(u, v)))/360;
		
	}

	protected Vector berechneOrtho(Vector u, Vector v) {
		Vector vec = vecDiv(kreuzprodukt(u, v), vecLength(kreuzprodukt(u, v)));
		return vec;
		
	}
	
	protected Vector berechnePunkt(Vector pDach, Vector u, double a) {
		double r = 1.0; // Constants.earthRadius // ist immer 1
		Vector v = null;
		
		return vecAddition(vecMulti(pDach, r*Math.cos(a)),vecMulti(u, r*Math.sin(a))); // Formel pDach*r*cos(a) + u*r*sin(a);
	}
	
	
}
