/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@RequestScoped
public class ConnectionBean {

    private static ConnectionBean instance;
    private Statement command;
    private ResultSet list;
    
    /**
     * Creates a new instance of ConnectionBean
     */
    private ConnectionBean() {
        
    }
    /**
     * Singleton para una única instancia de la base de datos.
     * @return 
     */
    public synchronized static ConnectionBean getInstance(){
        if(instance == null){
            instance = new ConnectionBean();
        }
        return instance;
    }
    /**
     * Realizar consulta "select * from X" en la base de datos.
     * @param inConsult Devuelve la informacion en las columnas
     * @return 
     */
    public ResultSet consult(String inConsult){
        try{
            command = getConnection().createStatement();
            ResultSet consulta = command.executeQuery(inConsult);
            return consulta;
        }catch(SQLException ex){
            System.err.println("Error en la consulta" +ex);
        }
        return null;
    }
    
    public void insert(String query){
        try{
            command = getConnection().createStatement();
            command.execute(query);
        }catch(SQLException ex){
            System.err.println("ERROR INSERTANDO : " +ex);
        }
    }
    /**
     * 
     * @param inConsult
     * @param column
     * @return 
     */
    public String rows (String inConsult, String column){
        try{
            list = consult(inConsult);
            if(list.next()==true){
                return list.getString(column);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    /**
     * Cargar consulta para que se ejecute y almacene los resultados.
     * @param inConsult 
     */
    public void loadQuery(String inConsult){
        list = consult(inConsult);
    }
    /**
     * Accede a la consulta y retorna la fila a conocer
     * @param columns
     * @return 
     */
    public String[] getDBData(String[] columns){
        String[] aux = new String[columns.length];
        try{
            if(list.next()==true){
                for (int i = 0; i < columns.length; i++) {
                    aux[i] = list.getString(columns[i]);
                }
                return aux;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public java.sql.Connection getConnection(){
    java.sql.Connection conexion=null;
      
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/users";
            String usuarioDB="root";
            String passwordDB="bases1";
            conexion = DriverManager.getConnection(servidor,usuarioDB,passwordDB);
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error1 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion=null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion=null;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion=null;
        }
        finally{
            return conexion;
        }
    }
}
