import java.util.List;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Primm{

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

		List<Vertice> arbolMin = primm(va);

		System.out.println("Árbol de Expansión Mínimo Primm");
		for (int i=0; i<arbolMin.size(); i++){
			System.out.print(arbolMin.get(i).nombre + " ");
		}
		System.out.println("");
	}

	public static List<Vertice> primm(Vertice s){
		Comparator<Arista> comparador = new ComparadorArista(); // Ordena la cola de prioridad
		PriorityQueue<Arista> pq = new PriorityQueue<>(comparador);

		List<Vertice> visit = bdfs(s,pq);
		return visit;
	}

	public static List<Vertice> bdfs(Vertice s, PriorityQueue<Arista> pq){
		List<Vertice> visitados = new LinkedList<>();
		s.visitado = true;
		visitados.add(s);
		List<Vertice> sub = new LinkedList<>();

		// Se llena la cola de prioridad con las aristas del vértice actual
		for (int i=0; i<s.aristas.size(); i++){
			pq.add(s.aristas.get(i));
		}

		while (!pq.isEmpty()){
			Arista a = pq.poll();
			s = a.u; // Vértice final
			if (s.visitado==false){
				sub = bdfs(s,pq);	// Analiza el siguiente vértice

				for (int j=0; j<sub.size(); j++){
					visitados.add(sub.get(j));
				}
			}
		}
		return visitados;
	}
}