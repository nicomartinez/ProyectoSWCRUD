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
public class sucursalBean {

    private String ips;
    
    /**
     * Creates a new instance of sucursalBean
     */
    public sucursalBean() {
    }

    public void sucursal(ActionEvent actionEvent){
    
    }
    
    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }
    
}