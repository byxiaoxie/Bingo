package de.synex.bingo.commands;

import de.synex.bingo.main.*;
import de.synex.bingo.util.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.*;
import org.bukkit.*;
import java.util.*;

public class Bingo_guide implements CommandExecutor
{
    Main main;
    ConfigLink cl;
    int seconds;
    
    public Bingo_guide(final Main instance) {
        this.seconds = 10;
        this.main = instance;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("bingo.defaultcmd")) {
                if (args.length == 0) {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().notavailable);
                }
                else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().notavailable);
                }
                else if (args.length == 1 && args[0].equalsIgnoreCase("prefixdebug")) {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().notavailable);
                }
                else if (args.length == 1 && args[0].equalsIgnoreCase("status")) {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().notavailable);
                }
                else if (args.length == 1 && args[0].equalsIgnoreCase("start")) {
                    if (p.hasPermission("bingo.start") || p.hasPermission("bingo.gamemaster")) {
                        if (!this.main.GameStarted) {
                            Bukkit.broadcastMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().gamewillstart);
                            new BukkitRunnable() {
                                public void run() {
                                    if (Bingo_guide.this.seconds != 0) {
                                        Bukkit.broadcastMessage(String.valueOf(Bingo_guide.this.main.getCL().prefix) + " " + Bingo_guide.this.main.getCL().gamestartsin.replace("%seconds%", String.valueOf(Bingo_guide.this.seconds)));
                                        for (final Player soundplayer : Bukkit.getOnlinePlayers()) {
                                            soundplayer.playSound(soundplayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.3f, 1.0f);
                                        }
                                        final Bingo_guide this$0 = Bingo_guide.this;
                                        --this$0.seconds;
                                    }
                                    else {
                                        Bukkit.broadcastMessage(String.valueOf(Bingo_guide.this.main.getCL().prefix) + " " + Bingo_guide.this.main.getCL().gamestarts);
                                        this.cancel();
                                        Bingo_guide.this.seconds = 10;
                                        Bingo_guide.this.main.startGame();
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
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().usagebingopl);
                }
            }
            else {
                p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().nopermission);
            }
        }
        else {
            sender.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().noconsole);
        }
        return true;
    }
}
