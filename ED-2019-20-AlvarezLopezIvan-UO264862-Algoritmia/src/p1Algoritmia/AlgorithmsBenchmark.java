package p1Algoritmia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author nestor
 * @version 2019-20
 */
public class AlgorithmsBenchmark {

    public long testAlgorithm(String name, String methodName, int n) {
	long tInicial = 0, tFinal = 0;

	Class<?> cl;
	try {
	    cl = Class.forName(name);
	    Object o = cl.newInstance();
	    Method m = cl.getMethod(methodName, int.class);

	    tInicial = System.currentTimeMillis();
	    m.invoke(o, n);
	    tFinal = System.currentTimeMillis();

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return tFinal - tInicial;
    }

    /**
     * @param output       Nombre del fichero de salida
     * @param startN       Valor inicial del par�metro en las pruebas
     * @param endN         Valor final del par�metro en la pruebas
     * @param times        N�mero de veces que se ejecutar� la prueba del m�todo
     *                     para cada valor del par�metro
     * @param nombreClase  Clase que tiene el m�todo que queremos medir
     * @param nombreMetodo M�todo que queremos medir
     */
    public void testFinal(String output, int startN, int endN, int times, String nombreClase, String nombreMetodo) {
	FileWriter file = null;
	PrintWriter pw;
	try {
	    file = new FileWriter(output);
	    pw = new PrintWriter(file);
	    long tStart = System.currentTimeMillis();
	    for (int n = startN; n <= endN; n++) {
		long millis = 0;
		for (int j = 0; j < times; j++)
		    millis += testAlgorithm(nombreClase, nombreMetodo, n);
		long mean = millis / times;
		pw.println(n + ";" + mean);
		System.out.println(n + " ; " + mean);
		if (System.currentTimeMillis() - tStart > 600000)
		    break;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (file != null)
		try {
		    file.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
    }

}
