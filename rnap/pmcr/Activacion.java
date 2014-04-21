/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Activacion

public final class Activacion{

	public static double activacionEscalonBinario(double potencial){
		return 0.0;
	}

	public static double activacionEscalonBipolar(double potencial){
		return 0.0;
	}

	public static double activacionSigmoidalBinaria(double potencial){
		double activacion = 1.0 / (1.0 + (Math.pow(Math.E, -potencial)));
		return 0.0;
	}

	public static double activacionTangenteSigmoidal(double potencial){
		double activacion = (2.0 / (1.0 + (Math.pow(Math.E, -potencial)))) - 1.0;
		return 0.0;
	}

	public static double activacionTangenteHiperbolica(double potencial){
		double activacion = ((Math.pow(Math.E, potencial)) - ((Math.pow(Math.E, -potencial)))) / ((Math.pow(Math.E, potencial)) + ((Math.pow(Math.E, -potencial))));
		return 0.0;
	}
}