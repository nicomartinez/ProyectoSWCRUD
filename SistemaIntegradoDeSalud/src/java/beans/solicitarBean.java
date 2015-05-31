/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.sql.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class solicitarBean {

    private char tipoCita;
    private String fechaCita;
    private char jornada;
    private int idCliente;
    
    
    /**
     * Creates a new instance of solicitarBean
     */
    public solicitarBean() {
    }

    public void solicitar(ActionEvent actionEvent){
        ConnectionBean data = ConnectionBean.getInstance();
        String query ="select hora_disponible, nombre_doctor, direccion_ips, h.id_hora, dc.id_doctor, d.id_dia,"
                + "from horas h, jornadas j, dias d, turnos t, doctores dc, tipos_cita tc,ips i, sedes_ips si "
                + "where si.id_ips=i.id_ips "
                + "and dc.tipo= tc.id_tipo "
                + "and dc.id_doctor=t.id_dia "
                + "and t.id_doctor=d.id_dia "
                + "and j.id_dia=d.id_dia "
                + "and j.id_hora= h.id_hora "
                + "and j.tipo_jornada='"+jornada+"' "
                + "and tc.nombre_tipo='"+tipoCita+"' "
                + "and nombre_dia=dayname('"+fechaCita+"')"
                + " and i.id_eps = (select id_eps from eps e, clientes c where e.id_eps = c.id_eps and c.id_cliente="+idCliente+") "
                + "minus select hora_disponible, nombre_doctor, direccion_ips, h.id_hora, dc.id_doctor, d.id_dia "
                + "from citas c, horas h,  dias d,  doctores dc, sedes_ips si "
                + "where "
                + "c.id_dia= d.id_dia "
                + "and c.id_hora=h.id_hora"
                + "and c.id_doctor=dc.id_doctor "
                + "and dc.id_sede_ips= si_id_sede_ips";
        
    
    }
    
    public char getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(char tipoCita) {
        this.tipoCita = tipoCita;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public char getJornada() {
        return jornada;
    }

    public void setJornada(char jornada) {
        this.jornada = jornada;
    }
    
}
