public class ShakerSort{

	int[] arr = {8,3,7,4,9,5,2,1};

	public static void main(String[] args){
		ShakerSort ss = new ShakerSort();
		ss.shakerSort(ss.arr); // Ordenamiento ShakerSort

		for (int i=0; i<ss.arr.length; i++){
			System.out.println(ss.arr[i]);
		}
	}

	public void shakerSort(int[] A){
		int n = A.length;
		int inf = 0, sup = n-1;

		while(inf <= sup){
			// Pasada de Derecha a Izquierda
			for(int j=sup; j>inf; j--){
				if (A[j] < A[j-1]){
					int tmp = A[j];
					A[j] = A[j-1];
					A[j-1] = tmp;
				}
			}
			inf++;

			// Pasada de Izquierda a Derecha
			for(int j=inf; j<sup; j++){
				if (A[j] > A[j+1]){
					int tmp = A[j];
					A[j] = A[j+1];
					A[j+1] = tmp;
				}
			}
			sup--;
		}
	}

}