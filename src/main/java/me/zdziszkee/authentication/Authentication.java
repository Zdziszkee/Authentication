package me.zdziszkee.authentication;

import co.aikar.commands.PaperCommandManager;
import me.zdziszkee.authentication.commands.TextBookCommand;
import me.zdziszkee.authentication.configuration.GeneralConfiguration;
import me.zdziszkee.authentication.configuration.PatternFinderAuthGUIConfiguration;
import me.zdziszkee.authentication.configuration.PinPadAuthGUIConfiguration;
import me.zdziszkee.authentication.configuration.PuzzleAuthGUIConfiguration;
import me.zdziszkee.authentication.gson.com.twodevsstudio.simplejsonconfig.SimpleJSONConfig;
import me.zdziszkee.authentication.gson.com.twodevsstudio.simplejsonconfig.api.Config;
import me.zdziszkee.authentication.gui.GUIListener;
import me.zdziszkee.authentication.gui.auth.PlayerKicker;
import me.zdziszkee.authentication.gui.space.PlayerDataCache;
import me.zdziszkee.authentication.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Authentication extends JavaPlugin {
    private PatternFinderAuthGUIConfiguration patternFinderAuthGUIConfiguration;
    private PuzzleAuthGUIConfiguration puzzleAuthGUIConfiguration;
    private PinPadAuthGUIConfiguration pinPadAuthGUIConfiguration;
    private GeneralConfiguration generalConfiguration;
    private PlayerKicker playerKicker;
    private static Authentication authentication;
    private final PlayerDataCache playerDataCache = new PlayerDataCache();

    @Override
    public void onEnable() {
        SimpleJSONConfig.INSTANCE.register(this);
        patternFinderAuthGUIConfiguration = Config.getConfig(PatternFinderAuthGUIConfiguration.class);
        pinPadAuthGUIConfiguration = Config.getConfig(PinPadAuthGUIConfiguration.class);
        puzzleAuthGUIConfiguration = Config.getConfig(PuzzleAuthGUIConfiguration.class);
        generalConfiguration = Config.getConfig(GeneralConfiguration.class);
        playerKicker = new PlayerKicker(generalConfiguration);
        Bukkit.broadcastMessage("Authentication has been enabled!");
        authentication = this;
        registerEvents();
        registerCommands();
    }


    public static Authentication getInstance() {
        return authentication;
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(patternFinderAuthGUIConfiguration, pinPadAuthGUIConfiguration, puzzleAuthGUIConfiguration, generalConfiguration, playerKicker), this);
    }

    private void registerCommands() {
        PaperCommandManager paperCommandManager = new PaperCommandManager(this);
        paperCommandManager.registerCommand(new TextBookCommand(playerDataCache, generalConfiguration));
    }

    @Override
    public void onDisable() {
        Bukkit.broadcastMessage("Authentication has been disabled!");
    }
}
