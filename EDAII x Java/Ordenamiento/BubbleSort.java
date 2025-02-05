public class BubbleSort{

	int i,j,k,cont,n,aux;
	int[] arr = {50,17,48,23,8,60};

	public static void main(String[] args){
		BubbleSort bs = new BubbleSort();
		bs.bubbleSort(bs.arr);  //Ordenamiento BubbleSort

		for(bs.i=0; bs.i<=bs.n-1; bs.i++){
			System.out.println(bs.arr[bs.i]);
		}
	}

	public void bubbleSort(int[] A){
		n = A.length;
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

}