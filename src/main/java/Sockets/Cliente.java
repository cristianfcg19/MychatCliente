/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

/**
 *
 * @author Christian
 */
import java.net.*;
import javax.swing.JOptionPane;
import java.io.*;
import Aplicacion.FormCliente;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class  Cliente extends Thread
{
    private FormCliente formCliente;
    public ArrayList listaMensajes=new ArrayList();
    int PUERTO ;
    String nombrecliente;
    Socket sc;
    DataOutputStream salida;
    DataInputStream entrada;
    private String mensajeRecibido;
    private String msn = "";
    private String mensajeInforme;
    
public Cliente(String nombrecliente ,int PUERTO,FormCliente formCliente)
{
        this.nombrecliente = nombrecliente;
        this.PUERTO=PUERTO;
        this.formCliente=formCliente;
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
           
           try {
               Thread.sleep(100);
           } catch (InterruptedException ex) {
               Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           mensajeInforme="Escriba un msn para enviar";
           formCliente.lbMensaje.setText(mensajeInforme);
           
           salida.writeUTF(msn);//enviamos mensaje
           msn="";
           mensajeRecibido = entrada.readUTF();//Leemos respuesta
           listaMensajes.add(formCliente.indice,mensajeRecibido);
           
          int count = 0;
          while (listaMensajes.size() > count) {
	 System.out.println(listaMensajes.get(count));
         count++;
          }
           
           
           formCliente.mensaje.setText(mensajeRecibido);
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
