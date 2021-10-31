/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

/**
 *
 * @author EZEA2
 */
public class Vendedor {
    private String user;
    private String password;
    private String lastname;
    private String name;
    private long dni;
    private String fechaNacimiento;
    private String fechaIncorporacion;
    private int vendidos;
    private boolean admin;

    public Vendedor(String user, String password, String lastname, String name, long dni, String fechaNacimiento, String fechaIncorporacion, int vendidos, boolean isAdmin) {
        this.user = user;
        this.password = password;
        this.lastname = lastname;
        this.name = name;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIncorporacion = fechaIncorporacion;
        this.vendidos = vendidos;
        this.admin = isAdmin;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }

    public long getDni() {
        return dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public int getVendidos() {
        return vendidos;
    }

    public boolean isAdmin() {
        return admin;
    }
    
    
           
}
