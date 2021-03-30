public class BusquedaBinaria{

	int[] arr = {1,4,6,9,3,2,10,12,1,20,1,3};

	public static void main(String[] args){
		BusquedaBinaria bb = new BusquedaBinaria();
		bb.mergeSort(bb.arr,0,bb.arr.length-1); // Asegura que el arreglo esté ordenado
		for(int i=0; i<bb.arr.length; i++){
			System.out.print(bb.arr[i] + " ");
		}

		int i = bb.busquedaBinariaIndice(bb.arr,4,0,bb.arr.length-1);
		System.out.println("\n" + i);

		boolean e = bb.busquedaBinariaExistente(bb.arr,5,0,bb.arr.length-1);
		System.out.println(e);

		int c = bb.busquedaBinariaCantidad(bb.arr,1,0,bb.arr.length-1);
		System.out.println(c);

		i = bb.busquedaBinariaRecursivaIndice(bb.arr,12,0,bb.arr.length-1);
		System.out.println(i);

		e = bb.busquedaBinariaRecursivaExistente(bb.arr,12,0,bb.arr.length-1);
		System.out.println(e);

		c = bb.busquedaBinariaRecursivaCantidad(bb.arr,1,0,bb.arr.length-1);
		System.out.println(c);
	}

	public int busquedaBinariaIndice(int[] A, int x, int inf, int sup){
		while(inf<=sup){
			int med=(inf+sup)/2;
			if(x==A[med]){
				return med;
			} else {
				if(A[med]<x){
					inf=med+1;
				} else {
					sup=med-1;
				}
			}
		}
		return -1;
	}

	public boolean busquedaBinariaExistente(int[] A, int x, int inf, int sup){
		while(inf<=sup){
			int med=(inf+sup)/2;
			if(x==A[med]){
				return true;
			} else {
				if(A[med]<x){
					inf=med+1;
				} else {
					sup=med-1;
				}
			}
		}
		return false;
	}

	public int busquedaBinariaCantidad(int[] A, int x, int inf, int sup){
		int cantidad = 0;
		while(inf<=sup){
			int med=(inf+sup)/2;
			if(x==A[med]){
				for(int i=inf; i<=sup; i++){
					if(A[i]==x){
						cantidad ++;
					}
				}
				return cantidad;
			} else {
				if(A[med]<x){
					inf=med+1;
				} else {
					sup=med-1;
				}
			}
		}
		return cantidad;
	}

	public int busquedaBinariaRecursivaIndice(int A[], int x, int inf, int sup){
		if(inf>sup){
			return -1;
		}
		int med = (inf+sup)/2;
		if(A[med]==x){
			return med;
		} else if (A[med]<x){
			return busquedaBinariaRecursivaIndice(A,x,med+1,sup);
		} else {
			return busquedaBinariaRecursivaIndice(A,x,inf,med-1);
		}
	}

	public boolean busquedaBinariaRecursivaExistente(int[] A, int x, int inf, int sup){
		if(inf>sup){
			return false;
		}
		int med = (inf+sup)/2;
		if(A[med]==x){
			return true;
		} else if(A[med]<x){
			return busquedaBinariaRecursivaExistente(A,x,med+1,sup);
		} else {
			return busquedaBinariaRecursivaExistente(A,x,inf,med-1);
		}
	}

	public int busquedaBinariaRecursivaCantidad(int[] A, int x, int inf, int sup){
		int cantidad = 0;
		if(inf>sup){
			cantidad=0;
		}
		int med = (inf+sup)/2;
		if(A[med]==x){
			for(int i=inf; i<=sup; i++){
				if(A[i]==x){
					cantidad ++;
				}
			}
			return cantidad;
		} else if(A[med]<x){
			return busquedaBinariaRecursivaCantidad(A,x,med+1,sup);
		} else {
			return busquedaBinariaRecursivaCantidad(A,x,inf,med-1);
		}
	}

	// Algoritmo de Ordenamiento
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