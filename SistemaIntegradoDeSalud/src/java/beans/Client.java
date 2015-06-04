/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class Client {

    private static Client instance;
    private int idUser;

    /**
     * Creates a new instance of Client
     */
    private Client() {
    }
    
    public static synchronized Client getInstance(){
        if(instance == null){
            instance = new Client();
        }
        return instance;
    }
    public void setIdUser(int inIdUser){
        this.idUser = inIdUser;
    }
    public int getIdUser(){
        return this.idUser;
    }
}
