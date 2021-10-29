/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author EZEA2
 */
public class Logic {
    DB data;
    
    Logic(DB _data){
        data=_data;
    }
    
    public boolean login(String user,String pass,JLabel txtError,JTextField txtContrasenia){
        if("".equals(user) || "".equals(pass)) return false;
        
        try {
            String SQLPass=data.login(user,pass);
            
            if(SQLPass==null){
                txtError.setVisible(true);
                txtError.setText("Error de conexion. Pruebe nuevamente");
                return false;
            }
            
            if(SQLPass.equals(pass)){
                new GUIMenu(user,data).setVisible(true);
                return true;
            }
            else{
                txtError.setVisible(true);
                txtError.setText("Usuario o contraseña incorrecta");
                txtContrasenia.setText("");
                txtContrasenia.grabFocus();
                
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUILogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private boolean incorrectDate(int dia,int mes,int anio){
        DateTimeFormatter dtfAnio = DateTimeFormatter.ofPattern("yyyy");
        String anioAct=dtfAnio.format(LocalDateTime.now());
        
        DateTimeFormatter dtfMes = DateTimeFormatter.ofPattern("MM");
        String mesAct=dtfMes.format(LocalDateTime.now());
        
        DateTimeFormatter dtfDia = DateTimeFormatter.ofPattern("dd");
        String diaAct=dtfDia.format(LocalDateTime.now());
        
        if(anio>Integer.parseInt(anioAct) || anio<1900) return true;
        if(mes>Integer.parseInt(mesAct) || mes<1) return true;
        if(dia>Integer.parseInt(diaAct) || dia<1) return true;
        
        if(dia==31 && (mes==2 || mes==4 || mes==6 || mes==9 || mes==11)) return true;
        if(dia==30 && mes==2) return true;
        if(dia==29 && mes==2 && anio%4!=0) return true;
        
        return false;
    }
    
    private boolean incorrectString(String str){
        for(int i=0;i<str.length();i++){
            if(!Character.isAlphabetic(str.charAt(i))) return true;
        }
        
        return false;
    }
    
    private boolean tienePunto(String str){
        for(int i=0;i<str.length();i++){
            if(!Character.isDigit(str.charAt(i))) return true;
        }
        
        return false;
    }
    
    public boolean nuevoVendedor(String _txtNombre,String _txtApellido,String _txtDNI,String _txtDiaNac,String _txtMesNac,String _txtAnioNac,String _txtUsuario,String _txtContrasenia,String _txtRepetirContrasenia, boolean isAdmin){
        float DNI;
        int dia=0,mes=0,anio=0;

        //COMPROBACIONES
        
        //campos vacios
        if("".equals(_txtApellido) || "".equals(_txtNombre) || "".equals(_txtUsuario) || "".equals(_txtContrasenia) || "".equals(_txtRepetirContrasenia) || "".equals(_txtDiaNac) || "".equals(_txtMesNac) || "".equals(_txtAnioNac) || "".equals(_txtDNI)){
            JOptionPane.showMessageDialog(null, "Complete todos los campos","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }

        //contraseña
        if(!_txtContrasenia.equals(_txtRepetirContrasenia)) {
            JOptionPane.showMessageDialog(null,"Las contraseñas no coinciden","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //dni
        if(tienePunto(_txtDNI)){
            JOptionPane.showMessageDialog(null, "DNI no valido. Ingrese solo numeros","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try{
            DNI=Float.parseFloat(_txtDNI);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "DNI no valido. Ingrese solo numeros","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(_txtDNI.length()>8 || _txtDNI.length()<7){
            JOptionPane.showMessageDialog(null, "DNI no valido","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //fecha
        try{
            dia=Integer.parseInt(_txtDiaNac);
            mes=Integer.parseInt(_txtMesNac);
            anio=Integer.parseInt(_txtAnioNac);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Fecha no valida. Ingrese solo numeros","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(incorrectDate(dia,mes,anio)){
            JOptionPane.showMessageDialog(null, "Fecha de nacimiento no valida","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //nombre y apellido
        if(incorrectString(_txtNombre) || incorrectString(_txtApellido)){
            JOptionPane.showMessageDialog(null, "Nombre y/o apellido no valido(s)","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //FIN COMPROBACIONES
        
        int admin=0;
        
        String fecha="'"+_txtAnioNac+"-"+_txtMesNac+"-"+_txtDiaNac+"'";
        
        if(isAdmin) admin=1;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaAct="'"+dtf.format(LocalDateTime.now())+"'";
        
        String txtUsuario="'"+_txtUsuario+"'";
        String txtContrasenia="'"+_txtContrasenia+"'";
        
        _txtApellido=Character.toUpperCase(_txtApellido.charAt(0))+(_txtApellido.toLowerCase()).substring(1);
        String txtApellido="'"+_txtApellido+"'";
        _txtNombre=Character.toUpperCase(_txtNombre.charAt(0))+(_txtNombre.toLowerCase()).substring(1);
        String txtNombre="'"+_txtNombre+"'";
        
        if(data.newLine(true,"empleados", txtUsuario, txtContrasenia, txtApellido, txtNombre, DNI,fecha,fechaAct,0,admin)) {
            JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");
            return true;
        }
        
        return false;
    }
    
    public boolean eliminarVendedor(String _txtDNI,double thisDNI){
        float DNI;
     
        if(tienePunto(_txtDNI)){
            JOptionPane.showMessageDialog(null, "DNI no valido. Ingrese solo numeros","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try{
            DNI=Float.parseFloat(_txtDNI); //un punto lo toma correcto
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "DNI no valido. Ingrese solo numeros","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(_txtDNI.length()>8 || _txtDNI.length()<7){
            JOptionPane.showMessageDialog(null, "DNI no valido","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (DNI==11111111){
            JOptionPane.showMessageDialog(null, "No se puede eliminar este usuario","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (DNI==thisDNI){
            JOptionPane.showMessageDialog(null, "No puedes eliminar el usuario actual","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(!data.eraseLine("empleados",DNI)){
            JOptionPane.showMessageDialog(null, "Vendedor no encontrado","Atencion",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else {
            JOptionPane.showMessageDialog(null, "Vendedor eliminado");
            return true;
        }
    }
    
    public String[] getColumnNames(String table) throws SQLException{
        ResultSetMetaData meta=data.getColumnNames(table);
        Integer cantColumnas = meta.getColumnCount();
        
        String[] names=new String[cantColumnas];

        for(int i=1;i<=cantColumnas;i++){
           names[i-1] = meta.getColumnLabel(i);
        }
      
        return names;
    }
}
