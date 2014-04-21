/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: RedNeuronal

import java.util.ArrayList;

public class RedNeuronal{
	private int epocas;		// Total de epocas/iteraciones de entrenamiento/aprendizaje.
	private double alpha;
	private double errorLocal;
	private double errorGlobal;
	private double[] salidas;
	private double[][] entradas;
	private ArrayList<CapaNeuronal> capasNeuronales;

	public RedNeuronal(int epocas, int numArgs, int numPatrs, int numNeurs, int numCapas, int[] numNeursCapa, double[] salidas, double[][] entradas){
		this.epocas = epocas;
		do{
			this.alpha = (Math.random()*10 + 1)/10.0;
		}while(this.alpha == 0.0);
		this.error = 0.0;
		this.salidas = new double[numPatrs];
		this.salidas = salidas;
		this.entradas = new double[numPatrs][numArgs];
		this.entradas = entradas;
		this.capaSalida = new Perceptron[numNeurs];
		for(int i=0; i<numNeurs; i++){
			this.capaSalida[i] = new Perceptron(numArgs);
		}
		this.capasNeuronales = new ArrayList<CapaNeuronal>(numCapas);
		//for(int i=0; i<numCapas; i++){
		//	CapaNeuronal capaOculta = new CapaNeuronal();
		//}
	}
}
