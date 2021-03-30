class MergeSort:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase.'
		self.arr = arr

	def mergesort(self, inf, sup):
		'Algoritmo de ordenamiento.'
		if inf==(sup-1): # Dos elementos
			if self.arr[inf] > self.arr[sup]:
				tmp = self.arr[inf];
				self.arr[inf] = self.arr[sup];
				self.arr[sup] = tmp;
		elif inf < sup:
			med = int((inf+sup)/2)

			self.mergesort(inf, med)
			self.mergesort(med+1, sup)
			self.merge(inf, med, sup)

	def merge(self, inf, med, sup):
		'Mezcla las dos sublistas de manera ordenada.'
		n1 = med-inf+1
		n2 = sup-med

		L = [0] * n1
		R = [0] * n2

		# Copiando a los arreglos auxiliares
		for i in range(0,n1):
			L[i] = self.arr[inf+i]

		for j in range(0,n2):
			R[j] = self.arr[med+1+j]

		i = j = 0
		k = inf

		# Obteniendo valores menores de ambos arreglos auxiliares
		while i<n1 and j<n2:
			if L[i]<= R[j]:
				self.arr[k] = L[i]
				i+=1
			else:
				self.arr[k] = R[j]
				j+=1
			k+=1

		# Copiando el resto de valores no asignados
		while i<n1:
			self.arr[k] = L[i]
			i+=1
			k+=1

		while j<n2:
			self.arr[k] = R[j]
			j+=1
			k+=1

# Ejecutando el objeto
programa = MergeSort([33,50,17,48,23,8,60])
programa.mergesort(0, len(programa.arr)-1)
print(programa.arr)