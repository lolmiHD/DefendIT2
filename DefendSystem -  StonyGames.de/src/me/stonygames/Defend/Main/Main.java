package me.stonygames.Defend.Main;

import me.stonygames.Defend.Commands.setLobby_Commands;
import me.stonygames.Defend.Commands.setSpawn_Commands;
import me.stonygames.Defend.Commands.start_Commands;
import me.stonygames.Defend.Commands.team_Commands;
import me.stonygames.Defend.Listeners.ChatListener;
import me.stonygames.Defend.Listeners.ItemsListener;
import me.stonygames.Defend.Listeners.PlayerEventListener;
import me.stonygames.Defend.Listeners.TeleporterListener;
import me.stonygames.Defend.Methoden.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chris on 02.02.2017.
 */
public class Main extends JavaPlugin{

    Strings_Methoden strings_methoden = new Strings_Methoden();

    public GameState_Methoden state;
    public FileManger_Methoden fm;
    public LocationManager_Methoden lm;
    public ArrayList<Player> alive;
    public static Main main;
    public Countdowns_Methoden cd;
    public Utils utils;
    public PlayerEventListener pel;

    public int min = 2;
    public int max = 12;

    public int lobby = 60;
    public int ingame = 60*20;
    public int restart = 15;

    public HashMap<Player, Player> lastdmg;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(strings_methoden.prefix + "§aDas Plugin wurde aktiviert");
        registerCommands();
        registerListeners();

        main = this;
        state = GameState_Methoden.LOBBY;
        alive = new ArrayList<Player>();
        fm = new FileManger_Methoden();
        fm.saveConfig();
        fm.registerConfig();
        cd = new Countdowns_Methoden();
        lm = new LocationManager_Methoden();
        lm.saveConfig();
        utils = new Utils();
        pel = new PlayerEventListener();
        lastdmg = new HashMap<Player, Player>();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(strings_methoden.prefix + "§cDas Plugin wurde deaktiviert");

    }

    public void registerCommands() {
        getCommand("setlobby").setExecutor(new setLobby_Commands());
        getCommand("setspawn").setExecutor(new setSpawn_Commands());
        getCommand("team").setExecutor(new team_Commands());
        getCommand("start").setExecutor(new start_Commands());
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerEventListener(), this);
        getServer().getPluginManager().registerEvents(new ItemsListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new TeleporterListener(), this);



    }
}
