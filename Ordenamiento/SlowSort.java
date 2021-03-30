public class SlowSort{

	int i,n;
	int[] arr = {50,17,48,23,8,60};

	public static void main(String[] args){
		SlowSort ss = new SlowSort();
		ss.slowSort(ss.arr, 0, ss.arr.length-1);  //Ordenamiento SlowSort
		ss.n = ss.arr.length;

		for(ss.i=0; ss.i<=ss.n-1; ss.i++){
			System.out.print(ss.arr[ss.i] + ", ");
		}
		System.out.println("\n");
	}

	public void slowSort(int[] A, int inf, int sup){
		if (inf >= sup){
			return;
		}

		int m = (inf+sup)/2;
		slowSort(A,inf,m);	// Subarreglo izquierdo
		slowSort(A,m+1,sup); // Subarreglo derecho

		if (A[sup] < A[m]){
			// Intercambio
			int temp = A[sup];
			A[sup] = A[m];
			A[m] = temp;
		}

		slowSort(A,inf,sup-1);
	}

}