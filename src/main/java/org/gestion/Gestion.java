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
        int num = 18;
        for (int i = 0; i < num; i++) {


            if (i % 2 == 0){ // cada cierto tiempo se activara el siguiente evento
                // funcion que cambia el horario de uno tarde a uno mañana
                Collections.sort(empleados);
                empleados.get(0).setTurns(new Turns[]{Turns.MORNING,Turns.NIGHT});
                // tambien se tendra que cambiar el numero maximo de mañanas dicho dia
                datos.setMaxMornings(3);
                generador.reOrganizar();
                generador.rellenar();
                datos.setMaxMornings(2);
                // habra que tener cuidado con si un dia haz trabajado tarde al dia siguiente no puedes ir por la mañana
                empleados.get(0).setTurns(new Turns[]{Turns.AFTERNOON,Turns.NIGHT});
            }else {
                Collections.sort(empleados);
                generador.reOrganizar();
                App.mostrarEmpleados();
                generador.rellenar();
            }
        }
            Collections.sort(empleados);
            generador.reOrganizar();
            App.imprimirSemana(fecha);
            generador.mostrarHorario(0);
            numeros(0);


        App.imprimirSemana(fecha);
        generador.mostrarHorario(28);
        numeros(28);
        App.imprimirSemana(fecha);
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
