package org.paddy.destroyTheNexus;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;



public class RegenerateItems implements Listener {
    private final JavaPlugin plugin;
    private Block block;
    private Material blockType;
    private Player player;
    private BlockBreakEvent event;
    private ItemStack itemHand;
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
    public void onBlockBreak(BlockBreakEvent e) {
        event  = e;
        player = event.getPlayer();

        if(player.getGameMode().equals(GameMode.CREATIVE)) return;

        itemHand = player.getInventory().getItemInMainHand();
        block = event.getBlock();
        blockType = block.getType();

        if(!REGENERATE_BLOCKS.contains(blockType)) return;

        switch (blockType) {
            case DIAMOND_ORE:
                List<Material> DIAMOND_ALLOWED_ITEMS = Arrays.asList(Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE);
                ItemStack diamond = new ItemStack(Material.DIAMOND);
                regenerateOre(20, 8, diamond, DIAMOND_ALLOWED_ITEMS);
                break;
            case GOLD_ORE:
                List<Material> GOLD_ALLOWED_ITEMS = Arrays.asList(Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
                ItemStack gold = new ItemStack(Material.GOLD_ORE);
                regenerateOre(15, 5, gold, GOLD_ALLOWED_ITEMS);
                break;
            case IRON_ORE:
                List<Material> IRON_ALLOWED_ITEMS = Arrays.asList(Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
                ItemStack iron = new ItemStack(Material.IRON_ORE);
                regenerateOre(10, 4, iron, IRON_ALLOWED_ITEMS);
                break;
            case COAL_ORE:
                List<Material> COAL_ALLOWED_ITEMS = Arrays.asList(Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
                ItemStack coal = new ItemStack(Material.COAL);
                regenerateOre(10, 8, coal, COAL_ALLOWED_ITEMS);
                break;
            case EMERALD_ORE:
                List<Material> EMERALD_ALLOWED_ITEMS = Arrays.asList(Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
                ItemStack emerald = new ItemStack(Material.EMERALD);
                regenerateOre(12, 15, emerald, EMERALD_ALLOWED_ITEMS);
                break;
            case REDSTONE_ORE:
                List<Material> REDSTONE_ALLOWED_ITEMS = Arrays.asList(Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
                ItemStack redstone = new ItemStack(Material.REDSTONE, 4);
                regenerateOre(12, 11, redstone, REDSTONE_ALLOWED_ITEMS);
                break;
            case LAPIS_ORE:
                List<Material> LAPIS_ALLOWED_ITEMS = Arrays.asList(Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
                ItemStack lapis = new ItemStack(Material.LAPIS_LAZULI, 8);
                regenerateOre(12, 12, lapis, LAPIS_ALLOWED_ITEMS);
                break;
            case OAK_LOG:
                List<Material> OAK_ALLOWED_ITEMS = Arrays.asList(Material.WOODEN_AXE, Material.STONE_AXE, Material.GOLDEN_AXE, Material.IRON_AXE, Material.DIAMOND_AXE);
                ItemStack oak = new ItemStack(Material.OAK_LOG);
                regenerateBlock(8, 4, oak, OAK_ALLOWED_ITEMS);
                break;
            case MELON:
                List<Material> MELON_ALLOWED_ITEMS = Arrays.asList(Material.WOODEN_AXE, Material.STONE_AXE, Material.GOLDEN_AXE, Material.IRON_AXE, Material.DIAMOND_AXE, Material.WOODEN_SWORD, Material.STONE_SWORD, Material.GOLDEN_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD);
                ItemStack melon = new ItemStack(Material.MELON_SLICE, 5);
                regenerateBlock(5, 2, melon, MELON_ALLOWED_ITEMS);
        }

    }
    private void regenerateOre(int time, int exp, ItemStack givenItem, List<Material> allowedItems) {
        if(!checkBlock(allowedItems)) return;
        event.setDropItems(false);
        event.setExpToDrop(0);
        block.setType(Material.COBBLESTONE); //Que pasa si trec aixo?
        player.giveExp(exp);
        player.getInventory().addItem(givenItem);
        playSound();

        Location blockLocation = block.getLocation();
        Material originalBlockType = blockType;

        new BukkitRunnable() {
            @Override
            public void run() {
                blockLocation.getBlock().setType(Material.COBBLESTONE);
            }
        }.runTask(plugin);

        new BukkitRunnable() {
            @Override
            public void run() {
                Block currentBlock = blockLocation.getBlock();
                currentBlock.setType(originalBlockType);
            }
        }.runTaskLater(plugin, 20L * time);

    }
    private void regenerateBlock(int time, int exp, ItemStack givenItem, List<Material> allowedItems) {
        if(!checkBlock(allowedItems)) return;
        event.setDropItems(false);
        event.setExpToDrop(0);
        player.giveExp(exp);
        player.getInventory().addItem(givenItem);
        playSound();

        Location blockLocation = block.getLocation();
        Material originalBlockType = blockType;

        new BukkitRunnable() {
            @Override
            public void run() {
                Block currentBlock = blockLocation.getBlock();
                currentBlock.setType(originalBlockType);
            }
        }.runTaskLater(plugin, 20L * time);

    }

    private boolean checkBlock(List<Material> allowedItems) {
        boolean check = true;
        if(!allowedItems.contains(itemHand.getType())) {
            player.sendMessage(ChatColor.RED + "You can not break the " + blockType.toString().toLowerCase() + " with " + itemHand.getType().name().toLowerCase() + "!");
            event.setCancelled(true);
            check = false;
        }
        return check;
    }

    private void playSound() {
        event.getPlayer().playSound(
                block.getLocation(),
                Sound.ENTITY_EXPERIENCE_ORB_PICKUP,
                1.0f,
                1.0f
        );
    }
}
