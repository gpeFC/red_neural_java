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
	}
}