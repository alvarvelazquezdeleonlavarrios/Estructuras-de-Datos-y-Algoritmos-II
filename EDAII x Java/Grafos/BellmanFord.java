public class BellmanFord{

	public static int INF = 9999;

	public static void main(String[] args){
		Grafo g = new Grafo(true,true);

		// Generando nodos
		Vertice va = new Vertice("a");
		Vertice vb = new Vertice("b");
		Vertice vc = new Vertice("c");
		Vertice vf = new Vertice("f");
		Vertice vi = new Vertice("i");
		Vertice vj = new Vertice("j");

		// Añadiendo nodos al grafo
		g.añadirVertice(va);
		g.añadirVertice(vb);
		g.añadirVertice(vc);
		g.añadirVertice(vf);
		g.añadirVertice(vi);
		g.añadirVertice(vj);

		// Generando aristas
		Arista ab = new Arista(va,vb);
		Arista ac = new Arista(va,vc);
		Arista bc = new Arista(vb,vc);
		Arista cb = new Arista(vc,vb);
		Arista bf = new Arista(vb,vf);
		Arista ci = new Arista(vc,vi);
		Arista ii = new Arista(vi,vf);
		Arista ij = new Arista(vi,vj);
		Arista fj = new Arista(vf,vj);

		// Conectando nodos
		g.añadirArista(ab,4);
		g.añadirArista(ac,2);
		g.añadirArista(bc,6);
		g.añadirArista(cb,6);
		g.añadirArista(bf,7);
		g.añadirArista(ci,3);
		g.añadirArista(ii,1);
		g.añadirArista(ij,5);
		g.añadirArista(fj,3);

		g.imprimirGrafo();

		bellmanFord(g,va);
	}

	public static void bellmanFord(Grafo g, Vertice origen){
		Vertice[] ruta = new Vertice[g.vertices.size()];
		Vertice[] previo = new Vertice[g.vertices.size()];
		int[] dist = new int[g.vertices.size()];

		// Inicializando distancias desde el origen a los otros nodos como infinitas
		for (int i=0; i<g.vertices.size(); i++){
			dist[i] = INF;
			ruta[i] = g.vertices.get(i);
			previo[i] = null;
		}
		dist[g.vertices.indexOf(origen)] = 0;

		// Relaciona todas las aristas con todos los vértices para obtener la ruta más corta
		for (int i=1; i<g.vertices.size(); i++){
			for (int j=0; j<g.aristas.size(); j++){
				int inicio = g.vertices.indexOf(g.aristas.get(j).v);
				int fin = g.vertices.indexOf(g.aristas.get(j).u);
				int costo = g.aristas.get(j).costo;

				if (dist[inicio] != INF && dist[inicio]+costo < dist[fin]){
					dist[fin] = dist[inicio] + costo;
					previo[fin] = ruta[inicio];
				}
			}
		}

		// Revisar si existen ciclos negativos generados (solo si hay costos negativos)
		for (int j=0; j<g.aristas.size(); j++){
			int inicio = g.vertices.indexOf(g.aristas.get(j).v);
			int fin = g.vertices.indexOf(g.aristas.get(j).u);
			int costo = g.aristas.get(j).costo;

			if (dist[inicio] != INF && dist[inicio]+costo < dist[fin]){
				System.out.println("El grafo contiene un ciclo negativo.");
			}
		}

		// Imprime la distancia para cada nodo partiendo del inicio
		for (int i=0; i<g.vertices.size(); i++){
			System.out.println("Nodo: " + ruta[i].nombre + ", distancia: " + dist[i]);
		}
		System.out.println("");

		// Imprime la ruta inversa para cada nodo
		for (int i=0; i<g.vertices.size(); i++){
			try{
				System.out.println("Nodo: " + ruta[i].nombre + ", previo: " + previo[i].nombre);
			} catch (Exception e){
				System.out.println("Nodo: " + ruta[i].nombre + ", previo: null");
			}
		}

	}

}