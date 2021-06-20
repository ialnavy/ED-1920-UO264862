//  NO MODIFICAR NOMBRE DE ESTE PAQUETE, crear un paquete nuevo (si no existe ya) en vuestro proyecto que se llame asi: "evalNestor"
//  y meted en el esta clase
package evalNestor;


import p3Arboles.AVLNode;
import p3Arboles.AVLTree;

public class EvalAVLTree <T extends Comparable<T>> extends AVLTree <T> {

	@SuppressWarnings("hiding")
	private class EvalAVLNode <T extends Comparable<T>> extends AVLNode <T> {
		public EvalAVLNode (T node) {
			super(node);
		}

		// Por si no funciona el vuestro.
		// necesita visibilidad protected del atributo height
		public int getHeight(){
			return height;
		}
		
		// necesita visibilidad protected del atributo height
		public void setHeight(int h) {
			height=h;
		}

		// Comentar metodo si no existe atributo balanceFactor, o ponerlo protected si existe
		public void setBF(int bf) {
			balanceFactor=bf;
		}
		
	}

	public EvalAVLTree() {
		super();
	}
	
	public EvalAVLTree(T[] serie)  {
		super();
		try {
			setRoot(reconstruirArbolAVL(0, serie));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	// Necesita que se cambie la visibilidad del atributo height a protected (y tambien el de balanceFactor si existe)
	private AVLNode<T> reconstruirArbolAVL(int i, T[] serie) throws Exception {
		if (i<serie.length && serie[i]!=null) {
			EvalAVLNode<T> r = new EvalAVLNode<T>(serie[i]);
			// Necesita que se cambie la visibilidad del BSTNode.setLeft a public
			r.setLeft(reconstruirArbolAVL(i*2+1, serie));
			// Necesita que se cambie la visibilidad del BSTNode.setRight a public
			r.setRight(reconstruirArbolAVL(i*2+2, serie));
			// Cojo la altura del nodo recien creado como se le haya puesto si no tiene hijos por la rama ademas no depende de vuestro getHeight()
			int altI=r.getLeft()!=null?r.getLeft().getHeight():r.getHeight()-1;
			int altR=r.getRight()!=null?r.getRight().getHeight():r.getHeight()-1;
			// Estabezco la altura correcta
			r.setHeight((altI>altR?altI:altR)+1);
			r.setBF(altR-altI);// Comentar esto si no es un atributo el factor de balance y cambiarlo a protected si lo es
			if (Math.abs(altR-altI)>1) throw new Exception("El arbol que se intentaba crear NO es un AVL: "+serializar(serie));
			return r;
		}
		return null;
	}
	
	private String serializar(T[] serie){
		StringBuilder cad=new StringBuilder();
		for (T i:serie) {
			cad.append((i==null?"null":i.toString())+"\t");
		}
		return cad.toString();
	}
	
	public String toStringParaPruebas() {
		String res=toStringParaPruebas((AVLNode<T>) getRoot());
		return res;
	}

	/**
	 * @param n
	 *            La raiz del arbol o subarbol
	 * 
	 * @return Un String con el texto mostrando un recorrido preOrden
	 */
	protected String toStringParaPruebas(AVLNode<T> n) {
		if (n == null) 
			return "_\t";
		return n + "\t"+//tambien valdria n.toString()
		toStringParaPruebas(n.getLeft())+
		toStringParaPruebas(n.getRight());
	}

}
