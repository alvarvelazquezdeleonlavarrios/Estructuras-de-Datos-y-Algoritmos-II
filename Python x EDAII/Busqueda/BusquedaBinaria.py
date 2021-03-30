class BusquedaBinaria:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase'
		self.arr = arr

	def busquedaBinariaIndice(self, x, inf, sup):
		'Devuelve la posición en donde se encuentra el elemento a buscar.'
		while inf <= sup:
			med = int((inf+sup)/2)
			if x == self.arr[med]:
				return med
			else:
				if self.arr[med] < x:
					inf = med+1
				else:
					sup = med-1
		return -1

	def busquedaBinariaExistente(self, x, inf, sup):
		'Determina si el elemento a buscar se encuentra en el arreglo.'
		while inf < sup:
			med = int((inf+sup)/2)
			if x == self.arr[med]:
				return True
			else:
				if self.arr[med] < x:
					inf = med+1
				else:
					sup = med-1
		return False

	def busquedaBinariaCantidad(self, x, inf, sup):
		'Determina cuantos elementos existen con el valor a buscar.'
		cantidad = 0
		while inf <= sup:
			med = int((inf+sup)/2)
			if x == self.arr[med]:
				for i in range(inf, sup+1):
					if self.arr[i] == x:
						cantidad += 1
				return cantidad
			else:
				if self.arr[med] < x:
					inf = med+1
				else:
					sup = med-1
		return cantidad

	def busquedaBinariaRecursivaIndice(self, x, inf, sup):
		'Devuelve la posición en donde se encuentra el elemento a buscar.'
		if inf > sup:
			return -1
		med = int((inf+sup)/2)
		if self.arr[med] == x:
			return med
		elif self.arr[med] < x:
			return self.busquedaBinariaRecursivaIndice(x,med+1,sup)
		else:
			return self.busquedaBinariaRecursivaIndice(x,inf,med-1)

	def  busquedaBinariaRecursivaExistente(self, x, inf, sup):
		'Determina si el elemento a buscar se encuentra en el arreglo.'
		if inf > sup:
			return False
		med = int((inf+sup)/2)
		if self.arr[med] == x:
			return True
		elif self.arr[med] < x:
			return self.busquedaBinariaRecursivaExistente(x,med+1,sup)
		else:
			return self.busquedaBinariaRecursivaExistente(x,inf,med-1)

	def busquedaBinariaRecursivaCantidad(self, x, inf, sup):
		'Determina cuantos elementos existen con el valor a buscar.'
		cantidad = 0
		if inf > sup:
			cantidad = 0
		med = int((inf+sup)/2)
		if self.arr[med] == x:
			for i in range(inf, sup+1):
				if self.arr[i] == x:
					cantidad += 1
			return cantidad
		elif self.arr[med] < x:
			return self.busquedaBinariaRecursivaCantidad(x,med+1,sup)
		else:
			return self.busquedaBinariaRecursivaCantidad(x,inf,med-1)

	# Algoritmo de ordenamiento
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
programa = BusquedaBinaria([1,4,6,9,3,2,10,12,1,20,1,3])
programa.mergesort(0, len(programa.arr)-1)
print(programa.arr)

print(programa.busquedaBinariaIndice(4,0,len(programa.arr)-1))
print(programa.busquedaBinariaExistente(5,0,len(programa.arr)-1))
print(programa.busquedaBinariaCantidad(1,0,len(programa.arr)-1))
print(programa.busquedaBinariaRecursivaIndice(12,0,len(programa.arr)-1))
print(programa.busquedaBinariaRecursivaExistente(12,0,len(programa.arr)-1))
print(programa.busquedaBinariaRecursivaCantidad(1,0,len(programa.arr)-1))