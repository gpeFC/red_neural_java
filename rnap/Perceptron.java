/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Perceptron

public class Perceptron{
	private double[] entradas;  // Entradas presinpaticas de la neurona.
	private double[][] pesos;   // Pesos de las conexiones sinapticas.
	private double umbral;      // Bias de la neurona.
	private double propagacion; // Propagacion sinaptica de la neurona.
	private double activacion;  // Activacion sinaptica de la neurona.
	private double salida;      // Salida postsinaptica de la neurona.

	public Perceptron(double[] entradas, double[][] pesos, double umbral){
		this.umbral = umbral;
		this.pesos = pesos;
		this.entradas = entradas;
	}

	public void establecerEntradas(double[] entradas){
		this.entradas = entradas;
	}

	public void establecerPesos(double[][] pesos){
		this.pesos = pesos;
	}

	public double[] obtenerEntradas(){
		return this.entradas;
	}

	public double[][] obtenerPesos(){
		return this.pesos;
	}

	public double obtenerUmbral(){
		return this.umbral;
	}

	public double obtenerPropagacion(){
		return this.propagacion;
	}

	public double obtenerActivacion(){
		return this.activacion;
	}

	public double obtenerSalida(){
		return this.salida;
	}

	public void calcularPropagacion(){
	}

	public void calcularActivacion(){
	}

	public void calcularSalida(){
	}
}