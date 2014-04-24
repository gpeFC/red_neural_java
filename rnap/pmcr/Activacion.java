/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Activacion

public final class Activacion{
	public static double logaritmoSigmoidal(double potencial){
		double activacion = 1.0 / (1.0 + (Math.pow(Math.E, -potencial)));
		if(activacion > 0.0)
			return 1.0;
		else
			return -1.0;
	}

	public static double tangenteSigmoidal(double potencial){
		double activacion = (2.0 / (1.0 + (Math.pow(Math.E, -potencial)))) - 1.0;
		if(activacion > 0.0)
			return 1.0;
		else
			return -1.0;
	}

	public static double tangenteHiperbolica(double potencial){
		double activacion = ((Math.pow(Math.E, potencial)) - ((Math.pow(Math.E, -potencial)))) / ((Math.pow(Math.E, potencial)) + ((Math.pow(Math.E, -potencial))));
		if(activacion > 0.0)
			return 1.0;
		else
			return -1.0;
	}
}