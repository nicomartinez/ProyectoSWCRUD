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
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class sucursalBean {

    private String ips;
    
    /**
     * Creates a new instance of sucursalBean
     */
    public sucursalBean() {
        Client client = Client.getInstance();
        if(client.getIdUser() >0){
        
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void sucursal(ActionEvent actionEvent){
        Client client = Client.getInstance();
        if(client.getIdUser() >0){
        
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }
    
}
