/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionTramiteDocumentos.Presentacion;

/**
 *
 * @author ENVY
 */
public class test {
    public static void main(String[] args) {
        ExpedienteCursoDLG expedienteCursoDLG = new ExpedienteCursoDLG(null, true);
        expedienteCursoDLG.modoVistaManual();
        expedienteCursoDLG.setVisible(true);
    }
}
