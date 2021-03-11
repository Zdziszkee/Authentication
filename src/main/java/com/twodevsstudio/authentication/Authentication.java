package com.twodevsstudio.authentication;

import co.aikar.commands.PaperCommandManager;
import com.twodevsstudio.authentication.commands.TextBookCommand;
import com.twodevsstudio.authentication.configuration.*;
import com.twodevsstudio.authentication.gui.GUIListener;
import com.twodevsstudio.authentication.gui.auth.PlayerKicker;
import com.twodevsstudio.authentication.gui.space.BookGUIManager;
import com.twodevsstudio.authentication.listeners.InventoryCloseListener;
import com.twodevsstudio.authentication.listeners.PlayerJoinListener;
import com.twodevsstudio.authentication.listeners.PlayerQuitListener;
import com.twodevsstudio.simplejsonconfig.SimpleJSONConfig;
import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.wyscore.api.CoreAPI;
import com.twodevsstudio.wyscore.database.service.CurrencyService;
import com.twodevsstudio.wyscore.database.service.PlayerService;
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
    private final BookGUIManager bookGUIManager = new BookGUIManager();
    private  PlayerService playerService;
    private  CurrencyService currencyService;
    
    @Override
    public void onEnable() {
        playerService  = CoreAPI.getPlayerService();
        currencyService  = CoreAPI.getCurrencyService();
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
        Bukkit.getPluginManager()
                .registerEvents(new PlayerJoinListener(patternFinderAuthGUIConfiguration, pinPadAuthGUIConfiguration, puzzleAuthGUIConfiguration,
                        generalConfiguration, playerKicker, bookGUIManager
                ), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(bookGUIManager, playerKicker), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(bookGUIManager), this);
    }
    
    private void registerCommands() {
        
        PaperCommandManager paperCommandManager = new PaperCommandManager(this);
        paperCommandManager.registerCommand(new TextBookCommand(bookGUIManager, playerService, generalConfiguration, currencyService));
    }
    
    public BookGUIConfiguration getBookGUIConfiguration() {
        
        return bookGUIConfiguration;
    }
    
    @Override
    public void onDisable() {
        
        Bukkit.broadcastMessage("Authentication has been disabled!");
    }
}
