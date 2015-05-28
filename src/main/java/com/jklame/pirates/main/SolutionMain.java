package com.jklame.pirates.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jklame.pirates.lib.NumberSet;
import com.jklame.pirates.lib.PermutationIterator;
import com.jklame.pirates.lib.Player;
import com.jklame.pirates.lib.PlayerArchetype;
import com.jklame.pirates.lib.PlayerArchetypes;

/**
 * This is where the algorithm for determining the solutions to a specific collections of preconditions and statements is refined
 * 
 * @author jlame
 *
 */
public final class SolutionMain
{
    private SolutionMain()
    {
        // do nothing
    }
    
    /**
     * Preconditions
     */
    private static final int playerCount = 3;
    private static final List<PlayerArchetype> playerArchetypes = Arrays.asList(PlayerArchetypes.PIRATE, PlayerArchetypes.SAINT, PlayerArchetypes.SPY);
    private static final NumberSet baseNumberSet = new NumberSet(1, 99, (int i) -> true);
    
    private static final List<Map<Integer, Player>> solutionList = new ArrayList<>();
    
    /**
     * This is a brute-force approach.  We loop through all possible archetype and number assignments matching the preconditions 
     * and remember which ones are possible according to the collections of statements which must be satisfied.   A "Solution"
     * here is simply a map of playerId to Player.
     * 
     * @param args
     */
    public static void main(final String[] args)
    {
        final NumberSet testNumberSet = new NumberSet(1, 9, (int i) -> { final int s = (int)Math.floor(Math.sqrt(i)); return s * s == i - 1; });
        final Iterator<List<Integer>> numberIterator = testNumberSet.iterator(3);
        while (numberIterator.hasNext())
        {
            final Iterator<List<PlayerArchetype>> playerIterator = new PermutationIterator<PlayerArchetype>(playerArchetypes);
            final List<Integer> nextNumberList = numberIterator.next();
            while (playerIterator.hasNext())
            {
                System.out.println(String.format("%1$s : %2$s", nextNumberList, playerIterator.next()));
            }
        }
        
    }
}
