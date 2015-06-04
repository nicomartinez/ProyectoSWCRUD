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
public class cancelacionBean {

    private char tipoCita;
    
    public cancelacionBean(){
        Client cliente = Client.getInstance();
        if(cliente.getIdUser() > 0){
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void cancelar(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        if(cliente.getIdUser() > 0){
            String query = "select nombre_doctor, fecha_cita, hora_disponible, nombre_lugar\n" +
                            "from citas c, doctores d, horas h\n" +
                            "where d.id_doctor = c.id_doctor\n" +
                            "and h.id_hora = c.id_hora\n" +
                            "and id_cliente = "+cliente.getIdUser();
            data.loadQuery(query);
            
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public char getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(char tipoCita) {
        this.tipoCita = tipoCita;
    }
    
}
