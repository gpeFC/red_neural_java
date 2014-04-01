/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: PerceptronSimpleDiscreto

public class PerceptronSimpleDiscreto{
	private int[] entradas;     // Entradas presinpaticas de la neurona.
	private int[] salidas;      // Salida postsinaptica de la neurona.
	private double umbral;      // Bias de la neurona.
	private double epsilon;     // Tasa de aprendizaje de la neurona.
	private double activacion;  // Activacion sinaptica de la neurona.
	private double propagacion; // Propagacion sinaptica de la neurona.
	private double[] pesos;   // Pesos de las conexiones sinapticas.
	
	

	public PerceptronSimpleDiscreto(int dimension, int[] entradas, int[] salidas){
		do{
			this.umbral = (Math.random()*10 + 1)/10.0;
			this.epsilon = (Math.random()*10 + 1)/10.0;
		}while(this.umbral==0.0 || this.epsilon==0.0);
		this.entradas = new int[dimension];
		this.entradas = entradas;
		this.salidas = new int[dimension];
		this.salidas = salidas;
		this.pesos = new double[dimension];
		for(int i=0; i<dimension; i++){
			do{
				this.pesos[i] = (Math.random()*10 + 1)/10.0;
			}while(this.pesos[i]==0.0);
		}
		this.propagacion = 0.0;
		this.activacion = 0.0;
	}

	public void actualizarPesos(){
	}

	public void calcularPropagacion(){
	}

	public void calcularActivacion(){
	}

	public double obtenerPropagacion(){
		return this.propagacion;
	}

	public double obtenerActivacion(){
		return this.activacion;
	}
}
