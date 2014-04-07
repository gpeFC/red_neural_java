/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: RNAP

import java.util.Scanner;

public class RNAP{
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		int opcion=-1, num_neuronas, num_patrones, num_valores, funcion;
		double bias, epsilon, salidas[], pesos[], prueba[], entradas[][];
		String tipo_red, tipo_entrada;
		Perceptron neurona;

		do{
			System.out.printf("\n\n\tRed Neuronal Artificial Perceptron...\n\n");
			System.out.println("1) Perceptron Simple.");
			System.out.println("2) Perceptron Multicapa.");
			System.out.printf("\n\tTipo de Red: ");
			opcion = entrada.nextInt();

			if(opcion == 1){
				tipo_red = "simple";

				System.out.println("1) Escalon Binario(Hardlim).");
				System.out.println("2) Escalon Bipolar(Hardlims).");
				System.out.printf("\n\tFuncion de activacion: ");
				opcion = entrada.nextInt();
				if(opcion == 1)
					funcion = 1;
				else
					funcion = 2;

				System.out.println("1) Teclado.");
				System.out.println("2) Archivo.).");
				System.out.printf("\n\tEntrada de datos: ");
				opcion = entrada.nextInt();
				if(opcion == 1)
					tipo_entrada = "teclado";
				else
					tipo_entrada = "archivo";

				//System.out.printf("\nNumero de neuronas de la red: ");
				//num_neuronas = 1;

				//neuronas = new Perceptron[num_neuronas];


				System.out.printf("\nNumero de patrones de entrenamiento: ");
				num_patrones = entrada.nextInt();

				System.out.printf("\nNumero de valores por patron: ");
				num_valores = entrada.nextInt();

				salidas = new double[num_patrones];
				pesos = new double[num_valores];
				entradas = new double[num_patrones][num_valores];

				for(int i=0; i<num_valores; i++){
					do{
						pesos[i] = (Math.random()*10 + 1)/10.0;	// Inicializacion aleatoria de los pesos.
					}while(pesos[i]==0.0);
				}

				System.out.printf("\nIngresar datos para el entrenamiento.");	
				for(int i=0;i<num_patrones;i++){
					for(int j=0;j<num_valores;j++){
						System.out.printf("\nPatron[%d]-valor[%d]: ", i ,j);
						entradas[i][j] = (double)(entrada.nextDouble());
					}
					System.out.printf("\nSalida asociada-Patron[%d]: ", i);
					salidas[i] = (double)(entrada.nextDouble());
				}

				do{
					bias = (Math.random()*10 + 1)/10.0;
					epsilon = (Math.random()*10 + 1)/10.0;
				}while(bias == 0.0 || epsilon == 0.0);

				//for(int i=0;i<num_neuronas;i++)
				neurona = new Perceptron(num_patrones, num_valores, bias, epsilon, salidas, pesos, entradas);

				do{
					neurona.inicializarErrores();
					System.out.printf("\n<DATOS>...\n");
					for(int i=0; i<num_patrones; i++){
						neurona.calcularSalida(i);
						neurona.establecerError(neurona.obtenerSalidaEstablecida(i) - neurona.obtenerSalidaCalculada());
						System.out.printf("\nNeurona.");
						System.out.printf("\n\tEpoca actual: %d", neurona.obtenerEpocas());
						System.out.printf("\n\tError de la red: %f", neurona.obtenerError());
						System.out.printf("\n\tBias de la neurona: %f", neurona.obtenerBias());
						System.out.printf("\n\tTasa de aprendizaje: %f", neurona.obtenerEpsilon());
						System.out.printf("\n\tPesos: ");
						neurona.imprimePesos();
						System.out.println();
						neurona.actualizarPesos(i);
						neurona.actualizarBias();
						if(neurona.obtenerError() != 0.0)
							neurona.actualizarErrores();
					}
					neurona.actualizarEpocas();
				}while(neurona.obtenerErrores() != 0);

				System.out.printf("\n<DATOS FINALES>...\n");
				System.out.printf("\nNeurona.");
				System.out.printf("\n\tEpoca de entrenamiento: %d", neurona.obtenerEpocas());
				System.out.printf("\n\tBias de la neurona: %f", neurona.obtenerBias());
				System.out.printf("\n\tTasa de aprendizaje: %f", neurona.obtenerEpsilon());
				System.out.printf("\n\tPesos: ");
				neurona.imprimePesos();
				System.out.println();
			}

		}while(opcion != 0);
	}
}
