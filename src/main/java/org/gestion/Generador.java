package org.gestion;
import FuturasLibrerias.*;

import java.util.LinkedList;

public class Generador {
    private Turns[][] horario;
    private int media;
    private DatosDia datosDia;
    private Calendar fecha;
    private LinkedList<Employee> employees;

    public static final int DIAS_GENERADOS = 5;
    public static final int DIAS_A_CALCULAR = 30;
    public static int dia;
    public static int registro;
    public static int valorNumerico = 1;

    public Generador(DatosDia datosDia, int media, Calendar fecha,LinkedList<Employee> employees) {
        this.datosDia = datosDia;
        this.media = redondearMedia(media);
        this.horario = new Turns[employees.size()][70];
        this.fecha = fecha;
        this.employees = employees;

        iniciarHorario(); // a la hora de iniciar el horario se pondra los datos de los empleados
    }
    public int redondearMedia(int media){ // es importante que la media sea multiplo de 5
        int mediaRedondeada = media / (DIAS_A_CALCULAR / DIAS_GENERADOS);

        while (mediaRedondeada % 5 != 0)
            mediaRedondeada++;

        return mediaRedondeada;
    }
    public void iniciarHorario(){
        for (int i = 0; i < horario.length; i++) {
            horario[i] = employees.get(i).getHorariosEmpleado();
        }
    }
    public Turns[][] getHorario() {
        return horario;
    }

    public void rellenar(){

        for (registro = 0; registro < employees.size(); registro++) {

            // fecha con los valores originales de la copia
            //
//                        if (registro % 2 == 0 && valorNumerico % 2 == 0 ) {
//                            media = media / 2;
//                        } else if (registro % 2 != 0 && valorNumerico %2 != 0) {
//                            media = media / 2;
//                        }

            Calendar fechaOriginal = fecha.clone();

            fecha = fechaOriginal.clone();

            for (dia = 0; dia < (DIAS_GENERADOS * valorNumerico) ; dia++) {

                if (horario[registro][dia] == null){

//                    mostrarHorario(0);
//                    System.out.println("-".repeat(100));
                    horario[registro][dia] = turnoDisponible(employees.get(registro).getTurns());
                    employees.get(registro).agregarHoras(horario[registro][dia]);

                    if (horario[0][dia] != Turns.MORNING && registro > 6) // si el primer dato es mañana
                        datosDia.setMaxMornings(2);

                }
                fecha.incrementarDia();
            }
            fecha = fechaOriginal.clone();
        }
        valorNumerico++;
        // al final del bucle la fecha no cambiara sus valores originales
    }
    public void reOrganizar(){
        for (int i = 0; i < horario.length; i++) {
            horario[i] = employees.get(i).getHorariosEmpleado();
        }
    }
//    public void invertir(){
//            int count1 = 0;
//            int count2 = horario.length-1;
//            do {
//                Turns[] temp = horario[count1];
//                horario[count1++] = horario[count2];
//                horario[count2--] = temp;
//            }while (count1 < count2);
//    }


