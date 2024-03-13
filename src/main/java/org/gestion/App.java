package org.gestion;

import FuturasLibrerias.Calendar;

import java.util.LinkedList;

public class App {
    public static void main(String[] args) {
        LinkedList<Employee> employees = new LinkedList<>();

        employees.add(new Employee("M-N-1", new Turns[]{Turns.MO, Turns.NI}, 0));

        employees.add(new Employee("M-N-2", new Turns[]{Turns.MO, Turns.NI}, 0));

        employees.add(new Employee("M-N-3", new Turns[]{Turns.MO, Turns.NI}, 0));

        employees.add(new Employee("M-N-4", new Turns[]{Turns.MO, Turns.NI}, 0));

        employees.add(new Employee("M-N-5", new Turns[]{Turns.MO, Turns.NI}, 0));

        employees.add(new Employee("T-N-1", new Turns[]{Turns.AF, Turns.NI}, 0));

        employees.add(new Employee("T-N-2", new Turns[]{Turns.AF, Turns.NI}, 0));

        employees.add(new Employee("T-N-3", new Turns[]{Turns.AF, Turns.NI}, 0));

        employees.add(new Employee("T-N-4", new Turns[]{Turns.AF, Turns.NI}, 0));

        employees.add(new Employee("T-N-5", new Turns[]{Turns.AF, Turns.NI}, 0));

        employees.add(new Employee("T-N-6", new Turns[]{Turns.AF}, 0));


        LinkedList<Employee> dayEmployee = new LinkedList<>();

        Calendar fecha = new Calendar(1,1,2024);

        Day dia = new Day(2,2,2);

        Generador generador = new Generador(dia,70,11,fecha);

        System.out.println("LU|MA|MI|JU|VI|SB|DM");
        generador.rellenar(new Turns[]{Turns.MO,Turns.NI},6);
        generador.rellenar(new Turns[]{Turns.AF},1);
        generador.rellenar(new Turns[]{Turns.AF,Turns.NI},4);

        generador.mostrarHorario();

        //Day day = new Day(employees,6);

    }

}
