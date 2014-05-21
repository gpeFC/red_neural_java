/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: RNAP

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class RNAP{
	public static void main(String[] args){
		int opcion, numCapas=0, numPatrones=0, numArgumentos=0, red, epocas=0, error=0;
		int finRed, config=0, funcion=0, funcionSalida=0, funcionOcultas=0, numNeuronas=0;;
		byte[] funcionCapa, numNeuronasCapa;
		boolean fin;
		double alpha=0.0, salidas[], entradas[][];
		Scanner entrada = new Scanner(System.in);

		do{
			System.out.printf("\n\n\tRed Neuronal Artificial Perceptron...\n\n");
			System.out.println("0) Salir de la aplicacion.");
			System.out.println("1) Perceptron Simple.");
			System.out.println("2) Perceptron Multicapa.");
			do{
				try{
					System.out.printf("\n\tTipo de Red: ");
					opcion = entrada.nextInt();
					break;
				}
				catch(InputMismatchException excepcion){
					entrada.nextLine();
				}
			}while(true);
			if(opcion == 1){
				CapaNeuronal perceptron;
				do{
					try{
						System.out.printf("\nNumero de neuronas en la capa: ");
						numNeuronas = entrada.nextInt();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
				funcionCapa = new byte[numNeuronas];
				System.out.println("1) Escalon Binario(Hardlim).");
				System.out.println("2) Escalon Bipolar(Hardlims).");
				do{
					try{
						System.out.printf("\n\tFuncion de activacion a utilizar: ");
						funcion = entrada.nextInt();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
				if(funcion == 1)
					for(int i=0; i<numNeuronas; i++)
						funcionCapa[i] = 2;
				else if(funcion == 2)
					for(int i=0; i<numNeuronas; i++)
						funcionCapa[i] = 3;

				do{
					try{
						System.out.printf("\nNumero de patrones de entrenamiento: ");
						numPatrones = entrada.nextInt();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
				do{
					try{
						System.out.printf("\nNumero de valores por patron: ");
						numArgumentos = entrada.nextInt();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);

				perceptron = new CapaNeuronal(numNeuronas, numArgumentos);
				perceptron.establecerFunciones(funcionCapa);

				salidas = new double[numPatrones];
				entradas = new double[numPatrones][numArgumentos];

				for(int i=0; i<numPatrones; i++){
					for(int j=0; j<numArgumentos; j++){
						do{
							try{
								System.out.printf("\nPatron[%d]->Valor[%d]: ", i, j);
								entradas[i][j] = (double)(entrada.nextDouble());
								break;
							}
							catch(InputMismatchException excepcion){
								entrada.nextLine();
							}
						}while(true);
						
					}
					do{
						try{
							System.out.printf("\nPatron[%d]->Salida: ", i);
							salidas[i] = (double)(entrada.nextDouble());
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);	
				}
				System.out.printf("\nInicio del entrenamiento...\n");
				Entrenamiento.algoritmoPerceptron(salidas, entradas, perceptron);
				System.out.printf("\nFin del entrenamiento...\n");

			}/*
			else if(opcion == 2){
				do{
					try{
						System.out.printf("\nNumero total de capas de la red(Ocultas y capa de salida): ");
						numCapas = entrada.nextInt();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
				numNeuronasCapa = new int[numCapas];
				for(int i=0; i<numCapas; i++){
					if(i == numCapas-1){
						do{
							try{
								System.out.printf("\nNumero de neuronas en la capa de salida: ");
								numNeuronasCapa[i] = entrada.nextInt();
								break;
							}
							catch(InputMismatchException excepcion){
								entrada.nextLine();
							}
						}while(true);
						
					}
					else{
						do{
							try{
								System.out.printf("\nNumero de neuronas en la capa oculta [%d]: ", i);
								numNeuronasCapa[i] = entrada.nextInt();
								break;
							}
							catch(InputMismatchException excepcion){
								entrada.nextLine();
							}
						}while(true);
					}
				}
				funcionCapa = new int[numCapas];

				System.out.printf("\nIndique la configuracion para las funciones de activacion.\n");
				System.out.println("1) Una misma funcion para todas las neuronas de la red.");
				System.out.println("2) Una funcion para la capa de salida y otra para las capas ocultas.");
				System.out.println("3) Una funcion diferente para cada capa.");
				do{
					try{
						System.out.printf("\n\tConfiguracion: ");
						config = entrada.nextInt();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
				System.out.println("1) Logaritmo Sigmoidal.");
				System.out.println("2) Tangente Sigmoidal.");
				System.out.println("3) Tangente Hiperbolica.");
				if(config == 1){
					do{
						try{
							System.out.printf("\n\tFuncion de activacion a utilizar: ");
							funcion = entrada.nextInt();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
				}
				else if(config == 2){
					do{
						try{
							System.out.printf("\n\tFuncion de activacion a utilizar para la capa de salida: ");
							funcionSalida = entrada.nextInt();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					do{
						try{
							System.out.printf("\n\tFuncion de activacion a utilizar para las capas ocultas: ");
							funcionOcultas = entrada.nextInt();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
				}
				else if(config == 3){
					for(int i=0; i<numCapas; i++){
						if(i == numCapas-1){
							do{
								try{
									System.out.printf("\nFuncion de activacion a utilizar en la capa de salida: ");
									funcionCapa[i] = entrada.nextInt();
									break;
								}
								catch(InputMismatchException excepcion){
									entrada.nextLine();
								}
							}while(true);
						}
						else{
							do{
								try{
									System.out.printf("\nFuncion de activacion a utilizar en la capa oculta [%d]: ", i);
									funcionCapa[i] = entrada.nextInt();
									break;
								}
								catch(InputMismatchException excepcion){
									entrada.nextLine();
								}
							}while(true);
						}
					}
				}

				System.out.printf("\nIndique la(s) condicion(es) para finalizar el entrenamiento de la red.\n");
				System.out.println("1) Establecer un numero maxmimo de epocas(iteraciones) de entrenamiento.");
				System.out.println("2) Establecer un minimo aceptable del error.");
				System.out.println("3) Establecer un maximo de epocas y un minimo aceptable del error.");
				do{
					try{
						System.out.printf("\n\tCondicion(es): ");
						finRed = entrada.nextInt();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
				

				if(finRed == 1){
					do{
						try{
							System.out.printf("\nTotal de epocas de entrenamiento: ");
							epocas = entrada.nextInt();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					
				}
				else if(finRed == 2){
					do{
						try{
							System.out.printf("\nMinimo aceptable del error, en porcentaje %%: ");
							error = entrada.nextInt();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					
				}
				else if(finRed == 3){
					do{
						try{
							System.out.printf("\nTotal de epocas de entrenamiento: ");
							epocas = entrada.nextInt();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					
					do{
						try{
							System.out.printf("\nMinimo aceptable del error, en porcentaje %%: ");
							error = entrada.nextInt();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					
				}

				do{
					try{
						System.out.printf("\nNumero de patrones de entrenamiento: ");
						numPatrones = entrada.nextInt();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
				

				do{
					try{
						System.out.printf("\nNumero de valores por patron: ");
						numArgumentos = entrada.nextInt();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
				

				perceptron = new RedNeuronal(epocas, numPatrones, numArgumentos, numCapas, numNeuronasCapa);
				System.out.printf("\nEpocas(%d), Error(%d), NumPatrs(%d), NumArgs(%d), NumCapas(%d).", 
								  epocas, error, numPatrones, numArgumentos, numCapas);

				if(config == 1)
					perceptron.establecerConfiguracionActivacion(funcion, numNeuronasCapa);
				else if(config == 2)
					perceptron.establecerConfiguracionActivacion(funcionSalida, funcionOcultas, numNeuronasCapa);
				else if(config == 3)
					perceptron.establecerConfiguracionActivacion(funcionCapa, numNeuronasCapa);

				System.out.printf("\nEstablecer patrones Entrada-Salida para el entrenamiento.\n");

				salidas = new double[numPatrones];
				entradas = new double[numPatrones][numArgumentos];
				for(int i=0; i<numPatrones; i++){
					for(int j=0; j<numArgumentos; j++){
						do{
							try{
								System.out.printf("\nPatron[%d]->Valor[%d]: ", i, j);
								entradas[i][j] = (double)(entrada.nextDouble());
								break;
							}
							catch(InputMismatchException excepcion){
								entrada.nextLine();
							}
						}while(true);
						
					}
					do{
						try{
							System.out.printf("\nPatron[%d]->Salida: ", i);
							salidas[i] = (double)(entrada.nextDouble());
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					
				}

				System.out.printf("\n\tInicio del entrenamiento...\n");
				for(int i=0; i<numPatrones; i++){
					fin = true;
					epocas = 0;
					do{
						System.out.printf("\nContinua");
						perceptron.establecerErrorMinimo(error, i);
						perceptron.realizarPropagacion(i, numNeuronasCapa);
						if(perceptron.obtenerErrorCalculado() <= perceptron.obtenerErrorMinimo())
							fin = false;
						else
							perceptron.realizarRetroPropagacion(numNeuronasCapa);
						epocas++;
					}while(fin);
					System.out.printf("\nEpoca: %d", epocas);
				}
				System.out.printf("\n\tFin del entrenamiento...\n");
			}*/
		}while(opcion != 0);
	}
}
