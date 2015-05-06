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
    /**
     * Creates a new instance of deleteBean
     */
    public deleteBean() {
    }
    
    public void delete(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance();//Instancia BD

        FacesMessage message = null;//Mensaje.
        if(username != null) {//No vacio.
            String query = "delete from user where nick='"+username+"';";//Consulta.
            data.insert(query);//Ejecución de la consulta.
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario ELIMINADO!", username);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Valores invalidos!");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //-------SET Y GET----
    public void setUsername(String inUsername){
        this.username = inUsername;
    }
    public String getUsername(){
        return this.username;
    }
}
