from Nodo import Nodo

class ArbolBinario:

	def __init__(self,raiz):
		'Constructor de la clase'
		self.raiz = raiz

	def añadir(self,nodo):
		'Añade un nodo al árbol'
		if self.raiz == None:
			self.raiz = nodo
			print("Nodo raíz generado")
		else:
			if self.buscarNodo(self.raiz,nodo.valor) == True:
				print("El nodo ya existe en el árbol")
			else:
				self.añadirUtil(self.raiz,nodo)
				self.balancear(self)

	def añadirUtil(self,padre,hijo):
		'Pasos para añadir un nodo'
		if hijo.valor < padre.valor:	# Se asigna al subárbol izquierdo
			if padre.izquierdo != None:
				self.añadirUtil(padre.izquierdo, hijo)	# Asignación recursiva
			else:
				padre.izquierdo = hijo
				padre.izquierdo.padre = padre	# Al nodo hijo se le asigna su padre
		elif hijo.valor > padre.valor:	# Se asigna al subárbol derecho
			if padre.derecho != None:
				self.añadirUtil(padre.derecho, hijo)	# Asignación recursiva
			else:
				padre.derecho = hijo
				padre.derecho.padre = padre	# Al nodo hijo se le asigna su padre

	def eliminar(self,valor):
		'Elimina un nodo del árbol'
		if self.raiz == None:
			print("Árbol vacío")
		else:
			self.eliminarUtil(self.raiz,valor)
			self.balancear(self)

	def eliminarUtil(self,padre,valor):
		'Pasos para eliminar el nodo'
		eliminar = self.obtenerNodo(padre,valor)

		if eliminar == None:
			print("El nodo no existe en el árbol")
		else:
			# Buscar nodo predecesor (menor-anterior del nodo a eliminar, o bien,
			# el nodo de mayor valor del subárbol izquierdo)
			if eliminar.izquierdo == None and eliminar.derecho == None:
				# Se elimina un nodo hoja
				if eliminar.padre != None:
					if eliminar.valor < eliminar.padre.valor:	# Nodo izquierdo
						eliminar.padre.izquierdo= None
					else:	# Nodo derecho
						eliminar.padre.derecho = None
					eliminar.padre = None
				else: # Se elimina el único nodo del árbol
					self.raiz = None
			elif eliminar.izquierdo != None and eliminar.derecho == None:
				# El nodo a eliminar (rama) tiene hijo izquierdo, pero no hijo derecho
				eliminar.valor = eliminar.izquierdo.valor
				eliminar.derecho = eliminar.izquierdo.derecho
				eliminar.izquierdo = eliminar.izquierdo.izquierdo

				eliminar.izquierdo.padre = eliminar
				eliminar.derecho.padre = eliminar
			elif eliminar.izquierdo == None and eliminar.derecho != None:
				# El nodo a eliminar (rama) tiene hijo derecho, pero no hijo izquierdo
				eliminar.valor = eliminar.derecho.valor
				eliminar.derecho = eliminar.derecho.derecho
				eliminar.izquierdo = eliminar.derecho.izquierdo

				eliminar.izquierdo.padre = eliminar
				eliminar.derecho.padre = eliminar
			elif eliminar.izquierdo != None and eliminar.derecho != None:
				# El nodo a eliminar (rama o raíz) tiene a sus dos hijos
				reemplazo = self.predecesor(eliminar.izquierdo)

				if reemplazo.izquierdo != None:
					# Si el nodo reemplazo tiene un subárbol izquierdo, se recorre
					eliminar.valor = reemplazo.valor
					reemplazo.valor = reemplazo.izquierdo.valor
					reemplazo.derecho = reemplazo.izquierdo.derecho
					reemplazo.izquierdo = reemplazo.izquierdo.izquierdo

					reemplazo.izquierdo.padre = reemplazo
					reemplazo.derecho.padre = reemplazo
				else:
					# Si se llegó a un nodo hoja se elimina (para el nodo raíz)
					aux = reemplazo.padre.valor
					eliminar.valor = reemplazo.valor
					if reemplazo.valor < aux:	# El nodo reemplazo es hijo izquierdo
						reemplazo.padre.izquierdo = None
					else:
						reemplazo.padre.derecho = None
					reemplazo,padre = None

		# Se limpia la referencia actual del nodo a eliminar
		eliminar = None

	def buscarNodo(self,raiz,valor):
		'Determina si un nodo existe en el árbol'
		# Si se trata de buscar en un árbol vacío
		if raiz == None:
			print("Árbol vacío")
			return False
		else:
			if valor == raiz.valor:	# Se encuentra en el nodo actual
				return True
			elif valor < raiz.valor:	# Se busca en el subárbol izquierdo
				if raiz.izquierdo != None:
					encontrado = self.buscarNodo(raiz.izquierdo,valor)
					return encontrado
				else:
					return False # Si ya no hay hijos en donde buscar, no existe el nodo
			elif valor > raiz.valor:	# Se busca en el subárbol derecho
				if raiz.derecho != None:
					encontrado = self.buscarNodo(raiz.derecho,valor)
					return encontrado
				else:
					return False # Si ya no hay hijos en donde buscar, no existe el nodo
			else:
				return False

	def obtenerNodo(self,raiz,valor):
		'Busca un nodo dentro del árbol'
		# Si se trata de buscar en un árbol vacío
		if raiz == None:
			print("Árbol vacío")
			return None
		else:
			if valor == raiz.valor:	# Se encuentra en el nodo actual
				return raiz
			elif valor < raiz.valor:	# Se busca en el subárbol izquierdo
				if raiz.izquierdo != None:
					encontrado = self.obtenerNodo(raiz.izquierdo,valor)
					return encontrado
				else:
					return None # Si ya no hay hijos en donde buscar, no existe el nodo
			elif valor > raiz.valor:	# Se busca en el subárbol derecho
				if raiz.derecho != None:
					encontrado = self.obteberNodo(raiz.derecho,valor)
					return encontrado
				else:
					return None # Si ya no hay hijos en donde buscar, no existe el nodo
			else:
				return None

	def predecesor(self,hijo):
		'Obtiene el mayor nodo de un subárbol izquierdo'
		if hijo.derecho != None:	# Si todavía hay hijos derechos, se visitan
			return self.predecesor(hijo.derecho)
		else:
			return hijo # Se llega al último derecho posible

	def balancear(self,arbol):
		'Balancea el árbol binario de búsqueda'
		# Revisar si el árbol sigue siendo balanceado
		balance = self.balanceado(self.raiz)
		if balance == False:
			# Realizar recorrido infijo para obtener los elementos ordenados de menor a mayor
			nodos = []
			print("Recorrido infijo del árbol: ")
			self.infijoUtil(self.raiz,nodos)

			# Limpiar enlaces de los nodos
			i = 0
			while i<len(nodos):
				nodos[i].padre = None
				nodos[i].izquierdo = None
				nodos[i].derecho = None
				i +=1

			# Redistribuir el árbol
			medio = int(len(nodos)/2)
			n = nodos[medio]
			arbolBin = ArbolBinario(n)

			# Nodos del nuevo subárbol izquierdo
			izq = nodos[0:medio]
			# Nodos del nuevo subárbol derecho
			der = nodos[medio+1:len(nodos)]

			self.redistribuir(arbolBin, izq)	# Nuevo subárbol izquierdo
			self.redistribuir(arbolBin, der)	# Nuevo subárbol derecho

			# Recorriendo el nuevo árbol balanceado
			print("Recorrido BFS del nuevo árbol (balanceado): ")
			arbolBin.BFS()

			arbol.raiz = arbolBin.raiz

	def balanceado(self,raiz):
		'Determina si el árbol está balanceado'
		niveles = []
		self.niveles(raiz,niveles,0)
		maximo = max(niveles)
		minimo = min(niveles)

		if (maximo-minimo) == 1:
			print("Árbol balanceado")
			return True
		elif (maximo-minimo) == 0:
			print("Árbol Perfectamente Balanceado")
			return True
		else:
			print("Árbol No Balanceado")
			return False

	def redistribuir(self,arbol,nodos):
		'Redistribuye los nodos para generar un árbol balanceado'
		if len(nodos) == 2:
			arbol.añadirUtil(arbol.raiz, nodos[1])
			arbol.añadirUtil(arbol.raiz, nodos[0])
		elif len(nodos) == 1:
			arbol.añadirUtil(arbol.raiz, nodos[0])
		elif len(nodos) >= 3:
			medio = int(len(nodos)/2)
			arbol.añadirUtil(arbol.raiz, nodos[medio])

			# Nodos del nuevo subárbol izquierdo
			izq = nodos[0:medio]
			# Nodos del nuevo subárbol derecho
			der = nodos[medio+1:len(nodos)]

			self.redistribuir(arbol, izq)	# Nuevo subárbol izquierdo
			self.redistribuir(arbol, der)	# Nuevo subárbol derecho

	def altura(self,raiz):
		'Determina la altura del árbol'
		niveles = []
		self.niveles(raiz,niveles,0)
		maximo = max(niveles)
		return maximo

	def BFS(self):
		'Recorrido BFS (por capas) del árbol'
		raiz = self.raiz
		cola = []
		if raiz != None:
			cola.append(raiz)
			while len(cola) != 0:
				raiz = cola.pop(0)
				print(raiz.valor)

				if raiz.izquierdo != None:
					cola.append(raiz.izquierdo)
				if raiz.derecho != None:
					cola.append(raiz.derecho)

	def prefijo(self,raiz):
		'Recorrido prefijo del árbol'
		print(raiz.valor)	# Se visita al nodo
		# Subárbol izquierdo
		if raiz.izquierdo != None:
			self.prefijo(raiz.izquierdo)
		# Subárbol derecho
		if raiz.derecho != None:
			self.prefijo(raiz.derecho)

	def infijo(self,raiz):
		'Recorrido infijo del árbol'
		# Subárbol izquierdo
		if raiz.izquierdo != None:
			self.infijo(raiz.izquierdo)
		print(raiz.valor)	# Se visita al nodo
		# Subárbol derecho
		if raiz.derecho != None:
			self.infijo(raiz.derecho)

	def infijoUtil(self,raiz,nodos):
		'Recorrido infijo del árbol para obtener una lista ordenada'
		# Subárbol izquierdo
		if raiz.izquierdo != None:
			self.infijoUtil(raiz.izquierdo, nodos)
		print(raiz.valor)	# Se visita al nodo
		nodos.append(raiz)
		# Subárbol derecho
		if raiz.derecho != None:
			self.infijoUtil(raiz.derecho, nodos)

	def postfijo(self,raiz):
		'Recorrido postfijo del árbol'
		# Subárbol izquierdo
		if raiz.izquierdo != None:
			self.infijo(raiz.izquierdo)
		# Subárbol derecho
		if raiz.derecho != None:
			self.infijo(raiz.derecho)
		print(raiz.valor)	# Se visita al nodo

	def niveles(self,raiz,niveles,actual):
		'Determina el nivel de un nodo sin o con un solo hijo'
		alt = actual

		# Si el nodo no tiene a alguno de sus hijos, se puede generar su nivel (tope)
		if raiz.izquierdo == None or raiz.derecho == None:
			niveles.append(alt)

		# Subárbol izquierdo
		if raiz.izquierdo != None:
			alt +=1	# Llega al siguiente nivel
			self.niveles(raiz.izquierdo, niveles, alt)
			alt -=1	# Regresa al nivel actual

		# Subárbol derecho
		if raiz.derecho != None:
			alt +=1	# Llega al siguiente nivel
			self.niveles(raiz.derecho, niveles, alt)
			alt -=1	# Regresa al nivel actual