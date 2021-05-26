/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica03;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author junior
 */
public class client 
{
    public static void main(String[] args) throws UnknownHostException
    {
        System.out.println("Ejecutando cliente");
        try {
            DatagramSocket socketUdp = new DatagramSocket(); //creando socket para la conexion con el server
            final int Puerto_servidor = 5001;

            String add = JOptionPane.showInputDialog("IP del servidor: ");
            //String add = "192.168.10.179";
            InetAddress direccion_servidor = InetAddress.getByName(add);
            //Socket cliente = new Socket(direccion_servidor, Puerto_servidor);

            String mensaje;
            
            for(;;)
            {
                mensaje = JOptionPane.showInputDialog("Ingrese una cadena: ");
                byte[] buffer = new byte[1024];
                buffer = mensaje.getBytes();
                DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length,direccion_servidor, Puerto_servidor);
                socketUdp.send(pregunta);//enviando el datagrama

                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
                socketUdp.receive(respuesta);//recibiendo respuesta del servidor
                if(respuesta.getLength() == 0)
                {
                    JOptionPane.showMessageDialog(null, "Cerrando cliente");
                    socketUdp.close();
                    System.exit(0);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Resp del server: "+new String(respuesta.getData())); 
                }
            }
            
        
        }catch (SocketException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }catch (UnknownHostException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
}
