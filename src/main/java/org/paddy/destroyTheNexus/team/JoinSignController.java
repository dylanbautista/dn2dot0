package org.paddy.destroyTheNexus.team;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;
import org.bukkit.event.Listener;
import org.paddy.destroyTheNexus.exceptions.JoinSignIsAlreadyConfiguredException;

import java.util.BitSet;

public class JoinSignController implements Listener {

    private static JoinSignController instance;
    private Sign[] join_signs = new Sign[4];
    private BitSet join_signs_mask = new BitSet(4);

    private JoinSignController() {

    }

    public static JoinSignController getInstance() {
        if (instance == null) {
            instance = new JoinSignController();
        }
        return instance;
    }

    public void setJoinSign(Sign sign, TeamColor team) throws JoinSignIsAlreadyConfiguredException {
        if (join_signs_mask.get(team.ordinal())) throw new JoinSignIsAlreadyConfiguredException(team.name());

        //Save Sign instance
        join_signs[team.ordinal()] = sign;

        SignSide side = sign.getSide(Side.FRONT);
        ChatColor teamChatCol;
        switch (team) {
            case BLUE:
                teamChatCol = ChatColor.BLUE;
                break;
            case YELLOW:
                teamChatCol = ChatColor.YELLOW;
                break;
            case GREEN:
                teamChatCol = ChatColor.GREEN;
                break;
            case RED:
                teamChatCol = ChatColor.RED;
                break;
            default:
                teamChatCol = ChatColor.WHITE;
        }

        side.setLine(0, teamChatCol + "" + ChatColor.BOLD + team.name());

    }


    //Event handler
}
