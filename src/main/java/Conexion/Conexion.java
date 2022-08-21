/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

/**
 *
 * @author Christian
 */
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Christian
 */
public class Conexion 
{
    private static Conexion conexion;
    private static final String DBurl="jdbc:mysql://localhost:3306/mychat?serverTimezone=UTC";
    private static Connection connection=null;
    
    private Conexion()
    {
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection= DriverManager.getConnection(DBurl,"root","cris12pro");
        } catch (ClassNotFoundException |SQLException|NoSuchMethodException|
                SecurityException|InstantiationException|IllegalAccessException
                |IllegalArgumentException|InvocationTargetException exception)
        {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,exception);
            
        }
        
        
    }
    
    public static synchronized Connection getConexion()
    {
        if (conexion==null) 
        {
         conexion = new Conexion();

        }
        return connection;
    } 
}