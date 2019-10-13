package de.synex.bingo.commands;

import de.synex.bingo.main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class Bingo_restart implements CommandExecutor
{
    Main main;
    
    public Bingo_restart(final Main instance) {
        this.main = instance;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("bingo.restart") || p.hasPermission("bingo.gamemaster")) {
                if (args.length == 0) {
                    this.main.isRestarting = true;
                    this.main.GameStarted = false;
                    this.main.LobbyStatus = true;
                    this.main.manuallyRestarting();
                }
                else {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().usagebingorestart);
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
