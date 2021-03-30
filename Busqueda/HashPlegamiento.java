public class HashPlegamiento{

	public int[] arr = {30342034,32565342};
	public int numDigitos = 3, tamaño = 100; // Tamaño para el mapa

	public static void main(String[] args){
		HashPlegamiento hp = new HashPlegamiento();

		// Muestra las características del elemento aplicado a la función hash
		System.out.println("Elemento -> Posición Virtual");
		for (int i=0; i<hp.arr.length; i++){
			int k = hp.hashPlegamiento(hp.arr[i],hp.tamaño);
			System.out.println(hp.arr[i] + " -> " + k);
		}
		System.out.println(" ");

		int[] mapa = new int[hp.tamaño];	// Areglo para el mapa

		// Asigna las llaves al mapa
		for (int i=0; i<hp.arr.length; i++){
			hp.agregar(hp.arr[i], mapa);
		}

		int j = hp.buscar(30342034,mapa);
		System.out.println(j);

		j = hp.buscar(32457492,mapa);
		System.out.println(j);
	}

	public int hashPlegamiento(int llave, int tamaño){
		int k = maximaPotencia(llave);
		int p = k/numDigitos;
		int inf, sup, part, potencia, posicion;

		int suma = 0;
		// Se parte el elemento en dígitos
		for (int i=1; i<=p; i++){
			potencia = (int) Math.pow(10,k-(numDigitos*i));
			part = particion(llave,potencia);
			suma = suma + part;
		}

		// Si sobraron dígitos a la derecha, se suman
		if (k%numDigitos != 0){
			potencia = (int) Math.pow(10,p);
			int c = llave % potencia;
			suma = suma + c;
		}

		// Regresa la posición para el arreglo (toma los dígitos de la derecha)
		posicion = suma % tamaño;
		return posicion;
	}

	public void agregar(int llave, int[] mapa){
		int k = hashPlegamiento(llave,mapa.length);

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
		int k = hashPlegamiento(llave, mapa.length);

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

	// Recorre el punto decimal hasta la izquierda de la clave. Obtiene el número máximo de dígitos
	public int maximaPotencia(int n){
		int max=0;
		for(int exp=1; n/exp != 0; exp*=10){
			max++;
		}
		return max;
	}

	// Genera una sección de dígitos a partir de la clave
	public int particion(int llave, int potencia){
		int inf = llave, sup = llave;

		sup = sup/potencia;

		inf = inf/(potencia * (int) Math.pow(10,numDigitos));
		inf = inf * (int) Math.pow(10,numDigitos);

		int c = sup - inf;
		return c;
	}

}