import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

// Árbol Binario de Búsqueda Balanceado
public class ArbolBinario{
	Nodo raiz = null;

	public ArbolBinario(){}	// Constructor predeterminado

	public ArbolBinario(int valor){
		this.raiz = new Nodo(valor);
	}

	public ArbolBinario(Nodo raiz){
		this.raiz = raiz;
	}

	public void añadir(Nodo nodo){
		if (raiz == null){
			raiz = nodo;
			System.out.println("Nodo raíz generado.");
		} else {
			if (buscarNodo(raiz, nodo.valor)){
				System.out.println("El nodo ya existe en el árbol.");
			} else {
				añadir(raiz,nodo);
				balancear(this);
			}
		}
	}

	public void añadir(Nodo padre, Nodo hijo){
		if (hijo.valor < padre.valor){	// Se asigna al subárbol izquierdo
			if (padre.izquierdo != null){
				añadir(padre.izquierdo, hijo); // Asignación recursiva
			} else {
				padre.izquierdo = hijo;
				padre.izquierdo.padre = padre; // Al nodo hijo se le asigna su padre
			}
		} else if (hijo.valor > padre.valor){ // Se asigna al subárbol derecho
			if (padre.derecho != null){
				añadir(padre.derecho, hijo); // Asignación recursiva
			} else {
				padre.derecho = hijo;
				padre.derecho.padre = padre; // Al nodo hijo se le asigna su padre
			}
		}
	}

	public void eliminar(int valor){
		if (raiz == null){
			System.out.println("Árbol vacío");
		} else {
			eliminar(raiz,valor);
			if (this.raiz != null){
				balancear(this);
			} else {
				System.out.println("Nodo raíz eliminado");
			}
		}
	}

	public void eliminar(Nodo padre, int valor){
		Nodo eliminar = obtenerNodo(padre, valor);

		if (eliminar == null){
			System.out.println("El nodo no existe en el árbol");
		} else {
			// Buscar nodo predecesor (menor-anterior del nodo a eliminar, o bien,
			// el nodo de mayor valor del subárbol izquierdo)
			if (eliminar.izquierdo == null && eliminar.derecho == null){
				// Se elimina un nodo hoja
				if (eliminar.padre != null){
					// Determinar padre del nodo a eliminar
					if (eliminar.valor < eliminar.padre.valor){ // Nodo izquierdo
						eliminar.padre.izquierdo = null;
					} else { // Nodo derecho
						eliminar.padre.derecho = null;
					}
					eliminar.padre = null;
				} else { // Se elimina el único nodo del árbol
					this.raiz = null;
				}
			} else if (eliminar.izquierdo != null && eliminar.derecho == null){
				// El nodo a eliminar (rama) tiene hijo izquierdo, pero no hijo derecho
				eliminar.valor = eliminar.izquierdo.valor;
				eliminar.derecho = eliminar.izquierdo.derecho;
				eliminar.izquierdo = eliminar.izquierdo.izquierdo;

				eliminar.izquierdo.padre = eliminar;
				eliminar.derecho.padre = eliminar;
			} else if (eliminar.izquierdo == null && eliminar.derecho != null){
				// El nodo a eliminar (rama) tiene hijo derecho, pero no hijo izquierdo
				eliminar.valor = eliminar.derecho.valor;
				eliminar.derecho = eliminar.derecho.derecho;
				eliminar.izquierdo = eliminar.derecho.izquierdo;
				
				eliminar.izquierdo.padre = eliminar;
				eliminar.derecho.padre = eliminar;
			} else if (eliminar.izquierdo != null && eliminar.derecho != null){
				// El nodo a eliminar (rama o raíz) tiene a sus dos hijos
				Nodo reemplazo = predecesor(eliminar.izquierdo);

				if (reemplazo.izquierdo != null){
					// Si el nodo reemplazo tiene un subárbol izquierdo, se recorre
					eliminar.valor = reemplazo.valor;
					reemplazo.valor = reemplazo.izquierdo.valor;
					reemplazo.derecho = reemplazo.izquierdo.derecho;
					reemplazo.izquierdo = reemplazo.izquierdo.izquierdo;

					reemplazo.izquierdo.padre = reemplazo;
					reemplazo.derecho.padre = reemplazo;
				} else {
					// Si se llegó a un nodo hoja se elimina (para el nodo raíz)
					int aux = reemplazo.padre.valor;
					eliminar.valor = reemplazo.valor;
					if (reemplazo.valor < aux){ // El nodo reemplazo es hijo izquierdo
						reemplazo.padre.izquierdo = null;
					} else {
						reemplazo.padre.derecho = null;
					}
					reemplazo.padre = null;
				}
			}
		}
		// Se limpia la referencia actual del nodo a eliminar
		eliminar = null;
	}

