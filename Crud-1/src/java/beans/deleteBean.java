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
public class deleteBean {

    private String username;
     
    public String getUsername() {
        return username;
    }
    /**
     * Creates a new instance of LogonBean
     */
    public deleteBean() {
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void delete(ActionEvent actionEvent) {
        ConnectionBean data = ConnectionBean.getInstance();

        FacesMessage message = null;
        if(username != null) {
            String query = "delete from user where nick='"+username+"';";
            data.insert(query);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario ELIMINADO!", username);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Valores invalidos!");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
