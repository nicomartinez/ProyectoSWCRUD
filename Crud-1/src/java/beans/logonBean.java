/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author JONATHAN
 */
@ManagedBean 
@SessionScoped
public class logonBean {
    private String username;
     
    private String password;
 
    public String getUsername() {
        return username;
    }
    /**
     * Creates a new instance of LogonBean
     */
    public logonBean() {
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

    public void login(ActionEvent actionEvent) {
        ConnectionBean data = ConnectionBean.getInstance();
        String query = "select * from user where nick='"+username+"';";
        FacesMessage message = null;
        if(username != null && password != null ) {
            if(username.equals(data.rows(query, "nick"))&&password.equals(data.rows(query, "pass"))){
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Datos invalidos!");
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", 
                            "Campos vacios!");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
