class HashModulo:

	arr = []

	def __init__(self, arr):
		'Constructor de la clase.'
		self.arr = arr

	def hashModulo(self,llave,modulo):
		'Devuelve el módulo de una llave'
		return llave % modulo

	def agregar(self,llave,mapa):
		'Agrega un elemento en una determinada posición del mapa.'
		k = self.hashModulo(llave,len(mapa))

		# Si la dirección está vacía, colocar llave
		if mapa[k] == 0:
			mapa[k] = llave
		else:
			# Colisión. Reasignación con Prueba Lineal
			for i in range(0,len(mapa)+1):
				hk = self.hashModulo(k+i,len(mapa))

				# Si encuentra una dirección vacía, colocar llave
				if mapa[hk] == 0:
					mapa[hk] = llave
					break

	def buscar(self,llave,mapa):
		'Busca un elemento en una determinada posición del mapa.'
		k = self.hashModulo(llave,len(mapa))

		# Si la posición no está vacía
		if mapa[k] != 0:
			# Encuentra la llave en la primera posición generada por la función
			if mapa[k] == llave:
				return k
			else:
				# Colisión. Búsqueda por Prueba Lineal
				for i in range(0, len(mapa)+1):
					hk = self.hashModulo(k+i,len(mapa))

					# Si ya se encontró la llave a buscar
					if mapa[hk] == llave:
						return hk
		else:
			print("No se encuentra el elemento.")
		return -1

# Ejecutando el objeto
programa = HashModulo([23,50,70,40,8,17])

# Imprimiendo módulos
for i in range(0,len(programa.arr)):
	k = programa.hashModulo(programa.arr[i], len(programa.arr))
	print(k)

mapa = [0] * len(programa.arr)	# Arreglo para el mapa

# Asigna las llaves al mapa
for i in range(0,len(programa.arr)):
	programa.agregar(programa.arr[i],mapa)

print(mapa)

print(programa.buscar(23,mapa))
print(programa.buscar(14,mapa))