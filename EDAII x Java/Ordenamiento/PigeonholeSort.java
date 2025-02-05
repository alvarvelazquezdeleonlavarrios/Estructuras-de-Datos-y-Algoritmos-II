import java.util.*;

public class PigeonholeSort{

	int i;
	int[] arr = {55,63,61,59,63,57,57,59,58,60};

	public static void main(String[] args){
		PigeonholeSort ps = new PigeonholeSort();
		ps.pigeonholeSort(ps.arr);  //Ordenamiento PigeonholeSort

		for(ps.i=0; ps.i<=ps.arr.length-1; ps.i++){
			System.out.print(ps.arr[ps.i] + ", ");
		}
		System.out.println("\n");
	}

	public void pigeonholeSort(int[] A){
		int max = Arrays.stream(A).max().getAsInt();
		int min = Arrays.stream(A).min().getAsInt();
		int rango = max-min+1;
		int conteo[] = new int[rango]; // Arreglo para conteo

		// Conteo de la cantidad de elementos hacia su respectivo casillero
		for (int j=0; j<A.length; j++){
			conteo[A[j]-min]++;
		}

		// Reasignando elementos de manera ordenada
		i = 0;
		for (int cuenta=0; cuenta<rango; cuenta++){
			while (conteo[cuenta] > 0){
				conteo[cuenta] -= 1;
				A[i] = cuenta + min;
				i = i+1;
			}
		}
	}

}