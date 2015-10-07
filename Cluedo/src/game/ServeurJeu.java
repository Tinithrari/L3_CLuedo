package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author Tinithrari
 */
public class ServeurJeu extends Thread{
    
    private ServerSocket sSocket;
    private LinkedList<JoueurReseauServeur> clients;
    private int max_connection;
    private int nb_connection;
    
    public ServeurJeu(int port, int max_connection) throws IOException
    {
        this.sSocket = new ServerSocket(port);
        this.sSocket.setSoTimeout(250);
        this.clients = new LinkedList<JoueurReseauServeur>();
        this.max_connection = max_connection;
        this.nb_connection = 0;
    }

    @Override
    public void run() 
    {    
        while (true)
        {
            if (nb_connection < max_connection)
            {
                try {
                    Socket s = sSocket.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String nom = reader.readLine();
                    
                    JoueurReseauServeur j = new JoueurReseauServeur(s, nom);
                    
                    clients.add(j);
                    this.setNb_connection(this.getNb_connection() + 1);
                } catch (IOException ex) {
                    //TODO
                }
            }
        }
    }

    public synchronized LinkedList<JoueurReseauServeur> getClients() {
        return clients;
    }

    public synchronized void setClients(LinkedList<JoueurReseauServeur> clients) {
        this.clients = clients;
    }

    public synchronized int getMax_connection() {
        return max_connection;
    }

    public synchronized void setMax_connection(int max_connection) {
        this.max_connection = max_connection;
    }

    public synchronized int getNb_connection() {
        return nb_connection;
    }

    public synchronized void setNb_connection(int nb_connection) {
        this.nb_connection = nb_connection;
    }
}
