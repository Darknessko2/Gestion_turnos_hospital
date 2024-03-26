package org.gestion;

import FuturasLibrerias.Calendar;
import FuturasLibrerias.Color;

import java.util.Collections;
import java.util.LinkedList;

public class Gestion {
    private Generador generador;
    private DatosDia datos;
    private Calendar fecha;

    public Gestion(DatosDia datos, Calendar fecha, Generador generador) {
        this.generador = generador;
        this.datos = datos;
        this.fecha = fecha;
    }
    public void creacionPlanilla(LinkedList<Employee> empleados) {
        int num = 6 * 12;
        for (int i = 0; i < num; i++) {


            if (i % 2 == 0){ // cada cierto tiempo se activara el siguiente evento
                // funcion que cambia el horario de uno tarde a uno mañana
                Collections.sort(empleados);
                empleados.get(0).intercambiarTurno();
                // tambien se tendra que cambiar el numero maximo de mañanas dicho dia
                datos.setMaxMornings(3);
                generador.reOrganizar();
                generador.rellenar();
                datos.setMaxMornings(2);
                // habra que tener cuidado con si un dia haz trabajado tarde al dia siguiente no puedes ir por la mañana
                empleados.get(0).intercambiarTurno();
            }else {
                Collections.sort(empleados);
                generador.reOrganizar();
                generador.rellenar();
            }
        }


        int index = 0;
        Collections.sort(empleados);
        generador.reOrganizar();
        for (int i = 0; i < 12; i++) {

            App.imprimirSemana(fecha);
            generador.mostrarHorario(index);
            numeros(index);
            index+= 28;
        }
        App.mostrarEmpleados();
    }
    public void numeros(int index){
        for (int i = index; i < (index+28); i++) {
            if (generador.verificar(i))
                System.out.print(String.format("%02d|", i));
            else
                System.out.print(Color.str(String.format("%02d|", i),Color.RED));
        }
        System.out.println();
    }
}
