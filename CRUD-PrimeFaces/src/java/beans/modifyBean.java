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
public class modifyBean {

    private String nick;
    private String username;
    private String password;
    
    /**
     * Constructor
     */
    public modifyBean(){
    }
    /**
     * Creates a new instance of modifyBean
     */
    public void modify(ActionEvent actionEvent) {
        ConnectionBean data = ConnectionBean.getInstance(); //Instancia a la BD.

        FacesMessage message = null;//Mensaje.
        if(nick != null && username != null && password != null) { //Campos no vacios.
            data.loadQuery("select * from user where nick='"+nick+"';");//Consulta.
            String[] columns = {"nick","pass"};//Nombre de las columnas en la BD.
            if(data.getDBData(columns)[0].equals(nick)){ //Verificación de valor ingresado con consulta.
                String query = "update user set nick='"+username+"',"
                        + "pass='"+password+"' where nick='"+nick+"';"; // Consulta para modificar.
                data.insert(query);//Ejecución de la consulta.
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario ACTUALIZADO!", username);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Usuario no existe!");
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Valores no digitados!");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //--------SETTERS Y GETTERS------
    public void setNick(String inNick){
        this.nick = inNick;
    }
    
    public String getNick(){
        return this.nick;
    }
    public void setUsername(String inUsername){
        this.username = inUsername;
    }
    
    public String getUsername(){
        return this.username;
    }
    public void setPassword(String inPassword){
        this.password = inPassword;
    }
    
    public String getPassword(){
        return this.password;
    }
}
