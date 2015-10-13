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
    //private Jeu j;
    //Liste de joueurs clients ?
    
    public JoueurReseauServeur(Socket socket, String nom)
    {
        super(nom);
        this.socket = socket;
        //j = new Jeu();
        //Initialisation de joueurs clients ?
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

	//gere ses propres commandes en local help,show,exit
	public void maCommande()
	{
		Scanner scan = new Scanner(System.in);
		
		String commande = scan.nextLine();
		String[] splitted = commande.split(" ");
		
		switch(splitted[0])
		{
			case "exit" :
				try{
					socket.close();
					this.send("L'hôte a quitté la partie, fermeture du socket...");
					System.err.println("L'hôte a quitté la partie, fermeture du socket...");
				}catch (IOException ex1) {
		    		Logger.getLogger(JoueurReseauServeur.class.getName()).log(Level.SEVERE, null, ex1);
		    	}
		    	break;
		    
		    case "help" : 
		    	System.out.println("Aide du jeu");
		    	break;
		    
		    case "show" :
		    	this.voirCartes();
		    	break;
		    	
		  	case "move" :
				//Instance du jeu j et joueur joueur
				//j.gererCommandeMove(splitted,joueurclient);
				break;
        }
	}
	
	//Traite les commandes que les joueurs clients envoie au serveur
	public void traitementCommande()
	{
		//On ne gere que move car les autres commandes sont en internes
		String message = commande();
		String[] splitted = commande.split(" ");
		
		switch(splitted[0])
		{
			case "move" :
				//Instance du jeu j et joueur joueur
				//j.gererCommandeMove(splitted,joueurclient);
				break;
		}
	}
}
