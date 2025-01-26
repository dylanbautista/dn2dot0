package org.paddy.destroyTheNexus;

import org.bukkit.Location;
import org.paddy.destroyTheNexus.team.Team;
import org.paddy.destroyTheNexus.team.TeamColor;

/**
 *
 * GeneralController is responsible for controlling all aspects of the game, from starting or stopping the game
 * to managing the persistent data. In other words, GeneralController is the Domain Controller of the system.
 *
 * */
public class GeneralController {


    private static GeneralController instance;

    /**
     *
     * Number of players of each team.
     *
     * */
    private int[] teamPlayers = new int[4];

    private void getJoinSigns() {

    }

    public static GeneralController getInstance() {
        if (instance == null) {
            instance = new GeneralController();
        }
        return instance;
    }

    public int getNumPlayers(TeamColor team) {
        return teamPlayers[team.ordinal()];
    }
}
