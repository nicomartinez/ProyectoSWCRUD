/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author USUARIO
 */
public class User {

    private String nick;
    private String pass;
    
    public User(String nick, String pass){
        this.nick = nick;
        this.pass = pass;
    }
    
    public String getUser(){
        return this.nick;
    }
    public String getPass(){
        return this.pass;
    }
}
