class StoogeSort:

	arr = []	# Arreglo de elementos

	def __init__(self, arr):
		self.arr = arr

	def stoogeSort(self, arr, inf, sup):
		'Algoritmo de ordenamiento.'
		if arr[inf] > arr[sup]:
			# Intercambio
			temp = arr[sup]
			arr[sup] = arr[inf]
			arr[inf] = temp

		# Si el subarreglo tiene más de dos elementos, se ordenan por tercios
		if (sup-inf+1) > 2:
			t = int((sup-inf+1)/3)
			self.stoogeSort(arr,inf,sup-t)	# Se ordenan los primeros dos tercios del arreglo
			self.stoogeSort(arr,inf+t,sup)	# Se ordenan los ultimos dos tercios del arreglo
			self.stoogeSort(arr,inf,sup-t)	# Se vuelven a ordenar los primeros dos tercios del arreglo

# Ejecutando objetos
programa = StoogeSort([50,17,48,23,8,60])
programa.stoogeSort(programa.arr,0,len(programa.arr)-1)
print(programa.arr)