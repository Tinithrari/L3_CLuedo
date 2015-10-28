package game;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * 
 */
public class JoueurOrdi extends Joueur {
	
        protected String[] joueurs;
    
	private HashMap<String, Float> croyanceArme;
	private HashMap<String, Float> croyanceLieu;
	private HashMap<String, Float> croyanceSuspect;
        
        private static LinkedList<String> armes;
        private static LinkedList<String> suspects;
        private static LinkedList<String> lieux;
	
	private HashMap<Integer, Queue<String>> memoireSuggestion;
	
    /**
     * 
     */
    public JoueurOrdi(String nom) 
    {
        super(nom);
        croyanceArme = new HashMap<String, Float>();
        croyanceLieu = new HashMap<String, Float>();
        croyanceSuspect = new HashMap<String, Float>();
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
        String[] cartes;
        joueurs = splitted[1].split(",");
        cartes = splitted[2].split(",");
        
        loadArme();
        loadLieu();
        loadSuspect();
        
        for (String c : cartes) {
            if (suspects.contains(c)) 
            {
                this.addCard(new Suspect(c));
                croyanceSuspect.replace(c, 0.f);
            } 
            else if (armes.contains(c)) 
            {
                this.addCard(new Arme(c));
                croyanceArme.replace(c, 0.f);
            } 
            else 
            {
                this.addCard(new Lieu(c));
                croyanceLieu.replace(c, 0.f);
            }
        }
    }
    
    /**
     * Charge les cartes armes à partir du fichier arme.tt dans le package data
     */
    private void loadArme()
    {
        if (armes == null)
        {
            armes = new LinkedList<String>();
            try {
                BufferedReader bR = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/arme.txt")));
                String line;

                while ((line = bR.readLine()) != null)
                {
                    armes.add(line);
                    croyanceArme.put(line, 0.f);
                }
            } catch (IOException ex) {
                System.err.println("The file doesn't exist ...");
                System.exit(1);
            }
        }
    }
    
    /**
     * Charge les cartes suspects à partir du fichier suspect.txt dans le package data
     */
    private void loadSuspect()
    {
        if (suspects == null)
        {
            suspects = new LinkedList<String>();
            try {
                BufferedReader bR = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/suspect.txt")));
                String line;

                while ((line = bR.readLine()) != null)
                {
                    suspects.add(line);
                    croyanceSuspect.put(line, 0.f);
                }
            } catch (IOException ex) {
                System.err.println("The file doesn't exist ...");
                System.exit(1);
            }
        }
    }
    
    /**
     * Charge les cartes lieu à partir du fichier lieu.txt dans le package data
     */
    private void loadLieu()
    {
        if (lieux == null)
        {
            lieux = new LinkedList<String>();
            try {
                BufferedReader bR = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/lieu.txt")));
                String line;

                while ((line = bR.readLine()) != null)
                {
                    lieux.add(line);
                    croyanceLieu.put(line, 0.f);
                }
            } catch (IOException ex) {
                System.err.println("The file doesn't exist ...");
                System.exit(1);
            }
        }
    }
    
    
}