class ShakerSort:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase'
		self.arr = arr

	def shakerSort(self):
		'Algoritmo de ordenamiento'
		n = len(self.arr)
		inf = 0
		sup = n-1

		while inf <= sup:
			# Pasada de Derecha a Izquierda
			for j in range(sup,inf,-1):
				if self.arr[j] < self.arr[j-1]:
					self.arr[j],self.arr[j-1] = self.arr[j-1],self.arr[j]	# Intercambio
			inf +=1

			for j in range(inf,sup):
				if self.arr[j] > self.arr[j+1]:
					self.arr[j],self.arr[j+1] = self.arr[j+1],self.arr[j]	# Intercambio
			sup -=1

# Ejecutando el objeto
programa = ShakerSort([8,3,7,4,9,5,2,1])
programa.shakerSort()
print(programa.arr)