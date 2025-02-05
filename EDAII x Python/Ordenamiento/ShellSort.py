class ShellSort:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase'
		self.arr = arr

	def shellSort(self):
		'Algoritmo de ordenamiento'
		n = len(self.arr)
		h = n//2

		# Genera las sublistas correspondientes y a partir del elemento medio realiza Insertion Sort
		while h > 0:
			# Insertion Sort
			for i in range(h,n):
				tmp = self.arr[i]
				j = i
				# Si la parte izquierda a analizar ya está ordenada, sale del ciclo
				while j>=h and self.arr[j-h]>tmp:
					self.arr[j] = self.arr[j-h]
					j -= h
				self.arr[j] = tmp	 # Coloca el elemento inicial en su posición correcta
			h //= 2

# Ejecutando el objeto
programa = ShellSort([13,8,21,6,10,2])
programa.shellSort()
print(programa.arr)