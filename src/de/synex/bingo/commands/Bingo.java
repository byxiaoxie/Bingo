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
                            	if (founditems.getType().name()=="MILK_BUCKET") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Å£ÄÌÍ°");
								}
                            	else if (founditems.getType().name()=="EMERALD") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ÂÌ±¦Ê¯");
								} 
                            	else if (founditems.getType().name()=="DIAMOND") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "×êÊ¯");
								} 
                            	else if (founditems.getType().name()=="MUSHROOM_SOUP") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Ä¢¹½ÌÀ");
								} 
                            	else if (founditems.getType().name()=="SANDSTONE_STAIRS") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "É°ÑÒÂ¥ÌÝ");
								}
                            	else if (founditems.getType().name()=="SANDSTONE_STAIRS") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "É°ÑÒÂ¥ÌÝ");
								}
                            	else if (founditems.getType().name()=="CACTUS") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ÏÉÈËÕÆ");
								}
                            	else if (founditems.getType().name()=="PAINTING") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "»­");
								}
                            	else if (founditems.getType().name()=="DETECTOR_RAIL") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Ì½²âÌú¹ì");
								}
                            	else if (founditems.getType().name()=="COOKED_COD") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Êì÷¨Óã");
								}
                            	else if (founditems.getType().name()=="FLETCHING_TABLE") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ÖÆ¼ýÌ¨");
								}
                            	else if (founditems.getType().name()=="SHIELD") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "¶ÜÅÆ");
								}
                            	else if (founditems.getType().name()=="PAPER") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Ö½");
								}
                            	else if (founditems.getType().name()=="ANVIL") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ÌúÕè");
								}
                            	else if (founditems.getType().name()=="TNT") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "TNT");
								}
                            	else if (founditems.getType().name()=="WHITE_BANNER") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "°×É«ÆìÖÄ");
								}
                            	else if (founditems.getType().name()=="CARROT_ON_A_STICK") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ºúÂÜ²·µö¸Í");
								}
                            	else if (founditems.getType().name()=="DIAMOND_LEGGINGS") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "×êÊ¯»¤ÍÈ");
								}
                            	else if (founditems.getType().name()=="DRIED_KELP") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "¸Éº£´ø");
								}
                            	else if (founditems.getType().name()=="GRANITE_STAIRS") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "»¨¸ÚÑÒ½×ÌÝ");
								}
                            	else if (founditems.getType().name()=="PISTON") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "»îÈû");
								}
                            	else if (founditems.getType().name()=="GOLDEN_APPLE") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "½ðÆ»¹û");
								}
                            	else {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + founditems.getType());
								}
                            }
                        }
                    }
                    else if (this.main.t2.contains(p)) {
                        if (!this.main.foundItemsT2.isEmpty()) {
                            p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().yourteamfoundfollowingitemsalready);
                            for (final ItemStack founditems : this.main.foundItemsT2) {
                            	if (founditems.getType().name()=="MILK_BUCKET") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Å£ÄÌÍ°");
								}
                            	else if (founditems.getType().name()=="EMERALD") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ÂÌ±¦Ê¯");
								} 
                            	else if (founditems.getType().name()=="DIAMOND") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "×êÊ¯");
								} 
                            	else if (founditems.getType().name()=="MUSHROOM_SOUP") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Ä¢¹½ÌÀ");
								} 
                            	else if (founditems.getType().name()=="SANDSTONE_STAIRS") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "É°ÑÒÂ¥ÌÝ");
								}
                            	else if (founditems.getType().name()=="SANDSTONE_STAIRS") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "É°ÑÒÂ¥ÌÝ");
								}
                            	else if (founditems.getType().name()=="CACTUS") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ÏÉÈËÕÆ");
								}
                            	else if (founditems.getType().name()=="PAINTING") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "»­");
								}
                            	else if (founditems.getType().name()=="DETECTOR_RAIL") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Ì½²âÌú¹ì");
								}
                            	else if (founditems.getType().name()=="COOKED_COD") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Êì÷¨Óã");
								}
                            	else if (founditems.getType().name()=="FLETCHING_TABLE") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ÖÆ¼ýÌ¨");
								}
                            	else if (founditems.getType().name()=="SHIELD") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "¶ÜÅÆ");
								}
                            	else if (founditems.getType().name()=="PAPER") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Ö½");
								}
                            	else if (founditems.getType().name()=="ANVIL") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ÌúÕè");
								}
                            	else if (founditems.getType().name()=="TNT") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "TNT");
								}
                            	else if (founditems.getType().name()=="WHITE_BANNER") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "°×É«ÆìÖÄ");
								}
                            	else if (founditems.getType().name()=="CARROT_ON_A_STICK") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ºúÂÜ²·µö¸Í");
								}
                            	else if (founditems.getType().name()=="DIAMOND_LEGGINGS") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "×êÊ¯»¤ÍÈ");
								}
                            	else if (founditems.getType().name()=="DRIED_KELP") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "¸Éº£´ø");
								}
                            	else if (founditems.getType().name()=="GRANITE_STAIRS") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "»¨¸ÚÑÒ½×ÌÝ");
								}
                            	else if (founditems.getType().name()=="PISTON") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "»îÈû");
								}
                            	else if (founditems.getType().name()=="GOLDEN_APPLE") {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + "½ðÆ»¹û");
								}
                            	else {
                            		p.sendMessage("¡ì2[Bingo] ¡ìe" + founditems.getType());
								}
                            }
                        }
                    }
                    else if (this.main.t3.contains(p) && !this.main.foundItemsT3.isEmpty()) {
                        p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().yourteamfoundfollowingitemsalready);
                        for (final ItemStack founditems : this.main.foundItemsT3) {
                        	if (founditems.getType().name()=="MILK_BUCKET") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Å£ÄÌÍ°");
							}
                        	else if (founditems.getType().name()=="EMERALD") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ÂÌ±¦Ê¯");
							} 
                        	else if (founditems.getType().name()=="DIAMOND") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "×êÊ¯");
							} 
                        	else if (founditems.getType().name()=="MUSHROOM_SOUP") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Ä¢¹½ÌÀ");
							} 
                        	else if (founditems.getType().name()=="SANDSTONE_STAIRS") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "É°ÑÒÂ¥ÌÝ");
							}
                        	else if (founditems.getType().name()=="SANDSTONE_STAIRS") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "É°ÑÒÂ¥ÌÝ");
							}
                        	else if (founditems.getType().name()=="CACTUS") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ÏÉÈËÕÆ");
							}
                        	else if (founditems.getType().name()=="PAINTING") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "»­");
							}
                        	else if (founditems.getType().name()=="DETECTOR_RAIL") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Ì½²âÌú¹ì");
							}
                        	else if (founditems.getType().name()=="COOKED_COD") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Êì÷¨Óã");
							}
                        	else if (founditems.getType().name()=="FLETCHING_TABLE") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ÖÆ¼ýÌ¨");
							}
                        	else if (founditems.getType().name()=="SHIELD") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "¶ÜÅÆ");
							}
                        	else if (founditems.getType().name()=="PAPER") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "Ö½");
							}
                        	else if (founditems.getType().name()=="ANVIL") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ÌúÕè");
							}
                        	else if (founditems.getType().name()=="TNT") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "TNT");
							}
                        	else if (founditems.getType().name()=="WHITE_BANNER") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "°×É«ÆìÖÄ");
							}
                        	else if (founditems.getType().name()=="CARROT_ON_A_STICK") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "ºúÂÜ²·µö¸Í");
							}
                        	else if (founditems.getType().name()=="DIAMOND_LEGGINGS") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "×êÊ¯»¤ÍÈ");
							}
                        	else if (founditems.getType().name()=="DRIED_KELP") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "¸Éº£´ø");
							}
                        	else if (founditems.getType().name()=="GRANITE_STAIRS") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "»¨¸ÚÑÒ½×ÌÝ");
							}
                        	else if (founditems.getType().name()=="PISTON") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "»îÈû");
							}
                        	else if (founditems.getType().name()=="GOLDEN_APPLE") {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + "½ðÆ»¹û");
							}
                        	else {
                        		p.sendMessage("¡ì2[Bingo] ¡ìe" + founditems.getType());
							}
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
