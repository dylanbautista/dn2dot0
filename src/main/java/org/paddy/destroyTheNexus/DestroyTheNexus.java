package org.paddy.destroyTheNexus;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;

public final class DestroyTheNexus extends JavaPlugin {

    private class JoinListener implements Listener {
        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();
            player.sendMessage(ChatColor.GOLD + "You are now destroying the Nexus, " + player.getName());
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Starting DestroyTheNexus...");
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new RegenerableItems(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Stopping DestroyTheNexus...");
    }
}
