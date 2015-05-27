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
    private Date fechaCita;
    private char jornada;
    
    /**
     * Creates a new instance of solicitarBean
     */
    public solicitarBean() {
    }

    public void solicitar(ActionEvent actionEvent){
    
    }
    
    public char getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(char tipoCita) {
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
    
}
