package GestionTramiteDocumentos.Presentacion;


import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import GestionTramiteDocumentos.LogicaNegocio.UnidadOrganizativaLN;
import Util.Util;
import Util.mdlGeneral;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UnidadOrganizativaDLG extends javax.swing.JDialog {

    UnidadOrganizativaLN unidadOrganizativaLN = new UnidadOrganizativaLN();
    UnidadOrganizativa unidadOrganizativa = new UnidadOrganizativa();
    List<UnidadOrganizativa> unidadOrganizativas = new ArrayList<UnidadOrganizativa>();
    
    String modoGuardado = "";
    
    public UnidadOrganizativaDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[MANTENIMIENTO] UNIDAD ORGANIZATIVA");
        Util.AplicarEncabezado(this,lblEncabezado,"UNIDAD_ORGANIZATIVA","UNIDAD ORGANIZATIVA","Permite registrar o actualizar los datos de una Unidad Organizativa");
        Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        Util.HabilitarContenedor(pnlLista, true);
        Util.HabilitarContenedor(pnlOperaciones,true);
        
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
        txtId.setEnabled(false);
        tblUnidadOrganizativa.setEnabled(!estado);
        btnNuevo.setEnabled(!estado);
        btnModificar.setEnabled(!estado);
        btnEliminar.setEnabled(!estado);
        btnCerrar.setEnabled(!estado);
        if (modoGuardado.equals("nuevo")) {
            tblUnidadOrganizativa.setRowSelectionAllowed(false);
            row = -1;
        }else{
            tblUnidadOrganizativa.setRowSelectionAllowed(true);
        }
    }

    public void limpiarCasillas(){
        txtAbreviatura.setText("");
        txtNombre.setText("");
        txtId.setText("");
    }

    public void mdlTabla(){
        String Columnas[] = {"N°","NOMBRE","ABREVIATURA"};
        
        tblUnidadOrganizativa.setModel(new mdlGeneral(Columnas));
        
        Integer[] Anchos = {30,360,190};
        Integer[] Alineaciones = {JLabel.CENTER,JLabel.LEFT,JLabel.LEFT};
        String[] Formatos = {"Cadena","Cadena","Cadena"};
        String[] Modos = {"Normal","Normal","Normal"};
        
        Util.AplicarEstilos(tblUnidadOrganizativa, Anchos, Alineaciones, Formatos, Modos);
    }
    
    public void mostrarTabla() {
        try{
            unidadOrganizativas = unidadOrganizativaLN.ConsultarAll();
            ((mdlGeneral)tblUnidadOrganizativa.getModel()).setData(parseVector(unidadOrganizativas));
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private List parseVector(List<UnidadOrganizativa> lista) {
        List datos = new ArrayList();
        Object[] newdata;

        for(int i = 0; i < lista.size(); i++) {
            newdata = new Object[3];

            newdata[0] = i+1;
            newdata[1] = lista.get(i).getNombre();
            newdata[2] = lista.get(i).getAbreviatura();

            datos.add(newdata);
        }

        return datos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlOperaciones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtAbreviatura = new javax.swing.JTextField();
        pnlLista = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUnidadOrganizativa = new javax.swing.JTable();
        lblEncabezado = new javax.swing.JLabel();
        lblSubEncabezado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlOperaciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Unidad organizativa"));
        pnlOperaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlOperaciones.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 80, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlOperaciones.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, -1, -1));

        jLabel2.setText("Id");
        pnlOperaciones.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 79, 20));

        jLabel3.setText("Nombre");
        pnlOperaciones.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 79, 20));

        jLabel4.setText("Abreviatura");
        pnlOperaciones.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 79, 20));
        pnlOperaciones.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 69, -1));
        pnlOperaciones.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 180, -1));
        pnlOperaciones.add(txtAbreviatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 180, -1));

        pnlLista.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista"));
        pnlLista.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlLista.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, -1, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlLista.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, -1, -1));

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        pnlLista.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, -1, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        pnlLista.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, -1, -1));

        jLabel1.setText("Unidades Organizativas");
        pnlLista.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 27, 159, -1));

        tblUnidadOrganizativa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblUnidadOrganizativa);

        pnlLista.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 59, 520, 250));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSubEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblSubEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(pnlOperaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        modoGuardado = "nuevo";
        Util.AplicarSubencabezado(this, lblSubEncabezado, "Guardar-24", modoGuardado);
        txtNombre.requestFocus();
        mostrarElementos(true);
        
        txtId.setText(Integer.toString(tblUnidadOrganizativa.getRowCount()+1));
    }//GEN-LAST:event_btnNuevoActionPerformed

    int row = -1;
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        row = tblUnidadOrganizativa.getSelectedRow();
        if (row != -1) {
            try {
                modoGuardado = "modificar";
                Util.AplicarSubencabezado(this, lblSubEncabezado, "Actualizar-24", modoGuardado);
                mostrarElementos(true);
                
                row = tblUnidadOrganizativa.getSelectedRow();
                txtId.setText(tblUnidadOrganizativa.getValueAt(row, 0).toString());
                txtNombre.setText(tblUnidadOrganizativa.getValueAt(row, 1).toString());
                txtAbreviatura.setText(tblUnidadOrganizativa.getValueAt(row, 2).toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            if (modoGuardado.equals("nuevo")) {
                unidadOrganizativa.setNombre(txtNombre.getText());
                unidadOrganizativa.setAbreviatura(txtAbreviatura.getText());            
                
                unidadOrganizativaLN.Insertar(unidadOrganizativa);
            } else {
                unidadOrganizativas.get(row).setNombre(txtNombre.getText());
                unidadOrganizativas.get(row).setAbreviatura(txtAbreviatura.getText());
                unidadOrganizativaLN.Modificar(unidadOrganizativas.get(row));
            }
            
            row = -1;
            modoGuardado = "";
            mostrarElementos(false);
            limpiarCasillas();

            mostrarTabla();
            tblUnidadOrganizativa.setRowSelectionInterval(tblUnidadOrganizativa.getRowCount()-1,tblUnidadOrganizativa.getRowCount()-1);
            Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        modoGuardado = "";
        mostrarElementos(false);
        limpiarCasillas();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (tblUnidadOrganizativa.getSelectedRow() >= 0) {
            try {
                if (JOptionPane.showConfirmDialog(this, "¿Desea Eliminar el Registro?","MENSAJE DEL SISTEMA",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    unidadOrganizativaLN.Eliminar(unidadSeleccionada());
                    mostrarTabla();    
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Elija un registro", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "¿Desea Salir?","MENSAJE DEL SISTEMA",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnCerrarActionPerformed
    
    public UnidadOrganizativa unidadSeleccionada() {
        UnidadOrganizativa objeto = null;
        int row = tblUnidadOrganizativa.getSelectedRow();
        String nombre = tblUnidadOrganizativa.getValueAt(row, 1).toString();
        String apellido = tblUnidadOrganizativa.getValueAt(row, 2).toString();
        for (int i = 0; i < unidadOrganizativas.size(); i++) {
            if (unidadOrganizativas.get(i).getNombre().equals(nombre) && unidadOrganizativas.get(i).getAbreviatura().equals(apellido)) {
                objeto = unidadOrganizativas.get(i);
                break;
            }
        }
        return objeto;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblSubEncabezado;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JPanel pnlOperaciones;
    private javax.swing.JTable tblUnidadOrganizativa;
    private javax.swing.JTextField txtAbreviatura;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
