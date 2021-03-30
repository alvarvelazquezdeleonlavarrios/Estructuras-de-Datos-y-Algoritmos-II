import java.util.*;

public class ProxmapSort{

	int i;
	int[] arr = {55,63,61,59,63,57,57,59,58,60};

	public static void main(String[] args){
		ProxmapSort ps = new ProxmapSort();
		ps.proxmapSort(ps.arr);  //Ordenamiento ProxmapSort

		for(ps.i=0; ps.i<=ps.arr.length-1; ps.i++){
			System.out.print(ps.arr[ps.i] + ", ");
		}
		System.out.println("\n");
	}

	public void proxmapSort(int[] A){
		int max = Arrays.stream(A).max().getAsInt();
		int min = Arrays.stream(A).min().getAsInt();
		int rango = max-min+1;
		int hitCount[] = new int[rango]; // Arreglo para conteo
		int proxMap [] = new int[rango]; // Arreglo para conteo acumulado
		int locations [] = new int[A.length]; // Arreglo para el mapa de direcciones
		int salida [] = new int[A.length]; // Arreglo que muestra la salida ordenada

		// Conteo de la cantidad de elementos hacia su respectivo casillero
		for (int j=0; j<A.length; j++){
			hitCount[A[j]-min]++;
		}

		// Conteo acumulado de todos los casilleros
		int total = 0;
		for (int i=0; i<rango; i++){
			if (hitCount[i] == 0){
				proxMap[i] = -9999;
			} else {
				proxMap[i] = total;
				total = total + hitCount[i];
			}
		}

		// Inicializa un mapa de direcciones y la salida
		for (int i=0; i<locations.length; i++){
			locations[i] = proxMap[A[i]-min];
			salida[i] = 0;
		}

		// Ordenamiento tomando como indice la localización actual
		for (int i=0; i<salida.length; i++){
			int inicio = locations[i];
			boolean insercion = false;

			for (int j=inicio; j<salida.length && insercion == false; j++){
				// Si la posición está vacía, se inserta
				if (salida[j] == 0){
					salida[j] = A[i];
					insercion = true;
				} else if (A[i] < salida[j]) {	// Si ya está ocupada la posición se busca la siguiente
					int fin = j+1;
					
					// Busca la siguiente posición vacía
					while (salida[fin] != 0){
						fin++;
					}

					// Desplaza los elementos agregados a la derecha
					for (int k=fin-1; k>j; k--){
						salida[k+1] = salida[k];
						salida[j] = A[i];
					}

					insercion = true;
				}
			}
		}

		// Reasignando al arreglo original
		for (int i=0; i<A.length; i++){
			A[i] = salida[i];
		}
	}

}