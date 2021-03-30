class RadixSort:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase.'
		self.arr = arr

	def radixSort(self):
		'Algoritmo de ordenamiento.'
		maximo = max(self.arr)
		salida = [0] * len(self.arr)

		exp = 1
		while int(maximo/exp) > 0:
			md = self.digitoMax(exp)
			cont1 = [0] * (md+1)
			cont2 = [0] * (md+1)

			# Arreglos de conteo
			for j in range(0,len(self.arr)):
				k = int((self.arr[j]/exp)%10)
				cont1[k]+=1
				cont2[k]+=1

			# Conteo acumulado
			for j in range(1,len(cont2)):
				cont2[j] += cont2[j-1]

			# Ordenando elementos
			for j in range(0,len(self.arr)):
				k = int((self.arr[j]/exp)%10)
				salida[cont2[k]-cont1[k]] = self.arr[j]
				cont1[k]-=1

			# Reasignando al arreglo original
			for j in range(0,len(self.arr)):
				self.arr[j] = salida[j]

			exp *= 10

	def digitoMax(self, exp):
		'Obtiene el dígito máximo en la unidad actual.'
		max1 = int((self.arr[0]/exp)%10)

		for i in range(1,len(self.arr)):
			act = int((self.arr[i]/exp)%10)
			if act > max1:
				max1 = act

		return max1

# Ejecutando el objeto
programa = RadixSort([3311,3211,2121,1123,1321,2133])
programa.radixSort()
print(programa.arr)