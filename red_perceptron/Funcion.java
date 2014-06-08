/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Funcion

/**
* Clase final donde se definen las 'Funciones de Activacion' mas comunes que se
* utilizan en las Redes Neuronales Artificiales mas usadas, asi como tambien las
* respectivas derivadas de aquellas que son diferenciables.
* @author Emanuel GP
*/
public final class Funcion{
	/** Funciones de Activacion. */
	public static double activacion(byte funcion, double potencial){
		double activacion=0.0;
		if(funcion == 1){		/** Funcion de activacion(lineal): Identidad */
			activacion = potencial;
		}
		else if(funcion == 2){	/** Funcion de activacion(sigmoidea): Logaritmo Sigmoidal */
			activacion = 1.0 / (1.0 + (Math.pow(Math.E, -potencial)));
		}
		else if(funcion == 3){	/** Funcion de activacion(sigmoidea): Tangente Sigmoidal */
			activacion = (2.0 / (1.0 + (Math.pow(Math.E, -potencial)))) - 1.0;
		}
		else if(funcion == 4){	/** Funcion de activacion(sigmoidea): Tangente Hiperbolica */
			activacion = ((Math.pow(Math.E, potencial)) - ((Math.pow(Math.E, -potencial)))) / ((Math.pow(Math.E, potencial)) + ((Math.pow(Math.E, -potencial))));
		}
		else if(funcion == 5){	/** Funcion de activacion(escalonada): Escalon Binario */
			if(potencial > 0.0)
				activacion = 1.0;
			else
				activacion = 0.0;
		}
		else if(funcion == 6){	/** Funcion de activacion(escalonada): Escalon Bipolar */
			if(potencial > 0.0)
				activacion = 1.0;
			else
				activacion = -1.0;
		}
		return activacion;
	}

	/** Derivadas de Funciones de Activacion. */
	public static double derivada(byte funcion, double potencial){
		double derivada=0.0;
		if(funcion == 1){		/** Derivada 'Identidad Lineal' */
			derivada = 1.0;
		}
		else if(funcion == 2){	/** Derivada 'Logaritmo Sigmoidal' */
			derivada = Funcion.activacion((byte)4, potencial) * (1 - Funcion.activacion((byte)4, potencial));
		}
		else if(funcion == 3){	/** Derivada 'Tangente Sigmoidal' */
			derivada = (2*(Math.pow(Math.E, -potencial))) / Math.pow((1.0 + (Math.pow(Math.E, -potencial))), 2);
		}
		else if(funcion == 4){	/** Derivada 'Tangente Hiperbolica' */
			derivada = 1 - Math.pow(Funcion.activacion((byte)6, potencial), 2);
		}
		return derivada;
	}
}