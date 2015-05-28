package com.jklame.pirates.lib.playergroup;

import com.jklame.pirates.lib.Player;
import com.jklame.pirates.lib.PlayerGroup;

/**
 * This represents the group of players other than the speaker
 * 
 * @author jlame
 */
public class NotSelf implements PlayerGroup
{
    @Override
    public PlayerGroup complement()
    {
        return new Self();
    }

    @Override
    public boolean containsPlayer(final Player speaker, final Player targetPlayer)
    {
        return speaker.getPlayerId() == targetPlayer.getPlayerId();
    }

    @Override
    public boolean memberShipDependsOnSpeaker()
    {
        return true;
    }
}
