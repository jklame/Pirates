package com.jklame.pirates.lib;

/**
 * A Statement represents a specific occurrence of a Player uttering a StatementArchetype at a specific time.
 *
 * @author jlame
 *
 */
public class Statement
{
    private final int                statementNumber;   // starts with 1
    private final Player             player;
    private final StatementArchetype statementArchetype;
    private final String             description;       // TODO: make the description auto-generated (DIFFICULT)

    public Statement(final int statementNumber, final Player player, final StatementArchetype statementArchetype, final String description)
    {
        super();
        this.statementNumber = statementNumber;
        this.player = player;
        this.statementArchetype = statementArchetype;
        this.description = description;
    }

    public int getStatementNumber()
    {
        return statementNumber;
    }

    public Player getPlayer()
    {
        return player;
    }

    public StatementArchetype getStatementArchetype()
    {
        return statementArchetype;
    }

    public String getDescription()
    {
        return description;
    }

}
