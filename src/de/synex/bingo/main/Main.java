package de.synex.bingo.main;

import org.bukkit.plugin.java.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.command.*;
import de.synex.bingo.commands.*;
import de.synex.bingo.listeners.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import de.synex.bingo.util.*;
import org.bukkit.util.*;
import org.bukkit.configuration.file.*;
import java.io.*;
import org.bukkit.configuration.*;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.*;
import io.netty.util.internal.*;
import org.bukkit.scheduler.*;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.*;
import org.bukkit.inventory.meta.*;
import java.util.*;
import org.bukkit.*;

public class Main extends JavaPlugin
{
    public boolean LobbyStatus;
    public boolean GameStarted;
    public boolean isRestarting;
    public String winner1;
    public String winner2;
    public ArrayList<Player> t1;
    public ArrayList<Player> t2;
    public ArrayList<Player> t3;
    public int players;
    public int spectator;
    public ItemStack sandstonestairs;
    public ItemStack cactus;
    public ItemStack limewool;
    public ItemStack tnt;
    public ItemStack emerald;
    public ItemStack detectorrail;
    public ItemStack goldenapple;
    public ItemStack fletchingtable;
    public ItemStack enchantingtable;
    public ItemStack bucketwithsalmon;
    public ItemStack itemframe;
    public ItemStack anvil;
    public ItemStack clock;
    public ItemStack armorstand;
    public ItemStack poweredrail;
    public ItemStack activatorrail;
    public ItemStack carrotonstick;
    public ItemStack jukebox;
    public ItemStack lectern;
    public ItemStack lilypad;
    public ItemStack bell;
    public ItemStack obsidian;
    public ItemStack painting;
    public ItemStack crossbow;
    public ItemStack diamondchestplate;
    public ItemStack compass;
    public ItemStack driedkelp;
    public ItemStack milkbucket;
    public ItemStack mushroomstew;
    public ItemStack enderpearl;
    public ItemStack bookshelf;
    public ItemStack cookedcod;
    public ItemStack diamondleggings;
    public ItemStack glassbottle;
    public ItemStack cauldron;
    public ItemStack goldpressureplate;
    public ItemStack piston;
    public ItemStack flintandsteel;
    public ItemStack diamond;
    public ItemStack banner;
    public ItemStack shield;
    public ItemStack granitestairs;
    public ItemStack paper;
    public ItemStack pumpkin;
    public ArrayList<ItemStack> itemlist;
    public ItemStack b1;
    public ItemStack b2;
    public ItemStack b3;
    public ItemStack b4;
    public ItemStack b5;
    public ItemStack b6;
    public ItemStack b7;
    public ItemStack b8;
    public ItemStack b9;
    public boolean b1t1;
    public boolean b2t1;
    public boolean b3t1;
    public boolean b4t1;
    public boolean b5t1;
    public boolean b6t1;
    public boolean b7t1;
    public boolean b8t1;
    public boolean b9t1;
    public boolean b1t2;
    public boolean b2t2;
    public boolean b3t2;
    public boolean b4t2;
    public boolean b5t2;
    public boolean b6t2;
    public boolean b7t2;
    public boolean b8t2;
    public boolean b9t2;
    public boolean b1t3;
    public boolean b2t3;
    public boolean b3t3;
    public boolean b4t3;
    public boolean b5t3;
    public boolean b6t3;
    public boolean b7t3;
    public boolean b8t3;
    public boolean b9t3;
    public int gotitems1;
    public int gotitems2;
    public int gotitems3;
    public ArrayList<ItemStack> foundItemsT1;
    public ArrayList<ItemStack> foundItemsT2;
    public ArrayList<ItemStack> foundItemsT3;
    public boolean randomized;
    private static Main instance;
    public FileConfiguration LangConfig;
    public File LangConfigFile;
    ConfigLink cl;
    
    public Main() {
        this.LobbyStatus = true;
        this.GameStarted = false;
        this.isRestarting = false;
        this.t1 = new ArrayList<Player>();
        this.t2 = new ArrayList<Player>();
        this.t3 = new ArrayList<Player>();
        this.players = 0;
        this.spectator = 0;
        this.itemlist = new ArrayList<ItemStack>();
        this.b1t1 = false;
        this.b2t1 = false;
        this.b3t1 = false;
        this.b4t1 = false;
        this.b5t1 = false;
        this.b6t1 = false;
        this.b7t1 = false;
        this.b8t1 = false;
        this.b9t1 = false;
        this.b1t2 = false;
        this.b2t2 = false;
        this.b3t2 = false;
        this.b4t2 = false;
        this.b5t2 = false;
        this.b6t2 = false;
        this.b7t2 = false;
        this.b8t2 = false;
        this.b9t2 = false;
        this.b1t3 = false;
        this.b2t3 = false;
        this.b3t3 = false;
        this.b4t3 = false;
        this.b5t3 = false;
        this.b6t3 = false;
        this.b7t3 = false;
        this.b8t3 = false;
        this.b9t3 = false;
        this.foundItemsT1 = new ArrayList<ItemStack>();
        this.foundItemsT2 = new ArrayList<ItemStack>();
        this.foundItemsT3 = new ArrayList<ItemStack>();
    }
    
