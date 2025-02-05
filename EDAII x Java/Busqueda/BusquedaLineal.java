public class BusquedaLineal{

	int[] arr = {4,6,9,1,3,2,10,12,1};

	public static void main(String[] args){
		BusquedaLineal bl = new BusquedaLineal();
		int i = bl.busquedaLinealIndice(bl.arr,12);
		System.out.println(i);

		boolean e = bl.busquedaLinealExistente(bl.arr,7);
		System.out.println(e);

		int c = bl.busquedaLinealCantidad(bl.arr,1);
		System.out.println(c);

		i = bl.busquedaLinealRecursivaIndice(bl.arr,3,0,bl.arr.length-1);
		System.out.println(i);

		e = bl.busquedaLinealRecursivaExistente(bl.arr,10,0,bl.arr.length-1);
		System.out.println(e);

		c = bl.busquedaLinealRecursivaCantidad(bl.arr,1,0,bl.arr.length-1);
		System.out.println(c);
	}

	public int busquedaLinealIndice(int[] A, int x){
		int indice=-1;
		for(int i=0; i<A.length; i++){
			if(x==A[i]){
				indice = i;
			}
		}
		return indice;
	}

	public boolean busquedaLinealExistente(int[] A, int x){
		boolean encontrado=false;
		for(int i=0; i<A.length; i++){
			if(x==A[i]){
				encontrado = true;
			}
		}
		return encontrado;
	}

	public int busquedaLinealCantidad(int[] A, int x){
		int cantidad=0;
		for(int i=0; i<A.length; i++){
			if(x==A[i]){
				cantidad ++;
			}
		}
		return cantidad;
	}

	public int busquedaLinealRecursivaIndice(int[] A, int x, int inicio, int fin){
		int indice;
		if(inicio>fin){
			indice = -1;
		} else {
			if(A[inicio]==x){
				indice=inicio;
			} else {
				indice = busquedaLinealRecursivaIndice(A,x,inicio+1,fin);
			}
		}
		return indice;
	}

	public boolean busquedaLinealRecursivaExistente(int[] A, int x, int inicio, int fin){
		boolean encontrado;
		if(inicio>fin){
			encontrado = false;
		} else {
			if(A[inicio]==x){
				encontrado=true;
			} else {
				encontrado = busquedaLinealRecursivaExistente(A,x,inicio+1,fin);
			}
		}
		return encontrado;
	}

	public int busquedaLinealRecursivaCantidad(int[] A, int x, int inicio, int fin){
		int cantidad=0;
		if(inicio>fin){
			cantidad = 0;
		} else {
			if(A[inicio]==x){
				for(int i=inicio; i<=fin; i++){
					if(A[i]==x){
						cantidad ++;
					}
				}
			} else {
				cantidad = busquedaLinealRecursivaCantidad(A,x,inicio+1,fin);
			}
		}
		return cantidad;
	}

}