/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author JULIO CESAR
 */
@ManagedBean
@RequestScoped
public class pagoCuotaBean {
    
    private int idCliente;
    private String referencia;
    private String descripcion;
    private int valor;
    private String numeroCuenta;
    private String contrasenia; 
    
    public void llenar(ActionEvent actionEvent){
     ConnectionBean data = ConnectionBean.getInstance();
     String query ="select  case  when month(dateiff(fecha_cuota,CURRENT_DATE)) = 1 then 1 else 2 end fecha from cuotas where id_cuota = (select max(id_cuota) from cuotas where id_cuota = "+idCliente+");";
     
    if(data.rows(query, "fecha").equals("1")){
        String query2="select distinc( max(id_cuota)) id, CURRENT_DATE fecha, valor_cuota valor from cuotas c, clientes cl where cl.id_cliente = c.id_cliente and cl.id_cliente =" +idCliente+";";
        referencia= data.rows(query2, "id");
        descripcion= data.rows(query2, "fecha");
        valor= Integer.parseInt(data.rows(query2, "valor"));
        // try {
            // FacesContext.getCurrentInstance().getExternalContext().redirect("pagoCuota.xhtml");
         //} catch (IOException ex) {
           //Logger.getLogger(pagoCuotaBean.class.getName()).log(Level.SEVERE, null, ex);
         //}
    }else{
     FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe","Aun no es hora de realizar el pago de las cuota");
    
    }
      
    

    }
 
    public void pagoCuota(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance();
        String query ="select case when saldo_cuenta > valor then 1 else 2 end valor from cuentas where id_cliente ="+idCliente+";";
        String query2 ="select 1 cuenta from cuentas where numero_cuenta="+ numeroCuenta+" and clave_cuenta="+contrasenia+";";
        if(data.rows(query2, "cuenta").equals("1")){
            if(data.rows(query, "valor").equals("1")){
            String pago = "insert into cuotas values (" + Integer.parseInt(data.rows(query, "id")) + "," + idCliente + "," + Integer.parseInt(data.rows(query, "valor")) + ", '" + data.rows(query, "fecha") + "');";
            String pago2="insert into valores values(1,"+numeroCuenta+","+valor+","+descripcion+");";
            String actualiza= "update cuentas set saldo_cuenta= (saldo_cuenta-"+valor+") where numero_cuenta="+Integer.parseInt(numeroCuenta)+";";
            FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe","Pago registrado");
            }else{
                FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe","No hay saldo suficiente para la operacion");
            }
        }else{
            FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe","El numero de cuenta o la contraseña no corresponden ");
        }
        
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    
}
