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
public class dispositivosBean {

    private String ips;
    private List<Dispositivo> list;
    
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
    
    public void dispositivos(ActionEvent actionEvent){
        list = new ArrayList<>();
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        if(cliente.getIdUser()>0){
            String[] columns = {"nombre_dispositivo","costo_dispositivo","descripcion_dispositivo"};
            String[] auxRow = {};
            String query = "select distinct nombre_dispositivo, costo_dispositivo, descripcion_dispositivo " +
                            "from ips i, sedes_ips si, servicios s, herramientas h, dispositivos d, procedimientos p " +
                            "where i.id_ips=si.id_ips " +
                            "and si.id_sede_ips=s.id_sede_ips " +
                            "and  s.id_procedimiento=p.id_procedimiento " +
                            "and p.id_procedimiento=h.id_procedimiento " +
                            "and h.id_dispositivo=d.id_dispositivo " +
                            "and i.nombre_ips='" + ips + "' " +
                            "order by 1 desc" ;
            data.loadQuery(query);
            while((auxRow = data.getDBData(columns))!= null){
               list.add(new Dispositivo(auxRow[0], auxRow[1], auxRow[2]));
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

    public List<Dispositivo> getList() {
        return list;
    }

    public void setList(List<Dispositivo> list) {
        this.list = list;
    }
    
    
}
