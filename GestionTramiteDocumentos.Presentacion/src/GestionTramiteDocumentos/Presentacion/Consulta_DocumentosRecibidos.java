package GestionTramiteDocumentos.Presentacion;

import GestionTramiteDocumentos.Entidades.*;
import GestionTramiteDocumentos.LogicaNegocio.*;
import Util.Util;
import Util.mdlGeneral;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


public class Consulta_DocumentosRecibidos extends javax.swing.JDialog {
    DocumentoEmitidoLN documentoEmitidoLN = new DocumentoEmitidoLN();
    RutaLN oRutaLN = new RutaLN();
    
    List<Ruta> listaRutas = new ArrayList<Ruta>();
    
    UnidadTramite oUnidadTramite;
    boolean estado =false;
    
    public Consulta_DocumentosRecibidos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setUnidadTramite();
        
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        Util.InicializarContenedor(this);
        this.setLocationRelativeTo(null);
        this.setTitle("[CONSULTAS] - DOCUMENTOS RECIBIDOS");
        Util.AplicarEncabezado(this,lblEncabezado,"UNIDAD_TRAMITE.PNG-64",oUnidadTramite.toString() ,"Permite Consultar los Documentos Recibidos por esta Unidad de Trámite");
        Util.HabilitarContenedor(pnlBusqueda,true);
        Util.HabilitarContenedor(pnlDocumentos, true);
        Util.HabilitarContenedor(this, true);
        
