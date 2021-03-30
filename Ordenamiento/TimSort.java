public class TimSort{
	
	int n;
	int tamSub=3; // Tamaño máximo para cada subarreglo
	int[] arr = {2, 10, 24, 2, 10, 11, 27, 4, 2, 4, 28, 16, 9, 8};

	public static void main(String[] args){
		TimSort ts = new TimSort();
		ts.timSort(ts.arr, ts.tamSub);

		for (int i=0; i<ts.arr.length; i++){
			System.out.print(ts.arr[i] + " ");
		}
		System.out.println("\n");
	}

	public void timSort(int[] A, int tamSub){
		n = A.length;

		// Ordenar los subarreglos mediante insertionSort
		for (int i=0; i<n; i+=tamSub){
			insertionSort(A, i, Math.min((i+tamSub),(n-1)));
		}

		// Mezclar lor subarreglos y ordenarlos mediente mergeSort
		for (int tamAct=tamSub; tamAct<n; tamAct=2*tamAct){

			// Mezcla los subarreglos de tamaño 2*tamAct
			for (int izq=0; izq<n; izq+=2*tamAct){

				// izq -> med = SubArr1
				// med+1 -> der = SubArr2
				int med = Math.min((izq + tamAct - 1),(n-1));
				int der = Math.min((izq+2*tamAct-1),(n-1));

				merge(A,izq,med,der);
			}
		}
	}

	private void insertionSort(int[] A, int inicio, int fin){
		int i, k, x;

		// Desde el segundo elemento
		for (i=inicio+1; i<=fin; i++){
			x=A[i];
			k=i-1;

			// Para k=-1 se ha alcanzado el extremo izquierdo
			while(k>=inicio && x<A[k]){
				A[k+1]=A[k]; //Hace hueco para insertar
				k--;
			}
			A[k+1]=x; //Inserta x en su lugar
		}
	}

	private void merge(int[] A, int inf, int med, int sup){
		int n1 = med-inf+1;
		int n2 = sup-med;

		int L[] = new int[n1];
		int R[] = new int[n2];

		// Copiando a los arreglos auxiliares
		for(int i=0; i<n1; i++){
			L[i]=A[inf+i];
		}
		for(int j=0; j<n2; j++){
			R[j]=A[med+1+j];
		}

		int i=0, j=0;
		int k=inf;

		// Obteniendo valores menores de ambos arreglos auxiliares
		while(i<n1 && j<n2){
			if(L[i]<=R[j]){
				A[k]=L[i];
				i++;
			} else{
				A[k]=R[j];
				j++;
			}
			k++;
		}

		// Copiando el resto de valores no asignados
		while(i<n1){
			A[k]=L[i];
			i++;
			k++;
		}

		while(j<n2){
			A[k]=R[j];
			j++;
			k++;
		}
	}

}