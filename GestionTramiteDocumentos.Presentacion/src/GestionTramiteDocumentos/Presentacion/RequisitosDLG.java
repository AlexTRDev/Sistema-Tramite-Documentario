package GestionTramiteDocumentos.Presentacion;


import GestionTramiteDocumentos.Entidades.*;
import GestionTramiteDocumentos.LogicaNegocio.*;
import Util.Util;
import Util.mdlGeneral;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class RequisitosDLG extends javax.swing.JDialog {
    
    UnidadOrganizativaLN unidadOrganizativaLN = new UnidadOrganizativaLN();
    UnidadTramiteLN unidadTramiteLN = new UnidadTramiteLN();
    TipoTramiteLN tipoTramiteLN = new TipoTramiteLN();
    RequisitoLN requisitoLN = new RequisitoLN();
    List<Requisito> requisitos = new ArrayList<Requisito>();
    
    DefaultComboBoxModel mdlCbxUnidadOrganizativa, mdlCbxUnidadTramite, mdlCbxTipoTramite;
    String modoGuardado = "";
    Integer row = -1;
    
    public RequisitosDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mostrarCbxUnidadOrganizativa();
        
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[MANTENIMIENTO] - TIPO DE TRAMITE");
        Util.AplicarEncabezado(this,lblEncabezado,"UNIDAD_TRAMITE.PNG-64","TIPO DE TRAMITE","Permite registrar o actualizar los datos de Tipos de Trámite de una Unidad de Trámite");
        Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        Util.HabilitarContenedor(pnlRequisitos,true);
        Util.HabilitarContenedor(pnlLista, true);
        
        mdlTabla();
        mostrarTabla();
        limpiarCasillas();
        mostrarElementos(false);
    }

    private void mostrarElementos(boolean estado){
        txtNombre.setEnabled(estado);
        txtTipoTramite.setEnabled(false);
        txtId.setEnabled(false);
        txtUnidadTramite.setEnabled(false);
        
        cbxUnidadOrganizativa.setEnabled(!estado);
        cbxUnidadTramite.setEnabled(!estado);
        cbxTipoTramite.setEnabled(!estado);
        
        btnGuardar.setEnabled(estado);
        btnCancelar.setEnabled(estado);
        btnNuevo.setEnabled(!estado);
        btnModificar.setEnabled(!estado);
        btnEliminar.setEnabled(!estado);
        btnCerrar.setEnabled(!estado);
        
        if (modoGuardado.equals("nuevo")) {
            tblRequisito.setRowSelectionAllowed(false);
            row = -1;
        }else{
            tblRequisito.setRowSelectionAllowed(true);
        }
    }

    public final void limpiarCasillas(){
        txtTipoTramite.setText("");
        txtUnidadTramite.setText("");
        txtNombre.setText("");
        txtId.setText("");
    }
    
    public final void mdlTabla(){
        String Columnas[] = {"N°","Nombre"};
        
        tblRequisito.setModel(new mdlGeneral(Columnas));
        
        Integer[] Anchos = {50,400};
        Integer[] Alineaciones = {JLabel.CENTER,JLabel.LEFT};
        String[] Formatos = {"Cadena","Cadena"};
        String[] Modos = {"Normal","Normal"};
        
        Util.AplicarEstilos(tblRequisito, Anchos, Alineaciones, Formatos, Modos);
    }
    
    private List parseVector(List<Requisito> lista) {
        List datos = new ArrayList();
        Object[] newdata;

        for(int i = 0; i < lista.size(); i++) {
            newdata = new Object[2];

            newdata[0] = i+1;
            newdata[1] = lista.get(i).getDescipcion();

            datos.add(newdata);
        }

        return datos;
    }

    public final void mostrarTabla(){
        TipoTramite objetoSelect = (TipoTramite)cbxTipoTramite.getSelectedItem();
        
        if(objetoSelect.getId_tipoTramite()!=0){
            try {          
                    requisitos = requisitoLN.ConsultarAll(objetoSelect);
                    ((mdlGeneral)tblRequisito.getModel()).setData(parseVector(requisitos));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public final void mostrarCbxUnidadOrganizativa(){
        try {
            UnidadOrganizativa primeraOpcion = new UnidadOrganizativa(0, "SELECCIONE", null, null);
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
    
    public final void mostrarCbxUnidadTramite(){
        if(((UnidadOrganizativa)cbxUnidadOrganizativa.getSelectedItem()).getId_unidadOrganizativa()!=0){
            try {
                UnidadTramite primeraOpcion = new UnidadTramite(0, "SELECCIONE", null, null,null, null, null);
                List<UnidadTramite> unidadTramites = new ArrayList<UnidadTramite>();
                unidadTramites=unidadTramiteLN.ConsultarAll((UnidadOrganizativa)cbxUnidadOrganizativa.getSelectedItem());
                unidadTramites.add(0,primeraOpcion);
                mdlCbxUnidadTramite = new DefaultComboBoxModel(unidadTramites.toArray());
                cbxUnidadTramite.setModel(mdlCbxUnidadTramite);
                
                mostrarCbxTipoTramite();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            try {
                UnidadTramite primeraOpcion = new UnidadTramite(0, "SELECCIONE", null, null,null, null, null);
                List<UnidadTramite> unidadTramites = new ArrayList<UnidadTramite>();
                unidadTramites.add(primeraOpcion);
                mdlCbxUnidadTramite = new DefaultComboBoxModel(unidadTramites.toArray());
                cbxUnidadTramite.setModel(mdlCbxUnidadTramite);
                mostrarCbxTipoTramite();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public final void mostrarCbxTipoTramite(){
        if(((UnidadTramite)cbxUnidadTramite.getSelectedItem()).getId_unidad_tramite()!=0){
            try {
                TipoTramite primeraOpcion = new TipoTramite(0, "SELECCIONE", null, null,null);
                List<TipoTramite> tipoTramites = new ArrayList<TipoTramite>();
                tipoTramites=tipoTramiteLN.ConsultarAll((UnidadTramite)cbxUnidadTramite.getSelectedItem());
                tipoTramites.add(0,primeraOpcion);
                mdlCbxTipoTramite = new DefaultComboBoxModel(tipoTramites.toArray());
                cbxTipoTramite.setModel(mdlCbxTipoTramite);
                
                mostrarTabla();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            try {
                TipoTramite primeraOpcion = new TipoTramite(0, "SELECCIONE", null, null,null);
                List<TipoTramite> tipoTramites = new ArrayList<TipoTramite>();
                tipoTramites.add(0,primeraOpcion);
                mdlCbxTipoTramite = new DefaultComboBoxModel(tipoTramites.toArray());
                cbxTipoTramite.setModel(mdlCbxTipoTramite);
                
                 mostrarTabla();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
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

    public UnidadTramite getUnidadTramite(){
        UnidadTramite objeto = null;
        try {          
                objeto = (UnidadTramite)cbxUnidadTramite.getSelectedItem();
                return objeto;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return objeto;
    }
    
    public TipoTramite getTipoTramite(){
        TipoTramite objeto = null;
        try {          
                objeto = (TipoTramite)cbxTipoTramite.getSelectedItem();
                return objeto;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return objeto;
    }
    
    public Requisito getRequisito(){
        Requisito objeto = null;
        String nombre = tblRequisito.getValueAt(tblRequisito.getSelectedRow(), 1).toString();
        for (int i = 0; i < this.requisitos.size(); i++) {
            if(this.requisitos.get(i).getDescipcion().equals(nombre)){
                objeto=this.requisitos.get(i);
                break;
            }
        }
        return objeto;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLista = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRequisito = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        cbxUnidadOrganizativa = new javax.swing.JComboBox();
        cbxUnidadTramite = new javax.swing.JComboBox();
        cbxTipoTramite = new javax.swing.JComboBox();
        pnlRequisitos = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        txtUnidadTramite = new javax.swing.JTextField();
        txtTipoTramite = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        lblEncabezado = new javax.swing.JLabel();
        lblSubEncabezado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlLista.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista"));

        tblRequisito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblRequisito);

        jLabel1.setText("Unidad de tramite");

        jLabel2.setText("Tipo de tramite");

        jLabel3.setText("Unidad organizativa");

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
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

        cbxUnidadOrganizativa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUnidadOrganizativa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUnidadOrganizativaActionPerformed(evt);
            }
        });

        cbxUnidadTramite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUnidadTramite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUnidadTramiteActionPerformed(evt);
            }
        });

        cbxTipoTramite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTipoTramite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoTramiteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlListaLayout = new javax.swing.GroupLayout(pnlLista);
        pnlLista.setLayout(pnlListaLayout);
        pnlListaLayout.setHorizontalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListaLayout.createSequentialGroup()
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrar))
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxUnidadOrganizativa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxUnidadTramite, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTipoTramite, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlListaLayout.setVerticalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxUnidadOrganizativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cbxUnidadTramite, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxTipoTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnEliminar)
                    .addComponent(btnModificar)
                    .addComponent(btnNuevo))
                .addGap(7, 7, 7))
        );

        pnlRequisitos.setBorder(javax.swing.BorderFactory.createTitledBorder("Requisitos del tipo de tramite"));

        jLabel8.setText("id");

        jLabel9.setText("Tipo");

        jLabel10.setText("U. Tramite");

        jLabel11.setText("Nombre");

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

        javax.swing.GroupLayout pnlRequisitosLayout = new javax.swing.GroupLayout(pnlRequisitos);
        pnlRequisitos.setLayout(pnlRequisitosLayout);
        pnlRequisitosLayout.setHorizontalGroup(
            pnlRequisitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRequisitosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRequisitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRequisitosLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre))
                    .addGroup(pnlRequisitosLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipoTramite))
                    .addGroup(pnlRequisitosLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlRequisitosLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUnidadTramite))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRequisitosLayout.createSequentialGroup()
                        .addGap(0, 162, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardar)))
                .addContainerGap())
        );
        pnlRequisitosLayout.setVerticalGroup(
            pnlRequisitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRequisitosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRequisitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRequisitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtUnidadTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRequisitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTipoTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRequisitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140)
                .addGroup(pnlRequisitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRequisitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSubEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblSubEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlRequisitos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxUnidadOrganizativaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUnidadOrganizativaActionPerformed
        mostrarCbxUnidadTramite();
    }//GEN-LAST:event_cbxUnidadOrganizativaActionPerformed

    private void cbxUnidadTramiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUnidadTramiteActionPerformed
        mostrarCbxTipoTramite();
    }//GEN-LAST:event_cbxUnidadTramiteActionPerformed

    private void cbxTipoTramiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoTramiteActionPerformed
        mostrarTabla();
    }//GEN-LAST:event_cbxTipoTramiteActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        if(getUnidadOrganizativa().getId_unidadOrganizativa()!=0){
            if(getUnidadTramite().getId_unidad_tramite()!=0){
                if(getTipoTramite().getId_tipoTramite()!=0){
                    modoGuardado="nuevo";
                    Util.AplicarSubencabezado(this, lblSubEncabezado, "Guardar-24", modoGuardado);
                    mostrarElementos(true);
                    
                    txtId.setText(Integer.toString(tblRequisito.getRowCount()+1));
                    txtUnidadTramite.setText(getUnidadTramite().getNombre());
                    txtTipoTramite.setText(getTipoTramite().getNombre());
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione un tipo de tramite", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione una unidad de tramite", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una unidad organizativa", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(getUnidadOrganizativa().getId_unidadOrganizativa()!=0){
            if(getUnidadTramite().getId_unidad_tramite()!=0){
                if(getTipoTramite().getId_tipoTramite()!=0){
                    if(tblRequisito.getSelectedRow()!=-1){
                        modoGuardado ="modificar";
                        Util.AplicarSubencabezado(this, lblSubEncabezado, "Actualizar-24", modoGuardado);
                        mostrarElementos(true);
                        
                        txtId.setText(Integer.toString(tblRequisito.getRowCount()));
                        txtUnidadTramite.setText(getUnidadTramite().getNombre());
                        txtTipoTramite.setText(getTipoTramite().getNombre());
                        txtNombre.setText(getRequisito().getDescipcion());

                    }else{
                        JOptionPane.showMessageDialog(null, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione un tipo de tramite", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione una unidad de tramite", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una unidad organizativa", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        modoGuardado = "";
        mostrarElementos(false);
        limpiarCasillas();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {    
            if(modoGuardado.equals("nuevo")){

                Requisito objeto = new Requisito();
                objeto.setDescipcion(txtNombre.getText().toUpperCase());
                objeto.setTipoTramite(getTipoTramite());
                requisitoLN.Insertar(objeto);

            }else{
                Requisito objeto = new Requisito();
                objeto.setId_requisito(getRequisito().getId_requisito());
                objeto.setDescipcion(txtNombre.getText().toUpperCase());
                objeto.setTipoTramite(getTipoTramite());
                requisitoLN.Modificar(objeto);
               
            }
        
            row = -1;
            modoGuardado = "";
            mostrarElementos(false);
            limpiarCasillas();
            mostrarTabla();
            tblRequisito.setRowSelectionInterval(tblRequisito.getRowCount()-1,tblRequisito.getRowCount()-1);
            Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        if(tblRequisito.getSelectedRow()!=-1){
            try { 
                if (JOptionPane.showConfirmDialog(this, "¿Desea Eliminar el Registro?","MENSAJE DEL SISTEMA",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    requisitoLN.Eliminar(getRequisito());
                    mostrarTabla();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "¿Desea Salir?","MENSAJE DEL SISTEMA",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cbxTipoTramite;
    private javax.swing.JComboBox cbxUnidadOrganizativa;
    private javax.swing.JComboBox cbxUnidadTramite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblSubEncabezado;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JPanel pnlRequisitos;
    private javax.swing.JTable tblRequisito;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTipoTramite;
    private javax.swing.JTextField txtUnidadTramite;
    // End of variables declaration//GEN-END:variables
}
