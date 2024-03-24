package org.gestion;

import FuturasLibrerias.Calendar;

import java.util.Collections;
import java.util.LinkedList;

public class App {

    public static  LinkedList<Employee> employees = new LinkedList<>();
    public static void main(String[] args) {


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

        employees.add(new Employee("T-N-6", new Turns[]{Turns.AFTERNOON, Turns.NIGHT}, 0));

        Calendar fecha = new Calendar(1, 1, 2020);

        DatosDia dia = new DatosDia(2, 2, 2);

        Gestion gestion = new Gestion(dia, employees, fecha);

        imprimirSemana(fecha);
        gestion.agregarHoras();

        Collections.sort(employees);

        mostrarEmpleados();

    }
    public static void mostrarEmpleados(){
        for(Employee employee : employees)
            System.out.println(employee);
    }
    public static void imprimirSemana(Calendar fecha){
        Calendar copia = fecha.clone();
        for (int i = 0; i < 30; i++) {
            System.out.print(copia.getDiaSemana().substring(0,2)+"|");
            copia.incrementarDia();
        }
        System.out.println();
    }
}