    public void onEnable() {
        this.createCustomConfig();
        Main.instance = this;
        this.t1.clear();
        this.t2.clear();
        this.t3.clear();
        final WorldCreator wc = new WorldCreator("bingoworld");
        wc.type(WorldType.NORMAL);
        Bukkit.createWorld(wc);
        this.getCommand("bingopl").setExecutor((CommandExecutor)new Bingo_guide(this));
        this.getCommand("bingorestart").setExecutor((CommandExecutor)new Bingo_restart(this));
        this.getCommand("top").setExecutor((CommandExecutor)new Bingo_top(this));
        this.getCommand("bingo").setExecutor((CommandExecutor)new Bingo(this));
        this.getCommand("start").setExecutor((CommandExecutor)new Start(this));
        this.getCommand("brestart").setExecutor((CommandExecutor)new Restart(this));
        this.getServer().getPluginManager().registerEvents((Listener)new Listeners(this), (Plugin)this);
        final WorldBorder wb = Bukkit.getWorld("bingoworld").getWorldBorder();
        wb.setCenter(0.0, 0.0);
        wb.setSize(8000.0, 8000L);
        this.cl = new ConfigLink(this);
        Bukkit.getConsoleSender().sendMessage(String.valueOf(this.getCL().prefix) + " " + this.getCL().pluginstarted);
    }
    
    public void onDisable() {
        this.deleteWorld();
        Bukkit.getConsoleSender().sendMessage(String.valueOf(this.getCL().prefix) + " " + this.getCL().pluginstopped);
    }
    
    public FileConfiguration getLangConfig() {
        return this.LangConfig;
    }
    
    public ConfigLink getCL() {
        return this.cl;
    }
    
    private void createCustomConfig() {
        this.LangConfigFile = new File(this.getDataFolder(), "language.yml");
        if (!this.LangConfigFile.exists()) {
            this.LangConfigFile.getParentFile().mkdirs();
            this.saveResource("language.yml", false);
        }
        this.LangConfig = (FileConfiguration)new YamlConfiguration();
        try {
            this.LangConfig.load(this.LangConfigFile);
        }
        catch (IOException | InvalidConfigurationException ex2) {
            final Exception ex = null;
            final Exception e = ex;
            e.printStackTrace();
        }
    }
    
    public static Main getPlugin() {
        return Main.instance;
    }
    
    public void deleteWorld() {
        final World world = Bukkit.getWorld("bingoworld");
        final File file = new File("bingoworld");
        if (!file.exists() || !file.isDirectory()) {
            return;
        }
        try {
            for (final Player players : Bukkit.getOnlinePlayers()) {
                players.kickPlayer(String.valueOf(this.getCL().prefix) + " " + this.getCL().gameisrestarting);
            }
            Bukkit.unloadWorld(world.getName(), true);
            FileUtils.deleteDirectory(file);
        }
        catch (IOException ex) {}
    }
    
