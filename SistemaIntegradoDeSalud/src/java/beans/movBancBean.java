/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class movBancBean {

    private List<Banco> list;
    
    /**
     * Creates a new instance of movBancBean
     */
    public movBancBean() {
    }

    public List<Banco> getList() {
        return list;
    }

    public void setList(List<Banco> list) {
        this.list = list;
    }
}
