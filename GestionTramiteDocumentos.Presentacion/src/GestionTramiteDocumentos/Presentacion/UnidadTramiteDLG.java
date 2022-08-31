package GestionTramiteDocumentos.Presentacion;


import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import GestionTramiteDocumentos.LogicaNegocio.UnidadOrganizativaLN;
import GestionTramiteDocumentos.LogicaNegocio.UnidadTramiteLN;
import Util.Util;
import Util.mdlGeneral;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class UnidadTramiteDLG extends javax.swing.JDialog {
    
    UnidadOrganizativaLN unidadOrganizativaLN = new UnidadOrganizativaLN();
    UnidadTramiteLN unidadTramiteLN = new UnidadTramiteLN();
    List<UnidadTramite> unidadTramites = new ArrayList<UnidadTramite>();
    
    DefaultComboBoxModel modeloCbx;
    String modoGuardado = "";
    int row = -1;

    public UnidadTramiteDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        mostrarCbx();
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[MANTENIMIENTO] UNIDAD DE TRAMITE");
        Util.AplicarEncabezado(this,lblEncabezado,"UNIDAD_TRAMITE.PNG-64","UNIDAD DE TRÁMITE","Permite registrar o actualizar los datos de una Unidad de Trámite");
        Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        Util.HabilitarContenedor(pnlUnidadTramite,true);
        Util.HabilitarContenedor(pnlLista, true);
        
        mdlTabla();
        mostrarTabla();
        limpiarCasillas();
        mostrarElementos(false);
    }
    
    private void mostrarElementos(boolean estado){
        txtNombre.setEnabled(estado);
        btnGuardar.setEnabled(estado);
        btnCancelar.setEnabled(estado);
        txtAbreviatura.setEnabled(estado);
        txtNombre.setEnabled(estado);
        txtResponsable.setEnabled(estado);
        txtUnidadOrganizativa.setEnabled(false);
        txtId.setEnabled(false);
        tblUnidadTramite.setEnabled(!estado);
        btnNuevo.setEnabled(!estado);
        btnModificar.setEnabled(!estado);
        btnEliminar.setEnabled(!estado);
        btnSalir.setEnabled(!estado);
        
        if (modoGuardado.equals("nuevo")) {
            tblUnidadTramite.setRowSelectionAllowed(false);
            row = -1;
        }else{
            tblUnidadTramite.setRowSelectionAllowed(true);
        }
    }

    public final void limpiarCasillas(){
        txtAbreviatura.setText("");
        txtNombre.setText("");
        txtId.setText("");
        txtUnidadOrganizativa.setText("");
        txtResponsable.setText("");
    }
    
    public final void mdlTabla(){
        String Columnas[] = {"N°","Nombre","Abreviatura","Responsable"};
        
        tblUnidadTramite.setModel(new mdlGeneral(Columnas));
        
        Integer[] Anchos = {30,250,100,200};
        Integer[] Alineaciones = {JLabel.CENTER,JLabel.LEFT,JLabel.LEFT,JLabel.LEFT};
        String[] Formatos = {"Cadena","Cadena","Cadena","Cadena"};
        String[] Modos = {"Normal","Normal","Normal","Normal"};
        
        Util.AplicarEstilos(tblUnidadTramite, Anchos, Alineaciones, Formatos, Modos);
    }
    
    public final void mostrarTabla(){
        try {          
            UnidadOrganizativa objetoSelect =  new UnidadOrganizativa();
            objetoSelect = (UnidadOrganizativa)cbxUnidadOrganizativa.getSelectedItem();
                
            if(objetoSelect.getId_unidadOrganizativa()==0){
                unidadTramites = unidadTramiteLN.ConsultarAll();
                ((mdlGeneral)tblUnidadTramite.getModel()).setData(parseVector(unidadTramites));
            }else{
                unidadTramites = unidadTramiteLN.ConsultarAll(objetoSelect);
                ((mdlGeneral)tblUnidadTramite.getModel()).setData(parseVector(unidadTramites));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private List parseVector(List<UnidadTramite> lista) {
        List datos = new ArrayList();
        Object[] newdata;

        for(int i = 0; i < lista.size(); i++) {
            newdata = new Object[4];

            newdata[0] = i+1;
            newdata[1] = lista.get(i).getNombre();
            newdata[2] = lista.get(i).getAbreviatura();
            newdata[3] = lista.get(i).getResponsable();

            datos.add(newdata);
        }

        return datos;
    }

    
    public final void mostrarCbx(){
        try {
            UnidadOrganizativa primeraOpcion = new UnidadOrganizativa(0, "TODAS", null, null);
            List<UnidadOrganizativa> unidadOrganizativas = new ArrayList<UnidadOrganizativa>();
            unidadOrganizativas=unidadOrganizativaLN.ConsultarAll();
            unidadOrganizativas.add(0,primeraOpcion);
            modeloCbx = new DefaultComboBoxModel(unidadOrganizativas.toArray());
            cbxUnidadOrganizativa.setModel(modeloCbx);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public UnidadTramite getUnidadTramiteTBL(int fila){
        UnidadTramite objeto = null;
        try {
            String nombre = tblUnidadTramite.getValueAt(fila,1).toString();
            for (int i = 0; i < unidadTramites.size(); i++) {
                if(unidadTramites.get(i).getNombre().toString().equals(nombre)){
                    objeto = unidadTramites.get(i);
                    System.out.println("id : "+objeto.getId_unidad_tramite());
                    System.out.println("nombre: "+objeto.getNombre());
                    break;
                }
            }
            return objeto;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return objeto;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlUnidadTramite = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtUnidadOrganizativa = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtAbreviatura = new javax.swing.JTextField();
        txtResponsable = new javax.swing.JTextField();
        pnlLista = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cbxUnidadOrganizativa = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUnidadTramite = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        lblEncabezado = new javax.swing.JLabel();
        lblSubEncabezado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlUnidadTramite.setBorder(javax.swing.BorderFactory.createTitledBorder("Unidad de tramite"));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel1.setText("Id");

        jLabel2.setText("Unidad Org");

        jLabel3.setText("Nombre");

        jLabel4.setText("Abreviatura");

        jLabel5.setText("Responsable");

        txtId.setEnabled(false);
        txtId.setFocusable(false);

        txtUnidadOrganizativa.setEnabled(false);
        txtUnidadOrganizativa.setFocusable(false);

        javax.swing.GroupLayout pnlUnidadTramiteLayout = new javax.swing.GroupLayout(pnlUnidadTramite);
        pnlUnidadTramite.setLayout(pnlUnidadTramiteLayout);
        pnlUnidadTramiteLayout.setHorizontalGroup(
            pnlUnidadTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnidadTramiteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUnidadTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUnidadTramiteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar))
                    .addGroup(pnlUnidadTramiteLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre))
                    .addGroup(pnlUnidadTramiteLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAbreviatura))
                    .addGroup(pnlUnidadTramiteLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtResponsable))
                    .addGroup(pnlUnidadTramiteLayout.createSequentialGroup()
                        .addGroup(pnlUnidadTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUnidadTramiteLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlUnidadTramiteLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUnidadOrganizativa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 119, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlUnidadTramiteLayout.setVerticalGroup(
            pnlUnidadTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnidadTramiteLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlUnidadTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUnidadTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUnidadOrganizativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUnidadTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUnidadTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAbreviatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUnidadTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(131, 131, 131)
                .addGroup(pnlUnidadTramiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pnlLista.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista"));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
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

        jLabel6.setText("Unidad organizativa");

        cbxUnidadOrganizativa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUnidadOrganizativa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUnidadOrganizativaActionPerformed(evt);
            }
        });

        tblUnidadTramite.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblUnidadTramite);

        jLabel7.setText("Unidades de tramite");

        javax.swing.GroupLayout pnlListaLayout = new javax.swing.GroupLayout(pnlLista);
        pnlLista.setLayout(pnlListaLayout);
        pnlListaLayout.setHorizontalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                            .addGroup(pnlListaLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlListaLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cbxUnidadOrganizativa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListaLayout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir)))
                .addContainerGap())
        );
        pnlListaLayout.setVerticalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxUnidadOrganizativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnEliminar)
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
                .addComponent(pnlUnidadTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(pnlUnidadTramite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxUnidadOrganizativaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUnidadOrganizativaActionPerformed
        mostrarTabla();
    }//GEN-LAST:event_cbxUnidadOrganizativaActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        if(!cbxUnidadOrganizativa.getSelectedItem().toString().equals("TODAS")){
            modoGuardado = "nuevo";

            Util.AplicarSubencabezado(this, lblSubEncabezado, "Guardar-24", modoGuardado);
            mostrarElementos(true);
            txtId.setText(Integer.toString(tblUnidadTramite.getRowCount()+1));
            txtUnidadOrganizativa.setText(((UnidadOrganizativa)cbxUnidadOrganizativa.getSelectedItem()).getAbreviatura());
            
        }else{
            JOptionPane.showMessageDialog(null, "Especifique: unidad organizativa", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            if(modoGuardado.equals("nuevo")){
                UnidadTramite unidadTramite = new UnidadTramite();
                unidadTramite.setNombre(txtNombre.getText().toUpperCase());
                unidadTramite.setAbreviatura(txtAbreviatura.getText().toUpperCase());
                unidadTramite.setResponsable(txtResponsable.getText().toUpperCase());
                unidadTramite.setUnidadOrganizativa((UnidadOrganizativa)cbxUnidadOrganizativa.getSelectedItem()); 
                System.out.println("Nom: "+unidadTramite.getUnidadOrganizativa().toString());
                unidadTramiteLN.Insertar(unidadTramite);
            }else{
                UnidadTramite unidadTramite = new UnidadTramite();
                int fila = tblUnidadTramite.getSelectedRow();
                unidadTramite.setId_unidad_tramite(getUnidadTramiteTBL(fila).getId_unidad_tramite());
                
                unidadTramite.setNombre(txtNombre.getText().toUpperCase());
                unidadTramite.setAbreviatura(txtAbreviatura.getText().toUpperCase());
                unidadTramite.setResponsable(txtResponsable.getText().toUpperCase());
                unidadTramiteLN.Modificar(unidadTramite);
                System.out.println("nivel");
            }
            row = -1;
            modoGuardado = "";
            mostrarElementos(false);
            limpiarCasillas();

            mostrarTabla();
            tblUnidadTramite.setRowSelectionInterval(tblUnidadTramite.getRowCount()-1,tblUnidadTramite.getRowCount()-1);
            Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

            if(tblUnidadTramite.getSelectedRow()>=0){
                modoGuardado = "modificar";
                Util.AplicarSubencabezado(this, lblSubEncabezado, "Actualizar-24", modoGuardado);
                mostrarElementos(true);
                
                UnidadTramite objeto = new UnidadTramite();
                objeto = getUnidadTramiteTBL(tblUnidadTramite.getSelectedRow());
                
                txtId.setText(Integer.toString(tblUnidadTramite.getRowCount()));
                txtUnidadOrganizativa.setText(objeto.getUnidadOrganizativa().getAbreviatura());
                txtNombre.setText(objeto.getNombre());
                txtAbreviatura.setText(objeto.getAbreviatura());
                txtResponsable.setText(objeto.getResponsable());
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione un registro de la lista", "Error", JOptionPane.WARNING_MESSAGE);
            }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
            if(tblUnidadTramite.getSelectedRow()>=0){
                try {
                    if (JOptionPane.showConfirmDialog(this, "¿Desea Eliminar el Registro?","MENSAJE DEL SISTEMA",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        unidadTramiteLN.Eliminar(getUnidadTramiteTBL(tblUnidadTramite.getSelectedRow()));
                        mostrarTabla();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione un registro de la lista", "Error", JOptionPane.WARNING_MESSAGE);
            }
    }//GEN-LAST:event_btnEliminarActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxUnidadOrganizativa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblSubEncabezado;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JPanel pnlUnidadTramite;
    private javax.swing.JTable tblUnidadTramite;
    private javax.swing.JTextField txtAbreviatura;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtResponsable;
    private javax.swing.JTextField txtUnidadOrganizativa;
    // End of variables declaration//GEN-END:variables
}
