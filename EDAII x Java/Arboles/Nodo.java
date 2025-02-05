public class Nodo{
	int valor;
	Nodo izquierdo = null;
	Nodo derecho = null;
	Nodo padre = null;

	public Nodo(){}	// Constructor predeterminado

	public Nodo(int valor){
		this.valor = valor;
	}

	public Nodo(int valor, Nodo izquierdo, Nodo derecho, Nodo padre){
		this.valor = valor;
		this.izquierdo = izquierdo;
		this.derecho = derecho;
		this.padre = padre;
	}

}