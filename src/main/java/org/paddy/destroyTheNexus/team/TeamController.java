package org.paddy.destroyTheNexus.team;

public class TeamController {

    Team[] teams = new Team[4];

    private static TeamController instance;

    private TeamController() {

        //Create team instances with different colors.
        teams[0] = new Team(Team.TeamColor.BLUE);
        teams[1] = new Team(Team.TeamColor.RED);
        teams[2] = new Team(Team.TeamColor.YELLOW);
        teams[3] = new Team(Team.TeamColor.GREEN);


    }

    public static TeamController getInstance() {
        if (instance == null) {
            instance = new TeamController();
        }
        return instance;
    }

    

}
