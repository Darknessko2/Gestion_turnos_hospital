package org.gestion;

import FuturasLibrerias.Calendar;

import java.util.LinkedList;
import java.util.List;

public class Gestion {
    private Generador generador;
    private DatosDia datos;
    private LinkedList<Employee> empleados;
    private Calendar fecha;

    public Gestion(DatosDia datos, LinkedList<Employee> empleados, Calendar fecha) {
        this.generador = new Generador(datos,30,fecha,empleados);
        this.datos = datos;
        this.empleados = empleados;
        this.fecha = fecha;
    }

    public void agregarHoras(){
        generador.rellenar();

        generador.incrementarRango();
        generador.rellenar();

        generador.incrementarRango();
        generador.rellenar();

        generador.incrementarRango();
        generador.rellenar();

        generador.incrementarRango();
        generador.rellenar();

        generador.incrementarRango();
        generador.rellenar();

        generador.incrementarRango();
        generador.rellenar();
        generador.mostrarHorario();
    }
}
