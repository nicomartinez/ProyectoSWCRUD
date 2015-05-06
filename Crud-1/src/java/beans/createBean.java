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
public class createBean {

    private String username;
     
    private String password;
 
    public String getUsername() {
        return username;
    }
    /**
     * Creates a new instance of LogonBean
     */
    public createBean() {
    }
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public void create(ActionEvent actionEvent) {
        ConnectionBean data = ConnectionBean.getInstance();

        FacesMessage message = null;
        if(username != null && password != null ) {
            String query = "insert into user values ('"+username+"','"+password+"');";
            data.insert(query);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario creado!", username);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Valores invalidos!");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
