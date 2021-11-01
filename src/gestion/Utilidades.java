/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.awt.Image;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EZEA2
 */
public class Utilidades {
    
    public void scaleImage(JLabel logo){
        ImageIcon icon = new ImageIcon("D:\\Documentos varios\\Documentos\\Facultad\\2do año\\Programacion 2\\Programas\\Gestion\\utilities\\computadora.png");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        logo.setIcon(scaledIcon);
    }
    
    public void cargarDatos(DB data,String table,DefaultTableModel tabla) throws SQLException{
        String info[];
        
        
        for(int i=1;i<=data.getRowCount(table);i++){
            info=data.getRow(table,i); 
            
            if(table.equals("empleados")){ //esconder num, user y password
                for(int j=0;j<info.length-3;j++){
                    info[j]=info[j+3];
                }
                
                if("1".equals(info[info.length-4])) info[info.length-4]="Si";
                else info[info.length-4]="No";
            }
            else{ //esconder num
                for(int j=0;j<info.length-1;j++){
                    info[j]=info[j+1];
                }
            }
            
            tabla.addRow(info); //agregar que solo muestre x columnas y no todas
        }
    }
}
