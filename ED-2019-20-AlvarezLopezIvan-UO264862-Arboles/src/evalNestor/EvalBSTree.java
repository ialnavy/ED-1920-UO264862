//  NO MODIFICAR NOMBRE DE ESTE PAQUETE, crear un paquete nuevo (si no existe ya) en vuestro proyecto que se llame asi: "evalNestor"
//  y meted en el esta clase
package evalNestor;

import p3Arboles.BSTNode;
import p3Arboles.BSTree;

public class EvalBSTree<T extends Comparable<T>> extends BSTree<T> {

	@SuppressWarnings("hiding")
	private class EvalBSTNode<T extends Comparable<T>> extends BSTNode<T> {
		public EvalBSTNode(T node) {
			super(node);
		}

		void setLeft(EvalBSTNode<T> x) {
			super.setLeft(x);
		}

		void setRight(EvalBSTNode<T> x) {
			super.setRight(x);
		}
	}

	public EvalBSTree() {
		super();
	}

	public EvalBSTree(T[] serie) {
		super();
		// Si no existe el metodo setRoot copiarlo del comentario de debajo
		setRoot(reconstruirArbolBST(0, serie));
	}
	/*
	 * // setter de la raiz del arbol BST que se debe copiar si no existe ya //
	 * Establece el parametro como nodo raiz del arbol protected void
	 * setRoot(BSTNode<T> nodo) { raiz = nodo; }
	 */

	private EvalBSTNode<T> reconstruirArbolBST(int i, T[] serie) {
		if (i < serie.length && serie[i] != null) {
			EvalBSTNode<T> r = new EvalBSTNode<T>(serie[i]);
			r.setLeft(reconstruirArbolBST(i * 2 + 1, serie));
			r.setRight(reconstruirArbolBST(i * 2 + 2, serie));
			return r;
		}
		return null;
	}

}
