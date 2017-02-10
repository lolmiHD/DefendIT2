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
public class setSpawn_Commands implements CommandExecutor {

    Strings_Methoden strings_methoden = new Strings_Methoden();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length != 1) {
                p.sendMessage(strings_methoden.prefix + "§c/setspawn <Verteidiger, Angreifer>");
                return true;
            }
            try {
                int number = Integer.parseInt(args[0]);
                Main.main.lm.setSpawn(number, p.getLocation());
                p.sendMessage(strings_methoden.prefix + "Du hast den Spawn [§a" + number + "§7] erfolgreich gesetzt");

            } catch (NumberFormatException e) {
                p.sendMessage(strings_methoden.prefix + "§cDu musst eine Zahl als Spawn angeben (1-12)");
            }

        }
        return false;
    }
}

