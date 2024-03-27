package org.gestion;

import FuturasLibrerias.Calendar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Escritura {
    private Calendar fecha;
    private LinkedList<Employee> empleados;

    public Escritura(Calendar fecha,LinkedList<Employee> empleados) {
        this.fecha = fecha;
        this.empleados = empleados;
    }
    public void escritura(String ruta){

        int limite = Calendar.getLimit(fecha.getMonth(),fecha.getYear());

        try(BufferedWriter wr = new BufferedWriter(new FileWriter(ruta))) {
            wr.write("Nombre,"+date(limite)+"\n");

            for(Employee empleado : empleados){
                wr.write(empleado.getCode()+","+turnosString(empleado.getHorariosEmpleado(),limite)+"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String date(int limite){ // todo cambiar los dias a espanyol
        StringBuilder datosFecha = new StringBuilder();

        for (int i = 0; i < limite; i++) {
            datosFecha.append(fecha.diaFormatedo()+" "+fecha.getDay()+"/"+fecha.getMonth()+",");
            fecha.incrementarDia();
        }

        return datosFecha.toString();
    }
    private String turnosString(Turns[] turnos, int limite){

        StringBuilder datosMes = new StringBuilder();

        Turns[] turnosMes = Arrays.copyOf(turnos,limite);

        for(Turns turno : turnosMes){
            datosMes.append(Turns.formateado(turno)).append(",");
        }
        return datosMes.toString();
    }
}
