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
/**
 *
 * @author junior
 */
public class Server 
{
    public static void main(String[] args)
    {
        System.out.println("Iniciando el server udp");
        try
        {            
            DatagramSocket server = new DatagramSocket(5001); //creando socket para la conexion
            byte[] buffer = new byte[1024];
            while(true)
            {
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length); //recibiendo la peticion del que se conecte
                server.receive(peticion);
                //ServerSocket servidor = new ServerSocket(5001);
                if(peticion.getLength() == 0)
                {
                    int puertoCliente = peticion.getPort();//obteniendo el puerto del cliente
                    InetAddress direccion = peticion.getAddress();//opteniendo la ip del cliente para regresar respuesta
                    JOptionPane.showMessageDialog(null, "El cliente envio nada");//imprimiendo

                    String mensaje = new String(peticion.getData());
                    buffer = mensaje.getBytes();// guardando el mensaje de respuesta

                    DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);//creando el paquete que se devolvera al cliente
                    server.send(respuesta); //enviando la resp en el paquete va la info del puerto y la ip
                    server.close();
                    System.exit(0);
                }
                else
                {
                    int puertoCliente = peticion.getPort();//obteniendo el puerto del cliente
                    InetAddress direccion = peticion.getAddress();//opteniendo la ip del cliente para regresar respuesta
                    JOptionPane.showMessageDialog(null, "El cliente envio: " +new String(peticion.getData()));//imprimiendo la peticion
                    String mensaje1 = new String();
                    mensaje1 = "Junior: ";
                    String mensaje = new String(peticion.getData());
                    String resp = mensaje1+mensaje;
                    buffer = resp.getBytes();// guardando el mensaje de respuesta

                    DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);//creando el paquete que se devolvera al cliente
                    server.send(respuesta); //enviando la resp en el paquete va la info del puerto y la ip
                }
                
            }
            
        }catch(SocketException ex)
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex)
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
