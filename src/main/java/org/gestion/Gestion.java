package org.gestion;

import FuturasLibrerias.Calendar;
import FuturasLibrerias.Color;

import java.util.Collections;
import java.util.LinkedList;

public class Gestion {
    private Generador generador;
    private DatosDia datos;
    private Calendar fecha;

    private static final int NUMERO_DIAS = 370; // 370 porque puede haber años que tengan mas de 365 dias

    public Gestion(DatosDia datos, Calendar fecha, Generador generador) {
        this.generador = generador;
        this.datos = datos;
        this.fecha = fecha;
    }
    public void creacionPlanilla(LinkedList<Employee> empleados) {

        int limite = NUMERO_DIAS / Generador.DIAS_GENERADOS;

        for (int i = 0; i < limite; i++) { // se crerar la planilla hasta cumplir los doce meses

            Collections.sort(empleados);

            if (i % 2 == 0){ // cada cierto tiempo se activara el evento de cambio de turno
                cambiarTurno(empleados);
            }else {
                generador.reOrganizar();
                generador.rellenar();
            }
        }
        mostrarResultados(empleados);

    }
    private void mostrarResultados(LinkedList<Employee> empleados){ // todo quitar del programa

        int index = 0;
        Collections.sort(empleados);
        generador.reOrganizar();
        for (int i = 0; i < 14; i++) {

            App.imprimirSemana(fecha);
            generador.mostrarHorario(index);
            numeros(index);
            index+= 28;
        }
        App.mostrarEmpleados();

    }
    private void cambiarTurno(LinkedList<Employee> employees){
        // funcion que cambia el horario de uno tarde a uno mañana
        employees.get(0).intercambiarTurno();
        // el primer elemento de tarde que es el que menos horas tiene
        datos.setMaxMornings(3);
        // tambien se tendra que cambiar el numero maximo de mañanas dicho dia
        generador.reOrganizar();
        generador.rellenar();

        datos.setMaxMornings(2);
        // habra que tener cuidado con si un dia haz trabajado tarde al dia siguiente no puedes ir por la mañana
        employees.get(0).intercambiarTurno();
        // el empleado volvera a tener su turno normal
    }
    public boolean numeros(int index){
        boolean correcto = true;
        for (int i = index; i < (index+28); i++) {
            if (index < 300) {
                if (generador.verificar(i))
                    continue;
                else {
                    System.out.print(Color.str(String.format("%02d|", i), Color.RED));
                    correcto = false;
                }
            }
        }
        System.out.println();
//        generador.mostrarHorario(index);
        return correcto;
//        System.out.print(String.format("%02d|", i));
    }
}
