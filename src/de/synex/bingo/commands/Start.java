package de.synex.bingo.commands;

import de.synex.bingo.main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.*;
import org.bukkit.*;
import java.util.*;

public class Start implements CommandExecutor
{
    Main main;
    int seconds;
    
    public Start(final Main pl) {
        this.seconds = 10;
        this.main = pl;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (args.length == 0) {
                if (p.hasPermission("bingo.start") || p.hasPermission("bingo.gamemaster")) {
                    if (!this.main.GameStarted) {
                        Bukkit.broadcastMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().gamewillstart);
                        new BukkitRunnable() {
                            public void run() {
                                if (Start.this.seconds != 0) {
                                    Bukkit.broadcastMessage(String.valueOf(Start.this.main.getCL().prefix) + " " + Start.this.main.getCL().gamestartsin.replace("%seconds%", String.valueOf(Start.this.seconds)));
                                    for (final Player soundplayer : Bukkit.getOnlinePlayers()) {
                                        soundplayer.playSound(soundplayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.3f, 1.0f);
                                    }
                                    final Start this$0 = Start.this;
                                    --this$0.seconds;
                                }
                                else {
                                    Bukkit.broadcastMessage(String.valueOf(Start.this.main.getCL().prefix) + " " + Start.this.main.getCL().gamestarts);
                                    this.cancel();
                                    Start.this.seconds = 10;
                                    Start.this.main.startGame();
                                }
                            }
                        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Bingo"), 0L, 20L);
                    }
                    else {
                        p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().gamealreadystarts);
                    }
                }
                else {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().nopermission);
                }
            }
            else {
                p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().usagestart);
            }
        }
        else {
            sender.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().noconsole);
        }
        return true;
    }
}
