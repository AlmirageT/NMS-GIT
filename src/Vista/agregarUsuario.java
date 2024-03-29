/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ContratosJpaController;
import Controlador.EmpresasJpaController;
import Controlador.PagosJpaController;
import Controlador.UsuariosJpaController;
import Modelo.ContratoEstados;
import Modelo.Contratos;
import Modelo.Empresas;
import Modelo.FormaPago;
import Modelo.Pagos;
import Modelo.Roles;
import Modelo.Usuarios;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author ivans
 */
public class agregarUsuario extends javax.swing.JFrame {

    private Usuarios usuario;
    private Contratos contrato;
    private Empresas empresa;
    private Pagos pago;
    private UsuariosJpaController controller;
    private PagosJpaController controllerPagos;
    private ContratosJpaController controllerContrato;
    private EmpresasJpaController controllerEmpresa;
    Date fecha = new Date();
    int mousepX;
    int mousepY;

    public agregarUsuario() {
        initComponents();
        controller = new UsuariosJpaController();
        controllerPagos = new PagosJpaController();
        controllerContrato = new ContratosJpaController();
        controllerEmpresa = new EmpresasJpaController();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblRutCliente = new javax.swing.JLabel();
        lblNombreCliente = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblApMat = new javax.swing.JLabel();
        lblClave = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblTelefonoCelular = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblApPat = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtAppPat = new javax.swing.JTextField();
        txtAppMat = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtClave = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        lblSubTitulo2 = new javax.swing.JLabel();
        lblSubTitulo3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMontoPagar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbFormaPago = new javax.swing.JComboBox<>();
        lblSubTitulo4 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblSubTitulo5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreEmpresa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDireccionEmpresa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTelefonoEmpresa = new javax.swing.JTextField();
        btnAgregar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(15, 14, 140));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_no_entry_32px_1.png"))); // NOI18N

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("No Mas Accidentes");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(739, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 870));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblTitulo.setText("Registro Cliente");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(524, 524, 524)
                .addComponent(lblTitulo)
                .addContainerGap(505, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 1300, 80));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Arial Narrow", 0, 36)); // NOI18N
        jLabel13.setText("X");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel13MousePressed(evt);
            }
        });
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1540, 20, -1, 30));

        lblRutCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblRutCliente.setText("Rut:");
        jPanel2.add(lblRutCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 81, 22));

        lblNombreCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNombreCliente.setText("Nombre:");
        jPanel2.add(lblNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 81, 22));

        lblFechaNacimiento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFechaNacimiento.setText("Fecha Nacimiento:");
        jPanel2.add(lblFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 260, -1, 22));

        lblApMat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblApMat.setText("Apellido Materno:");
        jPanel2.add(lblApMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 290, -1, 22));

        lblClave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblClave.setText("Clave:");
        jPanel2.add(lblClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 260, 66, 22));

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEmail.setText("Email:");
        jPanel2.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 114, 22));

        lblDireccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDireccion.setText("Direccion:");
        jPanel2.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 320, 81, 22));

        lblTelefonoCelular.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTelefonoCelular.setText("Celular:");
        jPanel2.add(lblTelefonoCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 320, 75, 22));

        lblTelefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTelefono.setText("Telefono:");
        jPanel2.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 70, 22));

        lblApPat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblApPat.setText("Apellido Paterno:");
        jPanel2.add(lblApPat, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, 114, 22));
        jPanel2.add(txtRut, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 150, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 150, -1));

        txtAppPat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAppPatKeyTyped(evt);
            }
        });
        jPanel2.add(txtAppPat, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 290, 150, -1));

        txtAppMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAppMatKeyTyped(evt);
            }
        });
        jPanel2.add(txtAppMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 290, 154, -1));
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 260, 150, -1));
        jPanel2.add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 260, 154, -1));

        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelularKeyTyped(evt);
            }
        });
        jPanel2.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 320, 154, -1));

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, 150, -1));

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        jPanel2.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, 150, -1));

        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregar.setText("Limpiar Datos");
        btnAgregar.setActionCommand("Limpiar Datos");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 720, 170, 50));

        lblSubTitulo2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSubTitulo2.setText("Datos Empresa");
        jPanel2.add(lblSubTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 700, -1, -1));

        lblSubTitulo3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSubTitulo3.setText("Datos Cliente");
        jPanel2.add(lblSubTitulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        jLabel1.setText("Fecha de Pago:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, -1, -1));

        jLabel2.setText("Monto a Pagar:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 470, -1, -1));

        txtMontoPagar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoPagarKeyTyped(evt);
            }
        });
        jPanel2.add(txtMontoPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 470, 150, -1));

        jLabel3.setText("Forma de Pago:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 470, -1, -1));

        cbFormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Credito", "Debito", "Efectivo" }));
        jPanel2.add(cbFormaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 460, 150, 30));

        lblSubTitulo4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSubTitulo4.setText("Datos de Pago");
        jPanel2.add(lblSubTitulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, -1, -1));

        jLabel4.setText("Fecha Inicio:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 620, -1, -1));

        jLabel5.setText("Fecha Termino:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 620, -1, -1));

        lblSubTitulo5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSubTitulo5.setText("Datos Contrato");
        jPanel2.add(lblSubTitulo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 570, -1, -1));

        jLabel6.setText("Nombre Empresa:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 750, -1, -1));

        txtNombreEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreEmpresaKeyTyped(evt);
            }
        });
        jPanel2.add(txtNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 750, 150, -1));

        jLabel7.setText("Razon Social:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 780, -1, -1));

        txtRazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyTyped(evt);
            }
        });
        jPanel2.add(txtRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 780, 150, -1));

        jLabel8.setText("Direccion:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 750, -1, -1));

        txtDireccionEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionEmpresaKeyTyped(evt);
            }
        });
        jPanel2.add(txtDireccionEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 750, 160, -1));

        jLabel9.setText("Telefono:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 780, -1, -1));

        txtTelefonoEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoEmpresaKeyTyped(evt);
            }
        });
        jPanel2.add(txtTelefonoEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 780, 160, -1));

        btnAgregar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregar1.setText("Agregar Cliente");
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 720, 170, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MousePressed
        dispose();
    }//GEN-LAST:event_jLabel13MousePressed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (isEmail(txtEmail.getText()) && isPass(txtClave.getText())) {
            Roles rl;
            rl = new Roles((BigDecimal.valueOf(3)), "Cliente");
            ContratoEstados ce;
            ce = new ContratoEstados(BigDecimal.valueOf(1), "En curso");
            int codigo = 1;
            boolean valor = false;
            do {
                codigo++;
                valor = controllerPagos.buscarCodigo(BigDecimal.valueOf(codigo));
            } while (valor == true);
            FormaPago fp = new FormaPago(BigDecimal.valueOf(cbFormaPago.getSelectedIndex()), cbFormaPago.getSelectedItem().toString());
            try {
                usuario = new Usuarios();
                usuario.setRut(txtRut.getText());
                usuario.setNombres(txtNombre.getText());
                usuario.setPaterno(txtAppPat.getText());
                usuario.setMaterno(txtAppMat.getText());
                usuario.setFechaNacimiento(fechaNacimiento.getDate());
                usuario.setEmail(txtEmail.getText());
                usuario.setClave(txtClave.getText());
                usuario.setCelular(Integer.parseInt(txtCelular.getText()));
                usuario.setTelefono(Integer.parseInt(txtTelefono.getText()));
                usuario.setDireccion(txtDireccion.getText());
                usuario.setEstado(Short.parseShort("1"));
                usuario.setFechaCreacion(fecha);
                usuario.setFechaModificacion(fecha);
                usuario.setRolesIdRol(rl);
                controller.create(usuario);
                pago = new Pagos();
                pago.setIdPago(BigDecimal.valueOf(codigo));
                pago.setFechaHora(dcFechaPago.getDate());
                pago.setMonto(Long.valueOf(txtMontoPagar.getText()));
                pago.setCreado(fecha);
                pago.setModificado(fecha);
                pago.setFormaPagoIdFormaPago(fp);
                pago.setContratosIdContrato(BigInteger.valueOf(codigo));
                controllerPagos.create(pago);
                contrato = new Contratos();
                contrato.setIdContrato(BigDecimal.valueOf(codigo));
                contrato.setFechaInicio(dcFechaInicio.getDate());
                contrato.setFechaTermino(dcFechaTermino.getDate());
                contrato.setCreado(fecha);
                contrato.setModificado(fecha);
                contrato.setMonto(Long.valueOf(txtMontoPagar.getText()));
                contrato.setIdContratoEstadoFk(ce);
                contrato.setPagosIdPago(pago);
                contrato.setRutCliente(txtRut.getText());
                contrato.setRazonSocial(txtRazonSocial.getText());
                controllerContrato.create(contrato);
                empresa = new Empresas();
                empresa.setNombre(txtNombreEmpresa.getText());
                empresa.setRazonSocial(txtRazonSocial.getText());
                empresa.setDireccion(txtDireccionEmpresa.getText());
                empresa.setTelefono(Integer.parseInt(txtTelefonoEmpresa.getText()));
                empresa.setCreado(fecha);
                empresa.setModificado(fecha);
                empresa.setContratosIdContrato(BigInteger.valueOf(codigo));
                controllerEmpresa.create(empresa);
                limpiar();
                JOptionPane.showMessageDialog(this, "El usuario de Rut " + usuario.getRut() + " se creo satisfactoriamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Llenar datos solicitados", "Validación", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese mail con formato ejemplo@ejemplo.com");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int coordenadaX = evt.getXOnScreen();
        int coordenadaY = evt.getYOnScreen();
        this.setLocation(coordenadaX - mousepX, coordenadaY - mousepY);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        mousepX = evt.getX();
        mousepY = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_BACK_SPACE) && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar solo letras", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNombreEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpresaKeyTyped
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_BACK_SPACE) && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar solo letras", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNombreEmpresaKeyTyped

    private void txtAppPatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAppPatKeyTyped
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_BACK_SPACE) && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar solo letras", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtAppPatKeyTyped

    private void txtAppMatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAppMatKeyTyped
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_BACK_SPACE) && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar solo letras", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtAppMatKeyTyped

    private void txtCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && car != (char) KeyEvent.VK_BACK_SPACE) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar solo numeros", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtCelularKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && car != (char) KeyEvent.VK_BACK_SPACE) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar solo numeros", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtTelefonoEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoEmpresaKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && car != (char) KeyEvent.VK_BACK_SPACE) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar solo numeros", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtTelefonoEmpresaKeyTyped

    private void txtRazonSocialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && car != (char) KeyEvent.VK_BACK_SPACE) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar solo numeros", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtRazonSocialKeyTyped

    private void txtMontoPagarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoPagarKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && car != (char) KeyEvent.VK_BACK_SPACE) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar solo numeros", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtMontoPagarKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car < '0' || car > '9') && (car != (char) KeyEvent.VK_BACK_SPACE) && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar letras y numeros", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtDireccionEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionEmpresaKeyTyped
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car < '0' || car > '9') && (car != (char) KeyEvent.VK_BACK_SPACE) && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingresar letras y numeros", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDireccionEmpresaKeyTyped

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregar1ActionPerformed

    public boolean isEmail(String email) {
        Pattern pat = null;
        Matcher mat = null;
        String algo = "^[A-Za-z0-9+_.-]+@(.+)$";
        pat = Pattern.compile(algo);
        mat = pat.matcher(email);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPass(String pass) {
        Pattern pat = null;
        Matcher mat = null;
        String algo = "^[A-Za-z0-9+_.-]+$";
        pat = Pattern.compile(algo);
        mat = pat.matcher(pass);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(agregarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(agregarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(agregarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(agregarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new agregarUsuario().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregar1;
    private javax.swing.JComboBox<String> cbFormaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblApMat;
    private javax.swing.JLabel lblApPat;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblRutCliente;
    private javax.swing.JLabel lblSubTitulo2;
    private javax.swing.JLabel lblSubTitulo3;
    private javax.swing.JLabel lblSubTitulo4;
    private javax.swing.JLabel lblSubTitulo5;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefonoCelular;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtAppMat;
    private javax.swing.JTextField txtAppPat;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccionEmpresa;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMontoPagar;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreEmpresa;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtRut;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoEmpresa;
    // End of variables declaration//GEN-END:variables
    private void limpiar() {
        txtTelefonoEmpresa.setText("");
        txtTelefono.setText("");
        txtRut.setText("");
        txtRazonSocial.setText("");
        txtNombreEmpresa.setText("");
        txtNombre.setText("");
        txtMontoPagar.setText("");
        txtEmail.setText("");
        txtDireccionEmpresa.setText("");
        txtDireccion.setText("");
        txtClave.setText("");
        txtCelular.setText("");
        txtAppPat.setText("");
        txtAppMat.setText("");
        fechaNacimiento.setDate(null);
        dcFechaTermino.setDate(null);
        dcFechaPago.setDate(null);
        dcFechaInicio.setDate(null);
        cbFormaPago.setSelectedItem("Seleccione");
    }
}
