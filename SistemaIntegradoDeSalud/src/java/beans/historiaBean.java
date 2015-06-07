/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
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
public class historiaBean {

    private String nombres;
    private String  numeroDoc;
    private String numeroTel;
    
    private char tipoDoc;
    private int numDocPac;
    private char ips;
    
    public historiaBean(){
        ConnectionBean data = ConnectionBean.getInstance();
    
        Client client = Client.getInstance();
        FacesMessage message;
         if(client.getIdUser()>0){
            String query="select nombre_cliente, documento_cliente, "
                    + "telefono_cliente telefono "
                    + "from clientes "
                    + "where id_cliente="+client.getIdUser();
            
                nombres=data.rows(query, "nombre_cliente");
                numeroDoc =data.rows(query, "documento_cliente");
                numeroTel= data.rows(query, "telefono");
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
       
    }
    
    
      public List<String> loadIPS(){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        List<String> aux = new ArrayList<String>();
        if(cliente.getIdUser()>0){
             String[] columns = {"nombre_ips"};
            String[] auxRow = {};
            
                 String query2="select nombre_ips " +
                            "from clientes c, eps e, ips i "+
                            "where c.id_eps=e.id_eps " +
                            "and e.id_eps=i.id_eps " +
                            "and c.documento_cliente="+ numDocPac;
            data.loadQuery(query2);
            while((auxRow = data.getDBData(columns))!= null){
                aux.add(auxRow[0]);
            }
                
            
           
           
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return aux;
    }
      
      public List<String> loadTipoDoc(String query){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        List<String> aux = new ArrayList<String>();
        if(cliente.getIdUser()>0){
            String[] columns = {"tipo_documento"};
            String[] auxRow = {};
            String query2="select distinct case tipo_documento when tipo_documento='CC' then 'Cedula' else 'Tarjeta Identidad' end tipo_documento " +
                            "from clientes";
            data.loadQuery(query2);
            while((auxRow = data.getDBData(columns))!= null){
                aux.add(query+auxRow[0]);
            }
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return aux;
    }
    
    public void historia(ActionEvent actionEvent){
         ConnectionBean data = ConnectionBean.getInstance();
         Client client = Client.getInstance();
         if(client.getIdUser() > 0){
            String query="select nombre_cliente, nombre_procedimiento"
                    + "from clientes c, eps e, ips i, historias_ips hi, procedimientos p, descripciones d "
                    + "where "
                    + "c.id_eps=e.id_eps "
                    + "and e.id_eps=i.id_eps "
                    + "and i.id_ips=hi.id_ips "
                    + "and hi.id_historia=d.id_historia "
                    + "and d.id_procedimiento=p.id_procedimiento "
                    + "and c.tipo_documento="+tipoDoc+" "
                    + "and c.documento_cliente="+numDocPac+" "
                    + "and i.nombre_ips="+ips+";";
           data.loadQuery(query);
         }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
         }
    }
    

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public char getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(char tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public int getNumDocPac() {
        return numDocPac;
    }

    public void setNumDocPac(int numDocPac) {
        this.numDocPac = numDocPac;
    }

    public char getIps() {
        return ips;
    }

    public void setIps(char ips) {
        this.ips = ips;
    }
    
}
