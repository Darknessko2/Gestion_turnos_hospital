package FuturasLibrerias;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

public class MainCalendar {
    static Scanner sc = new Scanner(System.in);
    public static final String RESET = "\u001B[0m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String VERDE = "\u001B[32m";
     public static Calendar[] listCalendars = new Calendar[0];

    public static void main(String[] args) {

        LocalDate currentDate = LocalDate.now();

        int option;
        do {
            option = menu();

            switch (option) {
                case 1:
                    showDates();
                    break;

                case 2:
                    listCalendars = increaseArr(listCalendars,createDate("Ingresa la fecha a guardar"));
                    break;

                case 3:
                    listCalendars = increaseArr(listCalendars,dateNow(currentDate));
                    break;

                case 4:
                    compare(listCalendars);
                    break;

                case 5:
                    editDate(listCalendars);
                    break;
            }
        } while (option != 0);
    }

    private static void editDate(Calendar[] listDates) {
        showDates();
        Calendar date = createDate("Ingresa la fecha para editar(Aunque no este guardada)");
        warnings("Ten en cuenta que esta vez no se realizarán cambios permanentes.");
        int secondOption;

        do {
            String fecha = date.formateado();
            secondOption = subMenu();
            switch (secondOption){
                case 1:
                    date.incrementarDia();
                    break;

                case 2:
                    date.incrementarMes();
                    break;

                case 3:
                    int cantidad = inputNum("Ingresa la cantidad de años a sumar",1,10000);
                    date.incrementarAnyo(cantidad);
                    break;
            }
            success(fecha+" -->> "+date.formateado());
        }while (secondOption!=0);
    }

    static int menu() {
        return inputNum("(1)Mostrar fechas guardadas\n(2)Guardar fecha personalizada\n(3)Guardar fecha actual"
                        + "\n(4)Comparar fechas\n(5)Editar una fecha\n(0)Salir"
                ,0, 6);
    }
    static int subMenu(){
        return inputNum("(1)Incrementar dia\n(2)Incrementar Mes\n(3)Incrementar año\n(0)Volver al menu principal",0,3);
    }
    static void showDates(){
        if (listCalendars.length>0) {
            success("--Fechas guardadas--");
            for (Calendar calendario : listCalendars) {
                System.out.println("DIA : %s MES: %s AÑO: %s".formatted(calendario.getDay(), calendario.getMonth(), calendario.getYear()));
                System.out.println("-".repeat(20));
            }
        }else
            warnings("No hay fechas guardadas");
    }
    static Calendar[] increaseArr(Calendar[] listCalendar, Calendar date){
        listCalendar = Arrays.copyOf(listCalendar,listCalendar.length+1);
        listCalendar[listCalendar.length-1] = date;
        return listCalendar;
    }
     static Calendar createDate(String message){ // testear
         int month = 0;
         int year = 0;
         int day = 0;
         do {
             System.out.println(message);
             String answer = inputStr("Formato => 01/01/1010");
             if (checkFormat(answer)){
                 day = Integer.parseInt(answer.substring(0,2));
                 month = Integer.parseInt(answer.substring(3,5));
                 year = Integer.parseInt(answer.substring(6));
             }else
                 warnings("Formato no valido");
             if (limites(day,month,year))
                 warnings("Fecha no valida");

         }while (limites(day, month, year));

         success("Fecha "+day+"/"+month+"/"+year+" validada");

         return new Calendar(day,month,year);
    }

    private static boolean limites(int day, int month, int year) {
        return day > Calendar.getLimit(month, year) || month > 12 || month < 1 || day < 1 || year < 1;
    }

    static Calendar dateNow(LocalDate currentDate){
        success("Fecha creada exitosamente %s/%s/%s".formatted(
                currentDate.getDayOfMonth(),
                currentDate.getMonthValue(),
                currentDate.getYear()
        ));
        return new Calendar(currentDate.getDayOfMonth(),currentDate.getMonthValue(),currentDate.getYear());
    }
    static boolean checkFormat(String answer){
        String formato = "++/++/++++";
        answer = answer.replaceAll("[0-9]","+");
        return answer.equals(formato);
    }

    static int askyear(String message){
        int num;
        do {
            num = readInt(message);
            if (num <= 0)
                warnings("Solo valores mayores que 0");
        }while (num<=0);

        sc.nextLine();
        return num;
    }

    static void compare(Calendar[] listDates){
        showDates();
        warnings("No es necesario ingresar una fecha guardada");
        Calendar date1 = createDate("Ingresa la primera fecha a comparar");
        Calendar date2 = createDate("Ingresa la segunda fecha");

        if (date1.iguales(date2))
            success("Las dos fechas son iguales");
        else
            success("Las fechas son distintas");
    }


    static int inputNum(String message, int min, int max) { // al pedir numero habra rangos
        System.out.println(message);
        int num;
        do {
            num = readInt("Ingresa un numero entre " + min + " y " + max);
        } while (!(num >= min && num <= max));
        sc.nextLine();
        return num;
    }
    static int readInt(String mensaje) { // solo leera numeros enteros
        System.out.println(mensaje);

        while (!sc.hasNextInt()) {
            warnings("Ingresa un valor valido");
            sc.next();
        }
        return sc.nextInt();
    }
    static void warnings(String message){
        System.out.println(AMARILLO+message+RESET);
    }
    static void success(String message) {
        System.out.println(VERDE + message + RESET);
    }
    static String inputStr(String mensaje) {
        System.out.println(mensaje);
        return sc.nextLine();
    }
}

