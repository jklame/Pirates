package com.jklame.pirates.lib.player;

import java.util.List;

import com.jklame.pirates.lib.PlayerArchetype;
import com.jklame.pirates.lib.StatementResult;

public class Pirate implements PlayerArchetype
{

    @Override
    public Boolean nextTruthValue(final List<StatementResult> statementResultsSoFar)
    {
        return false;
    }

    @Override
    public String toString()
    {
        return "Pirate";
    }
}
