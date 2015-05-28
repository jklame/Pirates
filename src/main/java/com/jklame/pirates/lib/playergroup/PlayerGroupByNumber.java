package com.jklame.pirates.lib.playergroup;

import com.jklame.pirates.lib.NumberSet;
import com.jklame.pirates.lib.Player;
import com.jklame.pirates.lib.PlayerGroup;

/**
 * This represents any group of players specified explicitly by playerNumber
 * Membership in this group does not depend on the speaker
 * 
 * @author jlame
 */
public class PlayerGroupByNumber implements PlayerGroup
{
    private final NumberSet playerNumbers;
    
    public PlayerGroupByNumber(final NumberSet playerNumbers)
    {
        this.playerNumbers = playerNumbers;
    }

    @Override
    public PlayerGroupByNumber complement()
    {
        return new PlayerGroupByNumber(playerNumbers.getComplement());
    }

    @Override
    public boolean containsPlayer(final Player speaker, final Player targetPlayer)
    {
        return playerNumbers.contains(targetPlayer.getPlayerNumber());
    }
    
    public NumberSet getPlayerNumbers()
    {
        return playerNumbers;
    }

    @Override
    public boolean memberShipDependsOnSpeaker()
    {
        return false;
    }

}
