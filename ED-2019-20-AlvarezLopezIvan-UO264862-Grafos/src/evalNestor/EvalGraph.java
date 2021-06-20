//  NO MODIFICAR NOMBRE DE ESTE PAQUETE, crear (si no existe ya) un paquete nuevo en vuestro proyecto que se llame asi: "evalNestor"
//  y meted en esta clase
package evalNestor;

import p2Grafos.Graph;

public class EvalGraph<T> extends Graph<T> {

	Alumno alum = new Alumno();

	public EvalGraph(int i) {
		super(i);
	}

	public EvalGraph(int i, T initialNodes[], boolean[][] initialEdges, double[][] initialWeights) {
		super(i, initialNodes, initialEdges, initialWeights);
	}

	// nuevo constructor en EvalGraph
	public EvalGraph(int i, T initialNodes[], boolean[][] initialEdges, double[][] initialWeights,
			double[][] initialAfloyd, int[][] initialPfloyd) {
		super(i, initialNodes, initialEdges, initialWeights, initialAfloyd, initialPfloyd);
	}

	public String getNombreFicheroAlumno() {
		return alum.getNombreFicheroAlumno();
	}
}
