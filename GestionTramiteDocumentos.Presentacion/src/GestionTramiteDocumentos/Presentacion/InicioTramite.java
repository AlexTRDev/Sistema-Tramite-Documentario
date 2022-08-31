package GestionTramiteDocumentos.Presentacion;

import GestionTramiteDocumentos.Entidades.DocumentoEmitido;
import GestionTramiteDocumentos.Entidades.TipoDocumento;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import GestionTramiteDocumentos.LogicaNegocio.DocumentoEmitidoLN;
import GestionTramiteDocumentos.LogicaNegocio.TipoDocumentoLN;
import GestionTramiteDocumentos.LogicaNegocio.UnidadTramiteLN;
import Util.FormatoFecha;
import Util.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class InicioTramite extends javax.swing.JDialog {
    UnidadTramiteLN unidadTramiteLN = new UnidadTramiteLN();
    UnidadTramite unidadTramite = new UnidadTramite();

    TipoDocumentoLN tipoDocumentoLN = new TipoDocumentoLN();
    DocumentoEmitidoLN documentoEmitidoLN = new DocumentoEmitidoLN();
    DocumentoEmitido documentoEmitido;
    
    DefaultComboBoxModel mdlCbxUnidadTramite, mdlCbxTipoDocumento;
    String modoPantalla ="";
    DocumentoEmitido documentoAEmitir;
    
    public InicioTramite(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setLocationRelativeTo(null);
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[MANTENIMIENTO] UNIDAD DE TRAMITE");
        Util.HabilitarContenedor(pnlDocumento,true);
    }
    
    public void mostarElementos(boolean estado){
        txtNumDocumento.setEditable(false);
        txtAnioDocumento.setEditable(false);
        aTextAsunto.setEditable(estado);
        txtNumExpediente.setEditable(false);
        txtHora.setEditable(false);
        btnHora.setEnabled(false);
        rbtnExterno.setEnabled(estado);
        rbtnInterno.setEnabled(estado);
        cbxPara.setEnabled(estado);
        txtPara.setEditable(estado);
        txtPara.setVisible(true);
        cldFechaEmision.setEnabled(estado);
        cldFechaRecepcion.setEnabled(false);
        btnCerrar.setVisible(true);
        
        lblParaInterno.setVisible(estado);
        lblParaExterno.setVisible(!estado);
        rbtnExterno.setVisible(estado);
        rbtnInterno.setVisible(estado);
        rbtnInterno.setSelected(estado);
        cbxPara.setVisible(estado);
    }
    
    public void modoNuevo(DocumentoEmitido documentoAEnviar){
        this.setTitle("[Operaciones - Emision de docuemtos] - Inicio de tramite");
        this.documentoAEmitir = documentoAEnviar;
        modoPantalla = "nuevo";
                
        unidadTramite = SistemaTramiteDocumentario.oUnidadTramite;
        Util.AplicarEncabezado(this,Encabezado,"UNIDAD_TRAMITE.PNG-64",unidadTramite.toString() ,"Permite Regisstrar los Documentos Emitidos por esta Unidad de Trámite");
        Util.AplicarSubencabezado(this,SubEncabezado,"Guardar-48","Guardar");
        lblAbrebiaturaDoc.setText(unidadTramite.getAbreviatura()+"-"+unidadTramite.getUnidadOrganizativa().getAbreviatura());

        txtNumDocumento.setText(documentoAEnviar.getNumero().substring(0,3));
        txtAnioDocumento.setText(documentoAEnviar.getNumero().substring(4,8));
        cldFechaEmision.setDate(documentoAEnviar.getFecha_emision());
        cldFechaRecepcion.setDate(documentoAEnviar.getFecha_recepcion());
        txtHora.setText(FormatoFecha.getFecha(documentoAEnviar.getFecha_recepcion(),"HH:mm:ss"));
        txtNumExpediente.setText(documentoAEnviar.getNumero_expediente());
        txtPara.setText(documentoAEnviar.getPara());
        aTextAsunto.setText(documentoAEnviar.getAsunto());
        mostrarCbxTipoDocumento();
        mostrarCbxUnidadTramite(false);
        mostarElementos(false);

        btnGuardar.setText("Iniciar");
        btnCerrar.setText("Atras");
        btnGuardar.setVisible(true);
    }
    
    public void modoVista(DocumentoEmitido documentoAEnviar){
        this.setTitle("[Consultas]  - Emision de docuemtos / Ver detalle");
        this.documentoAEmitir = documentoAEnviar;
        modoPantalla = "vista";
                
        unidadTramite = SistemaTramiteDocumentario.oUnidadTramite;
        Util.AplicarEncabezado(this,Encabezado,"UNIDAD_TRAMITE.PNG-64",unidadTramite.toString() ,"Permite Visualizar los Documentos Emitidos por esta Unidad de Trámite");
        Util.AplicarSubencabezado(this,SubEncabezado,"Guardar-48","Ver Detalle de Documento");
        lblAbrebiaturaDoc.setText(unidadTramite.getAbreviatura()+"-"+unidadTramite.getUnidadOrganizativa().getAbreviatura());

        txtNumDocumento.setText(documentoAEnviar.getNumero().substring(0,3));
        txtAnioDocumento.setText(documentoAEnviar.getNumero().substring(4,8));
        cldFechaEmision.setDate(documentoAEnviar.getFecha_emision());
        cldFechaRecepcion.setDate(documentoAEnviar.getFecha_recepcion());
        txtNumExpediente.setText(documentoAEnviar.getNumero_expediente());
        txtPara.setText(documentoAEnviar.getPara());
        txtHora.setText(FormatoFecha.getFecha(documentoAEnviar.getFecha_recepcion(),"HH:mm:ss"));
        aTextAsunto.setText(documentoAEnviar.getAsunto());
        mostrarCbxTipoDocumento();
        mostrarCbxUnidadTramite(false);
        
        mostarElementos(false);
        btnGuardar.setText("");
        btnCerrar.setText("Salir");
        btnGuardar.setVisible(false);
    }
    
    public void modoEdicion(DocumentoEmitido documentoAEmitir){
        this.setTitle("[Consultas] - Documentos emitidos / Modificar");
        this.documentoAEmitir = documentoAEmitir;
        modoPantalla = "edicion";
                
        unidadTramite = SistemaTramiteDocumentario.oUnidadTramite;
        Util.AplicarEncabezado(this,Encabezado,"UNIDAD_TRAMITE.PNG-64",unidadTramite.toString() ,"Permite Actualizar los Documentos Emitidos por esta Unidad de Trámite");
        Util.AplicarSubencabezado(this,SubEncabezado,"Actualizar-48","Modificar");
        lblAbrebiaturaDoc.setText(unidadTramite.getAbreviatura()+"-"+unidadTramite.getUnidadOrganizativa().getAbreviatura());

        txtNumDocumento.setText(documentoAEmitir.getNumero().substring(0,3));
        txtAnioDocumento.setText(documentoAEmitir.getNumero().substring(4,8));
        cldFechaEmision.setDate(documentoAEmitir.getFecha_emision());
        cldFechaRecepcion.setDate(documentoAEmitir.getFecha_recepcion());
        txtNumExpediente.setText(documentoAEmitir.getNumero_expediente());
        aTextAsunto.setText(documentoAEmitir.getAsunto());
        
        mostarElementos(true);
        btnGuardar.setVisible(true);

        mostrarCbxTipoDocumento();
        mostrarPara();
        
        btnGuardar.setText("Aceptar");
        btnCerrar.setText("Cancelar");
        btnGuardar.setVisible(true);
    }
    
    public final void mostrarPara(){
        if(esUnidadInterna()){
            rbtnInterno.setSelected(true);
            rbtnExterno.setSelected(false);
            cbxPara.setEnabled(true);
            txtPara.setEnabled(false);
            mostrarCbxUnidadTramite(true);
        }else{
            rbtnInterno.setSelected(false);
            rbtnExterno.setSelected(true);
            cbxPara.setEnabled(false);
            txtPara.setEnabled(true);
            mostrarCbxUnidadTramite(false);
        }
    }
    
    public final void mostrarCbxTipoDocumento(){
        try {
            if(modoPantalla.equals("edicion")){
                List<TipoDocumento> tipoDocumentos = tipoDocumentoLN.ConsultarAll();
                mdlCbxTipoDocumento = new DefaultComboBoxModel(tipoDocumentos.toArray());
                cbxTipoDocumento.setModel(mdlCbxTipoDocumento);
                
                for (int i = 0; i < tipoDocumentos.size(); i++) {
                    if(Objects.equals(documentoAEmitir.getTipoDocumento().getId_tipoDocumento(), tipoDocumentos.get(i).getId_tipoDocumento())){
                        cbxTipoDocumento.setSelectedIndex(i);
                    }
                }
                
            }else if(modoPantalla.equals("nuevo") || modoPantalla.equals("vista")){
                List<TipoDocumento> tipoDocumentos = new ArrayList<>();
                tipoDocumentos.add(documentoAEmitir.getTipoDocumento());
                mdlCbxTipoDocumento = new DefaultComboBoxModel(tipoDocumentos.toArray());
                cbxTipoDocumento.setModel(mdlCbxTipoDocumento);
                cbxTipoDocumento.setOpaque(false);
                cbxTipoDocumento.setEnabled(false);
            }      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public final void mostrarCbxUnidadTramite(boolean lleno){
        try {
            if(lleno){
                List<UnidadTramite> lista = unidadTramiteLN.ConsultarAll(unidadTramite.getUnidadOrganizativa()); 
                for (int i = 0; i < lista.size(); i++) {
                    if(lista.get(i).toString().equals(unidadTramite.toString())){
                        lista.remove(i);
                    }
                }
                mdlCbxUnidadTramite = new DefaultComboBoxModel(lista.toArray());
                cbxPara.setModel(mdlCbxUnidadTramite);

                for (int i = 0; i < lista.size(); i++) {
                    if(documentoAEmitir.getPara().equals(lista.get(i).getNombre())){
                        cbxPara.setSelectedIndex(i);
                        break;
                    }
                }
            }else{
                List<UnidadTramite> lista = new ArrayList<>();
                mdlCbxUnidadTramite = new DefaultComboBoxModel(lista.toArray());
                cbxPara.setModel(mdlCbxUnidadTramite);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoBotones = new javax.swing.ButtonGroup();
        Encabezado = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        pnlDocumento = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblParaInterno = new javax.swing.JLabel();
        txtNumDocumento = new javax.swing.JTextField();
        txtAnioDocumento = new javax.swing.JTextField();
        lblAbrebiaturaDoc = new javax.swing.JLabel();
        cldFechaEmision = new com.toedter.calendar.JDateChooser();
        txtNumExpediente = new javax.swing.JTextField();
        cldFechaRecepcion = new com.toedter.calendar.JDateChooser();
        txtHora = new javax.swing.JTextField();
        btnHora = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        aTextAsunto = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        rbtnInterno = new javax.swing.JRadioButton();
        rbtnExterno = new javax.swing.JRadioButton();
        cbxPara = new javax.swing.JComboBox();
        txtPara = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblParaExterno = new javax.swing.JLabel();
        cbxTipoDocumento = new javax.swing.JComboBox();
        SubEncabezado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[Operacion - emision de documentos] - Inicio de tramite");
        setResizable(false);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        pnlDocumento.setBorder(javax.swing.BorderFactory.createTitledBorder("Documento"));
        pnlDocumento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Tipo de documento");
        pnlDocumento.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, -1));

        jLabel4.setText("Numero de documento");
        pnlDocumento.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 130, -1));

        jLabel5.setText("Fecha de emision");
        pnlDocumento.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 130, -1));

        jLabel2.setText("Numero de expediente");
        pnlDocumento.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 130, -1));

        jLabel10.setText("Fecha de recepcion");
        pnlDocumento.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 130, -1));

        lblParaInterno.setText("Para");
        pnlDocumento.add(lblParaInterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 43, 20));
        pnlDocumento.add(txtNumDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 44, -1));
        pnlDocumento.add(txtAnioDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 44, -1));

        lblAbrebiaturaDoc.setText("---");
        pnlDocumento.add(lblAbrebiaturaDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 90, 20));
        pnlDocumento.add(cldFechaEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 125, -1));
        pnlDocumento.add(txtNumExpediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 250, -1));
        pnlDocumento.add(cldFechaRecepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 125, -1));
        pnlDocumento.add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 90, 20));

        btnHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoraActionPerformed(evt);
            }
        });
        pnlDocumento.add(btnHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 20, 20));

        aTextAsunto.setColumns(20);
        aTextAsunto.setRows(5);
        jScrollPane1.setViewportView(aTextAsunto);

        pnlDocumento.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 290, 130));

        jLabel9.setText("Asunto:");
        pnlDocumento.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 60, 20));

        GrupoBotones.add(rbtnInterno);
        rbtnInterno.setText("Interno");
        rbtnInterno.setOpaque(false);
        rbtnInterno.setPreferredSize(null);
        rbtnInterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnInternoActionPerformed(evt);
            }
        });
        pnlDocumento.add(rbtnInterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 73, 24));

        GrupoBotones.add(rbtnExterno);
        rbtnExterno.setText("Externo");
        rbtnExterno.setOpaque(false);
        rbtnExterno.setPreferredSize(null);
        rbtnExterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnExternoActionPerformed(evt);
            }
        });
        pnlDocumento.add(rbtnExterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 73, 24));

        cbxPara.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnlDocumento.add(cbxPara, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 560, -1));

        txtPara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtParaActionPerformed(evt);
            }
        });
        pnlDocumento.add(txtPara, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 560, -1));

        jLabel6.setText("-");
        pnlDocumento.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 7, 20));

        jLabel7.setText("-");
        pnlDocumento.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 10, 20));

        lblParaExterno.setText("Para");
        pnlDocumento.add(lblParaExterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 43, 20));

        cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnlDocumento.add(cbxTipoDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 240, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Encabezado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(SubEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(SubEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCerrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(modoPantalla.equals("nuevo")){
            try{
                TramiteExpedienteDLG.inicio_tramite = true;
                TramiteExpedienteDLG.btnInicioCancelado = false;
//                JOptionPane.showMessageDialog(null, "Registro exitoso!");
                this.dispose();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } 
        }else if(modoPantalla.equals("edicion")){
            if(datosOK()){
                documentoAEmitir.setTipoDocumento(((TipoDocumento)cbxTipoDocumento.getSelectedItem()));
                documentoAEmitir.setNumero(txtNumDocumento.getText()+"-"+txtAnioDocumento.getText()+"-"+lblAbrebiaturaDoc.getText());
                documentoAEmitir.setFecha_emision(cldFechaEmision.getDate());
                documentoAEmitir.setAsunto(aTextAsunto.getText());
                if(rbtnInterno.isSelected()){
                    documentoAEmitir.setPara(((UnidadTramite)cbxPara.getSelectedItem()).getNombre());
                }else if(rbtnExterno.isSelected()){
                    documentoAEmitir.setPara(txtPara.getText());
                }
                try {
                    documentoEmitidoLN.Modificar(documentoEmitido);
                } catch (Exception e) {
                    
                }
            }
            
        }
       
    }//GEN-LAST:event_btnGuardarActionPerformed
 
    public boolean datosOK(){
        if(cldFechaEmision.getDate()==null){
            JOptionPane.showMessageDialog(null, "La fecha de emision es incorrecta");
            return false;
        }else if(cldFechaRecepcion.getDate()==null){
            JOptionPane.showMessageDialog(null, "La fecha de recepcion es incorrecta");
            return false;
        }else if(aTextAsunto.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Falta especificar un asunto para el documento");
            return false;
        }else if(rbtnExterno.isSelected()){
            if(!txtPara.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Especifique un destino para el envio");
                return false;
            }
        }
        return true;
    }
    
    private void btnHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoraActionPerformed
        if(this.documentoEmitido!=null){
            Reloj reloj  =new Reloj(null, true,txtHora);
            reloj.setVisible(true);
        }
    }//GEN-LAST:event_btnHoraActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        if(modoPantalla.equals("nuevo")){
            TramiteExpedienteDLG.inicio_tramite = false;
            TramiteExpedienteDLG.btnInicioCancelado = true;
            this.dispose();
        }else if(modoPantalla.equals("vista") || modoPantalla.equals("edicion")){
            this.dispose();
        }

        
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtParaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtParaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtParaActionPerformed

    private void rbtnInternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnInternoActionPerformed
        if(rbtnInterno.isSelected()){
            cbxPara.setEnabled(true);
            txtPara.setEnabled(false);
            txtPara.setText(null);
            mostrarCbxUnidadTramite(true);
        }
    }//GEN-LAST:event_rbtnInternoActionPerformed

    private void rbtnExternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnExternoActionPerformed
        if(rbtnExterno.isSelected()){
            cbxPara.setEnabled(false);
            txtPara.setEnabled(true);
            mostrarCbxUnidadTramite(false);
        }
    }//GEN-LAST:event_rbtnExternoActionPerformed
    
    public boolean esUnidadInterna(){
        boolean esinterna = false;             
        try {
                List<UnidadTramite> lista = unidadTramiteLN.ConsultarAll(unidadTramite.getUnidadOrganizativa()); 
                for (int i = 0; i < lista.size(); i++) {
                    if(documentoAEmitir.getPara().equals(lista.get(i).getNombre())){
                        esinterna = true;
                        break;
                    }
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return esinterna;
    }
    

    public void mostrarFechaHoraRecepcion(){
        try {
            
            Date date = this.documentoEmitidoLN.ConsultarFechaBD();
            cldFechaRecepcion.setDate(date);
            
            String hora = Integer.toString(date.getHours());
            String minutos = Integer.toString(date.getMinutes());
            String segundos = Integer.toString(date.getSeconds());
            
            if(hora.length()==1){hora="0"+hora;}
            if(minutos.length()==1){minutos="0"+minutos;}
            if(segundos.length()==1){segundos="0"+segundos;}

            txtHora.setText(hora+":"+minutos+":"+segundos);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Encabezado;
    private javax.swing.ButtonGroup GrupoBotones;
    private javax.swing.JLabel SubEncabezado;
    private javax.swing.JTextArea aTextAsunto;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHora;
    private javax.swing.JComboBox cbxPara;
    private javax.swing.JComboBox cbxTipoDocumento;
    private com.toedter.calendar.JDateChooser cldFechaEmision;
    private com.toedter.calendar.JDateChooser cldFechaRecepcion;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAbrebiaturaDoc;
    private javax.swing.JLabel lblParaExterno;
    private javax.swing.JLabel lblParaInterno;
    private javax.swing.JPanel pnlDocumento;
    private javax.swing.JRadioButton rbtnExterno;
    private javax.swing.JRadioButton rbtnInterno;
    private javax.swing.JTextField txtAnioDocumento;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtNumDocumento;
    private javax.swing.JTextField txtNumExpediente;
    private javax.swing.JTextField txtPara;
    // End of variables declaration//GEN-END:variables
}
