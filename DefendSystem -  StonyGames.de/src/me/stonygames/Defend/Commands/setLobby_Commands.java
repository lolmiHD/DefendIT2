package me.stonygames.Defend.Commands;

import me.stonygames.Defend.Main.Main;
import me.stonygames.Defend.Methoden.Strings_Methoden;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by chris on 02.02.2017.
 */
public class setLobby_Commands implements CommandExecutor {

    Strings_Methoden strings_methoden = new Strings_Methoden();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length != 0) {
                p.sendMessage(strings_methoden.prefix + "§c/setlobby");
                return true;
            } else {
                Main.main.lm.setLoc(p.getLocation(), "lobby");
                p.sendMessage(strings_methoden.prefix + "Du hasst die §aLobby §7erfolgreich gesetzt");
            }
        }
        return false;
    }
}

