class CycleSort:

	arr = []

	def __init__(self,arr):
		'Constructor de la clase'
		self.arr = arr

	def cycleSort(self,arr):
		'Algoritmo de Ordenamiento'
		n = len(arr)

		for inicio in range(0,n-1):
			# Objeto actual a comparar con los otros elementos
			objeto = arr[inicio]

			posicion = inicio
			for i in range(inicio+1,n):
				if arr[i]<objeto:
					posicion +=1

			# Si el objeto ya se encuentra ordenado en su posición se omite el resto
			if posicion == inicio:
				continue

			# Se ignoran elementos duplicados
			while objeto == arr[posicion]:
				posicion +=1

			# Si el objeto no se encuentra en su posición ordenada, se intercambia
			if posicion != inicio:
				x = objeto
				objeto = arr[posicion]
				arr[posicion] = x

			# Rota el resto del ciclo
			while posicion != inicio:
				posicion = inicio

				# Busca la posición correcta para los elementos a rotar
				for i in range(inicio+1,n):
					if arr[i]<objeto:
						posicion +=1

				# Se ignoran elementos duplicados
				while objeto == arr[posicion]:
					posicion +=1

				# Coloca el objeto en su posición correcta
				if objeto != arr[posicion]:
					x = objeto
					objeto = arr[posicion]
					arr[posicion] = x


# Ejecutando el objeto
programa = CycleSort([2, 10, 24, 2, 10, 11, 27, 4, 2, 4, 28, 16, 9, 8])
programa.cycleSort(programa.arr)
print(programa.arr)