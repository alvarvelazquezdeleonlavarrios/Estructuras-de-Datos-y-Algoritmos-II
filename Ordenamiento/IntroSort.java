public class IntroSort{

	int i,n,maxRecursion;
	int tamMaxSub = 3; // Tamaño que define si un subarreglo se ordena mediante InsertionSort
	// int[] arr = {50,17,48,23,8,60};
	int[] arr = { 2, 10, 24, 2, 10, 11, 27, 4, 2, 4, 28, 16, 9, 8, 28, 10, 13, 24, 22, 28, 
				0, 13, 27, 13, 3, 23, 18, 22, 8, 8 };

	public static void main(String[] args){
		IntroSort is = new IntroSort();
		is.introSort(is.arr);  //Ordenamiento IntroSort
	}

	public void introSort(int[] A){
		n = A.length;
		maxRecursion = (int) (2*Math.floor(Math.log(n)/Math.log(2))); // Define la profundidad máxima de recursión

		quickSort(A,0,n-1,maxRecursion);
	}

	private void quickSort(int[] A, int inicio, int fin, int maxRecursion){
		// Si el tamaño de un subarreglo es muy grande, ordenar por quickSort o heapSort
		if (fin-inicio > tamMaxSub){
			
			// Ordenamiento por HeapSort
			if (maxRecursion == 0){
				System.out.println("Ordenando por HeapSort (" + inicio + ", " + fin + ")");
				heapSort(A, inicio, fin);

				for (int i=0; i<A.length; i++){
					System.out.print(A[i] + " ");
				}
				System.out.println("\n");

				return;
			}

			maxRecursion = maxRecursion - 1;

			System.out.println("Ordenando por QuickSort (" + inicio + ", " + fin + ")");

			// Ordenamiento por QuickSort
			if (inicio < fin){

				// Partición
				int izq = inicio-1;
				int der = fin+1;
				int pivote = A[inicio];

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

				for (int i=0; i<A.length; i++){
					System.out.print(A[i] + " ");
				}
				System.out.println("\n");

				quickSort(A,inicio,der,maxRecursion);  // Sublista Izquierda
				quickSort(A,der+1,fin,maxRecursion);  // Sublista Derecha
			}

		} else { // Si el tamaño de un subarreglo es pequeño, ordenar por insertionSort
			// Ordenamiento por InsertionSort
			System.out.println("Ordenando por InsertionSort (" + inicio + ", " + fin + ")");
			insertionSort(A,inicio, fin);

			for (int i=0; i<A.length; i++){
				System.out.print(A[i] + " ");
			}
			System.out.println("\n");
		}
	}

	private void heapSort(int[] A, int inicio, int fin){
		int n = fin-inicio+1;

		// Construyendo Árbol Binario
		for(int i=(n/2)-1;i>=0;i--){
			heap(A,n,i,inicio);
		}

		// Ordenando nodo por nodo desde el árbol binario
		for(int i=n-1;i>0;i--){
			// Mover raíz principal hasta el final
			int temp = A[inicio];
			A[inicio] = A[inicio+i];
			A[inicio+i] = temp;

			// Reconstruyendo el árbol particionado
			heap(A,i,0,inicio);
		}
	}

	private void heap(int[] A, int n, int raiz, int inicio){
		int l = raiz+inicio; // Nodo Actual
		int izq = (2*raiz)+1+inicio; // Hijo Izquierdo
		int der = (2*raiz)+2+inicio; // Hijo Derecho

		if(izq<(n+inicio) && A[izq]>A[l]){
			l=izq; // Raiz -> Izquierdo
		}

		if(der<(n+inicio) && A[der]>A[l]){
			l=der; // Izquierdo -> Derecho
		}

		if(l!=(raiz+inicio)){
			// Intercambio
			int aux = A[raiz+inicio];
			A[raiz+inicio] = A[l];
			A[l] = aux;

			// Construyendo sub-árbol izquierdo o derecho
			heap(A,n,l-inicio,inicio);
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

}