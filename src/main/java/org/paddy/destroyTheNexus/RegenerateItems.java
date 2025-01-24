package org.paddy.destroyTheNexus;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
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
    private Block block;
    private Material blockType;
    private Player player;
    private ItemStack item;
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
        block = event.getBlock();
        blockType = block.getType();
        player = event.getPlayer();
        itemHand = player.getInventory().getItemInMainHand();

        if(!REGENERATE_BLOCKS.contains(blockType)) return;

        item = new ItemStack(blockType);

        switch (blockType) {
            case DIAMOND_ORE:
                List<Material> DIAMOND_ALLOWED_ITEMS = Arrays.asList(Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE);
                ItemStack diamond = new ItemStack(Material.DIAMOND);
                regenerateOre(15, 8, diamond, DIAMOND_ALLOWED_ITEMS);
                break;
            case GOLD_ORE:
                List<Material> GOLD_ALLOWED_ITEMS = Arrays.asList(Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
                ItemStack gold = new ItemStack(Material.GOLD_ORE);
                regenerateOre(12, 5, gold, GOLD_ALLOWED_ITEMS);
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
                regenerateOre(10, 15, emerald, EMERALD_ALLOWED_ITEMS);
                break;

            case OAK_LOG:
                break;
        }
    }

    //seconds//exp//brokenItem//ItemUser//AllowedItems          boolean true -> if is the same //false if the given item is an item and not a material
    private void regenerateOre(int time, int exp, ItemStack givenItem, List<Material> allowedItems) {
        if(!checkBlock(allowedItems)) return;
        event.setDropItems(false);
        event.setExpToDrop(0);
        player.giveExp(exp);
        player.getInventory().addItem(givenItem);
        block.setType(Material.COBBLESTONE);
        playSound();

        new BukkitRunnable() {
            @Override
            public void run() {
                block.setType(blockType);
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
