package calculadora_con_JFrame;

import javax.swing.*;
import java.awt.*;

public class calculadora_Con_JFrame extends JFrame {
    private JButton boton_clear;
    private JButton boton_delete;
    private JButton operador_porcentaje;
    private JButton operador_division;
    private JButton operador_multiplicar;
    private JButton operador_resta;
    private JButton operador_mas;
    private JButton operador_igual;
    private JButton boton_coma;
    private JButton numero_0;
    private JButton numero_1;
    private JButton numero_2;
    private JButton numero_3;
    private JButton numero_4;
    private JButton numero_5;
    private JButton numero_6;
    private JButton numero_7;
    private JButton numero_8;
    private JButton numero_9;
    private JPanel JPanel1;
    private JPanel panelBotones;
    private JLabel mostrar_resultado;
    private JButton operador_Ans;
    private JButton operador_Cos;
    private JButton operador_log;
    private JButton operador_Raiz;
    private JButton operador_pi;

    private double valorPrevio = 0;
    private double ultimoResultado = 0;  // Variable para almacenar el último resultado
    private String operador = "";
    private boolean operadorPulsado = false;

    public calculadora_Con_JFrame() {
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculadora");

        // Inicializamos los componentes
        boton_clear = new JButton("Clear");
        boton_delete = new JButton("Delete");
        operador_porcentaje = new JButton("%");
        operador_division = new JButton("÷");
        operador_multiplicar = new JButton("x");
        operador_resta = new JButton("-");
        operador_mas = new JButton("+");
        operador_igual = new JButton("=");
        operador_pi = new JButton("π"); // añadir
        operador_Ans = new JButton("Ans"); // añadir
        operador_Cos = new JButton("Cos"); // añadir
        operador_log = new JButton("log"); // añadir
        operador_Raiz = new JButton("√ "); // añadir
        boton_coma = new JButton(",");
        numero_0 = new JButton("0");
        numero_1 = new JButton("1");
        numero_2 = new JButton("2");
        numero_3 = new JButton("3");
        numero_4 = new JButton("4");
        numero_5 = new JButton("5");
        numero_6 = new JButton("6");
        numero_7 = new JButton("7");
        numero_8 = new JButton("8");
        numero_9 = new JButton("9");

        // Inicializamos el JLabel para mostrar el resultado
        mostrar_resultado = new JLabel("0", SwingConstants.RIGHT);
        mostrar_resultado.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel1 = new JPanel(new BorderLayout());
        panelBotones = new JPanel(new GridLayout(6, 4, 5, 5));

        // Añadir ActionListeners a los botones numéricos
        addNumberButtonAction(numero_0, "0");
        addNumberButtonAction(numero_1, "1");
        addNumberButtonAction(numero_2, "2");
        addNumberButtonAction(numero_3, "3");
        addNumberButtonAction(numero_4, "4");
        addNumberButtonAction(numero_5, "5");
        addNumberButtonAction(numero_6, "6");
        addNumberButtonAction(numero_7, "7");
        addNumberButtonAction(numero_8, "8");
        addNumberButtonAction(numero_9, "9");

        // Botones de operaciones
        operador_mas.addActionListener(e -> setOperador("+"));
        operador_resta.addActionListener(e -> setOperador("-"));
        operador_multiplicar.addActionListener(e -> setOperador("x"));
        operador_division.addActionListener(e -> setOperador("÷"));
        operador_porcentaje.addActionListener(e -> setOperador("%"));
        operador_pi.addActionListener(e -> setOperador("π"));
        operador_Ans.addActionListener(e -> usarAns());
        operador_Cos.addActionListener(e -> setOperador("Cos"));
        operador_log.addActionListener(e -> setOperador("log"));
        operador_Raiz.addActionListener(e -> setOperador("√ "));

        // Botón igual
        operador_igual.addActionListener(e -> calcularResultado());

        // Botón clear
        boton_clear.addActionListener(e -> {
            mostrar_resultado.setText("0");
            valorPrevio = 0;
            operador = "";
            operadorPulsado = false;
        });

        // Botón delete (borrar último dígito)
        boton_delete.addActionListener(e -> {
            String textoActual = mostrar_resultado.getText();
            if (textoActual.length() > 1) {
                mostrar_resultado.setText(textoActual.substring(0, textoActual.length() - 1));
            } else {
                mostrar_resultado.setText("0");
            }
        });

        // Botón coma
        boton_coma.addActionListener(e -> {
            if (!mostrar_resultado.getText().contains(",")) {
                mostrar_resultado.setText(mostrar_resultado.getText() + ",");
            }
        });

        // Añadir botones al panel en orden
        panelBotones.add(boton_clear);
        panelBotones.add(boton_delete);
        panelBotones.add(operador_porcentaje);
        panelBotones.add(operador_division);
        //------------------------------------
        panelBotones.add(operador_Ans);
        panelBotones.add(operador_Cos);
        panelBotones.add(operador_log);
        panelBotones.add(operador_Raiz);
        //------------------------------------
        panelBotones.add(numero_7);
        panelBotones.add(numero_8);
        panelBotones.add(numero_9);
        panelBotones.add(operador_multiplicar);
        //------------------------------------
        panelBotones.add(numero_4);
        panelBotones.add(numero_5);
        panelBotones.add(numero_6);
        panelBotones.add(operador_resta);
        //------------------------------------
        panelBotones.add(numero_1);
        panelBotones.add(numero_2);
        panelBotones.add(numero_3);
        panelBotones.add(operador_mas);
        //------------------------------------
        panelBotones.add(operador_pi);
        panelBotones.add(numero_0);
        panelBotones.add(boton_coma);
        panelBotones.add(operador_igual);

        JPanel1.add(mostrar_resultado, BorderLayout.NORTH);
        JPanel1.add(panelBotones, BorderLayout.CENTER);
        add(JPanel1);

        setVisible(true);
    }

