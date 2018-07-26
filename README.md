# Call Center

Existe un call center donde hay 3 tipos de empleados: operador, supervisor y director. El proceso de la atención de una llamada telefónica en primera

instancia debe ser atendida por un operador, si no hay ninguno libre debe ser atendida por un supervisor, y de no haber tampoco supervisores libres

debe ser atendida por un director.

## Solucion

La solucion empleada para este problema se empleo utilizando Hilos, se poseen varios OPERADORES DISPONIBLES, y en caso de tener a los OPERADORES OCUPADOS,

los SUPERVISORES entraran a estar disponibles para ATENDER las llamadas, cuando los SUPERVISORES esten ocupados y entren mas llamadas el director entrara a 

tomar las llamadas ENTRANTES, pero, cuando no existan mas EMPLEADOS disponibles, las llamadas se pondran en espera hasta que se desocupe alguno de los OPERADORES.

## Requisitos

Java JDK 8 y Maven son requisitos para el proyecto

## Compilacion

As in any Maven project, it may be required to run

```bash
mvn clean install
```

## Autor

* **NERIO INDRIAGO**
