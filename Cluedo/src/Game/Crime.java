package Game;

/**
 * 
 */
public class Crime {
    
    private final Arme arme;
    private final Suspect meurtrier;
    private final Lieu lieu;

    
    public Crime(Arme arme, Suspect suspect, Lieu lieu) {
        this.arme = arme;
        this.meurtrier = suspect;
        this.lieu = lieu;
    }

    /**
     * Obtient le lieu du crime
     * @return Le lieu du crime
     */
    public Lieu getLieu() {
        return lieu;
    }

    /**
     * Obtient l'arme du crime
     * @return L'arme du crime
     */
    public Arme getArme() {
        return arme;
    }

    /**
     * Obtient le meurtrier
     * @return Le meurtrier
     */
    public Suspect getMeurtrier() {
        return meurtrier;
    }

}