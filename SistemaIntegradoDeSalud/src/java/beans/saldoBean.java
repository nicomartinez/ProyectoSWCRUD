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
@ManagedBean
@RequestScoped
public class saldoBean {

    private List<Banco> list;
    
    /**
     * Creates a new instance of saldoBean
     */
    public saldoBean() {
        Client client = Client.getInstance();
        if(client.getIdUser()>0){
            list = new ArrayList<>();
            ConnectionBean data = ConnectionBean.getInstance();
            String[] columns = {"nombre_titular","tipo_cuenta", "numero_cuenta", "saldo_cuenta"};
            String[] auxRow = {};
            data.loadQuery("select nombre_titular, tipo_cuenta, numero_cuenta, saldo_cuenta " +
                            "from cuentas " +
                            "where id_cliente="+ client.getIdUser());           
            while((auxRow = data.getDBData(columns))!= null){
                list.add(new Banco(auxRow[0],auxRow[1],auxRow[2],auxRow[3]));
            }
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
