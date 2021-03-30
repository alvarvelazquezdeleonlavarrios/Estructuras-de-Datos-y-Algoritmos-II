class CountingSort:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase'
		self.arr = arr

	def countingSort(self):
		'Algoritmo de ordenamiento.'
		maximo = max(self.arr)
		minimo = min(self.arr)
		rango = maximo-minimo+1
		conteo = [0] * rango	# Arreglo para conteo
		salida = [0] * len(self.arr)	# Arreglo que muestra la salida ordenada

		# Conteo de la cantidad de elementos hacia su respectivo casillero
		for i in range(0,len(self.arr)):
			conteo[self.arr[i]-minimo]+=1

		# Conteo acumulado de todos los casilleros
		for i in range(1,len(conteo)):
			conteo[i] += conteo[i-1]

		# Ordenamiento tomando como índice el conteo actual
		for i in range(len(self.arr)-1,-1,-1):
			salida[conteo[self.arr[i]-minimo]-1] = self.arr[i]
			conteo[self.arr[i]-minimo]-=1

		# Reasignando al arreglo original
		for i in range(0,len(self.arr)):
			self.arr[i] = salida[i]

# Ejecutando el objeto
programa = CountingSort([55,63,61,59,63,57,57,59,58,60])
programa.countingSort()
print(programa.arr)