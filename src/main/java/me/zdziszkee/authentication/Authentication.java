package me.zdziszkee.authentication;

import com.twodevsstudio.simplejsonconfig.SimpleJSONConfig;
import com.twodevsstudio.simplejsonconfig.api.Config;
import me.zdziszkee.authentication.configuration.GeneralConfiguration;
import me.zdziszkee.authentication.configuration.PatternFinderAuthGUIConfiguration;
import me.zdziszkee.authentication.configuration.PinPadAuthGUIConfiguration;
import me.zdziszkee.authentication.configuration.PuzzleAuthGUIConfiguration;
import me.zdziszkee.authentication.gui.GUIListener;
import me.zdziszkee.authentication.gui.auth.PlayerKicker;
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

    @Override
    public void onEnable() {
        SimpleJSONConfig.INSTANCE.register(this);
        patternFinderAuthGUIConfiguration = Config.getConfig(PatternFinderAuthGUIConfiguration.class);
        pinPadAuthGUIConfiguration = Config.getConfig(PinPadAuthGUIConfiguration.class);
        puzzleAuthGUIConfiguration = Config.getConfig(PuzzleAuthGUIConfiguration.class);
        generalConfiguration = Config.getConfig(GeneralConfiguration.class);
        playerKicker = new PlayerKicker(generalConfiguration);
        Bukkit.broadcastMessage("Authentication has been enabled!");
        registerEvents();
        authentication = this;
    }

    public static Authentication getInstance() {
        return authentication;
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(patternFinderAuthGUIConfiguration, pinPadAuthGUIConfiguration, puzzleAuthGUIConfiguration, playerKicker), this);
    }


    @Override
    public void onDisable() {
        Bukkit.broadcastMessage("Authentication has been disabled!");
    }
}
