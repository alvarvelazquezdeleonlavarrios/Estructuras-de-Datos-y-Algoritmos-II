import java.util.*;

public class RadixSort{
	int[] arr = {3311,3211,2121,1123,1321,2133};

	public static void main(String[] args){
		RadixSort rs = new RadixSort();
		rs.radixSort(rs.arr); // Ordenamiento RadixSort
	}

	public void radixSort(int[] A){
		int mx = Arrays.stream(A).max().getAsInt();
		int[] cont1;
		int[] cont2;
		int[] salida = new int[A.length];

		// Conteo dígito por dígito
		for(int exp=1; mx/exp > 0; exp*=10){
			int md = maxDigit(A,exp);
			cont1 = new int[md+1];
			cont2 = new int[md+1];
			int k;

			// Arreglos de conteo
			for(int j=0; j<A.length; j++){
				k=(A[j]/exp)%10;
				cont1[k]++;
				cont2[k]++;
			}

			// Conteo acumulado
			for(int j=1; j<cont2.length; j++){
				cont2[j] += cont2[j-1];
			}

			// Ordenando elementos
			for(int j=0; j<A.length; j++){
				k=(A[j]/exp)%10;
				salida[cont2[k]-cont1[k]] = A[j];
				cont1[k]--;
			}

			// Reasignando al arreglo original
			for(int j=0; j<A.length; j++){
				A[j] = salida[j];
				System.out.print(A[j] + " ");
			}
			System.out.println(" ");
		}

	}

	// Obtiene el dígito máximo de la unidad actual
	public int maxDigit(int[] A, int exp){
		int max = (A[0]/exp)%10;
		for(int i=1; i<A.length; i++){
			int act = (A[i]/exp)%10;
			if(act>max){
				max = act;
			}
		}
		return max;
	}
}