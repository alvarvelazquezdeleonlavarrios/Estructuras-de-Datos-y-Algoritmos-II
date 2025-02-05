public class RankSort{

	int i;
	int[] arr = {8,0,-4,-8,7,2};

	public static void main(String[] args){
		RankSort rs = new RankSort();
		rs.rankSort(rs.arr);  //Ordenamiento RankSort

		for(rs.i=0; rs.i<=rs.arr.length-1; rs.i++){
			System.out.print(rs.arr[rs.i] + ", ");
		}
		System.out.println("\n");
	}

	public void rankSort(int[] A){
		int rangos [] = new int[A.length]; // Arreglo para el mapa de direcciones
		int salida [] = new int[A.length]; // Arreglo que muestra la salida ordenada

		// Calcular el rango de cada elemento del arreglo
		for (int i=0; i<A.length; i++){
			for (int j=0; j<i; j++){
				if (A[i] >= A[j]){
					rangos[i]++;
				} else {
					rangos[j]++;
				}
			}
		}

		// Impresión de direcciones para cada elemento
		System.out.println("\nRango de cada elemento (elemento -> dirección)");
		for (int i=0; i<A.length; i++){
			System.out.println(A[i] + " -> " + rangos[i]);
		}
		System.out.println("\n");

		// Ordenar de acuerdo al rango
		for (int i=0; i<A.length; i++){
			salida[rangos[i]] = A[i]; 
		}

		// Reasignando al arreglo original
		for (int i=0; i<A.length; i++){
			A[i] = salida[i];
		}
	}

}