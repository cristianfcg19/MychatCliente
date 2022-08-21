/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christian
 */
public class Usuario extends Persona{
     final String SELECT_TODOS_LOS_USUARIOS="Select * from mostrarUsuarios";
    ArrayList listaUsuarios=new ArrayList<>();
    Direcciónred direcciónred =new Direcciónred();
    private String ip ;
    private String tipored; 
    private String tipoRol;//este espara imprimir en la tabla
    private int rol;//esta para añadir registro
    private String contrasenna;
    public  DefaultTableModel modelo=new DefaultTableModel();
    private  String tiporol; 

    public String getTiporol() {
        return tiporol;
    }

    public void setTiporol(String tiporol) {
        this.tiporol = tiporol;
    }
    
    
    @Override
    public void eliminarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  @Override
    public void  mostrarDatos()
    {try {
            
        
        PreparedStatement consulta =Conexion.getConexion().prepareStatement(SELECT_TODOS_LOS_USUARIOS);
        ResultSet rs= consulta.executeQuery();
          
                
                modelo.addColumn("ID");
                modelo.addColumn("Nombre");
                modelo.addColumn("Primer apellido");
                modelo.addColumn("Segundo Apellido");
                modelo.addColumn("Email");
                modelo.addColumn("Contraseña");
                modelo.addColumn("Fecha Nacimiento");
                modelo.addColumn("Direccion ip");
                modelo.addColumn("Roles");    
        while(rs!=null&&rs.next())
            {
                this.id=rs.getInt(1);
                this.nombrePersona=rs.getString(2);
                this.apellido1=rs.getString(3);
                this.apellido2=rs.getString(4);
                this.email=rs.getString(5);
                this.contrasenna=rs.getString(6);
                this.fechaNacimiento=rs.getDate(7);
                this.direcciónred.setIpv4(rs.getString(8));
                this.tipoRol=rs.getString(9);
                listaUsuarios.add(0,id);
                listaUsuarios.add(1,nombrePersona);
                listaUsuarios.add(2,apellido1);
                listaUsuarios.add(3,apellido2);
                listaUsuarios.add(4,email);
                listaUsuarios.add(5,contrasenna);
                listaUsuarios.add(6,fechaNacimiento);
                listaUsuarios.add(7,direcciónred.getIpv4());
                listaUsuarios.add(8,tipoRol);
                modelo.addRow(listaUsuarios.toArray());
                
            }
        } catch (SQLException e) {
             Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE,null, e);
        }        // TODO add your handling code here:
    }
   
}
