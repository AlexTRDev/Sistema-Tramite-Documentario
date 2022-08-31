/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionTramiteDocumentos.Presentacion;

import Reporte.Parametro;
import Reporte.Reporte;
import Reporte.frmReporte;
import Util.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ENVY
 */
public class GeneradorReporte extends javax.swing.JDialog {

    private String documento;//recibido, enviado
    public GeneradorReporte(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[Reportes]");
        Util.AplicarSubencabezado(this,SubEncabezado,"CONFIG-32","\tGenerar Reporte");
        Util.HabilitarContenedor(this, true);
    }

    public void modoDocumento(String documento){
        this.documento = documento;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cldFechaInicio = new com.toedter.calendar.JDateChooser();
        cldFechaFin = new com.toedter.calendar.JDateChooser();
        SubEncabezado = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("DE");

        jLabel3.setText("AL");

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cldFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cldFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
            .addComponent(SubEncabezado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SubEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cldFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cldFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            
            if(documento.equals("recibido")){

                List<Parametro> parametros = new ArrayList<Parametro>();

                parametros.add(new Parametro(1,"unidadTramite",SistemaTramiteDocumentario.oUnidadTramite.getId_unidad_tramite()));
                parametros.add(new Parametro(2,"nombreUnidad",SistemaTramiteDocumentario.oUnidadTramite.toString()));
                parametros.add(new Parametro(3,"fechaInicio",cldFechaInicio.getDate()));
                parametros.add(new Parametro(4,"fechaFin",cldFechaFin.getDate()));
                
                Reporte reporte = new Reporte(parametros);
                frmReporte fReporte = new frmReporte("Documentos Recibidos", reporte.VistaPreliminar("rptDocumentosRecibidos"));
                fReporte.setVisible(true); 
                this.dispose();
            }else if(documento.equals("emitidos")){
                List<Parametro> parametros = new ArrayList<Parametro>();
                
                parametros.add(new Parametro(1,"unidadTramite",SistemaTramiteDocumentario.oUnidadTramite.getId_unidad_tramite()));
                parametros.add(new Parametro(2,"nombreUnidad",SistemaTramiteDocumentario.oUnidadTramite.toString()));
                parametros.add(new Parametro(3,"fechaInicial",cldFechaFin.getDate()));
                parametros.add(new Parametro(4,"fechaFin",cldFechaFin.getDate()));
                
                Reporte reporte = new Reporte(parametros);
                frmReporte fReporte = new frmReporte("Documentos Emitidos", reporte.VistaPreliminar("rptDocumentosEmitidos"));
                fReporte.setVisible(true); 
                this.dispose();
            }

            
        }catch(Exception e){
            System.out.println("Error DetalleD: "+e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SubEncabezado;
    private com.toedter.calendar.JDateChooser cldFechaFin;
    private com.toedter.calendar.JDateChooser cldFechaInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
