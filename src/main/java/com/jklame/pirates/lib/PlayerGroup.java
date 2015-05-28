package com.jklame.pirates.lib;


/**
 * This interface is used by most Statement archetypes.  It represents any concrete way
 * of one player (the current player or speaker) specifying another player or group of players.  
 * Specific examples include:
 * 
 * I/Me
 * Except me
 * Speaker #2
 * All Pirates
 * Players with even numbers
 * Players with a greater number than me.
 * 
 * @author jlame
 *
 */
public interface PlayerGroup
{
    public PlayerGroup complement();

    /**
     * Note that the "contents" of a PlayerGroup are a function of the speaker.  For instance the set of
     * "Players whose number is divisible by my number" is different depending on who "I" am.
     * 
     * @param speaker
     * @param targetPlayer
     * @return true if the targetPlayer would be a member of this PlayerGroup from the point of view of the speaker.
     */
    public boolean containsPlayer(final Player speaker, final Player targetPlayer);
    
    public boolean memberShipDependsOnSpeaker();
    
}
