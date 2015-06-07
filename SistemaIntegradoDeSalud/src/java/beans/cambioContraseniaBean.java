/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author JULIO CESAR
 */
@ManagedBean
@RequestScoped
public class cambioContraseniaBean {
    
    private String contraseña;
    private String nuevaContraseña;
    private String confirmacion;
    
    public cambioContraseniaBean() {
    }
    
    public void cambiar(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        String query= "select contrasenia, nombre_usuario from usuarios u,clientes c where c.id_usuario=u.id_usuario and c.id_cliente="+cliente.getIdUser()+";";
        if (contraseña.equals(data.rows(query, "contrasenia"))){
            if(nuevaContraseña.equals(confirmacion)){
                String cambio="update usuarios set  contrasenia='"+nuevaContraseña+"' where nombre_usuario='"+data.rows(query, "nombre_usuario")+"' ";
                data.insert(cambio);
                FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe","Cambio realizado");
                FacesContext.getCurrentInstance().addMessage(null, prueba);
            }else{
                FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe","Confirme la contraseña de nuevo");
                FacesContext.getCurrentInstance().addMessage(null, prueba);
            }
        
        }else{
            FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe","La contraseña actual es incorrecta");
                FacesContext.getCurrentInstance().addMessage(null, prueba);
        }
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNuevaContraseña() {
        return nuevaContraseña;
    }

    public void setNuevaContraseña(String nuevaContraseña) {
        this.nuevaContraseña = nuevaContraseña;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }
    
    
    
    
    
    
}
