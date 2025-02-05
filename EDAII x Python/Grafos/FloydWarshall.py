from Grafo import Grafo
from Arista import Arista
from Vertice import Vertice

class FloydWarshall:

	INF = 9999

	def __init__(self):
		'Constructor de la clase'
		pass

	def floydWarshall(self,g):
		'Determina la ruta más corta de un nodo origen hacia el resto, para todos los nodos'
		distancias = [[0 for j in range(len(g.vertices))] for i in range(len(g.vertices))]

		# Inicializando matriz de adyacencia
		for i in range(0,len(g.vertices)):
			adyacentes = g.vertices[i].aristas

			for j in range(0,len(g.vertices)):
				if i==j:
					distancias[i][j] = 0
				else:
					uAct = g.vertices[j]
					a = self.buscarAdyacencia(adyacentes, uAct)

					if a != None:
						distancias[i][j] = a.costo;
					else:
						distancias[i][j] = self.INF

		# Obteniendo rutas mínimas para cada vertice
		for k in range(0,len(g.vertices)):
			for i in range(0,len(g.vertices)):
				for j in range(0,len(g.vertices)):
					if distancias[i][k] + distancias[k][j] < distancias[i][j]:
						distancias[i][j] = distancias[i][k] + distancias[k][j]

		# Imprimiendo matriz de adyacencia resultante
		for i in range(0,len(g.vertices)):
			# Matriz de adyacencia
			print("Nodo: ", g.vertices[i].nombre)
			for j in range(0,len(g.vertices)):
				print("\tDistancia al nodo: ",g.vertices[j].nombre," -> ",distancias[i][j])
			print(" ")

	def buscarAdyacencia(self,aristas,u):
		'Verifica si un nodo es próximo a otro'
		for i in range(0,len(aristas)):
			if aristas[i].u == u:
				return aristas[i]
		return None

# Ejecutando los objetos
programa = FloydWarshall()
g = Grafo(True,True);

# Generando nodos
va = Vertice("a");
vb = Vertice("b");
vc = Vertice("c");
vf = Vertice("f");
vi = Vertice("i");
vj = Vertice("j");

# Añadiendo nodos al grafo
g.añadirVertice(va);
g.añadirVertice(vb);
g.añadirVertice(vc);
g.añadirVertice(vf);
g.añadirVertice(vi);
g.añadirVertice(vj);

# Generando aristas
ab = Arista(va,vb,0);
ac = Arista(va,vc,0);
bc = Arista(vb,vc,0);
cb = Arista(vc,vb,0);
bf = Arista(vb,vf,0);
ci = Arista(vc,vi,0);
ii = Arista(vi,vf,0);
ij = Arista(vi,vj,0);
fj = Arista(vf,vj,0);

# Conectando nodos
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

programa.floydWarshall(g)