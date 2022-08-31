package GestionTramiteDocumentos.Presentacion;

import GestionTramiteDocumentos.Entidades.DocumentoEmitido;
import GestionTramiteDocumentos.Entidades.DocumentoRecibido;
import GestionTramiteDocumentos.Entidades.Ruta;
import GestionTramiteDocumentos.Entidades.Tramite;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import GestionTramiteDocumentos.LogicaNegocio.DocumentoEmitidoLN;
import GestionTramiteDocumentos.LogicaNegocio.RutaLN;
import Util.Util;
import Util.mdlGeneral;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Consulta_DocumentosEmitidos extends javax.swing.JDialog {
    DocumentoEmitidoLN documentoEmitidoLN = new DocumentoEmitidoLN();
    RutaLN oRutaLN = new RutaLN();
    
    List<DocumentoEmitido> listaDocEmitidos = new ArrayList<DocumentoEmitido>();
    
    UnidadTramite oUnidadTramite;
    boolean estado =false;
    
    public Consulta_DocumentosEmitidos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setUnidadTramite();
        
        
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[CONSULTAS] - DOCUMENTOS EMITIDOS");
        Util.AplicarEncabezado(this,lblEncabezado,"UNIDAD_TRAMITE.PNG-64",oUnidadTramite.toString() ,"Permite Consultar los Documentos Emitidos por esta Unidad de Trámite");
        Util.HabilitarContenedor(pnlBusqueda,true);
        Util.HabilitarContenedor(pnlDocumentos, true);
        Util.HabilitarContenedor(pnlContador, true);
        
        mdlTabla();
        mostrarFechas();
    }

    private void setUnidadTramite(){
            oUnidadTramite = SistemaTramiteDocumentario.oUnidadTramite;
    }
    
    private void mostrarFechas(){
        try {
            cldFechaInicio.setDate(documentoEmitidoLN.ConsultarFechaBD());
            cldFechaFin.setDate(documentoEmitidoLN.ConsultarFechaBD());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void mdlTabla(){
        String Columnas[] = {"Fecha de Emisión","Tipo de Documento","N° Documento","Tipo","N° de Expediente","Estado"};
        
        mdlGeneral mdl = new mdlGeneral(Columnas);
        tblDocEmitidos.setModel(mdl);
        
        Integer[] anchos = {170,170,170,170,170,170};
        Integer[] alineaciones = {JLabel.CENTER,JLabel.LEFT,JLabel.LEFT,JLabel.LEFT,JLabel.LEFT,JLabel.LEFT};
        String[] formatos = {"Cadena","Cadena","Cadena","Cadena","Cadena","Cadena"};
        String[] modos = {"Normal","Normal","Normal","Normal","Normal","Normal"};

        Util.AplicarEstilos(tblDocEmitidos,anchos,alineaciones,formatos,modos);
        
    }
    
    private String verFiltros(){
        Date Inicial = cldFechaInicio.getDate();
        Date Final = cldFechaFin.getDate();
        
        String sql = "SELECT * FROM documento_emitido\n";
               sql += "WHERE id_unidad_tramite = " + oUnidadTramite.getId_unidad_tramite() + "\n";
               sql += "AND fecha_emision BETWEEN '" + Inicial + "' AND '" + Final + "'\n";
    
        switch (cbxTipo.getSelectedIndex()) {
            case 1: sql += "AND inicio_tramite = false ";
                break;
            case 2: sql += "AND inicio_tramite = true ";
                break;
            default:
                break;
        }
        
        sql += "\nORDER BY fecha_emision, id_tipo_documento, numero;";
        
        return sql;
    }
    
    private void mostrarTabla(){
        try {
            listaDocEmitidos = documentoEmitidoLN.ConsultarXFiltros(verFiltros());
            
            ((mdlGeneral)(tblDocEmitidos.getModel())).setData(parseVectorDocumentos(listaDocEmitidos));
            lblTotalDoc.setText("Total de Documentos  " + listaDocEmitidos.size());
        } catch (Exception ex) {
            Logger.getLogger(Consulta_DocumentosRecibidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private List parseVectorDocumentos(List<DocumentoEmitido> lista) {
        List datos = new ArrayList();
        Object[] newdata;
        for(int i = 0; i < lista.size(); i++) {
            newdata = new Object[6];

            newdata[0] = lista.get(i).getFecha_emision();
            newdata[1] = lista.get(i).getTipoDocumento();
            newdata[2] = lista.get(i).getNumero();
            newdata[3] = lista.get(i).isInicio_tramite()?"Inicio de Trámite":"Trámite de Expediente";
            newdata[4] = lista.get(i).getNumero_expediente();
            newdata[5] = lista.get(i).isInicio_tramite()?"Desconocido":"En Proceso";

            datos.add(newdata);
        }
        return datos;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBusqueda = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cldFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        cldFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        cbxTipo = new javax.swing.JComboBox();
        btnBuscar = new javax.swing.JButton();
        pnlDocumentos = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocEmitidos = new javax.swing.JTable();
        lblEncabezado = new javax.swing.JLabel();
        pnlContador = new javax.swing.JPanel();
        lblTotalDoc = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("DEL");

        cldFechaInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cldFechaInicioPropertyChange(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("AL");

        cldFechaFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cldFechaFinPropertyChange(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Tipo");

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<TODOS>", "TRAMITE DE EXPEDIENTE", "INICIO DE TRAMITE" }));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBusquedaLayout = new javax.swing.GroupLayout(pnlBusqueda);
        pnlBusqueda.setLayout(pnlBusquedaLayout);
        pnlBusquedaLayout.setHorizontalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cldFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cldFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBusquedaLayout.setVerticalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBusquedaLayout.createSequentialGroup()
                        .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cldFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cldFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlBusquedaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cbxTipo))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlDocumentos.setBorder(javax.swing.BorderFactory.createTitledBorder("Documentos Emitidos"));

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

        tblDocEmitidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDocEmitidos);

        javax.swing.GroupLayout pnlDocumentosLayout = new javax.swing.GroupLayout(pnlDocumentos);
        pnlDocumentos.setLayout(pnlDocumentosLayout);
        pnlDocumentosLayout.setHorizontalGroup(
            pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDocumentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlDocumentosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        lblTotalDoc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTotalDoc.setText("Total de documentos ---");

        jButton5.setText("Cerrar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContadorLayout = new javax.swing.GroupLayout(pnlContador);
        pnlContador.setLayout(pnlContadorLayout);
        pnlContadorLayout.setHorizontalGroup(
            pnlContadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalDoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );
        pnlContadorLayout.setVerticalGroup(
            pnlContadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContadorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlContadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalDoc)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlContador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDocumentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (JOptionPane.showConfirmDialog(this, "¿Desea Salir?","Mensaje del Sistema",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        mostrarTabla();
    }//GEN-LAST:event_btnBuscarActionPerformed
    
    public boolean sePuedeModificar(DocumentoEmitido documentoEmitido){
        try {
            RutaLN rutaLN = new RutaLN();
            DocumentoRecibido documentoRecibido = documentoEmitido.getTramite().getRuta().getDocumentoRecibido();
//            Tramite tramite = documentoEmitido.getTramite();
//            System.out.println("Numero de tramite: "+tramite.getId_tramite());
            Ruta rutaAvance = rutaLN.ConsultarUltima(new Ruta(WIDTH, null, null, null, documentoRecibido, null));
            if(rutaAvance.getUnidadTramite().getId_unidad_tramite() == SistemaTramiteDocumentario.oUnidadTramite.getId_unidad_tramite()){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(Consulta_DocumentosRecibidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private void cldFechaInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cldFechaInicioPropertyChange
        btnBuscar.requestFocus();
    }//GEN-LAST:event_cldFechaInicioPropertyChange

    private void cldFechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cldFechaFinPropertyChange
        btnBuscar.requestFocus();
    }//GEN-LAST:event_cldFechaFinPropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int fila = tblDocEmitidos.getSelectedRow();
        if(fila!= -1){
            InicioTramite inicioTramite = new InicioTramite(null, true);
            inicioTramite.modoVista(listaDocEmitidos.get(fila));
            inicioTramite.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un registro");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int fila = tblDocEmitidos.getSelectedRow();
        if(fila!= -1){
            if(sePuedeModificar(listaDocEmitidos.get(fila))){
                InicioTramite inicioTramite = new InicioTramite(null, true);
                inicioTramite.modoEdicion(listaDocEmitidos.get(fila));
                inicioTramite.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Actualmente el expediente se encuentra en otra unidad");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un registro");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        TramiteExpedienteDLG tramiteExpedienteDLG = new TramiteExpedienteDLG(null, true);
        tramiteExpedienteDLG.inicialFrm();
        tramiteExpedienteDLG.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox cbxTipo;
    private com.toedter.calendar.JDateChooser cldFechaFin;
    private com.toedter.calendar.JDateChooser cldFechaInicio;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblTotalDoc;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlContador;
    private javax.swing.JPanel pnlDocumentos;
    private javax.swing.JTable tblDocEmitidos;
    // End of variables declaration//GEN-END:variables
}
