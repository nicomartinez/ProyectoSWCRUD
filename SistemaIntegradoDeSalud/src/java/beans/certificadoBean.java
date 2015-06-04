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
import javax.faces.context.FacesContext;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class certificadoBean {

    private String destino;
    
    public void certificado(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        if(cliente.getIdUser()>0){
            String query="select estado_cliente from clientes where id_cliente="+cliente.getIdUser();
            if((data.rows(query, "estado_cliente")).equals("A")){
                String query2="select nombre_cliente nombre, tipo_documento tipo, documento_cliente documento, fecha_nacimiento fecha, direccion_cliente direccion, telefono_cliente telefono "
                    + "from clientes "
                    + "where id_cliente="+cliente.getIdUser();
                data.loadQuery(query2);
                String[] columns = {};
                data.getDBData(columns);
            }else{
                FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe","No puedes obtener tu certificado, porque Tu estado es inactivo");
                FacesContext.getCurrentInstance().addMessage(null, prueba);
            }
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
}
