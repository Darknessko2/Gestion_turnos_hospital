package org.gestion;

import java.util.Comparator;

public class OrdenacionPorCodigo implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) { // se ordenan los empleados por codigo
        return o1.getCode().compareTo(o2.getCode());
//        if (Arrays.equals(o1.getTurns(), o2.getTurns()))
//            return o1.getHours() - o2.getHours();
//        else
//            return 0;
    }
}
