/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class sucursalBean {

    private String ips;
    private List<Sucursal> list;
    /**
     * Creates a new instance of sucursalBean
     */
    public sucursalBean() {
        Client client = Client.getInstance();
        if(client.getIdUser() >0){
        
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

    public void sucursal(ActionEvent actionEvent){
        list = new ArrayList<>();
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        if(cliente.getIdUser()>0){
            String[] columns = {"nombre_sede_ips","nombre_lugar","direccion_ips"};
            String[] auxRow = {};
            String query = "select nombre_sede_ips, nombre_lugar, direccion_ips " +
                            "from lugares l, sedes_ips s, ips i " +
                            "where l.id_lugar = s.id_lugar " +
                            "and s.id_ips = i.id_ips " +
                            "and nombre_ips= '" + ips + "'" ;
            data.loadQuery(query);
            while((auxRow = data.getDBData(columns))!= null){
                System.out.println("entro!!");
               list.add(new Sucursal(auxRow[0], auxRow[1], auxRow[2]));
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

    public List<Sucursal> getList() {
        return list;
    }

    public void setList(List<Sucursal> list) {
        this.list = list;
    }
    
    
}
