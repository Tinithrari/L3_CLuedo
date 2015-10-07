package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
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
    public String commande() 
    {
        String commande = null;
        try {
            this.send("command");
            commande = this.receive();
            
        } catch (IOException ex) {
            System.err.println("Erreur de communication, fermeture du socket");
            try {
                socket.close();
            } catch (IOException ex1) {
                Logger.getLogger(JoueurReseauServeur.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return commande;
    }

    @Override
    public Carte montrerCarte(Carte lieu, Carte arme, Carte suspect) {
        Carte card = null;
        try {
            this.send("card;lieu:" + lieu + " arme:" + arme + " suspect:" + suspect);
            String carte = this.receive();
            
            if (carte.equals(""))
                return null;
            else
            {
                String[] caracteristique = carte.split(":");
                
                if (caracteristique[0].equals("lieu"))
                    card = new Lieu(caracteristique[1]);
                else if (caracteristique[0].equals("arme"))
                    card = new Arme(caracteristique[1]);
                else if (caracteristique[0].equals("suspect"))
                    card = new Suspect(caracteristique[1]);
            }
        } catch (IOException ex) {
            System.err.println("Erreur de communication, fermeture du socket");
            try {
                socket.close();
            } catch (IOException ex1) {
                Logger.getLogger(JoueurReseauServeur.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return card;
    }

    @Override
    public void afficherMessage(String message) {
        try {
            this.send("afficher;" + message);
        } catch (IOException ex) {
            System.err.println("Erreur de communication, fermeture du socket");
            try {
                socket.close();
            } catch (IOException ex1) {
                Logger.getLogger(JoueurReseauServeur.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
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
