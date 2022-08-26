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
public class SendDataClient extends Thread
{
    private FormCliente formCliente;
    public ArrayList listaMensajes=new ArrayList();
    int PUERTO ;
    String nombrecliente;
    Socket socketReceive;
    DataOutputStream salida;
    DataInputStream entrada;
    private String mensajeRecibido = "";
    private String mensajeAEnviar = "";
    private String mensajeInforme;
    
public SendDataClient(String nombrecliente ,int PUERTO,FormCliente formCliente)
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

    public String getMensajeAEnviar() {
        return mensajeAEnviar;
    }

    public void setMensajeAEnviar(String msn) {
        this.mensajeAEnviar = msn;
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

public void delayDeTiempo(){
               try {
               Thread.sleep(50);
           } catch (InterruptedException ex) {
               Logger.getLogger(SendDataClient.class.getName()).log(Level.SEVERE, null, ex);
           }
}


public void cliente1()
{
    try 
    {
       String HOST = "localhost";
       socketReceive = new Socket(HOST, PUERTO);
       salida = new DataOutputStream(socketReceive.getOutputStream());
       entrada = new DataInputStream(socketReceive.getInputStream());
       String datosQueSevanADesplegarEnChat =  "";
       while(!mensajeAEnviar.equals("Exit")){
           System.out.println("Entra al while");
           try {
               System.out.println("Ve el delay");
               Thread.sleep(70);
           } catch (InterruptedException ex) {
               System.out.println("entra al catch ");
               Logger.getLogger(SendDataClient.class.getName()).log(Level.SEVERE, null, ex);
           }
           System.out.println("pasa el try catch");
           
           //aplica la etiqueta
           mensajeInforme="Escriba un msn para enviar";//solo pone un valor al label
           formCliente.lbMensaje.setText(mensajeInforme);//solo pone un valor al label
           System.out.println("aplica la etiqueta");
           
           //lee el msj 
           System.out.println("lee data");
           System.out.println("Cliente recibe: +"+ mensajeRecibido+"+");
           mensajeRecibido = entrada.readUTF();//Leemos respuesta
           System.out.println("Cliente recibe: "+ mensajeRecibido);
           System.out.println("Cleinte envia: "+ mensajeAEnviar);
           
            if (!mensajeRecibido.equals("")){
                datosQueSevanADesplegarEnChat=datosQueSevanADesplegarEnChat+ mensajeRecibido+"\n";
                formCliente.jTextAreaChatDisplay.setText(datosQueSevanADesplegarEnChat);
            }
            

           if(!mensajeAEnviar.equals("")){
                salida.writeUTF(mensajeAEnviar);//enviamos mensaje
                datosQueSevanADesplegarEnChat=datosQueSevanADesplegarEnChat+mensajeAEnviar+"->server\n";
                formCliente.jTextAreaChatDisplay.setText(datosQueSevanADesplegarEnChat);
                mensajeAEnviar="";
            }
           
           
           System.out.println(mensajeRecibido);
   
   }
   socketReceive.close();
   
  
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
       socketReceive = new Socket(HOST, PUERTO);
       salida = new DataOutputStream(socketReceive.getOutputStream());
       entrada = new DataInputStream(socketReceive.getInputStream());
       String msn = "";
       while(!msn.equals("x")){
           System.out.println("Escriba un msn para enviar");
           msn = JOptionPane.showInputDialog("Escriba mensaje");
           salida.writeUTF("Cliente 2:->"+msn);//enviamos mensaje
           mensajeRecibido = entrada.readUTF();//Leemos respuesta
           System.out.println(mensajeRecibido);
   
   }
   socketReceive.close();
   } 
       
     catch (IOException e) {
      
    }
}
    
}
