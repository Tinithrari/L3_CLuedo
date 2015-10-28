package game;


import java.io.IOException;
import java.util.*;
/**
 * 
 */
public class JoueurOrdi extends Joueur {
	
	private HashMap<String, Float> croyanceArme;
	private HashMap<String, Float> croyanceLieu;
	private HashMap<String, Float> croyanceSuspect;
	
	private HashMap<Integer, Queue<String>> memoireSuggestion;
	
    /**
     * 
     */
    public JoueurOrdi(String nom) 
    {
        super(nom);
       // Exemle usage HashMap : memoireSuggestion.put(1, new LinkedList<String>());
    }
    
    private void reactionSuggestion(String suggestion)
    {
    	
    }
    
    private void traitementRespond(String nomCarte)
    {
    	
    }

    private void reflechir()
    {
    	
    }
    
    private String eliminerCarte()
    {
    	return null;
    }
    
    /**
     * @return
     */
    public String commande() {
        // TODO implement here
        return "";
    }

    @Override
    public void send(String message) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String receive() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void erreur(String[] splitted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void commencer(String[] splitted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}