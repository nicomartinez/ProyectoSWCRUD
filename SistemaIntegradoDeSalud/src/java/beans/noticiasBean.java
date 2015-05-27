/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class noticiasBean {

    private String ips;
    private List<IPS> list;
    
    public void noticias(ActionEvent actionEvent){
        
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public List<IPS> getList() {
        return list;
    }

    public void setList(List<IPS> list) {
        this.list = list;
    }
    
}
