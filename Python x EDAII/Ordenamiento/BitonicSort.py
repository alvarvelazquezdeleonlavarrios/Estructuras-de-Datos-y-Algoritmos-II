import math

class BitonicSort:

	arr = []

	def __init__(self,arr):
		'Constructor de la clase'
		self.arr = arr

	def bitonicSort(self,arr,inicio,tam,dir):
		'Algoritmo de Ordenamiento'
		if tam > 1:
			k = math.floor(tam/2)

			# Ordena lado izquierdo de forma ascendente (menor-mayor)
			self.bitonicSort(arr,inicio,k,True)

			# Ordena lado derecho de forma descendente (mayor-menor)
			self.bitonicSort(arr,inicio+k,k,False)

			# Mezcla dos mitades en determinado orden
			self.bitonicMerge(arr,inicio,tam,dir)

	def bitonicMerge(self,arr,inicio,tam,dir):
		'Mezcla dos subarreglos de tamaño 2^n'
		if tam > 1:
			k = math.floor(tam/2)

			for i in range(inicio,inicio+k):
				# Intercambia dependiendo el orden asignado
				if (arr[i]>arr[i+k] and dir==True) or (arr[i]<arr[i+k] and dir==False):
					arr[i],arr[i+k] = arr[i+k],arr[i]

			self.bitonicMerge(arr,inicio,k,dir)
			self.bitonicMerge(arr,inicio+k,k,dir)

# Ejecutando el objeto
# Nota: BitonicSort solo funciona con arreglos de longitud 2^n
programa = BitonicSort([10, 24, 2, 10, 11, 27, 4, 2])
programa.bitonicSort(programa.arr,0,len(programa.arr),True)
print(programa.arr)