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
//import jdk.nashorn.internal.objects.annotations.Constructor;

/**
 *
 * @author JULIO CESAR
 */
@ManagedBean
@RequestScoped
public class pagoCuotaBean {
    
    private int referencia;
    private String descripcion;
    private int valor;
    private String numeroCuenta;
    private String contrasenia;

    public pagoCuotaBean(){
         ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        if(cliente.getIdUser() > 0){
            String query ="select  case  when truncate( datediff(CURRENT_DATE,fecha_cuota)/30,0) >= 1 then 1 else 2 end fecha from cuotas where id_cuota = (select max(id_cuota) from cuotas where id_cliente = "+cliente.getIdUser()+");";

                if(data.rows(query, "fecha").equals("1")){
                    String query2="select distinct( max(id_cuota)) id, CURRENT_DATE fecha, valor_cuota valor from cuotas c, clientes cl where cl.id_cliente = c.id_cliente;";
                    referencia= Integer.parseInt(data.rows(query2, "id"))+1;
                    descripcion= data.rows(query2, "fecha");
                    System.out.println(descripcion);
                    valor= Integer.parseInt(data.rows(query2, "valor"));
                }else{
                 FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe","Aun no es hora de realizar el pago de las cuota");
                 FacesContext.getCurrentInstance().addMessage(null, prueba);
                }
            
            
            
            
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
 
    public void pagoCuota(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        String query ="select case when saldo_cuenta > "+valor+" then 1 else 2 end valor from cuentas where id_cliente ="+cliente.getIdUser()+";";
        String query2 ="select 1 cuenta from cuentas where numero_cuenta="+ numeroCuenta+" and clave_cuenta="+contrasenia+";";
        String query3="select id_eps from clientes where id_cliente="+ cliente.getIdUser()+";";
        if(data.rows(query2, "cuenta").equals("1")){
            if(data.rows(query, "valor").equals("1")){
                
                    String pago = "insert into cuotas values (" + referencia + "," + cliente.getIdUser() + "," + valor + ", '" + descripcion + "');";
                    System.out.println(pago);
                    data.insert(pago);
                    String pago2="insert into valores values("+numeroCuenta+","+data.rows(query3, "id_eps")+","+valor+",'"+descripcion+"');";
                    data.insert(pago2);
                    String actualizar= "update cuentas set saldo_cuenta= (saldo_cuenta-"+valor+") where numero_cuenta="+Integer.parseInt(numeroCuenta)+";";
                    data.insert(actualizar);
                    FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe","Pago registrado");
                    
                    FacesContext.getCurrentInstance().addMessage(null, prueba);
                
            }else{
                FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe","No hay saldo suficiente para la operacion");
                FacesContext.getCurrentInstance().addMessage(null, prueba);
            }
        }else{
            FacesMessage prueba = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe","El numero de cuenta o la contraseña no corresponden ");
            FacesContext.getCurrentInstance().addMessage(null, prueba);
        }
        
    }
    
    public String getUser(){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        if(cliente.getIdUser()>0){
            String[] columns = {"nombre_usuario"};
            data.loadQuery("select nombre_usuario from usuarios where id_usuario="+cliente.getIdUser());
            return data.getDBData(columns)[0];
        }
        return "Perfil";
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
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
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
}
