public class GnomeSort{

	int[] arr = {4,3,12,21,7,15,23,30};

	public static void main(String[] args){
		GnomeSort gs = new GnomeSort();
		gs.gnomeSort(gs.arr); // Ordenamiento GnomeSort

		for (int i=0; i<gs.arr.length; i++){
			System.out.println(gs.arr[i]);
		}
	}

	public void gnomeSort(int[] A){
		int n = A.length;
		int i = 1, j = 2;

		while (i<n){
			if (A[i-1] > A[i]){
				int tmp = A[i-1];
				A[i-1] = A[i];
				A[i] = tmp;
				i--;

				// Si i llega al tope izquierdo, entonces vuelve a la posición actual j
				if(i==0){
					i=j;
					j++;
				}
			} else {
				i++;
				j++;
			}
		}
	}

}