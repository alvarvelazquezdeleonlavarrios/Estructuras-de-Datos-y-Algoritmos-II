public class HeapSort{
	
	int i;
	int[] arr = {50,17,48,23,8,60};

	public static void main(String[] args){
		HeapSort hs = new HeapSort();
		hs.heapSort(hs.arr);

		for(hs.i=0;hs.i<hs.arr.length;hs.i++){
			System.out.println(hs.arr[hs.i]);
		}
	}

	public void heapSort(int[] A){
		int n = A.length;

		// Construyendo Árbol Binario
		for(int i=(n/2)-1;i>=0;i--){
			heap(A,n,i);
		}

		// Ordenando nodo por nodo desde el árbol binario
		for(int i=n-1;i>0;i--){
			// Mover raíz principal hasta el final
			int temp = A[0];
			A[0] = A[i];
			A[i] = temp;

			// Reconstruyendo el árbol particionado
			heap(A,i,0);
		}
	}

	// Construir Árbol Binario
	public void heap(int[] A, int n, int raiz){
		int l = raiz; // Nodo Actual
		int izq = (2*raiz)+1; // Hijo Izquierdo
		int der = (2*raiz)+2; // Hijo Derecho

		if(izq<n && A[izq]>A[l]){
			l=izq; // Raiz -> Izquierdo
		}

		if(der<n && A[der]>A[l]){
			l=der; // Izquierdo -> Derecho
		}

		if(l!=raiz){
			// Intercambio
			int aux = A[raiz];
			A[raiz] = A[l];
			A[l] = aux;

			// Construyendo sub-árbol izquierdo o derecho
			heap(A,n,l);
		}
	}

}