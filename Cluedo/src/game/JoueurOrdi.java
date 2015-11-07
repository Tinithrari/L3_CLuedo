package game;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * Classe permettant de creer un joueur ordi
 * @author Tinithrari
 */
public class JoueurOrdi extends Joueur {
	
        protected String[] joueurs;
    
	private HashMap<String, Float> croyanceArme;
	private HashMap<String, Float> croyanceLieu;
	private HashMap<String, Float> croyanceSuspect;
	
	private String buffer;
        
        private LinkedList<String> armes;
        private LinkedList<String> suspects;
        private LinkedList<String> lieux;
        
        private String oldSuspect;
        private String oldArme;
        private String oldLieu;
        
        private boolean attentionRequisePourShow;
	
	private HashMap<Integer, Queue<String>> memoireSuggestion;
	
    /**
     * permet de creer un joueur ordi
     * @param nom le nom du joueur ordi
     */
    public JoueurOrdi(String nom) 
    {
        super(nom);
        croyanceArme = new HashMap<String, Float>();
        croyanceLieu = new HashMap<String, Float>();
        croyanceSuspect = new HashMap<String, Float>();
        memoireSuggestion = new HashMap<Integer, Queue<String>>();
        attentionRequisePourShow = false;
        buffer = "";
       // Exemple usage HashMap : memoireSuggestion.put(1, new LinkedList<String>());
    }

