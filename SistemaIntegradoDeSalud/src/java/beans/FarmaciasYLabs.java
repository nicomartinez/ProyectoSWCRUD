/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author USUARIO
 */
public class FarmaciasYLabs {

    private String nombre;
    private String direccion;
    
    /**
     * Creates a new instance of FarmaciasYLabs
     */
    public FarmaciasYLabs(String inNombre, String inDireccion) {
        this.nombre = inNombre;
        this.direccion = inDireccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
