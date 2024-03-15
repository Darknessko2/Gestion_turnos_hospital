package org.gestion;

import FuturasLibrerias.Calendar;

import java.util.LinkedList;

public class App {
    public static void main(String[] args) {
        LinkedList<Employee> employees = new LinkedList<>();

        employees.add(new Employee("M-N-1", new Turns[]{Turns.MORNING, Turns.NIGHT}, 0));

        employees.add(new Employee("M-N-2", new Turns[]{Turns.MORNING, Turns.NIGHT}, 0));

        employees.add(new Employee("M-N-3", new Turns[]{Turns.MORNING, Turns.NIGHT}, 0));

        employees.add(new Employee("M-N-4", new Turns[]{Turns.MORNING, Turns.NIGHT}, 0));

        employees.add(new Employee("M-N-5", new Turns[]{Turns.MORNING, Turns.NIGHT}, 0));

        employees.add(new Employee("T-N-1", new Turns[]{Turns.AFTERNOON, Turns.NIGHT}, 0));

        employees.add(new Employee("T-N-2", new Turns[]{Turns.AFTERNOON, Turns.NIGHT}, 0));

        employees.add(new Employee("T-N-3", new Turns[]{Turns.AFTERNOON, Turns.NIGHT}, 0));

        employees.add(new Employee("T-N-4", new Turns[]{Turns.AFTERNOON, Turns.NIGHT}, 0));

        employees.add(new Employee("T-N-5", new Turns[]{Turns.AFTERNOON, Turns.NIGHT}, 0));

        employees.add(new Employee("T-N-6", new Turns[]{Turns.AFTERNOON}, 0));


        LinkedList<Employee> dayEmployee = new LinkedList<>();

        Calendar fecha = new Calendar(1,1,2020);

        DatosDia dia = new DatosDia(2,2,2);

        Generador generador = new Generador(dia,25,fecha,employees);

        imprimirSemana(fecha);
        generador.rellenar();
        generador.mostrarHorario();

        //Day day = new Day(employees,6);
    }
    public static void imprimirSemana(Calendar fecha){
        Calendar copia = fecha.clone();
        for (int i = 0; i < 7; i++) {
            System.out.print(copia.getDiaSemana().substring(0,2)+"|");
            copia.incrementarDia();
        }
        System.out.println();
    }
}
