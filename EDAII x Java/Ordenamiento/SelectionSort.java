public class SelectionSort{

	int i,j,n,minimo,aux;
	int[] arr = {50,17,48,23,8,60};

	public static void main(String[] args){
		SelectionSort ss = new SelectionSort();
		ss.selectionSort(ss.arr); //Ordenamiento SelectionSort

		for(ss.i=0;ss.i<=ss.n-1;ss.i++){
			System.out.println(ss.arr[ss.i]);
		}
	}

	public void selectionSort(int[] A){
		n=A.length;
		for(i=0;i<=n-1;i++){
			minimo = i;
			for(j=i+1;j<=n-1;j++){
				if(A[j]<A[minimo]){
					minimo=j;
				}
			}
			//Intercambio
			aux=A[minimo];
			A[minimo]=A[i];
			A[i]=aux;
		}
	}

}