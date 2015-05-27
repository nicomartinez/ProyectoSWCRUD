/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class historiaBean {

    private String nombres;
    private int numeroDoc;
    private int numeroTel;
    private char tipoDoc;
    private int numDocPac;
    private char ips;
    
    public void historia(ActionEvent actionEvent){
        
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(int numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public int getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(int numeroTel) {
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
