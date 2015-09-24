package cluedo;

import game.Jeu;
import game.Joueur;
import game.JoueurHumain;
import java.util.LinkedList;

/**
 * Représente l'interface de lancement du cluedo
 * @author Tinithrari
 */
public class CluedoSolo {

    private Jeu jeu;
    
    /**
     * Permet de lancer une partie
     */
    public void run()
    {
        JoueurHumain j1 = new JoueurHumain("Joueur 1"), j2 = new JoueurHumain("Joueur 2"), j3 = new JoueurHumain("Joueur 3"), j4 = new JoueurHumain("Joueur 4");
        Jeu jeu;
        LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
        int iterator = 0;
        
        joueurs.add(j1);
        joueurs.add(j2);
        joueurs.add(j3);
        joueurs.add(j4);
        
        jeu = new Jeu(joueurs);
        
        while (! (jeu.estGagne() || j1.aPerdu() || j2.aPerdu() || j3.aPerdu() || j4.aPerdu() ))
        {
            jeu.effectuerTour(joueurs.get(iterator));
            
            iterator++;
            iterator %= joueurs.size();
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        CluedoSolo game = new CluedoSolo();
        
        game.run();
    }
    
}
