package me.stonygames.Defend.Commands;

import me.stonygames.Defend.Main.Main;
import me.stonygames.Defend.Methoden.Strings_Methoden;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by chris on 03.02.2017.
 */
public class start_Commands implements CommandExecutor {

    Strings_Methoden strings_methoden = new Strings_Methoden();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0 ) {
            if(sender.hasPermission("server.start")) {

                if (Main.main.cd.lobby > 5) {

                    Main.main.cd.lobby = 5;
                    sender.sendMessage(strings_methoden.prefix + "§6Du hast das Spiel gestartet");
                } else {
                    sender.sendMessage(strings_methoden.prefix + "§cDas Spiel startet bereits");
                }
            }
        }
        return false;
    }
}
