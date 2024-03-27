package org.gestion;

import FuturasLibrerias.Calendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Escritura {
    private Calendar fecha;
    private LinkedList<Employee> empleados;

    public Escritura(Calendar fecha,LinkedList<Employee> empleados) {
        this.fecha = fecha;
        this.empleados = empleados;
    }
    public void escribir(String ruta){ // escribira en el fichero csv los datos
        Collections.sort(empleados,new OrdenacionPorCodigo());
        // a la hora de la escritura final los empleados estaran ordenados por codigo

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
            abrirArchivoCSV(ruta);

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
    private void abrirArchivoCSV(String ruta) { // al final del programa ejecutara el nuevo fichero csv creado
        try {
            File archivoCSV = new File(ruta);
            Runtime.getRuntime().exec("cmd /c start "+archivoCSV);
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
