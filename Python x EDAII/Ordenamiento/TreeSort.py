class Nodo:

	def __init__(self,valor):
		'Constructor del Nodo'
		self.valor = valor
		self.izq = None
		self.der = None


class TreeSort:

	arr = []
	cont = 0
	raiz = None

	def __init__(self,arr):
		'Constructor de la clase'
		self.arr = arr


	def treeSort(self,arr):
		'Algoritmo de Ordenamiento'
		# Llenar un árbol binario de búsqueda
		for i in range (0,len(arr)):
			self.insertar(arr[i])

		# Recorrer el árbol binario de búsqueda in-orden
		self.recorridoInOrden(self.raiz,arr)

	def insertar(self,valor):
		'Inserta un nodo al árbol binario de búsqueda'
		self.raiz = self.añadir(self.raiz,valor)


	def añadir(self,raiz,valor):
		'Añade el nodo en la posición correspondiente'
		if raiz == None:
			raiz = Nodo(valor)
			return raiz

		if valor < raiz.valor:
			raiz.izq = self.añadir(raiz.izq,valor)
		else:
			raiz.der = self.añadir(raiz.der,valor)
		return raiz

	def recorridoInOrden(self,raiz,arr):
		'Recorre el árbol de forma in-orden'
		if raiz != None:
			self.recorridoInOrden(raiz.izq,arr) # Subarbol izquierdo
			arr[self.cont] = raiz.valor
			self.cont += 1
			self.recorridoInOrden(raiz.der,arr) # Subarbol derecho

# Ejecutando el objeto
programa = TreeSort([10, 24, 2, 10, 11, 27, 4, 2, 4, 28, 16, 9, 8, 2])
programa.treeSort(programa.arr)
print(programa.arr)