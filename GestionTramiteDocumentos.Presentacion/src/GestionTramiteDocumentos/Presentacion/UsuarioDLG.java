package GestionTramiteDocumentos.Presentacion;


import GestionTramiteDocumentos.Entidades.*;
import GestionTramiteDocumentos.LogicaNegocio.*;
import ModelosTablas.mdlPermisos;
import Util.Util;
import Util.mdlGeneral;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import mdlCeldas.CalendarCellEditor;

public class UsuarioDLG extends javax.swing.JDialog {
    
    PermisoLN permisoLN = new PermisoLN();
    UsuarioLN usuarioLN = new UsuarioLN();
    UnidadOrganizativaLN unidadOrganizativaLN = new UnidadOrganizativaLN();
    UnidadTramiteLN unidadTramiteLN = new UnidadTramiteLN();
    
    List<UnidadTramite> listaUtramites;
    List<Usuario> listaUsuarios;
    
    DefaultComboBoxModel mdlCbxTipo, mdlCbxUnidadOrganizativa;
    
    private Usuario oUsuario;
    private UnidadTramite oUnidadTramite;
    private UnidadOrganizativa oUnidadOrganizativa;
    
    private String modoGuardado = "Precionar";
    private int row = -1;
    
    public UsuarioDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[MANTENIMIENTO] - USUARIOS Y PERMISOS");
        Util.AplicarEncabezado(this,lblEncabezado,"UNIDAD_TRAMITE.PNG-64","USUARIOS","Permite registrar o actualizar los datos de Usuario");
        Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        Util.HabilitarContenedor(pnlUsuario,true);
        Util.HabilitarContenedor(pnlLista, true);
        
