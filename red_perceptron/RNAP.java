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
		int opcion, numCapas=1, numPatrones=0, numArgumentos=0, red, epocas=0;
		int configActivacion=0, configAlpha=0, numNeuronas=0;
		byte config=0, funcion=0, funcionSalida=0, funcionOcultas=0;
		byte[] funcionCapa, funcionesCapa, numNeuronasCapa;
		boolean fin;
		double alpha=0.0, error=0.0, salidas[], entradas[][];
		Scanner entrada = new Scanner(System.in);
		RedNeuronal rnap;

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
			if(opcion == 1){ //===================================================================================================
				numNeuronasCapa = new byte[1];
				do{
					try{
						System.out.printf("\nNumero de neuronas en la capa: ");
						numNeuronasCapa[0] = entrada.nextByte();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
				System.out.println("1) Escalon Binario(Hardlim).");
				System.out.println("2) Escalon Bipolar(Hardlims).");
				do{
					try{
						System.out.printf("\n\tFuncion de activacion a utilizar: ");
						funcion = entrada.nextByte();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
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

				rnap = new RedNeuronal(numArgumentos, numCapas, numNeuronasCapa); 
				rnap.establecerConfiguracionFunciones((byte)(funcion+1), (byte)(numNeuronasCapa[0])); 

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
				Entrenamiento.algoritmoPerceptron(salidas, entradas, rnap);
				System.out.printf("\nFin del entrenamiento...\n");
			}
			else if(opcion == 2){ //===============================================================================================
				
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
				numNeuronasCapa = new byte[numCapas];
				for(int i=0; i<numCapas; i++){
					if(i == numCapas-1){
						do{
							try{
								System.out.printf("\nNumero de neuronas en la capa de salida: ");
								numNeuronasCapa[i] = entrada.nextByte();
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
								numNeuronasCapa[i] = entrada.nextByte();
								break;
							}
							catch(InputMismatchException excepcion){
								entrada.nextLine();
							}
						}while(true);
					}
				}
				funcionCapa = new byte[numCapas];

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

				rnap = new RedNeuronal(numArgumentos, numCapas, numNeuronasCapa); 

				System.out.printf("\nIndique la configuracion para la tasa de aprendizaje de la red.\n");
				System.out.println("1) Una misma tasa de aprendizaje para toda la red.");
				System.out.println("2) Una tasa de aprendizaje distinta por capa.");
				System.out.println("3) Una tasa de aprendizaje distinta por neurona.");
				do{
					try{
						System.out.printf("\n\tConfiguracion: ");
						config = entrada.nextByte();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);

				rnap.establecerConfiguracionAlphas(config);
			
				System.out.printf("\nIndique la configuracion para las funciones de activacion.\n");
				System.out.println("1) Una misma funcion de activacion para toda la red.");
				System.out.println("2) Una funcion de activacion para la capa de salida y otra para las capas ocultas.");
				System.out.println("3) Una funcion de activacion diferente por capa.");
				System.out.println("4) Una funcion de activacion diferente por neurona.");
				do{
					try{
						System.out.printf("\n\tConfiguracion: ");
						config = entrada.nextByte();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
				System.out.println("1) Funcion Identidad.");
				System.out.println("2) Logaritmo Sigmoidal.");
				System.out.println("3) Tangente Sigmoidal.");
				System.out.println("4) Tangente Hiperbolica.");
				if(config == 1){
					do{
						try{
							System.out.printf("\n\tFuncion de activacion a utilizar: ");
							funcion = entrada.nextByte();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					rnap.establecerConfiguracionFunciones(funcion, numNeuronasCapa);
				}}/*
				else if(configActivacion == 2){
					do{
						try{
							System.out.printf("\n\tFuncion de activacion a utilizar para la capa de salida: ");
							funcionSalida = entrada.nextByte();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					do{
						try{
							System.out.printf("\n\tFuncion de activacion a utilizar para las capas ocultas: ");
							funcionOcultas = entrada.nextByte();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					CapaNeuronal capa;
					for(int i=0; i<numCapas; i++){
						capa = perceptron.get(i);
						funcionCapa = new byte[numNeuronasCapa[i]];
						if(i == numCapas-1){
							for(int j=0; j<numNeuronasCapa[i]; j++)
								funcionCapa[j] = funcionSalida;
							capa.establecerFunciones(funcionCapa);
						}
						else{
							for(int j=0; j<numNeuronasCapa[i]; j++)
								funcionCapa[j] = funcionOcultas;
							capa.establecerFunciones(funcionCapa);
						}
					}
				}
				else if(configActivacion == 3){
					CapaNeuronal capa;
					for(int i=0; i<numCapas; i++){
						capa = perceptron.get(i);
						funcionesCapa = new byte[numNeuronasCapa[i]];
						if(i == numCapas-1){
							do{
								try{
									System.out.printf("\nFuncion de activacion a utilizar en la capa de salida: ");
									funcionCapa[i] = entrada.nextByte();
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
									funcionCapa[i] = entrada.nextByte();
									break;
								}
								catch(InputMismatchException excepcion){
									entrada.nextLine();
								}
							}while(true);
						}
						for(int j=0; j<numNeuronasCapa[i]; j++)
							funcionesCapa[j] = funcionCapa[i];
						capa.establecerFunciones(funcionesCapa);
					}
				}
				else if(configActivacion == 4){}

				System.out.printf("\nIndique la(s) condicion(es) para finalizar el entrenamiento de la red.\n");
				System.out.println("1) Establecer un numero maxmimo de epocas(iteraciones) de entrenamiento.");
				System.out.println("2) Establecer un minimo aceptable del error.");
				System.out.println("3) Establecer un numero maximo de epocas y un minimo aceptable del error.");
				do{
					try{
						System.out.printf("\n\tCondicion(es): ");
						config = entrada.nextByte();
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
				if(config == 1){
					do{
						try{
							System.out.printf("\nMaximo de epocas de entrenamiento: ");
							epocas = entrada.nextInt();
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
							System.out.printf("\nMinimo aceptable del error, en porcentaje %%: ");
							error = entrada.nextDouble();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
				}
				else if(config == 3){
					do{
						try{
							System.out.printf("\nMaximo de epocas de entrenamiento: ");
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
							error = entrada.nextDouble();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					
				}
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
				Entrenamiento.algoritmoRetropropagacion(epocas, error, salidas, entradas, perceptron);
				System.out.printf("\nFin del entrenamiento...\n");
			}*/
		}while(opcion != 0);
	}
}
