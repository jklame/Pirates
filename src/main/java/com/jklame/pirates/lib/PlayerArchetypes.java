package com.jklame.pirates.lib;

import com.jklame.pirates.lib.player.Pirate;
import com.jklame.pirates.lib.player.Saint;
import com.jklame.pirates.lib.player.Spy;

public final class PlayerArchetypes
{
    private PlayerArchetypes()
    {
        // do nothing
    }
    
    public static final PlayerArchetype PIRATE = new Pirate();
    public static final PlayerArchetype SAINT = new Saint();
    public static final PlayerArchetype SPY = new Spy();
}
