/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: RedNeuronal

import java.util.ArrayList;

public class RedNeuronal{
	private double[] salidasPatrones;
	private double[][] entradasPatrones;
	ArrayList<CapaNeuronal> perceptron;

	public RedNeuronal(){
	}

	public void establecerSalidasPatrones(double[] salidasPatrones){
		this.salidasPatrones = salidasPatrones;
	}

	public void establecerEntradasPatrones(double[][] entradasPatrones){
		this.entradasPatrones = entradasPatrones;
	}
}
