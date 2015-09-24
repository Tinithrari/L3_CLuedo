package game;


import java.util.*;

/**
 * Représente un joueur humain
 */
public class JoueurHumain extends Joueur {

    /**
     * Créé un joueur humain
     * @param nom Le nom du joueur
     */
    public JoueurHumain(String nom) {
        super(nom);
    }

    /**
     * Demande une commande aux joueurs
     * @return la commande saisie par le joueur
     */
    public String commande() {
        String commande;
        
        Scanner sc = new Scanner(System.in);
        
        commande = sc.nextLine();
        return commande;
    }

}