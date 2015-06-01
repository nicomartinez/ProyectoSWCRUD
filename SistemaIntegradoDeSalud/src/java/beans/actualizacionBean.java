/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.sql.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class actualizacionBean {

    private String nombres;
    private String apellidos;
    private String tipoDoc;
    private int numeroDoc;
    private Date fechaNacimiento;
    private String direccionResidencia;
    private String telefono;
    private int idCliente;
    
    
    
    public void actualizar(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance();
        String query="select nombre_cliente nombre, tipo_documento tipo, documento_cliente documento, fecha_nacimiento fecha, direccion_cliente direccion, telefono_cliente telefono "
                + "from clientes "
                + "where id_cliente="+idCliente;
        nombres=data.rows(query, "nombre");
        tipoDoc=data.rows(query, "tipo");
        numeroDoc= Integer.parseInt(data.rows(query, "documento"));
        fechaNacimiento= Date.valueOf(data.rows(query, "fecha"));
        direccionResidencia= data.rows(query,"direccion");
        telefono= data.rows(query, "telefono");   
        
        String actualizar= "update clientes set nombre_cliente='"+nombres
                +"', tipo_documento='"+tipoDoc
                +"',documento_cliente="+numeroDoc
                +",fecha_nacimiento='"+fechaNacimiento
                +"', direccion_cliente='"+direccionResidencia
                +"',telefono_cliente='"+telefono
                +"' where id_cliente="+idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

  
    public int getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(int numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
