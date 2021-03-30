from Grafo import Grafo
from Vertice import Vertice
from Arista import Arista

class DFS:

	def __init__(self):
		'Constructor de la clase'
		pass

	def dfs(self,s):
		'Recorrido por profundidad del grafo.'
		visitados = []
		s.visitado = True
		visitados.append(s)
		sub = []

		for i in range(0,len(s.adyacentes)):
			if s.adyacentes[i].visitado == False:
				sub = self.dfs(s.adyacentes[i])

			for j in range(0,len(sub)):
				visitados.append(sub[j])

		return visitados

# Ejecutando los objetos
programa = DFS()
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

visit = programa.dfs(g.vertices[0])

nombres = []
for i in range(0,len(visit)):
	nombres.append(visit[i].nombre)
print("Visitados:", nombres)