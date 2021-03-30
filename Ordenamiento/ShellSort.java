public class ShellSort{

	int[] arr = {13,8,21,6,10,2};

	public static void main(String[] args){
		ShellSort ss = new ShellSort();
		ss.shellSort(ss.arr); // Ordenamiento ShellSort

		for (int i=0; i<ss.arr.length; i++){
			System.out.println(ss.arr[i]);
		}
	}

	public void shellSort(int[] A){
		int i, j, h, tmp, n = A.length;

		// Genera las sublistas correspondientes y a partir del elemento medio realiza Insertion Sort
		for (h=n/2; h>0; h=h/2){
			// Insertion Sort
			for (i=h; i<n; i++){
				tmp = A[i];
				// Si la parte izquierda a analizar ya está ordenada, sale del ciclo
				for (j=i; j>=h && A[j-h]>tmp; j=j-h){
					A[j] = A[j-h];
				}
				A[j] = tmp; // Coloca el elemento inicial en su posición correcta
			}
		}
	}

}