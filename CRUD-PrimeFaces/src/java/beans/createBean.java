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

    /**
     * Variables que almacenaran los datos ingresados por la interfaz de usuario.
     */
    private String usuario;
    private String password;
    
    /**
     * Creates a new instance of createBean
     */
    public createBean() {
    }
    
    public void create(ActionEvent actionEvent){
        //Se obtiene la instancia de la conexión con la base de datos.
        ConnectionBean data = ConnectionBean.getInstance();

        FacesMessage message = null; //Esta variable despliega un mensaje en pantalla.
        if(usuario != null && password != null ) {//Se valida que los campos usuario y contraseña no esten vacios.
            //inserción en la base de datos con los valores introducidos en la interfaz.
            String query = "insert into user values ('"+usuario+"','"+password+"');";
            data.insert(query); //Ejecución de la consulta en la base de datos.
            //Se establece que el mensaje será de exito.
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario creado!", usuario);
        } else {
            //Si los datos introducidos están vacios.
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Campos en blanco!");
        }
        FacesContext.getCurrentInstance().addMessage(null, message); //Despliega el mensaje.
    }
    
    public String getUsuario(){
        return this.usuario;
    }
    public String getPassword(){
        return this.password;
    }
    public void setUsuario(String inUsuario){
        this.usuario = inUsuario;
    }
    public void setPassword(String inPassword){
        this.password = inPassword;
    }
}
