class SlowSort:

	arr = []	# Arreglo de elementos

	def __init__(self, arr):
		self.arr = arr

	def slowSort(self, arr, inf, sup):
		'Algoritmo de ordenamiento.'
		if inf >= sup:
			return

		m = int((inf+sup)/2)
		self.slowSort(arr,inf,m)	# Subarreglo izquierdo
		self.slowSort(arr,m+1,sup)	# Subarreglo derecho

		if arr[sup] < arr[m]:
			# Intercambio
			temp = arr[sup]
			arr[sup] = arr[m]
			arr[m] = temp

		self.slowSort(arr,inf,sup-1)

# Ejecutando objetos
programa = SlowSort([50,17,48,23,8,60])
programa.slowSort(programa.arr,0,len(programa.arr)-1)
print(programa.arr)