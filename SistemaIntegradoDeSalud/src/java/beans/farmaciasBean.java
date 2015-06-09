/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
@ManagedBean(name="farmaciasBean")
@RequestScoped
public class farmaciasBean {

    private List<FarmaciasYLabs> listFarm;
    private List<FarmaciasYLabs> listLabs;
    
    public farmaciasBean(){
        listFarm = new ArrayList<>();
        listLabs = new ArrayList<>();
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        if(cliente.getIdUser()>0){
            String[] columns = {"nombre_farmacia","direccion_farmacia"};
            String[] auxRow = {};
            String query="select nombre_farmacia, direccion_farmacia "
                    + "from clientes c, eps e, sedes_eps se,farmacias f "
                    + "where c.id_eps=e.id_eps "
                    + "and e.id_eps=se.id_eps "
                    + "and se.id_sede_eps=f.id_sede_eps "
                    + "and c.id_cliente="+cliente.getIdUser();
            data.loadQuery(query);
            while((auxRow = data.getDBData(columns))!= null){
               listFarm.add(new FarmaciasYLabs(auxRow[0], auxRow[1]));
            }
            String[] columns2 = {"nombre_laboratorio","direccion_laboratorio"};
            String[] auxRow2 = {};
            String query2="select nombre_laboratorio, direccion_laboratorio "
                    + "from clientes c, eps e, sedes_eps se,laboratorios l "
                    + "where c.id_eps=e.id_eps "
                    + "and e.id_eps=se.id_eps "
                    + "and se.id_sede_eps=l.id_sede_eps "
                    + "and c.id_cliente="+cliente.getIdUser();
           data.loadQuery(query2);
           while((auxRow2 = data.getDBData(columns2))!= null){
               listLabs.add(new FarmaciasYLabs(auxRow2[0], auxRow2[1]));
           }
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public List getList() {
        return listFarm;
    }

    public void setList(List list) {
        this.listFarm = list;
    }

    public List<FarmaciasYLabs> getListFarm() {
        return listFarm;
    }

    public void setListFarm(List<FarmaciasYLabs> listFarm) {
        this.listFarm = listFarm;
    }

    public List<FarmaciasYLabs> getListLabs() {
        return listLabs;
    }

    public void setListLabs(List<FarmaciasYLabs> listLabs) {
        this.listLabs = listLabs;
    }
}
