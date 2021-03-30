from Grafo import Grafo
from Vertice import Vertice
from Arista import Arista

class Kruskal:

	def __init__(self):
		'Constructor de la clase'
		pass

	def costo(self,a):
		'Permite ordenar la cola de prioridad'
		return a.costo

	def kruskal(self,g):
		pq = []	# Cola de prioridad
		visitados = []

		# Añade todas las aristas del grafo a la cola de prioridad
		i = 0
		while i < len(g.aristas):
			pq.append(g.aristas[i])
			i +=1
		pq.sort(key=self.costo)	# Ordena la cola de prioridad

		# Mapa para determinar si se generan ciclos
		mapa = [0] * len(g.vertices)
		# Llenando mapa
		for i in range(0,len(mapa)):
			mapa[i] = i

		indice = 0
		while indice < len(g.vertices)-1:
			a = pq.pop(0)
			x = self.buscar(mapa, g.vertices.index(a.v))
			y = self.buscar(mapa, g.vertices.index(a.u))

			if x != y:
				# Se agrega la arista al árbol
				visitados.append(a)
				indice +=1

				x = self.buscar(mapa,x)
				y = self.buscar(mapa,y)
				# Convierte al vértice x como el padre del vértice y
				mapa[y] = x

		return visitados

	def buscar(self,mapa,posicion):
		if mapa[posicion] != posicion:
			return self.buscar(mapa, mapa[posicion])
		return posicion

# Ejecutando los objetos
programa = Kruskal()
g = Grafo(False, True)

# Generando nodos
va = Vertice("a")
vb = Vertice("b")
vc = Vertice("c")
vf = Vertice("f")
vi = Vertice("i")
vj = Vertice("j")

# Añadiendo nodos al grafo
g.añadirVertice(va)
g.añadirVertice(vb)
g.añadirVertice(vc)
g.añadirVertice(vf)
g.añadirVertice(vi)
g.añadirVertice(vj)

# Generando aristas
aa = Arista(va,vb,20)
ab = Arista(va,vf,50)
ac = Arista(va,vi,10)
ad = Arista(vi,vj,40)
ae = Arista(vb,vc,5)
af = Arista(vf,vj,30)

# Conectando nodos
g.añadirArista(aa,20)
g.añadirArista(ab,50)
g.añadirArista(ac,10)
g.añadirArista(ad,40)
g.añadirArista(ae,5)
g.añadirArista(af,30)

g.imprimirGrafo()

arbolMin = programa.kruskal(g)

print("Árbol de Expansión Mínimo Kruskal")
for i in range(0,len(arbolMin)):
	arbolMin[i].imprimirArista()