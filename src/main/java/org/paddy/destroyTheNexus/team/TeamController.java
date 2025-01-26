package org.paddy.destroyTheNexus.team;

import java.util.List;

public class TeamController {

    Team[] teams = new Team[4];

    private static TeamController instance;

    private TeamController() {

        //Create team instances with different colors.
        teams[0] = new Team(TeamColor.BLUE);
        teams[1] = new Team(TeamColor.RED);
        teams[2] = new Team(TeamColor.YELLOW);
        teams[3] = new Team(TeamColor.GREEN);
    }

    public static TeamController getInstance() {
        if (instance == null) {
            instance = new TeamController();
        }
        return instance;
    }

    //Setters



    //Getters

    /**
     *
     * Get a list of the TeamPlayer instances that compose the id team.
     *
     * */
    public void getPlayers(int teamId) {

    }

    /**
     *
     * Get a list of the TeamPlayer instances that compose the color team.
     * Since each team have exclusive colors, the team color can be used to identify them.
     * The method returns the list of players of the color team.
     *
     * @param color The color that identifies the team.
     *
    */
    public List<TeamPlayer> getPlayers(TeamColor color) {
        return null;
    }

}