    public void mostrarHorario(int index){ // todo quitar
        App.imprimirSemana(fecha);
        for (int i = 0; i < horario.length; i++) {
            for (int j = index; j < (index+28); j++) {
                show(horario[i][j]);
            }
            System.out.print("HORAS -> "+Turns.horasTotales(horario[i]));
            System.out.println();
        }
    }
    public void mostrarHoras(){ // todo quitar
        int dias = 30;
        int horas = 0;
        for (int i = 0; i < horario.length; i++) {
            horas += Turns.horasTotales(horario[i]);
        }

        System.out.println("Horas totales "+horas);
        System.out.println("Horas esperadas"+48*dias);
    }
    public void show(Turns turno){ // todo quitar
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
    public boolean verificar(int day){ // todo posible prueba
        int m = 0;
        int t = 0;
        int n = 0;
        for (int i = 0; i < horario.length; i++) {
            if (horario[i][day] == Turns.MORNING)
                m++;
            if (horario[i][day] == Turns.AFTERNOON)
                t++;
            if (horario[i][day] == Turns.NIGHT)
                n++;
        }
        if (m < datosDia.getMaxMornings())
            return false;

        if (t < datosDia.getMaxAfternoons())
            return false;

        if (n < datosDia.getMaxNights())
            return false;

        return true;
    }
    public boolean turnoCorrecto(Turns turno){ // verificara si el turno asignado es correcto

        if (turno == Turns.MORNING)
            return checkMorning();
        else if(turno == Turns.AFTERNOON)
            return checkTarde();
        else
            return checkNoche();
    }
    public Turns getDiaAnterior(){ // devolvera el turno del dia anterior al actual
        Turns diaAnterior = null;
        if (dia != 0)
            diaAnterior = horario[registro][dia - 1];

        return diaAnterior;
    }

    private boolean verificarTurnosDia(Turns turno){ // si el dia ha superado o no los maximos turnos permitidos
        if (turno == Turns.MORNING)
            return contarTurnosDia(turno) >= datosDia.getMaxMornings();
        else if (turno == Turns.AFTERNOON)
            return contarTurnosDia(turno) >= datosDia.getMaxAfternoons();
        else
            return contarTurnosDia(turno) >= datosDia.getMaxNights();
    }

    private boolean checkMorning(){ // condiciones de la mañana

        if (getDiaAnterior() == Turns.AFTERNOON) // a la hora de cambiar los turnos es posible que el dia anterior sea tarde
            return false;

        if (getDiaAnterior() == Turns.NIGHT) // si el dia anterior es una noche, turno mañana es incorrecto
            return false;


        if (verificarTurnosDia(Turns.MORNING)) // maximo numero de mañanas en el dia actual
            return false;


        if (!fecha.esFinSemana()){

            if (mediaUltimosDias() > media && (ultimosDias(Turns.MORNING,5) >= 4) ) { // si ha superado la media y no tiene mas de 5 mañanas seguidas
                return false;
            }

            if (ultimosDias(Turns.MORNING,3) >= 3 ) // si en los ultimos 3 dias hay igual o mas de 3 noches
                return false;


        }else {
            if (dia >= 4) {
                if (fecha.getDiaSemana().equals("SATURDAY") && diasLibres(5) <= 1 && diaAnteriorlibre())
                    return false;
            }
            if (SabadoEsLibre())
                return false;
        }

        return true;
    }

    private boolean checkTarde() {

        if (verificarTurnosDia(Turns.AFTERNOON)) // maximo numero de tardes en el dia actual
            return false;

        if (!fecha.esFinSemana()){

            if (ultimosDias(Turns.AFTERNOON,3) >= 3 ) // maximo turnos por semana
                return false;

            if (mediaUltimosDias() > media ) { // si ha superado la media
                return false;
            }

        }else {
            if (dia >= 4) { // todo testear
                if (fecha.getDiaSemana().equals("SATURDAY") && diasLibres(5) <= 1 && diaAnteriorlibre())
                    return false;
            }

            if (SabadoEsLibre()) // si es domingo y el dia anterior fue libre no tendria que trabajar
                return false;
        }

        if (getDiaAnterior() == Turns.NIGHT) // si el dia anterior  es una noche
            return false;

        return true;
    }
    private boolean diaAnteriorlibre(){ // si el dia anterior no es ni saliente ni libre
        return getDiaAnterior() != Turns.LIBRE && getDiaAnterior() != Turns.SALIENTE;
    }

    private boolean SabadoEsLibre() {
        if (fecha.getDiaSemana().equals("SUNDAY")) {
            if (getDiaAnterior() == Turns.LIBRE || getDiaAnterior() == Turns.SALIENTE)
                return true;
        }
        return false;
    }

    private int diasLibres(int diasAverificar){ // contara los dias libres o salientes de los dias anteriores
        int result = 0;
        for (int i = dia; i > (dia - diasAverificar); i--) {
            if (i >= 0){
                if (horario[registro][i] == Turns.LIBRE || horario[registro][i] == Turns.SALIENTE )
                     result++;
            }
        }
        return result;
    }

    private boolean checkNoche(){

        if (verificarTurnosDia(Turns.NIGHT)) // maximo numero de noches en el dia actual
            return false;


        if (ultimosDias(Turns.NIGHT,3) >= 1) // maximo noches cada tres dias
            return false;

        if (getDiaAnterior() == Turns.NIGHT)// no se permite dos noches seguidas
            return false;

        if (dia >= 5) {
            if (diasLibres(7) < 1)
                return false;
        }

        if (contarTurnosDia(Turns.NIGHT) == 1 && registro < 6) // con esta condicion se reparte la noche a cada turno  todo mejorar
            return false;

        return true;
    }

    private int mediaUltimosDias(){ // comprobara las horas realizadas los ultimos cinco dias
        int diasAverificar = 5;
        int result = 0;
        for (int i = dia; i > (dia - diasAverificar) ; i--) {
            if (i >= 0)
                if (horario[registro][i]!= null)
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
    private int contarTurnosSemana(Turns turno){ // contara los turnos que hay en la semana todo posiblemente borrar
        int result = 0;
        for (int dia = 0; dia < DIAS_GENERADOS; dia++) {
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
