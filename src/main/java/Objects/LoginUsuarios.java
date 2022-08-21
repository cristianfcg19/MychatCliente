/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author Christian
 */
import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Christian
 */
public class LoginUsuarios extends Usuario{
    
    public String tipoRol;
    
    

   

    
    public LoginUsuarios()
    {
    
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }
    
    


    public  void buscarUsuario(String buscar)
    {
        try 
        {
        PreparedStatement consultaNombreUsuario =Conexion.getConexion().prepareStatement("SELECT c.idusuario,c.nombreusuarios,c.primerApellido,d.tiporoles FROM usuarios as c inner join rol_usuario as b on b.idusuario=c.idusuario INNER JOIN roles as d on d.idrole=b.idrole where c.Email in(?)");
        consultaNombreUsuario.setString(1, buscar);
        ResultSet resultSet =consultaNombreUsuario.executeQuery();
        
        while(resultSet!=null&&resultSet.next())
                {   id=resultSet.getInt(1);
                    nombrePersona=resultSet.getString(2);
                    apellido1=resultSet.getString(3);
                    tipoRol=resultSet.getString(4);
                   
                }
        
        } catch (SQLException e) 
        {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,e);
        }
    } 
 
    public  boolean loggiarse(String verificarEmail,String verificarcontraseña)
    {
        try {
        PreparedStatement extrarUsuario = Conexion.getConexion().prepareStatement("SELECT c.Email,c.contraseña FROM usuarios as c WHERE c.Email in(?) AND c.contraseña in(?)");
          extrarUsuario.setString(1, verificarEmail);
          extrarUsuario.setString(2, verificarcontraseña);
        ResultSet rs=extrarUsuario.executeQuery();
        
             while (rs!=null && rs.next()) 
            {
              
               email= rs.getString(1);
               contranna=rs.getString(2);
                
             
          
              

               if(email.equals(verificarEmail)&&contranna.equals(verificarcontraseña))
               {
                   buscarUsuario(email);
                   JOptionPane.showMessageDialog(null, "Se ha iniciado seccion");
                   return true;
               }
              
            }
            
            
        } 
        catch (SQLException e) 
        {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,e);
        }
        return false;
        
    }

  
    
    
 
    
} 
