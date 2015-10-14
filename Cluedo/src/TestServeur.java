
import game.Jeu;
import game.JoueurHumain;
import game.ServeurReg;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tinithrari
 */
public class TestServeur {
    public static void main(String[] args)
    {
        try {
            ServeurReg server = new ServeurReg(12345, 1, true);
            server.run();
            Jeu jeu = new Jeu(server.getClients());
            jeu.getJoueurs().get(0).voirCartes();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
