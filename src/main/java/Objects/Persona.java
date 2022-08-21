/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author Christian
 */
import java.sql.Date;


/**
 *
 * @author css124646
 */
abstract class Persona {
    int id;
    String nombrePersona;
    String apellido1;
    String apellido2;
    String email;
    Date fechaNacimiento;
    String contranna;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContranna() {
        return contranna;
    }

    public void setContranna(String contranna) {
        this.contranna = contranna;
    }
    

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
 public abstract void eliminarDatos();
 public abstract void crearDatos();
 public abstract void modificarDatos();
 public abstract void actualizarDatos();
 public abstract void mostrarDatos();    

}
