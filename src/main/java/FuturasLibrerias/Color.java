package FuturasLibrerias;

public enum Color {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");
    String ansi;
    private Color(String ansi){
        this.ansi = ansi;
    }
    public static String str(String mensaje, Color color){
        return color.getAnsi()+mensaje+RESET.getAnsi();
    }
    public static void pintar(String mensaje, Color color){
        System.out.println(color.getAnsi()+mensaje+RESET.getAnsi());
    }
    public String getAnsi() {
        return ansi;
    }
}
