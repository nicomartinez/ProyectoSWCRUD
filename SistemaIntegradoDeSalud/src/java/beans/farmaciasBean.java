/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class farmaciasBean {

    private List<IPS> list;
    private int idCliente;
    
    public farmaciasBean(){
        
        
    }
    
    public void consultar(){
         ConnectionBean data = ConnectionBean.getInstance();
         String query="select nombre_farmacia, direccion_farmacia"
                 + "from cliente c, eps e, sedes_eps se,farmacias f "
                 + "where c.id_eps=e.id_eps "
                 + "and e.id_eps=se.id_eps "
                 + "and se.id_sede_eps=f.id_sede_eps "
                 + "and c.id_cliente="+idCliente;
         
         String query2="select nombre_laboratorio, direccion_laboratorio"
                 + "from cliente c, eps e, sedes_eps se,laboratorios l "
                 + "where c.id_eps=e.id_eps "
                 + "and e.id_eps=se.id_eps "
                 + "and se.id_sede_eps=l.id_sede_eps "
                 + "and c.id_cliente="+idCliente;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
    
}
