package org.paddy.destroyTheNexus;
import org.bukkit.ChatColor;
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

public class RegenerateItems implements Listener {
    private final JavaPlugin plugin;
    //private Block block;
    //private Material blockType;
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

    public RegenerateItems(JavaPlugin plugin) {
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
            case DIAMOND_ORE:
                regenerateDiamond(event, block, blockType);
                break;
            case GOLD_ORE:
                regenerateGold(event, block, item, blockType);
                break;
            case IRON_ORE:
                playSound(event, block);
                event.setDropItems(false);
                player.giveExp(2);
                player.getInventory().addItem(item);
                event.getBlock().setType(Material.COBBLESTONE);
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
            case OAK_LOG:
                regenerateLog(event, block, item, blockType);
        }
    }


    private void regenerateDiamond(BlockBreakEvent event, Block block, Material blockType) {
        var player = event.getPlayer();
        ItemStack itemHand = player.getInventory().getItemInMainHand();
        List<Material> ALLOWED_ITEMS = Arrays.asList(
                Material.IRON_PICKAXE,
                Material.GOLDEN_PICKAXE,
                Material.DIAMOND_PICKAXE
        );

        if(!ALLOWED_ITEMS.contains(itemHand.getType())){    //mirar el block.ispreferedtool
            player.sendMessage(ChatColor.RED + "You can not break the " + blockType.toString().toLowerCase() + " with " + itemHand.getType().name().toLowerCase()+ "!");
            event.setCancelled(true);
            return;
        }
        event.setDropItems(false);

        event.setExpToDrop(0);
        player.giveExp(2);

        ItemStack item = new ItemStack(Material.DIAMOND);
        player.getInventory().addItem(item);
        block.setType(Material.COBBLESTONE);

        playSound(event, block);

        new BukkitRunnable() {
            @Override
            public void run() {
                block.setType(blockType);
            }
        }.runTaskLater(plugin, 20L * 12);
    }

    private void regenerateGold(BlockBreakEvent event, Block block, ItemStack item, Material blockType) {
        var player = event.getPlayer();
        ItemStack itemHand = player.getInventory().getItemInMainHand();
        List<Material> ALLOWED_ITEMS = Arrays.asList(
                Material.STONE_PICKAXE,
                Material.IRON_PICKAXE,
                Material.DIAMOND_PICKAXE
        );

        if(!ALLOWED_ITEMS.contains(itemHand.getType())){
            player.sendMessage(ChatColor.RED + "You can not break the " + blockType.toString().toLowerCase() + " with " + itemHand.getType().name().toLowerCase()+ "!");
            event.setCancelled(true);
            return;
        }
        event.setDropItems(false);
        event.setExpToDrop(0);
        player.giveExp(4);
        player.getInventory().addItem(item);
        block.setType(Material.COBBLESTONE);

        playSound(event, block);

        new BukkitRunnable() {
            @Override
            public void run() {
                block.setType(blockType);
            }
        }.runTaskLater(plugin, 20L * 12);

    }

    private void regenerateLog(BlockBreakEvent event, Block block, ItemStack item, Material blockType) {
        var player = event.getPlayer();
        ItemStack itemHand = player.getInventory().getItemInMainHand();
        List<Material> ALLOWED_ITEMS = Arrays.asList(
                Material.WOODEN_AXE,
                Material.STONE_AXE,
                Material.IRON_AXE,
                Material.GOLDEN_AXE,
                Material.DIAMOND_AXE
        );
        if(!ALLOWED_ITEMS.contains(itemHand.getType())){
            player.sendMessage(ChatColor.RED + "You can not break the " + blockType.toString().toLowerCase() + "!");
            event.setCancelled(true);
            return;
        }

        event.setDropItems(false);
        event.setExpToDrop(0);
        player.giveExp(2);
        player.getInventory().addItem(item);

        new BukkitRunnable() {
            @Override
            public void run() {
                block.setType(blockType);
            }
        }.runTaskLater(plugin, 20L * 7);

    }


    private static void playSound(BlockBreakEvent event, Block block) {
        event.getPlayer().playSound(
                block.getLocation(),
                Sound.ENTITY_EXPERIENCE_ORB_PICKUP,
                1.0f,
                1.0f
        );
    }
}
