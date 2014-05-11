/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Activacion

/**
* Clase final donde se definen las 'Funciones de Activacion' mas comunes que se
* utilizan en las Redes Neuronales Artificiales mas usadas.
* @author Emanuel GP
*/
public final class Activacion{
	/** Funcion de activacion(lineal): Identidad */
	public static double identidad(double potencial){
		double activacion = potencial;
		return activacion;
	}

	/** Funcion de activacion(escalonada): Escalon Binario */
	public static double escalonBinario(double potencial){
		if(potencial > 0.0)
			return 1.0;
		else
			return 0.0;
	}

	/** Funcion de activacion(escalonada): Escalon Bipolar */
	public static double escalonBipolar(double potencial){
		if(potencial > 0.0)
			return 1.0;
		else
			return -1.0;
	}

	/** Funcion de activacion(sigmoidea): Logaritmo Sigmoidal */
	public static double logaritmoSigmoidal(double potencial){
		double activacion = 1.0 / (1.0 + (Math.pow(Math.E, -potencial)));
		return activacion;
	}

	/** Funcion de activacion(sigmoidea): Tangente Sigmoidal */
	public static double tangenteSigmoidal(double potencial){
		double activacion = (2.0 / (1.0 + (Math.pow(Math.E, -potencial)))) - 1.0;
		return activacion;
	}

	/** Funcion de activacion(sigmoidea): Tangente Hiperbolica */
	public static double tangenteHiperbolica(double potencial){
		double activacion = ((Math.pow(Math.E, potencial)) - ((Math.pow(Math.E, -potencial)))) / ((Math.pow(Math.E, potencial)) + ((Math.pow(Math.E, -potencial))));
		return activacion;
	}
}