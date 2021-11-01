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
public class Cliente {
    private String apellido;
    private String nombre;
    private long dni;
    private String direccion;

    public Cliente(String apellido, String nombre, long dni, String direccion) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public long getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }
    
    
}
