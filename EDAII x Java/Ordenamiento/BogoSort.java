import java.util.Random;

public class BogoSort{

	int i,n,cont = 0;
	int[] arr = {50,17,48,23,8,60};

	public static void main(String[] args){
		BogoSort bs = new BogoSort();
		bs.bogoSort(bs.arr);  //Ordenamiento BogoSort
		bs.n = bs.arr.length;

		for(bs.i=0; bs.i<=bs.n-1; bs.i++){
			System.out.print(bs.arr[bs.i] + ", ");
		}
		System.out.println("\nArreglo ordenado en la iteración " + bs.cont);
	}

	public void bogoSort(int[] A){
		while (ordenado(A) == false){
			barajar(A);
			cont = cont + 1;
		}
	}

	public void barajar(int[] A){
		Random r = new Random();

		for (int i=A.length-1; i>0; i--){
			// Toma un indice aleatorio desde 0 hasta i
			int j = r.nextInt(i+1);

			// Intercambio
			int temp = A[i];
			A[i] = A[j];
			A[j] = temp;
		}
	}

	public boolean ordenado(int[] A){
		int k=0;
		boolean ordenado = false;

		for (int i=0; i<A.length-1; i++){
			if (A[i] < A[i+1]){
				k++;
			}
		}

		if (k == A.length-1){
			ordenado = true;
		}

		return ordenado;
	}

}