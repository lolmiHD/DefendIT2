package me.stonygames.Defend.Methoden;

import me.stonygames.Defend.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by chris on 02.02.2017.
 */
public class LocationManager_Methoden {

    public File file = new File("plugins/" + Main.main.getName(), "locs.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void setLoc(Location loc, String name) {
        cfg.set(name + ".world", loc.getWorld().getName());
        cfg.set(name + ".x", loc.getX());
        cfg.set(name + ".y", loc.getY());
        cfg.set(name + ".z", loc.getZ());

        cfg.set(name + ".yaw", loc.getYaw());
        cfg.set(name + ".pitch", loc.getPitch());

        saveConfig();
    }

    public Location getLoc(String name) {
        Location loc;
        try {
            World w = Bukkit.getWorld(cfg.getString(name + ".world"));
            double x = cfg.getDouble(name + ".x");
            double y = cfg.getDouble(name + ".y");
            double z = cfg.getDouble(name + ".z");
            loc = new Location(w, x, y, z);
            loc.setYaw(cfg.getInt(name + ".yaw"));
            loc.setPitch(cfg.getInt(name + ".pitch"));
        } catch (Exception e) {
            loc = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
        }
        return loc;
    }


    public void setSpawn(int number, Location loc) {
        String name = "Spawn";
        cfg.set(name + "." + number + ".world", loc.getWorld().getName());
        cfg.set(name + "." + number +  ".x", loc.getX());
        cfg.set(name + "." + number +  ".y", loc.getY());
        cfg.set(name + "." + number +  ".z", loc.getZ());

        cfg.set(name + "." + number +  ".yaw", loc.getYaw());
        cfg.set(name + "." + number +  ".pitch", loc.getPitch());

        saveConfig();
    }

    public Location getSpawn(int number) {
        String name = "Spawn";
        World w = Bukkit.getWorld(cfg.getString(name + "." + number + ".world"));
        double x = cfg.getDouble(name + "." + number + ".x");
        double y = cfg.getDouble(name + "." + number + ".y");
        double z = cfg.getDouble(name + "." + number + ".z");

        Location loc = new Location(w, x, y, z);

        loc.setYaw(cfg.getInt(name + "." + number + ".yaw"));
        loc.setPitch(cfg.getInt(name + "." + number + ".pitch"));

        return loc;
    }

    public void saveConfig() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mapTeleport() {
        int count = 1;
        for(Player alive : Main.main.alive) {
            alive.teleport(getSpawn(count));
            count ++;
        }
    }

}
