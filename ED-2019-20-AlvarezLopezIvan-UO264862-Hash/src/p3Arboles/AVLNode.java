package p3Arboles;

/**
 * Clase derivada de BSTNode implementando funcionalidad de AVL
 * 
 * @author Ivan Alvarez Lopez UO264862
 * @version 30.10.2019
 * 
 */
public class AVLNode<T extends Comparable<T>> extends BSTNode<T> {

	/**
	 * Para almacenar la altura del nodo
	 */
	protected int height;

	/**
	 * Para almacenar al Factor de balance. Puede no existir y calcularse cada vez a
	 * partir de la altura de los hijos.
	 */
	protected int balanceFactor;

	/**
	 * Llama al padre e inserta la informacion propia, se le pasa la informacion que
	 * se mete en el nuevo nodo
	 * 
	 * @param info Informacion del nodo raiz
	 */
	public AVLNode(T info) {
		super(info);
	}

	/**
	 * Devuelve la altura del arbol del cual es raiz el nodo en cuestion. La altura
	 * de null es -1. La de un nodo sin hijos es 0. La de un nodo con hijos es uno
	 * mas que la altura del mas alto de sus hijos. Establecido por el criterio de
	 * teoria.
	 * 
	 * @return Altura del arbol
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Devuelve el factor de balance segun equilibrio del arbol del cual es raiz
	 * 
	 * @return Factor de balance
	 */
	public int getBF() {
		return this.balanceFactor;
	}

	/**
	 * Actualiza la altura del nodo en el arbol utilizando la altura de los hijos
	 */
	protected void updateHeight() {
		int leftHeight = -1;
		int rightHeight = -1;

		if (this.getLeft() != null)
			leftHeight = this.getLeft().getHeight();
		if (this.getRight() != null)
			rightHeight = this.getRight().getHeight();

		updateBalanceFactor(leftHeight, rightHeight);
		this.height = Math.max(leftHeight, rightHeight) + 1;
	}

	/**
	 * Actualiza el factor de balance del arbol AVL cuya raiz es este nodo. Se
	 * calculan previamente las alturas de sus hijos.
	 * 
	 * @param leftHeight  Altura de su hijo izquierdo
	 * @param rightHeight Altura de su hijo derecho
	 */
	private void updateBalanceFactor(int leftHeight, int rightHeight) {
		this.balanceFactor = rightHeight - leftHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTNode#getLeft()
	 */
	public AVLNode<T> getLeft() {
		return (AVLNode<T>) super.getLeft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTNode#getRight()
	 */
	public AVLNode<T> getRight() {
		return (AVLNode<T>) super.getRight();
	}

// No se necesitan los setters, valen los heredados 

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTNode#toString() Añade factor de balance
	 */
	public String toString() {
		return super.toString() + ":FB=" + getBF();
	}
}
