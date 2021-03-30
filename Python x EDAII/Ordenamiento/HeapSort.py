class HeapSort:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase.'
		self.arr = arr

	def heapSort(self):
		'Algoritmo de ordenamiento.'
		n = len(self.arr)

		# Construyendo árbol binario
		for i in range(int(n/2-1),-1,-1):
			self.heap(n,i)

		# Ordenando nodo por nodo desde el árbol binario
		for i in range(n-1,0,-1):
			# Mover raíz principal hasta el final
			temp = self.arr[0]
			self.arr[0] = self.arr[i]
			self.arr[i] = temp

			# Reconstruyendo el árbol particionado
			self.heap(i,0)

	def heap(self, n, raiz):
		'Construye el árbol binario.'
		l = raiz
		izq = 2*raiz+1	# Hijo izquierdo
		der = 2*raiz+2 # Hijo derecho

		if izq<n and self.arr[izq]>self.arr[l]:
			l=izq # Raíz -> Izquierdo

		if der<n and self.arr[der]>self.arr[l]:
			l=der # Raiz -> Derecho

		if l!=raiz:
			# Intercambio
			aux = self.arr[raiz]
			self.arr[raiz] = self.arr[l]
			self.arr[l] = aux

			# Construyendo sub-árbol izquierdo o derecho
			self.heap(n,l)

programa = HeapSort([50,17,48,23,8,33,60])
programa.heapSort()
print(programa.arr)