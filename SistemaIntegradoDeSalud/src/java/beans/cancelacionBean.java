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
public class cancelacionBean {

    private char tipoCita;
    
    public void cancelar(ActionEvent actionEvent){
        
    }

    public char getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(char tipoCita) {
        this.tipoCita = tipoCita;
    }
    
}
