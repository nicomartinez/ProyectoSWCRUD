/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
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
public class serviciosBean {

    private String ips;
    private String ubicacion;
    
    /**
     * Creates a new instance of sereviciosBean
     */
    public serviciosBean() {
        Client client = Client.getInstance();
        if(client.getIdUser() >0){
        
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void consultar(ActionEvent actionEvent){
         ConnectionBean data = ConnectionBean.getInstance();
         Client cliente = Client.getInstance();
         
         String query= "select nombre_procedimiento "
                 + "from clientes c, eps e, ips i, sedes_ips si, servicios s, procedimientos p "
                 + "where c.id_eps=e.id_eps "
                 + "and e.id_eps=i.id_eps "
                 + "and i.id_ips=si.id_ips "
                 + "and si.id_sede_ips=s.id_sede_ips "
                 + "and s.id_procedimiento=p.id_procedimiento "
                 + "and ";
         
    }
    public List<String> loadIPS(String query){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        List<String> aux = new ArrayList<String>();
        if(cliente.getIdUser()>0){
            String[] columns = {"nombre_ips"};
            String[] auxRow = {};
            String query2="select nombre_ips " +
                            "from clientes c, eps e, ips i "+
                            "where c.id_eps=e.id_eps " +
                            "and e.id_eps=i.id_eps " +
                            "and c.id_cliente="+cliente.getIdUser();
            data.loadQuery(query2);
            while((auxRow = data.getDBData(columns))!= null){
                aux.add(query+auxRow[0]);
            }
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return aux;
    }
    public List<String> loadLocation(String query){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        List<String> aux = new ArrayList<String>();
        String[] columns = {"nombre_ips"};
        String[] auxRow = {};
        String query2="select nombre_lugar " +
                        "from clientes c, eps e, ips i, sedes_ips si, lugares l " +
                        "where c.id_eps=e.id_eps " +
                        "and e.id_eps=i.id_eps " +
                        "and i.id_eps=si.id_ips " +
                        "and si.id_lugar=l.id_lugar " +
                        "and c.id_cliente="+cliente.getIdUser()+
                        "group by NOMBRE_LUGAR";
        data.loadQuery(query2);
        while((auxRow = data.getDBData(columns))!= null){
            aux.add(query+auxRow[0]);
        }
        return aux;
    }
    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
}
