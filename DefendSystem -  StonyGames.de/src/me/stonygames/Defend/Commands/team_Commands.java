package me.stonygames.Defend.Commands;

import me.stonygames.Defend.Listeners.PlayerEventListener;
import me.stonygames.Defend.Main.Utils;
import me.stonygames.Defend.Methoden.Strings_Methoden;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by chris on 02.02.2017.
 */
public class team_Commands implements CommandExecutor {


    Strings_Methoden strings_methoden = new Strings_Methoden();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(args.length == 0) {

            Inventory inv = Bukkit.createInventory(null, 1*9, "§6TeamWahl");

            ItemStack angreifer = Utils.createItemStack(Material.DIAMOND_SWORD, "§cAngreifer", (short) 0, 1, "§6Klicke hier§7, §6um das Team §cAngreifer §6zu wählen", "", false, true);
            ItemStack verteidiger = Utils.createItemStack(Material.DIAMOND_CHESTPLATE, "§aVerteidiger", (short) 0, 1, "§6Klicke hier§7, §6um das Team §aAngreifer §6zu wählen", "", false, true);

            inv.setItem(0, angreifer);
            inv.setItem(8, verteidiger);

            p.openInventory(inv);


        } else {
            p.sendMessage(strings_methoden.prefix + "§c/team");
        }
        return false;
    }
}
