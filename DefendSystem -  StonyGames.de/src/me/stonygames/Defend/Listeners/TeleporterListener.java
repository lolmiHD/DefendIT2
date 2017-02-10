package me.stonygames.Defend.Listeners;

import me.stonygames.Defend.Main.Main;
import me.stonygames.Defend.Methoden.ItemStack_Methoden;
import me.stonygames.Defend.Methoden.Strings_Methoden;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Created by chris on 03.02.2017.
 */
public class TeleporterListener implements Listener {

    Strings_Methoden strings_methoden = new Strings_Methoden();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getClickedInventory() != null && e.getCurrentItem() !=null) {
            if(!Main.main.alive.contains(p)) {
                e.setCancelled(true);
                if (e.getClickedInventory().getTitle().equals("§6Teleporter")) {
                    String playername = e.getCurrentItem().getItemMeta().getDisplayName();
                    if(Main.main.alive.contains(Bukkit.getPlayer(playername))) {

                        Player tar = Bukkit.getPlayer(playername);
                        p.teleport(tar);
                        p.sendMessage(strings_methoden.prefix + "Du befindest dich jetzt bei §a" + tar.getName());

                    } else {
                        p.sendMessage(strings_methoden.prefix + "Dieser Spieler lebt nicht mehr");
                    }
                }
            }
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if(!Main.main.alive.contains(p)) {
            if(p.getItemInHand().getType() == Material.COMPASS) {
                int lenght = (Main.main.alive.size() / 9) +1;

                Inventory inv = Bukkit.createInventory(null, 9*lenght, "§6Teleporter");
                for (Player alive: Main.main.alive) {
                    ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                    SkullMeta skm = (SkullMeta) head.getItemMeta();
                    skm.setOwner(alive.getName());
                    skm.setDisplayName("§6" + alive.getName());
                    head.setItemMeta(skm);
                    inv.addItem(head);
                }
                p.openInventory(inv);
            }
        }
    }
}
