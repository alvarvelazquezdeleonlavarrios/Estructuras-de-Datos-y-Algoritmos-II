public class InsertionSort{

	int[] arr = {50,17,48,23,8,60};
	static int i;
	
	public static void main(String[] args){
		InsertionSort is = new InsertionSort();
		is.insertionSort(is.arr); //Ordenamiento InsertionSort
		
		for (i=0; i<is.arr.length; i++){
			System.out.println(is.arr[i]);
		}
	}

	public void insertionSort(int[] A){
		int i, k, x, nelem = A.length;

		// Desde el segundo elemento
		for (i=1; i<nelem; i++){
			x=A[i];
			k=i-1;

			// Para k=-1 se ha alcanzado el extremo izquierdo
			while(k>=0 && x<A[k]){
				A[k+1]=A[k]; //Hace hueco para insertar
				k--;
			}
			A[k+1]=x; //Inserta x en su lugar
		}
	}

}