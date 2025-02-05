import java.util.List;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Boruvka{

	public static void main(String[] args){
		Grafo g = new Grafo(false,true);

		// Generando nodos
		Vertice va = new Vertice("a");
		Vertice vb = new Vertice("b");
		Vertice vc = new Vertice("c");
		Vertice vd = new Vertice("d");
		Vertice ve = new Vertice("e");
		Vertice vf = new Vertice("f");
		Vertice vi = new Vertice("i");
		Vertice vj = new Vertice("j");

		// Añadiendo nodos al grado
		g.añadirVertice(va);
		g.añadirVertice(vb);
		g.añadirVertice(vc);
		g.añadirVertice(vd);
		g.añadirVertice(ve);
		g.añadirVertice(vf);
		g.añadirVertice(vi);
		g.añadirVertice(vj);

		// Generando aristas
		Arista aa = new Arista(va,vb);
		Arista ab = new Arista(va,vf);
		Arista ac = new Arista(va,vi);
		Arista ad = new Arista(vi,vj);
		Arista ae = new Arista(vb,vc);
		Arista af = new Arista(vf,vj);
		Arista ag = new Arista(vi,vd);
		Arista ah = new Arista(vd,ve);
		Arista ai = new Arista(vc,ve);

		// Conectando nodos
		g.añadirArista(aa,20);
		g.añadirArista(ab,50);
		g.añadirArista(ac,10);
		g.añadirArista(ad,40);
		g.añadirArista(ae,5);
		g.añadirArista(af,30);
		g.añadirArista(ag,8);
		g.añadirArista(ah,4);
		g.añadirArista(ai,15);

		g.imprimirGrafo();

		LinkedList<Arista> arbolMin = boruvka(g);

		System.out.println("Árbol de Expansión Mínimo Boruvka");
		for (int i=0; i<arbolMin.size(); i++){
			arbolMin.get(i).imprimirArista();
		}
		System.out.println("");
	}

	public static LinkedList<Arista> boruvka(Grafo g){
		LinkedList<Grafo> componentes = new LinkedList<>();
		LinkedList<Arista> aristasVisitadas = new LinkedList<>();
		LinkedList<Arista> aristasTotales = new LinkedList<>();

		// Generar los componentes que contendrán sus respectivos vertices y aristas
		for (int i=0; i<g.vertices.size(); i++){
			componentes.add(new Grafo());

			Vertice s = g.vertices.get(i);
			componentes.get(i).vertices.add(s);

			Comparator<Arista> comparador = new ComparadorArista(); // Ordena la cola de prioridad
			PriorityQueue<Arista> pq = new PriorityQueue<>(comparador);
			
			// Añade todas las aristas adyacentes al vertice actual a la cola de prioridad
			for (int j=0; j<s.aristas.size(); j++){
				pq.add(s.aristas.get(j));
			}

			// Añade las aristas adyacentes a su respectivo componente
			for (int j=0; j<s.aristas.size(); j++){
				componentes.get(i).aristas.add(pq.poll());
			}

			// Remueve la arista de menor valor de ese vertice actual
			Arista minima = componentes.get(i).aristas.get(0);

			// Verificar que las aristas no se dupliquen
			if (buscarArista(aristasVisitadas,minima) == false){
				aristasVisitadas.add(minima);
				aristasTotales.add(minima);
			}
		}

		// Primera Iteración: conectar los vértices en componentes
		componentes = new LinkedList<>();

		while (aristasVisitadas.size() > 0){
			componentes.add(new Grafo());
			int ind = componentes.size()-1;

			LinkedList<Vertice> ruta = new LinkedList<>();
			LinkedList<Integer> indicesTotales = new LinkedList<>();
			Arista a = aristasVisitadas.poll();

			ruta.add(a.v);
			ruta.add(a.u);
			componentes.get(ind).aristas.add(a);

			buscarVerticeIzq(a.v,aristasVisitadas,ruta,indicesTotales);
			buscarVerticeDer(a.u,aristasVisitadas,ruta,indicesTotales);

			// Ordenar los indices para remover las aristas de la cola en determinado orden
			bubbleSort(indicesTotales);
			
			// Agrega las aristas que conectan a los vértices de un componente
			for (int i=indicesTotales.size()-1; i>=0; i--){
				int index = indicesTotales.get(i);
				componentes.get(ind).aristas.add(aristasVisitadas.get(index));
				aristasVisitadas.remove(index);
			}

			// Asigna la ruta de vertices generada para cada componente
			componentes.get(ind).vertices = ruta;
		}

		// Imprimir el contenido de cada componente generado
		for (int i=0; i<componentes.size(); i++){
			System.out.println("Componente: " + i);
			for (int j=0; j<componentes.get(i).vertices.size(); j++){
				System.out.print(componentes.get(i).vertices.get(j).nombre + ", ");
			}
			System.out.println("\n");
			for (int j=0; j<componentes.get(i).aristas.size(); j++){
				componentes.get(i).aristas.get(j).imprimirArista();
			}
			System.out.println(" ");
		}

		// Segunda y Final Iteración: conectar las componentes en una sola (árbol Mínimo)

		// Asignar las aristas minimas que conecten a cada nuevo componente
		for (int i=0; i<componentes.size(); i++){
			Comparator<Arista> comparador = new ComparadorArista(); // Ordena la cola de prioridad
			PriorityQueue<Arista> pq = new PriorityQueue<>(comparador);

			for (int j=0; j<componentes.get(i).vertices.size(); j++){
				for (int k=0; k<componentes.get(i).vertices.get(j).aristas.size(); k++){
					Arista act = componentes.get(i).vertices.get(j).aristas.get(k);

					// Si la arista adyacente al vertice no forma parte del componente, se agrega para
					// analizar conexiones entre componentes
					if (buscarArista(componentes.get(i).aristas, act) == false){
						pq.add(act);
					}
				}
			}

			// Una vez analizado todo el componente se procede a elegir la arista mínima
			Arista minima = pq.poll();

			// Verificar que las aristas no se dupliquen
			if (buscarArista(aristasVisitadas,minima) == false){
				aristasVisitadas.add(minima);
				aristasTotales.add(minima);
			}
		}

		return aristasTotales;
		
	}

	// Busca una arista almacenada en una lista (Listas de esta clase)
	public static boolean buscarArista(LinkedList<Arista> aristasVisitadas, Arista a){
		boolean encontrado = false;
		
		for (int i=0; i<aristasVisitadas.size(); i++){
			Arista aux = aristasVisitadas.get(i);
			
			if ((aux.v == a.v && aux.u == a.u) || (aux.v == a.u && aux.u == a.v)){
				encontrado = true;
			}
		}

		return encontrado;
	}

	// Busca una arista almacenada en una lista (Listas de la clase Grafo, Vertice, Arista)
	public static boolean buscarArista(List<Arista> aristasVisitadas, Arista a){
		boolean encontrado = false;
		
		for (int i=0; i<aristasVisitadas.size(); i++){
			Arista aux = aristasVisitadas.get(i);
			
			if ((aux.v == a.v && aux.u == a.u) || (aux.v == a.u && aux.u == a.v)){
				encontrado = true;
			}
		}

		return encontrado;
	}

	// Busca vertices conectados entre sí para añadirlos a un componente (hacia la izquierda)
	public static void buscarVerticeIzq(Vertice actual, LinkedList<Arista> aristasVisitadas, LinkedList<Vertice> ruta, List<Integer> indicesTotales){
		LinkedList<Integer> indices = new LinkedList<>();

		// Busca los vertices que conectan por la derecha
		for (int i=0; i<aristasVisitadas.size(); i++){
			if (aristasVisitadas.get(i).u == actual){
				indices.add(i);
				indicesTotales.add(i);
			}
		}

		// Añade los vertices a la ruta sin que se repitan
		for (int i=0; i<indices.size(); i++){
			Vertice aux = aristasVisitadas.get(indices.get(i)).v;
			añadirVertice(aux,ruta);
		}

		// Búsqueda recursiva
		for (int i=0; i<indices.size(); i++){
			buscarVerticeIzq(aristasVisitadas.get(indices.get(i)).v, aristasVisitadas, ruta, indicesTotales);
		}
	}

	// Busca vertices conectados entre sí para añadirlos a un componente (hacia la derecha)
	public static void buscarVerticeDer(Vertice actual, LinkedList<Arista> aristasVisitadas, LinkedList<Vertice> ruta, List<Integer> indicesTotales){
		LinkedList<Integer> indices = new LinkedList<>();

		// Busca los vertices que conectan por la izquierda
		for (int i=0; i<aristasVisitadas.size(); i++){
			if (aristasVisitadas.get(i).v == actual){
				indices.add(i);
				indicesTotales.add(i);
			}
		}

		// Añade los vertices a la ruta sin que se repitan
		for (int i=0; i<indices.size(); i++){
			Vertice aux = aristasVisitadas.get(indices.get(i)).u;
			añadirVertice(aux,ruta);
		}

		// Búsqueda recursiva
		for (int i=0; i<indices.size(); i++){
			buscarVerticeDer(aristasVisitadas.get(indices.get(i)).u, aristasVisitadas, ruta, indicesTotales);
		}
	}

	// Añade un vertice sin que se repita en una lista
	public static void añadirVertice(Vertice v,LinkedList<Vertice> ruta){
		boolean repetido = false;
		for (int i=0; i<ruta.size(); i++){
			if (ruta.get(i) == v){
				repetido = true;
			}
		}

		// Si no se repite el vertice, se inserta
		if (repetido == false){
			ruta.add(v);
		}
	}

	public static void bubbleSort(LinkedList<Integer> Lista){
		int n = Lista.size();
		for (int i=n-1; i>=0; i--){
			int k=0;
			for (int j=0; j<=i-1; j++) {
				if (Lista.get(j)>Lista.get(j+1)){
					int aux = Lista.get(j);
					Lista.set(j,Lista.get(j+1));
					Lista.set(j+1,aux);
				} else {
					k=k+1;
				}
			}
			if (k==i){
				break;
			}
		}
	}

}