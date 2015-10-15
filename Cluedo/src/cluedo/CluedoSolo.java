package cluedo;

import game.Jeu;
import game.Joueur;
import game.JoueurHumain;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Représente l'interface de lancement du cluedo
 * @author Tinithrari
 */
public class CluedoSolo {

    private Jeu jeu;
    
    /**
     * Permet de lancer une partie
     */
    public void run(int nbJoueur)
    {
        int numeroJoueur;
        Jeu jeu;
        LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
        int iterator = 0;
        
        for (numeroJoueur = 0; numeroJoueur < nbJoueur; numeroJoueur++)
        {
            joueurs.add(new JoueurHumain("Player" + numeroJoueur));
            joueurs.get(numeroJoueur).setNum_joueur(numeroJoueur);
        }
        
        jeu = new Jeu(joueurs);
        
        while (! (jeu.estGagne() || partiePerdu(joueurs) ) )
        {
            try {
                jeu.effectuerTour(joueurs.get(iterator));
                
                iterator++;
                iterator %= joueurs.size();
            } catch (IOException ex) {
                for (Joueur j : joueurs)
                    try {
                        j.send("error other");
                    } catch (IOException ex1) {
                        System.exit(1);
                    }
                System.exit(1);
            }
        }
        if (partiePerdu(joueurs))
            System.out.println("Nobody has won the game");
    }
    
    private boolean partiePerdu(LinkedList<Joueur> joueurs)
    {
        boolean partiePerdu = true;
        
        for (Joueur j : joueurs)
            partiePerdu &= j.aPerdu();
        
        return partiePerdu;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        CluedoSolo game = new CluedoSolo();
        Scanner sc = new Scanner(System.in);
        int nbJoueur = 0;
        
        while (nbJoueur < 3 || nbJoueur > 6)
        {
            System.out.println("How many players ? (between 3 and 6)");
            nbJoueur = sc.nextInt();
        }
        game.run(nbJoueur);
    }
    
}
