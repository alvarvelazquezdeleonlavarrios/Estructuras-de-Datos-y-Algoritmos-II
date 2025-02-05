from Grafo import Grafo
from Arista import Arista
from Vertice import Vertice

class Dijkstra:

	INF = 9999

	def __init__(self):
		'Constructor de la clase'
		pass

	def dijkstra(self,g,origen):
		'Determina la ruta más corta de un nodo origen hacia el resto'
		ruta = [0] * len(g.vertices)
		previo = [0] * len(g.vertices)
		dist = [0] * len(g.vertices)
		visitados = [0] * len(g.vertices)

		# Inicializando distancias desde el origen a los otros nodos como infinitos
		for i in range(0,len(g.vertices)):
			dist[i] = self.INF
			ruta[i] = g.vertices[i]
			previo[i] = None
			visitados[i] = False
		dist[g.vertices.index(origen)] = 0

		# Relaciona las aristas de los nodos adyacentes de cada nodo
		for i in range(1,len(g.vertices)):
			inicio = self.minDistancia(dist, visitados)
			visitados[inicio] = True

			# Revisa los nodos adyacentes del nodo seleccionado
			j = 0
			while j < len(g.vertices[inicio].adyacentes):
				fin = g.vertices.index(g.vertices[inicio].adyacentes[j])
				costo = g.vertices[inicio].aristas[j].costo

				if visitados[fin]==False and dist[inicio] != self.INF and dist[inicio]+costo < dist[fin]:
					dist[fin] = dist[inicio] + costo
					previo[fin] = ruta[inicio]

				j +=1

		# Imprime la distancia para cada nodo partiendo del inicio
		for i in range(0,len(g.vertices)):
			print("Nodo:",ruta[i].nombre,", distancia:",dist[i])
		print(" ")

		# Imprime la ruta inversa para cada nodo
		for i in range(0,len(g.vertices)):
			try:
				print("Nodo:",ruta[i].nombre,", previo:",previo[i].nombre)
			except:
				print("Nodo:",ruta[i].nombre,", previo: None")

	def minDistancia(self,dist,visitados):
		minimo = self.INF
		indice_min = -1

		# Determina al siguiente nodo con la ruta más corta
		for i in range(0,len(dist)):
			if visitados[i] == False and dist[i] <= minimo:
				minimo = dist[i]
				indice_min = i

		return indice_min

# Ejecutando los objetos
programa = Dijkstra()
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

programa.dijkstra(g,va)