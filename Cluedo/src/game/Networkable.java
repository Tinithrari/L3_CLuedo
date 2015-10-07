/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.IOException;

/**
 *
 * @author Tinithrari
 */
public interface Networkable {
    public void send(String message) throws IOException;
    public String receive() throws IOException;
}
