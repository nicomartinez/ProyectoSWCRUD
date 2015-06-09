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
        List<String> aux = new ArrayList<>();
        String[] columns = {"nombre_ips"};
        String[] auxRow = {};
        data.loadQuery("select nombre_ips, id_ips " +
                        "from ips i " +
                        "where i.id_eps = (select id_eps from clientes where id_cliente = "+ cliente.getIdUser() + " )");
        while((auxRow = data.getDBData(columns))!= null){
            aux.add(query+auxRow[0]);
        }
        return aux;
    }
    
    public String getIdIps(){
        ConnectionBean data = ConnectionBean.getInstance();
        String query = "select id_ips from ips where nombre_ips = '"+ips+"'";
        return data.rows(query, "id_ips");
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
               list.add(new Sucursal(auxRow[0], auxRow[1], auxRow[2]));
            }
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public List<Sucursal> getList() {
        return list;
    }

    public void setList(List<Sucursal> list) {
        this.list = list;
    }
    
    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }
    
}