        mostrarCbxTIpo();
        mostrarCbxUnidadOrganizativa();
        mostrarTablaUsuarios();
        mostrarTablaPermisos();
        mostrarElementos(false);
    }
    
    public void limpiarCasillas(){
        txtApm.setText("");
        txtApp.setText("");
        txtCorreo.setText("");
        txtNombre.setText("");
    }

    public final void mostrarCbxUnidadOrganizativa(){
        try {
            if (SistemaTramiteDocumentario.oUsuario.getCuenta().equals("ADM")) {
                UnidadOrganizativa primeraOpcion = new UnidadOrganizativa(0, "SELECCIONE", null, null);
                List<UnidadOrganizativa> unidadOrganizativas = new ArrayList<UnidadOrganizativa>();
                unidadOrganizativas=unidadOrganizativaLN.ConsultarAll();
                unidadOrganizativas.add(0,primeraOpcion);
                mdlCbxUnidadOrganizativa = new DefaultComboBoxModel(unidadOrganizativas.toArray());
                cbxUnidadOrganizativa.setModel(mdlCbxUnidadOrganizativa);
                 
            } else {
                
                List<UnidadOrganizativa> unidadOrganizativas = new ArrayList<UnidadOrganizativa>();
                unidadOrganizativas.add(0,SistemaTramiteDocumentario.oPermiso.getUnidadOrganizativa());
                mdlCbxUnidadOrganizativa = new DefaultComboBoxModel(unidadOrganizativas.toArray());
                cbxUnidadOrganizativa.setModel(mdlCbxUnidadOrganizativa);
                cbxUnidadOrganizativa.setEditable(false);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public final void mostrarCbxTIpo(){
        try {
            List<String> opciones = new ArrayList<String>();
            opciones.add("OPERADOR DE UNIDAD DE TRAMITE");
            opciones.add("JEFE DE UNIDAD DE TRÁMITE");
            opciones.add("JEFE DE UNIDAD ORGANIZATIVA");
            mdlCbxTipo = new DefaultComboBoxModel(opciones.toArray());
            cbxTipo.setModel(mdlCbxTipo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public final void mostrarTablaPermisos(){
        try {
            
            listaUtramites = unidadTramiteLN.ConsultarAll((UnidadOrganizativa)cbxUnidadOrganizativa.getSelectedItem());
            mdlPermisos mdl = new mdlPermisos(listaUtramites);
            tblPermisos.setModel(mdl);
            
            Integer[] anchos = {50,270,150,150};
            Integer[] alineaciones = {JLabel.CENTER,JLabel.LEFT,JLabel.LEFT,JLabel.LEFT};
            String[] formatos = {"Logico","Cadena","JCalendar","JCalendar"};
            String[] modos = {"Normal","Normal","Normal","Normal"};

            Util.AplicarEstilos(tblPermisos,anchos,alineaciones,formatos,modos);
            
            tblPermisos.getColumnModel().getColumn(2).setCellEditor(new CalendarCellEditor());
            tblPermisos.getColumnModel().getColumn(3).setCellEditor(new CalendarCellEditor());

        } catch (Exception e) {
        }
    }
    
    public final void mostrarTablaUsuarios(){
        try {
            String Columnas[] = {"N°","Apellido Paterno","Apellido Materno","Nombre"};

            tblUsuarios.setModel(new mdlGeneral(Columnas));

            Integer[] anchos = {40,145,140,190};
            Integer[] alineaciones = {JLabel.LEFT,JLabel.LEFT,JLabel.LEFT,JLabel.LEFT};
            String[] formatos = {"Cadena","Cadena","Cadena","Cadena"};
            String[] modos = {"Normal","Normal","Normal","Normal"};

            Util.AplicarEstilos(tblUsuarios,anchos,alineaciones,formatos,modos);
            listarUsuarios();
            
        } catch (Exception e) {
        }
        
    }
    
    private List parseVectorUsuario(List<Usuario> lista) {
        List datos = new ArrayList();
        Object[] newdata;

        for(int i = 0; i < lista.size(); i++) {
            newdata = new Object[4];

            newdata[0] = i+1;
            newdata[1] = lista.get(i).getApellido_paterno();
            newdata[2] = lista.get(i).getApellido_materno();
            newdata[3] = lista.get(i).getNombre();

            datos.add(newdata);
        }

        return datos;
    }

    private void listarUsuarios() throws Exception {
        try {
            
            listaUsuarios = usuarioLN.ConsultarAll((UnidadOrganizativa)cbxUnidadOrganizativa.getSelectedItem());
            ((mdlGeneral)(tblUsuarios.getModel())).setData(parseVectorUsuario(listaUsuarios));
            
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    private void mostrarElementos(boolean estado){
        txtNombre.setEnabled(estado);
        txtApp.setEnabled(estado);
        txtApm.setEnabled(estado);
        txtCorreo.setEnabled(estado);
        btnGuardar.setEnabled(estado);
        btnCancelar.setEnabled(estado);
        cbxTipo.setEnabled(estado);
        tblPermisos.setEnabled(estado);
        btnFoto.setEnabled(estado);
        
        switch (modoGuardado) {
            case "Nuevo":
                txtCorreo.setEditable(true);
                btnNuevo.setEnabled(!estado);
                btnModificar.setEnabled(!estado);
                btnElliminar.setEnabled(!estado);
                btnSalir.setEnabled(!estado);
                cbxTipo.setSelectedIndex(0);
                
                limpiarCasillas();
                mostrarTablaPermisos();
                setIconDefault(foto);
                break;
                
            case "Modificar":
                btnNuevo.setEnabled(!estado);
                btnModificar.setEnabled(!estado);
                btnElliminar.setEnabled(!estado);
                btnSalir.setEnabled(!estado);
                txtCorreo.setEditable(false);
                txtNombre.setText(listaUsuarios.get(row).getNombre());
                txtApp.setText(listaUsuarios.get(row).getApellido_paterno());
                txtApm.setText(listaUsuarios.get(row).getApellido_materno());
                txtCorreo.setText(listaUsuarios.get(row).getEmail());
                cbxTipo.setSelectedItem(listaUsuarios.get(row).getTipo());

                mostrarTablaPermisos();
                
                if(comprovarRutaImagen(listaUsuarios.get(row).getRutaImagen())){
                    setIconPersonalizado(foto,listaUsuarios.get(row).getRutaImagen());
                }else{
                    setIconDefault(foto);
                }
                row = -1;
                break;
            case "Cancelar":
                btnNuevo.setEnabled(!estado);
                btnModificar.setEnabled(!estado);
                btnElliminar.setEnabled(!estado);
                btnSalir.setEnabled(!estado);
                cbxTipo.setSelectedIndex(0);

                limpiarCasillas();
                mostrarTablaPermisos();
                setIconDefault(foto);
                break;
            default :
                break;
        }
    }
    
    public boolean comprovarRutaImagen(String ruta){
        File file = new File(ruta);
        if(file.exists()){return true;}
        return false;
    }
    
    private void guardarUsuario(){
        try {
            GestorEmail gestorEmail;
            String Mensaje;
            String Asunto;
            
            GeneradorUserPass g = new GeneradorUserPass();
            String Nombre = txtNombre.getText();
            String App = txtApp.getText();
            String Apm = txtApm.getText();
            String Correo = txtCorreo.getText();
            String Tipo = String.valueOf(cbxTipo.getSelectedItem());
            String Cuenta = g.generarCuenta(Nombre, App, Apm);
            String contraseña = g.generarPass(Cuenta);
            String EncripContraseña = g.encriptarPass(contraseña);
            Boolean Estado = true;

            int contador = 0;
            switch(modoGuardado){
                case "Nuevo":
                    
                    for (int i = 0; i < tblPermisos.getRowCount(); i++) {
                        if((boolean)tblPermisos.getValueAt(i, 0) == true) {
                            contador += contador +1;
                        }
                    }
                    
                    if (contador != 0) {
                        usuarioLN.Insertar(new Usuario(App, Apm, Nombre, Cuenta, EncripContraseña, Estado, Tipo, Correo, this.rutaImagen));
                        oUsuario = usuarioLN.ConsultarUsuarioCuenta(new Usuario(null, null, null, Cuenta, null, null, null, null));
                    
                        guardarPermisos();
                        
                        Asunto = "BIENVENIDO";
                        Mensaje = "DATOS DE USUARIO " + "\nNombre : " + oUsuario + "\nCuenta : " + Cuenta + "\nContraseña : " + contraseña + "\nCargo : " + oUsuario.getTipo();
                        Mensaje += "";

                        if(hayInternet("")){
                            gestorEmail = new GestorEmail(Correo, Asunto, Mensaje);
                            gestorEmail.enviarEmail();
                        }else{
                            JOptionPane.showMessageDialog(null, Mensaje);
                        }

                        JOptionPane.showMessageDialog(this, "SU CUENTA Y SU CONTRASEÑA FUERON ENVIADOS A SU CORREO");
                        
                        mostrarElementos(true);
                        
                    } else {
                        String msj = "Falta llenar Permisos";
                        JOptionPane.showMessageDialog(this, msj,"Mensaje del Sistema",JOptionPane.WARNING_MESSAGE);
                    }
                    
                    break;
                case "Modificar":
                    
                    for (int i = 0; i < tblPermisos.getRowCount(); i++) {
                        if((boolean)tblPermisos.getValueAt(i, 0) == true) {
                            contador += contador +1;
                        }
                    }
                    
                    if (contador != 0) {
                        row = tblUsuarios.getSelectedRow();
                        listaUsuarios.get(row).setApellido_materno(txtApm.getText());
                        listaUsuarios.get(row).setApellido_paterno(txtApp.getText());
                        listaUsuarios.get(row).setNombre(txtNombre.getText());
                        listaUsuarios.get(row).setTipo(cbxTipo.getSelectedItem().toString());
                        listaUsuarios.get(row).setRutaImagen(this.rutaImagen);
                        System.out.println(listaUsuarios.get(row).getCuenta());
                        
                        usuarioLN.Modificar(listaUsuarios.get(row));
                        
                        oUsuario = usuarioLN.ConsultarUsuarioID(listaUsuarios.get(row));
                        
                        permisoLN.EliminarPorID(oUsuario);
                        guardarPermisos();

                        String AsuntoAC = "ACTUALIZACION DE DATOS SISTEMA TRAMITE DOCUMENTARIO";
                        String MensajeAC = "DATOS DE USUARIO" + "\nNombre : " + oUsuario + "\nCuenta : " + Cuenta + "\nCargo : " + oUsuario.getTipo();

 
                        if(hayInternet("")){
                            gestorEmail = new GestorEmail(Correo, AsuntoAC, MensajeAC);
                            gestorEmail.enviarEmail();
                        }else{
                            JOptionPane.showMessageDialog(null, AsuntoAC+"\n"+MensajeAC);
                        }

                        modoGuardado = "Cancelar";
                        mostrarElementos(false);

                    }else{
                        String msj = "Falta llenar Permisos";
                        JOptionPane.showMessageDialog(this, msj,"Mensaje del Sistema",JOptionPane.WARNING_MESSAGE);
                    }
                    
                    break;
                default :
                    break;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDLG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean hayInternet(String url){
        String dirWeb = "unprg.std.root@gmail.com";
        int puerto = 80;
        
        try{
            Socket s = new Socket(dirWeb, puerto);
            if(s.isConnected()){
                return true;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de conexion a intenet:"+e.getMessage());
        }
        return false;
    }
    
    public void guardarPermisos(){
        try {
            for (int i = 0; i < tblPermisos.getRowCount(); i++) {
            if ((boolean)tblPermisos.getValueAt(i, 0) == true) {
                if ((String)tblPermisos.getValueAt(i, 2) != null && (String)tblPermisos.getValueAt(i, 3) != null) {
                    String fechaInicio = (String)tblPermisos.getValueAt(i, 2);
                    String fechaFinal = (String)tblPermisos.getValueAt(i, 3);
                    oUnidadTramite = listaUtramites.get(i);
                    oUnidadOrganizativa = (UnidadOrganizativa) cbxUnidadOrganizativa.getSelectedItem();
                    
                    permisoLN.Insertar(new Permiso(fechaInicio, fechaFinal, oUsuario, oUnidadOrganizativa, oUnidadTramite));
                } else {
                    Date date = new Date();
                    date.setYear(date.getYear()+1);
                    
                    oUnidadTramite = listaUtramites.get(i);
                    oUnidadOrganizativa = (UnidadOrganizativa) cbxUnidadOrganizativa.getSelectedItem();
                    
                    String fechaInicio = FormatoFecha.getFecha(new Date(), "dd/MM/yyyy");
                    String fechaFinal = FormatoFecha.getFecha(date , "dd/MM/yyyy");
                    
                    permisoLN.Insertar(new Permiso(fechaInicio, fechaFinal, oUsuario, oUnidadOrganizativa, oUnidadTramite));
                }
            }
        }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlUsuario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPermisos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApp = new javax.swing.JTextField();
        txtApm = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbxTipo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        foto = new javax.swing.JLabel();
        btnFoto = new javax.swing.JButton();
        pnlLista = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnElliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblUTramite = new javax.swing.JLabel();
        cbxUnidadOrganizativa = new javax.swing.JComboBox();
        lblEncabezado = new javax.swing.JLabel();
        lblSubEncabezado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblPermisos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblPermisos);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nombre(s)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Appellido paterno");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Apellido materno");

        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNombre.setPreferredSize(new java.awt.Dimension(2, 24));

        txtApp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtApp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtApp.setPreferredSize(new java.awt.Dimension(2, 24));

        txtApm.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtApm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtApm.setPreferredSize(new java.awt.Dimension(2, 24));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Tipo");

        cbxTipo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Permisos:");

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Correo Electronico");

        txtCorreo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCorreo.setPreferredSize(new java.awt.Dimension(2, 24));
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });

        foto.setBackground(new java.awt.Color(153, 153, 153));
        foto.setOpaque(true);

        btnFoto.setText("Foto");
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUsuarioLayout = new javax.swing.GroupLayout(pnlUsuario);
        pnlUsuario.setLayout(pnlUsuarioLayout);
        pnlUsuarioLayout.setHorizontalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(552, 552, 552))
                    .addGroup(pnlUsuarioLayout.createSequentialGroup()
                        .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxTipo, 0, 330, Short.MAX_VALUE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtApm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsuarioLayout.createSequentialGroup()
                        .addGap(472, 472, 472)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)))
                .addContainerGap())
        );
        pnlUsuarioLayout.setVerticalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlUsuarioLayout.createSequentialGroup()
                        .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtApm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(foto, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(11, 11, 11))
        );

        pnlLista.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblUsuarios);

        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnElliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnElliminar.setText("Eliminar");
        btnElliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElliminarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Usuarios");

        lblUTramite.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblUTramite.setText("UNIDAD ORGANIZATIVA");

        cbxUnidadOrganizativa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbxUnidadOrganizativa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUnidadOrganizativa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUnidadOrganizativaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlListaLayout = new javax.swing.GroupLayout(pnlLista);
        pnlLista.setLayout(pnlListaLayout);
        pnlListaLayout.setHorizontalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(lblUTramite, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxUnidadOrganizativa, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlListaLayout.createSequentialGroup()
                            .addComponent(btnNuevo)
                            .addGap(18, 18, 18)
                            .addComponent(btnModificar)
                            .addGap(18, 18, 18)
                            .addComponent(btnElliminar)
                            .addGap(18, 18, 18)
                            .addComponent(btnSalir))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlListaLayout.setVerticalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUTramite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxUnidadOrganizativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnElliminar)
                    .addComponent(btnModificar)
                    .addComponent(btnNuevo))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(lblEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSubEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblSubEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        if (SistemaTramiteDocumentario.oUsuario.getCuenta().equals("ADM")) {
            if (cbxUnidadOrganizativa.getSelectedIndex() != 0) {
                modoGuardado = "Nuevo";
                Util.AplicarSubencabezado(this, lblSubEncabezado, "Guardar-24", modoGuardado);
                mostrarElementos(true);
                Util.HabilitarContenedor(pnlLista, false);
            } else {
                String msj = "SELECCIONE UNA UNIDAD ORGANIZATIVA";
                JOptionPane.showMessageDialog(this, msj,"MENSAJE DEL SISTEMA",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            if (cbxUnidadOrganizativa.getSelectedIndex() != -1) {
                modoGuardado = "Nuevo";
                Util.AplicarSubencabezado(this, lblSubEncabezado, "Guardar-24", modoGuardado);
                mostrarElementos(true);
                Util.HabilitarContenedor(pnlLista, false);
            } else {
                String msj = "SELECCIONE UNA UNIDAD ORGANIZATIVA";
                JOptionPane.showMessageDialog(this, msj,"MENSAJE DEL SISTEMA",JOptionPane.WARNING_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!txtApm.getText().equals("") && !txtApp.getText().equals("") && !txtNombre.getText().equals("") && !txtCorreo.getText().equals("")) {
            guardarUsuario();
            mostrarTablaUsuarios();
            mostrarTablaPermisos();
            
            Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        } else {
            JOptionPane.showMessageDialog(this, "Faltan Llenar Datos Requeridos");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbxUnidadOrganizativaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUnidadOrganizativaActionPerformed
        mostrarTablaUsuarios();
        mostrarTablaPermisos();
    }//GEN-LAST:event_cbxUnidadOrganizativaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        row = tblUsuarios.getSelectedRow();
        if (row != -1) {
            modoGuardado = "Modificar";
            Util.AplicarSubencabezado(this, lblSubEncabezado, "Actualizar-24", modoGuardado);
            mostrarElementos(true);
            Util.HabilitarContenedor(pnlLista, false);
        } else {
            String msj = "NO SE HA SELECCIONADO NINGUN REGISTRO";
            JOptionPane.showMessageDialog(this, msj,"MENSAJE DEL SISTEMA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        modoGuardado = "Cancelar";
        Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        mostrarElementos(false);
        limpiarCasillas();
        Util.HabilitarContenedor(pnlLista, true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (JOptionPane.showConfirmDialog(this, "¿Desea Salir?","MENSAJE DEL SISTEMA",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnElliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElliminarActionPerformed
        try {
            modoGuardado = "";
            row = tblUsuarios.getSelectedRow();
            if (row != -1) {
                if (JOptionPane.showConfirmDialog(null,"¿Desea eliminar el Registro?","Mensaje del Sistema",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    usuarioLN.Eliminar(listaUsuarios.get(row));
                    String msj = "EL REGISTRO SE HA ELIMINADO CON EXITO";
                    JOptionPane.showMessageDialog(this, msj,"MENSAJE DEL SISTEMA",JOptionPane.INFORMATION_MESSAGE);
                    mostrarTablaUsuarios();
                }
            } else {
                String msj = "NO SE HA SELECCIONADO NINGUN REGISTRO";
                JOptionPane.showMessageDialog(this, msj,"MENSAJE DEL SISTEMA",JOptionPane.WARNING_MESSAGE);
            }    
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnElliminarActionPerformed

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed
        JFileChooser seleccionar = new JFileChooser();
        seleccionar.setFileFilter(new FileNameExtensionFilter("Archivo de imagen", "jpg"));
        if(seleccionar.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            setIconPersonalizado(foto, seleccionar.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnFotoActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    public void setIconDefault(JLabel label){
        rutaImagen="sin imagagen";
        ImageIcon imagen = new ImageIcon(getClass().getResource("/img/anonimo.jpg"));
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), WIDTH));
        label.setIcon(icono);
    }
    
    public void setIconPersonalizado(JLabel label, String ruta){
        rutaImagen=ruta;
        ImageIcon imagen = new ImageIcon(ruta);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), WIDTH));
        label.setIcon(icono);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnElliminar;
    private javax.swing.JButton btnFoto;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxTipo;
    private javax.swing.JComboBox cbxUnidadOrganizativa;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblSubEncabezado;
    private javax.swing.JLabel lblUTramite;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JPanel pnlUsuario;
    private javax.swing.JTable tblPermisos;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtApm;
    private javax.swing.JTextField txtApp;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
    private String rutaImagen = "sin imagen";
}
