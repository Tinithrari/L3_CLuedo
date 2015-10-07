/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Tinithrari
 */
public class JoueurReseauClient extends JoueurHumain implements Networkable{
    
    private Socket socket;
    
    public JoueurReseauClient(String nom, Socket socket)
    {
        super(nom);
        this.socket = socket;
    }

    @Override
    public void afficherMessage(String message) {
        super.afficherMessage(message); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String commande() {
        return super.commande(); //To change body of generated methods, choose Tools | Templates.
    }

    public Socket getSocket() {
        return socket;
    }
    
        @Override
    public void send(String message) throws IOException{
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println(message);
        writer.flush();
    }

    @Override
    public String receive() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = reader.readLine();
        return message;
    }
}
