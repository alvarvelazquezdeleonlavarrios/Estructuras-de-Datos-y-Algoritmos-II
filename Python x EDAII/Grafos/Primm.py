from Grafo import Grafo
from Vertice import Vertice
from Arista import Arista

class Primm:

	def __init__(self):
		'Constructor de la clase'
		pass

	def costo(self,a):
		'Permite ordenar la cola de prioridad'
		return a.costo

	def primm(self,s):
		'Obtiene el árbol de expansión mínimo a partir de un grafo'
		pq = []	# Cola de Prioridad
		visit = self.bdfs(s,pq)
		return visit

	def bdfs(self,s,pq):
		'Recorrido del grafo para obtener el árbol de expansión mínimo'
		visitados = []
		s.visitado = True
		visitados.append(s)
		sub = []

		# Se llena la cola de prioridad con las aristas del vértice actual
		i = 0
		while i<len(s.aristas):
			pq.append(s.aristas[i])
			i +=1
		pq.sort(key=self.costo)	# Ordena la cola de prioridad

		while len(pq) != 0:
			a = pq.pop(0)
			s = a.u	# Vertice final
			if s.visitado == False:
				sub = self.bdfs(s,pq)	# Analizar el siguiente vértice

				j = 0
				while j<len(sub):
					visitados.append(sub[j])
					j +=1
		return visitados

# Ejecutando los objetos
programa = Primm()
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

arbolMin = programa.primm(va)

nombres = []
for i in range(0,len(arbolMin)):
	nombres.append(arbolMin[i].nombre)
print("Árbol de Expansión Mínimo Primm:", nombres)