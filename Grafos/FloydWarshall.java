import java.util.List;
import java.util.LinkedList;

public class FloydWarshall{

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

		floydWarshall(g);
	}

	public static void floydWarshall(Grafo g){
		int distancias[][] = new int[g.vertices.size()][g.vertices.size()];
		int i, j, k;

		// Inicializando matriz de adyacencia
		for (i=0; i<g.vertices.size(); i++){
			List<Arista> adyacentes = g.vertices.get(i).aristas;

			for (j=0; j<g.vertices.size(); j++){

				if (i == j){
					distancias[i][j] = 0;
				} else {
					Vertice uAct = g.vertices.get(j);

					Arista a = buscarAdyacencia(adyacentes, uAct);
					if (a != null){
						distancias[i][j] = a.costo;
					} else {
						distancias[i][j] = INF;
					}
				}
			}
		}

		// Obteniendo rutas mínimas para cada vertice
		for (k=0; k<g.vertices.size(); k++){
			for (i=0; i<g.vertices.size(); i++){
				for (j=0; j<g.vertices.size(); j++){
					if (distancias[i][k] + distancias[k][j] < distancias[i][j]){
						distancias[i][j] = distancias[i][k] + distancias[k][j];
					}
				}
			}
		}

		// Imprimiendo matriz de adyacencia resultante
		for (i=0; i<g.vertices.size(); i++){
			// Matriz de adyacencia
			System.out.println("Nodo: " + g.vertices.get(i).nombre + ", ");
			for (j=0; j<g.vertices.size(); j++){
				System.out.println("\tDistancia al nodo: " + g.vertices.get(j).nombre + " -> " + distancias[i][j]);
			}
			System.out.println("");
		}
	}

	public static Arista buscarAdyacencia(List<Arista> aristas, Vertice u){
		for (int i=0; i<aristas.size(); i++){
			if (aristas.get(i).u == u){
				return aristas.get(i);
			}
		}
		return null;
	}

}