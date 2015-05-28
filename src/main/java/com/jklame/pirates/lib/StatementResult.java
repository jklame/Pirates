package com.jklame.pirates.lib;

public class StatementResult
{
    private final int     statementId;
    private final int     playerId;
    private final boolean value;

    public StatementResult(final int statementId, final int playerId, final boolean value)
    {
        super();
        this.statementId = statementId;
        this.playerId = playerId;
        this.value = value;
    }

    public int getStatementId()
    {
        return statementId;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public boolean isValue()
    {
        return value;
    }

}
