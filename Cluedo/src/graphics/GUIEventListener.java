/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import graphics.GUIElement.GUIEvent;

/**
 *
 * @author Tinithrari
 */
public interface GUIEventListener {
    public void actionPerformed(GUIEvent e, Object source);
}
