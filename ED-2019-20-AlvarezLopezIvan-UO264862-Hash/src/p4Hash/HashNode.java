package p4Hash;

/**
 * @author Néstor
 * @version 2019-20
 *
 * @param <T> Tipo generico
 */
public class HashNode<T> {
	private T info;
	private byte status;

	public static final byte BORRADO = -1;
	public static final byte VACIO = 0;
	public static final byte LLENO = 1;

	/**
	 * Constructor de un nodo de tabla Hash
	 */
	public HashNode() {
		info = null;
		status = VACIO;
	}

	/**
	 * Devuelve la informacion del nodo
	 * 
	 * @return Informacion del nodo
	 */
	public T getInfo() {
		return info;
	}

	/**
	 * Establece la informacion del nodo como borrada
	 */
	public void remove() {
		status = BORRADO;
	}

	/**
	 * Establece una nueva informacion al nodo y lo establece como lleno
	 * 
	 * @param elem Nueva informacion del nodo
	 */
	public void setInfo(T elem) {
		info = elem;
		status = LLENO;
	}

	/**
	 * Devuelve la constante del estado del nodo en un momento dado
	 * 
	 * @return Estado del nodo
	 */
	public byte getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder("{");
		switch (getStatus()) {
		case LLENO:
			cadena.append(info);
			break;
		case VACIO:
			cadena.append("_E_");
			break;
		case BORRADO:
			cadena.append("_D_");
		}
		cadena.append("}");
		return cadena.toString();
	}

}
