import java.util.*;

public class BucketSort{

	int[] arr = {15,20,43,11,39,12,31,42};

	public static void main(String[] args){
		BucketSort bs = new BucketSort();
		bs.bucketSort(bs.arr); // Ordenamiento BucketSort
	}

	public void bucketSort(int[] A){
		int md = maxDigit(A);
		int indice = maxIndex(A);
		int cubeta[][] = new int[md+1][A.length]; // Cubetas
		int conteo[] = new int[md+1]; // Arreglo que almacena la posición para el siguiente elemento

		// Asigna los elementos de A hacia sus respectivas cubetas
		for (int j=0; j<A.length; j++){
			int i = A[j]/(int) Math.pow(10,indice);
			cubeta[i][conteo[i]] = A[j];
			conteo[i]++;
		}

		// Ordena las cubetas mediante otro algoritmo de ordenamiento
		for (int j=0; j<(md+1); j++){
			bubbleSort(cubeta[j],conteo[j]);
		}
		System.out.println(" ");

		// Imprimiendo cubetas ordenadas
		System.out.println("Contenido de las cubetas");
		for (int i=0; i<(md+1); i++){
			for (int j=0; j<A.length; j++){
				System.out.println("Cubeta: " + i + ", elemento: " + cubeta[i][j]);
			}
		}
		System.out.println(" ");

		// Reasigna los elementos ordenados al arreglo original
		int k=0;
		for (int i=0; i<(md+1); i++){
			for(int j=0; j<A.length; j++){
				if (cubeta[i][j] != 0){
					A[k] = cubeta[i][j];
					System.out.print(A[k] + " ");
					k++;
				}
			}
		}
		System.out.println(" ");

	}

	// Algortimo de ordenamiento secundario
	public void bubbleSort(int[] A, int tam){
		int n = tam;
		int i,j,k,aux,cont = 0;
		for (i=n-1; i>=0; i--){
			k=0;
			for (j=0; j<=i-1; j++) {
				if (A[j]>A[j+1]){
					aux = A[j];
					A[j]=A[j+1];
					A[j+1]=aux;
				} else {
					k=k+1;
				}
			}
			cont = cont + 1;
			if (k==i){
				System.out.println("Conjunto ordenado en la pasada " + cont);
				break;
			}
		}
	}

	// Obtiene el dígito máximo del elemento mayor [0,9]
	public int maxDigit(int[] A){
		int max = Arrays.stream(A).max().getAsInt();
		int i=1;
		while (max/i >= 10){
			i=i*10;
		}
		return (max/i);
	}

	// Obtiene cuantas veces debe dividirse el mayor elemento entre 10 [0,9]
	public int maxIndex(int[] A){
		int max = Arrays.stream(A).max().getAsInt();
		int i=0;
		while (max >= 10){
			i++;
			max = max/10;
		}
		return i;
	}

}