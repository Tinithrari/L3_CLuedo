/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author Tinithrari
 */
public class JoueurReseauClient extends JoueurHumain{
    
    private Socket socket;
    
    public JoueurReseauClient(String nom, Socket socket)
    {
        super(nom);
        this.socket = socket;
    }

    @Override
    public String commande() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Carte montrerCarte(Carte lieu, Carte arme, Carte suspect) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
