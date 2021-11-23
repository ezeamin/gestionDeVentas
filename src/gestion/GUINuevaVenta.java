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
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author EZEA2
 */
public class GUINuevaVenta extends javax.swing.JFrame {

    /**
     * Creates new form GUINuevaVenta
     */
    Logic lo;
    DB data;
    DefaultTableModel tabla1;
    DefaultTableModel tabla2;
    TableRowSorter sorter;
    ArrayList<String[]> ids;
    Vendedor vend;
    
    public GUINuevaVenta(DB _data,Vendedor _vend) throws SQLException {
        initComponents();
        FlatLightLaf.setup();
        setLocationRelativeTo(null);
        
        data=_data;
        lo=new Logic(data);
        ids=new ArrayList<String[]>();
        vend=_vend;
        
        Action action = new AbstractAction(){ //para detectar el enter
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btnAgregarActionPerformed(e);
            }
        };
        txtEntrada.addActionListener(action);
        
        initTable(true);
    }
    
    private void initTable(boolean opc) throws SQLException{
        tabla1=new DefaultTableModel();
        String names[]=lo.getColumnNames("productos");
        
        //hacer que no sea editable
        
        for(int i=1;i<names.length;i++){
            tabla1.addColumn(names[i]);
        }
        tablaProductos.setModel(tabla1);
        sorter = new TableRowSorter<>(tabla1);
        tablaProductos.setRowSorter(sorter);
        
        if(opc){
            tabla2=new DefaultTableModel();
            tabla2.addColumn("ID");
            tabla2.addColumn("Nombre");
            tabla2.addColumn("Cantidad");
            tabla2.addColumn("Precio");
            tablaTicket.setModel(tabla2);
        }
        
        new Utilidades().cargarDatos(data, "productos", tabla1);
        
        tablaProductos.setDefaultEditor(Object.class, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        txtBuscar = new javax.swing.JLabel();
        txtEntrada = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        JLabel3 = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaTicket = new javax.swing.JTable();
        txtTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Nueva venta");

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductos);

        txtBuscar.setText("Buscar producto");

        txtEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEntradaKeyTyped(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Total:");

        JLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        JLabel3.setText("$");

        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tablaTicket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaTicket);

        txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTotal.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal))
                            .addComponent(btnContinuar, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar)
                            .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar)
                            .addComponent(btnEliminar))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JLabel3)
                        .addComponent(txtTotal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnContinuar)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        int row;
        boolean end=false;
        
        //verificar que ese producto tiene existencia disponible
        //restar existencia en tabla Y DB (por si otra instancia esta haciendo la venta)
        
        try{
            int selectedRow=tablaProductos.getSelectedRow();
            String info[]=new String[4];
            
            row=sorter.convertRowIndexToModel(selectedRow);

            for(int i=0;i<4;i++){
                info[i]=tabla1.getValueAt(row, i).toString(); 
            }  
            
            if(info[3].equals("0")){
                JOptionPane.showMessageDialog(null,"No hay mas productos disponibles del item seleccionado","Atencion",JOptionPane.WARNING_MESSAGE);
                return;
            }

            info[3]=info[2];
            info[2]="1";

            for(int i=0;i<tabla2.getRowCount();i++){
                if(info[0].equals(tabla2.getValueAt(i, 0).toString())){
                    int cant=Integer.parseInt(tabla2.getValueAt(i, 2).toString());
                    float precio=Float.parseFloat(tabla2.getValueAt(i, 3).toString());

                    cant++;
                    tabla2.setValueAt(cant, i, 2);
                    end=true;
                    
                    info[2]=Integer.toString(cant);
                    info[3]=Float.toString(precio);
                    
                    ids.set(i,info);
                    
                    break;
                }
            }

            if(!end) {
                tabla2.addRow(info);
                ids.add(info);
            }

            new Utilidades().setTotal(txtTotal,tabla2);
            
            String[] info2=new String[4];
            for(int i=0;i<ids.size();i++){
                info2=ids.get(i);
                
                if(info2[0].equals(tabla1.getValueAt(row, 0).toString())){
                    break;
                }
            }
            
            data.deleteProducto(info2); //PERO SI NO SE HACE LA COMPRA, NO BORRAR
            initTable(false);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: No hay datos seleccionados","Atencion",JOptionPane.WARNING_MESSAGE);
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(GUINuevaVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void txtEntradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaKeyTyped
        // TODO add your handling code here:
        txtEntrada.getDocument().addDocumentListener(new DocumentListener() {
           @Override
           public void insertUpdate(DocumentEvent e) {
              search(txtEntrada.getText());
           }
           @Override
           public void removeUpdate(DocumentEvent e) {
              search(txtEntrada.getText());
           }
           @Override
           public void changedUpdate(DocumentEvent e) {
              search(txtEntrada.getText());
           }
           public void search(String str) {
              if (str.length() == 0) {
                  sorter.setRowFilter(null);
              } else {
                 sorter.setRowFilter(RowFilter.regexFilter("(?i)" +str));
              }
           }
        });
        
        txtEntrada.grabFocus();
    }//GEN-LAST:event_txtEntradaKeyTyped

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        boolean end=false;
        
        try{
            int row=tablaTicket.getSelectedRow();
            
            int cant=Integer.parseInt(tabla2.getValueAt(row, 2).toString());
            float precio=Float.parseFloat(tabla2.getValueAt(row, 3).toString());
            
            cant--;
            
            data.addProducto(ids.get(row));
            initTable(false);
            
            if(cant==0){
                tabla2.removeRow(row);
                ids.remove(row);
            }
            else{
                tabla2.setValueAt(cant, row, 2);
                
                String info[]=new String[4];
                info[0]=tabla2.getValueAt(row, 0).toString();
                info[1]=tabla2.getValueAt(row, 1).toString();
                info[2]=Integer.toString(cant);
                info[3]=Float.toString(precio);
                    
                ids.set(row,info);
            }
            
            new Utilidades().setTotal(txtTotal,tabla2);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: No hay datos seleccionados","Atencion",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        // TODO add your handling code here:
        if(ids.isEmpty()){
            JOptionPane.showMessageDialog(null, "Compra vacia","Atencion",JOptionPane.WARNING_MESSAGE);
            return;
        }
        int opc=JOptionPane.showInternalConfirmDialog(null, "¿Está seguro? No se podrá modificar la factura.","Atencion",0);
        if(opc==1) return;
        
        try {
            new GUIPago(data,ids,vend).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(GUINuevaVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        String[] info=new String[4];
        for(int i=0;i<tabla2.getRowCount();i++){
            for(int j=0;j<Integer.parseInt(tabla2.getValueAt(i, 2).toString());j++){
                info[0]=tabla2.getValueAt(i, 0).toString();
                info[1]=tabla2.getValueAt(i, 1).toString();
                info[2]=tabla2.getValueAt(i, 2).toString();
                info[3]=tabla2.getValueAt(i, 3).toString();
                
                data.addProducto(info);
            }
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabel3;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTable tablaTicket;
    private javax.swing.JLabel txtBuscar;
    private javax.swing.JTextField txtEntrada;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
