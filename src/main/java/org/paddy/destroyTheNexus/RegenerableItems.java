package org.paddy.destroyTheNexus;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Sound;

public class RegenerableItems implements Listener {
    private final JavaPlugin plugin;
    private static final List<Material> REGENERATE_BLOCKS = Arrays.asList(
            Material.DIAMOND_ORE,
            Material.GOLD_ORE,
            Material.IRON_ORE,
            Material.COAL_ORE,
            Material.EMERALD_ORE,
            Material.REDSTONE_ORE,
            Material.LAPIS_ORE,
            Material.OAK_LOG,
            Material.MELON

    );
    public RegenerableItems(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material blockType = block.getType();

        if(!REGENERATE_BLOCKS.contains(blockType)) return;

        var player = event.getPlayer();
        ItemStack item = new ItemStack(blockType);

        switch (blockType) {
            case IRON_ORE:
                playSound(event, block);
                event.setDropItems(false);
                player.giveExp(2);
                player.getInventory().addItem(item);
                block.setType(Material.COBBLESTONE);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        block.setType(blockType); // Regenerar el bloque
                    }
                }.runTaskLater(plugin, 20L * 12);
                break;

            case COAL_ORE:
                playSound(event, block);
                event.setDropItems(false);
                event.setExpToDrop(0);
                player.giveExp(4);
                item = new ItemStack(Material.COAL);
                player.getInventory().addItem(item);
                block.setType(Material.COBBLESTONE);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        block.setType(blockType); // Regenerar el bloque
                    }
                }.runTaskLater(plugin, 20L * 10);
                break;

            case EMERALD_ORE:
                playSound(event, block);
                event.setDropItems(false);
                player.giveExp(10);
                item = new ItemStack(Material.EMERALD);
                player.getInventory().addItem(item);
                block.setType(Material.COBBLESTONE);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        block.setType(blockType);
                    }
                }.runTaskLater(plugin, 20L * 15);
                break;

            case GOLD_ORE:
                playSound(event, block);
                event.setDropItems(false);
                player.giveExp(7);
                player.getInventory().addItem(item);
                block.setType(Material.COBBLESTONE);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        block.setType(blockType); // Regenerar el bloque
                    }
                }.runTaskLater(plugin, 20L * 12);


        }
    }

    private static void playSound(BlockBreakEvent event, Block block) {
        event.getPlayer().playSound(
                block.getLocation(),                     // Ubicación donde se reproduce el sonido
                Sound.ENTITY_EXPERIENCE_ORB_PICKUP,     // Sonido de experiencia
                1.0f,                                   // Volumen
                1.0f                                    // Pitch (tono)
        );
    }
}
