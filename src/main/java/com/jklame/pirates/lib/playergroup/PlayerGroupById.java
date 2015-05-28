package com.jklame.pirates.lib.playergroup;

import com.jklame.pirates.lib.NumberSet;
import com.jklame.pirates.lib.Player;
import com.jklame.pirates.lib.PlayerGroup;

/**
 * This represents any group of players specified explicitly by playerId
 * Membership in this group does not depend on the speaker
 * 
 * @author jlame
 */
public class PlayerGroupById implements PlayerGroup
{
    private final NumberSet playerIds;
    
    public PlayerGroupById(final NumberSet playerIds)
    {
        this.playerIds = playerIds;
    }

    @Override
    public PlayerGroupById complement()
    {
        return new PlayerGroupById(playerIds.getComplement());
    }

    @Override
    public boolean containsPlayer(final Player speaker, final Player targetPlayer)
    {
        return playerIds.contains(targetPlayer.getPlayerId());
    }
    
    public NumberSet getPlayerIds()
    {
        return playerIds;
    }

    @Override
    public boolean memberShipDependsOnSpeaker()
    {
        return false;
    }

}
