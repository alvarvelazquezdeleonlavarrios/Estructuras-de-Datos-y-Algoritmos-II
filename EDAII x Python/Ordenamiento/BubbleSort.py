class BubbleSort:

	arr = []	# Arreglo de elementos

	def __init__(self, arr):
		self.arr = arr

	def bubbleSort(self):
		'Algoritmo de ordenamiento.'
		n = len(self.arr)
		cont = 0
		for i in range(n-1,-1,-1):
			k = 0
			for j in range(0,i):
				if self.arr[j] > self.arr[j+1]:
					aux = self.arr[j]
					self.arr[j] = self.arr[j+1]
					self.arr[j+1] = aux
				else:
					k = k+1
			cont = cont + 1;
			if k==i:
				print("Conjunto ordenado en la pasada", cont)
				break
		print(self.arr)

# Ejecutando objetos
programa = BubbleSort([50,17,48,23,8,60])
programa.bubbleSort()

programa2 = BubbleSort([50,17])
programa2.bubbleSort()