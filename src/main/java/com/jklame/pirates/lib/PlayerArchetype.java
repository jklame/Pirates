package com.jklame.pirates.lib;

import java.util.List;

/**
 * A player's archetype determines whether or not that player may tell the truth at a given time.
 *  
 * @author jlame
 */

public interface PlayerArchetype
{
    /**
     * The truth of a players next statement must depend only on the player's archetype, 
     * the list of players, and the collection of previous statements.  
     * If nextTruthValue() returns null, the truth of the player's next statement is not restricted in any way.
     * 
     * @param statementResultsSoFar 
     * 
     * @return true, false, or null
     */
    public Boolean nextTruthValue(final List<StatementResult> statementResultsSoFar);
    
    @Override
    public String  toString();
}
