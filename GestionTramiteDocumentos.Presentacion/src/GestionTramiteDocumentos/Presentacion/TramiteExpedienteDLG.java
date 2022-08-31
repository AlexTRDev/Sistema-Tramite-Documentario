package GestionTramiteDocumentos.Presentacion;

import GestionTramiteDocumentos.Entidades.DocRec;
import GestionTramiteDocumentos.Entidades.DocumentoEmitido;
import GestionTramiteDocumentos.Entidades.DocumentoRecibido;
import GestionTramiteDocumentos.Entidades.Ruta;
import GestionTramiteDocumentos.Entidades.TipoDocumento;
import GestionTramiteDocumentos.Entidades.Tramite;
import GestionTramiteDocumentos.Entidades.TramiteUnidad;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import GestionTramiteDocumentos.LogicaNegocio.DocRecLN;
import GestionTramiteDocumentos.LogicaNegocio.DocumentoEmitidoLN;
import GestionTramiteDocumentos.LogicaNegocio.DocumentoRecibidoLN;
import GestionTramiteDocumentos.LogicaNegocio.RequisitoLN;
import GestionTramiteDocumentos.LogicaNegocio.RutaLN;
import GestionTramiteDocumentos.LogicaNegocio.TipoDocumentoLN;
import GestionTramiteDocumentos.LogicaNegocio.TramiteLN;
import GestionTramiteDocumentos.LogicaNegocio.TramiteUnidadLN;
import GestionTramiteDocumentos.LogicaNegocio.UnidadTramiteLN;
import ModelosTablas.mdlRequisitoVisual;
import Util.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class TramiteExpedienteDLG extends javax.swing.JDialog {
    TipoDocumentoLN tipoDocumentoLN = new TipoDocumentoLN();
    RutaLN rutaLN = new RutaLN();
    Ruta rutaActual;
    TramiteLN tramiteLN = new TramiteLN();
    
    DocRecLN docRecLN = new DocRecLN();
    RequisitoLN requisitoLN = new RequisitoLN();
    DocumentoEmitidoLN documentoEmitidoLN = new DocumentoEmitidoLN();
    
    DocumentoRecibidoLN documentoRecibidoLN = new DocumentoRecibidoLN();
    DocumentoRecibido documentoRecibido;
    
    UnidadTramiteLN unidadTramiteLN = new UnidadTramiteLN();
    UnidadTramite unidadTramite = new UnidadTramite();
    
    DefaultComboBoxModel  mdlCbxTipoDocumento, mdlCbxUnidadTramite;

    DocumentoEmitido documentoAEmitir;
    
    public TramiteExpedienteDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        unidadTramite = SistemaTramiteDocumentario.oUnidadTramite;
        
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[MANTENIMIENTO] - TIPO DE DOCUMENTO");
//        Util.AplicarEncabezado(this,lblEncabezado,"UNIDAD_TRAMITE.PNG-64","TIPO DE DOCUMENTO","Permite registrar o actualizar los datos de Tipos de Documentos");
        Util.AplicarSubencabezado(this,SubEncabezado,"CONFIG-32",unidadTramite.toString());
        Util.HabilitarContenedor(pnlDocumentos,true);
        Util.HabilitarContenedor(pnlObservacion, true);
        Util.HabilitarContenedor(pnlRealizarTramite, true);
        Util.HabilitarContenedor(pnlRequisitos, true);
        Util.HabilitarContenedor(pnlTipoTramite, true);
        
        txtExpedienteUnidadOrganizativa.setEditable(false);
        
        txtExpedienteUnidadOrganizativa.setText(unidadTramite.getUnidadOrganizativa().getAbreviatura());
        txtExpedienteNumero.setText(null);
        txtExpedienteAnio.setText(null);
        txtExpedienteSigla.setText(unidadTramite.getAbreviatura());
        txtExpedienteUnidadOrganizativa.setText(unidadTramite.getUnidadOrganizativa().getAbreviatura());
        
        lblAbreviaturasDocumentoEmitir.setText(unidadTramite.getAbreviatura()+"-"+unidadTramite.getUnidadOrganizativa().getAbreviatura());
        
        txtExpedienteNumero.setEnabled(true);
        txtExpedienteAnio.setEditable(true);
        txtExpedienteSigla.setEnabled(true);
        txtExpedienteUnidadOrganizativa.setEnabled(false);
        txtNumDocumentoEmitir.setEditable(false);
        txtNumDocumentoEmitir.setFocusable(false);
        
        Util.HabilitarContenedor(pnlRealizarTramite, false);
    }
    
    public void inicialFrm(DocumentoEmitido documentoAEmitir){
        try {
            this.documentoAEmitir = documentoAEmitir;
            partirNumExpediente(documentoAEmitir.getNumero_expediente());
            mostrarCbxUnidadTramite(true);
            txtExpedienteAnio.setText(Integer.toString(documentoEmitidoLN.ConsultarFechaBD().getYear()+1900));
            txtAnioDocumentoEmitir.setText(Integer.toString(documentoEmitidoLN.ConsultarFechaBD().getYear()+1900));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void inicialFrm(){
        try {
            txtExpedienteNumero.setText(null);
            txtExpedienteAnio.setText(null);
            txtExpedienteSigla.setText(null);
            txtExpedienteUnidadOrganizativa.setText(SistemaTramiteDocumentario.oUnidadTramite.getUnidadOrganizativa().getAbreviatura());
            
            mostrarCbxUnidadTramite(true);
            mostrarCbxTipoDocumento(true);
            txtExpedienteAnio.setText(Integer.toString(documentoEmitidoLN.ConsultarFechaBD().getYear()+1900));
            txtAnioDocumentoEmitir.setText(Integer.toString(documentoEmitidoLN.ConsultarFechaBD().getYear()+1900));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void partirNumExpediente(String expediente){
        Integer[] nodos = new Integer[3];
        int j = 0;
        for (int i = 0; i < expediente.length(); i++) {
            if(expediente.substring(i,i+1).equals("-")){
                nodos[j]=i;
                j++;
            }
        }
        txtExpedienteNumero.setText(expediente.substring(0,nodos[0]));
        txtExpedienteAnio.setText(expediente.substring(nodos[0]+1,nodos[1]));
        txtExpedienteSigla.setText(expediente.substring(nodos[1]+1,nodos[2]));
        txtExpedienteUnidadOrganizativa.setText(expediente.substring(nodos[2]+1,expediente.length()));
    }
    
    public final void mostrarCbxUnidadTramite(boolean lleno){
        try {
            if(lleno){
                List<UnidadTramite> lista = unidadTramiteLN.ConsultarAll(unidadTramite.getUnidadOrganizativa()); 
                //quitamos de las opciones a la unidad actaul
                for (int i = 0; i < lista.size(); i++) {
                    if(lista.get(i).toString().equals(unidadTramite.toString())){
                        lista.remove(i);
                    }
                }
                mdlCbxUnidadTramite = new DefaultComboBoxModel(lista.toArray());
                cbxUnidadTramite.setModel(mdlCbxUnidadTramite);
            }else{
                List<UnidadTramite> lista = new ArrayList<>();
                mdlCbxUnidadTramite = new DefaultComboBoxModel(lista.toArray());
                cbxUnidadTramite.setModel(mdlCbxUnidadTramite);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public final void mostrarCbxTipoDocumento(boolean lleno){
        try {
            if(lleno){
                List<TipoDocumento> tipoDocumentos=tipoDocumentoLN.ConsultarAll();
                mdlCbxTipoDocumento = new DefaultComboBoxModel(tipoDocumentos.toArray());
                cbxTIpoDocumento.setModel(mdlCbxTipoDocumento);
            }else{
                List<TipoDocumento> tipoDocumentos = new ArrayList<>();
                mdlCbxTipoDocumento = new DefaultComboBoxModel(tipoDocumentos.toArray());
                cbxTIpoDocumento.setModel(mdlCbxTipoDocumento); 
            }   


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        pnlDocumentos = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblNumExpediente = new javax.swing.JLabel();
        lblTIpoDocumento = new javax.swing.JLabel();
        lblNumDocumento = new javax.swing.JLabel();
        lblDe = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblAsunto = new javax.swing.JLabel();
        pnlObservacion = new javax.swing.JPanel();
        lblObservacion = new javax.swing.JLabel();
        pnlTipoTramite = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblTiempoEstimado = new javax.swing.JLabel();
        lblFechaRespuesta = new javax.swing.JLabel();
        pnlRealizarTramite = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AtxtDescripcion = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        chkOtraEntidad = new javax.swing.JCheckBox();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        chkSinDocumento = new javax.swing.JCheckBox();
        cbxTIpoDocumento = new javax.swing.JComboBox();
        chkSinNumero = new javax.swing.JCheckBox();
        txtNumDocumentoEmitir = new javax.swing.JTextField();
        txtAnioDocumentoEmitir = new javax.swing.JTextField();
        lblAbreviaturasDocumentoEmitir = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        cbxUnidadTramite = new javax.swing.JComboBox();
        chkArchivo = new javax.swing.JCheckBox();
        txtOtraUnidadTramite = new javax.swing.JTextField();
        cldFechaEmision = new com.toedter.calendar.JDateChooser();
        cldFechaRecepcion = new com.toedter.calendar.JDateChooser();
        pnlRequisitos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRequisitos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtExpedienteNumero = new javax.swing.JTextField();
        txtExpedienteAnio = new javax.swing.JTextField();
        txtExpedienteSigla = new javax.swing.JTextField();
        txtExpedienteUnidadOrganizativa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        SubEncabezado = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[Operaciones - emision de documentos] - Tramite de expediente");
        setResizable(false);

        pnlDocumentos.setBorder(javax.swing.BorderFactory.createTitledBorder("Documento"));

        jLabel7.setText("N° de expediente");

        jLabel8.setText("Tipo de documento");

        jLabel9.setText("N° de documento");

        jLabel10.setText("De");

        lblNumExpediente.setText("---");

        lblTIpoDocumento.setText("---");

        lblNumDocumento.setText("---");

        lblDe.setText("---");

        jLabel15.setText("Asunto");

        lblAsunto.setText("---");

        javax.swing.GroupLayout pnlDocumentosLayout = new javax.swing.GroupLayout(pnlDocumentos);
        pnlDocumentos.setLayout(pnlDocumentosLayout);
        pnlDocumentosLayout.setHorizontalGroup(
            pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDocumentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblNumDocumento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                    .addComponent(lblTIpoDocumento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNumExpediente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDocumentosLayout.setVerticalGroup(
            pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDocumentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDocumentosLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblAsunto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlDocumentosLayout.createSequentialGroup()
                        .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNumExpediente)
                                .addComponent(jLabel15))
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblTIpoDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblNumDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15))))
        );

        pnlObservacion.setBorder(javax.swing.BorderFactory.createTitledBorder("Observacion"));

        lblObservacion.setText("---");

        javax.swing.GroupLayout pnlObservacionLayout = new javax.swing.GroupLayout(pnlObservacion);
        pnlObservacion.setLayout(pnlObservacionLayout);
        pnlObservacionLayout.setHorizontalGroup(
            pnlObservacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlObservacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblObservacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlObservacionLayout.setVerticalGroup(
            pnlObservacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlObservacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTipoTramite.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de tramite"));

        jLabel17.setText("Fecha de respuesta");

        jLabel18.setText("Tiempo estimado");

        jLabel19.setText("Descripcion");

        lblDescripcion.setText("---");

        lblTiempoEstimado.setText("---");

        lblFechaRespuesta.setText("---");

        javax.swing.GroupLayout pnlTipoTramiteLayout = new javax.swing.GroupLayout(pnlTipoTramite);
        pnlTipoTramite.setLayout(pnlTipoTramiteLayout);
        pnlTipoTramiteLayout.setHorizontalGroup(
            pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTipoTramiteLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblTiempoEstimado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addComponent(lblDescripcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFechaRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTipoTramiteLayout.setVerticalGroup(
            pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTipoTramiteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblTiempoEstimado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblFechaRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pnlRealizarTramite.setBorder(javax.swing.BorderFactory.createTitledBorder("Tramite realizado"));

        jLabel24.setText("Descripcion (Tramite realizado)");

        AtxtDescripcion.setColumns(20);
        AtxtDescripcion.setRows(5);
        jScrollPane2.setViewportView(AtxtDescripcion);

        jLabel25.setText("Fecha de emision");

        jLabel26.setText("N°");

        jLabel27.setText("Tipo");

        jLabel28.setBackground(new java.awt.Color(102, 153, 255));
        jLabel28.setText("Documento");
        jLabel28.setOpaque(true);

        jLabel29.setText("Unidad de tramite");

        chkOtraEntidad.setText("Otra entidad");
        chkOtraEntidad.setOpaque(false);
        chkOtraEntidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkOtraEntidadActionPerformed(evt);
            }
        });

        jLabel30.setText("Fecha de recepcion");

        jLabel31.setBackground(new java.awt.Color(102, 153, 255));
        jLabel31.setText("Traslado a");
        jLabel31.setOpaque(true);

        chkSinDocumento.setText("Sin documento");
        chkSinDocumento.setOpaque(false);
        chkSinDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSinDocumentoActionPerformed(evt);
            }
        });

        cbxTIpoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTIpoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTIpoDocumentoActionPerformed(evt);
            }
        });

        chkSinNumero.setText("S/N");
        chkSinNumero.setOpaque(false);
        chkSinNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSinNumeroActionPerformed(evt);
            }
        });

        txtAnioDocumentoEmitir.setEnabled(false);

        lblAbreviaturasDocumentoEmitir.setText("---");

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("-");

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("-");

        cbxUnidadTramite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUnidadTramite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUnidadTramiteActionPerformed(evt);
            }
        });

        chkArchivo.setText("Archivo");
        chkArchivo.setOpaque(false);
        chkArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkArchivoActionPerformed(evt);
            }
        });

        txtOtraUnidadTramite.setEnabled(false);

        cldFechaRecepcion.setEnabled(false);

        javax.swing.GroupLayout pnlRealizarTramiteLayout = new javax.swing.GroupLayout(pnlRealizarTramite);
        pnlRealizarTramite.setLayout(pnlRealizarTramiteLayout);
        pnlRealizarTramiteLayout.setHorizontalGroup(
            pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRealizarTramiteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRealizarTramiteLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRealizarTramiteLayout.createSequentialGroup()
                                .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel30))
                                    .addComponent(chkOtraEntidad))
                                .addGap(33, 33, 33)
                                .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnlRealizarTramiteLayout.createSequentialGroup()
                                        .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxUnidadTramite, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(pnlRealizarTramiteLayout.createSequentialGroup()
                                                .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(pnlRealizarTramiteLayout.createSequentialGroup()
                                                            .addComponent(txtNumDocumentoEmitir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(txtAnioDocumentoEmitir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lblAbreviaturasDocumentoEmitir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(cbxTIpoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(chkSinDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(txtOtraUnidadTramite, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cldFechaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cldFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 115, Short.MAX_VALUE)))
                                        .addContainerGap())))
                            .addGroup(pnlRealizarTramiteLayout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkSinNumero)
                                .addContainerGap())))
                    .addGroup(pnlRealizarTramiteLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(23, 23, 23))))
        );
        pnlRealizarTramiteLayout.setVerticalGroup(
            pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRealizarTramiteLayout.createSequentialGroup()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnlRealizarTramiteLayout.createSequentialGroup()
                        .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkSinDocumento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(cbxTIpoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(chkSinNumero)
                            .addComponent(txtNumDocumentoEmitir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAnioDocumentoEmitir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAbreviaturasDocumentoEmitir)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(cldFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(chkArchivo))
                        .addGap(6, 6, 6)
                        .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(cbxUnidadTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkOtraEntidad)
                            .addComponent(txtOtraUnidadTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRealizarTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(cldFechaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pnlRequisitos.setBorder(javax.swing.BorderFactory.createTitledBorder("Requisitos"));

        tblRequisitos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblRequisitos);

        javax.swing.GroupLayout pnlRequisitosLayout = new javax.swing.GroupLayout(pnlRequisitos);
        pnlRequisitos.setLayout(pnlRequisitosLayout);
        pnlRequisitosLayout.setHorizontalGroup(
            pnlRequisitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRequisitosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlRequisitosLayout.setVerticalGroup(
            pnlRequisitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        );

        jLabel3.setText("Numero de expediente");

        txtExpedienteNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtExpedienteAnio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtExpedienteSigla.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtExpedienteUnidadOrganizativa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtExpedienteUnidadOrganizativa.setFocusable(false);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("-");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("-");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("-");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        SubEncabezado.setBackground(new java.awt.Color(255, 255, 204));
        SubEncabezado.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDocumentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlRealizarTramite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlObservacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTipoTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlRequisitos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExpedienteNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExpedienteAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExpedienteSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExpedienteUnidadOrganizativa, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
            .addComponent(SubEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SubEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtExpedienteNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpedienteAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpedienteSigla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpedienteUnidadOrganizativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTipoTramite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlRequisitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRealizarTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {          
            consultarDocumentoAEnviar();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    public boolean esPrimerEnvio(){
        try {
            String numExpediente = txtExpedienteNumero.getText()+"-"+txtExpedienteAnio.getText()+"-"+txtExpedienteSigla.getText()+"-"+txtExpedienteUnidadOrganizativa.getText();
            DocumentoEmitido documento = new DocumentoEmitido();
            documento.setNumero_expediente(numExpediente);
            
            DocumentoEmitido documentoExiste = documentoEmitidoLN.Consultar1PorExpediente(documento);
            
            if(documentoExiste == null){
                System.out.println("NO ENCONTRADO");
                return true;
            }else{
                System.out.println("ENCONTRADO");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error1"+e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }  
        return false;
    }
    
    public DocumentoEmitido getDocumentoAEnviar(){
        
        DocumentoEmitido objeto = new DocumentoEmitido();
        String numExpediente = txtNumDocumentoEmitir.getText()+"-"+txtAnioDocumentoEmitir.getText()+"-"+lblAbreviaturasDocumentoEmitir.getText();
        objeto.setAnio(Integer.parseInt(txtAnioDocumentoEmitir.getText()));
        objeto.setAsunto(AtxtDescripcion.getText());
        objeto.setNumero(numExpediente);
        objeto.setNumero_expediente(documentoRecibido.getNumero_expediente());
        objeto.setFecha_recepcion(rutaActual.getFechaHora_recepcion());
        objeto.setFecha_emision(cldFechaEmision.getDate());
        objeto.setUnidadTramite(unidadTramite);

        if(chkSinDocumento.isSelected()){
            objeto.setTipoDocumento(new TipoDocumento());
        }else{
            objeto.setTipoDocumento((TipoDocumento)cbxTIpoDocumento.getSelectedItem());
        } 
        if(chkArchivo.isSelected()){
//            objeto.setPara(null);
            objeto.setPara("ARCHIVADO");
//            if(chkOtraEntidad.isSelected()){
//                objeto.setPara(((UnidadTramite)cbxUnidadTramite.getSelectedItem()).getNombre());
//            }else{
//                objeto.setPara(txtOtraUnidadTramite.getText().toUpperCase());
//            }
        }else{
            if(chkOtraEntidad.isSelected()){
                objeto.setPara(txtOtraUnidadTramite.getText().toUpperCase());
            }else{
                objeto.setPara(((UnidadTramite)cbxUnidadTramite.getSelectedItem()).getNombre());
            }
        }
        return objeto;
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(this.datosOK()){
            if(esPrimerEnvio()){
                this.setVisible(false);
                InicioTramite inicioTramite = new InicioTramite(null, rootPaneCheckingEnabled);
                inicioTramite.modoNuevo(getDocumentoAEnviar());
                inicioTramite.setVisible(true);
                if(!btnInicioCancelado && inicio_tramite){
                    guardarTodo();
                    this.dispose();
                }else{
                    this.setVisible(true);
                }
            }else{
                guardarTodo();
                this.dispose();
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed
    
    public void guardarTodo(){
        guardarTramite();
        guardarTramiteUnidad();
        guardarDocumentoEmitido();
        modificarDocumentoRecibido();//solo se ejecuta si se a seleccionado la opcion de archivar (fin de su tramite)
        JOptionPane.showMessageDialog(null, "Registro exitoso!");
    }
    
    public boolean datosOK(){
        if(this.documentoRecibido==null){
            JOptionPane.showMessageDialog(null, "No hay informacion del tramite");
            return false;
        }
        if(cldFechaEmision.getDate()==null){
            JOptionPane.showMessageDialog(null, "Fecha de emision incorrecta");
            return false;
        }
        if(chkOtraEntidad.isSelected() && txtOtraUnidadTramite.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El nombre de la unidad receptora esta vacío");
            return false;
        }
        if(AtxtDescripcion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo de descricion esta vacio");
            return false;        
        }
        return true;
    }
    
    public void modificarDocumentoRecibido(){
        try {
            if(chkArchivo.isSelected()){
                documentoRecibido.setEstado(true);
                documentoRecibidoLN.Modificar(documentoRecibido);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void consultarDocumentoAEnviar(){
        try {
            String numeroExpediente = txtExpedienteNumero.getText()+"-"+txtExpedienteAnio.getText()+"-"+txtExpedienteSigla.getText()+"-"+txtExpedienteUnidadOrganizativa.getText();
            DocumentoRecibido objeto = new DocumentoRecibido();
            objeto.setNumero_expediente(numeroExpediente);
            documentoRecibido = documentoRecibidoLN.Consultar1NumExp(objeto); 
            rutaActual = rutaLN.Consulta1DU(new Ruta(WIDTH, null, null,null, documentoRecibido, unidadTramite));
            
            //verifiquemos si el expediente aun esta en nuestro poder para eso tendremo que verificar que en su 
            //ruta ultima la unidad de tramite sea la nuestra
            
            Ruta rutaAvance = rutaLN.ConsultarUltima(new Ruta(WIDTH, null, null, null, documentoRecibido, null));
            DocumentoEmitido ultimoDocumentoEmitido = documentoEmitidoLN.ConsultarUltimo(new DocumentoEmitido(null, null, null, null, null, null, documentoRecibido.getNumero_expediente(), null, null, null,null,false));
   
            System.out.println("Unidad avacen: "+rutaAvance.getUnidadTramite().getNombre());
            System.out.println("Unidad actual:"+unidadTramite.getNombre());
            //PRIMERO VERIFICA SI LA RUTA DE TRABAJO Y LA RUTA DE AVANCE SEAN IGUALES
            
            if(documentoRecibido!=null){//existe en la base de datos?
                if(rutaAvance.getUnidadTramite().getId_unidad_tramite()==unidadTramite.getId_unidad_tramite()){//unidadAcutal=unidadAvance?
                    if(ultimoDocumentoEmitido == null){//verifica un solo envio
                        Util.HabilitarContenedor(pnlRealizarTramite, true);
                        mostrarDocumento();
                        mostrarRequisitos_TipoTramite();
                        mostrarFechas();

                        Date date = documentoEmitidoLN.ConsultarFechaBD();
                        txtNumDocumentoEmitir.setText(documentoEmitidoLN.generarNumeroDocumento(unidadTramite, (TipoDocumento)cbxTIpoDocumento.getSelectedItem()));
                        txtAnioDocumentoEmitir.setText(Integer.toString(date.getYear()+1900));
                        lblAbreviaturasDocumentoEmitir.setText(unidadTramite.getAbreviatura()+"-"+unidadTramite.getUnidadOrganizativa().getAbreviatura());
                    }else if(ultimoDocumentoEmitido.getUnidadTramite().getId_unidad_tramite()!=unidadTramite.getId_unidad_tramite()){
                        Util.HabilitarContenedor(pnlRealizarTramite, true);
                        mostrarDocumento();
                        mostrarRequisitos_TipoTramite();
                        mostrarFechas();

                        Date date = documentoEmitidoLN.ConsultarFechaBD();
                        txtNumDocumentoEmitir.setText(documentoEmitidoLN.generarNumeroDocumento(unidadTramite, (TipoDocumento)cbxTIpoDocumento.getSelectedItem()));
                        txtAnioDocumentoEmitir.setText(Integer.toString(date.getYear()+1900));
                        lblAbreviaturasDocumentoEmitir.setText(unidadTramite.getAbreviatura()+"-"+unidadTramite.getUnidadOrganizativa().getAbreviatura());
                        
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "El expediente ya fue enviado");
                    }

                }else{
                    JOptionPane.showMessageDialog(rootPane, "El expediente se encuentra en otra unidad de tramite");
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "No se ha encontrado el expediente");
            }
 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void mostrarDocumento(){
        try {
            lblNumExpediente.setText(documentoRecibido.getNumero_expediente());
            lblTIpoDocumento.setText(documentoRecibido.getTipoDocumento().getNombre());
            lblNumDocumento.setText(documentoRecibido.getNumero());
            lblDe.setText(documentoRecibido.getDe());
            lblAsunto.setText(documentoRecibido.getAsunto());
            lblObservacion.setText(documentoRecibido.getObservacion());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void mostrarRequisitos_TipoTramite(){
        try {
            List<DocRec> docRecs = docRecLN.ConsultarAll(documentoRecibido);
             if(docRecs.size()>=1){
                 lblDescripcion.setText(docRecs.get(0).getRequisito().getTipoTramite().getNombre());
                 tblRequisitos.setModel(new mdlRequisitoVisual(docRecs));
                 lblTiempoEstimado.setText(Integer.toString(docRecs.get(0).getRequisito().getTipoTramite().getTiempo_estimado()));
                 lblFechaRespuesta.setText(rutaActual.getFecha_respuesta().toString());
             }else{
                 lblDescripcion.setText("Otro tipo de tramite");
                 tblRequisitos.setModel(new mdlRequisitoVisual(docRecs));
             }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void mostrarFechas(){
        try {
            Date date = documentoEmitidoLN.ConsultarFechaBD();
            cldFechaEmision.setDate(date);
            cldFechaRecepcion.setDate(rutaActual.getFechaHora_recepcion());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void guardarTramite(){
        try {
            Tramite tramite = new Tramite();
            tramite.setDescripcion(AtxtDescripcion.getText());
            tramite.setFecha_emision(cldFechaEmision.getDate());
            tramite.setRuta(rutaActual);
            tramiteLN.Insertar(tramite);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void guardarTramiteUnidad(){
        try {
            TramiteUnidadLN tramiteUnidadLN = new TramiteUnidadLN();
            TramiteUnidad tramiteUnidad = new TramiteUnidad(unidadTramite,tramiteLN.Consultar1UltimoAgregado(rutaActual));
            tramiteUnidadLN.Insertar(tramiteUnidad);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void guardarDocumentoEmitido(){
        try {
            DocumentoEmitido objeto = new DocumentoEmitido();

            String numExpediente = txtNumDocumentoEmitir.getText()+"-"+txtAnioDocumentoEmitir.getText()+"-"+lblAbreviaturasDocumentoEmitir.getText();

            objeto.setAnio(Integer.parseInt(txtAnioDocumentoEmitir.getText()));
            objeto.setAsunto(AtxtDescripcion.getText());
            objeto.setNumero_expediente(documentoRecibido.getNumero_expediente());
            objeto.setFecha_recepcion(rutaActual.getFechaHora_recepcion());
            objeto.setFecha_emision(cldFechaEmision.getDate());
            objeto.setUnidadTramite(unidadTramite);
            
            if(chkSinDocumento.isSelected()){
                objeto.setNumero("Sin Documento");
            }else{
                objeto.setNumero(numExpediente);
            }
            
            if(esPrimerEnvio()){
                objeto.setInicio_tramite(true);
            }else{
                objeto.setInicio_tramite(false);
            }
            
            if(chkSinDocumento.isSelected()){
                objeto.setTipoDocumento(new TipoDocumento());
            }else{
                objeto.setTipoDocumento((TipoDocumento)cbxTIpoDocumento.getSelectedItem());
            }
            
            if(chkArchivo.isSelected()){
//                objeto.setPara(null);
                objeto.setPara("ARCHIVADO");
                objeto.setInicio_tramite(false);
//                if(chkOtraEntidad.isSelected()){
//                    objeto.setPara(((UnidadTramite)cbxUnidadTramite.getSelectedItem()).getNombre());
//                }else{
//                    objeto.setPara(txtOtraUnidadTramite.getText().toUpperCase());
//                }
            }else{
                if(chkOtraEntidad.isSelected()){
                    objeto.setPara(txtOtraUnidadTramite.getText().toUpperCase());
                }else{
                    objeto.setPara(((UnidadTramite)cbxUnidadTramite.getSelectedItem()).getNombre());
                }
            }

            objeto.setTramite(tramiteLN.Consultar1UltimoAgregado(rutaActual));
            documentoEmitidoLN.Insertar(objeto);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } 
    }
    
    private void chkSinDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSinDocumentoActionPerformed
        if(chkSinDocumento.isSelected()){
            txtNumDocumentoEmitir.setText("");
            txtAnioDocumentoEmitir.setText("");
            lblAbreviaturasDocumentoEmitir.setText("");
            cbxTIpoDocumento.setEnabled(false);
            chkSinNumero.setSelected(false);
            chkSinNumero.setEnabled(false);
            txtNumDocumentoEmitir.setText(null);
            mostrarCbxTipoDocumento(false);
        }else{
            cbxTIpoDocumento.setEnabled(true);
            chkSinNumero.setSelected(false);
            chkSinNumero.setEnabled(true);
            mostrarCbxTipoDocumento(true);
            try {
                String numero = documentoEmitidoLN.generarNumeroDocumento(unidadTramite, (TipoDocumento)cbxTIpoDocumento.getSelectedItem());
                txtAnioDocumentoEmitir.setText(txtExpedienteAnio.getText());
                txtNumDocumentoEmitir.setText(numero);   
                lblAbreviaturasDocumentoEmitir.setText(unidadTramite.getAbreviatura() + "-" + unidadTramite.getUnidadOrganizativa().getAbreviatura());
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_chkSinDocumentoActionPerformed

    private void chkArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkArchivoActionPerformed
        if(chkArchivo.isSelected()){
            cbxUnidadTramite.setEnabled(false);
            chkOtraEntidad.setSelected(false);
            chkOtraEntidad.setEnabled(false);
            txtOtraUnidadTramite.setEnabled(false);
            mostrarCbxUnidadTramite(false);
        }else{
            cbxUnidadTramite.setEnabled(true);
            chkOtraEntidad.setSelected(false);
            chkOtraEntidad.setEnabled(true);
            txtOtraUnidadTramite.setEnabled(false);
            mostrarCbxUnidadTramite(true);
        }
    }//GEN-LAST:event_chkArchivoActionPerformed

    private void chkOtraEntidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkOtraEntidadActionPerformed
        if(chkOtraEntidad.isSelected()){
            cbxUnidadTramite.setEnabled(false);
            txtOtraUnidadTramite.setText(null);
            txtOtraUnidadTramite.setEnabled(true);
            mostrarCbxUnidadTramite(false);
        }else{
            cbxUnidadTramite.setEnabled(true);
            txtOtraUnidadTramite.setText(null);
            txtOtraUnidadTramite.setEnabled(false);
            mostrarCbxUnidadTramite(true);
        }
    }//GEN-LAST:event_chkOtraEntidadActionPerformed

    private void chkSinNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSinNumeroActionPerformed
        try {
            if(chkSinNumero.isSelected()){
                txtNumDocumentoEmitir.setText("S/N");
            }else{
                String numero = documentoEmitidoLN.generarNumeroDocumento(unidadTramite, (TipoDocumento)cbxTIpoDocumento.getSelectedItem());
                txtNumDocumentoEmitir.setText(numero);
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_chkSinNumeroActionPerformed

    private void cbxTIpoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTIpoDocumentoActionPerformed
        try {
            String numero = documentoEmitidoLN.generarNumeroDocumento(unidadTramite, (TipoDocumento)cbxTIpoDocumento.getSelectedItem());
            txtNumDocumentoEmitir.setText(numero);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_cbxTIpoDocumentoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if((JOptionPane.showConfirmDialog(null,"¿Desea Cancelar el Tramite?","Mensaje del Sistema",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)){
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbxUnidadTramiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUnidadTramiteActionPerformed
        //el expediente puede segir su tramite por todas las unidades de tramite por las que no aya pasado aun
        if(((UnidadTramite)cbxUnidadTramite.getSelectedItem()).toString().equals(unidadTramite.toString())){
            JOptionPane.showMessageDialog(null, "Autoenvio no permitido. Elija una unidad\n de tramite distinta a la actual", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_cbxUnidadTramiteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AtxtDescripcion;
    private javax.swing.JLabel SubEncabezado;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox cbxTIpoDocumento;
    private javax.swing.JComboBox cbxUnidadTramite;
    private javax.swing.JCheckBox chkArchivo;
    private javax.swing.JCheckBox chkOtraEntidad;
    private javax.swing.JCheckBox chkSinDocumento;
    private javax.swing.JCheckBox chkSinNumero;
    private com.toedter.calendar.JDateChooser cldFechaEmision;
    private com.toedter.calendar.JDateChooser cldFechaRecepcion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAbreviaturasDocumentoEmitir;
    private javax.swing.JLabel lblAsunto;
    private javax.swing.JLabel lblDe;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFechaRespuesta;
    private javax.swing.JLabel lblNumDocumento;
    private javax.swing.JLabel lblNumExpediente;
    private javax.swing.JLabel lblObservacion;
    private javax.swing.JLabel lblTIpoDocumento;
    private javax.swing.JLabel lblTiempoEstimado;
    private javax.swing.JPanel pnlDocumentos;
    private javax.swing.JPanel pnlObservacion;
    private javax.swing.JPanel pnlRealizarTramite;
    private javax.swing.JPanel pnlRequisitos;
    private javax.swing.JPanel pnlTipoTramite;
    private javax.swing.JTable tblRequisitos;
    private javax.swing.JTextField txtAnioDocumentoEmitir;
    private javax.swing.JTextField txtExpedienteAnio;
    private javax.swing.JTextField txtExpedienteNumero;
    private javax.swing.JTextField txtExpedienteSigla;
    private javax.swing.JTextField txtExpedienteUnidadOrganizativa;
    private javax.swing.JTextField txtNumDocumentoEmitir;
    private javax.swing.JTextField txtOtraUnidadTramite;
    // End of variables declaration//GEN-END:variables
    static boolean inicio_tramite = false;
    static boolean btnInicioCancelado = true;    

}
