from Grafo import Grafo
from Arista import Arista
from Vertice import Vertice

class Boruvka:

	def __init__(self):
		'Constructor de la clase'
		pass

	def costo(self,a):
		'Permite ordenar la cola de prioridad'
		return a.costo

	def boruvka(self,g):
		componentes = []
		aristasVisitadas = []
		aristasTotales = []

		# Generar los componentes que contendrán sus respectivos vertices y aristas
		for i in range(0,len(g.vertices)):
			c = Grafo(False,False)
			componentes.append(c)

			s = g.vertices[i]
			componentes[i].vertices.append(s)

			pq = []	# Cola de prioridad

			# Añade todas las aristas adyacentes al vertice actual a la cola de prioridad
			for j in range(0,len(s.aristas)):
				pq.append(s.aristas[j])
			pq.sort(key=self.costo)	# Ordena la cola de prioridad

			# Añade las aristas adyacentes a su respectivo componente
			for j in range(0,len(s.aristas)):
				ar = pq[0]
				del pq[0]
				componentes[i].aristas.append(ar)

			# Remueve la arista de menor valor de ese vertice actual
			minima = componentes[i].aristas[0]

			# Verificar que las aristas no se dupliquen
			if self.buscarArista(aristasVisitadas, minima) == False:
				aristasVisitadas.append(minima)
				aristasTotales.append(minima)

		# Primera Iteración: conectar los vértices en componentes
		componentes.clear()

		while len(aristasVisitadas) > 0:
			c = Grafo(False, False)
			componentes.append(c)
			ind = len(componentes)-1

			ruta = []
			indicesTotales = []
			a = aristasVisitadas[0]
			del aristasVisitadas[0]

			ruta.append(a.v)
			ruta.append(a.u)
			componentes[ind].aristas.append(a)

			self.buscarVerticeIzq(a.v, aristasVisitadas, ruta, indicesTotales)
			self.buscarVerticeDer(a.u, aristasVisitadas, ruta, indicesTotales)

			# Ordenar los indices para remover las aristas de la cola en determinado orden
			indicesTotales.sort()

			# Agrega las aristas que conectan a los vértices de un componente
			for i in range(len(indicesTotales)-1,-1,-1):
				index = indicesTotales[i]
				ar = aristasVisitadas[index]
				componentes[ind].aristas.append(ar)
				del aristasVisitadas[index]

			# Asigna la ruta de vertices generada para cada componente
			componentes[ind].vertices = ruta

		# Imprimir el contenido de cada componente generado
		for i in range(0,len(componentes)):
			print("Componente: ",i)

			for j in range(0,len(componentes[i].vertices)):
				print(componentes[i].vertices[j].nombre,", ")
			print(" ")

			for j in range(0,len(componentes[i].aristas)):
				componentes[i].aristas[j].imprimirArista()
			print(" ")

		# Segunda y Final Iteración: conectar las componentes en una sola (árbol Mínimo)

		# Asignar las aristas minimas que conecten a cada nuevo componente
		for i in range(0,len(componentes)):
			pq = []

			for j in range(0,len(componentes[i].vertices)):
				for k in range(0,len(componentes[i].vertices[j].aristas)):
					act = componentes[i].vertices[j].aristas[k]

					# Si la arista adyacente al vertice no forma parte del componente, se agrega para
					# analizar conexiones entre componentes
					if self.buscarArista(componentes[i].aristas, act) == False:
						pq.append(act)

			# Una vez analizado todo el componente se procede a elegir la arista mínima
			pq.sort(key=self.costo) # Ordena la cola de prioridad
			minima = pq[0]
			del pq[0]

			# Verificar que las aristas no se dupliquen
			if self.buscarArista(aristasVisitadas, minima) == False:
				aristasVisitadas.append(minima)
				aristasTotales.append(minima)

		return aristasTotales

	def buscarArista(self,aristasVisitadas,a):
		'Busca una arista almacenada en una lista'
		encontrado = False
		for i in range(0,len(aristasVisitadas)):
			aux = aristasVisitadas[i]

			if (aux.v == a.v and aux.u == a.u) or (aux.v == a.u and aux.u == a.v):
				encontrado = True

		return encontrado

	def buscarVerticeIzq(self,actual,aristasVisitadas,ruta,indicesTotales):
		'Busca vertices conectados entre sí para añadirlos a un componente (hacia la izquierda)'
		indices = []

		# Busca los vertices que conectan por la derecha
		for i in range(0,len(aristasVisitadas)):
			if aristasVisitadas[i].u == actual:
				indices.append(i)
				indicesTotales.append(i)

		# Añade los vertices a la ruta sin que se repitan
		for i in range(0,len(indices)):
			aux = aristasVisitadas[indices[i]].v
			self.añadirVertice(aux,ruta)

		# Búsqueda recursiva
		for i in range(0,len(indices)):
			self.buscarVerticeIzq(aristasVisitadas[indices[i]].v, aristasVisitadas, ruta, indicesTotales)

	def buscarVerticeDer(self,actual,aristasVisitadas,ruta,indicesTotales):
		'Busca vertices conectados entre sí para añadirlos a un componente (hacia la derecha)'
		indices = []

		# Busca los vertices que conectan por la izquierda
		for i in range(0,len(aristasVisitadas)):
			if aristasVisitadas[i].v == actual:
				indices.append(i)
				indicesTotales.append(i)

		# Añade los vertices a la ruta sin que se repitan
		for i in range(0,len(indices)):
			aux = aristasVisitadas[indices[i]].u
			self.añadirVertice(aux,ruta)

		# Búsqueda recursiva
		for i in range(0,len(indices)):
			self.buscarVerticeDer(aristasVisitadas[indices[i]].u, aristasVisitadas, ruta, indicesTotales)

	def añadirVertice(self,v,ruta):
		'Añade un vertice sin que se repita en una lista'
		repetido = False
		for i in range(0,len(ruta)):
			if ruta[i] == v:
				repetido = True

		# Si no se repite el vertice, se inserta
		if repetido == False:
			ruta.append(v)

# Ejecutando los objetos
programa = Boruvka()
g = Grafo(False,True);

# Generando nodos
va = Vertice("a");
vb = Vertice("b");
vc = Vertice("c");
vd = Vertice("d");
ve = Vertice("e");
vf = Vertice("f");
vi = Vertice("i");
vj = Vertice("j");

# Añadiendo nodos al grafo
g.añadirVertice(va);
g.añadirVertice(vb);
g.añadirVertice(vc);
g.añadirVertice(vd);
g.añadirVertice(ve);
g.añadirVertice(vf);
g.añadirVertice(vi);
g.añadirVertice(vj);

aa = Arista(va,vb,20)
ab = Arista(va,vf,50)
ac = Arista(va,vi,10)
ad = Arista(vi,vj,40)
ae = Arista(vb,vc,5)
af = Arista(vf,vj,30)
ag = Arista(vi,vd,8)
ah = Arista(vd,ve,4)
ai = Arista(vc,ve,15)

# Conectando nodos
g.añadirArista(aa,20)
g.añadirArista(ab,50)
g.añadirArista(ac,10)
g.añadirArista(ad,40)
g.añadirArista(ae,5)
g.añadirArista(af,30)
g.añadirArista(ag,8)
g.añadirArista(ah,4)
g.añadirArista(ai,15)

g.imprimirGrafo()

arbolMin = programa.boruvka(g)

print("Árbol de Expansión Mínimo Boruvka")
for i in range(0,len(arbolMin)):
	arbolMin[i].imprimirArista()