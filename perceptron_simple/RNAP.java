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
		int opcion=-1, num_neu, num_patrones, num_valores, funcion, epocas, errores;
		double epsilon, clase, salidas[],  patron[][], entradas[][];
		String tipo_red, tipo_entrada;
		Perceptron neurona;

		do{
			System.out.printf("\n\n\tRed Neuronal Artificial Perceptron...\n\n");
			System.out.println("0) Salir de la aplicacion.");
			System.out.println("1) Perceptron Simple.");
			System.out.printf("\n\tTipo de Red: ");
			opcion = entrada.nextInt();

			if(opcion == 1){
				System.out.printf("\nNumero de patrones de entrenamiento: ");
				num_patrones = entrada.nextInt();

				System.out.printf("\nNumero de valores por patron: ");
				num_valores = entrada.nextInt();

				salidas = new double[num_patrones];
				entradas = new double[num_patrones][num_valores];

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
					epsilon = (Math.random()*10 + 1)/10.0;
				}while(epsilon == 0.0);

				neurona = new Perceptron(num_patrones, num_valores, epsilon);

				epocas = 0;
				do{
					errores = 0;
					System.out.printf("\n<DATOS>...\n");
					for(int j=0; j<num_patrones; j++){
						neurona.calcularSalida(j, entradas);
						neurona.establecerError(salidas[j] - neurona.obtenerSalidaCalculada());
						System.out.printf("\nNeurona.");
						System.out.printf("\n\tEpoca actual: %d", epocas);
						System.out.printf("\n\tError de la red: %f", neurona.obtenerError());
						System.out.printf("\n\tBias de la neurona: %f", neurona.obtenerBias());
						System.out.printf("\n\tTasa de aprendizaje: %f", neurona.obtenerEpsilon());
						System.out.printf("\n\tPesos: ");
						neurona.imprimePesos();
						System.out.println();
						neurona.actualizarPesos(j, entradas);
						neurona.actualizarBias();
						if(neurona.obtenerError() != 0.0)
							errores++;
					}
					epocas++;
				}while(errores != 0);

				System.out.printf("\n<DATOS FINALES>...\n");
				System.out.printf("\n\tEpocas de entrenamiento: %d", epocas);
				System.out.printf("\nNeurona.");
				System.out.printf("\n\tBias de la neurona: %f", neurona.obtenerBias());
				System.out.printf("\n\tTasa de aprendizaje: %f", neurona.obtenerEpsilon());
				System.out.printf("\n\tPesos: ");
				neurona.imprimePesos();
				System.out.println();
			

				System.out.printf("\nIngresar patrones para clasificar.");
				System.out.printf("\nNumero de patrones: ");
				num_patrones = entrada.nextInt();
				patron = new double[num_patrones][num_valores];
				for(int i=0;i<num_patrones;i++){
					for(int j=0;j<num_valores;j++){
						System.out.printf("\nPatron[%d]-valor[%d]: ", i ,j);
						patron[i][j] = (double)(entrada.nextDouble());
					}
				}

				for(int i=0; i<num_patrones; i++){
					neurona.calcularSalida(i, patron);
					System.out.printf("\n\nClase del patron(%d): %.1f.\n", i, neurona.obtenerSalidaCalculada());
				}
			}

		}while(opcion != 0);
	}
}
