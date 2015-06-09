/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@ViewScoped
public class cancelacionBean implements Serializable {

    private char tipoCita;
    private List<Cita> list;
    private Cita selectedCita;
    
    public cancelacionBean(){
        Client cliente = Client.getInstance();
        ConnectionBean data = ConnectionBean.getInstance();
        if(cliente.getIdUser() > 0){
            list = new ArrayList<>();
            String[] columns = {"fecha_cita", "hora_disponible", "nombre_doctor", "direccion_ips"};
            String[] aux = {};
            String query = "select nombre_doctor, fecha_cita, hora_disponible, DIRECCION_IPS " +
                            "from citas c, doctores d, horas h, sedes_ips si " +
                            "where d.id_doctor = c.id_doctor " +
                            "and h.id_hora = c.id_hora " +
                            "and si.ID_SEDE_IPS = d.ID_SEDE_IPS " +
                            "and id_cliente = "+cliente.getIdUser();
            data.loadQuery(query);
            while((aux = data.getDBData(columns))!= null){
               list.add(new Cita(aux[0], aux[1], aux[2], aux[3]));
            }
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

    public String getIdCita(){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        String query = "select c.ID_CITA " +
                        "from citas c, doctores d, horas h "+
                        "where c.ID_DOCTOR=d.ID_DOCTOR "+
                        "and c.ID_HORA = h.ID_HORA "+
                        "and d.NOMBRE_DOCTOR = '"+selectedCita.getDoctor()+"' "+
                        "and h.HORA_DISPONIBLE= '"+selectedCita.getHora()+"' "+
                        "and c.ID_CLIENTE = " + cliente.getIdUser();
        return data.rows(query, "id_cita");
    }
    
    public void realCanel(){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        String query = "delete from citas " +
                        "where id_cita = "+getIdCita();
        data.insert(query);
        FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe.","Cita cancelada.");
        FacesContext.getCurrentInstance().addMessage(null, prueba);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("");
        } catch (IOException ex) {
            Logger.getLogger(usuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Cita> getList() {
        return list;
    }

    public void setList(List<Cita> list) {
        this.list = list;
    }

    public char getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(char tipoCita) {
        this.tipoCita = tipoCita;
    }

    public Cita getSelectedCita() {
        return selectedCita;
    }

    public void setSelectedCita(Cita selectedCita) {
        this.selectedCita = selectedCita;
        realCanel();
        
    }
    
}
