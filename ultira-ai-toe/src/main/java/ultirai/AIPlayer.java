/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Random;
import structure.List;

/**
 *
 * @author Jori Lampi
 */
public class AIPlayer implements Player {
    
    private final Random random;

    public AIPlayer(Random random) {
        this.random = random;
    }

    @Override
    public int move(GameState gameState) {
        List<Integer> validMoves = gameState.getValidMoves();
        return validMoves.get(random.nextInt(validMoves.size()));
    }
    
}
