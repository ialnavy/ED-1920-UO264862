//  NO MODIFICAR NOMBRE DE ESTE PAQUETE, crear (si no existe ya) un paquete nuevo en vuestro proyecto que se llame as�: "evalNestor"
//  y meted en esta clase
package evalNestor;

/**
 * Clase Alumno.
 * 
 * @author Nestor
 */
public class Alumno {
	// Poned vuestros apellidos, nombre y UO; sustituyendolos en las asignaciones de
	// debajo...
	String nombre = "Ivan", // Primera en may�sculas y si es compuesto, sin espacios
			apellido1 = "Alvarez", // Primera en may�sculas y si es compuesto, sin espacios
			apellido2 = "Lopez", // Primera en may�sculas y si es compuesto, sin espacios
			uo = "UO264862"; // El UO en may�sculas

	/**
	 * Codifica el nombre y el UO del alumno
	 * 
	 * @return Nombre y UO del alumno codificados
	 */
	public String getNombreFicheroAlumno() {
		return apellido1 + apellido2 + nombre + "-" + uo;
	}

	/**
	 * Devuelve el email de uniovi del alumno
	 * 
	 * @return Email del alumno
	 */
	public String email() {
		return uo + "@uniovi.es";
	}

}
