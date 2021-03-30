class PigeonholeSort:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase'
		self.arr = arr

	def pigeonholeSort(self):
		'Algoritmo de ordenamiento.'
		maximo = max(self.arr)
		minimo = min(self.arr)
		rango = maximo-minimo+1
		conteo = [0] * rango	# Arreglo para conteo

		# Conteo de la cantidad de elementos hacia su respectivo casillero
		for i in range(0,len(self.arr)):
			conteo[self.arr[i]-minimo]+=1

		# Reasignando elementos de manera ordenada
		i = 0
		for cuenta in range(0,rango):
			while conteo[cuenta] > 0:
				conteo[cuenta] -= 1
				self.arr[i] = cuenta + minimo
				i = i+1

# Ejecutando el objeto
programa = PigeonholeSort([55,63,61,59,63,57,57,59,58,60])
programa.pigeonholeSort()
print(programa.arr)