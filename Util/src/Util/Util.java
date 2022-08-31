package Util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.TableColumn;
import javax.swing.ListSelectionModel;

public class Util {
    private static Object[] estiloEncabezadoNormal = {
        new Font("Tahoma",Font.BOLD,11),
        new Color(255,255,255),
        new Color(0,0,0)//Color(0,100,255)
    };

    private static Object[] estiloDetalleNormal = {
        new Font("Tahoma",Font.BOLD,11),
        new Color(0,0,0),
        new Color(0,204,102),//Color(255,255,190),
        new Font("Tahoma",Font.PLAIN,11),
        new Color(0,0,0),
        new Color(190,255,255),//new Color(255,255,255)
    };

    private static Object[] estiloEncabezadoEdicion = {
        new Font("Tahoma",Font.BOLD,11),
        new Color(255,255,255),
        new Color(0,0,0)//Color(0,100,255)
    };

    private static Object[] estiloDetalleEdicion = {
        new Font("Tahoma",Font.BOLD,11),
        new Color(0,0,0),
        new Color(190,255,255),
        new Font("Tahoma",Font.PLAIN,11),
        new Color(0,0,0),
        new Color(190,255,255),//new Color(255,255,255)
    };

    private static Object[] estiloEncabezadoResaltado = {
        new Font("Tahoma",Font.BOLD,11),
        new Color(255,255,255),
        new Color(0,0,0)//Color(0,100,255)
    };

    private static Object[] estiloDetalleResaltado = {
        new Font("Tahoma",Font.BOLD,11),
        new Color(0,0,0),
        new Color(255,180,120),
        new Font("Tahoma",Font.PLAIN,11),
        new Color(0,0,0),
        new Color(190,255,255),//new Color(255,255,255)
    };

    public static void AplicarIcono(JFrame formulario,String Icono) {
        formulario.setIconImage(Toolkit.getDefaultToolkit().getImage(formulario.getClass().getResource("/IconosFinales/"+Icono+".png")));
    }

    public static void AplicarIcono(JDialog formulario,String Icono) {
        formulario.setIconImage(Toolkit.getDefaultToolkit().getImage(formulario.getClass().getResource("/IconosFinales/"+Icono+".png")));
    }

    public static void InicializarContenedor(Container container) {
        Component[] components = container.getComponents();

        for(int i = 0; i < components.length; i++) {
            if(components[i] instanceof JLabel) {
                ((JLabel)(components[i])).setFont(new Font("Tahoma",Font.BOLD,11));
                ((JLabel)(components[i])).setForeground(new Color(0,0,0));
                ((JLabel)(components[i])).setBackground(new Color(240,240,240));
            }else
            if(components[i] instanceof JTextField) {
                if(((JTextField)(components[i])).isEditable()) {
                    ((JTextField)(components[i])).setForeground(Color.BLACK);
                    ((JTextField)(components[i])).setBackground(Color.WHITE);
                }
                else {
                    ((JTextField)(components[i])).setForeground(new Color(50,50,50));
                    ((JTextField)(components[i])).setBackground(new Color(250,250,250));
                }

                ((JTextField)(components[i])).setDisabledTextColor(Color.BLACK);
                ((JTextField)(components[i])).setFont(new Font("Tahoma",Font.BOLD,11));
                ((JTextField)(components[i])).setMargin(new Insets(3,3,3,3));
                ((JTextField)(components[i])).setText("");
            }
            else if(components[i] instanceof JTextArea) {
                if(((JTextArea)(components[i])).isEditable()) {
                    ((JTextArea)(components[i])).setForeground(Color.BLACK);
                    ((JTextArea)(components[i])).setBackground(Color.WHITE);
                }
                else {
                    ((JTextArea)(components[i])).setForeground(new Color(50,50,50));
                    ((JTextArea)(components[i])).setBackground(new Color(250,250,250));
                }

                ((JTextArea)(components[i])).setDisabledTextColor(Color.BLACK);
                ((JTextArea)(components[i])).setFont(new Font("Tahoma",Font.BOLD,11));
                ((JTextArea)(components[i])).setMargin(new Insets(3,3,3,3));
                ((JTextArea)(components[i])).setLineWrap(true);
                ((JTextArea)(components[i])).setTabSize(2);
                ((JTextArea)(components[i])).setText("");
            }
            else if(components[i] instanceof JTextPane) {
                if(((JTextPane)(components[i])).isEditable()) {
                    ((JTextPane)(components[i])).setForeground(Color.BLACK);
                    ((JTextPane)(components[i])).setBackground(Color.WHITE);
                }
                else {
                    ((JTextPane)(components[i])).setForeground(new Color(50,50,50));
                    ((JTextPane)(components[i])).setBackground(new Color(250,250,250));
                }

                ((JTextPane)(components[i])).setDisabledTextColor(Color.BLACK);
                ((JTextPane)(components[i])).setFont(new Font("Tahoma",Font.BOLD,11));
                ((JTextPane)(components[i])).setMargin(new Insets(3,3,3,3));
                ((JTextPane)(components[i])).setText("");
            }
            else if(components[i] instanceof JButton) {
                ((JButton)(components[i])).setForeground(new Color(50,50,50));
                ((JButton)(components[i])).setBackground(new Color(250,250,250));
                ((JButton)(components[i])).setFont(new Font("Tahoma",Font.BOLD,11));
            }
            else if(components[i] instanceof JComboBox) {
                ((JComboBox)(components[i])).setForeground(Color.BLACK);
                ((JComboBox)(components[i])).setBackground(Color.WHITE);
                ((JComboBox)(components[i])).setFont(new Font("Tahoma",Font.BOLD,11));

                if(((JComboBox)(components[i])).getItemCount() > 0) {
                    if(((JComboBox)(components[i])).getItemAt(((JComboBox)(components[i])).getItemCount() - 1).toString().equals("< Seleccione >")) {
                        ((JComboBox)(components[i])).setSelectedIndex(((JComboBox)(components[i])).getItemCount() - 1);
                    }
                    else {
                        ((JComboBox)(components[i])).setSelectedIndex(0);
                    }
                }
            }
            else if(components[i] instanceof JRadioButton) {
                ((JRadioButton)(components[i])).setForeground(Color.BLACK);
                ((JRadioButton)(components[i])).setBackground(Color.WHITE);
                ((JRadioButton)(components[i])).setFont(new Font("Tahoma",Font.BOLD,11));
                ((JRadioButton)(components[i])).setSelected(false);
            }
            else if(components[i] instanceof JCheckBox) {
                ((JCheckBox)(components[i])).setForeground(Color.BLACK);
                ((JCheckBox)(components[i])).setBackground(Color.WHITE);
                ((JCheckBox)(components[i])).setFont(new Font("Tahoma",Font.BOLD,11));
                ((JCheckBox)(components[i])).setBorderPaintedFlat(true);
                ((JCheckBox)(components[i])).setSelected(false);
            }
            else if(components[i] instanceof JSpinner) {
                ((JSpinner)(components[i])).setForeground(Color.BLACK);
                ((JSpinner)(components[i])).setBackground(new Color(255,255,225));
                ((JSpinner)(components[i])).setFont(new Font("Tahoma",Font.BOLD,11));
                ((JSpinner)(components[i])).setValue(0);
            }
            else if(components[i] instanceof Container) {
                InicializarContenedor((Container)(components[i]));
            }
        }
    }

