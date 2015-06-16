/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@ViewScoped
public class solicitarBean implements Serializable {

    private String tipoCita;
    private Date fechaCita;
    private char jornada;
    private List<Cita> list;
    private Cita selectedCita;
    
    /**
     * Creates a new instance of solicitarBean
     */
    public solicitarBean() {
        Client cliente = Client.getInstance();
        if(cliente.getIdUser() > 0){
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIN USUARIO", "NO has iniciado sesión, por favor vuelve a la pantalla inicial.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public List<String> loadTipoCita(String query){
        ConnectionBean data = ConnectionBean.getInstance();
        List<String> aux = new ArrayList<>();
        String[] columns = {"nombre_tipo"};
        String[] auxRow = {};
        data.loadQuery("select nombre_tipo from tipos_cita");
        while((auxRow = data.getDBData(columns))!= null){
            aux.add(query+auxRow[0]);
        }
        return aux;
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
    
    public boolean getIsUser(){
        Client cliente = Client.getInstance();
        if(cliente.getIdUser()>0){
            return true;
        }
        return false;
    }
    
    public void solicitar(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        list = new ArrayList<>();
        String[] columns = {"hora_disponible","nombre_doctor", "direccion_ips"};
        String[] auxRow = {};
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String query ="select distinct hora_disponible, nombre_doctor, si.direccion_ips direccion_ips\n" +
                        "from horas h, jornadas j, dias d, turnos t, doctores dc, tipos_cita tc,ips i, sedes_ips si \n" +
                        "where i.id_ips=si.id_ips \n" +
                        "and i.ID_IPS=si.ID_IPS\n" +
                        "and si.ID_SEDE_IPS= dc.ID_SEDE_IPS\n" +
                        "and dc.id_doctor=t.ID_DOCTOR\n" +
                        "and t.id_dia=d.id_dia \n" +
                        "and d.id_dia=j.id_dia \n" +
                        "and j.id_hora= h.id_hora\n" +
                        "and dc.ID_TIPO = tc.ID_TIPO \n" +
                        "and j.tipo_jornada='"+jornada+"' \n" +
                        "and tc.nombre_tipo='"+tipoCita+"' \n" +
                        "and nombre_dia=dayname('"+format.format(fechaCita)+"') \n" +
                        "and i.id_eps = (select e.id_eps \n" +
                        "				from eps e, clientes c \n" +
                        "				where e.id_eps = c.id_eps \n" +
                        "				and c.id_cliente="+cliente.getIdUser()+")\n" +
                        "and h.id_hora not in (select c.id_hora \n" +
                        "						from citas c \n" +
                        "						where c.FECHA_CITA = '"+format.format(fechaCita)+"'\n" +
                        "						and c.ID_DOCTOR = dc.id_doctor) "
                + "order by hora_disponible";
        data.loadQuery(query);
        while((auxRow = data.getDBData(columns))!= null){
            list.add(new Cita(auxRow[0], auxRow[1], auxRow[2]));
        }
        if(list.isEmpty()){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "No hay citas disponibles.", 
                    "No existen citas disponibles para los datos ingresados, por favor intente con otro día u otro tipo de cita.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }

    public int getIdHora(){
        ConnectionBean data = ConnectionBean.getInstance();
        String query = "select id_hora from horas where HORA_DISPONIBLE = '"+selectedCita.getHora()+"'";
        return Integer.parseInt(data.rows(query, "id_hora"));
    }
    
    public int getIdDoctor(){
        ConnectionBean data = ConnectionBean.getInstance();
        String query = "select id_doctor " +
                "from doctores " +
                "where NOMBRE_DOCTOR='"+selectedCita.getDoctor()+"'";
        return Integer.parseInt(data.rows(query, "id_doctor"));
    }

    public int getIdDia(){
        ConnectionBean data = ConnectionBean.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("u");
        return Integer.parseInt(format.format(fechaCita));
    }
    
    public void realSolicitar(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance();
        Client cliente = Client.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String query2="select max(id_cita)+1 id from citas";
        
        String query = "insert into citas values ("+data.rows(query2, "id")+","+cliente.getIdUser()+
                ", "+getIdDoctor()+
                ", "+getIdHora()+
                ", "+getIdDia()+
                ", '"+format.format(fechaCita)+"');";
        data.insert(query);
        solicitar(actionEvent);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe.", "Se le ha asignado la cita.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public char getJornada() {
        return jornada;
    }

    public void setJornada(char jornada) {
        this.jornada = jornada;
    }

    public List<Cita> getList() {
        return list;
    }

    public void setList(List<Cita> list) {
        this.list = list;
    }

    public Cita getSelectedCita() {
        return selectedCita;
    }

    public void setSelectedCita(Cita selectedCita) {
        this.selectedCita = selectedCita;
    }
    
}
