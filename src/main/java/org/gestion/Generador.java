package org.gestion;
import FuturasLibrerias.*;

public class Generador {
    private Turns[][] horario;
    private int numTrabajadores;
    private int media;
    private Day day;
    private Calendar fecha;

    private static final int DIAS_SEMANA = 7;
    private static int dia;
    private static int registro;

    public Generador(Day day, int media, int numTrabajadores, Calendar fecha) {
        this.day = day;
        this.media = media;
        this.numTrabajadores = numTrabajadores;
        this.horario = new Turns[numTrabajadores][DIAS_SEMANA];
        this.fecha = fecha;
    }
    public Turns[][] getHorario() {
        return horario;
    }

    public void rellenar(Turns[] turnos, int cantidad){

        Calendar original = fecha.clone();
        // guardo los datos de la fecha original


        int veces = cantidad * DIAS_SEMANA;

        for (registro = 0; registro < numTrabajadores; registro++) {

            // fecha con los valores originales de la copia
            fecha = original.clone();

            for (dia = 0; dia < DIAS_SEMANA ; dia++) {

                if (horario[registro][dia] == null){
                    horario[registro][dia] = turnoDisponible(turnos);
                    veces--;
                    fecha.incrementarDia();
                }
                if (veces == 0) // todo quitar
                    break;
            }
            if (veces == 0) {
                break;
            }
        }
        fecha = original.clone();
        // al final del bucle la fecha no cambiara sus valores originales
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

        if (turno == Turns.AF)
            color = Color.YELLOW;
        else if (turno == Turns.LI)
            color = Color.GREEN;
        else if (turno == Turns.NI)
            color = Color.BLUE;
        else if(turno == Turns.MO)
            color = Color.RED;
        else
            color = Color.CYAN;

        System.out.print(Color.str(turno+" ",color));
    }
    public Turns turnoDisponible(Turns[] turnos){ // devolvera el turno correspondiente

            for (int i = 0; i < turnos.length ; i++) {
                Turns turno = turnos[i];
                if (turnoCorrecto(turno))
                    return turno;
            }
            return (getDiaAnterior() == Turns.NI) ?Turns.SA:Turns.LI;
        // si no le corresponde ningun turno el dia sera libre o saliente
    }
    public boolean turnoCorrecto(Turns turno){

        if (turno == Turns.MO)
            return checkMorning();
        else if(turno == Turns.AF)
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

        if (contarTurnosDia(Turns.MO) >= day.getMorning()) // maximo numero de mañanas en el dia actual
            return false;

        if (Turns.horasTotales(horario[registro]) >= media) { // si las horas total de la semana han superado la media
            return false;
        }

        if (ultimosDias(Turns.MO) >= 3 ) // maximo turnos por semana
            return false;

        if (getDiaAnterior() == Turns.NI) // si el dia anterior no es una noche
            return false;

        return true;
    }

    private boolean checkTarde(){

        if (contarTurnosDia(Turns.AF) == day.getAfternoon()) // maximo numero de tardes en el dia actual
            return false;

        if (Turns.horasTotales(horario[registro]) >= media) { // si ha superado la media
            return false;
        }

        if (ultimosDias(Turns.AF) >= 4 ) { // maximo turnos por semana
            return false;
        }

        if (getDiaAnterior() == Turns.NI) // si el dia anterior no es una noche
            return false;

        return true;
    }

    private boolean checkNoche(){ // todo pensar el problema a la hora de generar otra semana

        if (contarTurnosDia(Turns.NI) == day.getNights()) // maximo numero de noches en el dia actual
            return false;

        /*
        if (!(fecha.getDiaSemana().equals("SATURDAY"))||!(fecha.getDiaSemana().equalsIgnoreCase("SUNDAY"))) {
            if (ultimosDias(Turns.NI) >= 1) // maximo noches por semana todo parametrizar
                return false;
        }
         */


        if (!fecha.esFinSemana()) {  // si no es fin de semana no se tomara en cuenta los siguientes condicionales

            if (ultimosDias(Turns.NI) >= 1) // maximo noches cada cinco dias
                return false;

            if (getDiaAnterior() == Turns.NI) // si el dia anterior no es una noche
                return false;
        }

        if (Turns.horasTotales(horario[registro]) > media) // si supera la media de la semana
            return false;

        return true;
    }

    private int contarTurnosDia(Turns turno){ // contara los turnos del dia
        int result = 0;
        for (int i = 0; i < numTrabajadores; i++) {
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
    private int ultimosDias(Turns turno){ // contara los turnos que hay en la semana
        int diasAverifiar = 5; // se comprueba el numero de turnos de los ultimos 5 dias

        int result = 0;

        for (int i = dia; i > (dia - diasAverifiar); i--) {
            if (i >= 0)
                if(horario[registro][i] == turno)
                    result++;
        }
        return result;
    }
}