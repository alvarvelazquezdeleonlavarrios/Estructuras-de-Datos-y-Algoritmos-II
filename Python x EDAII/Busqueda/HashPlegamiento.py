import math

class HashPlegamiento:

	arr = []
	numDigitos = 3; tamaño = 100	# Tamaño para el mapa

	def __init__(self, arr):
		'Constructor de la clase'
		self.arr = arr

	def hashPlegamiento(self, llave, tamaño):
		'Devuelve la posición virtual de la llave'
		k = self.maximaPotencia(llave)
		p = k//self.numDigitos

		suma = 0
		# Se parte el elemento en dígitos
		for i in range(1,p+1):
			potencia = int(math.pow(10,k-(self.numDigitos*i)))
			part = self.particion(llave,potencia)
			suma = suma + part

		# Si sobran dígitos a la derecha se suman
		if k%self.numDigitos != 0:
			potencia = int(math.pow(10,p))
			c = llave % potencia
			suma = suma + c

		# Regresa la posición para el arreglo (toma los dígitos de la derecha)
		posicion = suma % tamaño
		return posicion

	def agregar(self, llave, mapa):
		'Agrega un elemento en una determinada posición del mapa.'
		k = self.hashPlegamiento(llave,len(mapa))

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
		k = self.hashPlegamiento(llave,len(mapa))

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

	def maximaPotencia(self, n):
		'Recorre el punto decimal hasta la izquierda de la clave. Obtiene el número máximo de dígitos'
		maximo = 0; exp = 1
		while(n//exp != 0):
			maximo +=1
			exp *=10
		return maximo

	def particion(self, llave, potencia):
		'Genera una sección de dígitos a partir de la clave'
		inf = llave; sup = llave

		sup = sup//potencia

		inf = inf//(potencia * int(math.pow(10,self.numDigitos)))
		inf = inf * int(math.pow(10,self.numDigitos))

		c = sup - inf
		return c

# Ejecutando el objeto
programa = HashPlegamiento([30342034,32565342])

# Muestra las características del elemento aplicado a la función hash
print("Elemento -> Posición Virtual")
for i in range(0,len(programa.arr)):
	k = programa.hashPlegamiento(programa.arr[i],programa.tamaño)
	print(programa.arr[i]," -> ",k)

mapa = [0] * programa.tamaño 	# Arreglo para el mapa

# Asigna las llaves al mapa
for i in range(0,len(programa.arr)):
	programa.agregar(programa.arr[i],mapa)

j = programa.buscar(30342034,mapa)
print(j)

j = programa.buscar(32457492,mapa)
print(j)