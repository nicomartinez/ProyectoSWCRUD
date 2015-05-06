/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import logic.User;

/**
 *
 * @author USUARIO
 */
@ManagedBean(name="tableBean")
@RequestScoped
public class tableBean implements Serializable {

    private List<User> listUsers;
    
    @PostConstruct
    public void init(){
        listUsers = new ArrayList<>();
        ConnectionBean data = ConnectionBean.getInstance();
        data.loadQuery("select * from user;");
        String[] columns = {"nick","pass"};
        String[] auxRow = {};
        while((auxRow = data.getDBData(columns))!= null){
            listUsers.add(new User(auxRow[0], auxRow[1]));
        }
    }
    public List getUsers(){
        return this.listUsers;
    }
}
