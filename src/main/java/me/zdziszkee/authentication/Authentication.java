package me.zdziszkee.authentication;

import co.aikar.commands.PaperCommandManager;
import com.twodevsstudio.simplejsonconfig.SimpleJSONConfig;
import com.twodevsstudio.simplejsonconfig.api.Config;
import me.zdziszkee.authentication.commands.TextBookCommand;
import me.zdziszkee.authentication.configuration.*;

import me.zdziszkee.authentication.gui.GUIListener;
import me.zdziszkee.authentication.gui.auth.PlayerKicker;
import me.zdziszkee.authentication.gui.space.BookGUIManager;
import me.zdziszkee.authentication.gui.space.PlayerDataCache;
import me.zdziszkee.authentication.listeners.InventoryCloseListener;
import me.zdziszkee.authentication.listeners.PlayerJoinListener;
import me.zdziszkee.authentication.listeners.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Authentication extends JavaPlugin {
    private PatternFinderAuthGUIConfiguration patternFinderAuthGUIConfiguration;
    private PuzzleAuthGUIConfiguration puzzleAuthGUIConfiguration;
    private PinPadAuthGUIConfiguration pinPadAuthGUIConfiguration;
    private GeneralConfiguration generalConfiguration;
    private PlayerKicker playerKicker;
    private static Authentication authentication;
    private BookGUIConfiguration bookGUIConfiguration;
    private final PlayerDataCache playerDataCache = new PlayerDataCache();
    private final BookGUIManager bookGUIManager = new BookGUIManager();
    @Override
    public void onEnable() {
        SimpleJSONConfig.INSTANCE.register(this);
        patternFinderAuthGUIConfiguration = Config.getConfig(PatternFinderAuthGUIConfiguration.class);
        pinPadAuthGUIConfiguration = Config.getConfig(PinPadAuthGUIConfiguration.class);
        puzzleAuthGUIConfiguration = Config.getConfig(PuzzleAuthGUIConfiguration.class);
        generalConfiguration = Config.getConfig(GeneralConfiguration.class);
        playerKicker = new PlayerKicker(generalConfiguration);
        bookGUIConfiguration = Config.getConfig(BookGUIConfiguration.class);
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
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(patternFinderAuthGUIConfiguration, pinPadAuthGUIConfiguration, puzzleAuthGUIConfiguration, generalConfiguration, playerKicker,playerDataCache,bookGUIManager), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(bookGUIManager,playerKicker),this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(playerDataCache,bookGUIManager),this);
    }

    private void registerCommands() {
        PaperCommandManager paperCommandManager = new PaperCommandManager(this);
        paperCommandManager.registerCommand(new TextBookCommand(bookGUIManager,playerDataCache, generalConfiguration));
    }

    public BookGUIConfiguration getBookGUIConfiguration() {
        return bookGUIConfiguration;
    }

    @Override
    public void onDisable() {
        Bukkit.broadcastMessage("Authentication has been disabled!");
    }
}
