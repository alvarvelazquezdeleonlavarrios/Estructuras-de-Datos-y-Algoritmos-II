import java.util.Scanner;
import java.io.IOException;

public class MenuArbolBinario{

	public static void main(String[] args){
		ArbolBinario arbol = new ArbolBinario();
		Scanner sc = new Scanner(System.in);
		int op=0,valor;

		while (op != 8){
			System.out.println("\n------ Menú Árbol Binario de Búsqueda Balanceado ------");
			System.out.println("1) Añadir nodo");
			System.out.println("2) Eliminar nodo");
			System.out.println("3) Buscar nodo");
			System.out.println("4) Altura árbol");
			System.out.println("5) Imprimir árbol");
			System.out.println("6) Limpiar árbol");
			System.out.println("7) Limpiar pantalla");
			System.out.println("8) Salir");
			System.out.print("Seleccione una opción: ");
			op = sc.nextInt();

			switch(op){
				case 1:
					System.out.println("\n------ Añadir Nodo ------");
					System.out.print("Ingrese el valor para el nuevo nodo: ");
					valor = sc.nextInt();
					arbol.añadir(new Nodo(valor));
					break;
				case 2:
					System.out.println("\n------ Eliminar Nodo ------");
					System.out.print("Ingrese el valor del nodo a eliminar: ");
					valor = sc.nextInt();
					arbol.eliminar(valor);
					break;
				case 3:
					System.out.println("\n------ Buscar Nodo ------");
					System.out.print("Ingrese el valor del nodo a buscar: ");
					valor = sc.nextInt();
					Nodo nodo = arbol.obtenerNodo(arbol.raiz,valor);
					if (nodo == null){
						System.out.println("Nodo no encontrado.");
					} else {
						System.out.println("Nodo encontrado.");
					}
					break;
				case 4:
					System.out.println("\n------ Altura Árbol ------");
					if (arbol.raiz == null){
						System.out.println("Altura: -1");
					} else {
						int altura = arbol.altura(arbol.raiz);
						System.out.println("Altura: " + altura);
					}
					break;
				case 5:
					System.out.println("\n------ Imprimir Árbol ------");
					if (arbol.raiz != null){
						System.out.print("Recorrido Prefijo: ");
						arbol.prefijo(arbol.raiz);
						System.out.print("\nRecorrido Infijo: ");
						arbol.infijo(arbol.raiz);
						System.out.print("\nRecorrido Postfijo: ");
						arbol.postfijo(arbol.raiz);
						System.out.print("\nRecorrido BFS: ");
						arbol.BFS();
					} else {
						System.out.println("Árbol vacío.");
					}
					break;
				case 6:
					System.out.println("\n------ Limpiar Árbol ------");
					arbol = new ArbolBinario();
					System.out.println("Árbol limpiado.");
					break;
				case 7:
					// Comando que limpia la pantalla de la Terminal
					System.out.print("\033[H\033[2J");
    				System.out.flush();
					break;
				case 8:
					System.out.println("\n------ Salir ------\n");
					break;
				default:
					System.out.println("Opción no válida.");
					break;
			}
		}
	}
}