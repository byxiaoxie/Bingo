package de.synex.bingo.commands;

import de.synex.bingo.main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class Restart implements CommandExecutor
{
    Main main;
    
    public Restart(final Main pl) {
        this.main = pl;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (args.length == 0) {
                if (p.hasPermission("bingo.restart") || p.hasPermission("bingo.gamemaster")) {
                    p.performCommand("bingorestart");
                }
                else {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().nopermission);
                }
            }
            else {
                p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().usagebrestart);
            }
        }
        else {
            sender.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().noconsole);
        }
        return true;
    }
}
