/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: RNAP

import java.util.Scanner;

public class RNAP{
	public static void main(String[] args){
		int opcion, num_capas=0, num_patrones=0, num_argumentos=0, red, epocas=0, error=0;
		int fin_red, config=0, num_neuronas[], funcion=0, funcion_salida=0, funcion_ocultas=0;
		int[] funcion_capa;
		boolean fin;
		double salidas[], entradas[][];
		Scanner entrada = new Scanner(System.in);
		RedNeuronal perceptron;

		do{
			System.out.printf("\n\n\tRed Neuronal Artificial Perceptron...\n\n");
			System.out.println("0) Salir de la aplicacion.");
			System.out.println("1) Perceptron Simple.");
			System.out.println("2) Perceptron Multicapa.");
			System.out.printf("\n\tTipo de Red: ");
			opcion = entrada.nextInt();

			if(opcion == 1){
			}
			else if(opcion == 2){

				System.out.printf("\nNumero total de capas de la red(Ocultas y capa de salida): ");
				num_capas = entrada.nextInt();
				num_neuronas = new int[num_capas];
				for(int i=0; i<num_capas; i++){
					if(i == num_capas-1){
						System.out.printf("\nNumero de neuronas en la capa de salida: ");
						num_neuronas[i] = entrada.nextInt();
					}
					else{
						System.out.printf("\nNumero de neuronas en la capa oculta [%d]: ", i);
						num_neuronas[i] = entrada.nextInt();
					}
				}
				funcion_capa = new int[num_capas];

				System.out.printf("\nIndique la configuracion para las funciones de activacion.\n");
				System.out.println("1) Una misma funcion para todas las neuronas de la red.");
				System.out.println("2) Una funcion para la capa de salida y otra para las capas ocultas.");
				System.out.println("3) Una funcion diferente para cada capa.");
				System.out.printf("\n\tConfiguracion: ");
				config = entrada.nextInt();

				System.out.println("1) Logaritmo Sigmoidal.");
				System.out.println("2) Tangente Sigmoidal.");
				System.out.println("3) Tangente Hiperbolica.");
				if(config == 1){
					System.out.printf("\n\tFuncion de activacion a utilizar: ");
					funcion = entrada.nextInt();
				}
				else if(config == 2){
					System.out.printf("\n\tFuncion de activacion a utilizar para la capa de salida: ");
					funcion_salida = entrada.nextInt();
					System.out.printf("\n\tFuncion de activacion a utilizar para las capas ocultas: ");
					funcion_ocultas = entrada.nextInt();
				}
				else if(config == 3){
					for(int i=0; i<num_capas; i++){
						if(i == num_capas-1){
							System.out.printf("\nFuncion de activacion a utilizar en la capa de salida: ");
							funcion_capa[i] = entrada.nextInt();
						}
						else{
							System.out.printf("\nFuncion de activacion a utilizar en la capa oculta [%d]: ", i);
							funcion_capa[i] = entrada.nextInt();
						}
					}
				}

				System.out.printf("\nIndique la(s) condicion(es) para finalizar el entrenamiento de la red.\n");
				System.out.println("1) Establecer un numero maxmimo de epocas(iteraciones) de entrenamiento.");
				System.out.println("2) Establecer un minimo aceptable del error.");
				System.out.println("3) Establecer un maximo de epocas y un minimo aceptable del error.");
				System.out.printf("\n\tCondicion(es): ");
				fin_red = entrada.nextInt();

				if(fin_red == 1){
					System.out.printf("\nTotal de epocas de entrenamiento: ");
					epocas = entrada.nextInt();
				}
				else if(fin_red == 2){
					System.out.printf("\nMinimo aceptable del error, en porcentaje %%: ");
					error = entrada.nextInt();
				}
				else if(fin_red == 3){
					System.out.printf("\nTotal de epocas de entrenamiento: ");
					epocas = entrada.nextInt();
					System.out.printf("\nMinimo aceptable del error, en porcentaje %%: ");
					error = entrada.nextInt();
				}

				System.out.printf("\nNumero de patrones de entrenamiento: ");
				num_patrones = entrada.nextInt();

				System.out.printf("\nNumero de valores por patron: ");
				num_argumentos = entrada.nextInt();

				perceptron = new RedNeuronal(epocas, num_patrones, num_argumentos, num_capas, num_neuronas);
				System.out.printf("\nEpocas(%d), Error(%d), NumPatrs(%d), NumArgs(%d), NumCapas(%d).", 
								  epocas, error, num_patrones, num_argumentos, num_capas);

				if(config == 1)
					perceptron.establecerConfiguracionActivacion(funcion, num_neuronas);
				else if(config == 2)
					perceptron.establecerConfiguracionActivacion(funcion_salida, funcion_ocultas, num_neuronas);
				else if(config == 3)
					perceptron.establecerConfiguracionActivacion(funcion_capa, num_neuronas);

				System.out.printf("\nEstablecer patrones Entrada-Salida para el entrenamiento.\n");

				salidas = new double[num_patrones];
				entradas = new double[num_patrones][num_argumentos];
				for(int i=0; i<num_patrones; i++){
					for(int j=0; j<num_argumentos; j++){
						System.out.printf("\nPatron[%d]->Valor[%d]: ", i, j);
						entradas[i][j] = (double)(entrada.nextDouble());
					}
					System.out.printf("\nPatron[%d]->Salida: ", i);
					salidas[i] = (double)(entrada.nextDouble());
				}

				System.out.printf("\n\tInicio del entrenamiento...\n");
				for(int i=0; i<num_patrones; i++){
					fin = true;
					epocas = 0;
					do{
						System.out.printf("\nContinua");
						perceptron.establecerErrorMinimo(error, i);
						perceptron.realizarPropagacion(i, num_neuronas);
						if(perceptron.obtenerErrorCalculado() <= perceptron.obtenerErrorMinimo())
							fin = false;
						else
							perceptron.realizarRetroPropagacion(num_neuronas);
						epocas++;
					}while(fin);
					System.out.printf("\nEpoca: %d", epocas);
				}
				System.out.printf("\n\tFin del entrenamiento...\n");
			}
		}while(opcion != 0);
	}
}
