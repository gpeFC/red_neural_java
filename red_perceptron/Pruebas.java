/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Pruebas

public class Pruebas{
	public static void main(String[] args) {
		double[] auxiliar=null;
		double[] ent1 = {0.0, 0.0}, ent2 = {1.0, 0.0}, ent3 = {0.0, 1.0}, ent4 = {1.0, 1.0};
		double[] pesos = {0.301812, 1.065127};
		System.out.printf("\n Propagacion::=> %f \n", Propagacion.sumaPonderada(0.501361, ent1, pesos));
		System.out.printf("\n Activacion::=> %f \n", Funcion.activacion((byte)2, Propagacion.sumaPonderada(0.501361, ent1, pesos)));
		System.out.printf("\n Propagacion::=> %f \n", Propagacion.sumaPonderada(0.501361, ent2, pesos));
		System.out.printf("\n Activacion::=> %f \n", Funcion.activacion((byte)2, Propagacion.sumaPonderada(0.501361, ent2, pesos)));
		System.out.printf("\n Propagacion::=> %f \n", Propagacion.sumaPonderada(0.501361, ent3, pesos));
		System.out.printf("\n Activacion::=> %f \n", Funcion.activacion((byte)2, Propagacion.sumaPonderada(0.501361, ent3, pesos)));
		System.out.printf("\n Propagacion::=> %f \n", Propagacion.sumaPonderada(0.501361, ent4, pesos));
		System.out.printf("\n Activacion::=> %f \n", Funcion.activacion((byte)2, Propagacion.sumaPonderada(0.501361, ent4, pesos)));
		auxiliar = pesos;
		

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


	}
}