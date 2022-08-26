/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

import Aplicacion.FormCliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author css124646
 */
public class ReceiveDataClient extends Thread{
    private FormCliente formCliente;
    int PUERTO ;
    String nombrecliente;
    Socket socketReceive;
    DataInputStream entrada;
    DataOutputStream salida;
    String datosQueSevanADesplegarEnChat = "";
    String mensajeRecibido= "";
    String mensajeEnviar = "";
    
    
    
public ReceiveDataClient(String nombrecliente ,int PUERTO,FormCliente formCliente)
{
        this.nombrecliente = nombrecliente;
        this.PUERTO=PUERTO;
        this.formCliente=formCliente;
 }

    public String getMensajeEnviar() {
        return mensajeEnviar;
    }

    public void setMensajeEnviar(String mensajeEnviar) {
        this.mensajeEnviar = mensajeEnviar;
    }



public void run() {
  recibeDatos();
      
}
private void  agregarDatosAChatDisplayReceived(String mensaje){
    System.out.println("entra a agregar DATOS CHAT!!!");
    System.out.println("el mensaje a agregarDatos chat es +"+mensaje+"+");
    formCliente.jTextAreaChatDisplay.setText(formCliente.jTextAreaChatDisplay.getText()+"remote-> :"+mensaje+"\n");
    mensajeRecibido="";
}

    private void  agregarDatosAChatDisplaySend(String mensaje){
    System.out.println("entra a agregar DATOS CHAT!!!");
    System.out.println("el mensaje a agregarDatos chat es +"+mensaje+"+");
    formCliente.jTextAreaChatDisplay.setText(formCliente.jTextAreaChatDisplay.getText()+"local-> :"+mensaje+"\n");
    mensajeEnviar="";
    }


public void recibeDatos(){
    String HOST = "localhost";
        //en este bloque establecemos el socket
        String ultimoMsjrecibidoGuardado="";
        try {

            
            socketReceive = new Socket(HOST, PUERTO);
            entrada = new DataInputStream(socketReceive.getInputStream());
            salida = new DataOutputStream(socketReceive.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ReceiveDataClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        do{
            
            System.out.println("mensaje recibido en inicio while +"+mensajeRecibido+"+" );
        
            /*
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(ReceiveDataClient.class.getName()).log(Level.SEVERE, null, ex);
        }
           */           
            
            //en este bloque se lee el mensaje Recibido
        try {
            System.out.println("mensaje recibido en el try catch inicio+"+mensajeRecibido+"+" );
            mensajeRecibido = entrada.readUTF();//Leemos respuesta
            
            if (mensajeRecibido.equals(ultimoMsjrecibidoGuardado)){
                mensajeRecibido="";
                ultimoMsjrecibidoGuardado = "esperemos nunca tener que llegar aca";
            }
            
            System.out.println("mensaje recibido en el try catch final+"+mensajeRecibido+"+" );
            
        } catch (IOException ex) {
            Logger.getLogger(ReceiveDataClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //en este bloque se agrega el mensaje recibido al chat
            System.out.println("mensaje recibido de cliente +"+mensajeRecibido+"+" );
        if (!mensajeRecibido.equals("")){
            System.out.println("+++++++++++++++++++++++++"
                    + "\n+++++++++++++++++++++++++\n+++++++++++++++++++++++++\n+++++++++++++++++++++++++" );
            ultimoMsjrecibidoGuardado=mensajeRecibido;
            agregarDatosAChatDisplayReceived(mensajeRecibido);
            mensajeRecibido="";   
        }
            //System.out.println("mensaje recibido de cliente despues del if +"+mensajeRecibido+"+" );
        
            
            //en este bloque se envia informacion
            System.out.println("mensaje a enviar " + mensajeEnviar);
 
        if(!mensajeEnviar.equals("")){
            try {            
                salida.writeUTF(mensajeEnviar);
            } catch (IOException ex) {
                Logger.getLogger(ReceiveDataClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            agregarDatosAChatDisplaySend(mensajeEnviar);
            mensajeEnviar="";
        }
        
       
        }while(!mensajeRecibido.equals("Exit")||socketReceive.isConnected());
        
        try {
            socketReceive.close();
        } catch (IOException ex) {
            Logger.getLogger(ReceiveDataClient.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
}
