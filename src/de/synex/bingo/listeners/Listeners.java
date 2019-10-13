package de.synex.bingo.listeners;

import de.synex.bingo.main.*;
import de.synex.bingo.util.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;
import org.bukkit.scheduler.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.world.*;
import org.bukkit.event.server.*;
import org.bukkit.event.entity.*;
import io.netty.util.internal.*;
import org.bukkit.plugin.*;
import org.bukkit.event.player.*;
import org.bukkit.scoreboard.*;

public class Listeners implements Listener
{
    Main main;
    Sideboard sboard;
    ItemStack t1is;
    ItemStack t2is;
    ItemStack t3is;
    ItemStack ph;
    ArrayList<Player> livingplayer;
    int seconds;
    
    public Listeners(final Main instance) {
        this.livingplayer = new ArrayList<Player>();
        this.seconds = 10;
        this.main = instance;
    }
    
    public Listeners(final Sideboard sb) {
        this.livingplayer = new ArrayList<Player>();
        this.seconds = 10;
        this.sboard = sb;
    }
    
    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        if (this.main.LobbyStatus) {
            this.t1is = new ItemStack(Material.ORANGE_TULIP, 1);
            final ItemMeta t1im = this.t1is.getItemMeta();
            t1im.setDisplayName("¡ì6Team 1");
            this.t1is.setItemMeta(t1im);
            this.t2is = new ItemStack(Material.RED_TULIP, 1);
            final ItemMeta t2im = this.t1is.getItemMeta();
            t2im.setDisplayName("¡ìcTeam 2");
            this.t2is.setItemMeta(t2im);
            this.t3is = new ItemStack(Material.PINK_TULIP, 1);
            final ItemMeta t3im = this.t1is.getItemMeta();
            t3im.setDisplayName("¡ìdTeam 3");
            this.t3is.setItemMeta(t3im);
            final Inventory teamselector = Bukkit.createInventory((InventoryHolder)null, 9, this.main.getCL().teamselectorInventoryTitle);
            this.ph = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
            final ItemMeta phim = this.ph.getItemMeta();
            phim.setDisplayName(" ");
            this.ph.setItemMeta(phim);
            teamselector.setItem(0, this.ph);
            teamselector.setItem(1, this.ph);
            teamselector.setItem(2, this.ph);
            teamselector.setItem(3, this.t1is);
            teamselector.setItem(4, this.t2is);
            teamselector.setItem(5, this.t3is);
            teamselector.setItem(6, this.ph);
            teamselector.setItem(7, this.ph);
            teamselector.setItem(8, this.ph);
            if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getPlayer().getInventory().getItemInMainHand().getType().equals((Object)Material.NETHER_STAR)) {
                if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(this.main.getCL().teamselection)) {
                    e.getPlayer().openInventory(teamselector);
                }
                else {
                    e.setCancelled(true);
                }
            }
        }
        if (e.getPlayer().isInvulnerable()) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        e.getPlayer().getInventory().clear();
        e.getPlayer().setPlayerListFooter(this.main.getCL().tablistfooter);
        if (this.main.t1.contains(e.getPlayer())) {
            e.getPlayer().setPlayerListName(String.valueOf(ChatColor.translateAlternateColorCodes('&', this.main.getCL().TablistTeam1Colour)) + e.getPlayer().getName());
        }
        else if (this.main.t2.contains(e.getPlayer())) {
            e.getPlayer().setPlayerListName(String.valueOf(ChatColor.translateAlternateColorCodes('&', this.main.getCL().TablistTeam2Colour)) + e.getPlayer().getName());
        }
        else if (this.main.t3.contains(e.getPlayer())) {
            e.getPlayer().setPlayerListName(String.valueOf(ChatColor.translateAlternateColorCodes('&', this.main.getCL().TablistTeam3Colour)) + e.getPlayer().getName());
        }
        else if (e.getPlayer().hasPermission("bingo.gamemaster")) {
            e.getPlayer().setPlayerListName(String.valueOf(ChatColor.translateAlternateColorCodes('&', this.main.getCL().TablistGamemasterColour)) + e.getPlayer().getName());
        }
        else {
            e.getPlayer().setPlayerListName(String.valueOf(ChatColor.translateAlternateColorCodes('&', this.main.getCL().TablistDefaultUserColour)) + e.getPlayer().getName());
        }
        if (!this.main.isRestarting) {
            if (this.main.GameStarted) {
                e.getPlayer().setPlayerListHeader(this.main.getCL().tablistheaderingame);
                final Location specloc = Bukkit.getWorld("bingoworld").getSpawnLocation();
                e.getPlayer().teleport(specloc);
                e.getPlayer().setAllowFlight(true);
                e.getPlayer().setFlying(true);
                e.getPlayer().setInvulnerable(true);
                e.getPlayer().setCollidable(false);
                for (final Player players : Bukkit.getOnlinePlayers()) {
                    players.hidePlayer(e.getPlayer());
                }
                e.getPlayer().setGameMode(GameMode.SPECTATOR);
                e.setJoinMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().spectatorjoined.replace("%player%", e.getPlayer().getName()));
            }
            else {
                e.getPlayer().setGameMode(GameMode.SURVIVAL);
                e.getPlayer().setHealth(20.0);
                e.getPlayer().setFoodLevel(20);
                e.getPlayer().setPlayerListHeader(this.main.getCL().tablistheaderlobby);
                final Location startloc = Bukkit.getWorld("world").getSpawnLocation();
                e.getPlayer().teleport(startloc);
                if (Bukkit.getServer().getOnlinePlayers().size() >= 7) {
                    e.setJoinMessage((String)null);
                    e.getPlayer().kickPlayer(this.main.getCL().lobbyisfullkick);
                }
                else {
                    this.setLobbyScoreboardWithTeam(e.getPlayer());
                    e.setJoinMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().playerjoined.replace("%player%", e.getPlayer().getName()));
                    if (Bukkit.getServer().getOnlinePlayers().size() == 6) {
                        Bukkit.broadcastMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().gamewillstart);
                        new BukkitRunnable() {
                            public void run() {
                                if (Listeners.this.seconds != 0) {
                                    Bukkit.broadcastMessage(String.valueOf(Listeners.this.main.getCL().prefix) + " " + Listeners.this.main.getCL().gamestartsin.replace("%seconds%", String.valueOf(Listeners.this.seconds)));
                                    for (final Player soundplayer : Bukkit.getOnlinePlayers()) {
                                        soundplayer.playSound(soundplayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.3f, 1.0f);
                                    }
                                    final Listeners this$0 = Listeners.this;
                                    --this$0.seconds;
                                }
                                else {
                                    Bukkit.broadcastMessage(String.valueOf(Listeners.this.main.getCL().prefix) + " " + Listeners.this.main.getCL().gamestarts);
                                    this.cancel();
                                    Listeners.this.seconds = 10;
                                    Listeners.this.main.startGame();
                                }
                            }
                        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Bingo"), 0L, 20L);
                    }
                }
                final ItemStack is = new ItemStack(Material.NETHER_STAR, 1);
                final ItemMeta im = is.getItemMeta();
                im.setDisplayName(this.main.getCL().teamselection);
                is.setItemMeta(im);
                e.getPlayer().getInventory().setItem(4, is);
                e.getPlayer().setAllowFlight(true);
                e.getPlayer().setFlying(true);
                e.getPlayer().setInvulnerable(true);
                e.getPlayer().setCollidable(false);
            }
        }
        else {
            e.getPlayer().kickPlayer(this.main.getCL().roundisrestartingkick);
        }
    }
    
    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent e) {
        if (this.main.LobbyStatus) {
            e.setCancelled(true);
        }
        else if (e.getPlayer().isInvulnerable()) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent e) {
        if (this.main.LobbyStatus) {
            e.setCancelled(true);
        }
        else if (e.getPlayer().isInvulnerable()) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onDamage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && (e.getEntity().isInvulnerable() || this.main.LobbyStatus)) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onDamage2(final EntityDamageByEntityEvent e) {
        final Entity p = e.getDamager();
        if (p instanceof Player) {
            if (p.isInvulnerable() || this.main.LobbyStatus) {
                e.setCancelled(true);
            }
            else {
                if (!this.main.t1.isEmpty() && this.main.t1.size() == 2) {
                    if (this.main.t1.get(0).equals(e.getDamager()) && this.main.t1.get(1).equals(e.getEntity())) {
                        e.setCancelled(true);
                    }
                    if (this.main.t1.get(1).equals(e.getDamager()) && this.main.t1.get(0).equals(e.getEntity())) {
                        e.setCancelled(true);
                    }
                }
                if (!this.main.t2.isEmpty() && this.main.t2.size() == 2) {
                    if (this.main.t2.get(0).equals(e.getDamager()) && this.main.t2.get(1).equals(e.getEntity())) {
                        e.setCancelled(true);
                    }
                    if (this.main.t2.get(1).equals(e.getDamager()) && this.main.t2.get(0).equals(e.getEntity())) {
                        e.setCancelled(true);
                    }
                }
                if (!this.main.t3.isEmpty() && this.main.t3.size() == 2) {
                    if (this.main.t3.get(0).equals(e.getDamager()) && this.main.t3.get(1).equals(e.getEntity())) {
                        e.setCancelled(true);
                    }
                    if (this.main.t3.get(1).equals(e.getDamager()) && this.main.t3.get(0).equals(e.getEntity())) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        if (e.getView().getTitle() == this.main.getCL().bingoitemsinround) {
            e.setCancelled(true);
        }
        final Player p = (Player)e.getWhoClicked();
        this.t1is = new ItemStack(Material.ORANGE_TULIP, 1);
        final ItemMeta t1im = this.t1is.getItemMeta();
        t1im.setDisplayName("¡ì6Team 1");
        this.t1is.setItemMeta(t1im);
        this.t2is = new ItemStack(Material.RED_TULIP, 1);
        final ItemMeta t2im = this.t1is.getItemMeta();
        t2im.setDisplayName("¡ìcTeam 2");
        this.t2is.setItemMeta(t2im);
        this.t3is = new ItemStack(Material.PINK_TULIP, 1);
        final ItemMeta t3im = this.t1is.getItemMeta();
        t3im.setDisplayName("¡ìdTeam 3");
        this.t3is.setItemMeta(t3im);
        if (this.main.LobbyStatus) {
            if (e.getCurrentItem() == null) {
                e.setCancelled(true);
            }
            else if (e.getCurrentItem().getType().equals((Object)Material.ORANGE_TULIP)) {
                if (this.main.t1.size() < 2) {
                    if (!this.main.t1.contains(p)) {
                        p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().jointeam1);
                        this.main.t2.remove(p);
                        this.main.t3.remove(p);
                        this.main.t1.add(p);
                    }
                    else {
                        p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().alreadyteam1);
                    }
                    this.setLobbyScoreboardWithTeam(p);
                }
                else if (this.main.t1.contains(p)) {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().alreadyteam1);
                }
                else {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().team1full);
                }
                p.closeInventory();
            }
            else if (e.getCurrentItem().getType().equals((Object)Material.RED_TULIP)) {
                if (this.main.t2.size() < 2) {
                    if (!this.main.t2.contains(p)) {
                        p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().jointeam2);
                        this.main.t1.remove(p);
                        this.main.t3.remove(p);
                        this.main.t2.add(p);
                    }
                    else {
                        p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().alreadyteam2);
                    }
                    this.setLobbyScoreboardWithTeam(p);
                }
                else if (this.main.t2.contains(p)) {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().alreadyteam2);
                }
                else {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().team2full);
                }
                p.closeInventory();
            }
            else if (e.getCurrentItem().getType().equals((Object)Material.PINK_TULIP)) {
                if (this.main.t3.size() < 2) {
                    if (!this.main.t3.contains(p)) {
                        p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().jointeam3);
                        this.main.t1.remove(p);
                        this.main.t2.remove(p);
                        this.main.t3.add(p);
                    }
                    else {
                        p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().alreadyteam3);
                    }
                    this.setLobbyScoreboardWithTeam(p);
                }
                else if (this.main.t3.contains(p)) {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().alreadyteam3);
                }
                else {
                    p.sendMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().team3full);
                }
                p.closeInventory();
            }
            else {
                e.setCancelled(true);
            }
        }
        else if (e.getWhoClicked().isInvulnerable()) {
            e.setCancelled(true);
        }
        if (this.main.t1.contains(p)) {
            p.setPlayerListName(String.valueOf(ChatColor.translateAlternateColorCodes('&', this.main.getCL().TablistTeam1Colour)) + p.getName());
        }
        else if (this.main.t2.contains(p)) {
            p.setPlayerListName(String.valueOf(ChatColor.translateAlternateColorCodes('&', this.main.getCL().TablistTeam2Colour)) + p.getName());
        }
        else if (this.main.t3.contains(p)) {
            p.setPlayerListName(String.valueOf(ChatColor.translateAlternateColorCodes('&', this.main.getCL().TablistTeam3Colour)) + p.getName());
        }
    }
    
    @EventHandler
    public void onTarget(final EntityTargetLivingEntityEvent e) {
        if (e.getTarget() instanceof Player && (e.getTarget().isInvulnerable() || this.main.LobbyStatus)) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onCreation(final PortalCreateEvent e) {
        e.setCancelled(true);
    }
    
    @EventHandler
    public void onPing(final ServerListPingEvent e) {
        if (!this.main.GameStarted) {
            e.setMaxPlayers(6);
            e.setMotd(String.valueOf(this.main.getCL().modtLobbyRow1) + "\n" + this.main.getCL().modtLobbyRow2);
        }
        else if (this.main.GameStarted) {
            e.setMaxPlayers(15);
            e.setMotd(String.valueOf(this.main.getCL().modtInGameRow1) + "\n" + this.main.getCL().modtInGameRow2);
        }
    }
    
    @EventHandler
    public void onFoodChange(final FoodLevelChangeEvent e) {
        final Player p = (Player)e.getEntity();
        if (this.main.LobbyStatus || p.isInvulnerable()) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onDeath(final PlayerDeathEvent e) {
        e.getDrops().clear();
        e.setDeathMessage((String)null);
    }
    
    @EventHandler
    public void onRespawn(final PlayerRespawnEvent e) {
        final int l1x = ThreadLocalRandom.current().nextInt(-3000, 3000);
        final int l1z = ThreadLocalRandom.current().nextInt(-3000, 3000);
        final Location l1 = new Location(Bukkit.getWorld("bingoworld"), (double)l1x, (double)Bukkit.getWorld("bingoworld").getHighestBlockAt(l1x, l1z).getY(), (double)l1z);
        e.setRespawnLocation(l1);
        e.getPlayer().teleport(l1);
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, 0.3f, 1.0f);
        Bukkit.broadcastMessage(this.main.getCL().deathmessage.replace("%player%", e.getPlayer().getName()));
        final ItemStack boat = new ItemStack(Material.OAK_BOAT, 1);
        final ItemMeta boatmeta = boat.getItemMeta();
        boatmeta.setDisplayName(this.main.getCL().BoatName);
        boat.setItemMeta(boatmeta);
        e.getPlayer().getInventory().setItem(0, boat);
    }
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        if (!this.main.GameStarted && !this.main.isRestarting) {
            if (this.main.t1.contains(e.getPlayer())) {
                this.main.t1.remove(e.getPlayer());
            }
            else if (this.main.t2.contains(e.getPlayer())) {
                this.main.t2.remove(e.getPlayer());
            }
            else if (this.main.t3.contains(e.getPlayer())) {
                this.main.t3.remove(e.getPlayer());
            }
            e.setQuitMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().leftserverlobby.replace("%player%", e.getPlayer().getName()));
        }
        if (!this.main.isRestarting && this.main.GameStarted) {
            if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                e.setQuitMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().leftserveringame.replace("%player%", e.getPlayer().getName()));
            }
            else {
                e.setQuitMessage(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().leftserveringamespectator.replace("%player%", e.getPlayer().getName()));
            }
            for (final Player player : Bukkit.getOnlinePlayers()) {
                final Main main = this.main;
                ++main.players;
                if (player.getGameMode() == GameMode.SPECTATOR) {
                    final Main main2 = this.main;
                    ++main2.spectator;
                }
            }
            final Main main3 = this.main;
            --main3.players;
            if (this.main.spectator == this.main.players) {
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    if (this.main.t1.contains(player)) {
                        this.main.t1.remove(player);
                    }
                    else if (this.main.t2.contains(player)) {
                        this.main.t2.remove(player);
                    }
                    else if (this.main.t3.contains(player)) {
                        this.main.t3.remove(player);
                    }
                    player.kickPlayer(String.valueOf(this.main.getCL().prefix) + " " + this.main.getCL().gameisrestarting);
                }
                new BukkitRunnable() {
                    public void run() {
                        Listeners.this.main.manuallyRestarting();
                    }
                }.runTaskLater((Plugin)Main.getPlugin(), 80L);
            }
            this.main.players = 0;
            this.main.spectator = 0;
        }
    }
    
    @EventHandler
    public void onAsyncChat(final AsyncPlayerChatEvent e) {
        if (e.getPlayer().hasPermission("bingo.gamemaster")) {
            if (this.main.t1.contains(e.getPlayer())) {
                e.setFormat(this.main.getCL().chatformatTeam1.replace("%player%", e.getPlayer().getName()).replace("%message%", e.getMessage()));
            }
            else if (this.main.t2.contains(e.getPlayer())) {
                e.setFormat(this.main.getCL().chatformatTeam2.replace("%player%", e.getPlayer().getName()).replace("%message%", e.getMessage()));
            }
            else if (this.main.t3.contains(e.getPlayer())) {
                e.setFormat(this.main.getCL().chatformatTeam3.replace("%player%", e.getPlayer().getName()).replace("%message%", e.getMessage()));
            }
            else {
                e.setFormat(this.main.getCL().chatformatwithoutteam.replace("%player%", String.valueOf(this.main.getCL().chatcolourGamemaster) + e.getPlayer().getName()).replace("%message%", e.getMessage()));
            }
        }
        else if (this.main.t1.contains(e.getPlayer())) {
            e.setFormat(this.main.getCL().chatformatTeam1.replace("%player%", e.getPlayer().getName()).replace("%message%", e.getMessage()));
        }
        else if (this.main.t2.contains(e.getPlayer())) {
            e.setFormat(this.main.getCL().chatformatTeam2.replace("%player%", e.getPlayer().getName()).replace("%message%", e.getMessage()));
        }
        else if (this.main.t3.contains(e.getPlayer())) {
            e.setFormat(this.main.getCL().chatformatTeam3.replace("%player%", e.getPlayer().getName()).replace("%message%", e.getMessage()));
        }
        else {
            e.setFormat(this.main.getCL().chatformatwithoutteam.replace("%player%", String.valueOf(this.main.getCL().chatcolourDefaultUser) + e.getPlayer().getName()).replace("%message%", e.getMessage()));
        }
    }
    
    @EventHandler
    public void onDrop(final PlayerDropItemEvent e) {
        if (this.main.LobbyStatus || e.getPlayer().isInvulnerable()) {
            e.setCancelled(true);
        }
    }
    
    public void setLobbyScoreboardWithTeam(final Player p) {
        final Scoreboard lobbyboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective obj = lobbyboard.registerNewObjective("bingo", "dummy", this.main.getCL().scoreboardTitle);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        final Score placeholder2 = obj.getScore(ChatColor.GREEN + " " + ChatColor.WHITE);
        placeholder2.setScore(45);
        obj.getScore(ChatColor.GREEN + " " + ChatColor.WHITE).setScore(45);
        final Score yourTeam = obj.getScore(this.main.getCL().scoreboardYourTeam);
        yourTeam.setScore(44);
        if (this.main.t1.contains(p)) {
            final Team team = lobbyboard.registerNewTeam("team");
            team.addEntry(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.WHITE).toString());
            team.setPrefix(this.main.getCL().scoreboardTeam1);
            obj.getScore(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.WHITE).toString()).setScore(44);
        }
        else if (this.main.t2.contains(p)) {
            final Team team = lobbyboard.registerNewTeam("team");
            team.addEntry(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.WHITE).toString());
            team.setPrefix(this.main.getCL().scoreboardTeam2);
            obj.getScore(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.WHITE).toString()).setScore(44);
        }
        else if (this.main.t3.contains(p)) {
            final Team team = lobbyboard.registerNewTeam("team");
            team.addEntry(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.WHITE).toString());
            team.setPrefix(this.main.getCL().scoreboardTeam3);
            obj.getScore(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.WHITE).toString()).setScore(44);
        }
        else {
            final Team team = lobbyboard.registerNewTeam("team");
            team.addEntry(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.WHITE).toString());
            team.setPrefix(this.main.getCL().scoreboardNoTeam);
            obj.getScore(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.WHITE).toString()).setScore(44);
        }
        p.setScoreboard(lobbyboard);
    }
}
