public class Arista{
	public int costo;
	public Vertice v,u;

	public Arista(Vertice v, Vertice u){
		this.v = v;
		this.u = u;
	}

	public Arista(Vertice v, Vertice u, int costo){
		this.v = v;
		this.u = u;
		this.costo = costo;
	}

	public void imprimirArista(){
		System.out.println("Arista: " + this.v.nombre + " <-- " + this.costo + " --> " + this.u.nombre);
	}
}