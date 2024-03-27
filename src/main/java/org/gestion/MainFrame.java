package org.gestion;

import FuturasLibrerias.Calendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainFrame extends JFrame{
    private JCheckBox manyanaCheck;
    private JButton agregarButton;
    private JButton generarButton;
    private JPanel MainPanel;
    private JCheckBox NocheCheck;
    private JCheckBox tardeCheck;
    private JTextField codeCamp;
    private JTextField yearCamp;
    private JLabel MediaPanel;
    private JTextField MediaTexto;
    public static LinkedList<Employee> employees = new LinkedList<>();


    public static void main(String[] args) {
        MainFrame main = new MainFrame();
    }


    public MainFrame(){
        setContentPane(MainPanel);
        setTitle("Generacion de turnos");
        setSize(300,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        agregarButton.addActionListener(new ActionListener() { // se agrega a los empleados
            @Override
            public void actionPerformed(ActionEvent e) {
                employees.add(new Employee(codeCamp.getText(),listaTurno()));
                reset();
            }
        });

        codeCamp.addKeyListener(new KeyAdapter() { // agregar tambien funcionar con la tecla enter
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    employees.add(new Employee(codeCamp.getText(), listaTurno()));
                    reset();
                }
            }
        });

        generarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer anyo = Integer.parseInt(yearCamp.getText());
                Integer media = Integer.parseInt(MediaTexto.getText());
                execute(anyo,media);
            }
        });
    }
    public void reset(){
        codeCamp.setText("");
//        NocheCheck.setSelected(false);
//        tardeCheck.setSelected(false);
//        manyanaCheck.setSelected(false);
    }
    public Turns[] listaTurno(){
        ArrayList<Turns> listaTurnos = new ArrayList<>();
        if (manyanaCheck.isSelected())
            listaTurnos.add(Turns.MANYANAS);

        if (tardeCheck.isSelected())
            listaTurnos.add(Turns.TARDES);

        if (NocheCheck.isSelected())
            listaTurnos.add(Turns.NOCHE);

        Turns[] arregloTurnos = listaTurnos.toArray(new Turns[0]); // convierte la lista en arrays de turnos
        return arregloTurnos;
    }


    public void execute(int year , int media){

        Calendar fecha = new Calendar(1, 1, year);

        DatosDia dia = new DatosDia(2, 2, 2); // maximas dias aun por parametrizar

        Generador generador = new Generador(dia, media, fecha, employees);

        Gestion gestion = new Gestion(dia, fecha, generador);

        gestion.creacionPlanilla(employees);

        Escritura write = new Escritura(fecha, employees);

        write.escribir("prueba.csv");
    }
}
