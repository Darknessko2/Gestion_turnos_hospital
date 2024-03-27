package org.gestion;

import FuturasLibrerias.Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class App {

    public static  LinkedList<Employee> employees = new LinkedList<>();
    public static void main(String[] args) {

//        employees.add(new Employee("M-N-1", new Turns[]{Turns.MANYANAS, Turns.NOCHE}, 0));
//
//        employees.add(new Employee("M-N-2", new Turns[]{Turns.MANYANAS, Turns.NOCHE}, 0));
//
//        employees.add(new Employee("M-N-3", new Turns[]{Turns.MANYANAS, Turns.NOCHE}, 0));
//
//        employees.add(new Employee("M-N-4", new Turns[]{Turns.MANYANAS, Turns.NOCHE}, 0));
//
//        employees.add(new Employee("M-N-5", new Turns[]{Turns.MANYANAS, Turns.NOCHE}, 0));
//
//        employees.add(new Employee("T-N-1", new Turns[]{Turns.TARDES, Turns.NOCHE}, 0));
//
//        employees.add(new Employee("T-N-2", new Turns[]{Turns.TARDES, Turns.NOCHE}, 0));
//
//        employees.add(new Employee("T-N-3", new Turns[]{Turns.TARDES, Turns.NOCHE}, 0));
//
//        employees.add(new Employee("T-N-4", new Turns[]{Turns.TARDES, Turns.NOCHE}, 0));
//
//        employees.add(new Employee("T-N-5", new Turns[]{Turns.TARDES, Turns.NOCHE}, 0));
//
//        employees.add(new Employee("T-N-6", new Turns[]{Turns.TARDES, Turns.NOCHE}, 0));
//
//        Calendar fecha = new Calendar(1, 1, 2010);
//
//        DatosDia dia = new DatosDia(2, 2, 2);
//
//        Generador generador = new Generador(dia,140,fecha,employees);
//
//        Gestion gestion = new Gestion(dia, fecha,generador);
//
//        gestion.creacionPlanilla(employees);
//
//        Escritura write = new Escritura(fecha,employees);
//
//        write.escribir("prueba.csv");



//        JLabel mensajeLabel = new JLabel("GENERANDO LABERINTO...");
//        mensajeLabel.setForeground(Color.white);

//        Tablero tablero = new Tablero(boardWidth,boardHeight,mensajeLabel,headerPanel);


//        frame.add(tablero, BorderLayout.CENTER);


//        mensajeLabel.setFont(FUENTE);
//        headerPanel.add(mensajeLabel);

//        frame.add(tablero);

//        tablero.requestFocusInWindow();

        // registro de los empleados todo

        // peticion de los datos todo
        // falta ordenar los empleados antes de escribir el fichero todo
    }
    public static void mostrarEmpleados(){
        for(Employee employee : employees)
            System.out.println(employee);
    }
    public static void imprimirSemana(Calendar fecha){
        Calendar copia = fecha.clone();
        for (int i = 0; i < 28; i++) {
            System.out.print(copia.getDiaSemana().substring(0,2)+"|");
            copia.incrementarDia();
        }
        System.out.println();
    }
}
