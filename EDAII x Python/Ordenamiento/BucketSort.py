import math

class BucketSort():

	arr = []

	def __init__(self, arr):
		'Constructor de la clase'
		self.arr = arr

	def bucketSort(self):
		'Algoritmo de ordenamiento'
		md = self.maxDigit()
		indice = self.maxIndex()
		cubeta = [[0 for x in range(len(self.arr))] for y in range(md+1)] # Cubetas
		conteo = [0] * (md+1)	# Arreglo que almacena la posición para el siguiente elemento

		# Asigna los elementos de arr hacia sus respectivas cubetas
		for j in range(0,len(self.arr)):
			i = int(self.arr[j]/int(math.pow(10,indice)))
			cubeta[i][conteo[i]] = self.arr[j]
			conteo[i] +=1

		# Ordena las cubetas mediante otro algoritmo de ordenamiento
		print("Contenido de las cubetas")
		for j in range(0,(md+1)):
			self.bubbleSort(cubeta[j], conteo[j])

		# Reasigna los elementos ordenados al arreglo original
		k = 0
		print(" ")
		for i in range(0,(md+1)):
			for j in range(0,len(self.arr)):
				if cubeta[i][j] != 0:
					self.arr[k] = cubeta[i][j]
					k +=1

	def bubbleSort(self, A, tam):
		'Algoritmo de ordenamiento secundario.'
		n = tam
		cont = 0
		for i in range(n-1,-1,-1):
			k = 0
			for j in range(0,i):
				if A[j] > A[j+1]:
					aux = A[j]
					A[j] = A[j+1]
					A[j+1] = aux
				else:
					k = k+1
			cont = cont + 1;
			if k==i:
				print("Conjunto ordenado en la pasada", cont)
				break
		print(A)


	def maxDigit(self):
		'Obtiene el dígito máximo del elemento mayor [0,9]'
		maximo = max(self.arr)
		i = 1
		while int(maximo/i) >= 10:
			i = i*10
		return int(maximo/i)

	def maxIndex(self):
		'Obtiene cuantas veces debe dividirse el mayor elemento entre 10 [0,9]'
		maximo = max(self.arr)
		i = 0
		while maximo >= 10:
			i +=1
			maximo = maximo/10
		return i

# Ejecutando el objeto
programa = BucketSort([15,20,43,11,39,12,31,42])
programa.bucketSort()
print(programa.arr)