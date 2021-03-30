class TimSort:

	arr = []
	tamSub = 3 # Tamaño maximo para cada subarreglo

	def __init__(self,arr):
		'Constructor de la clase'
		self.arr = arr

	def timSort(self,arr,tamSub):
		'Algoritmo de Ordenamiento'
		n = len(arr)

		# Ordenar los subarreglos mediante insertionSort
		for i in range(0,n,tamSub):
			self.insertionSort(arr,i,min((i+tamSub),(n-1)))

		# Mezclar lor subarreglos y ordenarlos mediente mergeSort
		tamAct = tamSub
		while tamAct < n:

			# Mezcla los subarreglos de tamaño 2*tamAct
			for izq in range(0,n,2*tamAct):

				# izq -> med = SubArr1
				# med+1 -> der = SubArr2
				med = min((izq+tamAct-1),(n-1))
				der = min((izq+2*tamAct),(n-1))

				self.merge(arr,izq,med,der)

			tamAct = 2*tamAct


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

	def merge(self,arr,inf,med,sup):
		'Mezcla las dos sublistas de manera ordenada.'
		n1 = med-inf+1
		n2 = sup-med

		L = [0] * n1
		R = [0] * n2

		# Copiando a los arreglos auxiliares
		for i in range(0,n1):
			L[i] = arr[inf+i]

		for j in range(0,n2):
			R[j] = arr[med+1+j]

		i = j = 0
		k = inf

		# Obteniendo valores menores de ambos arreglos auxiliares
		while i<n1 and j<n2:
			if L[i]<= R[j]:
				arr[k] = L[i]
				i+=1
			else:
				arr[k] = R[j]
				j+=1
			k+=1

		# Copiando el resto de valores no asignados
		while i<n1:
			arr[k] = L[i]
			i+=1
			k+=1

		while j<n2:
			arr[k] = R[j]
			j+=1
			k+=1


# Ejecutando el objeto
programa = TimSort([2, 10, 24, 2, 10, 11, 27, 4, 2, 4, 28, 16, 9, 8])
programa.timSort(programa.arr,programa.tamSub)
print(programa.arr)