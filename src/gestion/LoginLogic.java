/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author EZEA2
 */
public class LoginLogic {
    DB data;
    
    LoginLogic(DB _data){
        data=_data;
    }
    
    public boolean login(String user,String pass,JLabel txtError,JTextField txtContrasenia){
        try {
            if(data.login(user,pass)){
                new GUIMenu(user).setVisible(true);
                return true;
            }
            else{
                txtError.setVisible(true);
                txtContrasenia.setText("");
                txtContrasenia.grabFocus();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUILogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
