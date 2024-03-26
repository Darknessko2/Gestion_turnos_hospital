package org.gestion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private String code;
    private Turns[] turns;
    private int hours;
    private Turns[] horariosEmpleado;

    public Employee(String code, Turns[] turns, int hours) {
        this.code = code;
        this.turns = turns;
        this.hours = hours;
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
        if (turns[0] == Turns.AFTERNOON)
            turns[0] = Turns.MORNING;
        else
            turns[0] = Turns.AFTERNOON;
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
    @Override
    public int compareTo(Employee o) {
//        if (Arrays.equals(this.turns,o.turns)) // se ordenan de menor a mayour
//            return this.hours -o.hours;
//        else
//            return 0;

        // Comparación de los turnos en orden inverso
        for (int i = this.turns.length - 1; i >= 0; i--) {
            if (this.turns[i] != o.turns[i]) {
                return o.turns[i].compareTo(this.turns[i]);
            }
        }

        // Si los turnos son iguales, se compara por horas
        return this.hours - o.hours;

    }
}
