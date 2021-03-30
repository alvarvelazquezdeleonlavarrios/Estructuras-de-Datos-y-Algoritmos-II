public class TreeSort{

	class Nodo{
		int valor;
		Nodo izq, der;

		public Nodo(int valor){
			this.valor = valor;
			this.izq = null;
			this.der = null;
		}
	}

	int[] arr = {10, 24, 2, 10, 11, 27, 4, 2, 4, 28, 16, 9, 8, 2};
	Nodo raiz = null;
	int cont=0;

	public static void main(String[] args){
		TreeSort ts = new TreeSort();
		ts.treeSort(ts.arr);

		for (int i=0; i<ts.arr.length; i++){
			System.out.print(ts.arr[i] + " ");
		}
		System.out.println("\n");
	}

	public void treeSort(int[] A){
		// Llenar un árbol binario de búsqueda
		for (int i=0; i<A.length; i++){
			insertar(A[i]);
		}

		// Recorrer el árbol binario de búsqueda in-orden
		recorridoInOrden(this.raiz, A);
	}

	public void insertar(int valor){
		raiz = añadir(raiz, valor);
	}

	public Nodo añadir(Nodo raiz, int valor){
		if (raiz == null){
			raiz = new Nodo(valor);
			return raiz;
		}

		if (valor < raiz.valor){
			raiz.izq = añadir(raiz.izq, valor);
		} else {
			raiz.der = añadir(raiz.der, valor);
		}
		return raiz;
	}

	public void recorridoInOrden(Nodo raiz, int[] A){
		// Verificar que exista el nodo
		if (raiz != null){
			recorridoInOrden(raiz.izq, A); // Subárbol Izquierdo
			A[cont] = raiz.valor;
			cont++;
			recorridoInOrden(raiz.der, A); // Subárbol Derecho
		}
	}

}