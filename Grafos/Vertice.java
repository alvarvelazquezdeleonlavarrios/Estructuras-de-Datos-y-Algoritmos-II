import java.util.List;
import java.util.LinkedList;

public class Vertice{
	public String nombre;
	public boolean visitado=false;
	public List<Vertice> adyacentes = new LinkedList<>(); // Lista de Adyacencia
	public List<Arista> aristas = new LinkedList<>();	// Aristas que contiene el vértice
	
	public Vertice(String nombre){
		this.nombre = nombre;
	}

	public void agregarAdyacente(Vertice v){
		boolean repetido = false;
		for(int i=0; i<this.adyacentes.size(); i++){
			if(v == this.adyacentes.get(i)){
				repetido = true;
			}
		}

		if(repetido == true){
			// Nodo repetido
		} else {
			this.adyacentes.add(v);	// Si no existe el nodo, se agrega
		}
	}

	public void imprimirAdyacentes(){
		for (int i=0; i<this.adyacentes.size(); i++){
			System.out.print(this.adyacentes.get(i).nombre + " ");
		}
		System.out.println("\n");
	}

}