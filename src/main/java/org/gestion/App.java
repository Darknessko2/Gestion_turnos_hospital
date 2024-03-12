package org.gestion;

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

        employees.add(new Employee("T-N-6", new Turns[]{Turns.AF, Turns.NI}, 0));


        LinkedList<Employee> dayEmployee = new LinkedList<>();

        Day dia = new Day(2,2,2);
        Week semana = new Week(dia,70,11);

        semana.rellenar(new Turns[]{Turns.MO,Turns.NI},6);
        semana.rellenar(new Turns[]{Turns.AF},1);
        semana.rellenar(new Turns[]{Turns.AF,Turns.NI},4);

        semana.mostrarHorario();
        semana.getFecha(2024);

        //Day day = new Day(employees,6);

    }

}
