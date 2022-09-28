package com.dwcfactions;

import com.dwcfactions.commands.FactionCreate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;


public final class DWCFactions extends JavaPlugin {
    private Scoreboard factionsBoard;
    private static DWCFactions instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;
        this.factionsBoard = Bukkit.getScoreboardManager().getNewScoreboard();

        getServer().getPluginCommand("FactionCreate").setExecutor(new FactionCreate());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
