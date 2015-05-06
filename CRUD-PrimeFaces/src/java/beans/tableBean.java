/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USUARIO
 */
@ManagedBean(name="dtBasicView")
@RequestScoped
public class tableBean {

    private List<User> list;
    /**
     * Creates a new instance of tableBean
     */
    public tableBean() {
        list = new ArrayList<>(); // Inicializacion de la List.
        ConnectionBean data = ConnectionBean.getInstance();//Instancia a BD.
        data.loadQuery("select * from user;");//Cargar usuarios.
        String[] columns = {"nick","pass"};//Seleccionar columnas.
        String[] auxRow = {};   //Variable auxiliar para almacenar temporalmente los valores.
        while((auxRow = data.getDBData(columns))!= null){
            list.add(new User(auxRow[0], auxRow[1]));//Cargar la lista con los usuarios.
        }
    }
    
    public List getList(){
        return this.list;
    }
}
