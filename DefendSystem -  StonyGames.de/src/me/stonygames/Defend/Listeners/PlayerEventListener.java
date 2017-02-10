package me.stonygames.Defend.Listeners;

import me.stonygames.Defend.Main.Main;
import me.stonygames.Defend.Methoden.GameState_Methoden;
import me.stonygames.Defend.Methoden.Strings_Methoden;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by chris on 02.02.2017.
 */
public class PlayerEventListener implements Listener {

    Strings_Methoden strings_methoden = new Strings_Methoden();


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Main.main.utils.clearPlayer(p);
        if (Main.main.state == GameState_Methoden.LOBBY) {
            if (!Main.main.alive.contains(p)) {
                Main.main.alive.add(p);
            }
            e.setJoinMessage(strings_methoden.prefix + "Der Spieler §6" + p.getDisplayName() + " §7hat den Server betreten");
            p.teleport(Main.main.lm.getLoc("lobby"));
            Main.main.utils.clearPlayer(p);
            if (Bukkit.getOnlinePlayers().size() == Main.main.min) {
                Main.main.cd.startLobbyCD();
            }
        } else {
            p.kickPlayer("§cDas Spiel hat bereits begonnen");
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if (Main.main.state != GameState_Methoden.LOBBY) {
            e.disallow(PlayerLoginEvent.Result.KICK_FULL, "§cDas Spieler hat bereits begonnen");
            return;
        } else {
            if (Main.main.alive.size() >= Main.main.max) {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cDas Spiel ist bereits voll");
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {


        if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {

        }  else if (e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {

        }

        }



    @EventHandler
    public void onPing(ServerListPingEvent e) {
        e.setMaxPlayers(Main.main.max);
        if (Main.main.state == GameState_Methoden.LOBBY) {
            e.setMotd("§6Lobby");
        } else if (Main.main.state == GameState_Methoden.INGAME) {
            e.setMotd("§aIngame");
        } else if (Main.main.state == GameState_Methoden.RESTARTING) {
            e.setMotd("§cRestarting");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(Main.main.state == GameState_Methoden.INGAME) {

        } else {
            e.setQuitMessage(strings_methoden.prefix + "Der Spieler §a" + p.getName() + " §7hat den Server verlassen");
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if(Main.main.alive.contains(p)) {
            Main.main.alive.remove(p);
            if(Main.main.lastdmg.containsKey(p)) {
                e.setDeathMessage(strings_methoden.prefix + "Der Spieler §c" + p.getName() + " §7wurde von §a" + Main.main.lastdmg.get(p).getDisplayName() + " §7getötet");
            } else {
                e.setDeathMessage(strings_methoden.prefix + "Der Spieler §a" + p.getDisplayName() + " §7ist gestorben");
            }

            for(Player alive : Main.main.alive) {
                alive.hidePlayer(p);
            }
            new BukkitRunnable() {

                @Override
                public void run() {
                    if(!p.isDead()) {
                        p.spigot().respawn();
                    }
                }
            }.runTaskLater(Main.main, 20*1);

        } else {
            e.setDeathMessage(null);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta cm = compass.getItemMeta();
        cm.setDisplayName("§6Teleporter");
        compass.setItemMeta(cm);

        p.getInventory().addItem(compass);
        p.setAllowFlight(true);
        p.setFlying(true);
    }
}
