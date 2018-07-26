package com.nerioindriago.callcenter.dao;

/**
 * @author nerio
 *
 */
public class Empleado {
	private String nombre;
	private EmpleadoTipo tipo;
	private EmpleadoEstado disponiblidad;
	
	/**
	 * @param nombre NOMBRE del EMPLEADO
	 * @param tipo	TIPO de EMPLEADO
	 * @param disponible ESTADO DEL EMPLEADO
	 */
	public Empleado (String nombre, EmpleadoTipo tipo, EmpleadoEstado disponible) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.disponiblidad = disponible;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the tipo
	 */
	public EmpleadoTipo getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(EmpleadoTipo tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the disponiblidad
	 */
	public EmpleadoEstado getDisponiblidad() {
		return disponiblidad;
	}

	/**
	 * @param disponiblidad the disponiblidad to set
	 */
	public void setDisponiblidad(EmpleadoEstado disponiblidad) {
		this.disponiblidad = disponiblidad;
	}


}
