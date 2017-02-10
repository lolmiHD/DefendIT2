package me.stonygames.Defend.Listeners;

import me.stonygames.Defend.Main.Main;
import me.stonygames.Defend.Methoden.GameState_Methoden;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by chris on 02.02.2017.
 */
public class ItemsListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if(!Main.main.alive.contains(e.getPlayer())) {

            e.setCancelled(true);
            return;
        }
        if(Main.main.state != GameState_Methoden.INGAME) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e) {
        if(!Main.main.alive.contains(e.getPlayer())) {

            e.setCancelled(true);
            return;
        }
        if(Main.main.state != GameState_Methoden.INGAME) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void entityDamage(EntityDamageEvent e) {
        if(!Main.main.alive.contains(e.getEntity())) {
            e.setCancelled(true);
            return;
        }
        if(Main.main.state != GameState_Methoden.INGAME) {
            e.setCancelled(true);
        } else {
            e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK.getId());
        }
    }

    @EventHandler
    public void onDmg(EntityDamageByEntityEvent e) {
        if(!Main.main.alive.contains(e.getEntity()) || Main.main.alive.contains(e.getDamager())) {
            e.setCancelled(true);
        }
        if (Main.main.state == GameState_Methoden.INGAME) {

            if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {

                Main.main.lastdmg.put((Player) e.getEntity(), (Player) e.getDamager());
            }
        }
    }
}
