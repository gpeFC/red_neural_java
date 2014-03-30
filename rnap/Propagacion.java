/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Propagacion

public class Propagacion{
	private double umbral;
	private double[][] pesos;
	private double[] entradas;
	private double propagacion;

	public Propagacion(double[] entradas, double[][] pesos){
		this.pesos = pesos;
		this.entradas = entradas;
	}

	public double obtenerPropagacion(){
		return propagacion;
	}

	public void propagacionSimpleDiscreta(){
	}

	public void propagacionSimpleContinua(){
	}

	public void propagacionMulticapaSinRetropropagacion(){
	}

	public void propagacionMulticapaConRetropropagacion(){
	}
}