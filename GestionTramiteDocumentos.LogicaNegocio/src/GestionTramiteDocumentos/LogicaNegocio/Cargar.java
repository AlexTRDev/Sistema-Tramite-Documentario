
package GestionTramiteDocumentos.LogicaNegocio;

import javax.swing.JProgressBar;

public class Cargar extends Thread {
    JProgressBar jProgressBar;
    

    public Cargar(JProgressBar jProgressBar){
        super();
        this.jProgressBar = jProgressBar;
    }
    
    @Override
    public void run(){
        for (int i = 1; i <= 100; i++) {
            jProgressBar.setValue(i);
            pausa(30);
        }
    }
    
    public void pausa(int mlSeg){
        try {
            Thread.sleep(mlSeg);
        } catch (Exception e) {
        }
    }
}
