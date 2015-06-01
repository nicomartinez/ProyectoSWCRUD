/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class serviciosBean {

    private String ips;
    private String ubicacion;
    private int idCliente;
    
    /**
     * Creates a new instance of sereviciosBean
     */
    public serviciosBean() {
    }
    
    public void consultar(){
         ConnectionBean data = ConnectionBean.getInstance();
         
         String query2="select nombre_ips, nombre_lugar "
                 + "from clientes c, eps e, ips i, sedes_ips si, lugares l "
                 + "where c.id_eps=e.id_eps "
                 + "and e.id_eps=i.id_eps "
                 + "and i.id_eps=si.id_ips "
                 + "and si.id_lugar=l.id_lugar"
                 + "and c.id_cliente="+idCliente;
         
         
         String query= "select nombre_procedimiento "
                 + "from clientes c, eps e, ips i, sedes_ips si, servicios s, procedimientos p "
                 + "where c.id_eps=e.id_eps "
                 + "and e.id_eps=i.id_eps "
                 + "and i.id_ips=si.id_ips "
                 + "and si.id_sede_ips=s.id_sede_ips "
                 + "and s.id_procedimiento=p.id_procedimiento "
                 + "and ";
         
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
