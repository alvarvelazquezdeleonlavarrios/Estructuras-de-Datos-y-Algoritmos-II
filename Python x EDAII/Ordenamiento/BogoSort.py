import random

class BogoSort:

	arr = []	# Arreglo de elementos

	def __init__(self, arr):
		self.arr = arr

	def bogoSort(self):
		'Algoritmo de ordenamiento.'
		cont = 0
		while not self.ordenado(self.arr):
			random.shuffle(self.arr)
			cont += 1
		
		print(self.arr)
		print("Arreglo ordenado en la iteración ",cont)

	def ordenado(self,arr):
		'Define si un arreglo está ordenado'
		k=0
		ordenado = False

		for i in range(0,len(arr)-1):
			if arr[i] < arr[i+1]:
				k += 1

		if k == len(arr)-1:
			ordenado = True

		return ordenado

# Ejecutando objetos
programa = BogoSort([50,17,48,23,8,60])
programa.bogoSort()