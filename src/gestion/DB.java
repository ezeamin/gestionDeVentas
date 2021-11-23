/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author EZEA2
 */
public class DB {
    String db_name;
    String userDB;
    String passDB;
    Connection conexion;
    
    DB(boolean crear,String _db_name,String _user,String _pass){
        db_name=_db_name;
        userDB=_user;
        passDB=_pass;
        
        if(crear) createDB();
    }
    
    public boolean checkConnection(){
        try{
            if(MySQLConnection()){
                MySQLCloseConnection();
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return false;
    }
    
    public void createDB(){
        try{
            System.out.println("-----Creacion de DB-----");
            MySQLFirstConnection();
            
            String query="DROP SCHEMA IF EXISTS "+db_name;
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            
            query="CREATE DATABASE "+db_name;
            st=conexion.createStatement();
            st.executeUpdate(query);
            System.out.println("Se ha creado la base de datos "+db_name);
            MySQLCloseConnection();
            
            System.out.println("\n-----Creacion de tablas-----");
            //tablas
            MySQLConnection();
            
            createTableEmpleados();
            createTableClientes();
            createTableProductos();
            createTableVentas();

            MySQLCloseConnection();
            JOptionPane.showMessageDialog(null, "Base de datos creada correctamente");
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error creando base de datos","Atencion",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void MySQLFirstConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306",userDB,passDB);
            //System.out.println("Se inicio exitosamente la conexion");
            System.out.println("Opened");
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public boolean MySQLConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db_name,userDB,passDB);
            //System.out.println("Se inicio exitosamente la conexion");
            
            System.out.println("Opened"); 
            return true;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return false;
    }
    
    public void MySQLCloseConnection(){
        try{
            conexion.close();
            //System.out.println("Se cerró la conexion");
            
            System.out.println("Closed");
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void createTableEmpleados(){
        try{
            String query="CREATE TABLE Empleados ("+
                         "Num INT(4) AUTO_INCREMENT,"+
                         "Usuario VARCHAR(20),"+
                         "Password VARCHAR(20),"+
                         "Apellido VARCHAR(15),"+
                         "Nombre VARCHAR(15),"+
                         "DNI LONG,"+
                         "Nacimiento DATE,"+
                         "Incorporacion DATE,"+
                         "Ventas INT(3),"+
                         "Admin INT(1),"+
                         "PRIMARY KEY(Num))";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            System.out.println("Se ha creado la tabla"+" empleados");
            
            newLine(false,"empleados","'admin'","'admin'","'admin'","'admin'",1,"'1111-11-11'","'1111-11-11'",0,1);
        }
        catch(Exception e){
            System.out.println("Error al crear tabla"+" empleados: "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear tabla empleados","Atencion",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void createTableClientes(){
        try{
            String query="CREATE TABLE Clientes ("+
                         "Num INT(4) AUTO_INCREMENT,"+
                         "Apellido VARCHAR(15),"+
                         "Nombre VARCHAR(15),"+
                         "DNI LONG,"+
                         "Direccion VARCHAR(20),"+
                         "ProdComprados INT(3),"+
                         "PRIMARY KEY(Num))";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            System.out.println("Se ha creado la tabla"+" clientes");
        }
        catch(Exception e){
            System.out.println("Error al crear tabla"+" clientes: "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear tabla clientes","Atencion",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void createTableProductos(){
        try{
            String query="CREATE TABLE Productos ("+
                         "Num INT(4) AUTO_INCREMENT,"+
                         "ID INT(4),"+
                         "Nombre VARCHAR(15),"+
                         "Precio FLOAT(8),"+
                         "Existencia INT(3),"+
                         "PRIMARY KEY(num))";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            System.out.println("Se ha creado la tabla"+" productos");
        }
        catch(Exception e){
            System.out.println("Error al crear tabla"+" productos: "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear tabla productos","Atencion",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void createTableVentas(){
        try{
            String query="CREATE TABLE Ventas ("+
                         "Num INT(4) AUTO_INCREMENT,"+
                         "Fecha DATE,"+
                         "Vendedor VARCHAR(15),"+
                         "Cliente VARCHAR(15),"+
                         "Importe DOUBLE,"+
                         "PRIMARY KEY(Num))";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            System.out.println("Se ha creado la tabla ventas");
        }
        catch(Exception e){
            System.out.println("Error al crear tabla ventas: "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear tabla ventas","Atencion",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /*public String getNameIfExists(boolean conectar,String table,long dni){
        try{
            if(conectar) MySQLConnection();
        
            String query="SELECT * FROM "+table+" WHERE DNI="+dni;
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(query);

            while (rs.next()) {
                String nombre="'"+rs.getString("Apellido")+", "+rs.getString("Nombre")+"'";
                if(conectar) MySQLCloseConnection();
                
                return nombre;
            }
            
            if(conectar) MySQLCloseConnection();
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    
        return null;
    }*/
    
    private boolean checkExistance(boolean conectar,String table,long dni){
        try{
            if(conectar) MySQLConnection();
        
            String query="SELECT DNI FROM "+table;
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(query);

            while (rs.next()) {
                if(dni==rs.getLong("dni")){
                    if(conectar) MySQLCloseConnection();
                    return true;
                }
            }
            
            if(conectar) MySQLCloseConnection();
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    
        return false;
    }
    
    private boolean checkExistance(boolean conectar,String table,String user){
        try{
            if(conectar) MySQLConnection();
        
            String query="SELECT usuario FROM "+table;
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(query);

            while (rs.next()) {
                if(user.equals(rs.getString("usuario"))){
                    if(conectar) MySQLCloseConnection();
                    return true;
                }
            }
            
            if(conectar) MySQLCloseConnection();
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    
        return false;
    }
    
    private boolean checkExistance(boolean conectar,String table,int id){
        try{
            if(conectar) MySQLConnection();
        
            String query="SELECT ID FROM "+table;
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(query);

            while (rs.next()) {
                if(id==rs.getInt("ID")){
                    if(conectar) MySQLCloseConnection();
                    return true;
                }
            }
            
            if(conectar) MySQLCloseConnection();
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    
        return false;
    }
    
    private int getAutoIncrement(String table) throws SQLException{
        int inc=0;
        
        String query="SELECT num FROM "+table;
        Statement st=conexion.createStatement();
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){
            inc=rs.getInt(1);
        }
            
        return inc;
            
    }
    
    private void resetAutoIncrement(String table) throws SQLException{
        int count=0;
        
        String query="SELECT count(*) FROM "+table;
        Statement st=conexion.createStatement();
        ResultSet rs=st.executeQuery(query);
        if(rs.next()) count=rs.getInt(1);
            
        if(count==0){
            query="ALTER TABLE "+table+" AUTO_INCREMENT=1";
            st=conexion.createStatement();
            st.executeUpdate(query);
        }
        else{
            int inc=getAutoIncrement(table)+1; //+1 porque es el siguiente
            query="ALTER TABLE "+table+" AUTO_INCREMENT="+Integer.toString(inc);
            st=conexion.createStatement();
            st.executeUpdate(query);
        }
            
    }
    
    //empleados
    public boolean newLine(boolean conectar,String table,String _user,String _pass,String lastname,String name,long dni,String birthDate,String firstDate,int sold,int admin){
        try{
            if(conectar) MySQLConnection();
            
            if(checkExistance(false,table,dni)){
                JOptionPane.showMessageDialog(null, "Ya existe un usuario con este DNI","Atencion",JOptionPane.WARNING_MESSAGE);
                if(conectar) MySQLCloseConnection();
                return false;
            }
            else if(checkExistance(false,table,_user)){
                JOptionPane.showMessageDialog(null, "Ya existe un usuario con este nombre de usuario","Atencion",JOptionPane.WARNING_MESSAGE);
                if(conectar) MySQLCloseConnection();
                return false;
            }
            
            resetAutoIncrement(table);
            
            String query="INSERT INTO "+table+"(usuario,password,apellido,nombre,dni,nacimiento,incorporacion,ventas,admin) VALUES("+
                        _user+","+
                        _pass+","+
                        lastname+","+
                        name+","+
                        dni+","+
                        birthDate+","+
                        firstDate+","+
                        sold+","+
                        admin+")";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Se cargó la linea en la tabla "+table);
            if(conectar) MySQLCloseConnection();
            
            return true;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return false;
    }
    
    //cliente
    public boolean newLine(boolean conectar,String table,String lastname,String name,long dni,String address,int number,int bought){
        try{
            if(conectar) MySQLConnection();
            
            if(checkExistance(false,table,dni)){
                JOptionPane.showMessageDialog(null, "Ya existe un usuario con este DNI","Atencion",JOptionPane.WARNING_MESSAGE);
                if(conectar) MySQLCloseConnection();
                return false;
            }
            
            resetAutoIncrement(table);
            
            String query="INSERT INTO "+table+"(apellido,nombre,dni,direccion,prodcomprados) VALUES("+
                        lastname+","+
                        name+","+
                        dni+","+
                        address+","+
                        bought+")";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Se cargó la linea en la tabla "+table);
            if(conectar) MySQLCloseConnection();
            
            return true;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return false;
    }
    
    //producto
    public boolean newLine(boolean conectar,String table,int id,String name,float price,int existance){
        try{
            if(conectar) MySQLConnection();
            
            if(checkExistance(false,table,id)){
                JOptionPane.showMessageDialog(null, "Ya existe un producto con este ID","Atencion",JOptionPane.WARNING_MESSAGE);
                if(conectar) MySQLCloseConnection();
                return false;
            }
            
            resetAutoIncrement(table);
            
            String query="INSERT INTO "+table+"(id,nombre,precio,existencia) VALUES("+
                        id+","+
                        name+","+
                        price+","+
                        existance+")";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Se cargó la linea en la tabla "+table);
            if(conectar) MySQLCloseConnection();
            
            return true;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return false;
    }
    
    //venta
    public boolean newLine(boolean conectar,String table,String date,String seller, String client,double price){
        try{
            if(conectar) MySQLConnection();
            
            resetAutoIncrement(table);
            
            String query="INSERT INTO "+table+"(Fecha,Vendedor,Cliente,Importe) VALUES("+
                        date+","+
                        seller+","+
                        client+","+
                        price+")";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Se cargó la linea en la tabla "+table);
            if(conectar) MySQLCloseConnection();
            
            return true;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return false;
    }
    
    public String login(String _user,String _pass) throws SQLException{
        String pass;
        try{
            MySQLConnection();
            pass="";

            String query="SELECT password FROM empleados WHERE usuario='"+_user+"'";
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(query);

            if (rs.next()) pass=rs.getString("password");
            
            MySQLCloseConnection();
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return null; //error de conexion
        }
        
        return pass;
    }
    
    public Vendedor createObjectVendedor(String _user) throws SQLException{
        try{
            MySQLConnection();

            String query="SELECT * FROM empleados WHERE usuario='"+_user+"'";
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            
            String user,pass,lastname,name,nacimiento,incorporacion;
            long dni;
            int ventas;
            boolean isAdmin=false;
            Vendedor vend = null;

            if(rs.next()){
                user=rs.getString("usuario");
                pass=rs.getString("password");
                lastname=rs.getString("apellido");
                name=rs.getString("nombre");
                dni=rs.getLong("dni");
                nacimiento=rs.getString("nacimiento");
                incorporacion=rs.getString("incorporacion");
                ventas=rs.getInt("ventas");
                
                if(rs.getInt("admin")==1) isAdmin=true;
                
                vend=new Vendedor(user,pass,lastname,name,dni,nacimiento,incorporacion,ventas,isAdmin);
            }

            MySQLCloseConnection();
            
            return vend;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return null;
    }
    
    public Cliente createObjectCliente(long dni) throws SQLException{
        try{
            MySQLConnection();

            String query="SELECT * FROM clientes WHERE dni="+dni;
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            
            String lastname,name,direccion;
            Cliente client = null;

            if(rs.next()){
                lastname=rs.getString("apellido");
                name=rs.getString("nombre");
                direccion=rs.getString("direccion");
                
                client=new Cliente(lastname,name,dni,direccion);
            }

            MySQLCloseConnection();
            
            return client;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return null;
    }
    
    private void refreshIndex(String table) throws SQLException{
        String query="SET @rownum = 0;";
        Statement st=conexion.createStatement();
        st.executeUpdate(query);        
        
        
        query="UPDATE "+table+" SET "+table+".Num = @rownum:=@rownum+1";
        st.executeUpdate(query);
    }   
    
    public boolean eraseLine(String table,long dni){
        boolean conn=false;
        
        try{
            MySQLConnection();

            String query="DELETE FROM "+table+" WHERE dni="+Long.toString(dni);
            Statement st=conexion.createStatement();
            if(st.executeUpdate(query)!=0) conn=true; //si ==0 significa que no encontró el usuario
            
            refreshIndex(table);
            
            MySQLCloseConnection();
            return conn;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return false;
    }
    
    public boolean eraseLine(String table,int id){
        boolean conn=false;
        
        try{
            MySQLConnection();

            String query="DELETE FROM "+table+" WHERE id="+Integer.toString(id);
            Statement st=conexion.createStatement();
            if(st.executeUpdate(query)!=0) conn=true; //si ==0 significa que no encontró el producto
            
            refreshIndex(table);
            
            MySQLCloseConnection();
            return conn;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return false;
    }
    
    public ResultSetMetaData getColumnNames(String table){
        ResultSet rs;
        ResultSetMetaData meta;
        try{
            MySQLConnection();

            String query="SELECT * FROM "+table+" WHERE num=0";
            Statement st=conexion.createStatement();
            rs=st.executeQuery(query);
            
            meta=rs.getMetaData(); 

            MySQLCloseConnection();
            return meta;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return null;
    }
    
    public int getRowCount(String table){
        int rows=0;
        try{
            MySQLConnection();

            String query="SELECT COUNT(*) FROM "+table;
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            
            if(rs.next()) rows=rs.getInt(1);
            
            MySQLCloseConnection();
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return rows;
    }
    
    public String[] getRow(String table,int i) throws SQLException{
        ResultSet rs;
        ResultSetMetaData meta;
        String info[];
        try{
            MySQLConnection();

            String query="SELECT * FROM "+table+" WHERE num="+Integer.toString(i);
            Statement st=conexion.createStatement();
            rs=st.executeQuery(query);
            
            meta=rs.getMetaData();
            int cant=meta.getColumnCount();
            
            info=new String[cant];
            
            if(rs.next()){
                for(int j=0;j<cant;j++){
                    info[j]=rs.getString(j+1);
                } 
            }
            
            MySQLCloseConnection();
            
            return info;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return null;
    }
    
    private int getExistance(int id){
        ResultSet rs;
        int cantidad=-1;
        
        try{
            MySQLConnection();

            String query="SELECT * FROM productos WHERE id="+Integer.toString(id);
            Statement st=conexion.createStatement();
            rs=st.executeQuery(query);
            
            if(rs.next()){
                cantidad=rs.getInt("Existencia");
            }
            
            MySQLCloseConnection();
            
            return cantidad;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return -1;
    }
    
    public void deleteProducto(String[] info){
        int id=Integer.parseInt(info[0]);
        int cantidad=Integer.parseInt(info[2]);
        int cant=getExistance(id)-1;

        if(cant<=0){
            eraseLine("productos",id); //me va a causar problemas si vuelvo a agregarlo
            return;
        }

        try {
            MySQLConnection();

            String query="UPDATE productos SET Existencia="+Integer.toString(cant)+" WHERE ID="+Integer.toString(id);
            Statement st=conexion.createStatement();
            st.executeUpdate(query);

            refreshIndex("productos");

            MySQLCloseConnection();
        } 
        catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }

    }
    
    public void addProducto(String[] info){
        int id=Integer.parseInt(info[0]);
        int cantidad=Integer.parseInt(info[2]);
        int cant=getExistance(id)+1;

        try {
            MySQLConnection();

            String query="UPDATE productos SET Existencia="+Integer.toString(cant)+" WHERE ID="+Integer.toString(id);
            Statement st=conexion.createStatement();
            st.executeUpdate(query);

            refreshIndex("productos");

            MySQLCloseConnection();
        } 
        catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
