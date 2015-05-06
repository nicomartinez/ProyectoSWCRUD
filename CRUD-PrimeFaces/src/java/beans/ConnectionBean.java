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
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class ConnectionBean {
    private static ConnectionBean instance;
    private ResultSet list; //Vector con las tuplas de los resultados.
    private Statement command; //Variable necesaria para la conexión con la base de datos.
    /**
     * Constructor
     */
    private ConnectionBean(){
    }
    /**
     * Patrón de Singleton para asegurar una única carga de la base de datos.
     * @return 
     */
    public static synchronized ConnectionBean getInstance(){
        if(instance == null){
            instance = new ConnectionBean();
        }
        return instance;
    }
    
    //--------------METODOS DE CONEXION Y CONSULTA A LA BASE DE DATOS-----
    /**
     * Accede a list y retorna la tupla deseada.
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
    
    /**
     * Cargar consulta para que se ejecute y almacenen los resultados en el ResultSet.
     * @param inConsult 
     */
    public void loadQuery(String inConsult){
        list = consult(inConsult);
    }
    
    /**
     * Este método leerá la consulta y retornará la columna que se le indique.
     * @param inConsult
     * @param column
     * @return 
     */
    public String rows (String inConsult, String column){
        try{
            list = consult(inConsult);
            if(list.next()==true){  //Recorrerá la siguiente tupla.
                return list.getString(column); //Retornará el valor obtenido.
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    /**
     * El método para efectuar la consulta a través del Statement sin esperar
     * una tupla como resultado es "execute".
     * @param query 
     */
    public void insert(String query){
        try{
            command = getConnection().createStatement();
            command.execute(query);
        }catch(SQLException ex){
            System.err.println("Error generando inserción: " +ex);
        }
    }
    
    /**
     * Realizar consulta "select * from X" en la base de datos.
     * @param inConsult Devuelve la informacion en las columnas
     * @return 
     */
    public ResultSet consult(String inConsult){
        try{
            command = getConnection().createStatement();
            ResultSet consulta = command.executeQuery(inConsult); // ExecuteQuery para efectuar consulta.
            return consulta;
        }catch(SQLException ex){
            System.err.println("Error en la consulta..." +ex);
        }
        return null;
    }
    /**
     * Método en Java para conectarse a la base de datos, es necesario tener la
     * libreria de JDBC.
     * @return 
     */
    public java.sql.Connection getConnection(){
    java.sql.Connection conexion=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");             //Definición del driver jdbc.
            String servidor = "jdbc:mysql://localhost/users";   //La base de datos será "users"
            String usuarioDB="root";                        //Usuario
            String passwordDB="bases1";                     //Contraseña para el acceso a la base de datos.
            conexion = DriverManager.getConnection(servidor,usuarioDB,passwordDB);
        }   //EXEPCIONES
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
