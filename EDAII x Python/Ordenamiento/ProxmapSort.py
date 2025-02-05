class ProxmapSort:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase'
		self.arr = arr

	def proxmapSort(self):
		'Algoritmo de ordenamiento.'
		maximo = max(self.arr)
		minimo = min(self.arr)
		rango = maximo-minimo+1
		hitCount = [0] * rango	# Arreglo para conteo
		proxMap = [0] * rango	# Arreglo para conteo
		locations = [0] * len(self.arr)	# Arreglo para el mapa de direcciones
		salida = [0] * len(self.arr)	# Arreglo que muestra la salida ordenada

		# Conteo de la cantidad de elementos hacia su respectivo casillero
		for i in range(0,len(self.arr)):
			hitCount[self.arr[i]-minimo]+=1

		# Conteo acumulado de todos los casilleros
		total = 0
		for i in range(0,rango):
			if hitCount[i] == 0:
				proxMap[i] = -9999
			else:
				proxMap[i] = total
				total = total + hitCount[i]

		# Inicializa un mapa de direcciones
		for i in range(0,len(locations)):
			locations[i] = proxMap[self.arr[i]-minimo]

		# Ordenamiento tomando como indice la localización actual
		for i in range(0, len(salida)):
			inicio = locations[i]

			for j in range(inicio,len(salida)):
				# Si la posición está vacía, se inserta
				if salida[j] == 0:
					salida[j] = self.arr[i]
					break
				elif self.arr[i] < salida[j]:	# Si ya está ocupada la posición se busca la siguiente
					fin = j+1

					# Busca la siguiente posición vacía
					while salida[fin] != 0:
						fin += 1

					# Desplaza los elementos agregados a la derecha
					for k in range(fin-1,j,-1):
						salida[k+1] = salida[k]
						salida[j] = self.arr[i]

					break

		# Reasignando al arreglo original
		for i in range(0,len(self.arr)):
			self.arr[i] = salida[i]

# Ejecutando el objeto
programa = ProxmapSort([55,63,61,59,63,57,57,59,58,60])
programa.proxmapSort()
print(programa.arr)