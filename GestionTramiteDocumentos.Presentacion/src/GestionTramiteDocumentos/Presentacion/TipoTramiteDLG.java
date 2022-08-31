package GestionTramiteDocumentos.Presentacion;


import GestionTramiteDocumentos.Entidades.TipoTramite;
import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import GestionTramiteDocumentos.LogicaNegocio.TipoTramiteLN;
import GestionTramiteDocumentos.LogicaNegocio.UnidadOrganizativaLN;
import GestionTramiteDocumentos.LogicaNegocio.UnidadTramiteLN;
import Util.Util;
import Util.mdlGeneral;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class TipoTramiteDLG extends javax.swing.JDialog {

    private UnidadOrganizativaLN unidadOrganizativaLN = new UnidadOrganizativaLN();
    private UnidadTramiteLN unidadTramiteLN = new UnidadTramiteLN();
    DefaultComboBoxModel mdlCbxUnidadOrganizativa, mdlCbxUnidadTramite;
    
    private List<TipoTramite> tipoTramites = new ArrayList<TipoTramite>();
    private TipoTramiteLN tipoTramiteLN = new TipoTramiteLN();

    private String modoGuardado="";
    private Integer row = -1;
    
    public TipoTramiteDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        mostrarCbxUnidadOrganizativa();
        
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[MANTENIMIENTO] - TIPO DE TRAMITE");
        Util.AplicarEncabezado(this,lblEncabezado,"UNIDAD_TRAMITE.PNG-64","TIPO DE TRAMITE","Permite registrar o actualizar los datos de Tipos de Trámite de una Unidad de Trámite");
        Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        Util.HabilitarContenedor(pnlTipoTramite,true);
        Util.HabilitarContenedor(pnlLista, true);
        
        mdlTabla();
        mostrarTabla();
        limpiarCasillas();
        mostrarElementos(false);
        
    }

    private void mostrarElementos(boolean estado){
        txtNombre.setEnabled(estado);
        txtTiempoEstimado.setEnabled(estado);
        txtId.setEnabled(false);
        txtUnidadTramite.setEnabled(false);
        cbxUnidadOrganizativa.setEnabled(!estado);
        cbxUnidadTramite.setEnabled(!estado);

        btnGuardar.setEnabled(estado);
        btnCancelar.setEnabled(estado);
        btnNuevo.setEnabled(!estado);
        btnModificar.setEnabled(!estado);
        btnEliminar.setEnabled(!estado);
        btnSalir.setEnabled(!estado);
        
        if (modoGuardado.equals("nuevo")) {
            tblTipoTramite.setRowSelectionAllowed(false);
            row = -1;
        }else{
            tblTipoTramite.setRowSelectionAllowed(true);
        }
    }

    public final void limpiarCasillas(){
        txtTiempoEstimado.setText("");
        txtUnidadTramite.setText("");
        txtNombre.setText("");
        txtId.setText("");
    }
    
    public final void mdlTabla(){
        String Columnas[] = {"N°","Nombre","Tiempo Estimado"};
        
        tblTipoTramite.setModel(new mdlGeneral(Columnas));
        
        Integer[] Anchos = {30,300,200};
        Integer[] Alineaciones = {JLabel.CENTER,JLabel.LEFT,JLabel.CENTER};
        String[] Formatos = {"Cadena","Cadena","Cadena"};
        String[] Modos = {"Normal","Normal","Normal"};
        
        Util.AplicarEstilos(tblTipoTramite, Anchos, Alineaciones, Formatos, Modos);
    }
    
    public final void mostrarTabla(){
        if(((UnidadOrganizativa)cbxUnidadOrganizativa.getSelectedItem()).getId_unidadOrganizativa()!=0){
            try {          
                UnidadTramite objetoSelect =  new UnidadTramite();
                objetoSelect = (UnidadTramite)cbxUnidadTramite.getSelectedItem();
                    tipoTramites = tipoTramiteLN.ConsultarAll(objetoSelect);
                    
                    ((mdlGeneral)tblTipoTramite.getModel()).setData(parseVector(tipoTramites));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    private List parseVector(List<TipoTramite> lista) {
        List datos = new ArrayList();
        Object[] newdata;

        for(int i = 0; i < lista.size(); i++) {
            newdata = new Object[3];

            newdata[0] = i+1;
            newdata[1] = lista.get(i).getNombre();
            newdata[2] = lista.get(i).getTiempo_estimado();

            datos.add(newdata);
        }

        return datos;
    }

    public final void mostrarCbxUnidadOrganizativa(){
        try {
            UnidadOrganizativa primeraOpcion = new UnidadOrganizativa(0, "TODAS", null, null);
            List<UnidadOrganizativa> unidadOrganizativas = new ArrayList<UnidadOrganizativa>();
            unidadOrganizativas=unidadOrganizativaLN.ConsultarAll();
            unidadOrganizativas.add(0,primeraOpcion);
            mdlCbxUnidadOrganizativa = new DefaultComboBoxModel(unidadOrganizativas.toArray());
            cbxUnidadOrganizativa.setModel(mdlCbxUnidadOrganizativa);
           
            mostrarCbxUnidadTramite();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void verLista(List<UnidadTramite> lista){
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getId_unidad_tramite()+" - "+lista.get(i).getNombre()+" - "+lista.get(i).getAbreviatura());
        }
    }
    
    public final void mostrarCbxUnidadTramite(){
        if(((UnidadOrganizativa)cbxUnidadOrganizativa.getSelectedItem()).getId_unidadOrganizativa()!=0){
            try {
                UnidadTramite primeraOpcion = new UnidadTramite(0, "TODAS", null, null,null, null, null);
                List<UnidadTramite> unidadTramites = new ArrayList<UnidadTramite>();
                unidadTramites=unidadTramiteLN.ConsultarAll((UnidadOrganizativa)cbxUnidadOrganizativa.getSelectedItem());
                unidadTramites.add(0,primeraOpcion);
                mdlCbxUnidadTramite = new DefaultComboBoxModel(unidadTramites.toArray());
                cbxUnidadTramite.setModel(mdlCbxUnidadTramite);
                
                verLista(unidadTramites);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            try {
                UnidadTramite primeraOpcion = new UnidadTramite(0, "TODAS", null, null,null, null, null);
                List<UnidadTramite> unidadTramites = new ArrayList<UnidadTramite>();
                unidadTramites.add(primeraOpcion);
                mdlCbxUnidadTramite = new DefaultComboBoxModel(unidadTramites.toArray());
                cbxUnidadTramite.setModel(mdlCbxUnidadTramite);
                
                verLista(unidadTramites);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public UnidadTramite getUnidadTramite(){
        UnidadTramite objeto = null;
        try {
            objeto = (UnidadTramite)cbxUnidadTramite.getSelectedItem();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return objeto;
    }
    
    public UnidadOrganizativa getUnidadOrganizativa(){
        UnidadOrganizativa objeto = null;
        try {
            objeto = (UnidadOrganizativa)cbxUnidadOrganizativa.getSelectedItem();
            return objeto;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return objeto;
    }
    
    public TipoTramite getTipoTramite(){
        TipoTramite objeto = null;    
        try {
            String nombre = txtNombre.getText();
            for (int i = 0; i < tipoTramites.size() ; i++) {
                if(tipoTramites.get(i).getNombre().equals(nombre)){
                    objeto = tipoTramites.get(i);
                    break;
                }
            }
            return objeto;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return objeto;
    }
    
    public TipoTramite getTipoTramiteDirectoTabla(){
        TipoTramite objeto = null;    
        try {
            String nombre = tblTipoTramite.getValueAt(tblTipoTramite.getSelectedRow(), 1).toString();
            for (int i = 0; i < tipoTramites.size() ; i++) {
                if(tipoTramites.get(i).getNombre().equals(nombre)){
                    objeto = tipoTramites.get(i);
                    break;
                }
            }
            return objeto;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return objeto;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLista = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbxUnidadTramite = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTipoTramite = new javax.swing.JTable();
        cbxUnidadOrganizativa = new javax.swing.JComboBox();
        pnlTipoTramite = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtUnidadTramite = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtTiempoEstimado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblEncabezado = new javax.swing.JLabel();
        lblSubEncabezado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlLista.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista"));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel6.setText("Unidad organizativa");

        jLabel7.setText("Unidad de tramite");

        jLabel8.setText("Tipo de tramites");

        cbxUnidadTramite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUnidadTramite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUnidadTramiteActionPerformed(evt);
            }
        });

        tblTipoTramite.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblTipoTramite);

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
                .addGap(18, 18, 18)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir))
                    .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                        .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(pnlListaLayout.createSequentialGroup()
                                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(19, 19, 19)
                                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxUnidadOrganizativa, 0, 400, Short.MAX_VALUE)
                                    .addComponent(cbxUnidadTramite, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlListaLayout.setVerticalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxUnidadOrganizativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbxUnidadTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnModificar)
                    .addComponent(btnSalir)
                    .addComponent(btnEliminar))
                .addContainerGap())
        );

        pnlTipoTramite.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de tramite"));

        jLabel1.setText("Id");

        jLabel2.setText("U. Tramite");

        jLabel3.setText("Nombre");

        jLabel4.setText("Tiempo estimado");

        txtId.setFocusable(false);

        txtUnidadTramite.setFocusable(false);

        jLabel5.setText("días.");

        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTipoTramiteLayout = new javax.swing.GroupLayout(pnlTipoTramite);
        pnlTipoTramite.setLayout(pnlTipoTramiteLayout);
        pnlTipoTramiteLayout.setHorizontalGroup(
            pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTipoTramiteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTipoTramiteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar))
                    .addGroup(pnlTipoTramiteLayout.createSequentialGroup()
                        .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUnidadTramite, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlTipoTramiteLayout.createSequentialGroup()
                                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlTipoTramiteLayout.createSequentialGroup()
                                        .addComponent(txtTiempoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel5))
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 178, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pnlTipoTramiteLayout.setVerticalGroup(
            pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTipoTramiteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUnidadTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTiempoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addGroup(pnlTipoTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(pnlTipoTramite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTipoTramite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxUnidadOrganizativaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUnidadOrganizativaActionPerformed
       mostrarCbxUnidadTramite();
       mostrarTabla();
    }//GEN-LAST:event_cbxUnidadOrganizativaActionPerformed

    private void cbxUnidadTramiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUnidadTramiteActionPerformed
        if(((UnidadOrganizativa)cbxUnidadOrganizativa.getSelectedItem()).getId_unidadOrganizativa()!=0){
            mostrarTabla();
        }else{
        
        }
    }//GEN-LAST:event_cbxUnidadTramiteActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        if(getUnidadOrganizativa().getId_unidadOrganizativa()!=0){
            if(getUnidadTramite().getId_unidad_tramite()!=0){
                try {
                    modoGuardado ="nuevo";
                    
                    Util.AplicarSubencabezado(this, lblSubEncabezado, "Guardar-24", modoGuardado);
                    mostrarElementos(true);
            
                    txtId.setText(Integer.toString(tblTipoTramite.getRowCount()+1));
                    txtUnidadTramite.setText(getUnidadTramite().getNombre());
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione unidad de tramite", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione unidad organizativa", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
 
        if(getUnidadOrganizativa().getId_unidadOrganizativa()!=0){
            if(getUnidadTramite().getId_unidad_tramite()!=0){
                int fila = tblTipoTramite.getSelectedRow();
                if(fila!=-1){
                    try {
                        modoGuardado ="modificar";
                        Util.AplicarSubencabezado(this, lblSubEncabezado, "Actualizar-24", modoGuardado);
                        mostrarElementos(true);
                        
                        txtId.setText(tblTipoTramite.getValueAt(fila,0).toString());
                        txtUnidadTramite.setText(getUnidadTramite().getNombre());
                        txtNombre.setText(tblTipoTramite.getValueAt(fila,1).toString());
                        txtTiempoEstimado.setText(tblTipoTramite.getValueAt(fila,2).toString());
                     
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione unidad de tramite", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione unidad organizativa", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            if(modoGuardado.equals("nuevo")){
                TipoTramite objeto = new TipoTramite();
                objeto.setNombre(txtNombre.getText().toUpperCase());
                objeto.setTiempo_estimado(Integer.parseInt(txtTiempoEstimado.getText()));
                objeto.setUnidadTramite(getUnidadTramite());
                tipoTramiteLN.Insertar(objeto);

            }else{
                row = tblTipoTramite.getSelectedRow();
                TipoTramite objeto = new TipoTramite();
                objeto.setId_tipoTramite(tipoTramites.get(row).getId_tipoTramite());
                objeto.setNombre(txtNombre.getText().toUpperCase());
                objeto.setTiempo_estimado(Integer.parseInt(txtTiempoEstimado.getText()));
                objeto.setUnidadTramite(getUnidadTramite());
                tipoTramiteLN.Modificar(objeto);

            }

            row = -1;
            modoGuardado = "";
            mostrarElementos(false);
            limpiarCasillas();
            mostrarTabla();
            tblTipoTramite.setRowSelectionInterval(tblTipoTramite.getRowCount()-1,tblTipoTramite.getRowCount()-1);
            Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (JOptionPane.showConfirmDialog(this, "¿Desea Salir?","MENSAJE DEL SISTEMA",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        modoGuardado = "";
        mostrarElementos(false);
        limpiarCasillas();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblTipoTramite.getSelectedRow();
        if(fila!=-1){
            try {
                if (JOptionPane.showConfirmDialog(this, "¿Desea Eliminar el Registro?","MENSAJE DEL SISTEMA",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    tipoTramiteLN.Eliminar(getTipoTramiteDirectoTabla());
                    mostrarTabla();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
        }        
    }//GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxUnidadOrganizativa;
    private javax.swing.JComboBox cbxUnidadTramite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblSubEncabezado;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JPanel pnlTipoTramite;
    private javax.swing.JTable tblTipoTramite;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTiempoEstimado;
    private javax.swing.JTextField txtUnidadTramite;
    // End of variables declaration//GEN-END:variables
}
