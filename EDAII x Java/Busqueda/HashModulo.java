public class HashModulo{
	public int[] arr = {23,50,70,40,8,17};

	public static void main(String[] args){
		HashModulo hm = new HashModulo();

		// Imprime módulos
		System.out.println("Elemento -> Posición Virtual");
		for(int i=0; i<hm.arr.length; i++){
			int k = hm.hashModulo(hm.arr[i], hm.arr.length);
			System.out.println(hm.arr[i] + " -> " + k);
		}

		int[] mapa = new int[hm.arr.length]; // Arreglo para el mapa

		// Asigna las llaves al mapa.
		for(int i=0; i<mapa.length; i++){
			hm.agregar(hm.arr[i],mapa);
		}

		// Imprime mapa
		System.out.println("\nMapa");
		for(int i=0; i<mapa.length; i++){
			System.out.print(mapa[i] + " ");
		}
		System.out.println("\n");

		int j = hm.buscar(23,mapa);
		System.out.println(j);

		j = hm.buscar(14,mapa);
		System.out.println(j);
	}

	public int hashModulo(int llave, int modulo){
		return llave % modulo;
	}

	public void agregar(int llave, int[] mapa){
		int k = hashModulo(llave,mapa.length);

		// Si la dirección está vacía, colocar llave.
		if(mapa[k]==0){
			mapa[k]=llave;
		} else {
			// Colisión. Reasignación con Prueba Lineal.
			for(int i=1; i<=mapa.length; i++){
				int hk = hashModulo(k+i,mapa.length);
				
				// Si encuentra una dirección vacía, colocar llave.
				if(mapa[hk]==0){
					mapa[hk]=llave;
					break;
				}
			}
		}
	}

	public int buscar(int llave, int[] mapa){
		int k = hashModulo(llave, mapa.length);

		// Si la posoción no está vacía.
		if(mapa[k]!=0){
			// Encuentra la llave en la primera posición generada por la función.
			if(mapa[k]==llave){
				return k;
			} else {
				// Colisión. Búsqueda por Prueba Lineal.
				for(int i=1; i<=mapa.length; i++){
					int hk = hashModulo(k+i,mapa.length);

					// Si ya se encontró la llave a buscar.
					if(mapa[hk]==llave){
						return hk;
					}
				}
			}
		} else {
			// No se encuentra el elemento en su primer posición virtual
		}
		return -1;
	}

}