
import game.JoueurReseauClient;
import java.io.IOException;
import java.net.Socket;

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
            JoueurReseauClient client = new JoueurReseauClient("John", new Socket("localhost", 12345));
            client.send("register " + client.getNom());
            String message = client.receive();
            
            if (! message.equals("ack 0"))
                throw new IOException("Erreur de communication");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
