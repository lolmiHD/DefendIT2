package me.stonygames.Defend.Listeners;

import me.stonygames.Defend.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by chris on 03.02.2017.
 */
public class ChatListener implements Listener{

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if(Main.main.alive.contains(p)) {
            Bukkit.broadcastMessage("§6" + p.getDisplayName() + " §8>> §r" + e.getMessage());
        } else {
            for(Player all : Bukkit.getOnlinePlayers()) {
                if(!Main.main.alive.contains(all)) {
                    all.sendMessage("§8[§4X§8] §7" + p.getDisplayName() + " §8>> §r" + e.getMessage());
                }
            }
        }
        e.setCancelled(true);
    }
}
