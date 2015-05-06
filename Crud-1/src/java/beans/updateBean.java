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
public class updateBean {

    private String nick;
    private String username;
    private String password;
     
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }
    public String getUsername() {
        return username;
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
    /**
     * Creates a new instance of LogonBean
     */
    public updateBean() {
    }
    
    public void update(ActionEvent actionEvent) {
        ConnectionBean data = ConnectionBean.getInstance();

        FacesMessage message = null;
        if(nick != null && username != null && password != null) {
            data.loadQuery("select * from user where nick='"+nick+"';");
            String[] columns = {"nick","pass"};
            if(data.getDBData(columns)[0].equals(nick)){
                String query = "update user set nick='"+username+"',"
                        + "pass='"+password+"' where nick='"+nick+"';";
                data.insert(query);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario ACTUALIZADO!", username);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Usuario no existe!");
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Valores no digitados!");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