    /**
     * permet d'envoyer un message
     * @param message le nom du message
     * @throws IOException
     */
    public void send(String message) throws IOException {
    	
        String[] splitted = message.split(" ");
        
        if (splitted[0].equals("start"))
        {
        	commencer(splitted);
        }
        if (splitted[0].equals("move") && Integer.parseInt(splitted[2]) != this.getNum_joueur())
        {
            String requete = splitted[3] + " " + splitted[4] + " " + splitted[5];
            
            Queue<String> memJoueur = memoireSuggestion.get( Integer.parseInt(splitted[2]) );
            
            if (! memJoueur.isEmpty())
            {
                
                // Premier cas : Deux des trois cartes sont connues par l'IA
                if ( (main.contains(splitted[3]) && main.contains(splitted[4]))
                        || (main.contains(splitted[3]) && main.contains(splitted[5]))
                        || (main.contains(splitted[4]) && main.contains(splitted[5])))
                    attentionRequisePourShow = true;
                else
                {
                    boolean un = false, deux = false, trois = false;
                    // Second cas : Carte inconnu
                    // Vérifie si la requête n'est pas identique à l'une des précédentes et si elle contient au moins une des trois cartes
                    for (String requetePrecedente : memJoueur)
                    if (! (requetePrecedente.contains(splitted[3]) && requetePrecedente.contains(splitted[4]) && requetePrecedente.contains(splitted[5])) )
                        {
                            if (requetePrecedente.contains(splitted[3]) && croyanceSuspect.containsKey(splitted[3]) && !un)
                            {
                            	float value = croyanceSuspect.get(splitted[3]) + (1f / (float)croyanceSuspect.size());
                                changeValue(croyanceSuspect, splitted[3], (value > 1f ? 1f : value) );
                                un = true;
                            }
                            if (requetePrecedente.contains(splitted[4])  && croyanceArme.containsKey(splitted[4]) && !deux)
                            {
                            	float value = croyanceArme.get(splitted[4]) + (1f / (float)croyanceArme.size());
                                changeValue(croyanceArme, splitted[4], (value > 1f ? 1f : value) );
                                deux = true;
                            }
                            if (! requetePrecedente.contains(splitted[5]) && croyanceLieu.containsKey(splitted[5]) && !trois)
                            {
                            	float value = croyanceLieu.get(splitted[5]) + (1f / (float)croyanceLieu.size());
                                changeValue(croyanceLieu, splitted[5], (value > 1f ? 1f : value) );
                                trois = true;
                            }
                            if (un && deux && trois)
                                return;
                        }
                }
            }
            memJoueur.offer(requete);
        }
        if (splitted[0].equals("info") && splitted[1].equals("respond"))
        {
        	if (Integer.parseInt(splitted[2]) == this.getNum_joueur())
        		return;
        	
                String carte = splitted[3];
            
                if (croyanceArme.containsKey(carte))
                    removeEntry(croyanceArme, carte);
                else if (croyanceLieu.containsKey(carte))
                    removeEntry(croyanceLieu, carte);
                else
                    removeEntry(croyanceSuspect, carte);
                
                if (! oldArme.equals(splitted[3]) )
                {
                	float value = croyanceArme.get(oldArme) + (1f / (float)croyanceArme.size());
                	changeValue(croyanceArme, oldArme, (value > 1f ? 1f : value) );
                }
                if (! oldLieu.equals(splitted[3]) )
                {
                	float value = croyanceLieu.get(oldLieu) + (1f / (float)croyanceLieu.size());
                	changeValue(croyanceLieu, oldLieu, (value > 1f ? 1f : value) );
                }
                if (! oldSuspect.equals(splitted[3]) )
                {
                	float value = croyanceSuspect.get(oldSuspect) + (1f / (float)croyanceSuspect.size());
                	changeValue(croyanceSuspect, oldSuspect, (value > 1f ? 1f : value) );
                }
        }
        if (splitted[0].equals("info") && splitted[1].equals("show") && attentionRequisePourShow)
        {
            LinkedList<String> l = (LinkedList<String>) memoireSuggestion.get(Integer.parseInt(splitted[3]));
            String[] requete_splitted = l.getLast().split(" ");
            
            if (main.contains(requete_splitted[1]) && main.contains(requete_splitted[2]))
                removeEntry(croyanceSuspect, requete_splitted[0]);
            if (main.contains(requete_splitted[0]) && main.contains(requete_splitted[2]))
                removeEntry(croyanceArme, requete_splitted[1]);
            else
                removeEntry(croyanceLieu, requete_splitted[2]);
            attentionRequisePourShow = false;
        }
        if (splitted[0].equals("play"))
        {
        	// On recupere le max
        	float maxArme = Collections.max(croyanceArme.values());
        	float maxSuspect = Collections.max(croyanceSuspect.values());
        	float maxLieu = Collections.max(croyanceLieu.values());
        	
        	// On recupere la cle associee au max
        	String arme = getKeyFromValue(croyanceArme, maxArme);
        	String suspect = getKeyFromValue(croyanceSuspect, maxSuspect);
        	String lieu = getKeyFromValue(croyanceLieu, maxLieu);
        	
        	String action = "move ";
        	
        	// Si les croyances sont sup a 75%, accuse, sinon suggestion
        	if(maxArme >= 0.80 && maxSuspect >= 0.80 && maxLieu >= 0.80)
        		action += "accuse ";
        	else
        		action += "suggest ";
        	
        	action += suspect + " " + arme + " " + lieu;
        	
        	// On place l'action dans le buffer
        	buffer = action;
        	
        	oldArme = arme;
        	oldLieu = lieu;
        	oldSuspect = suspect;
        }
        
        if (splitted[0].equals("ask"))
        {
        	Suspect suspectDemande = new Suspect(splitted[1]);
        	Arme armeDemande = new Arme(splitted[2]);
        	Lieu lieuDemande = new Lieu(splitted[3]);
        	
        	// On verifie les cartes que l'on a
        	LinkedList <Carte> possede = new LinkedList();
        	if (main.contains(suspectDemande))
        		possede.add(suspectDemande);
        	if (main.contains(armeDemande))
        		possede.add(armeDemande);
        	if (main.contains(lieuDemande))
        		possede.add(lieuDemande);
        	
        	// On regarde si possede est vide
        	if (possede.isEmpty())
        		buffer = "respond";
        	//Sinon on melange les cartes
        	else
        	{
        		Collections.shuffle(possede);
        		buffer = "respond " + possede.getFirst().toString();
        	}
        }
    }
    
    private String getKeyFromValue(HashMap <String,Float> hashmap, float value)
    {
    	if (hashmap == null && hashmap.containsValue(value))
    		return null;
    	for(String key : hashmap.keySet())
    	{
    		if(hashmap.get(key) == value)
    			return key;
    	}
    	return null;
    }

    @Override
    public String receive() throws IOException {
        String tmp = buffer;
        buffer = null;
        return tmp;
    }

    /**
     * permet de preparer l'IA
     * @param splitted
     */
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
        
        for (int i = 0; i < joueurs.length; i++)
        {
        	memoireSuggestion.put(i, new LinkedList<String>());
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
        
        pourcentage = 1f/hashmap.size();
        
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
        difference /= (hashmap.size() - 1f);
        
        hashmap.replace(key,valeur);
        
        for(String s : hashmap.keySet())
        {
            float tampon = hashmap.get(s);
            
            if(!s.equals(key))
                hashmap.replace(s, tampon + difference);
        }
    }
}