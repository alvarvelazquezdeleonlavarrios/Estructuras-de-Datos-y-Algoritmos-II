import math

class IntroSort:

	arr = []
	maxTamSub = 3 # Tamaño que define si un subarreglo se ordena mediante InsertionSort

	def __init__(self,arr):
		'Constructor de la clase'
		self.arr = arr

	def introSort(self,arr):
		'Algoritmo de Ordenamiento'
		n = len(arr)
		maxRecursion = 2*math.floor(math.log2(n))

		self.quickSort(arr,0,n-1,maxRecursion)

	def quickSort(self,arr,inicio,fin,maxRecursion):
		'Ordenamiento por QuickSort'
		# Si el tamaño de un subarreglo es muy grande, ordenar por quickSort o heapSort
		if (fin-inicio) > self.maxTamSub:

			# Ordenamiento por HeapSort
			if maxRecursion == 0:
				print("Ordenando por HeapSort (",inicio,", ",fin,")")
				self.heapSort(arr,inicio,fin)

				print(arr,"\n")
				return

			maxRecursion = maxRecursion - 1

			print("Ordenando por QuickSort (",inicio,", ",fin,")")

			# Ordenamiento por QuickSort
			if inicio < fin:

				#Partición
				izq = inicio - 1
				der = fin + 1
				pivote = arr[inicio]

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

				print(arr,"\n")

				self.quickSort(arr,inicio,der,maxRecursion)	# Sublista Izquierda
				self.quickSort(arr,der+1,fin,maxRecursion)	# Sublista Derecha

		else: # Si el tamaño de un subarreglo es pequeño, ordenar por insertionSort
			# Ordenamiento por InsertionSort
			print("Ordenando por InsertionSort (",inicio,", ",fin,")")
			self.insertionSort(arr,inicio,fin)
			print(arr,"\n")

	def heapSort(self,arr,inicio,fin):
		'Ordenamiento por HeapSort'
		n = fin-inicio+1

		# Construyendo árbol binario
		for i in range(int(n/2-1),-1,-1):
			self.heap(arr,n,i,inicio)

		# Ordenando nodo por nodo desde el árbol binario
		for i in range(n-1,0,-1):
			# Mover raíz principal hasta el final
			temp = arr[inicio]
			arr[inicio] = arr[inicio+i]
			arr[inicio+i] = temp

			# Reconstruyendo el árbol particionado
			self.heap(arr,i,0,inicio)

	def heap(self,arr,n,raiz,inicio):
		'Construcción de un árbol binario'
		l = raiz+inicio
		izq = 2*raiz+1+inicio	# Hijo izquierdo
		der = 2*raiz+2+inicio # Hijo derecho

		if izq<(n+inicio) and arr[izq]>arr[l]:
			l=izq # Raíz -> Izquierdo

		if der<(n+inicio) and arr[der]>arr[l]:
			l=der # Raiz -> Derecho

		if l!=(raiz+inicio):
			# Intercambio
			aux = arr[raiz+inicio]
			arr[raiz+inicio] = arr[l]
			arr[l] = aux

			# Construyendo sub-árbol izquierdo o derecho
			self.heap(arr,n,l-inicio,inicio)

	def insertionSort(self,arr,inicio,fin):
		'Ordenamiento por InsertionSort'
		nelem = len(self.arr)

		for i in range(inicio+1,fin+1):
			x = self.arr[i]
			k = i-1

			# Para k=-1 se ha alcanzado el extremo izquierdo
			while k>=inicio and x<self.arr[k]:
				self.arr[k+1] = self.arr[k]	# Hace hueco para insertar
				k-=1

			self.arr[k+1] = x

# Ejecutando el objeto
programa = IntroSort([2, 10, 24, 2, 10, 11, 27, 4, 2, 4, 28, 16, 9, 8, 28, 10, 13, 24, 22, 28, 0, 13, 27, 13, 3, 23, 18, 22, 8, 8])
programa.introSort(programa.arr)
print(programa.arr)