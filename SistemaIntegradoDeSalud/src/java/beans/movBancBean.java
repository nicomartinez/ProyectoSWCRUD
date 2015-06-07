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
public class movBancBean {

    private List<Movimiento> list;
    
    /**
     * Creates a new instance of movBancBean
     */
    public movBancBean() {
        Client client = Client.getInstance();
        if(client.getIdUser()>0){
        
              list = new ArrayList<>();
            ConnectionBean data = ConnectionBean.getInstance();
            String[] columns = {"fecha_operacion","tipo_operacion", "valor_operacion"};
            String[] auxRow = {};
            data.loadQuery("select fecha_operacion, case tipo_operacion when tipo_operacion='R' then 'Retiro' else 'Consignacion' end tipo_operacion, valor_operacion " +
                            "from valores v, operaciones o, cuentas c " +
                            "where c.numero_cuenta = v.numero_cuenta " +
                            "and o.id_operacion = v.id_operacion " +
                            "and c.id_cliente = "+ client.getIdUser());           
            while((auxRow = data.getDBData(columns))!= null){
                list.add(new Movimiento(auxRow[0],auxRow[1],auxRow[2]));
            }
            
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public List<Movimiento> getList() {
        return list;
    }

    public void setList(List<Movimiento> list) {
        this.list = list;
    }
}
