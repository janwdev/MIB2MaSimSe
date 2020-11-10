package maths;

import objectClasses.Matrix;
import objectClasses.Vector;

public class Vektorberechnung {
	VektorMatrixBerechnung vektorMatrix = new VektorMatrixBerechnung();

	public double skalarProdukt(Vector u, Vector v) {
		double skalarprodukt = 0;
		for (int i = 0; i < u.dim; i++) {
			skalarprodukt = skalarprodukt + u.getVector()[i] * v.getVector()[i];
		}
		return skalarprodukt;
	}

	public double wurzel(Vector v) {
		// Fehlermeldung hinzufügen, falls zu wenig werte im Array (oder zu viel)
		return Math.sqrt(Math.pow(v.getVector()[0], 2) + Math.pow(v.getVector()[1], 2) + Math.pow(v.getVector()[2], 2));
	}

	public double winkelBerechnen(Vector u, Vector v) {
		if (u.getVector()[2] >= 0) {
			return (double) Math.acos(skalarProdukt(u, v) / (wurzel(v) * wurzel(u)));
		} else {
			return (double) -(Math.acos(skalarProdukt(u, v) / (wurzel(v) * wurzel(u))));
		}
	}

	public Vector drehen(Vector vektor, Matrix drehMatrix) throws Exception {
		double oldZ = vektor.getVectorZ();
		vektor.setVector(vektor.getVectorX(), vektor.getVectorY(), 0);
		vektor = vektorMatrix.multiply(vektor, drehMatrix);
		vektor.setVector(vektor.getVectorX(), vektor.getVectorY(), oldZ);
		return vektor;
	}


	
	public double abstandVector(Vector u, Vector v) {
		
		double r = wurzel(u);//radius
		return (2*Math.PI*r*Math.toDegrees(winkelBerechnen(u, v)))/360;
		
	}
	
	
}
