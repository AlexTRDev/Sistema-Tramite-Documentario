package GestionTramiteDocumentos.Presentacion;

import GestionTramiteDocumentos.Entidades.DocRec;
import GestionTramiteDocumentos.Entidades.DocumentoRecibido;
import GestionTramiteDocumentos.Entidades.Requisito;
import GestionTramiteDocumentos.Entidades.Ruta;
import GestionTramiteDocumentos.Entidades.TipoDocumento;
import GestionTramiteDocumentos.Entidades.TipoTramite;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import GestionTramiteDocumentos.LogicaNegocio.DocRecLN;
import GestionTramiteDocumentos.LogicaNegocio.DocumentoRecibidoLN;
import GestionTramiteDocumentos.LogicaNegocio.RequisitoLN;
import GestionTramiteDocumentos.LogicaNegocio.RutaLN;
import GestionTramiteDocumentos.LogicaNegocio.TipoDocumentoLN;
import GestionTramiteDocumentos.LogicaNegocio.TipoTramiteLN;
import GestionTramiteDocumentos.LogicaNegocio.UnidadTramiteLN;
import Metodos.Calendario;
import ModelosTablas.mdlRequisitoDocNew;
import Util.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ExpedienteNuevoDLG extends javax.swing.JDialog {

    DefaultComboBoxModel mdlCbxTipoDocumento, mdlCbxTipoTramite;
    RequisitoLN requisitoLN = new RequisitoLN();
    TipoDocumentoLN tipoDocumentoLN = new TipoDocumentoLN();
    TipoTramiteLN tipoTramiteLN = new TipoTramiteLN();
    
    DocumentoRecibidoLN documentoRecibidoLN = new DocumentoRecibidoLN();
    RutaLN rutaLN = new RutaLN();
    DocRecLN docRecLN = new DocRecLN();
    
    UnidadTramiteLN unidadTramiteLN = new UnidadTramiteLN();
    UnidadTramite unidadTramite = new UnidadTramite();
    
    List<Requisito> requisitos = new ArrayList<>();
    
    String modoGuardado;
    
//    private String modo;//nuevo,edicion
    private Ruta rutaEdit;
    
    public ExpedienteNuevoDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        unidadTramite = SistemaTramiteDocumentario.oUnidadTramite;
        
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[OPERACIONES - RECEPCION DE DOCUMENTOS] - Expediente Nuevo");
        Util.AplicarEncabezado(this,Encabezado,"UNIDAD_TRAMITE.PNG-64",unidadTramite.toString() ,"Permite Realizar un Nuevo Expediente para realizar un Trámite");
        Util.HabilitarContenedor(pnlDocumento,true);
        Util.HabilitarContenedor(pnlTipoTramite, true);
        Util.HabilitarContenedor(pnlTabla, true);
        Util.HabilitarContenedor(pnlObservacion, true);
        Util.HabilitarContenedor(this, true);
        
        lblSiglas.setText(SistemaTramiteDocumentario.oUnidadTramite.getAbreviatura()+"-"+SistemaTramiteDocumentario.oUnidadTramite.getUnidadOrganizativa().getAbreviatura());
    }
    
    public void ModoEditor(Ruta rutaEdit){
        this.setTitle("[Consultas] - Documentos recibidos / Modificar");
        
        txtExpedienteNumero.setEditable(false);
        txtExpedienteAño.setEditable(false);
        cbxTipoDocumentos.setEnabled(false);
        txtNumDocumento.setEnabled(false);
        chkSinNumero.setEnabled(false);
        btnGuardar.setText("Modificar");
        btnCancelar.setText("Cancelar");
        btnGuardar.setVisible(true);
        btnCancelar.setVisible(true);
        
        this.modoGuardado="editor";
 
        
        this.rutaEdit = rutaEdit;
        
        mostrarCbxTipoDocumento(this.rutaEdit.getDocumentoRecibido().getTipoDocumento());
        this.txtExpedienteNumero.setText(this.rutaEdit.getDocumentoRecibido().getNumero_expediente().substring(0,3));
        this.txtExpedienteAño.setText(Integer.toString(this.rutaEdit.getDocumentoRecibido().getAño_expediente()));
        this.txtNumDocumento.setText(this.rutaEdit.getDocumentoRecibido().getNumero());
        this.txtAreaAsunto.setText(this.rutaEdit.getDocumentoRecibido().getAsunto());
        this.txtDe.setText(this.rutaEdit.getDocumentoRecibido().getDe());
        this.txtAreaObservacion.setText(this.rutaEdit.getDocumentoRecibido().getObservacion());    
        this.cldFechaRecepcion.setDate(this.rutaEdit.getFechaHora_recepcion());
        this.cldFechaRespuesta.setDate(this.rutaEdit.getFecha_respuesta());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String hora = formato.format(this.rutaEdit.getFechaHora_recepcion());
        txtHora.setText(hora.substring(11,19));
        
                
        try{
            List<Requisito> requisitos = docRecLN.ConsultarAllRequisitos(this.rutaEdit.getDocumentoRecibido());
            if(requisitos.isEmpty()){
                chkOtroTramite.setSelected(true);
                cbxTipoTramites.setEnabled(false);
                mostrarCbxTipoTramite(null);
                mostrarOtroTramite();
                lblDia.setText("0");       
            }else{
                mostrarCbxTipoTramite(requisitos.get(0).getTipoTramite());
                mostrarTabla(requisitos);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
         
    }
    
    public void ModoNuevo(){
        this.modoGuardado="nuevo";
        this.txtHora.setEditable(false);
        this.setTitle("[Operaciones - Recepcion de documentos] - Expediente nuevo");
        txtExpedienteNumero.setEditable(false);
        txtExpedienteAño.setEditable(false);
        cbxTipoDocumentos.setEnabled(true);
        txtNumDocumento.setEnabled(true);
        chkSinNumero.setEnabled(true);
        btnGuardar.setText("Guardar");
        btnCancelar.setText("Cancelar");
        btnGuardar.setVisible(true);
        btnCancelar.setVisible(true);

        generarExpedienteNuevo();
        mostrarCbxTipoDocumento(null);
        mostrarCbxTipoTramite(null);
        calcularFechaRespuesta(); 
        mostrarTabla(null);
    }
    
    public final void mostrarCbxTipoDocumento(TipoDocumento tipoDocumentoSelect){
        try {
            if(tipoDocumentoSelect==null){
                List<TipoDocumento> tipoDocumentos = tipoDocumentoLN.ConsultarAll();
                mdlCbxTipoDocumento = new DefaultComboBoxModel(tipoDocumentos.toArray());
                cbxTipoDocumentos.setModel(mdlCbxTipoDocumento);   
            }else{
                List<TipoDocumento> tipoDocumentos = tipoDocumentoLN.ConsultarAll();
                mdlCbxTipoDocumento = new DefaultComboBoxModel(tipoDocumentos.toArray());
                cbxTipoDocumentos.setModel(mdlCbxTipoDocumento);
                for (int i = 0; i < tipoDocumentos.size(); i++) {  
                    if(tipoDocumentos.get(i).toString().equals(tipoDocumentoSelect.toString())){
                        cbxTipoDocumentos.setSelectedItem(tipoDocumentoSelect);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public final void mostrarCbxTipoTramite(TipoTramite tipoTramiteSelect){
        try {
                
            if(tipoTramiteSelect==null){//restriccion para modo de pantalla
                if(!chkOtroTramite.isSelected()){
                    
                    
                    List<TipoTramite> tipoTramites = tipoTramiteLN.ConsultarAll(unidadTramite);
                    if(tipoTramites.size()!=0){
                        mdlCbxTipoTramite = new DefaultComboBoxModel(tipoTramites.toArray());
                        cbxTipoTramites.setModel(mdlCbxTipoTramite);
                    }else{
                        tipoTramites.add(new TipoTramite(0, "VACIA", 0, null, null));
                        mdlCbxTipoTramite = new DefaultComboBoxModel(tipoTramites.toArray());
                        cbxTipoTramites.setModel(mdlCbxTipoTramite);
                    }

                }else{
                    List<TipoTramite> tipoTramites = new ArrayList<>();
                    tipoTramites.add(new TipoTramite(0, "OTRO TRAMITE",0, null, null));
                    mdlCbxTipoTramite = new DefaultComboBoxModel(tipoTramites.toArray());
                    cbxTipoTramites.setModel(mdlCbxTipoTramite);
                    cldFechaRespuesta.setDate(null);
                }
            }else{
                List<TipoTramite> tipoTramites = tipoTramiteLN.ConsultarAll(unidadTramite);
                mdlCbxTipoTramite = new DefaultComboBoxModel(tipoTramites.toArray());
                cbxTipoTramites.setModel(mdlCbxTipoTramite);
                for (int i = 0; i < tipoTramites.size(); i++) {
                    if(tipoTramites.get(i).toString().equals(tipoTramiteSelect.toString())){
                        cbxTipoTramites.setSelectedIndex(i);
                    }
                }
            }
            lblDia.setText(Integer.toString(((TipoTramite)cbxTipoTramites.getSelectedItem()).getTiempo_estimado())); 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public final void mdlRequisitos(){
        
        tablaRequisitos.setModel(new mdlRequisitoDocNew(requisitos));
        Integer[] Anchos = {40,350};
        Integer[] Alineaciones = {JLabel.CENTER,JLabel.LEFT};
        String[] Formatos = {"Logico","Cadena"};
        String[] Modos = {"Normal","Normal"};

        Util.AplicarEstilos(tablaRequisitos, Anchos, Alineaciones, Formatos, Modos);
        
    }

    public final void mostrarTabla(List<Requisito> requisitosEdit){     
            try {
                if(requisitosEdit ==  null){
                    if(!chkOtroTramite.isSelected()){
                        TipoTramite ojetoSelect = (TipoTramite)cbxTipoTramites.getSelectedItem();
                        requisitos = requisitoLN.ConsultarAll(ojetoSelect);
                        mdlRequisitos();
                    }else{
                        requisitos = requisitoLN.ConsultarAll(new TipoTramite(0));
                        tablaRequisitos.setModel(new mdlRequisitoDocNew(requisitos));
                        mdlRequisitos();
                    }
                }else{
                    if(!chkOtroTramite.isSelected()){
                        requisitos = requisitosEdit;
                        mdlRequisitos();
                    }else{
                        requisitos = requisitoLN.ConsultarAll(new TipoTramite(0));
                        tablaRequisitos.setModel(new mdlRequisitoDocNew(requisitos));
                        mdlRequisitos();
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
    }
    
    public final void generarExpedienteNuevo(){     
            try {
                Date date = documentoRecibidoLN.ConsultarFechaBD();
                
                DocumentoRecibido ultimoExpediente = new DocumentoRecibido();
                //ultimoExpediente = documentoRecibidoLN.ConsultarUltimo();
                ultimoExpediente = documentoRecibidoLN.ConsultarUltimoUT(unidadTramite);
                String formato = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
                String anioActual = formatoFecha.format(date).substring(0,4);

                cldFechaRecepcion.setDate(date);
                txtHora.setText(formatoFecha.format(date).substring(11, 19));
                
                if(ultimoExpediente!=null){
                    if(ultimoExpediente.getAño_expediente()==Integer.parseInt(anioActual)){
                        String numTxt="";
                        int num = Integer.parseInt(ultimoExpediente.getNumero_expediente().substring(0,3));
                        num+=1;
                        if(num<10){
                            numTxt="00"+Integer.toString(num);
                            System.out.println(numTxt);
                        }else if(num<100){
                            numTxt="0"+Integer.toString(num);
                            System.out.println(numTxt);
                        }else if(num<1000){
                            numTxt=Integer.toString(num);
                        }

                        txtExpedienteNumero.setText(numTxt);
                        txtExpedienteAño.setText(anioActual);
                        lblSiglas.setText(unidadTramite.getAbreviatura()+"-"+unidadTramite.getUnidadOrganizativa().getAbreviatura());

                        txtHora.setText(Calendario.getHoraTxt(date));
                    }else{
                        txtExpedienteNumero.setText("001");
                        txtExpedienteAño.setText(anioActual);
                        lblSiglas.setText(unidadTramite.getAbreviatura()+"-"+unidadTramite.getUnidadOrganizativa().getAbreviatura());
                    }
                }else{
                        txtExpedienteNumero.setText("001");
                        txtExpedienteAño.setText(anioActual);
                        lblSiglas.setText(unidadTramite.getAbreviatura()+"-"+unidadTramite.getUnidadOrganizativa().getAbreviatura());
                }


            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        pnlDocumento = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtExpedienteNumero = new javax.swing.JTextField();
        txtExpedienteAño = new javax.swing.JTextField();
        cbxTipoDocumentos = new javax.swing.JComboBox();
        txtNumDocumento = new javax.swing.JTextField();
        txtDe = new javax.swing.JTextField();
        chkSinNumero = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaAsunto = new javax.swing.JTextArea();
        lblSiglas = new javax.swing.JLabel();
        pnlObservacion = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaObservacion = new javax.swing.JTextArea();
        pnlTipoTramite = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbxTipoTramites = new javax.swing.JComboBox();
        lblDia = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        chkOtroTramite = new javax.swing.JCheckBox();
        cldFechaRespuesta = new com.toedter.calendar.JDateChooser();
        Encabezado = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        pnlTabla = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaRequisitos = new javax.swing.JTable();
        txtHora = new javax.swing.JTextField();
        btnHora = new javax.swing.JButton();
        cldFechaRecepcion = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[Operacion - recepcion de documentos] - Expediente nuevo\n");

        pnlDocumento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Documento")));

        jLabel1.setText("N° de expediente");

        jLabel2.setText("Tipo de documento");

        jLabel3.setText("N° de Documento");

        jLabel4.setText("De");

        txtExpedienteNumero.setFocusable(false);

        txtExpedienteAño.setFocusable(false);

        cbxTipoDocumentos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        chkSinNumero.setText("Sin numero");
        chkSinNumero.setOpaque(false);
        chkSinNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSinNumeroActionPerformed(evt);
            }
        });

        jLabel5.setText("Asunto");

        txtAreaAsunto.setColumns(20);
        txtAreaAsunto.setRows(5);
        jScrollPane1.setViewportView(txtAreaAsunto);

        javax.swing.GroupLayout pnlDocumentoLayout = new javax.swing.GroupLayout(pnlDocumento);
        pnlDocumento.setLayout(pnlDocumentoLayout);
        pnlDocumentoLayout.setHorizontalGroup(
            pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDocumentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlDocumentoLayout.createSequentialGroup()
                        .addComponent(txtExpedienteNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExpedienteAño, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSiglas, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtDe, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDocumentoLayout.createSequentialGroup()
                        .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbxTipoDocumentos, javax.swing.GroupLayout.Alignment.LEADING, 0, 195, Short.MAX_VALUE)
                            .addComponent(txtNumDocumento, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkSinNumero)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlDocumentoLayout.setVerticalGroup(
            pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDocumentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDocumentoLayout.createSequentialGroup()
                        .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSiglas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtExpedienteNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtExpedienteAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbxTipoDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNumDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkSinNumero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );

        pnlObservacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Documento"), "Observacion"));

        txtAreaObservacion.setColumns(20);
        txtAreaObservacion.setRows(5);
        txtAreaObservacion.setFocusable(false);
        jScrollPane2.setViewportView(txtAreaObservacion);

        javax.swing.GroupLayout pnlObservacionLayout = new javax.swing.GroupLayout(pnlObservacion);
        pnlObservacion.setLayout(pnlObservacionLayout);
        pnlObservacionLayout.setHorizontalGroup(
            pnlObservacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlObservacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pnlObservacionLayout.setVerticalGroup(
            pnlObservacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlTipoTramite.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Documento"), "Tipo de tramite a iniciar"));
        pnlTipoTramite.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Descripcion");
        pnlTipoTramite.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 32, 105, -1));

        jLabel7.setText("Tiempo estimado");
        pnlTipoTramite.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 55, 105, -1));

        jLabel8.setText("Fecha de respuesta");
        pnlTipoTramite.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 75, 105, -1));

        cbxTipoTramites.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTipoTramites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoTramitesActionPerformed(evt);
            }
        });
        pnlTipoTramite.add(cbxTipoTramites, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 29, 250, -1));

        lblDia.setText("---");
        pnlTipoTramite.add(lblDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 55, 38, -1));

        jLabel11.setText("dias");
        pnlTipoTramite.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 55, 38, -1));
        pnlTipoTramite.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 75, 123, -1));

        chkOtroTramite.setText("Otro tipo de tramite");
        chkOtroTramite.setOpaque(false);
        chkOtroTramite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkOtroTramiteActionPerformed(evt);
            }
        });
        pnlTipoTramite.add(chkOtroTramite, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 125, -1, -1));

        cldFechaRespuesta.setEnabled(false);
        pnlTipoTramite.add(cldFechaRespuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 75, 173, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel14.setText("Fecha de recepcion");

        pnlTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("Requisitos"));

        tablaRequisitos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaRequisitos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tablaRequisitosPropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(tablaRequisitos);

        javax.swing.GroupLayout pnlTablaLayout = new javax.swing.GroupLayout(pnlTabla);
        pnlTabla.setLayout(pnlTablaLayout);
        pnlTablaLayout.setHorizontalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTablaLayout.setVerticalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTablaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );

        btnHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoraActionPerformed(evt);
            }
        });

        cldFechaRecepcion.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cldFechaRecepcionPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(cldFechaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHora, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addComponent(pnlDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlObservacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pnlTipoTramite, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(Encabezado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTipoTramite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnCancelar)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel14)
                        .addComponent(btnHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cldFechaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxTipoTramitesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoTramitesActionPerformed
        mostrarTabla(null);
        lblDia.setText(Integer.toString(((TipoTramite)cbxTipoTramites.getSelectedItem()).getTiempo_estimado()));
        calcularFechaRespuesta();  
    }//GEN-LAST:event_cbxTipoTramitesActionPerformed

    public void calcularFechaRespuesta(){
        int aumento = ((TipoTramite)cbxTipoTramites.getSelectedItem()).getTiempo_estimado();
        cldFechaRespuesta.setDate(new Date(cldFechaRecepcion.getDate().getYear(),cldFechaRecepcion.getDate().getMonth(),(cldFechaRecepcion.getDate().getDate()+aumento)));
    }
    private void tablaRequisitosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tablaRequisitosPropertyChange
        mostrarObservaciones();
    }//GEN-LAST:event_tablaRequisitosPropertyChange

    private void chkSinNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSinNumeroActionPerformed
        if(chkSinNumero.isSelected()){
            txtNumDocumento.setText("S/N");
            txtNumDocumento.setFocusable(false);
        }else{
            txtNumDocumento.setText(null);
            txtNumDocumento.setFocusable(true);
        }
    }//GEN-LAST:event_chkSinNumeroActionPerformed

    private void chkOtroTramiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkOtroTramiteActionPerformed
        mostrarOtroTramite();
    }//GEN-LAST:event_chkOtroTramiteActionPerformed

    public void mostrarOtroTramite(){
        if(chkOtroTramite.isSelected()){
            txtAreaObservacion.setText(null);
            txtAreaObservacion.setFocusable(true);
            cbxTipoTramites.setEnabled(false);
            cldFechaRespuesta.setEnabled(false);
            mostrarCbxTipoTramite(null);
        }else{
            txtAreaObservacion.setText(null);
            txtAreaObservacion.setFocusable(false);
            cbxTipoTramites.setEnabled(true);
            mostrarCbxTipoTramite(null);
            calcularFechaRespuesta();
            cldFechaRespuesta.setEnabled(true);
        }
        mostrarTabla(null);
    }
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(llenadoOK()){
            
            if(JOptionPane.showConfirmDialog(null, "¿Registrar tramite?","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                if(modoGuardado.equals("nuevo")){
                    guardarDocumento();
                }else if(modoGuardado.equals("editor")){
                    modificarDocumento();
                }
                JOptionPane.showMessageDialog(null, "Registro exitoso!");
                this.dispose();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoraActionPerformed
        Reloj reloj = new Reloj(null,true, txtHora);
        reloj.setVisible(true);
    }//GEN-LAST:event_btnHoraActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
         if(JOptionPane.showConfirmDialog(null, "¿Desea Cancelar el Proceso?","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
             this.dispose();
         }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cldFechaRecepcionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cldFechaRecepcionPropertyChange
//        if (modoGuardado.equals("nuevo")) {
//            int aumento = ((TipoTramite)cbxTipoTramites.getSelectedItem()).getTiempo_estimado();
//            cldFechaRespuesta.setDate(new Date(cldFechaRecepcion.getDate().getYear(),cldFechaRecepcion.getDate().getMonth(),(cldFechaRecepcion.getDate().getDate()+aumento)));
//        }
    }//GEN-LAST:event_cldFechaRecepcionPropertyChange


    public void guardarDocumento(){
        try {      
                DocumentoRecibido objeto = new DocumentoRecibido();
                
                objeto.setAño_expediente(Integer.parseInt(txtExpedienteAño.getText()));
                objeto.setDe(txtDe.getText());
                objeto.setAsunto(txtAreaAsunto.getText());
                objeto.setObservacion(txtAreaObservacion.getText());
                objeto.setNumero(txtNumDocumento.getText());
                objeto.setNumero_expediente(txtExpedienteNumero.getText()+"-"+txtExpedienteAño.getText()+"-"+lblSiglas.getText());
                objeto.setEstado(false);
                objeto.setTipoDocumento((TipoDocumento)cbxTipoDocumentos.getSelectedItem());
                documentoRecibidoLN.Insertar(objeto);
                
                guardarRuta();
                guardarRequisitos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void guardarRuta(){
        try {       
                    Ruta ruta = new Ruta();
                    ruta.setDocumentoRecibido(documentoRecibidoLN.ConsultarUltimo());
                    ruta.setTipo_expediente(((TipoTramite)cbxTipoTramites.getSelectedItem()).getNombre());
                    ruta.setUnidadTramite(unidadTramite);
                    Date fechaRecepcion = new Date(cldFechaRecepcion.getDate().getYear(), cldFechaRecepcion.getDate().getMonth(),cldFechaRecepcion.getDate().getDate(),Integer.parseInt(txtHora.getText().substring(0,2)),Integer.parseInt(txtHora.getText().substring(3, 5)),Integer.parseInt(txtHora.getText().substring(6,8)));           
                    ruta.setFechaHora_recepcion(fechaRecepcion);
                    ruta.setFecha_respuesta(cldFechaRespuesta.getDate());
                    rutaLN.Insertar(ruta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void guardarRequisitos(){
        try {      
            DocRec obejto = new DocRec();

            for (int i = 0; i < requisitos.size(); i++) {
                obejto.setDocumentoRecibido(documentoRecibidoLN.ConsultarUltimo());
                obejto.setRequisito(requisitos.get(i));
                docRecLN.Insertar(obejto);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void modificarDocumento(){
        try {      
                DocumentoRecibido objeto = new DocumentoRecibido();
                objeto.setId_documentoRecibido(this.rutaEdit.getDocumentoRecibido().getId_documentoRecibido());
                objeto.setAño_expediente(Integer.parseInt(txtExpedienteAño.getText()));
                objeto.setDe(txtDe.getText());
                objeto.setAsunto(txtAreaAsunto.getText());
                objeto.setObservacion(txtAreaObservacion.getText());
                objeto.setNumero(txtNumDocumento.getText());
                objeto.setNumero_expediente(txtExpedienteNumero.getText()+"-"+txtExpedienteAño.getText()+"-"+lblSiglas.getText());
                objeto.setEstado(false);
                objeto.setTipoDocumento((TipoDocumento)cbxTipoDocumentos.getSelectedItem());
                documentoRecibidoLN.Modificar(objeto);
                
                modificarRuta();
                modificarRequisitos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void modificarRuta(){
        try {       
                    Ruta ruta = new Ruta();
                    ruta.setId_ruta(rutaEdit.getId_ruta());
                    ruta.setDocumentoRecibido(rutaEdit.getDocumentoRecibido());
                    ruta.setTipo_expediente(((TipoTramite)cbxTipoTramites.getSelectedItem()).getNombre());
                    ruta.setUnidadTramite(unidadTramite);
                    int hh = Integer.parseInt(txtHora.getText().substring(0,2));
                    int mm = Integer.parseInt(txtHora.getText().substring(3,5));
                    int ss = Integer.parseInt(txtHora.getText().substring(6,8));
                    Date fechaRecepcion = new Date(cldFechaRecepcion.getDate().getYear(), cldFechaRecepcion.getDate().getMonth(),cldFechaRecepcion.getDate().getDate(),hh,mm,ss);
                    ruta.setFechaHora_recepcion(cldFechaRecepcion.getDate());
                    ruta.setFecha_respuesta(cldFechaRespuesta.getDate());   
                    rutaLN.Modificar(ruta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void modificarRequisitos(){
        try {      
            DocRec obejto = new DocRec();
            docRecLN.EliminarAll(rutaEdit.getDocumentoRecibido());
            for (int i = 0; i < requisitos.size(); i++) {
                obejto.setDocumentoRecibido(rutaEdit.getDocumentoRecibido());
                obejto.setRequisito(requisitos.get(i));
                docRecLN.Insertar(obejto);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void mostrarObservaciones(){
  
        boolean completo = true;
        for (int i = 0; i < requisitos.size(); i++) {
            if(!requisitos.get(i).getEstado()){
                completo = false;
                break;
            }
        }
        
        String obs= "";
        if(completo){
            obs = obs+"El expediente esta completo.";
        }else{
            obs = obs+"El expediente esta incompleto por que no incluye ";

            for (int i = 0; i < requisitos.size(); i++) {
                if(requisitos.size()<2){
                    if(!requisitos.get(0).getEstado()){
                        obs = obs + requisitos.get(0).getDescipcion()+".";
                        break;
                    }
                }else{
                    if((i+2)==requisitos.size()){
                        if(!requisitos.get(i).getEstado()){
                            if(!requisitos.get(i+1).getDescipcion().substring(0,1).equalsIgnoreCase("I")){
                                obs = obs+requisitos.get(i).getDescipcion().toLowerCase();
                                if(!requisitos.get(i+1).getEstado()){
                                    obs = obs + " y "+requisitos.get(i+1).getDescipcion().toLowerCase()+".";
                                }else{
                                    obs = obs+".";
                                }
                                break;
                            }else{
                                obs = obs+requisitos.get(i).getDescipcion().toLowerCase();
                                if(!requisitos.get(i+1).getEstado()){
                                    obs = obs + " e "+requisitos.get(i+1).getDescipcion().toLowerCase()+".";
                                }else{
                                    obs = obs+".";
                                }
                                break;
                            }
                        }
                    }else{
                        if(!requisitos.get(i).getEstado()){
                            obs = obs + requisitos.get(i).getDescipcion().toLowerCase()+", ";
                        }
                    }
                }
        }
        }
        
        txtAreaObservacion.setText(obs);

    }

    
    public boolean llenadoOK(){
        
            if(!chkOtroTramite.isSelected()){
                if(((TipoTramite)cbxTipoTramites.getSelectedItem()).getId_tipoTramite()==0){
                    JOptionPane.showMessageDialog(null, "Especificado un tipo de tramite", "Error", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
            if(!chkSinNumero.isSelected() && txtNumDocumento.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El numero del documento no esta definido", "Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if(txtDe.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El nombre del remitente esta vacio", "Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if(txtAreaAsunto.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Defina el campo asunto", "Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if(cldFechaRecepcion.getDate()==null){
                JOptionPane.showMessageDialog(null, "La fecha de recepcion es incorrecta", "Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Encabezado;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHora;
    private javax.swing.JComboBox cbxTipoDocumentos;
    private javax.swing.JComboBox cbxTipoTramites;
    private javax.swing.JCheckBox chkOtroTramite;
    private javax.swing.JCheckBox chkSinNumero;
    private com.toedter.calendar.JDateChooser cldFechaRecepcion;
    private com.toedter.calendar.JDateChooser cldFechaRespuesta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDia;
    private javax.swing.JLabel lblSiglas;
    private javax.swing.JPanel pnlDocumento;
    private javax.swing.JPanel pnlObservacion;
    private javax.swing.JPanel pnlTabla;
    private javax.swing.JPanel pnlTipoTramite;
    private javax.swing.JTable tablaRequisitos;
    private javax.swing.JTextArea txtAreaAsunto;
    private javax.swing.JTextArea txtAreaObservacion;
    private javax.swing.JTextField txtDe;
    private javax.swing.JTextField txtExpedienteAño;
    private javax.swing.JTextField txtExpedienteNumero;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtNumDocumento;
    // End of variables declaration//GEN-END:variables
}
