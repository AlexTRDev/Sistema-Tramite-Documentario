package GestionTramiteDocumentos.Presentacion;
import GestionTramiteDocumentos.Entidades.DocumentoEmitido;
import GestionTramiteDocumentos.Entidades.DocumentoRecibido;
import GestionTramiteDocumentos.Entidades.Indicador;
import GestionTramiteDocumentos.Entidades.Ruta;
import GestionTramiteDocumentos.Entidades.TipoTramite;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import GestionTramiteDocumentos.LogicaNegocio.IndicadoresLN;
import GestionTramiteDocumentos.LogicaNegocio.TipoTramiteLN;
import GestionTramiteDocumentos.LogicaNegocio.UnidadTramiteLN;
import Util.Util;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class Inidicador extends javax.swing.JDialog {
    
    UnidadTramiteLN unidadTramiteLN = new UnidadTramiteLN();
    IndicadoresLN indicadoresLN = new IndicadoresLN();
    TipoTramiteLN tipoTramiteLN = new TipoTramiteLN();
    
    DefaultComboBoxModel  mdlCbxUnidadTramite, mdlCbxTipoTramite;
    public Inidicador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[INDICADORES DE GESTIÓN]");
        Util.HabilitarContenedor(pnlEmitidos, false);
        Util.HabilitarContenedor(pnlRecibidos,false);
        Util.HabilitarContenedor(pnlDuracionTramite,false);
        cbxTipoTramite.setEnabled(true);
        
        colores();
    }
    
    private void colores(){
        lblAnio.setForeground(new java.awt.Color(255, 102, 0));
        lblMes.setForeground(new java.awt.Color(255, 102, 0));
        lblUnidadTramite.setForeground(new java.awt.Color(255, 102, 0));
    }
    String modoPantalla;
    public void modoGeneral(){
        this.setTitle("[Consultas -  Indicadores de gestion] - General");
        modoPantalla = "general";
        mostrarCbxUnidadTramite(true);
        mostrarCbxTipoTramite();
    }
    
    public void modoPorUnidad(){
        this.setTitle("[Consultas -  Indicadores de gestion] - Por unidad de tramtie");
        modoPantalla = "porUnidad";
        mostrarCbxUnidadTramite(false);
        mostrarCbxTipoTramite();
    }
    
    public final void mostrarCbxTipoTramite(){
        try {
            List<TipoTramite> tipoTramites = tipoTramiteLN.ConsultarAll(((UnidadTramite)cbxUnidadTramite.getSelectedItem()));
            if(tipoTramites.size()==0){
                tipoTramites.add(new TipoTramite(0, "VACIA", 0, null, null));
            }
            mdlCbxTipoTramite = new DefaultComboBoxModel(tipoTramites.toArray());
            cbxTipoTramite.setModel(mdlCbxTipoTramite);
            
            txtDuracionEstimado.setText(Integer.toString(((TipoTramite)cbxTipoTramite.getSelectedItem()).getTiempo_estimado()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblUnidad = new javax.swing.JLabel();
        cbxUnidadTramite = new javax.swing.JComboBox();
        lblUnidadCopia = new javax.swing.JLabel();
        lblUnidadTramite = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblAnio = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblMes = new javax.swing.JLabel();
        cbxMes = new javax.swing.JComboBox();
        pnlDuracionTramite = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxTipoTramite = new javax.swing.JComboBox();
        pnlDuracionRealTramite = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDuracionMax = new javax.swing.JTextField();
        txtDuracionMin = new javax.swing.JTextField();
        txtDuracionAvg = new javax.swing.JTextField();
        txtDuracionEstimado = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        pnlEmitidos = new javax.swing.JPanel();
        pnlDocumentosEmitidos = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDocumentoEnviadoMax = new javax.swing.JTextField();
        txtDocumentoEnviadoMin = new javax.swing.JTextField();
        txtDocumentoEnviadoPromedio = new javax.swing.JTextField();
        pnlRecibidos = new javax.swing.JPanel();
        pnlDocumentosRecibidos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDocumentoRecibidoMax = new javax.swing.JTextField();
        txtDocumentoRecibidoMin = new javax.swing.JTextField();
        txtDocumentoRecibidoPromedio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[Consultas - Indicadores de gestion] - General");

        jLabel3.setText("Año");

        jLabel4.setText("Mes");

        lblUnidad.setText("Unidad de tramite");

        cbxUnidadTramite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUnidadTramite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUnidadTramiteActionPerformed(evt);
            }
        });

        lblUnidadCopia.setText("Unidad de tramite");

        lblUnidadTramite.setForeground(new java.awt.Color(255, 102, 0));
        lblUnidadTramite.setText("---");
        lblUnidadTramite.setOpaque(true);

        jLabel8.setText("Año");

        lblAnio.setForeground(new java.awt.Color(255, 102, 0));
        lblAnio.setText("---");

        jLabel10.setText("Mes");

        lblMes.setForeground(new java.awt.Color(255, 102, 0));
        lblMes.setText("---");

        cbxMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ENERO", "FEFRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        cbxMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMesActionPerformed(evt);
            }
        });

        pnlDuracionTramite.setBorder(javax.swing.BorderFactory.createTitledBorder("Duracion de tramite"));

        jLabel1.setText("Tipo de Trámite");

        cbxTipoTramite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTipoTramite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoTramiteActionPerformed(evt);
            }
        });

        pnlDuracionRealTramite.setBackground(new java.awt.Color(204, 204, 255));
        pnlDuracionRealTramite.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setText("Maximo    :");

        jLabel19.setText("Minimo     :");

        jLabel20.setText("Promedio :");

        txtDuracionMax.setEnabled(false);

        txtDuracionMin.setEnabled(false);

        txtDuracionAvg.setEnabled(false);

        txtDuracionEstimado.setForeground(new java.awt.Color(0, 204, 51));
        txtDuracionEstimado.setEnabled(false);

        jLabel11.setText("Estimado  :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDuracionMin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDuracionMax, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDuracionAvg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDuracionEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtDuracionEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtDuracionMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtDuracionMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtDuracionAvg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDuracionTramiteLayout = new javax.swing.GroupLayout(pnlDuracionTramite);
        pnlDuracionTramite.setLayout(pnlDuracionTramiteLayout);
        pnlDuracionTramiteLayout.setHorizontalGroup(
            pnlDuracionTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDuracionTramiteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDuracionTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlDuracionTramiteLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDuracionRealTramite, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxTipoTramite, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDuracionTramiteLayout.setVerticalGroup(
            pnlDuracionTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDuracionTramiteLayout.createSequentialGroup()
                .addGroup(pnlDuracionTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxTipoTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDuracionTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDuracionRealTramite, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81))
        );

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ver detalles");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        pnlEmitidos.setBorder(javax.swing.BorderFactory.createTitledBorder("Documentos emitidos"));
        pnlEmitidos.setPreferredSize(new java.awt.Dimension(400, 300));
        pnlEmitidos.setLayout(null);

        pnlDocumentosEmitidos.setBackground(new java.awt.Color(204, 204, 255));
        pnlDocumentosEmitidos.setLayout(new java.awt.BorderLayout());
        pnlEmitidos.add(pnlDocumentosEmitidos);
        pnlDocumentosEmitidos.setBounds(16, 16, 440, 232);

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("Maximo    :");

        jLabel17.setText("Minimo     :");

        jLabel18.setText("Promedio :");

        txtDocumentoEnviadoMax.setEnabled(false);

        txtDocumentoEnviadoMin.setEnabled(false);

        txtDocumentoEnviadoPromedio.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDocumentoEnviadoMax, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDocumentoEnviadoMin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDocumentoEnviadoPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel7)
                    .addComponent(txtDocumentoEnviadoMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDocumentoEnviadoMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDocumentoEnviadoPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        pnlEmitidos.add(jPanel2);
        jPanel2.setBounds(16, 257, 438, 30);

        pnlRecibidos.setBorder(javax.swing.BorderFactory.createTitledBorder("Documentos recibidos"));
        pnlRecibidos.setPreferredSize(new java.awt.Dimension(400, 300));
        pnlRecibidos.setLayout(null);

        pnlDocumentosRecibidos.setBackground(new java.awt.Color(204, 204, 255));
        pnlDocumentosRecibidos.setEnabled(false);
        pnlDocumentosRecibidos.setLayout(new java.awt.BorderLayout());
        pnlRecibidos.add(pnlDocumentosRecibidos);
        pnlDocumentosRecibidos.setBounds(16, 16, 450, 236);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Maximo    :");

        jLabel12.setText("Minimo     :");

        jLabel13.setText("Promedio :");

        txtDocumentoRecibidoMax.setEnabled(false);

        txtDocumentoRecibidoMin.setEnabled(false);

        txtDocumentoRecibidoPromedio.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(txtDocumentoRecibidoMax, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(txtDocumentoRecibidoMin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(10, 10, 10)
                .addComponent(txtDocumentoRecibidoPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtDocumentoRecibidoMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txtDocumentoRecibidoMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txtDocumentoRecibidoPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)))
        );

        pnlRecibidos.add(jPanel1);
        jPanel1.setBounds(20, 260, 440, 30);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRecibidos, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(pnlEmitidos, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlEmitidos, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(pnlRecibidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUnidadCopia)
                                .addGap(10, 10, 10)
                                .addComponent(lblUnidadTramite, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addGap(14, 14, 14)
                                .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUnidad)
                                .addGap(10, 10, 10)
                                .addComponent(cbxUnidadTramite, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(14, 14, 14)
                                .addComponent(cbxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))))
                    .addComponent(pnlDuracionTramite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblUnidad))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cbxUnidadTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUnidadCopia)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblUnidadTramite)
                                .addComponent(jLabel8)
                                .addComponent(lblAnio))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMes)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDuracionTramite, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            
            lblUnidadTramite.setText(((UnidadTramite)cbxUnidadTramite.getSelectedItem()).getNombre());
            lblAnio.setText(txtAnio.getText());
            lblMes.setText(cbxMes.getSelectedItem().toString());

            Indicador parametros = new Indicador(((UnidadTramite)cbxUnidadTramite.getSelectedItem()).getId_unidad_tramite(), txtAnio.getText(), getNumeroMes(cbxMes.getSelectedItem().toString()), null,null);
            
            List<Ruta> documentosRecibidos = indicadoresLN.ConsultarDocumentosRecibidos(parametros);
            mostrarGrafica1(documentosRecibidos);
            
            List<DocumentoEmitido> documentoEmitidos = indicadoresLN.ConsultarDocumentosEnviados(parametros);
            mostrarGrafica2(documentoEmitidos);
            
            mostrarGrafica3();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void cbxMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMesActionPerformed
        
    }//GEN-LAST:event_cbxMesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxUnidadTramiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUnidadTramiteActionPerformed
        mostrarCbxTipoTramite();
    }//GEN-LAST:event_cbxUnidadTramiteActionPerformed

    private void cbxTipoTramiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoTramiteActionPerformed
        txtDuracionEstimado.setText(Integer.toString(((TipoTramite)cbxTipoTramite.getSelectedItem()).getTiempo_estimado()));
        mostrarGrafica3();
    }//GEN-LAST:event_cbxTipoTramiteActionPerformed

    public void mostrarGrafica1(List<Ruta> ruta){
        Integer[] datos = new Integer[ruta.size()];
        Integer[] dias = new Integer[31];
        Integer[] numero = new Integer[31];
        
        for (int i = 0; i < ruta.size(); i++) {
            datos[i]=ruta.get(i).getFechaHora_recepcion().getDate();
        }

        for (int i = 0; i < dias.length; i++) {
            dias[i]=(i+1);
        }
        
        for (int i = 0; i < dias.length; i++) {
            int cont =0;
            for (int j = 0; j < datos.length; j++) {
                if(dias[i]==datos[j]){
                    cont++;
                }
            }
            numero[i]=cont;
        }
        
        txtDocumentoRecibidoMax.setText(Integer.toString(arrayMax(numero)));
        txtDocumentoRecibidoMin.setText(Integer.toString(arrayMin(numero)));
        txtDocumentoRecibidoPromedio.setText(Double.toString((double)arrayPromedio(arrayMin(numero),(double)arrayMax(numero))));
        
        Graficas grafica1 = new Graficas(Graficas.LINEAL, "", "dias", "N° de documentos");
        grafica1.setParametros("", dias, numero);
        pnlDocumentosRecibidos.removeAll();
        pnlDocumentosRecibidos.add(grafica1.getPanel(), BorderLayout.CENTER);
        pnlDocumentosRecibidos.validate();
    }
    
    public void mostrarGrafica2(List<DocumentoEmitido> documentoEmitidos){
        Integer[] datos = new Integer[documentoEmitidos.size()];
        Integer[] dias = new Integer[31];
        Integer[] numero = new Integer[31];
        
        for (int i = 0; i < documentoEmitidos.size(); i++) {
            datos[i]=documentoEmitidos.get(i).getFecha_emision().getDate();
        }

        for (int i = 0; i < dias.length; i++) {
            dias[i]=(i+1);
        }
        
        for (int i = 0; i < dias.length; i++) {
            int cont =0;
            for (int j = 0; j < datos.length; j++) {
                if(dias[i]==datos[j]){
                    cont++;
                }
            }
            numero[i]=cont;
        }
        
        txtDocumentoEnviadoMax.setText(Integer.toString(arrayMax(numero)));
        txtDocumentoEnviadoMin.setText(Integer.toString(arrayMin(numero)));
        txtDocumentoEnviadoPromedio.setText(Double.toString((double)arrayPromedio(arrayMin(numero),(double)arrayMax(numero))));
        
        Graficas grafica2 = new Graficas(Graficas.LINEAL, "", "dias", "N° de documentos");
        grafica2.setParametros("", dias, numero);
        pnlDocumentosEmitidos.removeAll();
        pnlDocumentosEmitidos.add(grafica2.getPanel(), BorderLayout.CENTER);
        pnlDocumentosEmitidos.validate();
    }
    
    public void mostrarGrafica3(){
        try {
            Indicador indicador = new Indicador();
            indicador.setTipoTramite(((TipoTramite)cbxTipoTramite.getSelectedItem()).getNombre());
            indicador.setUnidadTramite((UnidadTramite)cbxUnidadTramite.getSelectedItem());
            indicador.setMes(getNumeroMes(cbxMes.getSelectedItem().toString()));
            List<DocumentoRecibido> documentoRecibidos = indicadoresLN.ConsultarDocumentosRecibidosFinalizados(indicador);

            Integer[] x = new Integer[documentoRecibidos.size()];
            Integer[] y = new Integer[documentoRecibidos.size()];
            
            for (int i = 0; i < documentoRecibidos.size(); i++) {
                x[i]=(i+1);
            }
            
            for (int i = 0; i < documentoRecibidos.size(); i++) {
                Double n = indicadoresLN.calcularDuracionReal(documentoRecibidos.get(i));
                y[i]=n.intValue();
            }
            
            Graficas g3 = new Graficas(Graficas.LINEAL, "", "documento", "dias");
            g3.setParametros("", x, y);
            pnlDuracionRealTramite.removeAll();
            pnlDuracionRealTramite.add(g3.getPanel(), BorderLayout.CENTER);
            pnlDuracionRealTramite.validate();     
            
            txtDuracionEstimado.setText(Integer.toString(((TipoTramite)cbxTipoTramite.getSelectedItem()).getTiempo_estimado()));
            
            if(x.length==0 || y.length==0){
                txtDuracionMax.setText("0");
                txtDuracionMin.setText("0");
                txtDuracionAvg.setText("0");
            }else{
                txtDuracionMax.setText(Integer.toString(arrayMax(y)));
                txtDuracionMin.setText(Integer.toString(arrayMin(y)));
                Double promedio = (Double.parseDouble(txtDuracionMax.getText()) + Double.parseDouble(txtDuracionMin.getText()))/2;
                txtDuracionAvg.setText(Double.toString(promedio));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public final void mostrarCbxUnidadTramite(boolean general){
        try {
            if(general){
                List<UnidadTramite> unidadTramites =unidadTramiteLN.ConsultarAll(SistemaTramiteDocumentario.oUnidadTramite.getUnidadOrganizativa());
                mdlCbxUnidadTramite = new DefaultComboBoxModel(unidadTramites.toArray());
                cbxUnidadTramite.setModel(mdlCbxUnidadTramite);
            }else{
                List<UnidadTramite> unidadTramites = new ArrayList<>();
                unidadTramites.add(SistemaTramiteDocumentario.oUnidadTramite);
                mdlCbxUnidadTramite = new DefaultComboBoxModel(unidadTramites.toArray());
                cbxUnidadTramite.setModel(mdlCbxUnidadTramite);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public String getNumeroMes(String mes){
        String numero = "";
        switch(mes){
            case "ENERO":
                numero = "01";
                break;
            case "FEFRERO":
                numero = "02";
                break;
            case "MARZO":
                numero = "03";
                break;
            case "ABRIL":
                numero = "04";
                break;
            case "MAYO":
                numero = "05";
                break;
            case "JUNIO":
                numero = "06";
                break;
            case "JULIO":
                numero = "07";
                break;
            case "AGOSTO":
                numero = "08";
                break;
            case "SEPTIEMBRE":
                numero = "09";
                break;
            case "OCTUBRE":
                numero = "10";
                break;
            case "NOVIEMBRE":
                numero = "11";
                break;
            case "DICIEMBRE":
                numero = "12";
                break;
        }
        return numero;
    }
    
    public int arrayMax(Integer[] array){
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if(max<array[i]){
                max=array[i];
            }
        }
        return max;
    }
    
    public int arrayMin(Integer[] array){
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if(min>array[i]){
                min=array[i];
            }
        }
        return min;
    }
    
    public double arrayPromedio(double min, double max){
        return (min+max)/2;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbxMes;
    private javax.swing.JComboBox cbxTipoTramite;
    private javax.swing.JComboBox cbxUnidadTramite;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblUnidad;
    private javax.swing.JLabel lblUnidadCopia;
    private javax.swing.JLabel lblUnidadTramite;
    public javax.swing.JPanel pnlDocumentosEmitidos;
    private javax.swing.JPanel pnlDocumentosRecibidos;
    public javax.swing.JPanel pnlDuracionRealTramite;
    private javax.swing.JPanel pnlDuracionTramite;
    private javax.swing.JPanel pnlEmitidos;
    private javax.swing.JPanel pnlRecibidos;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtDocumentoEnviadoMax;
    private javax.swing.JTextField txtDocumentoEnviadoMin;
    private javax.swing.JTextField txtDocumentoEnviadoPromedio;
    private javax.swing.JTextField txtDocumentoRecibidoMax;
    private javax.swing.JTextField txtDocumentoRecibidoMin;
    private javax.swing.JTextField txtDocumentoRecibidoPromedio;
    private javax.swing.JTextField txtDuracionAvg;
    private javax.swing.JTextField txtDuracionEstimado;
    private javax.swing.JTextField txtDuracionMax;
    private javax.swing.JTextField txtDuracionMin;
    // End of variables declaration//GEN-END:variables
}
