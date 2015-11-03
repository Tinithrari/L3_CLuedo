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
        String[] splitted = message.split(" ");
        
        if (splitted[0].equals("respond"))
        {
            String carte = splitted[1];
            
            if (croyanceArme.containsKey(carte))
                removeEntry(croyanceArme, carte);
            else if (croyanceLieu.containsKey(carte))
                removeEntry(croyanceLieu, carte);
            else
                removeEntry(croyanceLieu, carte);
        }
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
        
        initHashMap(croyanceArme);
        initHashMap(croyanceLieu);
        initHashMap(croyanceSuspect);
        
        for (String c : cartes) 
        {
            if (suspects.contains(c)) 
            {
                this.addCard(new Suspect(c));
                removeEntry(croyanceSuspect,c);
            } 
            else if (armes.contains(c)) 
            {
                this.addCard(new Arme(c));
                removeEntry(croyanceArme,c);
            } 
            else 
            {
                this.addCard(new Lieu(c));
                removeEntry(croyanceLieu,c);
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
    
    private void initHashMap(HashMap<String,Float> hashmap)
    {
        float pourcentage;
        if(hashmap == null)
            return;
        
        if(hashmap.size()==0)
            return;
        
        pourcentage = 1/hashmap.size();
        
        for(String s : hashmap.keySet())
        {
            hashmap.replace(s,pourcentage);
        }
    }
    
    private void removeEntry(HashMap<String,Float> hashmap,String key)
    {
        if(hashmap == null && !hashmap.containsKey(key))
            return;
        
        changeValue(hashmap, key, 0f);
        hashmap.remove(key);
    }
    
    /**
     * 
     * @param hashmap
     * @param key
     * @param valeur la valeur que va prendre l'entree key
     */
    private void changeValue(HashMap<String,Float> hashmap,String key,float valeur)
    {
        float difference;
                
        if(hashmap == null && !hashmap.containsKey(key))
            return;
        
        difference = hashmap.get(key) - valeur;
        valeur /= hashmap.size() - 1;
        
        hashmap.replace(key,valeur);
        
        for(String s : hashmap.keySet())
        {
            float tampon = hashmap.get(s);
            
            if(!s.equals(key))
                hashmap.replace(key, tampon + difference);
        }
    }
}