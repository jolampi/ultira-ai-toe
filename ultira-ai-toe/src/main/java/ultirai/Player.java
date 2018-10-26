/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

/**
 * The interface for player classes.
 * 
 * @author Jori Lampi
 */
public interface Player {
    
    /**
     * @param gameState the GameState where the move should be based on
     * @return player's move
     */
    public int move(GameState gameState);
    
}
