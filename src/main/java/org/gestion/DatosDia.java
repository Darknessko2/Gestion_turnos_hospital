package org.gestion;

public class DatosDia {

    private int maxMornings;
    private int maxAfternoons;
    private int maxNights;

    // los parametros seran el numero maximo de puestos a cubrir en cada dia
    public DatosDia(int maxMornings, int maxAfternoons, int maxNights) {
        this.maxMornings = maxMornings;
        this.maxAfternoons = maxAfternoons;
        this.maxNights = maxNights;
    }

    public int getMaxMornings() {
        return maxMornings;
    }

    public int getMaxAfternoons() {
        return maxAfternoons;
    }

    public int getMaxNights() {
        return maxNights;
    }
}
