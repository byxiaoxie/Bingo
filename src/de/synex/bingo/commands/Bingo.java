package de.synex.bingo.commands;

import de.synex.bingo.main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import java.util.*;

public class Bingo implements CommandExecutor
{
    Main main;
    
    public Bingo(final Main m) {
        this.main = m;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (sender instanceof Player) {
            if (args.length == 0) {
                if (this.main.GameStarted) {
                    final Inventory bingo = Bukkit.createInventory((InventoryHolder)null, 9, this.main.getCL().bingoitemsinround);
                    if (!this.main.randomized) {
                        this.main.getRandomItems();
                    }
                    bingo.setItem(0, this.main.b1);
                    bingo.setItem(1, this.main.b2);
                    bingo.setItem(2, this.main.b3);
                    bingo.setItem(3, this.main.b4);
                    bingo.setItem(4, this.main.b5);
                    bingo.setItem(5, this.main.b6);
                    bingo.setItem(6, this.main.b7);
                    bingo.setItem(7, this.main.b8);
                    bingo.setItem(8, this.main.b9);
                    p.openInventory(bingo);
                    if (this.main.t1.contains(p)) {
                        if (!this.main.foundItemsT1.isEmpty()) {
                            p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().yourteamfoundfollowingitemsalready);
                            for (final ItemStack founditems : this.main.foundItemsT1) {
                            	p.sendMessage("¡ì2[Bingo] ¡ìe" + main.GetItemName.Get_Item_Name(founditems.getType().name()));
                            }
                        }
                    }
                    else if (this.main.t2.contains(p)) {
                        if (!this.main.foundItemsT2.isEmpty()) {
                            p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().yourteamfoundfollowingitemsalready);
                            for (final ItemStack founditems : this.main.foundItemsT2) {
                            	p.sendMessage("¡ì2[Bingo] ¡ìe" + main.GetItemName.Get_Item_Name(founditems.getType().name()));
                            }
                        }
                    }
                    else if (this.main.t3.contains(p) && !this.main.foundItemsT3.isEmpty()) {
                        p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().yourteamfoundfollowingitemsalready);
                        for (final ItemStack founditems : this.main.foundItemsT3) {
                        	p.sendMessage("¡ì2[Bingo] ¡ìe" + main.GetItemName.Get_Item_Name(founditems.getType().name()));
                        }
                    }
                }
                else {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().onlyingamecommand);
                }
            }
            else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("start")) {
                    p.performCommand("bingopl start");
                }
                else if (args[0].equalsIgnoreCase("restart")) {
                    p.performCommand("bingorestart");
                }
                else {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().usagebingo);
                }
            }
            else {
                p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().usagebingo);
            }
        }
        else {
            p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().noconsole);
        }
        return true;
    }
}
