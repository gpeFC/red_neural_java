/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: PerceptronSimpleDiscreto

public class PerceptronSimpleDiscreto{
	private int[] entradas;  // Entradas presinpaticas de la neurona.
	private int[] salidas;      // Salida postsinaptica de la neurona.
	private double umbral;      // Bias de la neurona.
	private double activacion;  // Activacion sinaptica de la neurona.
	private double epsilon;  // Tasa de aprendizaje de la neurona.
	private double propagacion; // Propagacion sinaptica de la neurona.
	private double[][] pesos;   // Pesos de las conexiones sinapticas.
	
	

	public PerceptronSimpleDiscreto(int numPatrones){
		this.umbral = 0.0;
		this.epsilon = 0.0;
		this.entradas = new int[numPatrones];
		this.salidas = new int[numPatrones];
		for(int i=0; i<numPatrones; i++){
			this.entradas[i] = 0;
			this.salidas[i] = 0;
		}
		this.pesos = new double[numPatrones][numPatrones];
		for(int i=0; i<numPatrones; i++)
			for(int j=0; j<numPatrones; j++)
				this.pesos[i][j] = 0.0;
	}

	public void establecerEntradas(int[] entradas){
		this.entradas = entradas;
	}

	public void establecerSalidas(int[] salidas){
		this.salidas = salidas;
	}

	public void establecerUmbral(){
	}

	public void establecerPesos(){
	}

	public void calcularPropagacion(){
	}

	public void calcularActivacion(){
	}
}
