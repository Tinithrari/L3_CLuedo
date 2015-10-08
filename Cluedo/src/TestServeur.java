
import game.JoueurReseauServeur;
import game.ServeurReg;
import java.io.IOException;
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
public class TestServeur {
    public static void main(String[] args)
    {
        int nbConnecte = 0;
        try {
            ServeurReg serveur = new ServeurReg(12345, 1);
            serveur.start();
            
            while (serveur.getNb_connection() != 1);
            
            JoueurReseauServeur joueur = serveur.getClients().get(0);
            
            System.out.println(joueur.getNom() + " s'est connecté");
            
            while (true)
            {
                Scanner sc = new Scanner(System.in);
                String message = sc.nextLine();
                
                joueur.afficherMessage(message);
                message = joueur.commande();
                
                System.out.println(message);
            }
            
        } catch (IOException ex) {
            System.err.println("Impossible d'établir une connexion sur le port 12345");
        }
    }
}
