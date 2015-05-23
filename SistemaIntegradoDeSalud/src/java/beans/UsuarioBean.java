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
import javax.swing.JOptionPane;

/**
 *
 * @author JULIO CESAR
 */
@ManagedBean
@RequestScoped
public class usuarioBean {

    /**
     * Creates a new instance of UsuarioBean
     */
    private String userName;
    private String password;
    
    public void ingresar(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance(); // Instancia de la conexion a BD.
        FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_INFO, "Lo tomo", userName);
        String query = "select * from cliente where nombre_cliente='"+userName+"';"; //Consulta.
        FacesMessage message = null;    //Mensaje en pantalla.
        if(userName.length() > 0 && password.length() > 0 ) {//Campos no vacios, aunque en el xhtml también se valida.
            //Se valida que los valores digitados en los campos de la interfaz
            //correspondan a valores existentes en la base de datos.
            if(userName.equals(data.rows(query, "nombre_cliente"))&&password.equals(data.rows(query, "contrasenia"))){
                //Todas las validaciones permiten ingresar.
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", userName);
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(usuarioBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                //La validación no se encontró, los datos no corresponden a valores en la BD.
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Datos invalidos!");
            }
        } else {
            //Campos vacios.
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Los Campos no deben estar vacios!");
        }
        //Desplegar mensaje.
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
