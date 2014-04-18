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
		int opcion, num_neuronas, num_patrones, num_argumentos, red, epocas, funcion;
		Scanner entrada = new Scanner(System.in);

		do{
			System.out.printf("\n\n\tRed Neuronal Artificial Perceptron...\n\n");
			System.out.println("0) Salir de la aplicacion.");
			System.out.println("1) Perceptron Simple.");
			System.out.println("2) Perceptron Multicapa.");
			System.out.printf("\n\tTipo de Red: ");
			opcion = entrada.nextInt();

			if(opcion == 1){

				System.out.printf("\nNumero de neuronas de la red: ");
				num_neuronas = entrada.nextInt();

				System.out.printf("\nNumero de patrones de entrenamiento: ");
				num_patrones = entrada.nextInt();

				System.out.printf("\nNumero de valores por patron: ");
				num_argumentos = entrada.nextInt();
			}
			else if(opcion == 2){
			}
		}while(opcion != 0);
	}
}
