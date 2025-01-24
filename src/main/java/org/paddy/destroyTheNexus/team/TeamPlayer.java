package org.paddy.destroyTheNexus.team;

public class TeamPlayer {
    private String name;
    private Team team;

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
