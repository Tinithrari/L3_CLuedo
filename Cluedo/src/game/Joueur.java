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
     
     // Accesseur pour le nom du joueur
     public String getNom(){
         return this.nom;
     }
     
    // Affiche chaque carte de la main du joueur
    public void voirCartes() {
        for(Carte c : main) System.out.println(c.getNom);
    }
    
    public void carteMontré(Carte c){
        System.out.println("Le joueur vous a montré la carte "+c.getNom()+" !");
    }
    // Ajoute une carte dans la main du joueur (lors de la distribution)
    public void addCard(Carte c) {
        main.add(c);
    }
    /**
     * @return
     */
     
    // Retourne la carte si elle est présente dans la main, sinon null
    public Carte montrerCarte(Carte c) {
        for(Carte c : main){
            if(main.contains(c)) return c;
        }
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
