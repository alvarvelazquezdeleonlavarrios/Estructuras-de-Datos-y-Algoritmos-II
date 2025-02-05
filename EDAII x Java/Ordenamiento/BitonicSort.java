public class BitonicSort{

	// Nota: BitonicSort solo funciona con arreglos de longitud 2^n
	int[] arr = {10, 24, 2, 10, 11, 27, 4, 2};

	public static void main(String[] args){
		BitonicSort bs = new BitonicSort();
		bs.bitonicSort(bs.arr, 0, bs.arr.length, true);

		for (int i=0; i<bs.arr.length; i++){
			System.out.print(bs.arr[i] + " ");
		}
		System.out.println("\n");
	}

	public void bitonicSort(int[] A, int inicio, int tam, boolean dir){
		if (tam > 1){
			int k = tam/2;

			// Ordena lado izquierdo de forma ascendente (menor-mayor)
			bitonicSort(A, inicio, k, true);

			// Ordena lado derecho de forma descendente (mayor-menor)
			bitonicSort(A, inicio+k, k, false);

			// Mezcla dos mitades en determinado orden
			bitonicMerge(A, inicio, tam, dir);
		}
	}

	public void bitonicMerge(int[] A, int inicio, int tam, boolean dir){
		if (tam > 1){
			int k=tam/2;

			for (int i=inicio; i<inicio+k; i++){
				// Intercambia dependiendo el orden asignado
				if ((A[i]>A[i+k] && dir==true) || (A[i]<A[i+k] && dir==false)){
					int x = A[i];
					A[i] = A[i+k];
					A[i+k] = x;
				}
			}
			bitonicMerge(A,inicio,k,dir);
			bitonicMerge(A,inicio+k,k,dir);
		}
	}

}