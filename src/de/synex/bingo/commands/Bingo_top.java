package de.synex.bingo.commands;

import de.synex.bingo.main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.block.*;

public class Bingo_top implements CommandExecutor
{
    Main main;
    
    public Bingo_top(final Main instance) {
        this.main = instance;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("bingo.top")) {
                if (args.length == 0) {
                    if (p.getGameMode() == GameMode.SURVIVAL) {
                        if (p.getWorld() == Bukkit.getWorld("bingoworld")) {
                            final Location temploc = p.getLocation();
                            final Block b = p.getWorld().getHighestBlockAt(temploc.getBlockX(), temploc.getBlockZ());
                            final Location temploc2 = new Location(b.getLocation().getWorld(), (double)b.getLocation().getBlockX(), (double)(b.getLocation().getBlockY() + 1), (double)b.getLocation().getBlockZ());
                            p.teleport(temploc2);
                            p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().teleportedtop);
                        }
                        else {
                            p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().mustbingoworld);
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().nospectatortop);
                    }
                }
                else {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().usagetop);
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
