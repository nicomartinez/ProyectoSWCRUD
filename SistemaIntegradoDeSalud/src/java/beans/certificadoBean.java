/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class certificadoBean {

    private String destino;
    private int idCliente;
    
    public void certificado(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance();
         
        String query="select estado_cliente estado from clientes where id_cliente="+idCliente;
        
        if(data.rows(query, "estado").equals("A")){
        String query2="select nombre_cliente nombre, tipo_documento tipo, documento_cliente documento, fecha_nacimiento fecha, direccion_cliente direccion, telefono_cliente telefono "
                + "from clientes "
                + "where id_cliente="+idCliente;
        }else{
            FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe","No puedes obtener tu certificado, porque Tu estado es inactivo");
        }
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
}
