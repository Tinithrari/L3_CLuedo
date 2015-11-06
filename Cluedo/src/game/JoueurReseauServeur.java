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
public class JoueurReseauServeur extends Joueur{

    private Socket socket;
    
    /**
     * Constructeur de l'interface de communication avec le réseau pour le joueur réseau
     * @param socket Le socket permettant de communiquer avec le joueur distant
     * @param nom Le nom du joueur
     */
    public JoueurReseauServeur(Socket socket, String nom)
    {
        super(nom);
        this.socket = socket;
    }
    
    /**
     * Permet d'envoyer un message au joueur distant
     * @param message Le message a envoyer
     */
    @Override
    public void send(String message) throws IOException{
    	try {
			Thread.sleep(250);
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
	        writer.println(message);
	        writer.flush();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Permet de recevoir le message du joueur distant
     * @return Le message du joueur distant
     */
    @Override
    public String receive() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = reader.readLine();
        return message;
    }
}
