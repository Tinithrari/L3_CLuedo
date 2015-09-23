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

    private String nom;
    private boolean muet=false;
    private Set<Carte> main;
     
     /**
      * 
      * @return 
      */
     public String getNom(){
         return this.nom;
     }
     
    // Affiche chaque carte de la main du joueur
    public void voirCartes() {
        for(Carte c : main)
        {
            String type;
            if (c instanceof Suspect)
                type = "suspect";
            else if (c instanceof Arme)
                type = "weapon";
            else
                type = "place";
            System.out.println(type + ": " + c.getNom());
        }
    }
    
    public Carte montreCarte(){
        
        Scanner sc = new Scanner(System.in);
        String nom = sc.nextLine();
        
        return null;
    }
    
    /**
     * Ajoute une carte dans la main du joueur
     * @param c La carte à ajouter
     */
    public void addCard(Carte c) {
        main.add(c);
    }
    
    /**
     * @return
     */
     
    public Carte montrerCarte(String nom) {
        return null;
    }

    /**
     * @return
     */
    public abstract String commande();

}
