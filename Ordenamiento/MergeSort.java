public class MergeSort{
	int[] arr = {33,50,17,48,23,8,60};

	public static void main(String[] args){
		MergeSort ms = new MergeSort();
		ms.mergeSort(ms.arr, 0, ms.arr.length-1);

		for (int i=0; i<ms.arr.length; i++){
			System.out.print(ms.arr[i] + " ");
		}
	}

	public void mergeSort(int[] A, int inf, int sup){
		if(inf==sup-1){ // Dos elementos
			if(A[inf]>A[sup]){
				int tmp = A[inf];
				A[inf]=A[sup];
				A[sup]=tmp;
			}
		} else if(inf < sup){
			int med = (inf+sup)/2;

			mergeSort(A,inf,med);
			mergeSort(A,med+1,sup);
			merge(A,inf,med,sup);
		}
	}

	public void merge(int[] A, int inf, int med, int sup){
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