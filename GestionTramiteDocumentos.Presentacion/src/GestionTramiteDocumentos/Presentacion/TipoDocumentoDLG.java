package GestionTramiteDocumentos.Presentacion;


import GestionTramiteDocumentos.Entidades.TipoDocumento;
import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import GestionTramiteDocumentos.LogicaNegocio.TipoDocumentoLN;
import ModelosTablas.mdlTipoDocumentos;
import Util.Util;
import Util.mdlGeneral;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TipoDocumentoDLG extends javax.swing.JDialog {

    TipoDocumentoLN tipoDocumentoLN = new TipoDocumentoLN();
    List<TipoDocumento> tipoDocumentos = new ArrayList<TipoDocumento>();
    
    DefaultComboBoxModel mdlCbxUnidadOrganizativa;
    String modoGuardado = "";
    Integer row = -1;
    
    public TipoDocumentoDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        Util.InicializarContenedor(this.getContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("[MANTENIMIENTO] - TIPO DE DOCUMENTO");
        Util.AplicarEncabezado(this,lblEncabezado,"UNIDAD_TRAMITE.PNG-64","TIPO DE DOCUMENTO","Permite registrar o actualizar los datos de Tipos de Documentos");
        Util.AplicarSubencabezado(this,lblSubEncabezado,"CONFIG-32","Operacion");
        Util.HabilitarContenedor(pnlTipoDocumentos,true);
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
        txtNombre.setEnabled(estado);
        txtId.setEnabled(false);
        btnNuevo.setEnabled(!estado);
        btnModificar.setEnabled(!estado);
        btnEliminar.setEnabled(!estado);
        btnSalir.setEnabled(!estado);
        
        if (modoGuardado.equals("nuevo")) {
            tblTipoDocumentos.setRowSelectionAllowed(false);
            row = -1;
        }else{
            tblTipoDocumentos.setRowSelectionAllowed(true);
        }
    }

    public void limpiarCasillas(){
        txtNombre.setText("");
        txtId.setText("");
    }
    
    public void mdlTabla(){
        String Columnas[] = {"N°","Nombre"};
        
        tblTipoDocumentos.setModel(new mdlGeneral(Columnas));
        
        Integer[] Anchos = {50,400};
        Integer[] Alineaciones = {JLabel.CENTER,JLabel.LEFT};
        String[] Formatos = {"Cadena","Cadena"};
        String[] Modos = {"Normal","Normal"};
        
        Util.AplicarEstilos(tblTipoDocumentos, Anchos, Alineaciones, Formatos, Modos);
    }
    
    private List parseVector(List<TipoDocumento> lista) {
        List datos = new ArrayList();
        Object[] newdata;

        for(int i = 0; i < lista.size(); i++) {
            newdata = new Object[2];

            newdata[0] = i+1;
            newdata[1] = lista.get(i).getNombre();

            datos.add(newdata);
        }

        return datos;
    }

    public final void mostrarTabla(){
        try {          
            tipoDocumentos = tipoDocumentoLN.ConsultarAll();
            ((mdlGeneral)tblTipoDocumentos.getModel()).setData(parseVector(tipoDocumentos));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    

    public TipoDocumento getTipoDocumento(){
        TipoDocumento objeto = null;
        for (int i = 0; i < tipoDocumentos.size(); i++) {
            if(tblTipoDocumentos.getValueAt(tblTipoDocumentos.getSelectedRow(), 1).toString().equals(tipoDocumentos.get(i).getNombre())){
                objeto = tipoDocumentos.get(i);
                break;
            }
        }
        return objeto;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTipoDocumentos = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        pnlLista = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTipoDocumentos = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblEncabezado = new javax.swing.JLabel();
        lblSubEncabezado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlTipoDocumentos.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de documento"));
        pnlTipoDocumentos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlTipoDocumentos.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlTipoDocumentos.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jLabel2.setText("Id");
        pnlTipoDocumentos.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, -1, -1));

        jLabel3.setText("Nombre");
        pnlTipoDocumentos.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 56, 65, -1));

        txtId.setFocusable(false);
        pnlTipoDocumentos.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 27, 50, -1));
        pnlTipoDocumentos.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 53, 210, -1));

        pnlLista.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista"));

        tblTipoDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblTipoDocumentos);

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

        jLabel1.setText("Lista de documentos");

        javax.swing.GroupLayout pnlListaLayout = new javax.swing.GroupLayout(pnlLista);
        pnlLista.setLayout(pnlListaLayout);
        pnlListaLayout.setHorizontalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListaLayout.createSequentialGroup()
                        .addGap(0, 107, Short.MAX_VALUE)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir))
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlListaLayout.setVerticalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnEliminar)
                    .addComponent(btnModificar)
                    .addComponent(btnNuevo))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSubEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTipoDocumentos, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblSubEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTipoDocumentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (JOptionPane.showConfirmDialog(this, "¿Desea Salir?","MENSAJE DEL SISTEMA",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        modoGuardado="nuevo";
        txtId.setText(Integer.toString(tblTipoDocumentos.getRowCount()+1));
        mostrarElementos(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int fila = tblTipoDocumentos.getSelectedRow();
        if(fila!=-1){
            modoGuardado="modificar";
            txtId.setText(tblTipoDocumentos.getValueAt(fila, 0).toString());
            txtNombre.setText(tblTipoDocumentos.getValueAt(fila, 1).toString());
            mostrarElementos(true);
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        modoGuardado = "";
        mostrarElementos(false);
        limpiarCasillas();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {          
            if(modoGuardado.equals("nuevo")){
                TipoDocumento objeto = new TipoDocumento();
                objeto.setNombre(txtNombre.getText());
         
                tipoDocumentoLN.Insertar(objeto);
                txtId.setText(Integer.toString(tblTipoDocumentos.getRowCount()+1));
                mostrarTabla();
                
            }else{
                TipoDocumento objeto = new TipoDocumento();
                objeto.setId_tipoDocumento(getTipoDocumento().getId_tipoDocumento());
                objeto.setNombre(txtNombre.getText());
                tipoDocumentoLN.Modificar(objeto);
                
            }
            
            row = -1;
            modoGuardado = "";
            mostrarElementos(false);
            limpiarCasillas();

            mostrarTabla();
            tblTipoDocumentos.setRowSelectionInterval(tblTipoDocumentos.getRowCount()-1,tblTipoDocumentos.getRowCount()-1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } 
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblTipoDocumentos.getSelectedRow();
        if(fila!=-1){
            try {          
                if (JOptionPane.showConfirmDialog(this, "¿Desea Eliminar el Registro?","MENSAJE DEL SISTEMA",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    tipoDocumentoLN.Eliminar(getTipoDocumento());
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblSubEncabezado;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JPanel pnlTipoDocumentos;
    private javax.swing.JTable tblTipoDocumentos;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
