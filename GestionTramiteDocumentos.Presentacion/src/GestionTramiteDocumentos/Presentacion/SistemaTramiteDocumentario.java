package GestionTramiteDocumentos.Presentacion;

import GestionTramiteDocumentos.Entidades.Permiso;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import GestionTramiteDocumentos.Entidades.Usuario;
import GestionTramiteDocumentos.LogicaNegocio.PermisoLN;
import GestionTramiteDocumentos.LogicaNegocio.UnidadOrganizativaLN;
import GestionTramiteDocumentos.LogicaNegocio.UnidadTramiteLN;
import GestionTramiteDocumentos.LogicaNegocio.UsuarioLN;
import Util.FormatoFecha;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.edisoncor.gui.util.Avatar;

public class SistemaTramiteDocumentario extends javax.swing.JFrame {

    public static Usuario oUsuario;
    public static UnidadTramite oUnidadTramite;
    public static Permiso oPermiso;
    UsuarioLN usuarioLN = new UsuarioLN();
    PermisoLN permisoLN = new PermisoLN();
    UnidadTramiteLN unidadTramiteLN = new UnidadTramiteLN();
    UnidadOrganizativaLN unidadOrganizativaLN = new UnidadOrganizativaLN();
    
    public SistemaTramiteDocumentario() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        llenarMenu();
        bloquearComponentes();
    }

    
    public void bloquearComponentes(){
        mnuInicioTramite.setVisible(false);
    }
    
    public void llenarMenu(){
        List<Avatar> avatars=new ArrayList<Avatar>();
        avatars.add(new Avatar("Nuevo", loadImage("/img/Nuevo.png")));
        avatars.add(new Avatar("En Curso", loadImage("/img/Curso.png")));
        avatars.add(new Avatar("Tramite", loadImage("/img/Tramite.png")));
        avatars.add(new Avatar("Emitidos", loadImage("/img/BuscarDOc.png")));
        avatars.add(new Avatar("Recibidos", loadImage("/img/Buscar.png")));
        avatars.add(new Avatar("Salir", loadImage("/img/Exit.png")));
        menu.setAvatars(avatars);
    }
    
    public static Image loadImage(String fileName){
        try {
            return ImageIO.read(SistemaTramiteDocumentario.class.getResource(fileName));
        }
        catch (Exception e) {
            return null;
        }
    }
    
