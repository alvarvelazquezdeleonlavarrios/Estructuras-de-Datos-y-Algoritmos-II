import java.util.List;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Kruskal{

	public static void main(String[] args){
		Grafo g = new Grafo(false,true);

		// Generando nodos
		Vertice va = new Vertice("a");
		Vertice vb = new Vertice("b");
		Vertice vc = new Vertice("c");
		Vertice vf = new Vertice("f");
		Vertice vi = new Vertice("i");
		Vertice vj = new Vertice("j");

		// Añadiendo nodos al grado
		g.añadirVertice(va);
		g.añadirVertice(vb);
		g.añadirVertice(vc);
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

		// Conectando nodos
		g.añadirArista(aa,20);
		g.añadirArista(ab,50);
		g.añadirArista(ac,10);
		g.añadirArista(ad,40);
		g.añadirArista(ae,5);
		g.añadirArista(af,30);

		g.imprimirGrafo();

		List<Arista> arbolMin = kruskal(g);

		System.out.println("Árbol de Expansión Mínimo Kruskal");
		for (int i=0; i<arbolMin.size(); i++){
			arbolMin.get(i).imprimirArista();
		}
		System.out.println("");
	}

	public static List<Arista> kruskal(Grafo g){
		Comparator<Arista> comparador = new ComparadorArista(); // Ordena la cola de prioridad
		PriorityQueue<Arista> pq = new PriorityQueue<>(comparador);
		List<Arista> visitados = new LinkedList<>();

		// Añade todas las aristas del grafo a la cola de prioridad
		for (int i=0; i<g.aristas.size(); i++){
			pq.add(g.aristas.get(i));
		}

		// Mapa para determinar si se generan ciclos
		int[] mapa = new int[g.vertices.size()];
		// Llenando mapa
		for (int i=0; i<mapa.length; i++){
			mapa[i] = i;
		}

		int indice = 0;
		while (indice < g.vertices.size()-1){
			Arista a = pq.poll();
			int x = buscar(mapa, g.vertices.indexOf(a.v));
			int y = buscar(mapa, g.vertices.indexOf(a.u));

			if (x == y){
				// Se genera un ciclo
			} else {
				// Se agrega la arista al árbol
				visitados.add(a);
				indice ++;

				x = buscar(mapa,x);
				y = buscar(mapa,y);
				// Convierte al vértice x como el padre del vértice y
				mapa[y] = x;
			}
		}
		return visitados;
	}

	public static int buscar(int[] mapa, int posición){
		if (mapa[posición] != posición){
			return buscar(mapa, mapa[posición]);
		}
		return posición;
	}

}