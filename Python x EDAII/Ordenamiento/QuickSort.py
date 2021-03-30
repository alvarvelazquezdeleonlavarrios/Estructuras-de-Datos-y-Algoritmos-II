class QuickSort:

	def __init__(self,arr):
		'Constructor de la clase'
		self.arr = arr

	def quickSort(self,arr,inf,sup):
		'Algoritmo de Ordenamiento'
		if len(arr) == 0:
			print("Sin elementos.")
		elif len(arr) == 1:
			print("Lista con un solo elemento: ",arr[0])
		elif len(arr) > 1:

			if inf < sup:

				#Partición
				izq = inf - 1
				der = sup + 1
				pivote = arr[inf]

				while (True):
					#Do-While
					izq += 1
					while (arr[izq] < pivote):
						izq += 1


					#Do-While
					der -= 1
					while (arr[der] > pivote):
						der -= 1

					if izq >= der:
						break

					arr[izq],arr[der] = arr[der],arr[izq]	# Intercambio


				self.quickSort(arr,inf,der)	# Sublista Izquierda
				self.quickSort(arr,der+1,sup)	# Sublista Derecha

# Ejecutando el objeto
programa = QuickSort([33,50,48,17,23,8,60])
programa.quickSort(programa.arr,0,len(programa.arr)-1)
print(programa.arr)