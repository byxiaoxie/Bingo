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
    
    public String get_Item_name(String name) {
    	if (name=="MILK_BUCKET") {
    		return "Å£ÄÌÍ°";
		}
    	else if (name=="EMERALD") {
    		return "ÂÌ±¦Ê¯";
		} 
    	else if (name=="DIAMOND") {
    		return "×êÊ¯";
		} 
    	else if (name=="MUSHROOM_STEW") {
    		return "Ä¢¹½ÌÀ";
		} 
    	else if (name=="SANDSTONE_STAIRS") {
    		return "É°ÑÒÂ¥ÌÝ";
		}
    	else if (name=="SANDSTONE_STAIRS") {
    		return "É°ÑÒÂ¥ÌÝ";
		}
    	else if (name=="CACTUS") {
    		return "ÏÉÈËÕÆ";
		}
    	else if (name=="PAINTING") {
    		return "»­";
		}
    	else if (name=="DETECTOR_RAIL") {
    		return "Ì½²âÌú¹ì";
		}
    	else if (name=="COOKED_COD") {
    		return "Êì÷¨Óã";
		}
    	else if (name=="FLETCHING_TABLE") {
    		return "ÖÆ¼ýÌ¨";
		}
    	else if (name=="SHIELD") {
    		return "¶ÜÅÆ";
		}
    	else if (name=="PAPER") {
    		return "Ö½";
		}
    	else if (name=="ANVIL") {
    		return "ÌúÕè";
		}
    	else if (name=="TNT") {
    		return "TNT";
		}
    	else if (name=="WHITE_BANNER") {
    		return "°×É«ÆìÖÄ";
		}
    	else if (name=="CARROT_ON_A_STICK") {
    		return "ºúÂÜ²·µö¸Í";
		}
    	else if (name=="DIAMOND_LEGGINGS") {
    		return "×êÊ¯»¤ÍÈ";
		}
    	else if (name=="DRIED_KELP") {
    		return "¸Éº£´ø";
		}
    	else if (name=="GRANITE_STAIRS") {
    		return "»¨¸ÚÑÒ½×ÌÝ";
		}
    	else if (name=="PISTON") {
    		return "»îÈû";
		}
    	else if (name=="GOLDEN_APPLE") {
    		return "½ðÆ»¹û";
		}
    	else if (name=="ARMOR_STAND") {
			return "¿ø¼×¼Ü";
		}
    	else if (name=="ENCHANTING_TABLE") {
    		return "¸½Ä§Ì¨";
		}
    	else if (name=="CROSSBOW") {
    		return "åó";
		}
    	else if (name=="PUMPKIN") {
    		return "ÄÏ¹Ï";
		}
    	else if (name=="LECTERN") {
    		return "½²Ì¨";
		}
    	else if (name=="COMPASS") {
    		return "Ö¸ÄÏÕë";
		}
    	else if (name=="LIGHT_WEIGHTED_PRESSURE_PLATE") {
    		return "ÇáÖÊ²âÖØÑ¹Á¦°å";
		}
    	else {
    		return name;
		}
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
                            	p.sendMessage("¡ì2[Bingo] ¡ìe" + get_Item_name(founditems.getType().name()));
                            }
                        }
                    }
                    else if (this.main.t2.contains(p)) {
                        if (!this.main.foundItemsT2.isEmpty()) {
                            p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().yourteamfoundfollowingitemsalready);
                            for (final ItemStack founditems : this.main.foundItemsT2) {
                            	p.sendMessage("¡ì2[Bingo] ¡ìe" + get_Item_name(founditems.getType().name()));
                            }
                        }
                    }
                    else if (this.main.t3.contains(p) && !this.main.foundItemsT3.isEmpty()) {
                        p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().yourteamfoundfollowingitemsalready);
                        for (final ItemStack founditems : this.main.foundItemsT3) {
                        	p.sendMessage("¡ì2[Bingo] ¡ìe" + get_Item_name(founditems.getType().name()));
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
