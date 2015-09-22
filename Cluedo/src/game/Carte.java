package game;

/**
 * Classe abstraite représentant une carte
 */
public abstract class Carte {

    private String nom;

    /**
     * Construis une carte à partir de son nom
     * @param nom le nom de la carte à créer
     */
    public Carte(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le nom de la carte
     * @return Le nom de la carte
     */
    public String getNom() {
        // TODO implement here
        return nom;
    }

    /**
     * Permet de récupérer une chaine de caractère représentant la carte
     * @return le nom de la carte
     */
    @Override
    public String toString() {
        return nom;
    }

    /**
     * Permet de vérifier l'égalité entre carte ou entre carte et chaine de caractère
     * @param obj Une carte ou une chaine de caractère
     * @return vrai si les nom correspondent, faux sinon
     * @throws IllegalArgumentException Exception lancé lorsque l'objet n'est pas de type String ou Carte
     */
    @Override
    public boolean equals(Object obj) throws IllegalArgumentException{
        if (obj instanceof Carte)
            return this.nom.equals(((Carte)obj).getNom());
        
        else if (obj instanceof String)
            return this.nom.equals((String)obj);
        
        else
            throw new IllegalArgumentException("L'argument entré n'est pas du bon type");
    }

    
}