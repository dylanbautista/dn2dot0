package org.paddy.destroyTheNexus.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.paddy.destroyTheNexus.team.JoinSignController;
import org.paddy.destroyTheNexus.team.TeamColor;

public class GetJoinSignsCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.sendMessage(ChatColor.GOLD + "You are now getting some join signs!");

            Block target_block = player.getTargetBlockExact(5);

            if (target_block != null && target_block.getType() == Material.OAK_WALL_SIGN || target_block.getType() == Material.OAK_SIGN) {
                Sign sign = (Sign) target_block.getState();
                player.sendMessage(args[0]);
                JoinSignController joinSignController = JoinSignController.getInstance();
                switch (args[0]) {
                    case "blue":
                        joinSignController.setJoinSign(sign, TeamColor.BLUE);
                        break;
                    case "red":
                        joinSignController.setJoinSign(sign, TeamColor.RED);
                        break;
                    case "yellow":
                        joinSignController.setJoinSign(sign, TeamColor.YELLOW);
                        break;
                    case "green":
                        joinSignController.setJoinSign(sign, TeamColor.GREEN);
                        break;
                    default:
                        player.sendMessage(ChatColor.RED + "Invalid argument. Please use [blue, red, yellow, green].]");
                        return false;
                }
            } else {
                player.sendMessage(ChatColor.RED + "You are not targeting a sign!");
            }
        }
        return true;
    }
}
