import java.util.List;
import java.util.LinkedList;

public class Grafo{
	public List<Vertice> vertices = new LinkedList<>();
	public List<Arista> aristas = new LinkedList<>();
	public boolean dirigido = false, ponderado = false;

	public Grafo(){}	// Constructor predeterminado

	public Grafo(boolean dirigido, boolean ponderado){
		this.dirigido = dirigido;
		this.ponderado = ponderado;
	}

	public void añadirVertice(Vertice v){
		boolean repetido = false;
		for (int i=0; i<this.vertices.size(); i++){
			if(v == this.vertices.get(i)){
				repetido = true;
			}
		}

		if(repetido==true){
			// Nodo repetido
		} else {
			this.vertices.add(v);	// Añade el nodo al grafo
		}
	}

	public void añadirArista(Arista a, int costo){
		boolean repetido = false;
		for (int i=0; i<this.aristas.size(); i++){
			if (a == this.aristas.get(i)){
				repetido = true;
			}
		}

		if (repetido == true){
			// Arista repetida
		} else {
			// Grafo Ponderado/No ponderado
			if (this.ponderado==false){
				a.costo = 0;
			} else {
				a.costo = costo;
			}

			// Grafo Dirigido/No dirigido
			if (this.dirigido==false){
				a.v.agregarAdyacente(a.u);
				a.u.agregarAdyacente(a.v);

				Arista ai = new Arista(a.u,a.v,a.costo); // Arista inversa para el nodo del fin
				a.v.aristas.add(a);
				a.u.aristas.add(ai);

				this.aristas.add(a);	// Añade la arista al grafo
				this.aristas.add(ai);	// Añade la arista inversa al grafo
			} else {
				a.v.agregarAdyacente(a.u);
				a.v.aristas.add(a);
				this.aristas.add(a);	// Añade la arista al grafo
			}
		}
	}

	public void imprimirGrafo(){
		// Imprimiendo vértices
		for (int i=0; i<this.vertices.size(); i++){
			System.out.println("Nodo " + this.vertices.get(i).nombre);
			System.out.println("Adyacentes: ");
			this.vertices.get(i).imprimirAdyacentes();
		}

		// Imprimiendo aristas
		for (int i=0; i<this.aristas.size(); i++){
			this.aristas.get(i).imprimirArista();
		}
		System.out.println("");
	}
}