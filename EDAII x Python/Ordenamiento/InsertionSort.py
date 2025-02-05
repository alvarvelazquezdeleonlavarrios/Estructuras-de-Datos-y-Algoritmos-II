class InsertionSort:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase.'
		self.arr = arr

	def insertionSort(self):
		'Algoritmo de ordenamiento.'
		nelem = len(self.arr)

		for i in range(1,nelem):
			x = self.arr[i]
			k = i-1

			# Para k=-1 se ha alcanzado el extremo izquierdo
			while k>=0 and x<self.arr[k]:
				self.arr[k+1] = self.arr[k]	# Hace hueco para insertar
				k-=1

			self.arr[k+1] = x

# Ejecutando el objeto
programa = InsertionSort([50,17,48,23,8,60])
programa.insertionSort()
print(programa.arr)