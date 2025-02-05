public class QuickSort{
	
	int[] arr = {33,50,48,17,23,8,60};
	static int i;

	public static void main(String[] args){
		QuickSort qs = new QuickSort();
		qs.quickSort(qs.arr,0,qs.arr.length-1); //Ordenamiento QuickSort

		for (i=0; i<qs.arr.length; i++){
			System.out.println(qs.arr[i]);
		}
	}

	public void quickSort(int[] A, int inf, int sup){
		if(A.length == 0){
			System.out.println("Sin elementos.");
		} else if (A.length == 1) {
			System.out.println("Lista con un solo elemento: " + A[0]);
		} else if (A.length > 1){

			if (inf < sup){

				// Partición
				int izq = inf-1;
				int der = sup+1;
				int pivote = A[inf];

				while (true){

					do{
						izq++;
					} while (A[izq] < pivote);

					do{
						der--;
					} while (A[der] > pivote);

					if (izq >= der){ // Compara las posiciones actuales de izq y der
						break;
					}

					int x = A[izq];
					A[izq] = A[der];  // Intercambio
					A[der] = x;

				}

				quickSort(A,inf,der);  // Sublista Izquierda
				quickSort(A,der+1,sup);  // Sublista Derecha
			}
		}
	}

}