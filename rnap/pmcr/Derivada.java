/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Derivada

public final class Derivada{
	public static double logaritmoSigmoidal(double potencial){
		return (Activacion.logaritmoSigmoidal(potencial) * (1 - Activacion.logaritmoSigmoidal(potencial)));
	}

	public static double tangenteSigmoidal(double potencial){
		return ((2*(Math.pow(Math.E, -potencial))) / Math.pow((1.0 + (Math.pow(Math.E, -potencial))), 2));
	}

	public static double tangenteHiperbolica(double potencial){
		return (1 - Math.pow(Activacion.tangenteHiperbolica(potencial), 2));
	}
}