package GestionTramiteDocumentos.Presentacion;

import GestionTramiteDocumentos.Entidades.Usuario;
import GestionTramiteDocumentos.LogicaNegocio.GeneradorUserPass;
import GestionTramiteDocumentos.LogicaNegocio.UsuarioLN;
import Util.Util;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;


/**
 *
 * @author Alex
 */
public class IngresoSistema extends javax.swing.JDialog {
    
    UsuarioLN usuarioLN = new UsuarioLN();
    
    public IngresoSistema(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        Util.AplicarIcono(this,"MANTENIMIENTO-16X16");
        
        Util.InicializarContenedor(this.getContentPane());
        Util.AplicarSubencabezado(this,SubEncabezado,"IDENTIFICACION");
//        Util.InicializarContenedor(this);
        this.setLocationRelativeTo(null);
        this.setTitle("[IDENTIFICACION] - INGRESO AL SISTEMA");
        Util.HabilitarContenedor(pnlIdentificacion,true);
        
        //lblIngreso.setIcon(new ImageIcon(getClass().getResource("IconosFinales/login.png")));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlIdentificacion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCuenta = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtPasword = new javax.swing.JPasswordField();
        lblIngreso = new javax.swing.JLabel();
        SubEncabezado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESO AL SISTEMA");

        pnlIdentificacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("CUENTA :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("CONTRASEÑA: ");

        txtCuenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtCuenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCuenta.setPreferredSize(new java.awt.Dimension(6, 24));

        btnIngresar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtPasword.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPasword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPasword.setPreferredSize(new java.awt.Dimension(111, 24));
        txtPasword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPaswordKeyTyped(evt);
            }
        });

        lblIngreso.setIcon(new javax.swing.ImageIcon("C:\\STD.UNPRG\\Software-copia\\Iconos\\src\\IconosFinales\\login.png")); // NOI18N

        javax.swing.GroupLayout pnlIdentificacionLayout = new javax.swing.GroupLayout(pnlIdentificacion);
        pnlIdentificacion.setLayout(pnlIdentificacionLayout);
        pnlIdentificacionLayout.setHorizontalGroup(
            pnlIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdentificacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlIdentificacionLayout.createSequentialGroup()
                        .addComponent(btnIngresar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addComponent(txtCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPasword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lblIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnlIdentificacionLayout.setVerticalGroup(
            pnlIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdentificacionLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(pnlIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlIdentificacionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPasword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngresar)
                    .addComponent(btnCancelar))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlIdentificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(SubEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(SubEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        try {
            GeneradorUserPass g = new GeneradorUserPass();
            String Cuenta = txtCuenta.getText();
            String Contraseña = g.encriptarPass(txtPasword.getText());
            
            Usuario oUsuario = usuarioLN.VerificarIngreso(Cuenta,Contraseña);
            
            if (oUsuario != null) {
                String msj = "BIENVENIDO\n " + oUsuario;
                JOptionPane.showMessageDialog(this, msj,"MENSAJE DEL SISTEMA",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                
                if (oUsuario.getCuenta().equals("ADM")) {
                    SistemaTramiteDocumentario Sistema = new SistemaTramiteDocumentario();
                    Sistema.setVisible(true);
                    Sistema.setDatosUsuario(oUsuario);
                } else {
                    SistemaTramiteDocumentario Sistema = new SistemaTramiteDocumentario();
                    Sistema.setVisible(true);
                    Sistema.setDatosUsuario(oUsuario);
                }

            } else {
                txtPasword.setText(null);
                String msj = "CUENTA O CONTRASEÑA INCORRECTOS";
                JOptionPane.showMessageDialog(this, msj,"MENSAJE DEL SISTEMA",JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
            
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtPaswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaswordKeyTyped
        char Tecla = evt.getKeyChar();
        
        if (Tecla == KeyEvent.VK_ENTER) {
            btnIngresar.doClick();
        }
    }//GEN-LAST:event_txtPaswordKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SubEncabezado;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblIngreso;
    private javax.swing.JPanel pnlIdentificacion;
    private javax.swing.JTextField txtCuenta;
    private javax.swing.JPasswordField txtPasword;
    // End of variables declaration//GEN-END:variables
}
