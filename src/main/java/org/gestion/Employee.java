package org.gestion;

import java.util.Arrays;
import java.util.Objects;

public class Employee  {
    private String code;
    private Turns[] turns;
    private int hours;
    private Turns[] horariosEmpleado;

    public Employee(String code, Turns[] turns) {
        this.code = code;
        this.turns = turns;
        this.hours = 0;
        this.horariosEmpleado = new Turns[400];
    }

    public Turns[] getHorariosEmpleado() {
        return horariosEmpleado;
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

    public void setTurns(Turns[] turns) {
        this.turns = turns;
    }
    public void intercambiarTurno(){ // intercambia el valor de tarde a mañana y viceversa
        if (turns[0] == Turns.TARDES)
            turns[0] = Turns.MANYANAS;
        else
            turns[0] = Turns.TARDES;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Employee employee = (Employee) object;
        return Objects.equals(code, employee.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Employee{" + "code='" + code + '\'' + ", turns=" + Arrays.toString(turns) + ", hours=" + hours + '}';
    }

}
