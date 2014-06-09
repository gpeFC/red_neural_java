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
		int opcion=0, numCapas=1, numPatrones=0, numArgumentos=0, red, epocas=0;
		int configActivacion=0, configAlpha=0, numNeuronas=0;
		byte config=0, funcion=0, funcionSalida=0, funcionOcultas=0;
		byte[] funcionesCapa, numNeuronasCapa;
		boolean continuar=true, buscar=true;
		double alpha=0.0, error=0.0, salidas[], entradas[][];
		String eco=null, nombre=null, topologia=null, configAlphas=null, configFunciones=null;
		Scanner entrada = new Scanner(System.in);
		CapaNeuronal capaNeural=null;
		RedNeuronal rnap=null;
		ArrayList<CapaNeuronal> redNeural=null;
		ArrayList<RedNeuronal> redesNeuronalesPerceptron = new ArrayList<RedNeuronal>();
		
		while(continuar){
			limpiarPantalla();
			System.out.printf("\n\n\tRed Neuronal Artificial Perceptron...\n\n");
			System.out.println("1) Crear perceptron.");
			System.out.println("2) Mostrar perceptron.");
			System.out.println("3) Aplicar perceptron.");
			System.out.println("4) Entrenar perceptron.");
			System.out.println("5) Modificar perceptron.");
			System.out.println("6) Salir de la aplicacion.");
			do{
				try{
					System.out.printf("\n\tTarea a realizar: ");
					opcion = entrada.nextInt();
					break;
				}
				catch(InputMismatchException excepcion){
					entrada.nextLine();
				}
			}while(true);
			if(opcion == 1){ // CREAR PERCEPTRON ================================================================================
				limpiarPantalla();
				entrada.nextLine();
				do{
					nombre = null;
					buscar = false;
					System.out.printf("\nEscribe un nombre para la red: ");
					nombre = entrada.nextLine();
					nombre = nombre.toUpperCase();
					if(redesNeuronalesPerceptron.size() != 0){
						for(int i=0; i<redesNeuronalesPerceptron.size(); i++){
							rnap = redesNeuronalesPerceptron.get(i);
							if(nombre.equals(rnap.obtenerNombrePerceptron())){
								System.out.printf("\n\tNombre existente.\n");
								buscar = true;
								break;
							}
						}
					}
				}while(buscar);
				System.out.println();
				opcion = 0;
				while(opcion<1 || opcion>2){
					System.out.println("1) Perceptron Simple.");
					System.out.println("2) Perceptron Multicapa.");
					do{
						try{
							System.out.printf("\n\tArquitectura: ");
							opcion = entrada.nextInt();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
				}
				if(opcion == 1){ // PERCEPTRON SIMPLE -----------------------------------------------------------------
					topologia = "SIMPLE";
					configAlphas = "TDA/RED";
					configFunciones = "FUNCION/RED";
					numNeuronasCapa = new byte[1];
					do{
						try{
							System.out.printf("\nNumero de neuronas: ");
							numNeuronasCapa[0] = entrada.nextByte();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					System.out.println();
					System.out.println("1) Escalon Binario(Hardlim).");
					System.out.println("2) Escalon Bipolar(Hardlims).");
					do{
						try{
							System.out.printf("\n\tFuncion de activacion: ");
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
							System.out.printf("\nNumero de argumentos por patron: ");
							numArgumentos = entrada.nextInt();
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);

					rnap = new RedNeuronal(numArgumentos, numCapas, numNeuronasCapa, nombre, topologia);
					rnap.establecerConfiguracionAlphas((byte)1);
					rnap.establecerConfiguracionFunciones((byte)(funcion+4), (byte)(numNeuronasCapa[0]));
					rnap.establecerConfiguracionAlphas(configAlphas);
					rnap.establecerConfiguracionFunciones(configFunciones);
					redesNeuronalesPerceptron.add(rnap);
				}
				else if(opcion == 2){ // PERCEPTRON MULTICAPA ---------------------------------------------------------
					topologia = "MULTICAPA";
					do{
						try{
							System.out.printf("\nNumero total de capas en la red(Ocultas y capa de salida): ");
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
					rnap = new RedNeuronal(numArgumentos, numCapas, numNeuronasCapa, nombre, topologia);
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
					configAlphas = configuracionAlphas((byte)config);
					rnap.establecerConfiguracionAlphas(configAlphas);
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
					if(config == 1){
						funcionesRP();
						do{
							try{
								System.out.printf("\n\tFuncion de activacion: ");
								funcion = entrada.nextByte();
								break;
							}
							catch(InputMismatchException excepcion){
								entrada.nextLine();
							}
						}while(true);
						configFunciones = "FUNCION/RED";
						rnap.establecerConfiguracionFunciones(configFunciones);
						rnap.establecerConfiguracionFunciones(funcion, numNeuronasCapa);
					}
					else if(config == 2){
						funcionesRP();
						do{
							try{
								System.out.printf("\n\tFuncion de activacion capa de salida: ");
								funcionSalida = entrada.nextByte();
								break;
							}
							catch(InputMismatchException excepcion){
								entrada.nextLine();
							}
						}while(true);
						do{
							try{
								System.out.printf("\n\tFuncion de activacion capas ocultas: ");
								funcionOcultas = entrada.nextByte();
								break;
							}
							catch(InputMismatchException excepcion){
								entrada.nextLine();
							}
						}while(true);
						configFunciones = "FUNCION/OCULTA/SALIDA";
						rnap.establecerConfiguracionFunciones(configFunciones);
						rnap.establecerConfiguracionFunciones(funcionSalida, funcionOcultas, numNeuronasCapa);
					}
					else if(config == 3){
						funcionesRP();
						for(int i=0; i<numCapas; i++){
							funcionesCapa = new byte[numNeuronasCapa[i]];
							if(i == numCapas-1){
								do{
									try{
										System.out.printf("\nFuncion de activacion capa de salida: ");
										funcion = entrada.nextByte();
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
										System.out.printf("\nFuncion de activacion capa oculta [%d]: ", i);
										funcion = entrada.nextByte();
										break;
									}
									catch(InputMismatchException excepcion){
										entrada.nextLine();
									}
								}while(true);
							}
							for(int j=0; j<numNeuronasCapa[i]; j++)
								funcionesCapa[j] = funcion;
							configFunciones = "FUNCION/CAPA";
							rnap.establecerConfiguracionFunciones(configFunciones);
							rnap.establecerConfiguracionFunciones(i, funcionesCapa);
						}
					}
					else if(config == 4){
						for(int i=0; i<numCapas; i++){
							funcionesCapa = new byte[numNeuronasCapa[i]];
							for(int j=0; j<funcionesCapa.length; j++){
								if(i == numCapas-1){
									funcionesRP();
									do{
										try{
											System.out.printf("\nFuncion de activacion neurona [%d] capa de salida: ", j);
											funcionesCapa[j] = entrada.nextByte();
											break;
										}
										catch(InputMismatchException excepcion){
											entrada.nextLine();
										}
									}while(true);
								}
								else{
									funcionesRP();
									do{
										try{
											System.out.printf("\nFuncion de activacion neurona [%d] capa oculta [%d]: ", j, i);
											funcionesCapa[j] = entrada.nextByte();
											break;
										}
										catch(InputMismatchException excepcion){
											entrada.nextLine();
										}
									}while(true);
								}
							}
							configFunciones = "FUNCION/NEURONA";
							rnap.establecerConfiguracionFunciones(configFunciones);
							rnap.establecerConfiguracionFunciones(i, funcionesCapa);
						}
					}
					redesNeuronalesPerceptron.add(rnap);
				}
				System.out.printf("\nPresiona <Enter> para continuar...");
				entrada.nextLine();
				eco = entrada.nextLine();
			}
			else if(opcion == 2){ // MOSTRAR PERCEPTRON =========================================================================
				limpiarPantalla();
				if(redesNeuronalesPerceptron.size() == 0){
					System.out.printf("\n\tNo hay redes existentes para mostrar.\n");
					entrada.nextLine();
				}
				else{
					buscar = true;
					System.out.printf("\n\tRedes existentes.\n");
					for(int i=0; i<redesNeuronalesPerceptron.size(); i++){
						rnap = redesNeuronalesPerceptron.get(i);
						System.out.printf("{%s} ", rnap.obtenerNombrePerceptron());
					}
					System.out.printf("\nNombre de la red a mostrar: ");
					entrada.nextLine();
					nombre = entrada.nextLine();
					nombre = nombre.toUpperCase();
					for(int i=0; i<redesNeuronalesPerceptron.size() && buscar; i++){
						rnap = redesNeuronalesPerceptron.get(i);
						if(nombre.equals(rnap.obtenerNombrePerceptron()))
							buscar = false;
					}
					if(buscar)
						System.out.printf("\n\tNombre de red incorrecto.\n");
					else
						rnap.mostrarDatosPerceptron();
				}
				nombre = null;
				System.out.printf("\nPresiona <Enter> para continuar...");
				eco = entrada.nextLine();
			}
			else if(opcion == 3){ // APLICAR PERCEPTRON =========================================================================
				if(redesNeuronalesPerceptron.size() == 0){
					System.out.printf("\n\tNo hay redes existentes para aplicar.\n");
				}
				else{
				}
				System.out.printf("\nPresiona <Enter> para continuar...");
				entrada.nextLine();
				eco = entrada.nextLine();
			}
			else if(opcion == 4){ // ENTRENAR PERCEPTRON ========================================================================
				if(redesNeuronalesPerceptron.size() == 0){
					System.out.printf("\n\tNo hay redes existentes para entrenar.\n");
				}
				else{
				}
				System.out.printf("\nPresiona <Enter> para continuar...");
				entrada.nextLine();
				eco = entrada.nextLine();
			}
			else if(opcion == 5){ // MODIFICAR PERCEPTRON =======================================================================
				if(redesNeuronalesPerceptron.size() == 0){
					System.out.printf("\n\tNo hay redes existentes para modificar.\n");
				}
				else{
				}
				System.out.printf("\nPresiona <Enter> para continuar...");
				entrada.nextLine();
				eco = entrada.nextLine();
			}
			else if(opcion == 6){ // SALIR ======================================================================================
				continuar = false;
			}
		}

		/*
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
				}
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
					rnap.establecerConfiguracionFunciones(funcionSalida, funcionOcultas, numNeuronasCapa);
				}
				else if(configActivacion == 3){
					for(int i=0; i<numCapas; i++){
						funcionesCapa = new byte[numNeuronasCapa[i]];
						if(i == numCapas-1){
							do{
								try{
									System.out.printf("\nFuncion de activacion a utilizar en la capa de salida: ");
									funcion = entrada.nextByte();
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
									funcion = entrada.nextByte();
									break;
								}
								catch(InputMismatchException excepcion){
									entrada.nextLine();
								}
							}while(true);
						}
						for(int j=0; j<numNeuronasCapa[i]; j++)
							funcionesCapa[j] = funcion;
						rnap.establecerConfiguracionFunciones(i, funcionesCapa);
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
				System.out.printf("\nA continuacion ingrese los datos para el entrenamiento.\n");
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
				//Entrenamiento.algoritmoRetropropagacion(epocas, error, salidas, entradas, perceptron);
				System.out.printf("\nFin del entrenamiento...\n");
			}
		}while(opcion != 0);
		*/
	}

	public static String configuracionAlphas(byte indice){
		String alpha = new String();
		if(indice == 1)
			alpha = "TDA/RED";
		else if(indice == 2)
			alpha = "TDA/CAPA";
		else if(indice == 3)
			alpha = "TDA/NEURONA";
		return alpha;
	}

	public static void funcionesRP(){
		System.out.println("1) Identidad Lineal.");
		System.out.println("2) Logaritmo Sigmoidal.");
		System.out.println("3) Tangente Sigmoidal.");
		System.out.println("4) Tangente Hiperbolica.");
	}

	public static void limpiarPantalla(){
		for(int i=0; i<100; i++)
			System.out.println();
	}
}
