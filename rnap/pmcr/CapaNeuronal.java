/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: CapaNeuronal

public class CapaNeuronal{
	private double[] salidas;
	private double[] entradas;
	private Perceptron[] neuronas;

	public CapaNeuronal(int numNeurs, int numArgs){
		this.entradas = new double[numArgs];
		for(int i=0; i<numArgs; i++){
			this.entradas[i] = 0.0;
		}
		this.salidas = new double[numNeurs];
		this.neuronas = new Perceptron[numNeurs];
		for(int i=0; i<numNeurs; i++){
			this.salidas[i] = 0.0;
			this.neuronas[i] = new Perceptron(numArgs);
		}
	}

	public void establecerEntradas(double[] entradas){
		this.entradas = entradas;
	}

	public void establecerSalidas(){
		for(int i=0; i<this.salidas.length; i++){
			this.salidas[i] = this.neuronas[i].obtenerSalida();
		}
	}

	public double[] obtenerSalidas(){
		return this.salidas;
	}
}
