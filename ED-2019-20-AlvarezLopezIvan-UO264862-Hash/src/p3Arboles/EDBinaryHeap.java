package p3Arboles;

/**
 * Clase EDBinaryHeap que simula un monticulo binario
 * 
 * @author Ivan Alvarez Lopez UO264862
 * @version 20.11.2019
 * @param <T> Tipo generico
 */
public class EDBinaryHeap<T extends Comparable<T>> implements EDPriorityQueue<T> {

	protected T[] elementos;
	protected int numElementos;

	/**
	 * Constructor principal de un monticulo binario
	 * 
	 * @param numMaxElementos Numero maximo de elementos
	 */
	@SuppressWarnings("unchecked")
	public EDBinaryHeap(int numMaxElementos) {
		elementos = (T[]) new Comparable[numMaxElementos];
	}

	@Override
	public int add(T info) {
		if (info == null)
			return -2;
		if (this.numElementos == this.elementos.length)
			return -1;
		this.elementos[this.numElementos] = info;
		filtradoAscendente(this.numElementos);
		this.numElementos++;
		return 0;
	}

	@Override
	public T getTop() {
		if (this.numElementos == 0)
			return null;
		this.numElementos--;
		T top = this.elementos[0];
		swap(0, this.numElementos);
		filtradoDescendente(0);
		return top;
	}

	@Override
	public int remove(T info) {
		if (info == null)
			return -2;
		boolean foundElement = false;
		for (int i = 0; i < this.numElementos; i++) {
			if (this.elementos[i].equals(info)) {
				foundElement = true;
				swap(i, --this.numElementos);
				filtradoDescendente(i);
				filtradoAscendente(i);
				break;
			}
		}
		return foundElement ? 0 : -1;
	}

	@Override
	public boolean isEmpty() {
		return this.numElementos == 0;
	}

	@Override
	public void clear() {
		this.numElementos = 0;
	}

	@Override
	public String toString() {
		// Devuelve una cadena con la informacion de los elementos que contiene el
		// monticulo en forma visible (recomendado inorden-derecha-izquierda tabulado)
		StringBuilder cadena = new StringBuilder();
		cadena.append(inOrdenDerechaTabulado(0, 0));
		return cadena.toString();
	}

	/**
	 * El arbol que empieza en indice p tumbado con esp tabulaciones...
	 * 
	 * @param p   Origen
	 * @param esp Espaciado
	 * @return Arbol tumbado
	 */
	private String inOrdenDerechaTabulado(int p, int esp) {
		String cadena = "";
		if (p < numElementos) {
			int izq = Math.abs(2 * p + 1);
			int der = Math.abs(2 * p + 2);
			cadena += inOrdenDerechaTabulado(der, esp + 1);
			for (int i = 0; i < esp; i++)
				cadena += "\t";
			cadena += elementos[p] + "\n";
			cadena += inOrdenDerechaTabulado(izq, esp + 1);
		}
		return cadena;
	}

	/**
	 * Realiza una filtrado ascendente de minimos en el monticulo
	 * 
	 * @param index Se le pasa el indice del elemento a filtrar
	 */
	protected void filtradoAscendente(int index) {
		int sonPos = index;
		int fatherPos = (sonPos - 1) / 2;
		while (fatherPos >= 0 && fatherPos != sonPos) {
			if (this.elementos[fatherPos].compareTo(this.elementos[sonPos]) <= 0)
				break;

			swap(sonPos, fatherPos);

			sonPos = fatherPos;
			fatherPos = (sonPos - 1) / 2;
		}
	}

	/**
	 * Intercambia dos elementos del vector dadas sus posiciones
	 * 
	 * @param i Posicion del primer elemento
	 * @param j Posicion del segundo elemento
	 */
	private void swap(int i, int j) {
		T aux = this.elementos[i];
		this.elementos[i] = this.elementos[j];
		this.elementos[j] = aux;
	}

	/**
	 * Realiza una filtrado descendente de minimos en el monticulo
	 * 
	 * @param index Se le pasa el indice del elemento a filtrar
	 */
	protected void filtradoDescendente(int index) {
		int fatherPos = index;
		int leftSonPos = 2 * fatherPos + 1;
		int rightSonPos = 2 * fatherPos + 2;
		while (leftSonPos < this.numElementos) {
			if (this.elementos[fatherPos].compareTo(this.elementos[leftSonPos]) <= 0) {
				if (rightSonPos >= this.numElementos
						|| this.elementos[fatherPos].compareTo(this.elementos[rightSonPos]) <= 0)
					break;
				swap(fatherPos, rightSonPos);
				fatherPos = rightSonPos;
			} else {
				int sonPos = (rightSonPos < this.numElementos
						&& this.elementos[rightSonPos].compareTo(this.elementos[leftSonPos]) <= 0) ? rightSonPos
								: leftSonPos;
				swap(fatherPos, sonPos);
				fatherPos = sonPos;
			}
			leftSonPos = 2 * fatherPos + 1;
			rightSonPos = 2 * fatherPos + 2;
		}

	}
}
