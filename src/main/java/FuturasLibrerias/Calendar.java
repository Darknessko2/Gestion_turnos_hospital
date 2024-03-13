package FuturasLibrerias;

import org.gestion.Turns;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Calendar {
    private int year;
    private int month;
    private int day;

    public Calendar(int day, int month, int year) {
        if (isValid(year,month,day)) {
            this.year = year;
            this.month = month;
            this.day = day;
        }else
            throw new IllegalArgumentException("ERROR AL CREAR LA CLASE");
    }

    public void incrementarDia(){
        if (day >= getLimit(month,year)) {
            day = 1;
            incrementarMes();
        }else
            day += 1;
    }
    public void incrementarMes(){
        if (month == 12 ) {
            incrementarAnyo(1);
            month = 1;
        }else
            month += 1;
    }

    public void incrementarAnyo(int quantity){
        year+=quantity;
        if (!isBisiesto(year) && month == 2 && day == 29)  // si actualmente estamos en un año bisiesto y cambiamos a uno que no es
            day = 28;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month > 0 && month <= 12)
            this.month = month;
        else
            System.err.println("valor "+month+" no valido para el mes");
    }

    public int getDay() {
        return day;
    }

    public static int getLimit(int month, int year) { // parametros antes de instanciar la clase
        int limit;
        switch (month) {
            case 2:
                limit = (isBisiesto(year)) ? 29 : 28; // si es febrero y bisiesto tiene 29 dias
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                limit = 30;
                break;
            default:
                limit = 31;
                break;
        }
        return limit;
    }
    public String getDiaSemana(){ // devolvera el dia en string de la fecha del objeto
        LocalDate fecha = LocalDate.of(year,month,day);
        DayOfWeek dayOfWeek = fecha.getDayOfWeek();
        return dayOfWeek.toString();
    }

    public void mostrar(){
        System.out.println("Año : "+year +" Mes : "+month+" Dia : "+day);
    }
    private boolean isValid(int year, int month, int day){
        boolean valid = false;
        if (year > 0 && month > 0 && month <=12 && day > 0 && day <=31) { // fuera de los parametros logicos

            int limit = getLimit(month,year);
            if (day <= limit)  // cada mes tiene su propio limite
                valid = true;
            else
                System.err.println("El mes " + month + " tiene como maximo " + limit + " dias");

        }else
            System.err.println("Valores fuera de rango");

        return valid;
    }
    public static boolean isBisiesto(int year){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public boolean iguales(Calendar otherDate){
        return (year == otherDate.year && month == otherDate.month && day == otherDate.day);
    }
    public String formateado(){
        return day+"/"+month+"/"+year;
    }
    public Calendar clone() { // un nuevo objeto calendario con los datos del otro
        return new Calendar(day,month,year);
    }
    public boolean esFinSemana(){
        return getDiaSemana().equals("SATURDAY") || getDiaSemana().equals("SUNDAY");
    }
    @Override
    public String toString() {
        return "Calendar{" + "year=" + year + ", month=" + month + ", day=" + day + '}';
    }
}