	public boolean buscarNodo(Nodo raiz, int valor){
		// Si se trata de buscar en un árbol vacío
		if (raiz == null){
			System.out.println("Árbol vacío");
			return false;
		} else {
			if (valor == raiz.valor){	// Se encuentra en el nodo actual
				return true;
			} else if (valor < raiz.valor){	// Se busca en el subárbol izquierdo
				if (raiz.izquierdo != null){
					boolean encontrado = buscarNodo(raiz.izquierdo, valor);
					return encontrado;
				} else {
					return false; // Si ya no hay hijos en donde buscar, no existe el nodo
				}
			} else if (valor > raiz.valor){	// Se busca en el subárbol derecho
				if (raiz.derecho != null){
					boolean encontrado = buscarNodo(raiz.derecho, valor);
					return encontrado;
				} else {
					return false; // Si ya no hay hijos en donde buscar, no existe el nodo
				}
			} else {
				return false;
			}
		}
	}

	public Nodo obtenerNodo(Nodo raiz, int valor){
		// Si se trata de buscar en un árbol vacío
		if (raiz == null){
			System.out.println("Árbol vacío");
			return null;
		} else {
			if (valor == raiz.valor){	// Se encuentra en el nodo actual
				return raiz;
			} else if (valor < raiz.valor){	// Se busca en el subárbol izquierdo
				if (raiz.izquierdo != null){
					Nodo encontrado = obtenerNodo(raiz.izquierdo, valor);
					return encontrado;
				} else {
					return null; // Si ya no hay hijos en donde buscar, no existe el nodo
				}
			} else if (valor > raiz.valor){	// Se busca en el subárbol derecho
				if (raiz.derecho != null){
					Nodo encontrado = obtenerNodo(raiz.derecho, valor);
					return encontrado;
				} else {
					return null; // Si ya no hay hijos en donde buscar, no existe el nodo
				}
			} else {
				return null;
			}
		} 
	}

	public Nodo predecesor(Nodo hijo){
		if (hijo.derecho != null){ // Si todavía hay hijos derechos, se visitan
			return predecesor(hijo.derecho);
		} else {
			return hijo; // Se llega al último hijo derecho posible
		}
	}

	public void balancear(ArbolBinario arbol){
		// Revisar si el árbol sigue siendo balanceado
		boolean balance = balanceado(this.raiz);
		if (balance == false){
			// Realizar recorrido infijo para obtener los elementos ordenados de menor a mayor
			List<Nodo> nodos = new LinkedList<>();
			System.out.println("Recorrido infijo del árbol: ");
			infijo(this.raiz, nodos);
			System.out.println("");

			// Limpiar enlaces de los nodos
			for (int i=0; i<nodos.size(); i++){
				nodos.get(i).padre = null;
				nodos.get(i).izquierdo = null;
				nodos.get(i).derecho = null;
			}

			// Redistribuir el árbol
			int medio = nodos.size()/2;
			Nodo n = nodos.get(medio);
			ArbolBinario arbolBin = new ArbolBinario(n); // Nuevo arbol binario balanceado

			// Nodos del nuevo subárbol izquierdo
			List<Nodo> izq = nodos.subList(0,medio);
			// Nodos del nuevo subárbol derecho
			List<Nodo> der = nodos.subList(medio+1, nodos.size());

			redistribuir(arbolBin, izq); // Nuevo subárbol izquierdo
			redistribuir(arbolBin, der); // Nuevo subárbol derecho

			// Recorriendo el nuevo árbol balanceado
			System.out.print("Recorrido BFS del nuevo árbol (balanceado): ");
			arbolBin.BFS();

			arbol.raiz = arbolBin.raiz;
		}
	}

	public boolean balanceado(Nodo raiz){
		LinkedList<Integer> niveles = new LinkedList<>(); // Niveles de cada nodo
		niveles(raiz,niveles,0);
		int max = nivelMax(niveles);
		int min = nivelMin(niveles);

		if ((max-min)==1){
			System.out.println("Árbol balanceado");
			return true;
		} else if ((max-min)==0){
			System.out.println("Árbol Perfectamente Balanceado");
			return true;
		} else {
			System.out.println("Árbol No Balanceado");
			return false;
		}
	}

