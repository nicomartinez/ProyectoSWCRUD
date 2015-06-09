/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.io.IOException;
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
public class indexBean {

    private boolean isUser;
    
    /**
     * Creates a new instance of indexBean
     */
    public indexBean() {
        isUser = false;
        Client cliente = Client.getInstance();
        if(cliente.getIdUser()>0){
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public String getUser(){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        if(cliente.getIdUser()>0){
            String[] columns = {"nombre_usuario"};
            data.loadQuery("select nombre_usuario from usuarios where id_usuario="+cliente.getIdUser());
            isUser = true;
            return data.getDBData(columns)[0];
        }
        return "Perfil";
    }
    public void exit(ActionEvent actionEvent){
        Client cliente = Client.getInstance();
        cliente.setIdUser(0);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../");
        } catch (IOException ex) {
            Logger.getLogger(indexBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isIsUser() {
        return isUser;
    }

    public void setIsUser(boolean isUser) {
        this.isUser = isUser;
    }
}