//    public void llamarMenu(){
//        if(menu.getSelectedtitulo().equals("Menu1")){
//            new ventanaMenu1().setVisible(true);
//        }
//        if(menu.getSelectedtitulo().equals("Salir")){
//            System.exit(0);
//        }
//    }
    
    public boolean isAdministrador(){
        return oUsuario.getCuenta().equals("ADM");
    }

    public void setDatosUsuario(Usuario oUsuario){
        this.oUsuario = oUsuario;
        lblUsuario.setText(oUsuario.getCuenta());
        llenarCmbPermisos();
        //--informacion de usuario:
        pnlFoto.setIcon(null);
        lblNombre.setText(oUsuario.getNombre());
        lblApellidos.setText(oUsuario.getApellido_paterno()+" "+oUsuario.getApellido_materno());
        lblApellidos1.setText(oUsuario.getTipo());
        
        cargarFoto(oUsuario.getRutaImagen());
    }
    
    public void cargarFoto(String ruta){
        lblFoto.setText(null);
        if(ruta != null){
            File file = new File(ruta);
            if(file.exists()){
                ImageIcon imagen = new ImageIcon(ruta);
                Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
                lblFoto.setIcon(icon);
            }else{
                URL url = this.getClass().getResource("/Iconos/anonimo.jpg");
                System.out.println(url);
                ImageIcon imagen = new ImageIcon(url);
                Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
                lblFoto.setIcon(icon);
            }
        }else{
            URL url = this.getClass().getResource("/Iconos/anonimo.jpg");
            System.out.println(url);
            ImageIcon imagen = new ImageIcon(url);
            Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
            lblFoto.setIcon(icon);
        }
    }
    public void llenarCmbPermisos(){
        try {
            Permiso primeraOpcion = new Permiso();
            List<Permiso> listaPermisos = new ArrayList<Permiso>();
            List<UnidadTramite> listaUTramites = new ArrayList<UnidadTramite>();
            DefaultComboBoxModel modeloCbx;
            
            if (isAdministrador()) {
                listaUTramites = unidadTramiteLN.ConsultarAll();
                modeloCbx = new DefaultComboBoxModel(listaUTramites.toArray());
                cbxPermisos.setModel(modeloCbx);
                cbxPermisos.setSelectedIndex(0);
                oUnidadTramite = (UnidadTramite) cbxPermisos.getSelectedItem();
            }else{
                switch(SistemaTramiteDocumentario.oUsuario.getTipo()){
                    case "OUT": ItemUsuarios.setVisible(false);
                                ItemRequisitos.setVisible(false);
                                ItemTiposDocumentos.setVisible(false);
                                ItemRequisitos.setVisible(false);
                                ItemUTramites.setVisible(false);
                                ItemUOrganizativas.setVisible(false);
                        break;
                    case "JUT": 
                    case "JUO": 
                        break;
                }
                listaPermisos = permisoLN.ConsultarAll(oUsuario);
                modeloCbx = new DefaultComboBoxModel(listaPermisos.toArray());
                cbxPermisos.setModel(modeloCbx);
//                cbxPermisos.setSelectedIndex(0);
                oPermiso = (Permiso) cbxPermisos.getSelectedItem();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(SistemaTramiteDocumentario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class mostrarFecha implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                
                String fechaHora = FormatoFecha.getFecha(usuarioLN.getFechaBD(), "dd/MM/yyyy || HH:mm:ss");
                
                lblFechaHora.setText(fechaHora);
                
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }


    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblFechaHora = new javax.swing.JLabel();
        cbxPermisos = new javax.swing.JComboBox();
        menu = new org.edisoncor.gui.panel.PanelAvatarChooser();
        buttonIpod1 = new org.edisoncor.gui.button.ButtonIpod();
        lblFondo = new javax.swing.JLabel();
        panel1 = new org.edisoncor.gui.panel.Panel();
        panelCurves1 = new org.edisoncor.gui.panel.PanelCurves();
        pnlFoto = new org.edisoncor.gui.panel.PanelRectTranslucido();
        lblFoto = new javax.swing.JLabel();
        labelMetric1 = new org.edisoncor.gui.label.LabelMetric();
        lblNombre = new org.edisoncor.gui.label.LabelMetric();
        labelMetric3 = new org.edisoncor.gui.label.LabelMetric();
        lblApellidos = new org.edisoncor.gui.label.LabelMetric();
        lblApellidos1 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric4 = new org.edisoncor.gui.label.LabelMetric();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        ItemUOrganizativas = new javax.swing.JMenuItem();
        ItemUTramites = new javax.swing.JMenuItem();
        ItemTiposDocumentos = new javax.swing.JMenuItem();
        ItemTiposTramites = new javax.swing.JMenuItem();
        ItemRequisitos = new javax.swing.JMenuItem();
        ItemUsuarios = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        mnuInicioTramite = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEMA DE TRAMITE DOCUMENTARIO");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelMenu2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("USUARIO :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("UNIDAD DE TRAMITE : ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("FECHA Y HORA :");

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N

        lblFechaHora.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblFechaHora.setToolTipText("");

        cbxPermisos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cbxPermisos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxPermisos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxPermisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPermisosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenu2Layout = new javax.swing.GroupLayout(panelMenu2);
        panelMenu2.setLayout(panelMenu2Layout);
        panelMenu2Layout.setHorizontalGroup(
            panelMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        panelMenu2Layout.setVerticalGroup(
            panelMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenu2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenu2Layout.createSequentialGroup()
                        .addComponent(cbxPermisos)
                        .addContainerGap())
                    .addGroup(panelMenu2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(panelMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFechaHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenu2Layout.createSequentialGroup()
                        .addGroup(panelMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        menu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menuKeyPressed(evt);
            }
        });
        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonIpod1.setText("...");
        buttonIpod1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIpod1ActionPerformed(evt);
            }
        });
        menu.add(buttonIpod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 130, 150));

        lblFondo.setBackground(new java.awt.Color(255, 255, 51));
        lblFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fotoMadera.jpg"))); // NOI18N
        lblFondo.setOpaque(true);
        menu.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1090, 640));

        lblFoto.setForeground(new java.awt.Color(255, 255, 255));
        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setText("[Foto]");

        javax.swing.GroupLayout pnlFotoLayout = new javax.swing.GroupLayout(pnlFoto);
        pnlFoto.setLayout(pnlFotoLayout);
        pnlFotoLayout.setHorizontalGroup(
            pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlFotoLayout.setVerticalGroup(
            pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );

        labelMetric1.setText("Nombre");
        labelMetric1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        lblNombre.setText("---");
        lblNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        labelMetric3.setText("Apellidos");
        labelMetric3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        lblApellidos.setText("---");
        lblApellidos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblApellidos1.setText("---");
        lblApellidos1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        labelMetric4.setText("Tipo");
        labelMetric4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout panelCurves1Layout = new javax.swing.GroupLayout(panelCurves1);
        panelCurves1.setLayout(panelCurves1Layout);
        panelCurves1Layout.setHorizontalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves1Layout.createSequentialGroup()
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCurves1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(pnlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 29, Short.MAX_VALUE))
                    .addGroup(panelCurves1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblApellidos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblApellidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelCurves1Layout.createSequentialGroup()
                                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelMetric4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelMetric3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panelCurves1Layout.setVerticalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(pnlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(labelMetric3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(labelMetric4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblApellidos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(337, 35));

        jMenu1.setText("Mantenimiento");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        ItemUOrganizativas.setText("Unidades Organizativas");
        ItemUOrganizativas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ItemUOrganizativas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemUOrganizativasActionPerformed(evt);
            }
        });
        jMenu1.add(ItemUOrganizativas);

        ItemUTramites.setText("Unidades de Tramites");
        ItemUTramites.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ItemUTramites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemUTramitesActionPerformed(evt);
            }
        });
        jMenu1.add(ItemUTramites);

        ItemTiposDocumentos.setText("Tipos de Documentos");
        ItemTiposDocumentos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ItemTiposDocumentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemTiposDocumentosActionPerformed(evt);
            }
        });
        jMenu1.add(ItemTiposDocumentos);

        ItemTiposTramites.setText("Tipos de Trámites");
        ItemTiposTramites.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ItemTiposTramites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemTiposTramitesActionPerformed(evt);
            }
        });
        jMenu1.add(ItemTiposTramites);

        ItemRequisitos.setText("Requisitos de Tipos de Trámites");
        ItemRequisitos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ItemRequisitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemRequisitosActionPerformed(evt);
            }
        });
        jMenu1.add(ItemRequisitos);

        ItemUsuarios.setText("Usuarios y Permisos");
        ItemUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ItemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemUsuariosActionPerformed(evt);
            }
        });
        jMenu1.add(ItemUsuarios);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Operaciones");
        jMenu2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenu6.setText("Recepción de Documentos");

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Expediente Nuevo");
        jMenuItem7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Expediente en Curso");
        jMenuItem8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem8);

        jMenu2.add(jMenu6);

        jMenu7.setText("Emisión de Documentos");

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Trámite de Expediente");
        jMenuItem9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem9);

        mnuInicioTramite.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        mnuInicioTramite.setText("Inicio de Trámite");
        mnuInicioTramite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnuInicioTramite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInicioTramiteActionPerformed(evt);
            }
        });
        jMenu7.add(mnuInicioTramite);

        jMenu2.add(jMenu7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Consultas");
        jMenu3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenuItem12.setText("Documentos Recibidos");
        jMenuItem12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuItem13.setText("Documentos Emitidos");
        jMenuItem13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem13);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setText("Busqueda de Expediente");
        jMenuItem16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem16);

        jMenu8.setText("Indicadores de Gestión");

        jMenuItem14.setText("General");
        jMenuItem14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem14);

        jMenuItem15.setText("Por Unidad de Trámite");
        jMenuItem15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem15);

        jMenu3.add(jMenu8);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Reportes");
        jMenu4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenuItem11.setText("Documentos Recibidos");
        jMenuItem11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuItem17.setText("Documentos Emitidos");
        jMenuItem17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem17);

        jMenuItem18.setText("Indicadores de Gestión");
        jMenuItem18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu4.add(jMenuItem18);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Ayuda");
        jMenu5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 1108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(panelMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ItemUOrganizativasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemUOrganizativasActionPerformed
        UnidadOrganizativaDLG UO = new UnidadOrganizativaDLG(this, rootPaneCheckingEnabled);
        UO.setLocationRelativeTo(null);
        UO.setVisible(true);
        
    }//GEN-LAST:event_ItemUOrganizativasActionPerformed

    private void ItemUTramitesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemUTramitesActionPerformed
        UnidadTramiteDLG UT = new UnidadTramiteDLG(this, rootPaneCheckingEnabled);
        UT.setLocationRelativeTo(null);
        UT.setVisible(true);
    }//GEN-LAST:event_ItemUTramitesActionPerformed

    private void ItemTiposDocumentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemTiposDocumentosActionPerformed
        TipoDocumentoDLG TD = new TipoDocumentoDLG(this, rootPaneCheckingEnabled);
        TD.setLocationRelativeTo(null);
        TD.setVisible(true);
    }//GEN-LAST:event_ItemTiposDocumentosActionPerformed

    private void ItemTiposTramitesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemTiposTramitesActionPerformed
        TipoTramiteDLG TT = new TipoTramiteDLG(this, rootPaneCheckingEnabled);
        TT.setLocationRelativeTo(null);
        TT.setVisible(true);
    }//GEN-LAST:event_ItemTiposTramitesActionPerformed

    private void ItemRequisitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemRequisitosActionPerformed
        RequisitosDLG R = new RequisitosDLG(this, rootPaneCheckingEnabled);
        R.setLocationRelativeTo(null);
        R.setVisible(true);
    }//GEN-LAST:event_ItemRequisitosActionPerformed

    private void ItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemUsuariosActionPerformed
            UsuarioDLG U = new UsuarioDLG(this, rootPaneCheckingEnabled);
            U.setLocationRelativeTo(null);
            U.setVisible(true);
    }//GEN-LAST:event_ItemUsuariosActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Tiempo = new javax.swing.Timer(1000, new mostrarFecha());
        Tiempo.start();
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        ExpedienteNuevoDLG expedienteNuevoDLG = new ExpedienteNuevoDLG(this, rootPaneCheckingEnabled);
        expedienteNuevoDLG.ModoNuevo();
        expedienteNuevoDLG.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        TramiteExpedienteDLG tramiteExpedienteDLG = new TramiteExpedienteDLG(this, rootPaneCheckingEnabled);
        tramiteExpedienteDLG.inicialFrm();
        tramiteExpedienteDLG.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        ExpedienteCursoDLG expedienteCursoDLG = new ExpedienteCursoDLG(this, rootPaneCheckingEnabled);
        expedienteCursoDLG.modoNuevo();
        expedienteCursoDLG.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void mnuInicioTramiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInicioTramiteActionPerformed
