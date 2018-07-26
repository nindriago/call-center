package com.nerioindriago.callcenter.dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmpleadoTest {

	@Test
    public void testEmpleadoCreaciom() {
    	Empleado empleado = new Empleado(null, EmpleadoTipo.OPERADOR, EmpleadoEstado.DISPONIBLE);

        assertNotNull(empleado);
        assertEquals(EmpleadoTipo.OPERADOR, empleado.getTipo());
        assertEquals(EmpleadoEstado.DISPONIBLE, empleado.getDisponiblidad());
    }
}
