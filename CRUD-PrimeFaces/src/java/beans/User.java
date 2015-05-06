/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author USUARIO
 */
public class User {
    private String username;
    private String password;
    
    /**
     * Clase empleada para cargar un bean con un tipo de dato User.
     */
    public User() {
    }
    public User(String inUsername, String inPassword){
        this.username = inUsername;
        this.password = inPassword;
    }
    
    public void setUsername(String inUsername){
        this.username = inUsername;
    }
    public void setPassword(String inPassword){
        this.password = inPassword;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
}
