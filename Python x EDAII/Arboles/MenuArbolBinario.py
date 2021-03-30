from ArbolBinario import ArbolBinario
from Nodo import Nodo
import os
import platform

class MenuArbolBinario:

	def main(self):
		'Constructor de la clase'
		arbol = ArbolBinario(None);
		op = 0
		valor = 0

		while op != 8:
			print("\n------ Menú Árbol Binario de Búsqueda Balanceado ------")
			print("1) Añadir nodo")
			print("2) Eliminar nodo")
			print("3) Buscar nodo")
			print("4) Altura árbol")
			print("5) Imprimir árbol")
			print("6) Limpiar árbol")
			print("7) Limpiar pantalla")
			print("8) Salir")
			op = int(input("Seleccione una opción: "))

			if op == 1:
				print("\n------ Añadir Nodo ------")
				valor = int(input("Ingrese el valor para el nuevo nodo: "))
				arbol.añadir(Nodo(valor,None,None,None))
			elif op == 2:
				print("\n------ Eliminar Nodo ------")
				valor = int(input("Ingrese el valor del nodo a eliminar: "))
				arbol.eliminar(valor)
			elif op == 3:
				print("\n------ Buscar Nodo ------")
				valor = int(input("Ingrese el valor del nodo a buscar: "))
				nodo = arbol.obtenerNodo(arbol.raiz, valor)
				if nodo == None:
					print("Nodo no encontrado")
				else:
					print("Nodo encontrado")
			elif op == 4:
				print("\n------ Altura Árbol ------")
				if arbol.raiz == None:
					print("Altura: -1")
				else:
					altura = arbol.altura(arbol.raiz)
					print("Altura: ",altura)
			elif op == 5:
				print("\n------ Imprimir Árbol ------")
				if arbol.raiz != None:
					print("Recorrido Prefijo: ")
					arbol.prefijo(arbol.raiz)
					print("Recorrido Infijo: ")
					arbol.infijo(arbol.raiz)
					print("Recorrido Postfijo: ")
					arbol.postfijo(arbol.raiz)
					print("Recorrido BFS: ")
					arbol.BFS()
				else:
					print("Árbol vacío")
			elif op == 6:
				print("\n------ Limpiar Árbol ------")
				arbol = ArbolBinario(None)
				print("Árbol limpiado.")
			elif op == 7:
				if platform.system() == 'Windows':
					uv = os.system("cls")
				else:
					uv = os.system("clear")	# MacOS / Linux
			elif op == 8:
				print("\n------ Salir ------\n")
			else:
				print("Opción no válida")

# Ejecutando el objeto
programa = MenuArbolBinario()
programa.main()