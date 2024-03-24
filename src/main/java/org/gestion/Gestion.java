package org.gestion;

import FuturasLibrerias.Calendar;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Gestion {
    private Generador generador;
    private DatosDia datos;
    private Calendar fecha;

    public Gestion(DatosDia datos, Calendar fecha, Generador generador) {
        this.generador = generador;
        this.datos = datos;
        this.fecha = fecha;
    }
    public void agregarHoras(LinkedList<Employee> empleados) {
        LinkedList<Employee> ultimaLista= new LinkedList<>(empleados);
        for (int i = 0; i < 2; i++) {
            Collections.sort(empleados);
            generador.ordenarHorario(ultimaLista);
            generador.rellenar();
            ultimaLista = new LinkedList<>(empleados);
        }
        Collections.sort(empleados);
        generador.ordenarHorario(ultimaLista);
        generador.mostrarHorario();
        App.mostrarEmpleados();
    }

}
