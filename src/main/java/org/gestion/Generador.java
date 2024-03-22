package org.gestion;
import FuturasLibrerias.*;

import java.util.Arrays;
import java.util.LinkedList;

public class Generador {
    private Turns[][] horario;
    private int media;
    private DatosDia datosDia;
    private Calendar fecha;
    private LinkedList<Employee> employees;

    private static final int DIAS_SEMANA = 5;
    private static int dia;
    private static int registro;
    private static int valorNumerico = 1;

    public Generador(DatosDia datosDia, int media, Calendar fecha,LinkedList<Employee> employees) {
        this.datosDia = datosDia;
        this.media = media;
        this.horario = new Turns[employees.size()][DIAS_SEMANA];
        this.fecha = fecha;
        this.employees = employees;
    }
    public Turns[][] getHorario() {
        return horario;
    }

    public void rellenar(){

        int mediaOriginal = media;


        Calendar original = fecha.clone();
        // guardo los datos de la fecha original

        for (registro = 0; registro < employees.size(); registro++) {

            // fecha con los valores originales de la copia
            fecha = original.clone();
            if (registro % 2 == 0 && valorNumerico % 2 == 0 ) {
                media = media / 2;
            } else if (registro % 2 != 0 && valorNumerico %2 != 0) {
                media = media / 2;
            }

            for (dia = 0; dia < (DIAS_SEMANA * valorNumerico) ; dia++) {

                if (horario[registro][dia] == null){
                    horario[registro][dia] = turnoDisponible(employees.get(registro).getTurns());
                    employees.get(registro).agregarHoras(horario[registro][dia]);
                    fecha.incrementarDia();
                }
            }
            media = mediaOriginal;
        }
        fecha = original.clone();
        valorNumerico++;
        // al final del bucle la fecha no cambiara sus valores originales
    }
    public void invertir(){
            int count1 = 0;
            int count2 = horario.length-1;
            do {
                Turns[] temp = horario[count1];
                horario[count1++] = horario[count2];
                horario[count2--] = temp;
            }while (count1 < count2);
    }
    public void incrementarRango(){
        for (int re = 0; re < employees.size(); re++) {
            horario[re] =  Arrays.copyOf(horario[re],horario[re].length + DIAS_SEMANA);
        }
    }
    public void mostrarHorario(){
        for (int i = 0; i < horario.length; i++) {
            for (int j = 0; j < horario[i].length; j++) {
                show(horario[i][j]);
            }
            System.out.print("HORAS -> "+Turns.horasTotales(horario[i]));
            System.out.println();
        }
    }
    public void show(Turns turno){
        Color color;

        if (turno == Turns.AFTERNOON)
            color = Color.YELLOW;
        else if (turno == Turns.LIBRE)
            color = Color.GREEN;
        else if (turno == Turns.NIGHT)
            color = Color.BLUE;
        else if(turno == Turns.MORNING)
            color = Color.RED;
        else
            color = Color.CYAN;

        if (turno != null)
            System.out.print(Color.str(turno.toString().substring(0,2)+" ",color));
    }
    public Turns turnoDisponible(Turns[] turnos){ // devolvera el turno correspondiente

            for (int i = 0; i < turnos.length ; i++) {
                Turns turno = turnos[i];
                if (turnoCorrecto(turno))
                    return turno;
            }
            return (getDiaAnterior() == Turns.NIGHT) ?Turns.SALIENTE :Turns.LIBRE;
        // si no le corresponde ningun turno el dia sera libre o saliente
    }
    public boolean turnoCorrecto(Turns turno){

        if (turno == Turns.MORNING)
            return checkMorning();
        else if(turno == Turns.AFTERNOON)
            return checkTarde();
        else
            return checkNoche();
    }
    public Turns getDiaAnterior(){
        Turns diaAnterior = null;
        if (dia != 0)
            diaAnterior = horario[registro][dia - 1];

        return diaAnterior;
    }

    private boolean checkMorning(){


        if (contarTurnosDia(Turns.MORNING) >= datosDia.getMaxMornings()) // maximo numero de mañanas en el dia actual
            return false;

        if (mediaUltimosDias(Turns.MORNING) >= media) { // si las horas total de la semana han superado la media
            return false;
        }

        if (ultimosDias(Turns.MORNING,5) >= 3 ) // maximo turnos por semana
            return false;

        if (getDiaAnterior() == Turns.NIGHT) // si el dia anterior no es una noche
            return false;

        return true;
    }

    private boolean checkTarde(){


        if (contarTurnosDia(Turns.AFTERNOON) == datosDia.getMaxAfternoons()) // maximo numero de tardes en el dia actual
            return false;

        if (mediaUltimosDias(Turns.AFTERNOON) >= media) { // si ha superado la media
            return false;
        }

        if (ultimosDias(Turns.AFTERNOON,5) >= 3) { // maximo turnos por semana
            return false;
        }

        if (getDiaAnterior() == Turns.NIGHT) // si el dia anterior no es una noche
            return false;

        return true;
    }

    private boolean checkNoche(){ // todo pensar el problema a la hora de generar otra semana

        if (contarTurnosDia(Turns.NIGHT) == datosDia.getMaxNights()) // maximo numero de noches en el dia actual
            return false;

        if (getDiaAnterior() == Turns.SALIENTE)
            return false;

        if (getDiaAnterior() == Turns.NIGHT && fecha.getDiaAnterior().equals("FRIDAY")) {
            return false;
        }

        if (ultimosDias(Turns.NIGHT,4) >= 1) // maximo noches cada cinco dias
            return false;
        if (getDiaAnterior() == Turns.NIGHT)// si el dia anterior no es una noche
            return false;

        if (mediaUltimosDias(Turns.MORNING) >= media) // si supera la media de la semana
            return false;

        return true;
    }

    private int mediaUltimosDias(Turns turno){ // comprobara las horas realizadas los ultimos cinco dias
        int diasAverificar = 7;
        int result = 0;
        for (int i = dia; i > (dia - diasAverificar) ; i--) {
            if (i >= 0)
                if (horario[registro][i] == turno)
                    result+=horario[registro][i].getHours();
        }
        return result;
    }

    private int contarTurnosDia(Turns turno){ // contara los turnos del dia
        int result = 0;
        for (int i = 0; i < employees.size() ; i++) {
            if (horario[i][dia] != null) {
                if (turno == horario[i][dia])
                    result++;
            }
        }
        return result;
    }
    private int contarTurnosSemana(Turns turno){ // contara los turnos que hay en la semana
        int result = 0;
        for (int dia = 0; dia < DIAS_SEMANA ; dia++) {
            if (horario[registro][dia] != null) {
                if (turno == horario[registro][dia])
                    result++;
            }
        }
        return result;
    }
    private int ultimosDias(Turns turno,int diasAverificar){ // contara los turnos que hay en los ultimos dias
         // se comprueba el numero de turnos de los ultimos 5 dias
        int result = 0;

        for (int i = dia; i > (dia - diasAverificar); i--) {
            if (i >= 0)
                if(horario[registro][i] == turno)
                    result++;
        }
        return result;
    }
}