	public void redistribuir(ArbolBinario arbol, List<Nodo> nodos){
		if (nodos.size() == 2){
			arbol.añadir(arbol.raiz, nodos.get(1));
			arbol.añadir(arbol.raiz, nodos.get(0));
		} else if (nodos.size() == 1){
			arbol.añadir(arbol.raiz, nodos.get(0));
		} else if (nodos.size() >= 3){
			int medio = nodos.size()/2;
			arbol.añadir(arbol.raiz, nodos.get(medio));

			// Nodos del nuevo subárbol izquierdo
			List<Nodo> izq = nodos.subList(0,medio);
			// Nodos del nuevo subárbol derecho
			List<Nodo> der = nodos.subList(medio+1, nodos.size());

			redistribuir(arbol, izq); // Nuevo subárbol izquierdo
			redistribuir(arbol, der); // Nuevo subárbol derecho
		}
	}

	public int altura(Nodo raíz){
		LinkedList<Integer> niveles = new LinkedList<>();
		niveles(raiz,niveles,0);
		int max = nivelMax(niveles);
		return max;
	}

	public void BFS(){
		Nodo raiz = this.raiz;
		Queue<Nodo> cola = new LinkedList<>();
		if (raiz != null){
			cola.add(raiz);
			while (!cola.isEmpty()){
				raiz = cola.poll();
				System.out.print(raiz.valor + " ");

				if (raiz.izquierdo != null){
					cola.add(raiz.izquierdo);
				}
				if (raiz.derecho != null){
					cola.add(raiz.derecho);
				}
			}
		}
		System.out.println("");
	}

	public void prefijo(Nodo raiz){
		System.out.print(raiz.valor + " "); // Se visita al nodo
		// Subárbol izquierdo
		if (raiz.izquierdo != null){
			prefijo(raiz.izquierdo);
		}
		// Subárbol derecho
		if (raiz.derecho != null){
			prefijo(raiz.derecho);
		}
	}

	public void infijo(Nodo raiz){
		// Subárbol izquierdo
		if (raiz.izquierdo != null){
			infijo(raiz.izquierdo);
		}
		System.out.print(raiz.valor + " "); // Se visita al nodo
		// Subárbol derecho
		if (raiz.derecho != null){
			infijo(raiz.derecho);
		}
	}

	public void infijo(Nodo raiz, List<Nodo> nodos){
		// Subárbol izquierdo
		if (raiz.izquierdo != null){
			infijo(raiz.izquierdo, nodos);
		}
		System.out.print(raiz.valor + " ");	// Se visita al nodo
		nodos.add(raiz);
		// Subárbol derecho
		if (raiz.derecho != null){
			infijo(raiz.derecho, nodos);
		}
	}

	public void postfijo(Nodo raiz){
		// Subárbol izquierdo
		if (raiz.izquierdo != null){
			postfijo(raiz.izquierdo);
		}
		// Subárbol derecho
		if (raiz.derecho != null){
			postfijo(raiz.derecho);
		}
		System.out.print(raiz.valor + " ");
	}

	public void niveles(Nodo raiz, LinkedList<Integer> niveles, int actual){
		int alt = actual;

		// Si el nodo no tiene a alguno de sus hijos, se puede generar su nivel (tope)
		if (raiz.izquierdo == null || raiz.derecho == null){
			niveles.add(alt);
		}

		// Subárbol izquierdo
		if (raiz.izquierdo != null){
			alt++; // Llega al siguiente nivel
			niveles(raiz.izquierdo, niveles, alt);
			alt--; // Regresa al nivel actual
		}

		// Subárbol derecho
		if (raiz.derecho != null){
			alt++; // Llega al siguiente nivel
			niveles(raiz.derecho, niveles, alt);
			alt--; // Regresa al nivel actual
		}
	}

	protected int nivelMax(LinkedList<Integer> niveles){
		int max=0;
		for (int i=0; i<niveles.size(); i++){
			if (niveles.get(i) > max){
				max = niveles.get(i);
			}
		}
		return max;
	}

	protected int nivelMin(LinkedList<Integer> niveles){
		int min=niveles.get(0);
		for (int i=0; i<niveles.size(); i++){
			if (niveles.get(i) < min){
				min = niveles.get(i);
			}
		}
		return min;
	}
}