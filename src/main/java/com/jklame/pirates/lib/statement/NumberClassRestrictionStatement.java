package com.jklame.pirates.lib.statement;

import java.util.List;

import com.jklame.pirates.lib.NumberSet;
import com.jklame.pirates.lib.Player;
import com.jklame.pirates.lib.PlayerGroup;
import com.jklame.pirates.lib.StatementArchetype;
import com.jklame.pirates.lib.StatementResult;

/**
 * These are statements of the form PlayerGroup's numbers are members of NumberSet
 * 
 * Examples:
 * I have an even number.
 * Player Three has a number greater than 23.
 * Everyone else has a prime number.
 * Every even player number is two times a prime number.
 * Every spy has a two digit number whose digits differ by 3.
 * 
 * Not Examples:
 * Player Three's number is less than my number.
 * No two player numbers are the same.
 * At least one player has a two digit number whose digits differ by 3.
 * 
 * @author jlame
 *
 */
public class NumberClassRestrictionStatement implements StatementArchetype
{
    private final PlayerGroup playerGroup;
    private final NumberSet numberSet;
    
    public NumberClassRestrictionStatement(final PlayerGroup playerGroup, final NumberSet numberSet)
    {
        this.playerGroup = playerGroup;
        this.numberSet = numberSet;
    }

    @Override
    public boolean evaluate(final int speakerId, final List<Player> players, final List<StatementResult> priorResults)
    {
        Player speaker = null;
        if (playerGroup.memberShipDependsOnSpeaker())
        {
            for (final Player player : players)
            {
                if (player.getPlayerId() == speakerId)
                {
                    speaker = player;
                    break;
                }
            }
            if (speaker == null)
            {
                throw new IllegalArgumentException(String.format("List of players does not contain speaker %1$s", speakerId));
            }
        }
        boolean result = true;   // will be vacuously true if the PlayerGroup is empty
        for (final Player player : players)
        {
            if (playerGroup.containsPlayer(speaker, player))  // speaker will only be null if membership does not depend on the speaker
            {
                if (!numberSet.contains(player.getPlayerNumber()))  // if any member of the playerGroup has a number not in the numberSet, then we fail
                {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

}
