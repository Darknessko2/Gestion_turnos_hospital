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

        int inicio = 0;
        int limite = 0;
        int numMeses = 12;

        try(BufferedWriter wr = new BufferedWriter(new FileWriter(ruta))) {
            for (int i = 0; i < numMeses; i++) {
                limite += Calendar.getLimit(fecha.getMonth(),fecha.getYear());
                wr.write("Mes,"+fecha.mesString()+"\n");
                wr.write("Nombre,"+date(inicio,limite)+"Horas\n");

                for(Employee empleado : empleados){
                    wr.write(empleado.getCode()+","+
                            turnosString(empleado.getHorariosEmpleado(),inicio,limite)+"\n");
                }
                inicio = limite;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String date(int inicio,int limite){ // todo cambiar los dias a espanyol
        StringBuilder datosFecha = new StringBuilder();

        for (int i = inicio; i < limite; i++) {
            datosFecha.append(fecha.diaFormatedo()+" "+
                    String.format("%02d",fecha.getDay())+"/"+
                    String.format("%02d",fecha.getMonth())+",");
            fecha.incrementarDia();
        }
        return datosFecha.toString();
    }
    private String turnosString(Turns[] turnos,int inicio ,int limite){

        StringBuilder datosMes = new StringBuilder();

        Turns[] turnosMes = Arrays.copyOfRange(turnos,inicio,limite);

        for(Turns turno : turnosMes){
            datosMes.append(Turns.formateado(turno)).append(",");
        }
        datosMes.append(Turns.horasTotales(turnosMes));

        return datosMes.toString();
    }
}
