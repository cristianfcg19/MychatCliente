/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Christian
 */
public class mutirunnuble implements Runnable{

      int PUERTO ;
    String nombrecliente;
    Socket sc;
    DataOutputStream salida;
    DataInputStream entrada;
    private String mensajeRecibido;
    private String msn = "";
    private String mensajeInforme;
    
public mutirunnuble(String nombrecliente ,int PUERTO)
{
        this.nombrecliente = nombrecliente;
        this.PUERTO=PUERTO;
 }

    public String getMensajeRecibido() {
        return mensajeRecibido;
    }

    public void setMensajeRecibido(String mensajeRecibido) {
        this.mensajeRecibido = mensajeRecibido;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getMensajeInforme() {
        return mensajeInforme;
    }

    public void setMensajeInforme(String mensajeInforme) {
        this.mensajeInforme = mensajeInforme;
    }




    @Override
public void run() {

    cliente1();  
      
}


public void cliente1()
{
    try 
    {
       String HOST = "localhost";
       sc = new Socket(HOST, PUERTO);
       salida = new DataOutputStream(sc.getOutputStream());
       entrada = new DataInputStream(sc.getInputStream());
       
       while(!msn.equals("Exit")){
           mensajeInforme="Escriba un msn para enviar";
          
           
           salida.writeUTF(nombrecliente+" :->"+msn);//enviamos mensaje
           mensajeRecibido = entrada.readUTF();//Leemos respuesta
           System.out.println(mensajeRecibido);
   
   }
   sc.close();
   
  
   } 
       
     catch (IOException  e) {
      System.out.println(e);
    }
}
public void cliente2()
{
    try 
    {
       String HOST = "localhost";
       sc = new Socket(HOST, PUERTO);
       salida = new DataOutputStream(sc.getOutputStream());
       entrada = new DataInputStream(sc.getInputStream());
       String msn = "";
       while(!msn.equals("x")){
           System.out.println("Escriba un msn para enviar");
           msn = JOptionPane.showInputDialog("Escriba mensaje");
           salida.writeUTF("Cliente 2:->"+msn);//enviamos mensaje
           mensajeRecibido = entrada.readUTF();//Leemos respuesta
           System.out.println(mensajeRecibido);
   
   }
   sc.close();
   } 
       
     catch (IOException e) {
      
    }
}
}
