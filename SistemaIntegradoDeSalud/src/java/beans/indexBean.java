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

    /**
     * Creates a new instance of indexBean
     */
    public indexBean() {
    }
    public String getUser(){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        if(cliente.getIdUser()>0){
            String[] columns = {"nombre_usuario"};
            data.loadQuery("select nombre_usuario from usuarios where id_usuario="+cliente.getIdUser());
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
}
