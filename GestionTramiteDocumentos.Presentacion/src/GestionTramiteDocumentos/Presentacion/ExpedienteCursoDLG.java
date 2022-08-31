package GestionTramiteDocumentos.Presentacion;
import GestionTramiteDocumentos.Entidades.DocRec;
import GestionTramiteDocumentos.Entidades.DocumentoEmitido;
import GestionTramiteDocumentos.Entidades.DocumentoRecibido;
import GestionTramiteDocumentos.Entidades.Ruta;
import GestionTramiteDocumentos.Entidades.Tramite;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import GestionTramiteDocumentos.LogicaNegocio.DocRecLN;
import GestionTramiteDocumentos.LogicaNegocio.DocumentoEmitidoLN;
import GestionTramiteDocumentos.LogicaNegocio.RutaLN;
import GestionTramiteDocumentos.LogicaNegocio.UnidadTramiteLN;
import Util.FormatoFecha;
import Util.Util;
import Util.mdlGeneral;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ExpedienteCursoDLG extends javax.swing.JDialog {
    UnidadTramiteLN unidadTramiteLN = new UnidadTramiteLN();
    UnidadTramite unidadTramite = new UnidadTramite();
    DocumentoEmitidoLN documentoEmitidoLN = new DocumentoEmitidoLN();
    RutaLN rutaLN = new RutaLN();
    
    //De uso en modo vista
    List<DocumentoEmitido> documentoEmitidos;
    List<Tramite> tramites;
    List<Ruta> rutas;
    List<DocumentoRecibido> documentoRecibidos;
    //------------------------------------------
    
    //De uso en modo nuevo
    DocumentoEmitido MNdocumentoEmitido;
    Tramite MNtramite;
    Ruta MNruta;
    DocumentoRecibido MNdocumentoRecibido;
    //-------------------------------------------
    
    DefaultComboBoxModel  mdlCbxTipoDocumento, mdlCbxUnidadTramite;
    String modoPantalla;
    DocumentoRecibido documentoRecibidoAConsultar;
    public ExpedienteCursoDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        txtHora.setEditable(false);
        cldFecha.setEnabled(false);
        unidadTramite = SistemaTramiteDocumentario.oUnidadTramite;
        
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[OPERACIONES - RECEPCION DE DOCUMENTOS] - Expediente en Curso");
        Util.AplicarEncabezado(this,Encabezado,"UNIDAD_TRAMITE.PNG-64",unidadTramite.toString() ,"Permite Registrar los Documentos que han sido dirigidos a esta Unidad de Trámite");
        Util.HabilitarContenedor(pnlDocumento,true);
        Util.HabilitarContenedor(pnlTipoTramite, true);
        Util.HabilitarContenedor(pnlRequisitos, true);
        Util.HabilitarContenedor(pnlRuta, true);
        Util.HabilitarContenedor(this, true);
        
        mdlRequisitos();
        mdlRutas();
        
    }
    
    private void llenarCasillas(){
        txtExpedienteNumero.setText("000");
        txtExpedienteAnio.setText(FormatoFecha.getFecha(new Date(), "yyyy"));
        txtExpedienteSigla.setText(unidadTramite.getAbreviatura());
        txtExpedienteUnidadOrganizativa.setText(unidadTramite.getUnidadOrganizativa().getAbreviatura());
    }
    
    public void modoNuevo(){
        this.setTitle("[Operaciones - Recepcion de documentos] - Expediente en curso");
        this.modoPantalla="nuevo";
        lblEstado.setVisible(false);
        lblFechaHoraRecepcion.setVisible(true);
        cldFecha.setVisible(true);
        txtHora.setVisible(true);
        btnHora.setVisible(true);
        btnGuardar.setText("Guardar");
        btnCancelar.setText("Cancelar");
        btnGuardar.setVisible(true);
        btnCancelar.setVisible(true);
        llenarCasillas();
  
    }
    
    public void modoVista(DocumentoRecibido documentoRecibidoAConsultar){
        this.setTitle("[Consultas] - Documentos recibidos / Ver detalle");
        this.modoPantalla="vista";
        this.documentoRecibidoAConsultar = documentoRecibidoAConsultar;
        lblEstado.setVisible(true);
        lblFechaHoraRecepcion.setVisible(false);
        cldFecha.setVisible(false);
        txtHora.setVisible(false);
        btnHora.setVisible(false);
        btnGuardar.setText("Guardar");
        btnCancelar.setText("Salir");
        btnGuardar.setVisible(false);
        btnBuscar.setVisible(false);
        
        txtExpedienteAnio.setEditable(false);
        txtExpedienteNumero.setEditable(false);
        txtExpedienteSigla.setEditable(false);
        txtExpedienteUnidadOrganizativa.setEditable(false);

        partirNumExpediente(documentoRecibidoAConsultar.getNumero_expediente());
        buscarVista();
    }
    
    public void modoVistaManual(){
        this.setTitle("[Consultas] - Documentos recibidos / Ver detalle");
        this.modoPantalla="vistaManual";
        txtExpedienteUnidadOrganizativa.setText(SistemaTramiteDocumentario.oUnidadTramite.getUnidadOrganizativa().getAbreviatura());
        txtExpedienteAnio.setEditable(true);
        txtExpedienteNumero.setEditable(true);
        txtExpedienteSigla.setEditable(true);
        txtExpedienteUnidadOrganizativa.setEditable(false);
        
        this.documentoRecibidoAConsultar = documentoRecibidoAConsultar;
        
        lblEstado.setVisible(true);
        lblFechaHoraRecepcion.setVisible(false);
        cldFecha.setVisible(false);
        txtHora.setVisible(false);
        btnHora.setVisible(false);
        btnGuardar.setVisible(false);
        btnCancelar.setText("Salir");
        btnGuardar.setText("Guardar");
        btnCancelar.setText("Cerrar");
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
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDocumento = new javax.swing.JPanel();
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
        lblTipoTramite = new javax.swing.JLabel();
        lblTiempoEstimado = new javax.swing.JLabel();
        lblFechaRespuesta = new javax.swing.JLabel();
        pnlRequisitos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRequisitos = new javax.swing.JTable();
        Encabezado = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtExpedienteNumero = new javax.swing.JTextField();
        txtExpedienteAnio = new javax.swing.JTextField();
        txtExpedienteSigla = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnlRuta = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRuta = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblTrasladoA = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cldFecha = new com.toedter.calendar.JDateChooser();
        txtHora = new javax.swing.JTextField();
        btnHora = new javax.swing.JButton();
        lblFechaHoraRecepcion = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtExpedienteUnidadOrganizativa = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[Operaciones - recepcion de documentos] - Tramite de expediente");
        setResizable(false);

        pnlDocumento.setBorder(javax.swing.BorderFactory.createTitledBorder("Documento"));

        jLabel7.setText("N° de expediente");

        jLabel8.setText("Tipo de documento");

        jLabel9.setText("N° de documento");

        jLabel10.setText("De");

        lblNumExpediente.setForeground(new java.awt.Color(255, 51, 51));
        lblNumExpediente.setText("---");

        lblTIpoDocumento.setText("---");

        lblNumDocumento.setText("---");

        lblDe.setText("---");

        jLabel15.setText("Asunto");

        lblAsunto.setText("---");

        javax.swing.GroupLayout pnlDocumentoLayout = new javax.swing.GroupLayout(pnlDocumento);
        pnlDocumento.setLayout(pnlDocumentoLayout);
        pnlDocumentoLayout.setHorizontalGroup(
            pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDocumentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblNumDocumento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                    .addComponent(lblTIpoDocumento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNumExpediente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(64, 64, 64)
                .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDocumentoLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(250, 250, 250))
                    .addComponent(lblAsunto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        pnlDocumentoLayout.setVerticalGroup(
            pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDocumentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDocumentoLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAsunto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlDocumentoLayout.createSequentialGroup()
                        .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNumExpediente)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblTIpoDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblNumDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addComponent(lblObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
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

        lblTipoTramite.setText("---");

        lblTiempoEstimado.setText("---");

        lblFechaRespuesta.setText("---");

        javax.swing.GroupLayout pnlTipoTramiteLayout = new javax.swing.GroupLayout(pnlTipoTramite);
        pnlTipoTramite.setLayout(pnlTipoTramiteLayout);
        pnlTipoTramiteLayout.setHorizontalGroup(
            pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTipoTramiteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoTramite, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTiempoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlTipoTramiteLayout.setVerticalGroup(
            pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTipoTramiteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTipoTramite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jLabel3.setText("Numero de expediente");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("-");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("-");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("-");

        pnlRuta.setBorder(javax.swing.BorderFactory.createTitledBorder("Ruta"));

        tblRuta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblRuta);

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Tramite realizado");

        jLabel11.setText("Descripcion");

        jLabel12.setText("Traslado a");

        jLabel13.setText("Documento");

        lblDescripcion.setText("---");

        lblTrasladoA.setText("---");

        lblDocumento.setText("---");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTrasladoA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblDescripcion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblTrasladoA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel14.setIcon(new javax.swing.ImageIcon("C:\\STD.UNPRG\\Software-copia\\Iconos\\src\\IconosFinales\\Proceso-16.png")); // NOI18N
        jLabel14.setText("Tramite en proceso");

        jLabel16.setIcon(new javax.swing.ImageIcon("C:\\STD.UNPRG\\Software-copia\\Iconos\\src\\IconosFinales\\confirmar1-20.png")); // NOI18N
        jLabel16.setText("Tramite concluido");

        javax.swing.GroupLayout pnlRutaLayout = new javax.swing.GroupLayout(pnlRuta);
        pnlRuta.setLayout(pnlRutaLayout);
        pnlRutaLayout.setHorizontalGroup(
            pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRutaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRutaLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlRutaLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))))
        );
        pnlRutaLayout.setVerticalGroup(
            pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRutaLayout.createSequentialGroup()
                .addGroup(pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlRutaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnHora.setText("+");
        btnHora.setMaximumSize(new java.awt.Dimension(41, 25));
        btnHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoraActionPerformed(evt);
            }
        });

        lblFechaHoraRecepcion.setText("Fecha y hora de recepcion");

        lblEstado.setText("Estado: ");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(0, 0, 0))
        );

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlRuta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlObservacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTipoTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addComponent(txtExpedienteUnidadOrganizativa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEstado)
                        .addGap(37, 37, 37)
                        .addComponent(lblFechaHoraRecepcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
            .addComponent(Encabezado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtExpedienteNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpedienteAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpedienteSigla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(txtExpedienteUnidadOrganizativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlRequisitos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTipoTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnHora, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(txtHora, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblFechaHoraRecepcion)
                                .addComponent(lblEstado)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void buscarVista(){
        try{
            lblNumExpediente.setForeground(new java.awt.Color(255, 102, 0));
            //consultamos todas las rutas que tenga un expediente siempre y cuando ya aya sido enviado mas de una ves 
            if(modoPantalla.equals("vista") || modoPantalla.equals("vistaManual")){
                DocumentoEmitido documento = new DocumentoEmitido();
                documento.setNumero_expediente(txtExpedienteNumero.getText()+"-"+txtExpedienteAnio.getText()+"-"+txtExpedienteSigla.getText()+"-"+txtExpedienteUnidadOrganizativa.getText());
                documentoEmitidos = documentoEmitidoLN.Consultar1DocRecepcionado(documento);//el documento solo lleva un numero de expediente

                tramites = new ArrayList<>();
                rutas = new ArrayList<>();
                documentoRecibidos = new ArrayList<>();
                    
                for (int i = 0; i < documentoEmitidos.size(); i++) {
                    tramites.add(documentoEmitidos.get(i).getTramite());
                }
                for (int i = 0; i < documentoEmitidos.size(); i++) {
                    rutas.add(tramites.get(i).getRuta());
                }
                for (int i = 0; i < documentoEmitidos.size(); i++) {
                    documentoRecibidos.add(rutas.get(i).getDocumentoRecibido());
                }

                if(documentoEmitidos.size()>0){
                    System.out.println("xxxxxxxxxxxxxxxx");
                    mostrarDocumento();
                    mostrarTipoTramite();
                    mostrarTablaRequisitos();
                    mostrarTablaRutas();
                    mostrarTramiteRealizado();
                    mostrarFechaHoraRecepcion();

                    lblEstado.setText(documentoRecibidos.get(0).getEstado()?"Estado: Concluido":"Estado: En proceso");
  
                } 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El Expediente no existe. Si ya tiene el expediente es posible que la unidad\nde tramite que remite dicho expediente aun no registre el envio", "Error", JOptionPane.WARNING_MESSAGE);
            JOptionPane.showMessageDialog(null,"Error:"+e.getMessage());
        }
    }
    
    public void buscar(){
        try{
            lblNumExpediente.setForeground(new java.awt.Color(255, 102, 0));
            //consultamos todas las rutas que tenga un expediente siempre y cuando ya aya sido enviado mas de una ves 
            if(modoPantalla.equals("nuevo")){
                DocumentoEmitido documento = new DocumentoEmitido();
                documento.setNumero_expediente(txtExpedienteNumero.getText()+"-"+txtExpedienteAnio.getText()+"-"+txtExpedienteSigla.getText()+"-"+txtExpedienteUnidadOrganizativa.getText());
                documentoEmitidos = documentoEmitidoLN.Consultar1DocRecepcionado(documento);//el documento solo lleva un numero de expediente

                tramites = new ArrayList<>();
                rutas = new ArrayList<>();
                documentoRecibidos = new ArrayList<>();
                    
                for (int i = 0; i < documentoEmitidos.size(); i++) {
                    tramites.add(documentoEmitidos.get(i).getTramite());
                }
                for (int i = 0; i < documentoEmitidos.size(); i++) {
                    rutas.add(tramites.get(i).getRuta());
                }
                for (int i = 0; i < documentoEmitidos.size(); i++) {
                    documentoRecibidos.add(rutas.get(i).getDocumentoRecibido());
                }

                if(documentoEmitidos.size()>0){
                    if(rutas.get(rutas.size()-1).getUnidadTramite().getId_unidad_tramite() == SistemaTramiteDocumentario.oUnidadTramite.getId_unidad_tramite()){
                            JOptionPane.showMessageDialog(null, "El expdiente no se encuentra en esta unidad");
                    }else{
                        mostrarDocumento();
                        mostrarTipoTramite();
                        mostrarTablaRequisitos();
                        mostrarTablaRutas();
                        mostrarTramiteRealizado();
                        mostrarFechaHoraRecepcion();
                        lblEstado.setText(documentoRecibidos.get(0).getEstado()?"Estado: Concluido":"Estado: En proceso");
                    }
                } 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El Expediente no existe. Si ya tiene el expediente es posible que la unidad\nde tramite que remite dicho expediente aun no registre el envio", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void mostrarDocumento() {
        try{
            lblNumExpediente.setText(documentoRecibidos.get(0).getNumero_expediente());
            lblTIpoDocumento.setText(documentoRecibidos.get(0).getTipoDocumento().getNombre());
            lblNumDocumento.setText(documentoRecibidos.get(0).getNumero());
            lblDe.setText(documentoRecibidos.get(0).getDe());
            lblAsunto.setText(documentoRecibidos.get(0).getAsunto());
            lblObservacion.setText(documentoRecibidos.get(0).getObservacion());
            
            //solo para modo vista
            if(this.modoPantalla.equals("vista")){
                if(documentoRecibidos.get(0).getEstado()){
                    lblEstado.setText(lblEstado.getText()+"CONCLUIDO");
                    lblEstado.setVisible(true);
                }else{
                    lblEstado.setText(lblEstado.getText()+"EN PROCESO");
                    lblEstado.setVisible(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }   
    
    private void mostrarTipoTramite() {                                          
        try{
            DocRecLN docRecLN = new DocRecLN();
            List<DocRec> docRecs = docRecLN.ConsultarAll(documentoRecibidos.get(0));
            
            if(docRecs.size()!=0){
                lblTipoTramite.setText(docRecs.get(0).getRequisito().getTipoTramite().getNombre());
                lblTiempoEstimado.setText(Integer.toString(docRecs.get(0).getRequisito().getTipoTramite().getTiempo_estimado()));
                lblFechaRespuesta.setText(rutas.get(0).getFecha_respuesta().toString());
            }else{
                lblTipoTramite.setText("OTRO TIPO DE TRAMITE");
                lblTiempoEstimado.setText("desconocido");
                lblFechaRespuesta.setText("desconocido");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    } 
    
    public final void mdlRequisitos(){
        String Columnas[] = {"N°","Nombre"};
        
        tblRequisitos.setModel(new mdlGeneral(Columnas));
        
        Integer[] Anchos = {50,400};
        Integer[] Alineaciones = {JLabel.CENTER,JLabel.LEFT};
        String[] Formatos = {"Cadena","Cadena"};
        String[] Modos = {"Normal","Normal"};
        
        Util.AplicarEstilos(tblRequisitos, Anchos, Alineaciones, Formatos, Modos);
    }
    
    private List parseVectorRequisitos(List<DocRec> lista) {
        List datos = new ArrayList();
        Object[] newdata;

        for(int i = 0; i < lista.size(); i++) {
            newdata = new Object[2];

            newdata[0] = lista.get(i).getRequisito().getEstado()?"Si":"No";
            newdata[1] = lista.get(i).getRequisito().getDescipcion();

            datos.add(newdata);
        }

        return datos;
    }
    
    private void mostrarTablaRequisitos() {                                          
        try{
            DocRecLN docRecLN = new DocRecLN();
            List<DocRec> docRecs = docRecLN.ConsultarAll(documentoRecibidos.get(0));//tiene objetos de tipo requisito y documentoRecibido
            ((mdlGeneral)tblRequisitos.getModel()).setData(parseVectorRequisitos(docRecs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    } 
    
   public final void mdlRutas(){
        String Columnas[] = {"||","Fecha de Recepción","Recepcionado"};
        
        mdlGeneral mdl = new mdlGeneral(Columnas);
        tblRuta.setModel(mdl);
        
        Integer[] anchos = {50,150,350};
        Integer[] alineaciones = {JLabel.CENTER,JLabel.CENTER,JLabel.LEFT};
        String[] formatos = {"Imagen","Cadena","Cadena"};
        String[] modos = {"Normal","Normal","Normal"};

        Util.AplicarEstilos(tblRuta,anchos,alineaciones,formatos,modos);

    }
    
    private List parseVectorRutas(List<Ruta> lista) {
        List datos = new ArrayList();
        Object[] newdata;

        for(int i = 0; i < lista.size(); i++) {
            newdata = new Object[3];
            newdata[0] = lista.get(i).getDocumentoRecibido().getEstado()?"Confirmar1-20":"Proceso-16";
            newdata[1] = lista.get(i).getFechaHora_recepcion();
            newdata[2] = lista.get(i).getUnidadTramite().toString();
            datos.add(newdata);
        }

        return datos;
    }
    
    private void mostrarTablaRutas() {                                          
        try {
            ((mdlGeneral)tblRuta.getModel()).setData(parseVectorRutas(rutas));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    } 
    
    public void mostrarTramiteRealizado(){
        try {
            //se muestran los datos del ultimo tramite que se a echo a ese expediente
            lblDescripcion.setText(tramites.get(tramites.size()-1).getDescripcion());
            lblTrasladoA.setText(documentoEmitidos.get(tramites.size()-1).getPara());
            lblDocumento.setText(documentoEmitidos.get(tramites.size()-1).getTipoDocumento().getNombre()+"/"+documentoEmitidos.get(tramites.size()-1).getNumero()+"/"+documentoEmitidos.get(tramites.size()-1).getFecha_recepcion());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void mostrarFechaHoraRecepcion(){
        try {
            
            Date date = documentoEmitidoLN.ConsultarFechaBD();
            cldFecha.setDate(date);
            
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
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try { 
            if(datosOK()){
                if(JOptionPane.showConfirmDialog(null, "¿Registrar tramite?","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    RutaLN rutaLN = new RutaLN();
                    Ruta objeto = new Ruta();
                    Date dateRecepcion = new Date(cldFecha.getDate().getYear(), cldFecha.getDate().getMonth(), cldFecha.getDate().getDate(),Integer.parseInt(txtHora.getText().substring(0,2)),Integer.parseInt(txtHora.getText().substring(3,5)) , Integer.parseInt(txtHora.getText().substring(6,8)));
                    objeto.setTipo_expediente(rutas.get(0).getTipo_expediente());
                    objeto.setUnidadTramite(unidadTramite);
                    objeto.setDocumentoRecibido(documentoRecibidos.get(0));
                    objeto.setFechaHora_recepcion(dateRecepcion);
                    objeto.setFecha_respuesta(rutas.get(0).getFecha_respuesta());
                    rutaLN.Insertar(objeto);
                    JOptionPane.showMessageDialog(null, "Registro exitoso!", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    this.dispose();
                } 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    public boolean datosOK(){
        boolean estado = true;
        if(documentoEmitidos==null){
            estado =false;
            JOptionPane.showMessageDialog(null, "No hay ningun expediente", "Error", JOptionPane.WARNING_MESSAGE);
        }else if(cldFecha.getDate()==null){
            estado = false;
            JOptionPane.showMessageDialog(null, "Fecha incorrecta", "Error", JOptionPane.WARNING_MESSAGE);
        }
        return estado;
    }
    
    private void btnHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoraActionPerformed
        if(documentoRecibidos.get(0)!=null){
            Reloj reloj  =new Reloj(null, true,txtHora);
            reloj.setVisible(true);
        }
    }//GEN-LAST:event_btnHoraActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if(JOptionPane.showConfirmDialog(null, "¿Desea Cancelar el Proceso?","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
             this.dispose();
         }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if(modoPantalla.equals("nuevo")){
            buscar();
        }else if (modoPantalla.equals("vistaManual")){
            buscarVista();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Encabezado;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHora;
    private com.toedter.calendar.JDateChooser cldFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAsunto;
    private javax.swing.JLabel lblDe;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaHoraRecepcion;
    private javax.swing.JLabel lblFechaRespuesta;
    private javax.swing.JLabel lblNumDocumento;
    private javax.swing.JLabel lblNumExpediente;
    private javax.swing.JLabel lblObservacion;
    private javax.swing.JLabel lblTIpoDocumento;
    private javax.swing.JLabel lblTiempoEstimado;
    private javax.swing.JLabel lblTipoTramite;
    private javax.swing.JLabel lblTrasladoA;
    private javax.swing.JPanel pnlDocumento;
    private javax.swing.JPanel pnlObservacion;
    private javax.swing.JPanel pnlRequisitos;
    private javax.swing.JPanel pnlRuta;
    private javax.swing.JPanel pnlTipoTramite;
    private javax.swing.JTable tblRequisitos;
    private javax.swing.JTable tblRuta;
    private javax.swing.JTextField txtExpedienteAnio;
    private javax.swing.JTextField txtExpedienteNumero;
    private javax.swing.JTextField txtExpedienteSigla;
    private javax.swing.JTextField txtExpedienteUnidadOrganizativa;
    private javax.swing.JTextField txtHora;
    // End of variables declaration//GEN-END:variables
}
