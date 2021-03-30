public class CycleSort{

	int[] arr = {10, 24, 2, 10, 11, 27, 4, 2, 4, 28, 16, 9, 8, 2};
	
	public static void main(String[] args){
		CycleSort cs = new CycleSort();
		cs.cycleSort(cs.arr);

		for (int i=0; i<cs.arr.length; i++){
			System.out.print(cs.arr[i] + " ");
		}
		System.out.println("\n");
	}

	public void cycleSort(int[] A){
		int n=A.length;

		for (int inicio=0; inicio<=n-2; inicio++){
			// Objeto actual a comparar con los otros elementos
			int objeto = A[inicio];

			int posicion = inicio;
			for (int i=inicio+1; i<n; i++){
				if (A[i]<objeto){
					posicion++;
				}
			}

			// Si el objeto ya se encuentra ordenado en su posición se omite el resto
			if (posicion == inicio){
				continue;
			}

			// Se ignoran elementos duplicados
			while (objeto == A[posicion]){
				posicion++;
			}

			// Si el objeto no se encuentra en su posición ordenada, se intercambia
			if (posicion != inicio){
				int x = objeto;
				objeto = A[posicion];
				A[posicion] = x;
			}

			// Rota el resto del ciclo
			while (posicion != inicio){
				posicion = inicio;

				// Busca la posición correcta para los elementos a rotar
				for (int i=inicio+1; i<n; i++){
					if (A[i]<objeto){
						posicion++;
					}
				}

				// Se ignoran elementos duplicados
				while (objeto == A[posicion]){
					posicion++;
				}

				// Coloca el objeto en su posición correcta
				if (objeto != A[posicion]){
					int x = objeto;
					objeto = A[posicion];
					A[posicion] = x;
				}
			}
		}
	}

}