public class StoogeSort{

	int i,n;
	int[] arr = {50,17,48,23,8,60};

	public static void main(String[] args){
		StoogeSort ss = new StoogeSort();
		ss.stoogeSort(ss.arr, 0, ss.arr.length-1);  //Ordenamiento StoogeSort
		ss.n = ss.arr.length;

		for(ss.i=0; ss.i<=ss.n-1; ss.i++){
			System.out.print(ss.arr[ss.i] + ", ");
		}
		System.out.println("\n");
	}

	public void stoogeSort(int[] A, int inf, int sup){
		if (A[inf] > A[sup]){
			// Intercambio
			int temp = A[sup];
			A[sup] = A[inf];
			A[inf] = temp;
		}

		// Si el subarreglo tiene más de dos elementos, se ordenan por tercios
		if ((sup-inf+1) > 2){
			int t = (sup-inf+1)/3;
			stoogeSort(A,inf,sup-t); // Se ordenan los primeros dos tercios del arreglo
			stoogeSort(A,inf+t,sup); // Se ordenan los ultimos dos tercios del arreglo
			stoogeSort(A,inf,sup-t); // Se vuelven a ordenar los primeros dos tercios del arreglo
		}
	}

}