/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Entrenamiento

import java.util.Scanner;

/**
* Clase definida para crear objetos 'entrenamiento' que aplica el algoritmo de 
* entrenamiento definido para la RNA indicada.
* @author Emanuel GP
*/
public final class Entrenamiento{
	private int epocas;
	private double alpha;
	private double errorMinimo;
	private double errorCalculado;
	private double[] salidasPatrones;
	private double[][] entradasPatrones;

	public static void perceptronSimple(double[] salidasPatrones, double[][] entradasPatrones, CapaNeuronal perceptron){
		int epocas=0, seguir=0;
		double error, alpha=0.0, salidas[];
		boolean fin = true;
		Scanner entrada = new Scanner(System.in);
		salidas = new double[salidasPatrones.length];
		do{
			alpha = (Math.random()*10 + 1)/10.0;
		}while(alpha == 0.0);
		perceptron.establecerAlphas(alpha);

		while(fin){
			fin = false;
			for(int i=0; i<entradasPatrones.length; i++){
				error = 0.0;
				perceptron.establecerEntrada(entradasPatrones[i]);
				perceptron.calcularSalidas();
				salidas = perceptron.obtenerSalidas();
				for(int j=0; j<salidas.length; j++)
					if(salidas[j] != salidasPatrones[i])
						error = error + (salidasPatrones[i] - salidas[j]);
				System.out.printf("\nError: %f", error);
				error = error / (double)salidas.length;
				System.out.printf("\nError actual: %f", error);
				if(error != 0.0){
					fin = true;
					perceptron.actualizarUmbrales(error);
					perceptron.actualizarPesos(error);
				}
			}
			System.out.printf("\nEpoca: %d", epocas);
			perceptron.imprimirDatos();
			System.out.printf("\n\n");
			seguir = entrada.nextInt();
			epocas++;
		}
		/*
		do{
			System.out.printf("\nEpoca: %d", epocas);
			perceptron.imprimirDatos();
			fin = false;
			for(int i=0; i<entradasPatrones.length; i++){
				error = 0.0;
				perceptron.establecerEntrada(entradasPatrones[i]);
				perceptron.calcularSalidas();
				salidas = perceptron.obtenerSalidas();
				for(int j=0; j<salidas.length; j++)
					if(salidas[j] != salidasPatrones[i])
						error = error + (salidasPatrones[i] - salidas[j]);
				error = error / (double)salidas.length;
				if(error != 0.0){
					System.out.printf("\nPatron(%d)-Error: %f\n", i, error);
					fin = true;
					perceptron.actualizarUmbrales(error);
					perceptron.actualizarPesos(error);
				}
				else{
				}
				seguir = entrada.nextInt();
			}
			epocas++;
		}while(fin);
		*/
	}

	public void algoritmoRetropropagacion(){}

	public void algoritmoMomento(){}

	public void algoritmoSilvaAlmeida(){}

	public void algoritmoDeltaBarDelta(){}
}
