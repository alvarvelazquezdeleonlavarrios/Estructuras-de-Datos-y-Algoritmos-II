import java.util.List;
import java.util.LinkedList;

public class DFS{

	public static void main(String[] args){
		Grafo g = new Grafo(false,false);

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

		// Conectando nodos
		g.añadirArista(aa,0);
		g.añadirArista(ab,0);
		g.añadirArista(ac,0);
		g.añadirArista(ad,0);
		g.añadirArista(ae,0);

		g.imprimirGrafo();

		List<Vertice> visit = dfs(g.vertices.get(0));

		System.out.println("Visitados: ");
		for (int k=0; k<visit.size(); k++){
			System.out.print(visit.get(k).nombre + " ");
		}
		System.out.println("");
	}

	public static List<Vertice> dfs(Vertice s){
		List<Vertice> visitados = new LinkedList<>();
		s.visitado=true;
		visitados.add(s);
		List<Vertice> sub = new LinkedList<>();

		for (int i=0; i<s.adyacentes.size(); i++){
			if (s.adyacentes.get(i).visitado==false){
				sub = dfs(s.adyacentes.get(i));
			}

			for (int j=0; j<sub.size(); j++){
				visitados.add(sub.get(j));
			}
		}

		return visitados;
	}

}