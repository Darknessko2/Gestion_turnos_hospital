package org.gestion;

import FuturasLibrerias.Calendar;
import FuturasLibrerias.Color;

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
        for (int i = 0; i < 18; i++) {
            Collections.sort(empleados);
            if (i %2 == 0)
                Collections.reverse(empleados);

            generador.reOrganizar();
            generador.rellenar();
        }

        App.imprimirSemana(fecha);
        generador.mostrarHorario(0);
        numeros(0);

        generador.mostrarHorario(28);
        numeros(28);

        generador.mostrarHorario(56);
        numeros(56);

        App.mostrarEmpleados();
    }
    public void numeros(int index){
        for (int i = index; i < (index+30); i++) {
            if (generador.verificar(i))
                System.out.print(String.format("%02d|", i));
            else
                System.out.print(Color.str(String.format("%02d|", i),Color.RED));
        }
        System.out.println();
    }
}
