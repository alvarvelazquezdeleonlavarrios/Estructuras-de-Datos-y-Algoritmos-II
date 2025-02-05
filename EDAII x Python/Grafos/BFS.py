from Grafo import Grafo
from Vertice import Vertice
from Arista import Arista

class BFS:

	def __init__(self):
		'Constructor de la clase'
		pass

	def bfs(self,s):
		'Recorrido por expansión del grafo (por capas o niveles)'
		cola = []
		visitados = []
		cola.append(s)
		s.visitado = True
		visitados.append(s)

		while len(cola) != 0:
			v = cola.pop(0)

			if len(v.adyacentes) != 0:
				for i in range(0,len(v.adyacentes)):
					if v.adyacentes[i].visitado == False:
						cola.append(v.adyacentes[i])
						v.adyacentes[i].visitado = True
						visitados.append(v.adyacentes[i])

		return visitados

# Ejecutando los objetos
programa = BFS()
g = Grafo(False, False)

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
aa = Arista(va,vb,0)
ab = Arista(va,vf,0)
ac = Arista(va,vi,0)
ad = Arista(vi,vj,0)
ae = Arista(vb,vc,0)

# Conectando nodos
g.añadirArista(aa,0)
g.añadirArista(ab,0)
g.añadirArista(ac,0)
g.añadirArista(ad,0)
g.añadirArista(ae,0)

g.imprimirGrafo()

visit = programa.bfs(g.vertices[0])

nombres = []
for i in range(0,len(visit)):
	nombres.append(visit[i].nombre)
print("Visitados:", nombres)