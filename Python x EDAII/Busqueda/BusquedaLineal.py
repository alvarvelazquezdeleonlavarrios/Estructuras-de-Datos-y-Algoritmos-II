class BusquedaLineal:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase.'
		self.arr = arr

	def busquedaLinealIndice(self, x):
		'Devuelve la posición en donde se encuentra el elemento a buscar.'
		indice = -1
		for i in range(0, len(self.arr)):
			if x == self.arr[i]:
				indice = i
		return indice

	def busquedaLinealExistente(self, x):
		'Determina si el elemento a buscar se encuentra en el arreglo.'
		encontrado = False
		for i in range(0, len(self.arr)):
			if x == self.arr[i]:
				encontrado = True
		return encontrado

	def busquedaLinealCantidad(self, x):
		'Determina cuantos elementos existen con el valor a buscar.'
		cantidad = 0
		for i in range(0, len(self.arr)):
			if x == self.arr[i]:
				cantidad += 1
		return cantidad

	def busquedaLinealRecursivaIndice(self, x, inicio, fin):
		'Devuelve la posición en donde se encuentra el elemento a buscar.'
		indice = 0
		if inicio > fin:
			indice = -1
		else:
			if self.arr[inicio] == x:
				indice = inicio
			else:
				indice = self.busquedaLinealRecursivaIndice(x,inicio+1,fin)
		return indice

	def busquedaLinealRecursivaExistente(self, x, inicio, fin):
		'Determina si el elemento a buscar se encuentra en el arreglo.'
		encontrado = False
		if inicio > fin:
			encontrado = False
		else:
			if self.arr[inicio] == x:
				encontrado = True
			else:
				encontrado = self.busquedaLinealRecursivaExistente(x,inicio+1,fin)
		return encontrado

	def busquedaLinealRecursivaCantidad(self, x, inicio, fin):
		'Determina cuantos elementos existen con el valor a buscar.'
		cantidad = 0
		if inicio > fin:
			cantidad = 0
		else:
			if self.arr[inicio] == x:
				for i in range(inicio, fin+1):
					if self.arr[i] == x:
						cantidad += 1
			else:
				cantidad = self.busquedaLinealRecursivaCantidad(x,inicio+1,fin)
		return cantidad

# Ejecutando el objeto
programa = BusquedaLineal([4,6,9,1,3,2,10,12,1])
print(programa.busquedaLinealIndice(12))
print(programa.busquedaLinealExistente(7))
print(programa.busquedaLinealCantidad(1))
print(programa.busquedaLinealRecursivaIndice(3,0,len(programa.arr)-1))
print(programa.busquedaLinealRecursivaExistente(10,0,len(programa.arr)-1))
print(programa.busquedaLinealRecursivaCantidad(1,0,len(programa.arr)-1))