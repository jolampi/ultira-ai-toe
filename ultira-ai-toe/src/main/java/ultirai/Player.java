/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

/**
 *
 * @author Jori Lampi
 */
public interface Player {
    
    public int move(GameState gameState);
    
    public void win();
    
    public void lose();
    
}