    private void addNumberButtonAction(JButton button, String number) {
        button.addActionListener(e -> {
            if (operadorPulsado) {
                mostrar_resultado.setText(number);
                operadorPulsado = false;
            } else {
                if (mostrar_resultado.getText().equals("0")) {
                    mostrar_resultado.setText(number);
                } else {
                    mostrar_resultado.setText(mostrar_resultado.getText() + number);
                }
            }
        });
    }

    private void setOperador(String operadorSeleccionado) {
        valorPrevio = Double.parseDouble(mostrar_resultado.getText().replace(",", "."));
        operador = operadorSeleccionado;
        operadorPulsado = true;
    }

    private void calcularResultado() {
        double valorActual = Double.parseDouble(mostrar_resultado.getText().replace(",", "."));
        double resultado = 0;

        switch (operador) {
            case "+":
                resultado = valorPrevio + valorActual;
                break;
            case "-":
                resultado = valorPrevio - valorActual;
                break;
            case "x":
                resultado = valorPrevio * valorActual;
                break;
            case "÷":
                if (valorActual != 0) {
                    resultado = valorPrevio / valorActual;
                } else {
                    mostrar_resultado.setText("Error");
                    return;
                }
                break;
            case "%":
                resultado = valorPrevio * (valorActual / 100); // Aplica el porcentaje correctamente
                break;
            case "Cos":
                resultado = Math.cos(Math.toRadians(valorActual));// Convierte grados a radianes y calcula el coseno
            case "log":
                resultado = Math.log(Math.toRadians(valorActual));
            case "√ ":
                resultado = Math.sqrt(valorActual);
            case "π":
                resultado = 3.14;
        }

        ultimoResultado = resultado; // Almacenar el resultado para el botón Ans
        mostrar_resultado.setText(String.valueOf(resultado).replace(".", ","));
        operadorPulsado = true;
    }

    private void usarAns() {
        mostrar_resultado.setText(String.valueOf(ultimoResultado).replace(".", ","));
        operadorPulsado = false;
    }

    public static void main(String[] args) {
        new calculadora_Con_JFrame();
    }
}
