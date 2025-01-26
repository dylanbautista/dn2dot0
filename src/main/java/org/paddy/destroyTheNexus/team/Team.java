package org.paddy.destroyTheNexus.team;

import java.util.ArrayList;
import java.util.List;

public class Team {

    //Private attributes
    private List<TeamPlayer> players = new ArrayList<>();
    private TeamColor color;

    public Team(TeamColor color) {
        this.color = color;
    }

    //Getters


    //Setters
    public void addPlayer(TeamPlayer player) {
        players.add(player);
    }

    public void removePlayer(TeamPlayer player) {
        players.remove(player);
    }
}