    public void deleteWorldWithoutChunk() {
        final World world = Bukkit.getWorld("bingoworld");
        final File file = new File("bingoworld");
        if (!file.exists() || !file.isDirectory()) {
            return;
        }
        try {
            for (final Player players : Bukkit.getOnlinePlayers()) {
                players.kickPlayer(String.valueOf(this.getCL().prefix) + " " + this.getCL().gameisrestarting);
            }
            Bukkit.unloadWorld(world.getName(), false);
            FileUtils.deleteDirectory(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void restartGame() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (this.t1.contains(player)) {
                this.t1.remove(player);
            }
            else if (this.t2.contains(player)) {
                this.t2.remove(player);
            }
            else if (this.t3.contains(player)) {
                this.t3.remove(player);
            }
            if (this.winner2 == null) {
                player.kickPlayer(String.valueOf(this.getCL().prefix) + " " + this.getCL().kickmsgwinOnePlayer.replace("%player%", this.winner1));
            }
            else {
                player.kickPlayer(String.valueOf(this.getCL().prefix) + " " + this.getCL().kickmsgwinTwoPlayer.replace("%player1%", this.winner1).replace("%player2%", this.winner2));
            }
        }
        final boolean isRestarting = true;
        this.isRestarting = isRestarting;
        if (isRestarting) {
            this.deleteWorld();
            final WorldCreator wc = new WorldCreator("bingoworld");
            wc.type(WorldType.NORMAL);
            Bukkit.createWorld(wc);
            final WorldBorder wb = Bukkit.getWorld("bingoworld").getWorldBorder();
            wb.setCenter(0.0, 0.0);
            wb.setSize(8000.0, 8000L);
            wb.setWarningTime(5);
            wb.setDamageAmount(0.5);
            this.GameStarted = false;
            this.LobbyStatus = true;
            this.isRestarting = false;
            this.randomized = false;
            this.gotitems1 = 0;
            this.gotitems2 = 0;
            this.gotitems3 = 0;
            this.b1t1 = false;
            this.b2t1 = false;
            this.b3t1 = false;
            this.b4t1 = false;
            this.b5t1 = false;
            this.b6t1 = false;
            this.b7t1 = false;
            this.b8t1 = false;
            this.b9t1 = false;
            this.b1t2 = false;
            this.b2t2 = false;
            this.b3t2 = false;
            this.b4t2 = false;
            this.b5t2 = false;
            this.b6t2 = false;
            this.b7t2 = false;
            this.b8t2 = false;
            this.b9t2 = false;
            this.b1t3 = false;
            this.b2t3 = false;
            this.b3t3 = false;
            this.b4t3 = false;
            this.b5t3 = false;
            this.b6t3 = false;
            this.b7t3 = false;
            this.b8t3 = false;
            this.b9t3 = false;
            this.winner1 = null;
            this.winner2 = null;
            this.t1.clear();
            this.t2.clear();
            this.t3.clear();
            this.foundItemsT1.clear();
            this.foundItemsT2.clear();
            this.foundItemsT3.clear();
        }
    }
    
    public void manuallyRestarting() {
        this.isRestarting = true;
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (this.t1.contains(player)) {
                this.t1.remove(player);
            }
            else if (this.t2.contains(player)) {
                this.t2.remove(player);
            }
            else if (this.t3.contains(player)) {
                this.t3.remove(player);
            }
            player.kickPlayer(String.valueOf(this.getCL().prefix) + " " + this.getCL().gameisrestarting);
        }
        final boolean isRestarting = true;
        this.isRestarting = isRestarting;
        if (isRestarting) {
            this.deleteWorld();
            final WorldCreator wc = new WorldCreator("bingoworld");
            wc.type(WorldType.NORMAL);
            Bukkit.createWorld(wc);
            final WorldBorder wb = Bukkit.getWorld("bingoworld").getWorldBorder();
            wb.setCenter(0.0, 0.0);
            wb.setSize(8000.0, 8000L);
            wb.setWarningTime(5);
            wb.setDamageAmount(0.5);
            this.GameStarted = false;
            this.LobbyStatus = true;
            this.isRestarting = false;
            this.randomized = false;
            this.gotitems1 = 0;
            this.gotitems2 = 0;
            this.gotitems3 = 0;
            this.b1t1 = false;
            this.b2t1 = false;
            this.b3t1 = false;
            this.b4t1 = false;
            this.b5t1 = false;
            this.b6t1 = false;
            this.b7t1 = false;
            this.b8t1 = false;
            this.b9t1 = false;
            this.b1t2 = false;
            this.b2t2 = false;
            this.b3t2 = false;
            this.b4t2 = false;
            this.b5t2 = false;
            this.b6t2 = false;
            this.b7t2 = false;
            this.b8t2 = false;
            this.b9t2 = false;
            this.b1t3 = false;
            this.b2t3 = false;
            this.b3t3 = false;
            this.b4t3 = false;
            this.b5t3 = false;
            this.b6t3 = false;
            this.b7t3 = false;
            this.b8t3 = false;
            this.b9t3 = false;
            this.t1.clear();
            this.t2.clear();
            this.t3.clear();
            this.winner1 = null;
            this.winner2 = null;
            this.foundItemsT1.clear();
            this.foundItemsT2.clear();
            this.foundItemsT3.clear();
        }
    }
    
