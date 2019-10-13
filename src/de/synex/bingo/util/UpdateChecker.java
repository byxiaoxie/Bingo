package de.synex.bingo.util;

import org.bukkit.plugin.*;
import org.bukkit.util.*;
import org.bukkit.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class UpdateChecker
{
    private Plugin plugin;
    private int resourceId;
    
    public UpdateChecker(final Plugin plugin, final int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }
}
