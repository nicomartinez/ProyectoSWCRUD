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
public class loginBean {

    private String username;
    private String password;
    
    /**
     * Creates a new instance of loginBean
     */
    public loginBean() {
    }
    /**
     * Método encargado de obtener los datos de la interfaz y pasarlos a la
     * persistencia.
     * @param actionEvent 
     */
    public void read(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance(); // Instancia de la conexion a BD.
        String query = "select * from user where nick='"+username+"';"; //Consulta.
        FacesMessage message = null;    //Mensaje en pantalla.
        if(username != null && password != null ) {//Campos no vacios, aunque en el xhtml también se valida.
            //Se valida que los valores digitados en los campos de la interfaz
            //correspondan a valores existentes en la base de datos.
            if(username.equals(data.rows(query, "nick"))&&password.equals(data.rows(query, "pass"))){
                //Todas las validaciones permiten ingresar.
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
            }else{
                //La validación no se encontró, los datos no corresponden a valores en la BD.
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Datos invalidos!");
            }
        } else {
            //Campos vacios.
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Campos vacios!");
        }
        //Desplegar mensaje.
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void setUsername(String inUsername){
        this.username = inUsername;
    }
    public void setPassword(String inPassword){
        this.password = inPassword;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
}
