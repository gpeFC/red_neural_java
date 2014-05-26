/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Entrenamiento

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

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

	public static void algoritmoPerceptron(double[] salidasPatrones, double[][] entradasPatrones, RedNeuronal rnap){
		int epocas=0, seguir=0;
		double error, alpha=0.0, salidas[];
		boolean fin = true;
		Scanner entrada = new Scanner(System.in);
		Random aleatorio = new Random();
		ArrayList<CapaNeuronal> redNeuronal = rnap.obtenerPerceptron();
		CapaNeuronal perceptron = redNeuronal.get(0);
		salidas = new double[salidasPatrones.length];
		do{
			alpha = aleatorio.nextDouble();
		}while(alpha == 0.0);
		perceptron.establecerAlphas(alpha);
		System.out.printf("\nAntes de Entrenar...");
		perceptron.imprimirDatos();
		System.out.printf("\n\n");
		while(fin){
			fin = false;
			for(int i=0; i<entradasPatrones.length; i++){
				error = 0.0;
				perceptron.establecerEntrada(entradasPatrones[i]);
				perceptron.calcularSalidas();
				salidas = perceptron.obtenerSalidas();
				for(int j=0; j<salidas.length; j++){
					if(salidas[j] != salidasPatrones[i]){
						error = salidasPatrones[i] - salidas[j];
						System.out.printf("\nError actual(%d): (%f) - (%f) := (%f)\n", i, salidasPatrones[i], salidas[j], error);
						perceptron.actualizarUmbrales(j, error);
						perceptron.actualizarPesos(j, error);
						fin = true;
					}
				}
			}
			System.out.printf("\nEpoca: %d", epocas);
			perceptron.imprimirDatos();
			System.out.printf("\n\n");
			epocas++;
		}
	}

	public static void algoritmoRetropropagacion(int epocas, double error, double[] salidasPatrones, double[][] entradasPatrones, ArrayList<CapaNeuronal> perceptron){
		int iteraciones=0;
		double salidas[];
		Scanner entrada = new Scanner(System.in);
		salidas = new double[salidasPatrones.length];
		if(epocas>0 && error==0.0){
			CapaNeuronal capa;
			while(iteraciones < epocas){
				for(int i=0; i<salidasPatrones.length; i++){
					for(int j=0; j<perceptron.size(); j++){
						capa = perceptron.get(j);
						capa.establecerEntrada(entradasPatrones[i][j]);
					}
				}

				iteraciones++;
			}
		}
		else if(epocas==0 && error>0.0){
		}
		else if(epocas>0 && error>0.0){
		}
	}

	public void algoritmoMomento(){}

	public void algoritmoSilvaAlmeida(){}

	public void algoritmoDeltaBarDelta(){}
}
