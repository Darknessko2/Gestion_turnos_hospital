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

        Calendar fecha = new Calendar(1,1,2021);

        DatosDia dia = new DatosDia(2,2,2,11);

        Generador generador = new Generador(dia,23,fecha);

        generador.rellenar(new Turns[]{Turns.MO,Turns.NI},6);
        generador.rellenar(new Turns[]{Turns.AF},1);
        generador.rellenar(new Turns[]{Turns.AF,Turns.NI},4);

        imprimirSemana(fecha);
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
