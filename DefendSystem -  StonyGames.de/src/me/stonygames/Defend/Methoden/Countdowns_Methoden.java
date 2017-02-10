package me.stonygames.Defend.Methoden;

import com.connorlinfoot.titleapi.TitleAPI;
import me.stonygames.Defend.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Created by chris on 02.02.2017.
 */
public class Countdowns_Methoden {

    public boolean lobbyStarted = false;
    public boolean restartStarted = false;

    Strings_Methoden strings_methoden = new Strings_Methoden();

    int lobbycd;
    int gamecd;
    int restartcd;

    public int lobby = Main.main.lobby;
    int restart = Main.main.restart;
    int game = Main.main.ingame;

    public void startLobbyCD() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (lobbyStarted == false) {
                lobbyStarted = true;

                lobbycd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable() {
                    @Override
                    public void run() {
                        if (lobby >= 1) {
                            if (lobby == 60
                                    || lobby == 30
                                    || lobby == 15
                                    || lobby == 10
                                    || lobby <= 5 && lobby >= 1) {

                                if (lobby == 1) {
                                    all.playSound(all.getLocation(), Sound.NOTE_SNARE_DRUM, 10, 10);
                                    Bukkit.broadcastMessage(strings_methoden.prefix + "Das Spiel startet in §a" + lobby + " §7Sekunde");

                                } else {
                                    Bukkit.broadcastMessage(strings_methoden.prefix + "Das Spiel startet in §a" + lobby + " §7Sekunden");
                                    all.playSound(all.getLocation(), Sound.NOTE_SNARE_DRUM, 10, 10);
                                }

                            }

                        } else if (lobby == 0) {

                            Main.main.lm.mapTeleport();
                            startIngameCD();
                            Bukkit.getScheduler().cancelTask(lobbycd);
                            Bukkit.broadcastMessage(strings_methoden.prefix + "Das Spiel hat begonnen");



                        }
                        lobby--;

                    }
                }, 0, 20L);

            }
        }
    }

    public void startRestartCD() {

        Main.main.state = GameState_Methoden.RESTARTING;

        if(restartStarted == false) {

            restartStarted = true;

            restartcd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable() {
                @Override
                public void run() {
                    if (restart >= 1) {
                        if (restart == 15
                                || restart == 10
                                || restart <= 5 && restart >= 1 ) {

                            if(restart == 1) {
                                Bukkit.broadcastMessage(strings_methoden.prefix + "Das Server restartet in §a" + restart + " §7Sekunde");

                            } else {
                                Bukkit.broadcastMessage(strings_methoden.prefix + "Das Server restartet in §a" + restart + " §7Sekunden");
                            }
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.setLevel(restart);
                            }

                        }

                    } else if (restart == 0) {

                        Bukkit.getScheduler().cancelTask(restartcd);
                        Bukkit.shutdown();

                    }
                    restart--;
                }
            }, 0, 20L);
        }
    }

    public void startIngameCD() {

        Main.main.state = GameState_Methoden.INGAME;

        gamecd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable() {
            @Override
            public void run() {
                 if(game == 0) {
                        startRestartCD();
                 }

                 lobby --;

            }
        }, 0, 20L);


    }
}
