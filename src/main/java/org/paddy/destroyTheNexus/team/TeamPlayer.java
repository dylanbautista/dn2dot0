package org.paddy.destroyTheNexus.team;

import org.bukkit.entity.Player;

public class TeamPlayer {
    private String name;
    private Team team;
    private Player player;

    public TeamPlayer(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

}
