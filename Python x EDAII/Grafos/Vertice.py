class Vertice:
	nombre = " "
	visitado = False

	def __init__(self, nombre):
		'Constructor de la clase'
		self.nombre = nombre
		self.adyacentes = []	# Crea una nueva lista vacía para cada vértice !!!
		self.aristas = []	# Crea una nueva lista vacía para cada vértice !!!

	def agregarAdyacente(self,v):
		'Agrega un nodo vecino a este'
		if len(self.adyacentes) == 0:
			self.adyacentes.append(v)
		else:
			repetido = False
			for i in range(0,len(self.adyacentes)):
				if v == self.adyacentes[i]:
					repetido = True

			if repetido != True:
				self.adyacentes.append(v)

	def imprimirAdyacentes(self):
		'Muestra en pantalla el nombre de los nodos vecinos de este'
		nombres = []
		if len(self.adyacentes) != 0:
			for i in range(0,len(self.adyacentes)):
				nombres.append(self.adyacentes[i].nombre)
			return nombres
		else:
			return "Sin nodos adyacentes"