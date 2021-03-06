/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EZEA2
 */
public class GUIPago extends javax.swing.JFrame {

    /**
     * Creates new form GUIPago
     */
    Logic lo;
    DB data;
    double total,extra;
    ArrayList<String[]> ids;
    DefaultTableModel tabla;
    Vendedor vend;
    Cliente client;
    
    public GUIPago(DB _data,ArrayList _ids,Vendedor _vend) throws SQLException {
        initComponents();
        FlatLightLaf.setup();
        setLocationRelativeTo(null);
        
        data=_data;
        lo=new Logic(data);
        ids=_ids;
        vend=_vend;
        
        //acciones
        Action action = new AbstractAction(){ //para detectar el enter
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btnCargarActionPerformed(e);
            }
        };
        txtEntrada.addActionListener(action);
        
        Action action2 = new AbstractAction(){ //para detectar el enter
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btnValidarActionPerformed(e);
            }
        };
        txtCodigo.addActionListener(action2);
        
        initTable();
        
        new Utilidades().scaleImage(icon,"userIcon.jpg");
        
        txtApellido.setVisible(false);
        txtNombre.setVisible(false);
        txtDni.setVisible(false);
        txtDireccion.setVisible(false);
        lblVendedor.setText(vend.getName()+" "+vend.getLastname());
        setVisibleTarjeta(false);
    }
    
    private void initTable() throws SQLException{
        tabla=new DefaultTableModel();
        tabla.addColumn("ID");
        tabla.addColumn("Nombre");
        tabla.addColumn("Cantidad");
        tabla.addColumn("Precio");
        tablaTicket.setModel(tabla);
        
        new Utilidades().cargarDatos(tabla,ids);
        
        tablaTicket.setDefaultEditor(Object.class, null);
        
        total=new Utilidades().setTotal(txtTotal, tabla);
    }
    
    private void setVisibleTarjeta(boolean cond){
        lblTarjeta.setVisible(cond);
        lblVenc1.setVisible(cond);
        lblVenc2.setVisible(cond);
        lblCodigo.setVisible(cond);
        btnValidar.setVisible(cond);
        txtTarjeta.setVisible(cond);
        txtMesVenc.setVisible(cond);
        txtAnioVenc.setVisible(cond);
        txtCodigo.setVisible(cond);
        lblRecargo.setVisible(cond);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDni = new javax.swing.JLabel();
        txtApellido = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        btnProcesar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEntrada = new javax.swing.JTextField();
        btnCargar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        selector = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        lblTarjeta = new javax.swing.JLabel();
        lblVenc2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtTarjeta = new javax.swing.JTextField();
        lblVenc1 = new javax.swing.JLabel();
        txtMesVenc = new javax.swing.JTextField();
        txtAnioVenc = new javax.swing.JTextField();
        btnValidar = new javax.swing.JButton();
        lblRecargo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTicket = new javax.swing.JTable();
        txtTotal = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblVendedor = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(btnProcesar.getBorder());
        jPanel1.setFocusable(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Nombre:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Apellido:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 33, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Direccion:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 134, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("DNI:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 84, -1, -1));

        txtDni.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtDni.setText("dato");
        jPanel1.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 84, -1, -1));

        txtApellido.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtApellido.setText("dato");
        txtApellido.setAlignmentY(0.0F);
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 33, -1, -1));

        txtDireccion.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtDireccion.setText("dato");
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 134, -1, -1));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtNombre.setText("dato");
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 180, 20, 20));
        jPanel1.add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 90, 90));

        btnProcesar.setText("Procesar pago");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Pago");

        jLabel2.setText("Buscar cliente por dni:");

        btnCargar.setText("Cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(btnProcesar.getBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCodigo.setText("Codigo de seguridad:");
        jPanel2.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        selector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));
        selector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorActionPerformed(evt);
            }
        });
        jPanel2.add(selector, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 24, 100, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setText("Seleccionar metodo de pago:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 20, -1, -1));

        lblTarjeta.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTarjeta.setText("Numero de tarjeta:");
        jPanel2.add(lblTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        lblVenc2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblVenc2.setText("/");
        jPanel2.add(lblVenc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));
        jPanel2.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 155, 70, 30));
        jPanel2.add(txtTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 75, 220, 30));

        lblVenc1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblVenc1.setText("Vencimiento:");
        jPanel2.add(lblVenc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));
        jPanel2.add(txtMesVenc, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 115, 50, 30));
        jPanel2.add(txtAnioVenc, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 115, 50, 30));

        btnValidar.setText("Validar");
        btnValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarActionPerformed(evt);
            }
        });
        jPanel2.add(btnValidar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 210, -1, -1));

        lblRecargo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblRecargo.setForeground(new java.awt.Color(255, 0, 0));
        lblRecargo.setText("10% de recargo");
        jPanel2.add(lblRecargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 25, 140, 30));

        tablaTicket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaTicket);

        txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTotal.setText("0");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setText("Total: $");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Vendedor:");

        lblVendedor.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblVendedor.setText("nombre");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCargar))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnProcesar)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal)))
                        .addContainerGap(37, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblVendedor)
                        .addGap(172, 172, 172))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblVendedor))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(btnProcesar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        try {
            // TODO add your handling code here:
            client=lo.getCliente(txtEntrada.getText(),txtApellido,txtNombre,txtDni,txtDireccion);
        } catch (SQLException ex) {
            Logger.getLogger(GUIPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void selectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorActionPerformed
        // TODO add your handling code here:
        if(selector.getSelectedIndex()==1){
            lo.agregarCargoPorTarjeta(total,tabla);
            extra=total*0.10;
            total+=extra;
            setVisibleTarjeta(true);
        }
        else{
            if(tabla.getValueAt(tabla.getRowCount()-1, 0).equals("1")){
                tabla.removeRow(tabla.getRowCount()-1);
                total-=extra;
            }
            
            setVisibleTarjeta(false);
        }
        
        new Utilidades().setTotal(txtTotal,tabla);
    }//GEN-LAST:event_selectorActionPerformed

    private void btnValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarActionPerformed
        // TODO add your handling code here:
        lo.validarTarjeta(txtTarjeta.getText(),txtMesVenc.getText(),txtAnioVenc.getText(),txtCodigo.getText(),true);
    }//GEN-LAST:event_btnValidarActionPerformed

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
        // TODO add your handling code here:
        if(selector.getSelectedIndex()==1){
            if(!lo.validarTarjeta(txtTarjeta.getText(),txtMesVenc.getText(),txtAnioVenc.getText(),txtCodigo.getText(),false)) return;
        }
        
        if(client==null){
            JOptionPane.showMessageDialog(null,"Cargue datos de cliente","Atencion",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //cargar a DB
        if(lo.nuevaVenta(client,total,vend,ids)){
            JOptionPane.showMessageDialog(null,"Pago procesado con exito");
            dispose();
        }
        else JOptionPane.showMessageDialog(null,"Error procesando la compra","Atencion",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btnProcesarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        String[] info=new String[4];
        for(int i=0;i<tablaTicket.getRowCount();i++){
            for(int j=0;j<Integer.parseInt(tablaTicket.getValueAt(i, 2).toString());j++){
                info[0]=tablaTicket.getValueAt(i, 0).toString();
                info[1]=tablaTicket.getValueAt(i, 1).toString();
                info[2]=tablaTicket.getValueAt(i, 2).toString();
                info[3]=tablaTicket.getValueAt(i, 3).toString();
                
                data.addProducto(info);
            }
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnProcesar;
    private javax.swing.JButton btnValidar;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblRecargo;
    private javax.swing.JLabel lblTarjeta;
    private javax.swing.JLabel lblVenc1;
    private javax.swing.JLabel lblVenc2;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JComboBox<String> selector;
    private javax.swing.JTable tablaTicket;
    private javax.swing.JTextField txtAnioVenc;
    private javax.swing.JLabel txtApellido;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JLabel txtDireccion;
    private javax.swing.JLabel txtDni;
    private javax.swing.JTextField txtEntrada;
    private javax.swing.JTextField txtMesVenc;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JTextField txtTarjeta;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
