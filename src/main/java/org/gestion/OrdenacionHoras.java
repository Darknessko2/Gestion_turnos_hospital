package org.gestion;

import java.util.Comparator;

public class OrdenacionHoras implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        // Comparación de los turnos en orden inverso
        for (int i = o1.getTurns().length - 1; i >= 0; i--) {
            if (o1.getTurns()[i] != o2.getTurns()[i]) {
                return o2.getTurns()[i].compareTo(o1.getTurns()[i]);
            }
        }
        // Si los turnos son iguales, se compara por horas
        return o1.getHours() - o2.getHours();
    }
}
