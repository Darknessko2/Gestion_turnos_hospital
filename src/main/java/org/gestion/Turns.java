package org.gestion;

public enum Turns {
    NOCHE(10),
    MANYANAS(7),
    TARDES(7),
    LIBRE(0),
    SALIENTE(0);

    private int horas;

    private Turns(int horas) {
        this.horas = horas;
    }

    public int getHours() {
        return horas;
    }
    public void setHoras(int horas){
        this.horas = horas;
    }
    public static int horasTotales(Turns[] turnos){
        int result = 0;
        for (int i = 0; i < turnos.length; i++) {
            if (turnos[i] != null)
                result += turnos[i].horas;
        }
        return result;
    }
    public static String formateado(Turns turno){
        return turno.toString().substring(0,1);
    }
}
