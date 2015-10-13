package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Classe permettant au serveur de communiquer avec ses joueurs
 * @author Tinithrari
 */
public class JoueurReseauServeur extends Joueur implements Networkable{

    private Socket socket;
    
    public JoueurReseauServeur(Socket socket, String nom)
    {
        super(nom);
        this.socket = socket;
    }

    @Override
    public Carte montrerCarte(Carte lieu, Carte arme, Carte suspect) 
    {
        return null;
    }

    @Override
    public void afficherMessage(String message) 
    {
        
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

    @Override
    public String commande() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
