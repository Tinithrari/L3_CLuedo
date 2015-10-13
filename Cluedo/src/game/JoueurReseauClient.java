package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Classe permettant au client de communiquer avec le serveur
 * @author Tinithrari
 */
public class JoueurReseauClient extends JoueurHumain implements Networkable{
    
    private Socket socket;
    
    public JoueurReseauClient(String nom, Socket socket) throws IOException
    {
        super(nom);
        this.socket = socket;
        send("register " + nom);
        
        String request = receive();
        String[] splitted_request = request.split(" ");
        
        if (splitted_request.length == 2 && splitted_request[0].equals("ack"))
        {
            int num_client = Integer.parseInt(splitted_request[1]);
            this.setNum_joueur(num_client);
        }
        else
            throw new IOException("Requête erronée de la part du serveur " + socket.getInetAddress().toString());
    }

    @Override
    public void afficherMessage(String message) 
    {
        super.afficherMessage(message); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String commande() 
    {
        return super.commande(); //To change body of generated methods, choose Tools | Templates.
    }

    public Socket getSocket() {
        return socket;
    }
    
    @Override
    public void send(String message) throws IOException
    {
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println(message);
        writer.flush();
    }

    @Override
    public String receive() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = reader.readLine();
        return message;
    }
}
