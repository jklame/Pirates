package com.jklame.pirates.lib.playergroup;

import com.jklame.pirates.lib.Player;
import com.jklame.pirates.lib.PlayerGroup;

public class Self implements PlayerGroup
{
    @Override
    public PlayerGroup complement()
    {
        return new NotSelf();
    }

    @Override
    public boolean containsPlayer(final Player speaker, final Player targetPlayer)
    {
        return speaker.getPlayerId() != targetPlayer.getPlayerId();
    }

    @Override
    public boolean memberShipDependsOnSpeaker()
    {
        return true;
    }

}
