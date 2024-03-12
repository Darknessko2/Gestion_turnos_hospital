package org.gestion;

public class Day {

    private int morning;
    private int afternoon;
    private int nights;

    // los parametros seran el numero maximo de puestos a cubrir en cada dia
    public Day(int morning, int afternoon, int nights) {
        this.morning = morning;
        this.afternoon = afternoon;
        this.nights = nights;
    }

    public int getMorning() {
        return morning;
    }

    public int getAfternoon() {
        return afternoon;
    }

    public int getNights() {
        return nights;
    }
}
