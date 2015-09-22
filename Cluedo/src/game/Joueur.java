package game;


import java.util.*;

/**
 * 
 */
public abstract class Joueur {

    /**
     * 
     */
    public Joueur() {
    }

    /**
     * 
     */
    private String nom;

    /**
     * 
     */
    private boolean muet=1;

    /**
     * 
     */
    private Set<Carte> main;

    /**
     */
     
    // Affiche chaque carte de la main du joueur
    public void voirCartes() {
        for(Carte c : main) System.out.println(c.getNom);
    }
    
    // Ajoute une carte dans la main du joueur (lors de la distribution)
    public void addCard(Carte c) {
        main.add(c);
    }
    /**
     * @return
     */
    public Carte montrerCarte() {
        // TODO implement here
        return null;
    }

    /**
     * @param lieu 
     * @param arme 
     * @param meurtrier 
     */
    public void suggerer(Lieu lieu, Arme arme, Suspect meurtrier) {
        // TODO implement here
    }

    /**
     * @param lieu 
     * @param arme 
     * @param meurtrier 
     */
    public void accuser(Lieu lieu, Arme arme, Suspect meurtrier) {
        // TODO implement here
    }

    /**
     * @return
     */
    public abstract String commande();

}
