/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author JONATHAN
 */
@ManagedBean
@ApplicationScoped
public class countBean implements Serializable {
     /**
     * Creates a new instance of countBean
     */
    public countBean() {
    }
    
    private int number;
 
    public int getNumber() {
        return number;
    }
 
    public void increment() {
        number++;
    }
}
