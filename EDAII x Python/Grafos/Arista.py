from Vertice import Vertice

class Arista:
	costo = 0
	v = Vertice(" ")
	u = Vertice(" ")

	def __init__(self,v,u,costo):
		'Constructor de la clase'
		self.costo = costo
		self.v = v
		self.u = u

	def imprimirArista(self):
		'Imprime las características de la arista'
		print("Arista:", self.v.nombre," <-- ",self.costo," --> ",self.u.nombre)