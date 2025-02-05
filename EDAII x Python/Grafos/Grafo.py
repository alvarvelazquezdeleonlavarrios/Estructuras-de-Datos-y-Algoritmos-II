from Vertice import Vertice
from Arista import Arista

class Grafo:
	dirigido = False
	ponderado = False

	def __init__(self, dirigido, ponderado):
		'Constructor de la clase'
		self.vertices = []
		self.aristas = []
		self.dirigido = dirigido
		self.ponderado = ponderado

	def añadirVertice(self,v):
		'Añade un nodo al grafo'
		if len(self.vertices) == 0:
			self.vertices.append(v)
		else:
			repetido = False
			for i in range(0,len(self.vertices)):
				if v == self.vertices[i]:
					repetido = True

			if repetido != True:
				self.vertices.append(v) # Añade el nodo al grafo

	def añadirArista(self,a,costo):
		'Añade una arista al grafo'
		repetido = False
		i = 0
		while i<len(self.aristas):
			if a == self.aristas[i]:
				repetido = True
			i +=1

		if repetido != True:	# La arista no está repetida
			# Grafo Ponderado/No ponderado
			if self.ponderado == False:
				a.costo = 0
			else:
				a.costo = costo

			# Grafo Dirigido/No dirigido
			if self.dirigido == False:
				a.v.agregarAdyacente(a.u)
				a.u.agregarAdyacente(a.v)

				ai = Arista(a.u,a.v,a.costo) # Arista inversa para el nodo del fin
				a.v.aristas.append(a)
				a.u.aristas.append(ai)

				self.aristas.append(a)	# Añade la arista al grafo
				self.aristas.append(ai)	# Añade la arista inversa al grafo
			else:
				a.v.agregarAdyacente(a.u)
				a.v.aristas.append(a)
				self.aristas.append(a)	# Añade la arista al grafo

	def imprimirGrafo(self):
		'Imprime las características de los nodos y aristas'
		# Imprimiendo vértices
		if len(self.vertices) != 0:
			for i in range(0,len(self.vertices)):
				print("Nodo:",self.vertices[i].nombre)
				print("Adyacentes:",self.vertices[i].imprimirAdyacentes())
		else:
			print("Sin vértices")
		print(" ")

		# Imprimiendo aristas
		if len(self.aristas) != 0:
			for i in range(0,len(self.aristas)):
				self.aristas[i].imprimirArista()
		else:
			print("Sin aristas")
		print(" ")