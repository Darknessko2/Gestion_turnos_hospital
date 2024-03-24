package org.gestion;

import java.util.Arrays;
import java.util.Comparator;

public class Employee implements Comparable<Employee> {
    private String code;
    private Turns[] turns;
    private int hours;

    public Employee(String code, Turns[] turns, int hours) {
        this.code = code;
        this.turns = turns;
        this.hours = hours;
    }

    public String getCode() {
        return code;
    }
    public void agregarHoras(Turns dia){
        hours += dia.getHours();
    }

    public Turns[] getTurns() {
        return turns;
    }

    public int getHours() {
        return hours;
    }

    @Override
    public String toString() {
        return "Employee{" + "code='" + code + '\'' + ", turns=" + Arrays.toString(turns) + ", hours=" + hours + '}';
    }
    @Override
    public int compareTo(Employee o) {
        if (Arrays.equals(this.turns,o.turns)) // se ordenan de menor a mayour
            return this.hours -o.hours;
        else
            return 0;

    }
}
