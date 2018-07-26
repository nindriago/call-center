package com.nerioindriago.callcenter.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;

import com.nerioindriago.callcenter.dao.Empleado;
import com.nerioindriago.callcenter.dao.EmpleadoEstado;
import com.nerioindriago.callcenter.dao.EmpleadoTipo;

/**
 * @author nerio
 *
 */
public class Dispatcher implements Runnable {

	private int numeroLlamada;
	private int tiempoSegundos;
	private int numeroEmpleado;
	public static ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();

	public static final int MIN_TIEMPO_LLAMADA = 5;
	public static final int MAX_TIEMPO_LLAMADA = 10;

	public static final int MAX_THREAD = 20;
	public static final int MAX_OPERADORES = 10;
	public static final int MAX_SUPERVISORES = 2;
	public static final int MAX_DIRECTORES = 1;

	/**
	 * @param numeroEmpleado Empleado disponible para ATENDER las llamadas
	 * @param numeroLlamada Llamada entrante
	 * @param tiempo Duracion de la llamada 
	 */
	public Dispatcher(int numeroEmpleado, int numeroLlamada, int tiempo) {
		this.numeroEmpleado = numeroEmpleado;
		this.numeroLlamada = numeroLlamada;
		this.tiempoSegundos = tiempo;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(this.tiempoSegundos * 1000);

			System.out.println(Thread.currentThread().getName() +" - Llamada: " + numeroLlamada + " - Nombre: "
					+ listaEmpleados.get(numeroEmpleado).getNombre() + " - Tipo: "
					+ listaEmpleados.get(numeroEmpleado).getTipo() + " - Tiempo: " + tiempoSegundos + " Segundos");

			listaEmpleados.get(numeroEmpleado).setDisponiblidad(EmpleadoEstado.DISPONIBLE);
			
			System.out.println(Thread.currentThread().getName() +" - Nombre: " + listaEmpleados.get(numeroEmpleado).getNombre() + " Tipo: "
					+ listaEmpleados.get(numeroEmpleado).getTipo() + " - Estado: "
					+ listaEmpleados.get(numeroEmpleado).getDisponiblidad());
			System.out.println("---------------------------------------------------------------------------------");

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < MAX_OPERADORES; i++) {
			listaEmpleados.add(new Empleado(EmpleadoTipo.OPERADOR.toString() + "-" + (i + 1), EmpleadoTipo.OPERADOR,
					EmpleadoEstado.DISPONIBLE));
		}

		for (int i = 0; i < MAX_SUPERVISORES; i++) {
			listaEmpleados.add(new Empleado(EmpleadoTipo.SUPERVISOR.toString() + "-" + (i + 1), EmpleadoTipo.SUPERVISOR,
					EmpleadoEstado.DISPONIBLE));
		}

		for (int i = 0; i < MAX_DIRECTORES; i++) {
			listaEmpleados.add(new Empleado(EmpleadoTipo.DIRECTOR.toString() + "-" + (i + 1), EmpleadoTipo.DIRECTOR,
					EmpleadoEstado.DISPONIBLE));
		}

		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("CALL CENTER - TEST");
		System.out.println("BIENVENID@S");
		System.out.println("---------------------------------------------------------------------------------");
		for (int i = 0; i < listaEmpleados.size(); i++) {
			if (listaEmpleados.get(i).getTipo().equals(EmpleadoTipo.OPERADOR)
					&& listaEmpleados.get(i).getDisponiblidad().equals(EmpleadoEstado.DISPONIBLE)) {

				System.out.println("Nombre: " + listaEmpleados.get(i).getNombre() + " - Tipo: "
						+ listaEmpleados.get(i).getTipo() + " - Estado: " + listaEmpleados.get(i).getDisponiblidad());
			}
		}

		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Comenzaremos atender llamadas en breve...");
		System.out.println("---------------------------------------------------------------------------------");

		dispatchCall(MAX_THREAD, MIN_TIEMPO_LLAMADA, MAX_TIEMPO_LLAMADA);
	}

	/**
	 * @param maxHilos Cantidad de hilos y/o capacidad de llamadas entrantes
	 * @param minTiempoLlamada minimo tiempo estipulado para la duracion de las llamadas
	 * @param maxTiempoLlamada maximo tiempoo estipulado para la duracion de las llamadas
	 */
	public static void dispatchCall(int maxHilos, int minTiempoLlamada, int maxTiempoLlamada) {
		int numeroLlamada = 1;
		int numeroEmpleado = 0;
		// Instanciar clase Random
		Random random = new Random();
		// Obtener IntStream. El IntStream tendra 10 numeros aleatorios
		// entre 5 y 11, excluido el 11.
		IntStream intStream = random.ints(maxHilos, minTiempoLlamada, maxTiempoLlamada);
//		IntStream intStream = random.ints(MAX_THREAD, MIN_TIEMPO_LLAMADA, MAX_TIEMPO_LLAMADA);
		// Iterador para ir obteniendo los numeros
		Iterator<?> iterator = intStream.iterator();
		
		while (iterator.hasNext()) {
			for (int i = 0; i < listaEmpleados.size(); i++) {
				if (listaEmpleados.get(i).getTipo().equals(EmpleadoTipo.OPERADOR)
						&& listaEmpleados.get(i).getDisponiblidad().equals(EmpleadoEstado.DISPONIBLE)) {
					numeroEmpleado = i;
					listaEmpleados.get(i).setDisponiblidad(EmpleadoEstado.OCUPADO);
					break;
				} else if (listaEmpleados.get(i).getTipo().equals(EmpleadoTipo.SUPERVISOR)
						&& listaEmpleados.get(i).getDisponiblidad().equals(EmpleadoEstado.DISPONIBLE)) {
					numeroEmpleado = i;
					listaEmpleados.get(i).setDisponiblidad(EmpleadoEstado.OCUPADO);
					break;
				} else if (listaEmpleados.get(i).getTipo().equals(EmpleadoTipo.DIRECTOR)
						&& listaEmpleados.get(i).getDisponiblidad().equals(EmpleadoEstado.DISPONIBLE)) {
					numeroEmpleado = i;
					listaEmpleados.get(i).setDisponiblidad(EmpleadoEstado.OCUPADO);
					break;
				} else {
					numeroEmpleado = -1;
				}
			}
			if (numeroEmpleado != -1) {
				System.out.println("Llamada: " + numeroLlamada + " recibida por " + "- Nombre: "
						+ listaEmpleados.get(numeroEmpleado).getNombre() + " - Tipo: "
						+ listaEmpleados.get(numeroEmpleado).getTipo() + " - Estado: "
						+ listaEmpleados.get(numeroEmpleado).getDisponiblidad());
				
				int tiempo = (Integer) iterator.next();
				
				Thread r1 = new Thread(new Dispatcher(numeroEmpleado, numeroLlamada, tiempo));
				r1.start();

				numeroLlamada++;

			} else {
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("No hay operadores disponibles, por favor, espere un momento");
				System.out.println("---------------------------------------------------------------------------------");

				try {
					Thread.sleep(MIN_TIEMPO_LLAMADA * 1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
		System.out.println("---------------------------------------------------------------------------------");
	}
}
