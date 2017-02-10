package me.stonygames.Defend.Methoden;

import me.stonygames.Defend.Main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by chris on 02.02.2017.
 */
public class FileManger_Methoden {

    public File file = new File("plugins/" + Main.main.getName(), "config.yml");
    public FileConfiguration cfg = YamlConfiguration .loadConfiguration(file);

    public void registerConfig() {
        cfg.options().copyDefaults(true);
        cfg.addDefault("Minimale Spieler" , Main.main.min);
        cfg.addDefault("Maximale Spieler", Main.main.max);
        saveConfig();

        Main.main.min = cfg.getInt("Minimale Spieler");
        Main.main.max = cfg.getInt("Maximale Spieler");

    }

    public void  saveConfig() {

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
