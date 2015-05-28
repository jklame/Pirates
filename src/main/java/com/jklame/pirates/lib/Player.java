package com.jklame.pirates.lib;

public class Player
{
    private final int             playerId;
    private final PlayerArchetype playerArchetype;
    private final int             playerNumber;

    public Player(final int playerId, final PlayerArchetype playerArchetype, final int playerNumber)
    {
        super();
        this.playerId = playerId;
        this.playerArchetype = playerArchetype;
        this.playerNumber = playerNumber;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public PlayerArchetype getPlayerArchetype()
    {
        return playerArchetype;
    }

    public int getPlayerNumber()
    {
        return playerNumber;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((playerArchetype == null) ? 0 : playerArchetype.hashCode());
        result = (prime * result) + playerId;
        result = (prime * result) + playerNumber;
        return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(obj == null)
        {
            return false;
        }
        if(getClass() != obj.getClass())
        {
            return false;
        }
        final Player other = (Player) obj;
        if(playerArchetype == null)
        {
            if(other.playerArchetype != null)
            {
                return false;
            }
        }
        else
        {
            final String playerArchetypeClass = playerArchetype.getClass().getName();
            final String otherPlayerArchetypeClass = other.playerArchetype.getClass().getName();
            if(!playerArchetypeClass.equals(otherPlayerArchetypeClass))
            {
                return false;
            }
        }
        if(playerId != other.playerId)
        {
            return false;
        }
        if(playerNumber != other.playerNumber)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String.format("<P%s, %s, N=%s>", playerId, playerArchetype, playerNumber);
    }
    
    

}
