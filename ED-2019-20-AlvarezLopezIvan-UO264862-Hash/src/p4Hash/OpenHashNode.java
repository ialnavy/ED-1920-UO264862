package p4Hash;

import p3Arboles.AVLTree;

/**
 * Clase OpenHashNode que simula el nodo de una tabla Hash abierta
 * 
 * @author Ivan Alvarez Lopez UO264862
 * @version 11.12.2019
 * @param <T> Tipo generico
 */
public class OpenHashNode<T extends Comparable<T>> {

	protected AVLTree<T> cell;

	/**
	 * Constructor de la clase OpenHashNode
	 */
	public OpenHashNode() {
		this.cell = new AVLTree<T>();
	}

	/**
	 * Inserta una informacion dada en el nodo
	 * 
	 * @param info Informacion a anadir
	 * @return 0 si lo anade; -1 si ya existe un nodo con la informacion dada; -2
	 *         enotro caso
	 */
	public int add(T info) {
		return this.cell.addNode(info);
	}

	/**
	 * Elimina una informacion dada del nodo
	 * 
	 * @param info Informacion a eliminar
	 * @return 0 si lo elimina; -1 si no existe; -2 en otro caso
	 */
	public int remove(T info) {
		return this.cell.removeNode(info);
	}

	/**
	 * Busca una informacion en el nodo
	 * 
	 * @param info Informacion a buscar
	 * @return Informacion del nodo encontrado; null si no lo encuentra
	 */
	public T find(T info) {
		return this.cell.searchNode(info);
	}
}
