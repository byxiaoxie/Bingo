package de.synex.bingo.util;

import de.synex.bingo.main.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.scoreboard.*;

public class Sideboard
{
    Main main;
    
    public Sideboard(final Main pl) {
        this.main = pl;
    }
    
    public void setLobbyScoreboard(final Player p) {
        final Scoreboard lobbyboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective obj = lobbyboard.registerNewObjective("¡ì2Bingo!", "dummy", "¡ì2Bingo! - Lobby");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        final Score yourTeam = obj.getScore("¡ìaDein Team:");
        yourTeam.setScore(2);
        final Team team = lobbyboard.registerNewTeam("team");
        team.addEntry(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.WHITE).toString());
        team.setPrefix("¡ìcDu bist in keinem Team");
        obj.getScore(new StringBuilder().append(ChatColor.AQUA).append(ChatColor.WHITE).toString()).setScore(2);
        p.setScoreboard(lobbyboard);
    }
}
