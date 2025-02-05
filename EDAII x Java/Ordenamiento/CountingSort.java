import java.util.*;

public class CountingSort{
	int[] arr = {55,63,61,59,63,57,57,59,58,60};
	int j;

	public static void main(String[] args){
		CountingSort cs = new CountingSort();
		cs.countingSort(cs.arr); // Ordenamiento CountingSort

		for (cs.j=0; cs.j <cs.arr.length;cs.j++){
			System.out.print(cs.arr[cs.j] + " ");
		}
	}

	public void countingSort(int[] A){
		int max = Arrays.stream(A).max().getAsInt();
		int min = Arrays.stream(A).min().getAsInt();
		int rango = max-min+1;
		int conteo[] = new int[rango]; // Arreglo para conteo
		int salida[] = new int[A.length]; // Arreglo que muestra la salida ordenada

		// Conteo de la cantidad de elementos hacia su respectivo casillero
		for (int i=0; i<A.length; i++){
			conteo[A[i]-min]++;
		}

		// Conteo acumulado de todos los casilleros
		for(int i=1; i<conteo.length; i++){
			conteo[i] += conteo[i-1] ;
		}

		// Ordenamiento tomando como índice el conteo actual
		for (int i=A.length-1; i>=0; i--){
			salida[conteo[A[i]-min]-1] = A[i];
			conteo[A[i]-min]--;
		}

		// Reasignando al arreglo original
		for (int i=0; i<A.length; i++){
			A[i] = salida[i];
		}
	}

}