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
		double epsilon, clase, salidas[], patron[], entradas[][];
		String tipo_red, tipo_entrada;
		Perceptron neuronas[];

		do{
			System.out.printf("\n\n\tRed Neuronal Artificial Perceptron...\n\n");
			System.out.println("0) Salir de la aplicacion.");
			System.out.println("1) Perceptron Simple.");
			System.out.println("2) Perceptron Multicapa.");
			System.out.printf("\n\tTipo de Red: ");
			opcion = entrada.nextInt();

			if(opcion == 1){
				System.out.printf("\nNumero de neuronas de la red: ");
				num_neu = entrada.nextInt();
				neuronas = new Perceptron[num_neu];

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

				for(int i=0;i<num_neu;i++)
					neuronas[i] = new Perceptron(num_patrones, num_valores, epsilon);

				epocas = 0;
				do{
					errores = 0;
					System.out.printf("\n<DATOS>...\n");
					for(int i=0; i<num_neu; i++){
						for(int j=0; j<num_patrones; j++){
							neuronas[i].calcularSalida(j, entradas);
							neuronas[i].establecerError(salidas[j] - neuronas[i].obtenerSalidaCalculada());
							System.out.printf("\nNeurona(%d).", i);
							System.out.printf("\n\tEpoca actual: %d", epocas);
							System.out.printf("\n\tError de la red: %f", neuronas[i].obtenerError());
							System.out.printf("\n\tBias de la neurona(%d): %f", i, neuronas[i].obtenerBias());
							System.out.printf("\n\tTasa de aprendizaje: %f", neuronas[i].obtenerEpsilon());
							System.out.printf("\n\tPesos: ");
							neuronas[i].imprimePesos();
							System.out.println();
							neuronas[i].actualizarPesos(j, entradas);
							neuronas[i].actualizarBias();
							if(neuronas[i].obtenerError() != 0.0)
								errores++;
						}
					}
					epocas++;
				}while(errores != 0);

				System.out.printf("\n<DATOS FINALES>...\n");
				System.out.printf("\n\tEpocas de entrenamiento: %d", epocas);
				for(int i=0; i<num_neu; i++){
					System.out.printf("\nNeurona(%d).", i);
					System.out.printf("\n\tBias de la neurona(%d): %f", i, neuronas[i].obtenerBias());
					System.out.printf("\n\tTasa de aprendizaje: %f", neuronas[i].obtenerEpsilon());
					System.out.printf("\n\tPesos: ");
					neuronas[i].imprimePesos();
					System.out.println();
				}

				patron = new double[num_valores];
				System.out.printf("\nIngresar patron para clasificar.");
				for(int i=0;i<num_valores;i++){
					System.out.printf("\nPatron-valor[%d]: ", i);
					patron[i] = (double)(entrada.nextDouble());
				}

				for(int i=0; i<num_neu; i++){
					neuronas[i].clasificarPatron(patron);
					System.out.printf("\n\nClase del patron ingresado: %.1f.\n", neuronas[i].obtenerSalidaCalculada());
				}

				System.out.printf("\nIngresar patron para clasificar.");
				for(int i=0;i<num_valores;i++){
					System.out.printf("\nPatron-valor[%d]: ", i);
					patron[i] = (double)(entrada.nextDouble());
				}

				for(int i=0; i<num_neu; i++){
					neuronas[i].clasificarPatron(patron);
					System.out.printf("\n\nClase del patron ingresado: %.1f.\n", neuronas[i].obtenerSalidaCalculada());
				}
			}

		}while(opcion != 0);
	}
}
