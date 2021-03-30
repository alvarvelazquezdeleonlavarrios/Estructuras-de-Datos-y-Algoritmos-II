class SelectionSort:

	arr = []

	def __init__(self, arr):
		'Constructor del objeto.' 
		self.arr = arr

	def selectionSort(self):
		'Algoritmo de ordenamiento.'
		n = len(self.arr)

		for i in range(0,n):
			minimo = i
			for j in range(i+1,n):
				if self.arr[j] < self.arr[minimo]:
					minimo = j

			# Intercambio
			aux = self.arr[minimo]
			self.arr[minimo] = self.arr[i]
			self.arr[i] = aux

# Ejecutanto el objeto
programa = SelectionSort([50,17,48,23,8,60])
programa.selectionSort()
print(programa.arr)