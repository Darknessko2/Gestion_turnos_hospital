package org.gestion;

import java.util.Arrays;
import java.util.Comparator;

public class Employee implements Comparator<Employee> {
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
    public int compare(Employee o1, Employee o2) {
        return o1.hours-o2.hours;
    }
}
