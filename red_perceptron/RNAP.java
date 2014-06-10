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
		int opcion=0, numCapas=0, numPatrones=0, numArgumentos=0, red;
		int configActivacion=0, configAlpha=0;
		byte config=0, funcion=0, funcionSalida=0, funcionOcultas=0, numNeuronas=0;
		byte[] funcionesCapa, numNeuronasCapa;
		long epocas=0;
		boolean continuar=true, buscar=true, errorValid=true;
		double alpha=0.0, error=0.0, salidas[], entradas[][];
		String eco=null, nombre=null, funcionPercetron=null, topologia=null, configAlphas=null, configFunciones=null;
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
			System.out.println("5) Eliminar perceptron.");
			System.out.println("6) Modificar perceptron.");
			System.out.println("7) Salir de la aplicacion.");
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
				System.out.println("1) Perceptron Simple.");
				System.out.println("2) Perceptron Multicapa.");
				opcion = 0;
				do{
					try{
						while(opcion<1 || opcion>2){
							System.out.printf("\n\tArquitectura: ");
							opcion = entrada.nextInt();
						}
						break;
					}
					catch(InputMismatchException excepcion){
						entrada.nextLine();
					}
				}while(true);
			
				if(opcion == 1){ // PERCEPTRON SIMPLE -----------------------------------------------------------------
					topologia = "SIMPLE";
					configAlphas = "TDA/RED";
					configFunciones = "FUNCION/RED";
					numNeuronasCapa = new byte[1];
					numCapas = 1;
					numNeuronas = 0;
					do{
						try{
							while(numNeuronas < 1){
								System.out.printf("\nNumero de neuronas: ");
								numNeuronas = entrada.nextByte();
								numNeuronasCapa[0] = numNeuronas;
							}
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					numArgumentos = 0;
					do{
						try{
							while(numArgumentos < 1){
								System.out.printf("\nNumero de argumentos por entrada: ");
								numArgumentos = entrada.nextInt();
							}
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					rnap = new RedNeuronal(numArgumentos, numCapas, numNeuronasCapa, nombre, topologia);

					rnap.establecerConfiguracionAlphas((byte)1);
					configuracionFuncion(numNeuronasCapa[0], rnap);
					rnap.establecerConfiguracionAlphas(configAlphas);
					rnap.establecerConfiguracionFunciones(configFunciones);

					redesNeuronalesPerceptron.add(rnap);
				}
				else if(opcion == 2){ // PERCEPTRON MULTICAPA ---------------------------------------------------------
					topologia = "MULTICAPA";
					numCapas = 0;
					do{
						try{
							while(numCapas < 2){
								System.out.printf("\nNumero total de capas en la red(Ocultas y capa de salida): ");
								numCapas = entrada.nextInt();
							}
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					numNeuronasCapa = new byte[numCapas];
					for(int i=0; i<numCapas; i++){
						numNeuronas = 0;
						if(i == numCapas-1){
							do{
								try{
									while(numNeuronas < 1){
										System.out.printf("\nNumero de neuronas en la capa de salida: ");
										numNeuronas = entrada.nextByte();
										numNeuronasCapa[i] = numNeuronas;
									}
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
									while(numNeuronas < 1){
										System.out.printf("\nNumero de neuronas en la capa oculta [%d]: ", i+1);
										numNeuronas = entrada.nextByte();
										numNeuronasCapa[i] = numNeuronas;
									}
									break;
								}
								catch(InputMismatchException excepcion){
									entrada.nextLine();
								}
							}while(true);
						}
					}
					numArgumentos = 0;
					do{
						try{
							while(numArgumentos < 1){
								System.out.printf("\nNumero de argumentos por entrada: ");
								numArgumentos = entrada.nextInt();
							}
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					rnap = new RedNeuronal(numArgumentos, numCapas, numNeuronasCapa, nombre, topologia);

					configuracionAlphas(rnap);
					configuracionFuncionesMulticapa(numNeuronasCapa, numCapas, rnap);

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
				System.out.printf("\n\nPresiona <Enter> para continuar...");
				eco = entrada.nextLine();
			}
			else if(opcion == 3){ // APLICAR PERCEPTRON =========================================================================
				limpiarPantalla();
				entradas = null;
				salidas = null;
				if(redesNeuronalesPerceptron.size() == 0){
					System.out.printf("\n\tNo hay redes existentes para aplicar.\n");
				}
				else{
					buscar = true;
					System.out.printf("\n\tRedes existentes.\n");
					for(int i=0; i<redesNeuronalesPerceptron.size(); i++){
						rnap = redesNeuronalesPerceptron.get(i);
						System.out.printf("{%s} ", rnap.obtenerNombrePerceptron());
					}
					System.out.printf("\nNombre de la red a aplicar: ");
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
					else{
						System.out.println();
						numPatrones = 0;
						do{
							try{
								while(numPatrones < 1){
									System.out.printf("\nNumero de entradas: ");
									numPatrones = entrada.nextInt();
								}
								break;
							}
							catch(InputMismatchException excepcion){
								entrada.nextLine();
							}
						}while(true);
						numArgumentos = rnap.obtenerPerceptron().get(0).obtenerNeuronas()[0].obtenerPesos().length;
						salidas = new double[numPatrones];
						entradas = new double[numPatrones][numArgumentos];
						for(int i=0; i<numPatrones; i++){
							for(int j=0; j<numArgumentos; j++){
								do{
									try{
										System.out.printf("\nPatron[%d]->Valor[%d]: ", i+1, j+1);
										entradas[i][j] = (double)(entrada.nextDouble());
										break;
									}
									catch(InputMismatchException excepcion){
										entrada.nextLine();
									}
								}while(true);	
							}
						}
						salidas = rnap.aplicarRed(entradas);
						System.out.println();
						for(int i=0; i<salidas.length; i++)
							System.out.printf("\nSalida-Entrada[%d]: %f", i+1, salidas[i]);
					}
				}
				System.out.printf("\nPresiona <Enter> para continuar...");
				entrada.nextLine();
				eco = entrada.nextLine();
			}
			else if(opcion == 4){ // ENTRENAR PERCEPTRON ========================================================================
				limpiarPantalla();
				if(redesNeuronalesPerceptron.size() == 0){
					System.out.printf("\n\tNo hay redes existentes para entrenar.\n");
					entrada.nextLine();
				}
				else{
					buscar = true;
					System.out.printf("\n\tRedes existentes.\n");
					for(int i=0; i<redesNeuronalesPerceptron.size(); i++){
						rnap = redesNeuronalesPerceptron.get(i);
						System.out.printf("{%s} ", rnap.obtenerNombrePerceptron());
					}
					System.out.printf("\nNombre de la red a entrenar: ");
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
					else{
						if(rnap.obtenerTopologiaPerceptron().equals("SIMPLE")){
							epocas = 0;
							salidas = null;
							entradas = null;
							System.out.println();
							do{
								try{
									while(epocas < 1){
										System.out.printf("\nNumero maximo de epocas de entrenamiento: ");
										epocas = entrada.nextLong();
									}
									break;
								}
								catch(InputMismatchException excepcion){
									entrada.nextLine();
								}
							}while(true);
							System.out.println();
							System.out.println("1) Ingresar patrones por teclado.");
							System.out.println("2) Ingresar patrones por archivo.");
							opcion = 0;
							do{
								try{
									while(opcion < 1 || opcion > 2){
										System.out.printf("\nOpcion para ingresar patrones de entrenamiento: ");
										opcion = entrada.nextInt();
									}
									break;
								}
								catch(InputMismatchException excepcion){
									entrada.nextLine();
								}
							}while(true);
							if(opcion == 1){
								System.out.println();
								numPatrones = 0;
								do{
									try{
										while(numPatrones < 1){
											System.out.printf("\nNumero de patrones de entrenamiento: ");
											numPatrones = entrada.nextInt();
										}
										break;
									}
									catch(InputMismatchException excepcion){
										entrada.nextLine();
									}
								}while(true);
								numArgumentos = rnap.obtenerPerceptron().get(0).obtenerNeuronas()[0].obtenerPesos().length;
								salidas = new double[numPatrones];
								entradas = new double[numPatrones][numArgumentos];
								for(int i=0; i<numPatrones; i++){
									for(int j=0; j<numArgumentos; j++){
										do{
											try{
												System.out.printf("\nPatron[%d]->Valor[%d]: ", i+1, j+1);
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
											do{
												System.out.printf("\nPatron[%d]->Salida: ", i+1);
												salidas[i] = (double)(entrada.nextDouble());
												if(rnap.obtenerFuncionPerceptron().equals("HARDLIM")){
													if(salidas[i]==0 || salidas[i]==1)
														break;
													else
														System.out.println("\tValor incorrecto.");
												}
												else{
													if(salidas[i]==-1 || salidas[i]==1)
														break;
													else
														System.out.println("\tValor incorrecto.");
												}
											}while(true);
											break;
										}
										catch(InputMismatchException excepcion){
											entrada.nextLine();
										}
									}while(true);	
								}
							}
							else{}
							System.out.printf("\n\tInicio del entrenamiento...\n");
							Entrenamiento.algoritmoPerceptron(epocas, salidas, entradas, rnap);
							System.out.printf("\n\tFin del entrenamiento...\n");
						}
						else{
							error = 0.0;
							epocas = 0;
							salidas = null;
							entradas = null;
							System.out.printf("\nIndique la condicion para finalizar el entrenamiento de la red.\n");
							System.out.println("1) Establecer un numero maxmimo de epocas(iteraciones) de entrenamiento.");
							System.out.println("2) Establecer un numero maximo de epocas y un minimo aceptable del error.");
							opcion = 0;
							do{
								try{
									while(opcion<1 || opcion>2){
										System.out.printf("\n\tCondicion: ");
										opcion = entrada.nextInt();
									}
									break;
								}
								catch(InputMismatchException excepcion){
									entrada.nextLine();
								}
							}while(true);
							if(opcion == 1){
								epocas = 0;
								do{
									try{
										while(epocas < 1){
											System.out.printf("\nMaximo de epocas de entrenamiento: ");
											epocas = entrada.nextLong();
										}
										break;
									}
									catch(InputMismatchException excepcion){
										entrada.nextLine();
									}
								}while(true);
							}
							else if(opcion == 2){
								epocas = 0;
								do{
									try{
										while(epocas < 1){
											System.out.printf("\nMaximo de epocas de entrenamiento: ");
											epocas = entrada.nextLong();
										}
										break;
									}
									catch(InputMismatchException excepcion){
										entrada.nextLine();
									}
								}while(true);
								error = -1.0;
								do{
									try{
										while(error < 0.0){
											System.out.printf("\nMinimo aceptable del error(ejemp. 5 para un minimo de 5%%): ");
											error = entrada.nextDouble();
										}
										break;
									}
									catch(InputMismatchException excepcion){
										entrada.nextLine();
									}
								}while(true);	
							}
							System.out.println();
							System.out.println("1) Ingresar patrones por teclado.");
							System.out.println("2) Ingresar patrones por archivo.");
							opcion = 0;
							do{
								try{
									while(opcion < 1 || opcion > 2){
										System.out.printf("\nOpcion para ingresar patrones de entrenamiento: ");
										opcion = entrada.nextInt();
									}
									break;
								}
								catch(InputMismatchException excepcion){
									entrada.nextLine();
								}
							}while(true);
							if(opcion == 1){
								System.out.println();
								numPatrones = 0;
								do{
									try{
										while(numPatrones < 1){
											System.out.printf("\nNumero de patrones de entrenamiento: ");
											numPatrones = entrada.nextInt();
										}
										break;
									}
									catch(InputMismatchException excepcion){
										entrada.nextLine();
									}
								}while(true);
								numArgumentos = rnap.obtenerPerceptron().get(0).obtenerNeuronas()[0].obtenerPesos().length;
								salidas = new double[numPatrones];
								entradas = new double[numPatrones][numArgumentos];
								for(int i=0; i<numPatrones; i++){
									for(int j=0; j<numArgumentos; j++){
										do{
											try{
												System.out.printf("\nPatron[%d]->Valor[%d]: ", i+1, j+1);
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
											do{
												System.out.printf("\nPatron[%d]->Salida: ", i+1);
												salidas[i] = (double)(entrada.nextDouble());
												break;
											}while(true);
											break;
										}
										catch(InputMismatchException excepcion){
											entrada.nextLine();
										}
									}while(true);	
								}
							}
							else{}
							System.out.printf("\nIndique el algoritmo de entrenamiento a utilizar.\n");
							System.out.println("1) Retropropagacion.");
							System.out.println("2) Retropropagacion/Momento.");
							opcion = 0;
							do{
								try{
									while(opcion<1 || opcion>2){
										System.out.printf("\n\tAlgoritmo: ");
										opcion = entrada.nextInt();
									}
									break;
								}
								catch(InputMismatchException excepcion){
									entrada.nextLine();
								}
							}while(true);
							if(opcion == 1){
								System.out.printf("\n\tInicio del entrenamiento...\n");
								Entrenamiento.algoritmoRetropropagacion(epocas, error, salidas, entradas, rnap);
								System.out.printf("\n\tFin del entrenamiento...\n");
							}
							else{
								System.out.printf("\n\tInicio del entrenamiento...\n");
								Entrenamiento.algoritmoRetropropagacionMomento(epocas, error, salidas, entradas, rnap);
								System.out.printf("\n\tFin del entrenamiento...\n");
							}
						}
					}
				}
				System.out.printf("\nPresiona <Enter> para continuar...");
				entrada.nextLine();
				eco = entrada.nextLine();
			}
			else if(opcion == 5){ // ELIMINAR PERCEPTRON ========================================================================
				limpiarPantalla();
				if(redesNeuronalesPerceptron.size() == 0){
					System.out.printf("\n\tNo hay redes existentes para eliminar.\n");
					entrada.nextLine();
				}
				else{
					System.out.println();
					System.out.println("1) Eliminar una red especifica.");
					System.out.println("2) Eliminar todas las redes existentes.");
					opcion = 0;
					do{
						try{
							while(opcion < 1 || opcion > 2){
								System.out.printf("\nOpcion de borrado: ");
								opcion = entrada.nextInt();
							}
							break;
						}
						catch(InputMismatchException excepcion){
							entrada.nextLine();
						}
					}while(true);
					if(opcion == 1){
						buscar = true;
						System.out.printf("\n\tRedes existentes.\n");
						for(int i=0; i<redesNeuronalesPerceptron.size(); i++){
							rnap = redesNeuronalesPerceptron.get(i);
							System.out.printf("{%s} ", rnap.obtenerNombrePerceptron());
						}
						System.out.printf("\nNombre de la red a eliminar: ");
						entrada.nextLine();
						nombre = entrada.nextLine();
						nombre = nombre.toUpperCase();
						for(int i=0; i<redesNeuronalesPerceptron.size() && buscar; i++){
							rnap = redesNeuronalesPerceptron.get(i);
							if(nombre.equals(rnap.obtenerNombrePerceptron())){
								buscar = false;
								redesNeuronalesPerceptron.remove(i);
								System.out.printf("\nRed eliminada.\n");
								break;
							}
						}
						if(buscar){
							System.out.printf("\n\tNombre de red incorrecto.\n");
						}
					}
					else{
						entrada.nextLine();
						redesNeuronalesPerceptron.clear();
						if(redesNeuronalesPerceptron.size() == 0)
							System.out.printf("\nRedes eliminadas.\n");
					}
				}
				System.out.printf("\nPresiona <Enter> para continuar...");
				//entrada.nextLine();
				eco = entrada.nextLine();
			}
			else if(opcion == 6){ // MODIFICAR PERCEPTRON =======================================================================
				limpiarPantalla();
				if(redesNeuronalesPerceptron.size() == 0){
					System.out.printf("\n\tNo hay redes existentes para modificar.\n");
					entrada.nextLine();
				}
				else{
					buscar = true;
					System.out.printf("\n\tRedes existentes.\n");
					for(int i=0; i<redesNeuronalesPerceptron.size(); i++){
						rnap = redesNeuronalesPerceptron.get(i);
						System.out.printf("{%s} ", rnap.obtenerNombrePerceptron());
					}
					System.out.printf("\nNombre de la red a modificar: ");
					entrada.nextLine();
					nombre = entrada.nextLine();
					nombre = nombre.toUpperCase();
					for(int i=0; i<redesNeuronalesPerceptron.size() && buscar; i++){
						rnap = redesNeuronalesPerceptron.get(i);
						if(nombre.equals(rnap.obtenerNombrePerceptron()))
							buscar = false;
					}
					if(buscar){
						System.out.printf("\n\tNombre de red incorrecto.\n");
					}
					else{
						topologia = rnap.obtenerTopologiaPerceptron();
						if(topologia.equals("SIMPLE")){
							System.out.println();
							System.out.println("1) Modificar nombre.");
							System.out.println("2) Modificar funcion de activacion.");
							opcion = 0;
							do{
								try{
									while(opcion < 1 || opcion > 2){
										System.out.printf("\nParametro a modificar: ");
										opcion = entrada.nextInt();
									}
									break;
								}
								catch(InputMismatchException excepcion){
									entrada.nextLine();
								}
							}while(true);
							entrada.nextLine();
							if(opcion == 1){
								nombre = null;
								System.out.printf("\nEscribe el nuevo nombre para la red: ");
								nombre = entrada.nextLine();
								nombre = nombre.toUpperCase();
								rnap.establecerNombrePerceptron(nombre);
							}
							else{
								configuracionFuncion((byte)rnap.obtenerPerceptron().get(0).obtenerFunciones().length, rnap);
							}
						}
						else{
							System.out.println();
							System.out.println("1) Modificar nombre.");
							System.out.println("2) Modificar configuracion de la tasa de aprendizaje.");
							System.out.println("3) Modificar configuracion de las funciones de activacion.");
							opcion = 0;
							do{
								try{
									while(opcion < 1 || opcion > 3){
										System.out.printf("\nParametro a modificar: ");
										opcion = entrada.nextInt();
									}
									break;
								}
								catch(InputMismatchException excepcion){
									entrada.nextLine();
								}
							}while(true);
							entrada.nextLine();
							if(opcion == 1){
								nombre = null;
								System.out.printf("\nEscribe el nuevo nombre para la red: ");
								nombre = entrada.nextLine();
								nombre = nombre.toUpperCase();
								rnap.establecerNombrePerceptron(nombre);
							}
							else if(opcion == 2){
								configuracionAlphas(rnap);
							}
							else{
								numCapas = rnap.obtenerPerceptron().size();
								numNeuronasCapa = new byte[numCapas];
								for(int i=0; i<numCapas; i++){
									numNeuronasCapa[i] = (byte)rnap.obtenerPerceptron().get(i).obtenerFunciones().length;
								}
								configuracionFuncionesMulticapa(numNeuronasCapa, numCapas, rnap);
							}
						}
					}
				}
				System.out.printf("\nPresiona <Enter> para continuar...");
				//entrada.nextLine();
				eco = entrada.nextLine();
			}
			else if(opcion == 7){ // SALIR ======================================================================================
				continuar = false;
			}
		}
	}

	public static void configuracionAlphas(RedNeuronal rnap){
		byte config=0;
		String configAlphas=null;
		Scanner entrada = new Scanner(System.in);
		System.out.printf("\nIndique la configuracion para la tasa de aprendizaje de la red.\n");
		System.out.println("1) Una misma tasa de aprendizaje para toda la red.");
		System.out.println("2) Una tasa de aprendizaje distinta por capa.");
		System.out.println("3) Una tasa de aprendizaje distinta por neurona.");
		do{
			try{
				while(config<1 || config>3){
					System.out.printf("\n\tConfiguracion: ");
					config = entrada.nextByte();
				}
				break;
			}
			catch(InputMismatchException excepcion){
				entrada.nextLine();
			}
		}while(true);
		if(config == 1)
			configAlphas = "TDA/RED";
		else if(config == 2)
			configAlphas = "TDA/CAPA";
		else if(config == 3)
			configAlphas = "TDA/NEURONA";
		rnap.establecerConfiguracionAlphas(configAlphas);
		rnap.establecerConfiguracionAlphas(config);
	}

	public static void configuracionFuncionesMulticapa(byte[] numNeuronasCapa, int numCapas, RedNeuronal rnap){
		byte config=0, funcion=0, funcionSalida=0, funcionOcultas=0, funcionesCapa[]=null;
		String configFunciones=null;
		Scanner entrada = new Scanner(System.in);
		
		System.out.printf("\nIndique la configuracion para las funciones de activacion.\n");
		System.out.println("1) Una misma funcion de activacion para toda la red.");
		System.out.println("2) Una funcion de activacion para la capa de salida y otra para las capas ocultas.");
		System.out.println("3) Una funcion de activacion diferente por capa.");
		System.out.println("4) Una funcion de activacion diferente por neurona.");
		do{
			try{
				while(config<1 || config>4){
					System.out.printf("\n\tConfiguracion: ");
					config = entrada.nextByte();
				}
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
					while(funcion<1 || funcion>4){
						System.out.printf("\n\tFuncion de activacion: ");
						funcion = entrada.nextByte();
					}
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
					while(funcionSalida<1 || funcionSalida>4){
						System.out.printf("\n\tFuncion de activacion capa de salida: ");
						funcionSalida = entrada.nextByte();
					}
					break;
				}
				catch(InputMismatchException excepcion){
					entrada.nextLine();
				}
			}while(true);
			do{
				try{
					while(funcionOcultas<1 || funcionOcultas>4){
						System.out.printf("\n\tFuncion de activacion capas ocultas: ");
						funcionOcultas = entrada.nextByte();
					}
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
				funcion = 0;
				funcionesCapa = new byte[numNeuronasCapa[i]];
				if(i == numCapas-1){
					do{
						try{
							while(funcion<1 || funcion>4){
								System.out.printf("\nFuncion de activacion capa de salida: ");
								funcion = entrada.nextByte();
							}
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
							while(funcion<1 || funcion>4){
								System.out.printf("\nFuncion de activacion capa oculta [%d]: ", i+1);
								funcion = entrada.nextByte();
							}
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
				funcion = 0;
				funcionesCapa = new byte[numNeuronasCapa[i]];
				for(int j=0; j<funcionesCapa.length; j++){
					if(i == numCapas-1){
						funcionesRP();
						do{
							try{
								while(funcion<1 || funcion>4){
									System.out.printf("\nFuncion de activacion neurona [%d] capa de salida: ", j+1);
									funcion = entrada.nextByte();
									funcionesCapa[j] = funcion;
								}
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
								while(funcion<1 || funcion>4){
									System.out.printf("\nFuncion de activacion neurona [%d] capa oculta [%d]: ", j+1, i+1);
									funcion = entrada.nextByte();
									funcionesCapa[j] = funcion;
								}
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
	}

	public static void configuracionFuncion(byte numNeuronas, RedNeuronal rnap){
		byte funcion=0;
		Scanner entrada = new Scanner(System.in);
		System.out.println();
		System.out.println("1) Escalon Binario(Hardlim).");
		System.out.println("2) Escalon Bipolar(Hardlims).");
		do{
			try{
				while(funcion<1 || funcion>2){
					System.out.printf("\n\tFuncion de activacion: ");
					funcion = entrada.nextByte();
				}
				break;
			}
			catch(InputMismatchException excepcion){
				entrada.nextLine();
			}
		}while(true);
		if(funcion == 1)
			rnap.establecerFuncionPerceptron("HARDLIM");
		else
			rnap.establecerFuncionPerceptron("HARDLIMS");
		rnap.establecerConfiguracionFunciones((byte)(funcion+4), (byte)(numNeuronas));
	}

	public static void funcionesRP(){
		System.out.println();
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