        mdlTabla();
        mostrarFechas();
        Concluido.setIcon(new ImageIcon(getClass().getResource("/IconosFinales/Confirmar1-20.png")));
        Concluido.setHorizontalTextPosition(SwingConstants.LEADING);
        Proceso.setIcon(new ImageIcon(getClass().getResource("/IconosFinales/Proceso-16.png")));
        Proceso.setHorizontalTextPosition(SwingConstants.LEADING);
    }

    private void setUnidadTramite(){
            oUnidadTramite = SistemaTramiteDocumentario.oUnidadTramite;
    }
    
    private void mostrarFechas(){
        try {
            Date di = documentoEmitidoLN.ConsultarFechaBD();
            Date fechaI = new Date(di.getYear(), di.getMonth(), di.getDate());
            cldFechaInicio.setDate(fechaI);
            cldFechaFin.setDate(documentoEmitidoLN.ConsultarFechaBD());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void mdlTabla(){
        String Columnas[] = {"||","Fecha de Recepcion","De","Tipo de Trámite","N° de Expediente"};
        
        mdlGeneral mdl = new mdlGeneral(Columnas);
        tblDocRecibidos.setModel(mdl);
        
        Integer[] anchos = {40,205,250,250,220};
        Integer[] alineaciones = {JLabel.CENTER,JLabel.CENTER,JLabel.LEFT,JLabel.LEFT,JLabel.CENTER};
        String[] formatos = {"Imagen","Cadena","Cadena","Cadena","Cadena"};
        String[] modos = {"Normal","Normal","Normal","Normal","Normal"};

        Util.AplicarEstilos(tblDocRecibidos,anchos,alineaciones,formatos,modos);
        
    }
    
    
    public String verFiltros(){
        Date Inicial = cldFechaInicio.getDate();
        Date Final = cldFechaFin.getDate();
        
        String sql = "SELECT * FROM ruta NATURAL JOIN documento_recibido\n";
               sql += "WHERE id_unidad_tramite = " + oUnidadTramite.getId_unidad_tramite() + "\n";
               sql += "AND fecha_hora_recepcion BETWEEN '" + Inicial + "' AND '" + Final + "'\n";
    
        switch (cbxTipoExpediente.getSelectedIndex()) {
            case 1: sql += "AND id_documento_recibido ";
                    sql += "IN ( ";
                    sql += "SELECT id_documento_recibido ";
                    sql += "FROM ruta ";
                    sql += "GROUP BY id_documento_recibido ";
                    sql += "HAVING count(id_documento_recibido) = 1 ) \n";
                break;
            case 2: sql += "AND id_documento_recibido ";
                    sql += "IN ( ";
                    sql += "SELECT id_documento_recibido ";
                    sql += "FROM ruta ";
                    sql += "GROUP BY id_documento_recibido ";
                    sql += "HAVING count(id_documento_recibido) > 1 ) \n";
                break;
            default: 
                break;
        }
        
        switch (cbxEstado.getSelectedIndex()) {
            case 1: sql += "AND estado = false \n" ;
                break;
            case 2: sql += "AND estado = true \n";
                break;
            default :
                break;
        }
        
        sql += "ORDER BY fecha_hora_recepcion,numero_expediente;";

        return sql;
    }
    
    public void mostrarTabla(){
        try {
            listaRutas = oRutaLN.ConsultarXFiltros(verFiltros());
            ((mdlGeneral)(tblDocRecibidos.getModel())).setData(parseVectorDocumentos(listaRutas));
            lblTotalDoc.setText("Total de Documentos  " + listaRutas.size());
        } catch (Exception ex) {
            Logger.getLogger(Consulta_DocumentosRecibidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private List parseVectorDocumentos(List<Ruta> lista) {
        List datos = new ArrayList();
        Object[] newdata;

        for(int i = 0; i < lista.size(); i++) {
            newdata = new Object[5];

            newdata[0] = lista.get(i).getDocumentoRecibido().getEstado()?"Confirmar1-20":"Proceso-16";
            newdata[1] = String.valueOf(lista.get(i).getFechaHora_recepcion());
            newdata[2] = lista.get(i).getDocumentoRecibido().getDe();
            newdata[3] = lista.get(i).getTipo_expediente();
            newdata[4] = lista.get(i).getDocumentoRecibido().getNumero_expediente();

            datos.add(newdata);
        }

        return datos;
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEncabezado = new javax.swing.JLabel();
        pnlBusqueda = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cldFechaInicio = new com.toedter.calendar.JDateChooser();
        cldFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        cbxTipoExpediente = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox();
        btnBuscar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        pnlDocumentos = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocRecibidos = new javax.swing.JTable();
        Concluido = new javax.swing.JLabel();
        Proceso = new javax.swing.JLabel();
        lblTotalDoc = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblEncabezado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEncabezado.setOpaque(true);

        pnlBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("DEL");

        cldFechaInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cldFechaInicioPropertyChange(evt);
            }
        });

        cldFechaFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cldFechaFinPropertyChange(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Tipo");

        cbxTipoExpediente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<TODOS>", "EXPEDIENTE NUEVO", "EXPEDIENTE EN CURSO" }));
        cbxTipoExpediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoExpedienteActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Estado");

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Todos>", "TRAMITE EN PROCESO", "TRAMITE CONCLUIDO" }));
        cbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("AL");

        javax.swing.GroupLayout pnlBusquedaLayout = new javax.swing.GroupLayout(pnlBusqueda);
        pnlBusqueda.setLayout(pnlBusquedaLayout);
        pnlBusquedaLayout.setHorizontalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cldFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addGap(6, 6, 6)
                .addComponent(cldFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxTipoExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBusquedaLayout.setVerticalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBusquedaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxEstado)
                            .addComponent(cbxTipoExpediente)))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGroup(pnlBusquedaLayout.createSequentialGroup()
                        .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cldFechaFin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cldFechaInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlDocumentos.setBorder(javax.swing.BorderFactory.createTitledBorder("Documento recibido"));

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Ver detalle");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Nuevo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tblDocRecibidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDocRecibidos);

        javax.swing.GroupLayout pnlDocumentosLayout = new javax.swing.GroupLayout(pnlDocumentos);
        pnlDocumentos.setLayout(pnlDocumentosLayout);
        pnlDocumentosLayout.setHorizontalGroup(
            pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDocumentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                    .addGroup(pnlDocumentosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlDocumentosLayout.setVerticalGroup(
            pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDocumentosLayout.createSequentialGroup()
                .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Concluido.setText("Tramite concluido");
        Concluido.setToolTipText("");
        Concluido.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        Proceso.setText("Tramite en progreso");

        lblTotalDoc.setText("Total de documentos ---");

        jButton5.setText("Cerrar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDocumentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Concluido)
                        .addGap(93, 93, 93)
                        .addComponent(Proceso)
                        .addGap(75, 75, 75)
                        .addComponent(lblTotalDoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(lblEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Concluido)
                    .addComponent(Proceso)
                    .addComponent(jButton5)
                    .addComponent(lblTotalDoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoActionPerformed
        if(cbxEstado.getSelectedItem().toString().equals("TRAMITE EN PROCESO")){
            estado = false;
        }else if(cbxEstado.getSelectedItem().toString().equals("TRAMITE CONCLUIDO")) {
            estado = true;
        }
    }//GEN-LAST:event_cbxEstadoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (JOptionPane.showConfirmDialog(this, "¿Desea Salir?","Mensaje del Sistema",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cldFechaInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cldFechaInicioPropertyChange
        btnBuscar.requestFocus();
    }//GEN-LAST:event_cldFechaInicioPropertyChange

    private void cldFechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cldFechaFinPropertyChange
        btnBuscar.requestFocus();
    }//GEN-LAST:event_cldFechaFinPropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int fila = tblDocRecibidos.getSelectedRow();
        if(tblDocRecibidos.getSelectedRow()!=-1){
            ExpedienteCursoDLG expedienteCursoDLG = new ExpedienteCursoDLG(null, true);
            expedienteCursoDLG.modoVista(listaRutas.get(fila).getDocumentoRecibido());
            expedienteCursoDLG.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un registro");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ExpedienteNuevoDLG expedienteNuevoDLG = new ExpedienteNuevoDLG(null, rootPaneCheckingEnabled);
        expedienteNuevoDLG.ModoNuevo();
        expedienteNuevoDLG.setVisible(true);        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int fila = tblDocRecibidos.getSelectedRow();
        if(tblDocRecibidos.getSelectedRow()!=-1){
           ExpedienteNuevoDLG expedienteNuevoDLG = new ExpedienteNuevoDLG(null, true);
           expedienteNuevoDLG.ModoEditor(listaRutas.get(fila));
           expedienteNuevoDLG.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un registro");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        mostrarTabla();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cbxTipoExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoExpedienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoExpedienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Concluido;
    private javax.swing.JLabel Proceso;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox cbxEstado;
    private javax.swing.JComboBox cbxTipoExpediente;
    private com.toedter.calendar.JDateChooser cldFechaFin;
    private com.toedter.calendar.JDateChooser cldFechaInicio;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblTotalDoc;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlDocumentos;
    private javax.swing.JTable tblDocRecibidos;
    // End of variables declaration//GEN-END:variables
}
