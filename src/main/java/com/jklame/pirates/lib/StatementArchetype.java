package com.jklame.pirates.lib;

import java.util.List;

/**
 * Statements can refer to: Player Ids, Player Archetypes, Player Numbers
 * Statements can declare certain player numbers to be members of some class.
 * Statements can declare some combination of player numbers to be members of some class.
 * Statements can order combinations of player numbers.
 * Statements can refer to the truth of previously made statements.
 * 
 * The truth of a statement must be a function of the speaker, the collection of Players (playerId, playerArchetype, playerNumber) and
 * the collection of prior StatementResults (statementId, playerId, value).   It should not depend on previous StatementArchetypes.
 * 
 * A StatementArchetype encapsulates a collection of statements whose truth is always computed in the same way.  For instance,
 * the statement "My number is prime" could be generalized to the archetype: "(collection of players defined by self, id, archetype, speaker number) has
 * a number which is a member of (some number class)".   So another member of the same archetype might be:  
 * "No pirate who has spoken before me has a square number".    
 * 
 * @author jlame
 */
public interface StatementArchetype
{
    public boolean evaluate(final int speakerId, final List<Player> players, final List<StatementResult> priorResults);
}
