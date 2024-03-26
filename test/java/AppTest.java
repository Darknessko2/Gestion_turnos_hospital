import FuturasLibrerias.Calendar;

import org.gestion.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    public static LinkedList<Employee> employees = new LinkedList<>();

    public static DatosDia dia = new DatosDia(2, 2, 2);

    public static Generador generador;
    public static Gestion gestion;
    public static Calendar fecha;

    @BeforeEach
    public void empleados(){

        employees.add(new Employee("M-N-1", new Turns[]{Turns.MORNING, Turns.NIGHT}, 0));

        employees.add(new Employee("M-N-2", new Turns[]{Turns.MORNING, Turns.NIGHT}, 0));

        employees.add(new Employee("M-N-3", new Turns[]{Turns.MORNING, Turns.NIGHT}, 0));

        employees.add(new Employee("M-N-4", new Turns[]{Turns.MORNING, Turns.NIGHT}, 0));

        employees.add(new Employee("M-N-5", new Turns[]{Turns.MORNING, Turns.NIGHT}, 0));

        employees.add(new Employee("T-N-1", new Turns[]{Turns.AFTERNOON, Turns.NIGHT}, 0));

        employees.add(new Employee("T-N-2", new Turns[]{Turns.AFTERNOON, Turns.NIGHT}, 0));

        employees.add(new Employee("T-N-3", new Turns[]{Turns.AFTERNOON, Turns.NIGHT}, 0));

        employees.add(new Employee("T-N-4", new Turns[]{Turns.AFTERNOON, Turns.NIGHT}, 0));

        employees.add(new Employee("T-N-5", new Turns[]{Turns.AFTERNOON, Turns.NIGHT}, 0));

        employees.add(new Employee("T-N-6", new Turns[]{Turns.AFTERNOON, Turns.NIGHT}, 0));

    }


    @ParameterizedTest
    @CsvFileSource(resources = "year.csv")
    public void testDias(int year){ // verificara que cada dia no sobrepasa o le falta turnos por asignar
        fecha = new Calendar(1, 1, year);
        generador = new Generador(dia,140,fecha,employees);
        gestion = new Gestion(dia, fecha,generador);

        gestion.creacionPlanilla(employees);

        int index = 0;
        for (int i = 0; i < 5; i++) {
            assertEquals(true,gestion.numeros(index)); ;
            index+= 28;
        }
    }

    @AfterEach
    public void  reset(){
        generador = null;
        gestion = null;
        Generador.dia = 0;
        Generador.valorNumerico = 1;
        Generador.registro = 0;

        employees = new LinkedList<>();
    }
//        public static Calculadora calculadora;
//

//        @Test
//        public void testResta(){
//            calculadora = new Calculadora(4,2);
//            int esperado = 2;
//            assertEquals(esperado,calculadora.resta());
//        }
//        @ParameterizedTest
//        @CsvFileSource(resources = "testeo.csv")
//        public void testMultiplicar(int num1, int num2, int resultadoEsperado) {
//            calculadora = new Calculadora(num1,num2);
//            int resultado = calculadora.multiplica();
//            assertEquals(resultadoEsperado, resultado);
//        }
//        @Test
//        public void testDividir(){
//            calculadora = new Calculadora(4,2);
//            int esperado = 2;
//            assertEquals(esperado,calculadora.divide());
//        }

}