//        InicioTramite inicioTramite = new InicioTramite(this, rootPaneCheckingEnabled);
//        inicioTramite.modoNuevo();
//        inicioTramite.setVisible(true);
//        InicioTramite inicioTramite = new InicioTramite(this, rootPaneCheckingEnabled);
//        inicioTramite.modoNuevo();
//        inicioTramite.setVisible(true);

    }//GEN-LAST:event_mnuInicioTramiteActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        GeneradorReporte g = new GeneradorReporte(this, rootPaneCheckingEnabled);
        g.modoDocumento("recibido");
        g.setVisible(true);

    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        Consulta_DocumentosRecibidos consultaRecibido = new Consulta_DocumentosRecibidos(this, true);
        consultaRecibido.setVisible(true);         
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        Inidicador inidicador = new Inidicador(null, true);
        inidicador.modoGeneral();
        inidicador.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        Consulta_DocumentosEmitidos consultaEmitido = new Consulta_DocumentosEmitidos(this, true);
        consultaEmitido.setVisible(true);        
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
//        GeneradorReporte g = new GeneradorReporte(this, rootPaneCheckingEnabled);
//        g.modoDocumento("emitidos");
//        g.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        Inidicador inidicador = new Inidicador(this, true);
        inidicador.modoPorUnidad();
        inidicador.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void cbxPermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPermisosActionPerformed
        if (isAdministrador()) {
            oUnidadTramite = (UnidadTramite) cbxPermisos.getSelectedItem();
        } else {
            oPermiso = (Permiso) cbxPermisos.getSelectedItem();
        }
    }//GEN-LAST:event_cbxPermisosActionPerformed

    private void buttonIpod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIpod1ActionPerformed
        llamarMenu();
    }//GEN-LAST:event_buttonIpod1ActionPerformed

    private void menuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menuKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            llamarMenu();
        }
    }//GEN-LAST:event_menuKeyPressed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        ExpedienteCursoDLG expedienteCursoDLG = new ExpedienteCursoDLG(null, true);
        expedienteCursoDLG.modoVistaManual();
        expedienteCursoDLG.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    public void llamarMenu(){
        if(menu.getSelectedtitulo().equals("Nuevo")){
            ExpedienteNuevoDLG expedienteNuevoDLG = new ExpedienteNuevoDLG(this, rootPaneCheckingEnabled);
            expedienteNuevoDLG.ModoNuevo();
            expedienteNuevoDLG.setVisible(true);
        }
        if(menu.getSelectedtitulo().equals("En Curso")){
            ExpedienteCursoDLG expedienteCursoDLG = new ExpedienteCursoDLG(this, rootPaneCheckingEnabled);
            expedienteCursoDLG.modoNuevo();
            expedienteCursoDLG.setVisible(true);
        }
        if(menu.getSelectedtitulo().equals("Tramite")){
            TramiteExpedienteDLG tramiteExpedienteDLG = new TramiteExpedienteDLG(this, rootPaneCheckingEnabled);
            tramiteExpedienteDLG.inicialFrm();
            tramiteExpedienteDLG.setVisible(true);
        }
        if(menu.getSelectedtitulo().equals("Emitidos")){
            Consulta_DocumentosEmitidos consultaEmitido = new Consulta_DocumentosEmitidos(this, true);
            consultaEmitido.setVisible(true);
        }
        if(menu.getSelectedtitulo().equals("Recividos")){
            Consulta_DocumentosRecibidos consultaRecibido = new Consulta_DocumentosRecibidos(this, true);
            consultaRecibido.setVisible(true); 
        }
        if(menu.getSelectedtitulo().equals("Salir")){
            if(JOptionPane.showConfirmDialog(null, "¿Desea Salir?","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        System.exit(0);
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SistemaTramiteDocumentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SistemaTramiteDocumentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SistemaTramiteDocumentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SistemaTramiteDocumentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SistemaTramiteDocumentario().setVisible(true);
            }
        });
    }
    
    private javax.swing.Timer Tiempo;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ItemRequisitos;
    private javax.swing.JMenuItem ItemTiposDocumentos;
    private javax.swing.JMenuItem ItemTiposTramites;
    private javax.swing.JMenuItem ItemUOrganizativas;
    private javax.swing.JMenuItem ItemUTramites;
    private javax.swing.JMenuItem ItemUsuarios;
    private org.edisoncor.gui.button.ButtonIpod buttonIpod1;
    private javax.swing.JComboBox cbxPermisos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private org.edisoncor.gui.label.LabelMetric labelMetric1;
    private org.edisoncor.gui.label.LabelMetric labelMetric3;
    private org.edisoncor.gui.label.LabelMetric labelMetric4;
    private org.edisoncor.gui.label.LabelMetric lblApellidos;
    private org.edisoncor.gui.label.LabelMetric lblApellidos1;
    private javax.swing.JLabel lblFechaHora;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblFoto;
    private org.edisoncor.gui.label.LabelMetric lblNombre;
    private javax.swing.JLabel lblUsuario;
    private org.edisoncor.gui.panel.PanelAvatarChooser menu;
    private javax.swing.JMenuItem mnuInicioTramite;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    private javax.swing.JPanel panelMenu2;
    private org.edisoncor.gui.panel.PanelRectTranslucido pnlFoto;
    // End of variables declaration//GEN-END:variables
    static String asunto;
    static boolean inicioTramite;

}