    public static void HabilitarContenedor(Container container, Boolean activado) {
        Component[] components = container.getComponents();

        for(int i = 0; i < components.length; i++) {
            if(components[i] instanceof JTextField) {
                ((JTextField)components[i]).setEnabled(activado);
           }
            else if(components[i] instanceof JTextArea) {
                ((JTextArea)components[i]).setEnabled(activado);
            }
            else if(components[i] instanceof JButton) {
                ((JButton)components[i]).setEnabled(activado);
            }
            else if(components[i] instanceof JComboBox) {
                ((JComboBox)components[i]).setEnabled(activado);
            }
            else if(components[i] instanceof JRadioButton) {
                ((JRadioButton)components[i]).setEnabled(activado);
            }
            else if(components[i] instanceof JCheckBox) {
                ((JCheckBox)components[i]).setEnabled(activado);
            }
            else if(components[i] instanceof JSpinner) {
                ((JSpinner)components[i]).setEnabled(activado);
            }
            else if(components[i] instanceof Container) {
                HabilitarContenedor((Container)components[i],activado);
            }else if (components[i] instanceof JLabel){
                ((JLabel)components[i]).setEnabled(activado);
            }
        }
    }

    public static void AplicarEncabezado(JDialog formulario, JLabel encabezado, String iconoEncabezado, String titulo, String subtitulo) {
        String cadena;

        cadena = "<html>";
        cadena = cadena + "<head>";
        cadena = cadena + "</head>";
        cadena = cadena + "<body>";
        cadena = cadena + "<table border='0'>";
        cadena = cadena + "<tr>";
        cadena = cadena + "<td>";
        cadena = cadena + "<img src='" + formulario.getClass().getResource("/IconosFinales/" + iconoEncabezado + ".png") + "'>";
        cadena = cadena + "</td>";
        cadena = cadena + "<td>";
        cadena = cadena + "<font size='3' color=rgb(0,60,120))>" + titulo + "</font>";
        cadena = cadena + "<br></br>";
        cadena = cadena + "<font size='2' color=rgb(100,100,100))>" + subtitulo + "</font>";
        cadena = cadena + "</td>";
        cadena = cadena + "</tr>";
        cadena = cadena + "</table>";
        cadena = cadena + "</body>";
        cadena = cadena + "</html>";

        encabezado.setText(cadena);
        encabezado.setBackground(new Color(255,255,200));
        encabezado.setOpaque(true);
        encabezado.setHorizontalAlignment(JLabel.LEFT);
    }

