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

	public static void algoritmoPerceptron(long epocas, double[] salidasPatrones, double[][] entradasPatrones, RedNeuronal rnap){
		int seguir=0, errores=0, iteraciones=0;
		double error=0.0, alpha=0.0, salidas[];
		boolean fin = true;
		Scanner entrada = new Scanner(System.in);
		ArrayList<CapaNeuronal> redNeuronal = rnap.obtenerPerceptron();
		CapaNeuronal perceptron = redNeuronal.get(0);
		salidas = new double[salidasPatrones.length];
		while(fin){
			errores = 0;
			for(int i=0; i<entradasPatrones.length; i++){
				error = 0.0;
				perceptron.establecerEntrada(entradasPatrones[i]);
				perceptron.calcularSalidas();
				salidas = perceptron.obtenerSalidas();
				for(int j=0; j<salidas.length; j++){
					if(salidas[j] != salidasPatrones[i]){
						errores++;
						error = salidasPatrones[i] - salidas[j];
						perceptron.actualizarUmbrales(j, error);
						perceptron.actualizarPesos(j, error);
					}
				}
			}
			if(errores == 0)
				fin = false;
			else if(iteraciones == epocas-1)
				fin = false;
			else
				fin = true;
			iteraciones++;
		}
		if(errores == 0){
			System.out.printf("\nRed entrenada correctamente.");
			System.out.printf("\nEpocas de entrenamiento: %d.\n", iteraciones+1);
			perceptron.mostrarDatosCapa();
			System.out.printf("\n\n");
		}
		else
			System.out.printf("\nRed no entrenada correctamente.\n");
	}

	public static void algoritmoRetropropagacion(long epocas, double error, double[] salidasPatrones, double[][] entradasPatrones, RedNeuronal rnap){
		int iteraciones=0;
		boolean fin=true, fallo;
		double errorPatron=0.0, errorMinimo=0.0, ecm=0.0, salidas[], errores[];
		CapaNeuronal capa;
		ArrayList<CapaNeuronal> perceptron;
		for(int i=0; i<salidasPatrones.length; i++)
			errorMinimo = errorMinimo + salidasPatrones[i];
		errorMinimo = errorMinimo / ((double)salidasPatrones.length);
		errorMinimo = errorMinimo * (error/100.0);
		errores = new double[salidasPatrones.length];
		while(fin){
			ecm = 0.0;
			for(int i=0; i<salidasPatrones.length; i++){
				fallo = false;
				errorPatron = 0.0;
				rnap.realizarPropagacion(entradasPatrones[i]);
				perceptron = rnap.obtenerPerceptron();
				capa = perceptron.get(perceptron.size()-1);
				salidas = capa.obtenerSalidas();
				for(int j=0; j<salidas.length; j++){
					if(salidas[j] != salidasPatrones[i])
						fallo = true;
					errorPatron = errorPatron + (salidasPatrones[i]-salidas[j]);
				}
				errores[i] = Math.pow((errorPatron / ((double)salidas.length)), 2);
				if(fallo)
					rnap.realizarRetroPropagacion(salidasPatrones[i]);
				rnap.actualizarParametrosNeuronalesRP(1, 0.0);
			}
			for(int i=0; i<errores.length; i++)
				ecm = ecm + errores[i];
			ecm = ecm / ((double)errores.length);
			if(ecm<=errorMinimo || iteraciones==epocas-1)
				fin = false;
			iteraciones++;
		}
		System.out.printf("\nEpocas de entrenamiento:   %d.\n", iteraciones);
		System.out.printf("\nError minimo acordado:     %f.\n", errorMinimo);
		System.out.printf("\nError minimo alcanzado:    %f.\n", ecm);
		rnap.mostrarDatosPerceptron();
		System.out.printf("\n\n");
	}

	public static void algoritmoRetropropagacionMomento(long epocas, double error, double[] salidasPatrones, double[][] entradasPatrones, RedNeuronal rnap){
		int iteraciones=0;
		boolean fin=true, fallo;
		double errorPatron=0.0, errorMinimo=0.0, ecm=0.0, eta=0.0, salidas[], errores[];
		CapaNeuronal capa;
		ArrayList<CapaNeuronal> perceptron;
		do{
			eta = (Math.random()*10 + 1)/10.0;	// Inicialización aleatoria del umbral\bias de la neurona.
		}while(eta == 0.0);
		if(eta > 1.0)
			eta = eta / 2.0;
		for(int i=0; i<salidasPatrones.length; i++)
			errorMinimo = errorMinimo + salidasPatrones[i];
		errorMinimo = errorMinimo / ((double)salidasPatrones.length);
		errorMinimo = errorMinimo * (error/100.0);
		errores = new double[salidasPatrones.length];
		while(fin){
			ecm = 0.0;
			for(int i=0; i<salidasPatrones.length; i++){
				fallo = false;
				errorPatron = 0.0;
				rnap.realizarPropagacion(entradasPatrones[i]);
				perceptron = rnap.obtenerPerceptron();
				capa = perceptron.get(perceptron.size()-1);
				salidas = capa.obtenerSalidas();
				for(int j=0; j<salidas.length; j++){
					if(salidas[j] != salidasPatrones[i])
						fallo = true;
					errorPatron = errorPatron + (salidasPatrones[i]-salidas[j]);
				}
				errores[i] = Math.pow((errorPatron / 2.0), 2);
				if(fallo)
					rnap.realizarRetroPropagacion(salidasPatrones[i]);
				rnap.actualizarParametrosNeuronalesRP(2, eta);
			}
			for(int i=0; i<errores.length; i++)
				ecm = ecm + errores[i];
			ecm = ecm / ((double)errores.length);
			if(ecm<=errorMinimo || iteraciones==epocas-1)
				fin = false;
			iteraciones++;
		}
		System.out.printf("\nEpocas de entrenamiento:   %d.\n", iteraciones);
		System.out.printf("\nError minimo acordado:     %f.\n", errorMinimo);
		System.out.printf("\nError minimo alcanzado:    %f.\n", ecm);
		rnap.mostrarDatosPerceptron();
		System.out.printf("\n\n");
	}
}
