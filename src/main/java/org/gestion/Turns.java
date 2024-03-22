package org.gestion;

public enum Turns {
    NIGHT(10),
    MORNING(7),
    AFTERNOON(7),
    LIBRE(0),
    SALIENTE(0);

    private int horas;

    private Turns(int horas) {
        this.horas = horas;
    }

    public int getHours() {
        return horas;
    }
    public static int horasTotales(Turns[] turnos){
        int result = 0;
        for (int i = 0; i < turnos.length; i++) {
            if (turnos[i] != null)
                result += turnos[i].horas;
        }
        return result;
    }
}