    public static void AplicarSubencabezado(JDialog formulario, JLabel subencabezado, String iconoSubencabezado, String titulo) {
        String cadena;

        cadena = "<html>";
        cadena = cadena + "<head>";
        cadena = cadena + "</head>";
        cadena = cadena + "<body>";
        cadena = cadena + "<table border='0'>";
        cadena = cadena + "<tr>";
        cadena = cadena + "<td>";
        cadena = cadena + "<img src='" + formulario.getClass().getResource("/IconosFinales/" + iconoSubencabezado + ".png") + "'>";
        cadena = cadena + "</td>";
        cadena = cadena + "<td>";
        cadena = cadena + "<font size='3' color=rgb(0,120,190)>" + titulo + "</font>";
        cadena = cadena + "</td>";
        cadena = cadena + "</tr>";
        cadena = cadena + "</table>";
        cadena = cadena + "</body>";
        cadena = cadena + "</html>";

        subencabezado.setText(cadena);
        subencabezado.setBackground(new Color(255,255,220));
        subencabezado.setOpaque(true);
        subencabezado.setHorizontalAlignment(JLabel.LEFT);
    }
    
    public static void AplicarSubencabezado(JDialog formulario, JLabel subencabezado, String titulo) {
        String cadena;

        cadena = "<html>";
        cadena = cadena + "<head>";
        cadena = cadena + "</head>";
        cadena = cadena + "<body>";
        cadena = cadena + "<table border='0'>";
        cadena = cadena + "<tr>";
        cadena = cadena + "<td>";
//        cadena = cadena + "<img src='" + formulario.getClass().getResource("/IconosFinales/" + iconoSubencabezado + ".png") + "'>";
        cadena = cadena + "</td>";
        cadena = cadena + "<td>";
        cadena = cadena + "<font size='3' color=rgb(0,120,190)>" + titulo + "</font>";
        cadena = cadena + "</td>";
        cadena = cadena + "</tr>";
        cadena = cadena + "</table>";
        cadena = cadena + "</body>";
        cadena = cadena + "</html>";

        subencabezado.setText(cadena);
        subencabezado.setBackground(new Color(255,255,220));
        subencabezado.setOpaque(true);
        subencabezado.setHorizontalAlignment(JLabel.LEFT);
    }

    public static void AplicarEstilos(TableColumn columna, Integer ancho, Integer alineacion, String formato ,Object[] estilo_encabezado, Object[] estilo_detalle) {
        columna.setMinWidth(ancho);
        columna.setMaxWidth(ancho);
        columna.setWidth(ancho);

        columna.setHeaderRenderer(new Renderer((Font)(estilo_encabezado[0]),(Color)(estilo_encabezado[1]),(Color)(estilo_encabezado[2]),(Font)(estilo_encabezado[0]),(Color)(estilo_encabezado[1]),(Color)(estilo_encabezado[2]),alineacion,"Cadena"));
        columna.setCellRenderer(new Renderer((Font)(estilo_detalle[0]),(Color)(estilo_detalle[1]),(Color)(estilo_detalle[2]),(Font)(estilo_detalle[3]),(Color)(estilo_detalle[4]),(Color)(estilo_detalle[5]),alineacion,formato));
    }

    public static void AplicarEstilos(JTable tabla, Integer[] anchos, Integer[] alineaciones, String[] formatos, String[] modos) {
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(25);

        for(int i = 0; i < tabla.getModel().getColumnCount(); i++) {
            if(modos[i].equals("Normal")) {
                AplicarEstilos(tabla.getColumnModel().getColumn(i),anchos[i],alineaciones[i],formatos[i],estiloEncabezadoNormal,estiloDetalleNormal);
            }
            else if(modos[i].equals("Edicion")) {
                AplicarEstilos(tabla.getColumnModel().getColumn(i),anchos[i],alineaciones[i],formatos[i],estiloEncabezadoEdicion,estiloDetalleEdicion);
            }
            else if(modos[i].equals("Resaltado")) {
                AplicarEstilos(tabla.getColumnModel().getColumn(i),anchos[i],alineaciones[i],formatos[i],estiloEncabezadoResaltado,estiloDetalleResaltado);
            }
        }
    }

    public static Boolean EsExpresionGeneralPermitida(String expresion) {
        String[] palabrasNoPermitidas = {"SELECT","INSERT","UPDATE","DELETE","CREATE","ALTER","DROP"};
        Boolean expresionGeneralPermitida = true;

        for (int i = 0; i < palabrasNoPermitidas.length; i++) {
            if((" " + expresion + " ").contains(" " + palabrasNoPermitidas[i] + " ") || expresion.contains("'")) {
                expresionGeneralPermitida = false;

                break;
            }
        }

        return expresionGeneralPermitida;
    }
}