    public void noPlayerRestart() {
        this.isRestarting = true;
        final boolean isRestarting = true;
        this.isRestarting = isRestarting;
        if (isRestarting) {
            this.deleteWorldWithoutChunk();
            final WorldCreator wc = new WorldCreator("bingoworld");
            wc.type(WorldType.NORMAL);
            Bukkit.createWorld(wc);
            final WorldBorder wb = Bukkit.getWorld("bingoworld").getWorldBorder();
            wb.setCenter(0.0, 0.0);
            wb.setSize(8000.0, 8000L);
            wb.setWarningTime(5);
            wb.setDamageAmount(0.5);
            this.GameStarted = false;
            this.LobbyStatus = true;
            this.isRestarting = false;
            this.randomized = false;
            this.gotitems1 = 0;
            this.gotitems2 = 0;
            this.gotitems3 = 0;
            this.b1t1 = false;
            this.b2t1 = false;
            this.b3t1 = false;
            this.b4t1 = false;
            this.b5t1 = false;
            this.b6t1 = false;
            this.b7t1 = false;
            this.b8t1 = false;
            this.b9t1 = false;
            this.b1t2 = false;
            this.b2t2 = false;
            this.b3t2 = false;
            this.b4t2 = false;
            this.b5t2 = false;
            this.b6t2 = false;
            this.b7t2 = false;
            this.b8t2 = false;
            this.b9t2 = false;
            this.b1t3 = false;
            this.b2t3 = false;
            this.b3t3 = false;
            this.b4t3 = false;
            this.b5t3 = false;
            this.b6t3 = false;
            this.b7t3 = false;
            this.b8t3 = false;
            this.b9t3 = false;
            this.t1.clear();
            this.t2.clear();
            this.t3.clear();
            this.winner1 = null;
            this.winner2 = null;
            this.foundItemsT1.clear();
            this.foundItemsT2.clear();
            this.foundItemsT3.clear();
        }
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
    
    public void startGame() {
        for (final Player aplayer : Bukkit.getOnlinePlayers()) {
            if (!this.t1.contains(aplayer) && !this.t2.contains(aplayer) && !this.t3.contains(aplayer)) {
                if (this.t1.size() < 2) {
                    this.t1.add(aplayer);
                }
                else if (this.t2.size() < 2) {
                    this.t2.add(aplayer);
                }
                else if (this.t3.size() < 2) {
                    this.t3.add(aplayer);
                }
            }
            if (this.t1.contains(aplayer)) {
                aplayer.setPlayerListName("¡ì6" + aplayer.getName());
            }
            else if (this.t2.contains(aplayer)) {
                aplayer.setPlayerListName("¡ìc" + aplayer.getName());
            }
            else {
                if (!this.t3.contains(aplayer)) {
                    continue;
                }
                aplayer.setPlayerListName("¡ìd" + aplayer.getName());
            }
        }
        this.GameStarted = true;
        this.LobbyStatus = false;
        final int l1x = ThreadLocalRandom.current().nextInt(-3000, 3000);
        final int l1z = ThreadLocalRandom.current().nextInt(-3000, 3000);
        final int l2x = ThreadLocalRandom.current().nextInt(-3000, 3000);
        final int l2z = ThreadLocalRandom.current().nextInt(-3000, 3000);
        final int l3x = ThreadLocalRandom.current().nextInt(-3000, 3000);
        final int l3z = ThreadLocalRandom.current().nextInt(-3000, 3000);
        final Location l1 = new Location(Bukkit.getWorld("bingoworld"), (double)l1x, (double)Bukkit.getWorld("bingoworld").getHighestBlockAt(l1x, l1z).getY(), (double)l1z);
        final Location l2 = new Location(Bukkit.getWorld("bingoworld"), (double)l2x, (double)Bukkit.getWorld("bingoworld").getHighestBlockAt(l2x, l2z).getY(), (double)l2z);
        final Location l3 = new Location(Bukkit.getWorld("bingoworld"), (double)l3x, (double)Bukkit.getWorld("bingoworld").getHighestBlockAt(l3x, l3z).getY(), (double)l3z);
        Bukkit.broadcastMessage(String.valueOf(this.getCL().prefix) + " " + this.getCL().FollowingTeamTeleported);
        if (!this.t1.isEmpty()) {
            if (this.t1.size() == 1) {
                Bukkit.broadcastMessage(String.valueOf(this.getCL().prefix) + " " + this.getCL().T1OnePlayer.replace("%player%", this.t1.get(0).getName()));
            }
            else {
                Bukkit.broadcastMessage(String.valueOf(this.getCL().prefix) + " " + this.getCL().T1TwoPlayer.replace("%player1%", this.t1.get(0).getName()).replace("%player2%", this.t1.get(1).getName()));
            }
        }
        if (!this.t2.isEmpty()) {
            if (this.t2.size() == 1) {
                Bukkit.broadcastMessage(String.valueOf(this.getCL().prefix) + " " + this.getCL().T2OnePlayer.replace("%player%", this.t2.get(0).getName()));
            }
            else {
                Bukkit.broadcastMessage(String.valueOf(this.getCL().prefix) + " " + this.getCL().T2TwoPlayer.replace("%player1%", this.t2.get(0).getName()).replace("%player2%", this.t2.get(1).getName()));
            }
        }
        if (!this.t3.isEmpty()) {
            if (this.t3.size() == 1) {
                Bukkit.broadcastMessage(String.valueOf(this.getCL().prefix) + " " + this.getCL().T3OnePlayer.replace("%player%", this.t3.get(0).getName()));
            }
            else {
                Bukkit.broadcastMessage(String.valueOf(this.getCL().prefix) + " " + this.getCL().T3TwoPlayer.replace("%player1%", this.t3.get(0).getName()).replace("%player2%", this.t3.get(1).getName()));
            }
        }
        for (final Player tplayer : this.t1) {
            tplayer.teleport(l1);
        }
        for (final Player tplayer : this.t2) {
            tplayer.teleport(l2);
        }
        for (final Player tplayer : this.t3) {
            tplayer.teleport(l3);
        }
        for (final Player soundplayer : Bukkit.getOnlinePlayers()) {
            soundplayer.playSound(soundplayer.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.3f, 1.0f);
        }
        for (final Player player : Bukkit.getOnlinePlayers()) {
            player.setAllowFlight(false);
            player.getPlayer().setFlying(false);
            player.getPlayer().setInvulnerable(false);
            player.getPlayer().setCollidable(true);
            player.setHealth(20.0);
            player.setFoodLevel(20);
            player.getPlayer().getInventory().clear();
            final ItemStack boat = new ItemStack(Material.OAK_BOAT, 1);
            final ItemMeta boatmeta = boat.getItemMeta();
            boatmeta.setDisplayName(this.getCL().BoatName);
            boat.setItemMeta(boatmeta);
            player.getInventory().setItem(0, boat);
            player.sendMessage(String.valueOf(this.getCL().prefix) + " " + this.getCL().StartMessage);
            player.setPlayerListHeader(this.getCL().tablistheaderingame);
            final String foundt1 = String.valueOf(this.getCL().prefix) + " " + this.getCL().teamfounditemTeam1;
            final String foundt2 = String.valueOf(this.getCL().prefix) + " " + this.getCL().teamfounditemTeam2;
            final String foundt3 = String.valueOf(this.getCL().prefix) + " " + this.getCL().teamfounditemTeam3;
            new BukkitRunnable() {
                public void run() {
                    if (Main.this.t1.contains(player)) {
                        final String actionbar1 = Main.this.cl.ActionBar.replace("%gotitems%", String.valueOf(Main.this.gotitems1));
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionbar1));
                    }
                    else if (Main.this.t2.contains(player)) {
                        final String actionbar2 = Main.this.cl.ActionBar.replace("%gotitems%", String.valueOf(Main.this.gotitems2));
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionbar2));
                    }
                    else if (Main.this.t3.contains(player)) {
                        final String actionbar3 = Main.this.cl.ActionBar.replace("%gotitems%", String.valueOf(Main.this.gotitems3));
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionbar3));
                    }
                    for (final Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getInventory().containsAtLeast(Main.this.b1, 1)) {
                            if (Main.this.t1.contains(p)) {
                                if (!Main.this.b1t1) {
                                    Main.this.b1t1 = true;
                                    final Main this$0 = Main.this;
                                    ++this$0.gotitems1;
                                    Main.this.foundItemsT1.add(Main.this.b1);
                                	Bukkit.broadcastMessage(foundt1.replace("%item%", get_Item_name(Main.this.b1.getType().name())));
                                }
                            }
                            else if (Main.this.t2.contains(p)) {
                                if (!Main.this.b1t2) {
                                    Main.this.b1t2 = true;
                                    final Main this$2 = Main.this;
                                    ++this$2.gotitems2;
                                    Main.this.foundItemsT2.add(Main.this.b1);
                                    Bukkit.broadcastMessage(foundt2.replace("%item%", get_Item_name(Main.this.b1.getType().name())));
                                }
                            }
                            else if (Main.this.t3.contains(p) && !Main.this.b1t3) {
                                Main.this.b1t3 = true;
                                final Main this$3 = Main.this;
                                ++this$3.gotitems3;
                                Main.this.foundItemsT3.add(Main.this.b1);
                                Bukkit.broadcastMessage(foundt3.replace("%item%", get_Item_name(Main.this.b1.getType().name())));
                            }
                        }
                        if (p.getInventory().containsAtLeast(Main.this.b2, 1)) {
                            if (Main.this.t1.contains(p)) {
                                if (!Main.this.b2t1) {
                                    Main.this.b2t1 = true;
                                    final Main this$4 = Main.this;
                                    ++this$4.gotitems1;
                                    Main.this.foundItemsT1.add(Main.this.b2);
                                    Bukkit.broadcastMessage(foundt1.replace("%item%", get_Item_name(Main.this.b2.getType().name())));
                                }
                            }
                            else if (Main.this.t2.contains(p)) {
                                if (!Main.this.b2t2) {
                                    Main.this.b2t2 = true;
                                    final Main this$5 = Main.this;
                                    ++this$5.gotitems2;
                                    Main.this.foundItemsT2.add(Main.this.b2);
                                    Bukkit.broadcastMessage(foundt2.replace("%item%", get_Item_name(Main.this.b2.getType().name())));
                                }
                            }
                            else if (Main.this.t3.contains(p) && !Main.this.b2t3) {
                                Main.this.b2t3 = true;
                                final Main this$6 = Main.this;
                                ++this$6.gotitems3;
                                Main.this.foundItemsT3.add(Main.this.b2);
                                Bukkit.broadcastMessage(foundt3.replace("%item%", get_Item_name(Main.this.b2.getType().name())));
                            }
                        }
                        if (p.getInventory().containsAtLeast(Main.this.b3, 1)) {
                            if (Main.this.t1.contains(p)) {
                                if (!Main.this.b3t1) {
                                    Main.this.b3t1 = true;
                                    final Main this$7 = Main.this;
                                    ++this$7.gotitems1;
                                    Main.this.foundItemsT1.add(Main.this.b3);
                                    Bukkit.broadcastMessage(foundt1.replace("%item%", get_Item_name(Main.this.b3.getType().name())));
                                }
                            }
                            else if (Main.this.t2.contains(p)) {
                                if (!Main.this.b3t2) {
                                    Main.this.b3t2 = true;
                                    final Main this$8 = Main.this;
                                    ++this$8.gotitems2;
                                    Main.this.foundItemsT2.add(Main.this.b3);
                                    Bukkit.broadcastMessage(foundt2.replace("%item%", get_Item_name(Main.this.b3.getType().name())));
                                }
                            }
                            else if (Main.this.t3.contains(p) && !Main.this.b3t3) {
                                Main.this.b3t3 = true;
                                final Main this$9 = Main.this;
                                ++this$9.gotitems3;
                                Main.this.foundItemsT3.add(Main.this.b3);
                                Bukkit.broadcastMessage(foundt3.replace("%item%", get_Item_name(Main.this.b3.getType().name())));
                            }
                        }
                        if (p.getInventory().containsAtLeast(Main.this.b4, 1)) {
                            if (Main.this.t1.contains(p)) {
                                if (!Main.this.b4t1) {
                                    Main.this.b4t1 = true;
                                    final Main this$10 = Main.this;
                                    ++this$10.gotitems1;
                                    Main.this.foundItemsT1.add(Main.this.b4);
                                    Bukkit.broadcastMessage(foundt1.replace("%item%", get_Item_name(Main.this.b4.getType().name())));
                                }
                            }
                            else if (Main.this.t2.contains(p)) {
                                if (!Main.this.b4t2) {
                                    Main.this.b4t2 = true;
                                    final Main this$11 = Main.this;
                                    ++this$11.gotitems2;
                                    Main.this.foundItemsT2.add(Main.this.b4);
                                    Bukkit.broadcastMessage(foundt2.replace("%item%", get_Item_name(Main.this.b4.getType().name())));
                                }
                            }
                            else if (Main.this.t3.contains(p) && !Main.this.b4t3) {
                                Main.this.b4t3 = true;
                                final Main this$12 = Main.this;
                                ++this$12.gotitems3;
                                Main.this.foundItemsT3.add(Main.this.b4);
                                Bukkit.broadcastMessage(foundt3.replace("%item%", get_Item_name(Main.this.b4.getType().name())));
                            }
                        }
                        if (p.getInventory().containsAtLeast(Main.this.b5, 1)) {
                            if (Main.this.t1.contains(p)) {
                                if (!Main.this.b5t1) {
                                    Main.this.b5t1 = true;
                                    final Main this$13 = Main.this;
                                    ++this$13.gotitems1;
                                    Main.this.foundItemsT1.add(Main.this.b5);
                                    Bukkit.broadcastMessage(foundt1.replace("%item%", get_Item_name(Main.this.b5.getType().name())));
                                }
                            }
                            else if (Main.this.t2.contains(p)) {
                                if (!Main.this.b5t2) {
                                    Main.this.b5t2 = true;
                                    final Main this$14 = Main.this;
                                    ++this$14.gotitems2;
                                    Main.this.foundItemsT2.add(Main.this.b5);
                                    Bukkit.broadcastMessage(foundt2.replace("%item%", get_Item_name(Main.this.b5.getType().name())));
                                }
                            }
                            else if (Main.this.t3.contains(p) && !Main.this.b5t3) {
                                Main.this.b5t3 = true;
                                final Main this$15 = Main.this;
                                ++this$15.gotitems3;
                                Main.this.foundItemsT3.add(Main.this.b5);
                                Bukkit.broadcastMessage(foundt3.replace("%item%", get_Item_name(Main.this.b5.getType().name())));
                            }
                        }
                        if (p.getInventory().containsAtLeast(Main.this.b6, 1)) {
                            if (Main.this.t1.contains(p)) {
                                if (!Main.this.b6t1) {
                                    Main.this.b6t1 = true;
                                    final Main this$16 = Main.this;
                                    ++this$16.gotitems1;
                                    Main.this.foundItemsT1.add(Main.this.b6);
                                    Bukkit.broadcastMessage(foundt1.replace("%item%", get_Item_name(Main.this.b6.getType().name())));
                                }
                            }
                            else if (Main.this.t2.contains(p)) {
                                if (!Main.this.b6t2) {
                                    Main.this.b6t2 = true;
                                    final Main this$17 = Main.this;
                                    ++this$17.gotitems2;
                                    Main.this.foundItemsT2.add(Main.this.b6);
                                    Bukkit.broadcastMessage(foundt2.replace("%item%", get_Item_name(Main.this.b6.getType().name())));
                                }
                            }
                            else if (Main.this.t3.contains(p) && !Main.this.b6t3) {
                                Main.this.b6t3 = true;
                                final Main this$18 = Main.this;
                                ++this$18.gotitems3;
                                Main.this.foundItemsT3.add(Main.this.b6);
                                Bukkit.broadcastMessage(foundt3.replace("%item%", get_Item_name(Main.this.b6.getType().name())));
                            }
                        }
                        if (p.getInventory().containsAtLeast(Main.this.b7, 1)) {
                            if (Main.this.t1.contains(p)) {
                                if (!Main.this.b7t1) {
                                    Main.this.b7t1 = true;
                                    final Main this$19 = Main.this;
                                    ++this$19.gotitems1;
                                    Main.this.foundItemsT1.add(Main.this.b7);
                                    Bukkit.broadcastMessage(foundt1.replace("%item%", get_Item_name(Main.this.b7.getType().name())));
                                }
                            }
                            else if (Main.this.t2.contains(p)) {
                                if (!Main.this.b7t2) {
                                    Main.this.b7t2 = true;
                                    final Main this$20 = Main.this;
                                    ++this$20.gotitems2;
                                    Main.this.foundItemsT2.add(Main.this.b7);
                                    Bukkit.broadcastMessage(foundt2.replace("%item%", get_Item_name(Main.this.b7.getType().name())));
                                }
                            }
                            else if (Main.this.t3.contains(p) && !Main.this.b7t3) {
                                Main.this.b7t3 = true;
                                final Main this$21 = Main.this;
                                ++this$21.gotitems3;
                                Main.this.foundItemsT3.add(Main.this.b7);
                                Bukkit.broadcastMessage(foundt3.replace("%item%", get_Item_name(Main.this.b7.getType().name())));
                            }
                        }
                        if (p.getInventory().containsAtLeast(Main.this.b8, 1)) {
                            if (Main.this.t1.contains(p)) {
                                if (!Main.this.b8t1) {
                                    Main.this.b8t1 = true;
                                    final Main this$22 = Main.this;
                                    ++this$22.gotitems1;
                                    Main.this.foundItemsT1.add(Main.this.b8);
                                    Bukkit.broadcastMessage(foundt1.replace("%item%", get_Item_name(Main.this.b8.getType().name())));
                                }
                            }
                            else if (Main.this.t2.contains(p)) {
                                if (!Main.this.b8t2) {
                                    Main.this.b8t2 = true;
                                    final Main this$23 = Main.this;
                                    ++this$23.gotitems2;
                                    Main.this.foundItemsT2.add(Main.this.b8);
                                    Bukkit.broadcastMessage(foundt2.replace("%item%", get_Item_name(Main.this.b8.getType().name())));
                                }
                            }
                            else if (Main.this.t3.contains(p) && !Main.this.b8t3) {
                                Main.this.b8t3 = true;
                                final Main this$24 = Main.this;
                                ++this$24.gotitems3;
                                Main.this.foundItemsT3.add(Main.this.b8);
                                Bukkit.broadcastMessage(foundt3.replace("%item%", get_Item_name(Main.this.b8.getType().name())));
                            }
                        }
                        if (p.getInventory().containsAtLeast(Main.this.b9, 1)) {
                            if (Main.this.t1.contains(p)) {
                                if (!Main.this.b9t1) {
                                    Main.this.b9t1 = true;
                                    final Main this$25 = Main.this;
                                    ++this$25.gotitems1;
                                    Main.this.foundItemsT1.add(Main.this.b9);
                                    Bukkit.broadcastMessage(foundt1.replace("%item%", get_Item_name(Main.this.b9.getType().name())));
                                }
                            }
                            else if (Main.this.t2.contains(p)) {
                                if (!Main.this.b9t2) {
                                    Main.this.b9t2 = true;
                                    final Main this$26 = Main.this;
                                    ++this$26.gotitems2;
                                    Main.this.foundItemsT2.add(Main.this.b9);
                                    Bukkit.broadcastMessage(foundt2.replace("%item%", get_Item_name(Main.this.b9.getType().name())));
                                }
                            }
                            else if (Main.this.t3.contains(p) && !Main.this.b9t3) {
                                Main.this.b9t3 = true;
                                final Main this$27 = Main.this;
                                ++this$27.gotitems3;
                                Main.this.foundItemsT3.add(Main.this.b9);
                                Bukkit.broadcastMessage(foundt3.replace("%item%", get_Item_name(Main.this.b9.getType().name())));
                            }
                        }
                        if (Main.this.gotitems1 == 9) {
                            if (Main.this.t1.size() == 1) {
                                Main.this.winner1 = Main.this.t1.get(0).getName();
                            }
                            else {
                                Main.this.winner1 = Main.this.t1.get(0).getName();
                                Main.this.winner2 = Main.this.t1.get(1).getName();
                            }
                            Main.this.isRestarting = true;
                            Main.this.restartGame();
                        }
                        else if (Main.this.gotitems2 == 9) {
                            if (Main.this.t2.size() == 1) {
                                Main.this.winner1 = Main.this.t2.get(0).getName();
                            }
                            else {
                                Main.this.winner1 = Main.this.t2.get(0).getName();
                                Main.this.winner2 = Main.this.t2.get(1).getName();
                            }
                            Main.this.isRestarting = true;
                            Main.this.restartGame();
                        }
                        else {
                            if (Main.this.gotitems3 != 9) {
                                continue;
                            }
                            if (Main.this.t3.size() == 1) {
                                Main.this.winner1 = Main.this.t3.get(0).getName();
                            }
                            else {
                                Main.this.winner1 = Main.this.t3.get(0).getName();
                                Main.this.winner2 = Main.this.t3.get(1).getName();
                            }
                            Main.this.isRestarting = true;
                            Main.this.restartGame();
                        }
                    }
                }
            }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Bingo"), 0L, 20L);
        }
    }
    
    public static int getRandomInt(final int min, final int max) {
        final Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    public static ArrayList<Integer> getRandomNonRepeatingIntegers(final int size, final int min, final int max) {
        final ArrayList<Integer> numbers = new ArrayList<Integer>();
        while (numbers.size() < size) {
            final int random = getRandomInt(min, max);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        return numbers;
    }
    
    public void getRandomItems() {
        this.sandstonestairs = new ItemStack(Material.SANDSTONE_STAIRS);
        this.cactus = new ItemStack(Material.CACTUS);
        this.limewool = new ItemStack(Material.LIME_WOOL);
        this.tnt = new ItemStack(Material.TNT);
        this.emerald = new ItemStack(Material.EMERALD);
        this.detectorrail = new ItemStack(Material.DETECTOR_RAIL);
        this.goldenapple = new ItemStack(Material.GOLDEN_APPLE);
        this.fletchingtable = new ItemStack(Material.FLETCHING_TABLE);
        this.enchantingtable = new ItemStack(Material.ENCHANTING_TABLE);
        this.bucketwithsalmon = new ItemStack(Material.SALMON_BUCKET);
        this.itemframe = new ItemStack(Material.ITEM_FRAME);
        this.anvil = new ItemStack(Material.ANVIL);
        this.clock = new ItemStack(Material.CLOCK);
        this.armorstand = new ItemStack(Material.ARMOR_STAND);
        this.poweredrail = new ItemStack(Material.POWERED_RAIL);
        this.activatorrail = new ItemStack(Material.ACTIVATOR_RAIL);
        this.carrotonstick = new ItemStack(Material.CARROT_ON_A_STICK);
        this.jukebox = new ItemStack(Material.JUKEBOX);
        this.lectern = new ItemStack(Material.LECTERN);
        this.lilypad = new ItemStack(Material.LILY_PAD);
        this.bell = new ItemStack(Material.BELL);
        this.obsidian = new ItemStack(Material.OBSIDIAN);
        this.painting = new ItemStack(Material.PAINTING);
        this.crossbow = new ItemStack(Material.CROSSBOW);
        this.diamondchestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        this.compass = new ItemStack(Material.COMPASS);
        this.driedkelp = new ItemStack(Material.DRIED_KELP);
        this.milkbucket = new ItemStack(Material.MILK_BUCKET);
        this.mushroomstew = new ItemStack(Material.MUSHROOM_STEW);
        this.enderpearl = new ItemStack(Material.ENDER_PEARL);
        this.bookshelf = new ItemStack(Material.BOOKSHELF);
        this.cookedcod = new ItemStack(Material.COOKED_COD);
        this.diamondleggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        this.glassbottle = new ItemStack(Material.GLASS_BOTTLE);
        this.cauldron = new ItemStack(Material.CAULDRON);
        this.goldpressureplate = new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
        this.piston = new ItemStack(Material.PISTON);
        this.flintandsteel = new ItemStack(Material.FLINT_AND_STEEL);
        this.diamond = new ItemStack(Material.DIAMOND);
        this.banner = new ItemStack(Material.WHITE_BANNER);
        this.shield = new ItemStack(Material.SHIELD);
        this.granitestairs = new ItemStack(Material.GRANITE_STAIRS);
        this.paper = new ItemStack(Material.PAPER);
        this.pumpkin = new ItemStack(Material.PUMPKIN);
        this.itemlist.add(this.sandstonestairs);
        this.itemlist.add(this.cactus);
        this.itemlist.add(this.limewool);
        this.itemlist.add(this.tnt);
        this.itemlist.add(this.emerald);
        this.itemlist.add(this.detectorrail);
        this.itemlist.add(this.goldenapple);
        this.itemlist.add(this.fletchingtable);
        this.itemlist.add(this.enchantingtable);
        this.itemlist.add(this.bucketwithsalmon);
        this.itemlist.add(this.itemframe);
        this.itemlist.add(this.anvil);
        this.itemlist.add(this.clock);
        this.itemlist.add(this.armorstand);
        this.itemlist.add(this.poweredrail);
        this.itemlist.add(this.activatorrail);
        this.itemlist.add(this.carrotonstick);
        this.itemlist.add(this.jukebox);
        this.itemlist.add(this.lectern);
        this.itemlist.add(this.lilypad);
        this.itemlist.add(this.bell);
        this.itemlist.add(this.obsidian);
        this.itemlist.add(this.painting);
        this.itemlist.add(this.crossbow);
        this.itemlist.add(this.diamondchestplate);
        this.itemlist.add(this.compass);
        this.itemlist.add(this.driedkelp);
        this.itemlist.add(this.milkbucket);
        this.itemlist.add(this.mushroomstew);
        this.itemlist.add(this.enderpearl);
        this.itemlist.add(this.bookshelf);
        this.itemlist.add(this.cookedcod);
        this.itemlist.add(this.diamondleggings);
        this.itemlist.add(this.glassbottle);
        this.itemlist.add(this.cauldron);
        this.itemlist.add(this.goldpressureplate);
        this.itemlist.add(this.piston);
        this.itemlist.add(this.flintandsteel);
        this.itemlist.add(this.diamond);
        this.itemlist.add(this.banner);
        this.itemlist.add(this.shield);
        this.itemlist.add(this.granitestairs);
        this.itemlist.add(this.paper);
        this.itemlist.add(this.pumpkin);
        final ArrayList<Integer> list = getRandomNonRepeatingIntegers(9, 0, 43);
        this.b1 = this.itemlist.get(list.get(0));
        this.b2 = this.itemlist.get(list.get(1));
        this.b3 = this.itemlist.get(list.get(2));
        this.b4 = this.itemlist.get(list.get(3));
        this.b5 = this.itemlist.get(list.get(4));
        this.b6 = this.itemlist.get(list.get(5));
        this.b7 = this.itemlist.get(list.get(6));
        this.b8 = this.itemlist.get(list.get(7));
        this.b9 = this.itemlist.get(list.get(8));
        this.randomized = true;
    }
}
