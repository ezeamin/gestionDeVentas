/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.*;
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
        
        if(crear) createDB(_db_name);
    }
    
    public void createDB(String name){
        try{
            System.out.println("-----Creacion de DB-----");
            MySQLFirstConnection();
            
            String query="DROP SCHEMA IF EXISTS "+name; //modificar despues!!!
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            
            query="CREATE DATABASE "+name;
            st=conexion.createStatement();
            st.executeUpdate(query);
            System.out.println("Se ha creado la base de datos "+name);
            MySQLCloseConnection();
            
            System.out.println("\n-----Creacion de tablas-----");
            //tablas
            MySQLConnection();
            
            createTableEmpleados();
            createTableClientes();
            createTableProductos();
            createTableVentas();
            
            //info
            /*System.out.println("\n-----Cargar Info-----");
            newLine(false,"clientes","'Amin'","'Carlos'",17860733,"'Pringles 425'",3);
            newLine(false,"productos",1111,"'Computadora'",123,3);
            newLine(false,"ventas","'2020-09-26'","'Eze'","'Amin'",123);*/
            
            MySQLCloseConnection();
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
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
                         "Usuario VARCHAR(20),"+
                         "Password VARCHAR(20),"+
                         "Apellido VARCHAR(15),"+
                         "Nombre VARCHAR(15),"+
                         "DNI DOUBLE PRECISION,"+
                         "Nacimiento DATE,"+
                         "Incorporacion DATE,"+
                         "Ventas INT(3),"+
                         "Admin INT(1))";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            System.out.println("Se ha creado la tabla"+" empleados");
            
            newLine(false,"empleados","'admin'","'admin'","'admin'","'admin'",11111111,"'1111-11-11'","'1111-11-11'",0,1);
        }
        catch(Exception e){
            System.out.println("Error al crear tabla"+" empleados: "+e.getMessage());
        }
    }
    
    public void createTableClientes(){
        try{
            String query="CREATE TABLE Clientes ("+
                         "Apellido VARCHAR(15),"+
                         "Nombre VARCHAR(15),"+
                         "DNI DOUBLE PRECISION,"+
                         "Direccion VARCHAR(20),"+
                         "ProdComprados INT(3))";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            System.out.println("Se ha creado la tabla"+" clientes");
        }
        catch(Exception e){
            System.out.println("Error al crear tabla"+" clientes: "+e.getMessage());
        }
    }
    
    public void createTableProductos(){
        try{
            String query="CREATE TABLE Productos ("+
                         "ID INT(4),"+
                         "Nombre VARCHAR(15),"+
                         "Precio FLOAT(8),"+
                         "Existencia INT(3))";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            System.out.println("Se ha creado la tabla"+" productos");
        }
        catch(Exception e){
            System.out.println("Error al crear tabla"+" productos: "+e.getMessage());
        }
    }
    
    public void createTableVentas(){
        try{
            String query="CREATE TABLE Ventas ("+
                         "Num INT(4) AUTO_INCREMENT,"+
                         "Fecha DATE,"+
                         "Vendedor VARCHAR(15),"+
                         "Cliente VARCHAR(15),"+
                         "Importe FLOAT(8),"+
                         "PRIMARY KEY(Num))";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            System.out.println("Se ha creado la tabla"+" ventas");
        }
        catch(Exception e){
            System.out.println("Error al crear tabla"+" ventas: "+e.getMessage());
        }
    }
    
    public boolean checkExistance(boolean conectar,String table,double dni){
        
        try{
            if(conectar) MySQLConnection();
        
            String query="SELECT DNI FROM "+table;
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(query);

            while (rs.next()) {
                if(dni==rs.getFloat("dni")){
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
    
    //empleados
    public boolean newLine(boolean conectar,String table,String _user,String _pass,String lastname,String name,double dni,String birthDate,String firstDate,int sold,int admin){
        try{
            if(conectar) MySQLConnection();
            
            if(checkExistance(false,table,dni)){
                JOptionPane.showMessageDialog(null, "Ya existe un usuario con este DNI","Atencion",JOptionPane.WARNING_MESSAGE);
                if(conectar) MySQLCloseConnection();
                return false;
            }
            
            String query="INSERT INTO "+table+" VALUES("+
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
    public void newLine(boolean conectar,String table,String lastname,String name,double dni,String address,int bought){
        try{
            if(conectar) MySQLConnection();
            
            String query="INSERT INTO "+table+" VALUES("+
                        lastname+","+
                        name+","+
                        dni+","+
                        address+","+
                        bought+")";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Se cargó la linea en la tabla "+table);
            if(conectar) MySQLCloseConnection();
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    //producto
    public void newLine(boolean conectar,String table,int id,String name,float price,int existance){
        try{
            if(conectar) MySQLConnection();
            
            String query="INSERT INTO "+table+" VALUES("+
                        id+","+
                        name+","+
                        price+","+
                        existance+")";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Se cargó la linea en la tabla "+table);
            if(conectar) MySQLCloseConnection();
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    //venta
    public void newLine(boolean conectar,String table,String date,String seller, String client,float price){
        try{
            if(conectar) MySQLConnection();
            
            String query="INSERT INTO "+table+"(Fecha,Vendedor,Cliente,Importe) VALUES("+
                        date+","+
                        seller+","+
                        client+","+
                        price+")";
            Statement st=conexion.createStatement();
            st.executeUpdate(query);
            
            System.out.println("Se cargó la linea en la tabla "+table);
            if(conectar) MySQLCloseConnection();
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public boolean login(String _user,String _pass) throws SQLException{
        boolean ret=false;
        try{
            MySQLConnection();
            String pass="";

            String query="SELECT password FROM empleados WHERE usuario='"+_user+"'";
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(query);

            if (rs.next()) pass=rs.getString("password");

            if(_pass.equals(pass)) ret=true;

            MySQLCloseConnection();
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }
        
        
        return ret;
    }
    
    public Vendedor createObjectVendedor(String _user) throws SQLException{
        try{
            MySQLConnection();

            String query="SELECT * FROM empleados WHERE usuario='"+_user+"'";
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            
            String user,pass,lastname,name,nacimiento,incorporacion;
            double dni;
            int ventas;
            boolean isAdmin=false;
            Vendedor vend = null;

            if(rs.next()){
                user=rs.getString("usuario");
                pass=rs.getString("password");
                lastname=rs.getString("apellido");
                name=rs.getString("nombre");
                dni=rs.getDouble("dni");
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
    
    public boolean eraseLine(String table,double dni){
        boolean conn=false;
        
        try{
            MySQLConnection();

            String query="DELETE FROM "+table+" WHERE dni="+Double.toString(dni);
            Statement st=conexion.createStatement();
            if(st.executeUpdate(query)!=0) conn=true; //si ==0 significa que no encontró el usuario
            
            MySQLCloseConnection();
            return conn;
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return false;
    }
}
