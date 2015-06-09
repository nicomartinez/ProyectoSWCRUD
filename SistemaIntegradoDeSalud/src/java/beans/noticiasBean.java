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
public class noticiasBean {

    private String ips;
    private List<Informacion> list;
    
    public noticiasBean(){
        Client client = Client.getInstance();
        if(client.getIdUser()>0){
        
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
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
    
    
    public void noticias(ActionEvent actionEvent){
        list = new ArrayList<>();
        Client client = Client.getInstance();
        ConnectionBean data = ConnectionBean.getInstance();
        if(client.getIdUser()>0){
            String[] columns = {"tipo_informacion","descripcion_informacion"};
            String[] auxRow = {};
            String query = "select  case tipo_informacion when tipo_informacion='N' then 'Noticia' else 'Evento' end tipo_informacion , descripcion_informacion " +
                            "from ips i, informaciones info " +
                            "where i.id_ips=info.id_ips " +
                            "and i.nombre_ips='" +ips+"' "+
                            "order by tipo_informacion desc; " ;
            data.loadQuery(query);
            while((auxRow = data.getDBData(columns))!= null){
               list.add(new Informacion(auxRow[0], auxRow[1]));
            }
        

        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public List<Informacion> getList() {
        return list;
    }

    public void setList(List<Informacion> list) {
        this.list = list;
    }
    
}
