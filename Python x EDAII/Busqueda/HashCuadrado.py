class HashCuadrado:

	arr = []
	tamaño = 100	# Tamaño para el mapa

	def __init__(self, arr):
		'Constructor de la clase'
		self.arr = arr

	def hashCuadrado(self, llave, tamaño):
		'Devuelve la posición virtual de la llave'
		c = llave * llave
		med = (self.maximaPotencia(c)-1)//2	# Determina en donde inicia el numero para la posición del arreglo
		inf = c; sup = c; rango = 0

		# Determina la posición de la frontera media (donde inicia el número para la posición en el arreglo)
		exp = 1
		while tamaño//exp > 0:
			rango +=1
			exp *= 10

		# Frontera Superior (llega hasta el inicio de la posición)
		# Recorre el punto decimal a la izquierda
		for i in range(med,0,-1):
			sup = sup//10

		for i in range(med+rango-1,0,-1):
			inf = inf//10

		for i in range(med,med+rango-1):
			inf = inf*10

		c = sup-inf
		return c

	def maximaPotencia(self, n):
		'Recorre el munto decimal hasta la izquierda del número cuadrado'
		maximo = 0; exp = 1
		while n//exp > 0:
			maximo +=1
			exp *= 10
		return maximo

	def agregar(self, llave, mapa):
		'Agrega un elemento en una determinada posición del mapa.'
		k = self.hashCuadrado(llave,len(mapa))

		# Si la dirección está vacía, colocar llave
		if mapa[k]==0:
			mapa[k]=llave
		else:
			# Colisión. Reasignación con Prueba Lineal
			for i in range(1,len(mapa)+1):
				k = k+1
				# Si se llega al final del arreglo antes, se regresa
				if k >= len(mapa):
					k = k - len(mapa)

				# Si encuentra una dirección vacía, coloca la llave
				if mapa[k]==0:
					mapa[k]=llave
					break

	def buscar(self, llave, mapa):
		'Busca un elemento en una determinada posición del mapa.'
		k = self.hashCuadrado(llave,len(mapa))

		# Si la posición no está vacía
		if mapa[k]!=0:
			# Encuentra la llave en la primera posición generada por la función
			if mapa[k]==llave:
				return k
			else:
				# Colisión. Búsqueda por Prueba Lineal
				for i in range(1,len(mapa)+1):
					k = k+1
					# Si se llega al final del arreglo antes, se regresa
					if k >= len(mapa):
						k = k - len(mapa)

					# Si ya se encontró la llave a buscar
					if mapa[k]==llave:
						return k
		else:
			pass
			# No se encuentra el elemento en su primer posición virtual
		return -1

# Ejecutando el objeto
programa = HashCuadrado([7259,359])

# Imprimiendo posiciones virtuales
print("Elemento -> Posición Virtual")
for i in range(0,len(programa.arr)):
	k = programa.hashCuadrado(programa.arr[i], programa.tamaño)
	print(programa.arr[i]," -> ",k)

mapa = [0] * programa.tamaño	# Arreglo para el mapa

# Asigna las llaves al mapa
for i in range(0,len(programa.arr)):
	programa.agregar(programa.arr[i],mapa)

j = programa.buscar(7259,mapa)
print(j)

j = programa.buscar(1440,mapa)
print(j)