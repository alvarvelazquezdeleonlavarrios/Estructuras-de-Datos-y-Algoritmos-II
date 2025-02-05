public class HashCuadrado{
	public int[] arr = {7259,359};
	public int tamaño = 100; // Tamaño para el mapa

	public static void main(String[] args){
		HashCuadrado hc = new HashCuadrado();

		// Imprime posiciones virtuales
		System.out.println("Elemento -> Posición Virtual");
		for (int i=0; i<hc.arr.length; i++){
			int k = hc.hashCuadrado(hc.arr[i], hc.tamaño);
			System.out.println(hc.arr[i] + " -> " + k);
		}
		System.out.println(" ");

		int[] mapa = new int[hc.tamaño]; // Arreglo para el mapa

		// Asigna las llaves al mapa.
		for(int i=0; i<hc.arr.length; i++){
			hc.agregar(hc.arr[i],mapa);
		}

		int j = hc.buscar(7259,mapa);
		System.out.println(j);

		j = hc.buscar(1440,mapa);
		System.out.println(j);
	}

	public int hashCuadrado(int llave, int tamaño){
		int c = llave*llave;
		int med = (maximaPotencia(c)-1)/2; // Determina en donde inicia el número para la posición en el arreglo
		int inf = c;
		int sup = c;
		int rango=0;

		// Determina la posición de la frontera media (donde inicia el número para la posición en el arreglo)
		for (int exp=1; tamaño/exp>0; exp*=10){
			rango ++;
		}

		// Frontera Superior (llega hasta el inicio de la posición)
		// Recorre el punto decimal a la izquierda
		for(int i=med; i>0; i--){
			sup=sup/10;
		}

		// Frontera Inferior (llega hasta el fin de la posición)
		// Recorre el punto decimal a la izquierda
		for(int i=med+(rango-1); i>0; i--){
			inf=inf/10;
		}

		// Recorre el punto decimal a la derecha (regresa al inicio de la posición pero con ceros)
		for(int i=med; i<med+(rango-1); i++){
			inf=inf*10;
		}

		c = (sup-inf);
		return c;
	}

	// Recorre el punto decimal hasta la izquierda del número cuadrado
	public int maximaPotencia(int n){
		int max=0;
		for(int exp=1; n/exp > 0; exp*=10){
			max++;
		}
		return max;
	}

	public void agregar(int llave, int[] mapa){
		int k = hashCuadrado(llave,mapa.length);

		// Si la dirección está vacía, colocar llave.
		if(mapa[k]==0){
			mapa[k]=llave;
		} else {
			// Colisión. Reasignación con Prueba Lineal.
			for(int i=1; i<=mapa.length; i++){
				k = k+1;
				// Si se llega al final del arreglo antes, se regresa.
				if (k >= mapa.length){
					k = k - mapa.length;
				}

				// Si encuentra una dirección vacía, colocar llave.
				if(mapa[k]==0){
					mapa[k]=llave;
					break;
				}
			}
		}
	}

	public int buscar(int llave, int[] mapa){
		int k = hashCuadrado(llave, mapa.length);

		// Si la posoción no está vacía.
		if(mapa[k]!=0){
			// Encuentra la llave en la primera posición generada por la función.
			if(mapa[k]==llave){
				return k;
			} else {
				// Colisión. Búsqueda por Prueba Lineal.
				for(int i=1; i<=mapa.length; i++){
					k = k+1;
					// Si se llega al final del arreglo antes, se regresa.
					if (k >= mapa.length){
						k = k - mapa.length;
					}

					// Si ya se encontró la llave a buscar.
					if(mapa[k]==llave){
						return k;
					}
				}
			}
		} else {
			// No se encuentra el elemento en su primer posición virtual
		}
		return -1;
	}

}