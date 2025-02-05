class GnomeSort:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase'
		self.arr = arr

	def gnomeSort(self):
		'Algoritmo de ordenamiento'
		n = len(self.arr)
		i = 1; j = 2

		while i < n:
			if self.arr[i-1] > self.arr[i]:
				self.arr[i-1],self.arr[i] = self.arr[i],self.arr[i-1]	# Intercambio
				i -=1

				# Si i llega al tope izquierdo, entonces vuelve a la posición actual j
				if i == 0:
					i = j
					j +=1
			else:
				i +=1
				j +=1

# Ejecutando el objeto
programa = GnomeSort([4,3,12,21,7,15,23,30])
programa.gnomeSort()
print(programa.arr)