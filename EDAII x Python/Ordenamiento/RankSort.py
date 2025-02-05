class RankSort:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase'
		self.arr = arr

	def rankSort(self):
		'Algoritmo de ordenamiento.'
		rangos = [0] * len(self.arr)	# Arreglo para el mapa de direcciones
		salida = [0] * len(self.arr)	# Arreglo que muestra la salida ordenada

		# Calcular el rango de cada elemento del arreglo
		for i in range(0,len(self.arr)):
			for j in range(0,i):
				if self.arr[i] >= self.arr[j]:
					rangos[i] += 1
				else:
					rangos[j] +=1

		# Impresión de direcciones para cada elemento
		print("\nRango de cada elemento (elemento -> dirección)")
		for i in range(0,len(self.arr)):
			print(self.arr[i]," -> ",rangos[i])
		print("\n")

		# Ordenar de acuerdo al rango
		for i in range(0,len(self.arr)):
			salida[rangos[i]] = self.arr[i]

		# Reasignando al arreglo original
		for i in range(0,len(self.arr)):
			self.arr[i] = salida[i]

# Ejecutando el objeto
programa = RankSort([8,0,-4,-8,7,2])
programa.rankSort()
print(programa.arr)