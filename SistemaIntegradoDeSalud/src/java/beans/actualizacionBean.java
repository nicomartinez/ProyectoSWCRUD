/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class actualizacionBean {

    private String nombres;
    private String tipoDoc;
    private int numeroDoc;
    private Date fechaNacimiento;
    private String direccionResidencia;
    private String telefono;
    private int idCliente;
    
    public actualizacionBean(){
        ConnectionBean data = ConnectionBean.getInstance();
        Client client = Client.getInstance();
        if(client.getIdUser()>0){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String query="select nombre_cliente, tipo_documento, documento_cliente, "
                    + "fecha_nacimiento, direccion_cliente direccion, telefono_cliente telefono "
                    + "from clientes "
                    + "where id_cliente="+client.getIdUser();
            try {
                nombres=data.rows(query, "nombre_cliente");
                tipoDoc=data.rows(query, "tipo_documento");
                numeroDoc= Integer.parseInt(data.rows(query, "documento_cliente"));
                fechaNacimiento= format.parse(data.rows(query, "fecha_nacimiento"));
                direccionResidencia= data.rows(query,"direccion");
                telefono= data.rows(query, "telefono");
            } catch (ParseException ex) {
                Logger.getLogger(actualizacionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void actualizar(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance();
        Client client = Client.getInstance();
        FacesMessage message = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String auxFecha = format.format(fechaNacimiento);
        
        if(client.getIdUser()>0){
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN MODIFICADA", "Los datos ingresados han sido reemplazados en la base de datos, Gracias.");
            String actualizar= "update clientes set nombre_cliente='"+nombres
                    +"', tipo_documento='"+tipoDoc
                    +"',documento_cliente="+numeroDoc
                    +",fecha_nacimiento='"+auxFecha
                    +"', direccion_cliente='"+direccionResidencia
                    +"',telefono_cliente='"+telefono
                    +"' where id_cliente="+client.getIdUser();
            data.insert(actualizar);
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public String getUser(){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        if(cliente.getIdUser()>0){
            String[] columns = {"nombre_usuario"};
            data.loadQuery("select nombre_usuario from usuarios where id_usuario="+cliente.getIdUser());
            return data.getDBData(columns)[0];
        }
        return "Perfil";
    }
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
}
