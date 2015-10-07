
import game.JoueurReseauClient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tinithrari
 */
public class TestClient {
    public static void main(String[] args)
    {
        
        try {
            JoueurReseauClient client = new JoueurReseauClient("Xavier", new Socket(InetAddress.getByName("localhost"), 12345));
            client.send(client.getNom());
            
            while (true)
            {
                Scanner sc = new Scanner(System.in);
                String message = client.receive();
                
                if (message.equals("command"))
                {
                    message = sc.nextLine();
                    client.send(message);
                }
                else
                {
                    String[] divided = message.split(";");
                    if (divided[0].equals("afficher"))
                    {
                        System.out.println(divided[1]);
                    }
                }
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
