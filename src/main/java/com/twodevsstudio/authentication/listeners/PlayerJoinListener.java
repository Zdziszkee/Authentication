package com.twodevsstudio.authentication.listeners;

import com.twodevsstudio.authentication.Authentication;
import com.twodevsstudio.authentication.configuration.GeneralConfiguration;
import com.twodevsstudio.authentication.configuration.PatternFinderAuthGUIConfiguration;
import com.twodevsstudio.authentication.configuration.PinPadAuthGUIConfiguration;
import com.twodevsstudio.authentication.configuration.PuzzleAuthGUIConfiguration;
import com.twodevsstudio.authentication.gui.auth.PatternFinderAuthGUI;
import com.twodevsstudio.authentication.gui.auth.PlayerKicker;
import com.twodevsstudio.authentication.gui.auth.PuzzleAuthGUI;
import com.twodevsstudio.authentication.utils.Coordinates;
import com.twodevsstudio.wyscore.api.CoreAPI;
import com.twodevsstudio.wyscore.database.service.PlayerService;
import com.twodevsstudio.wyscore.utils.ConnectionUtil;
import lombok.RequiredArgsConstructor;
import com.twodevsstudio.authentication.gui.auth.PinPadAuthGUI;
import com.twodevsstudio.authentication.gui.space.BookGUIManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {
    private final PatternFinderAuthGUIConfiguration patternFinderAuthGUIConfiguration;
    private final PinPadAuthGUIConfiguration pinPadAuthGUIConfiguration;
    private final PuzzleAuthGUIConfiguration puzzleAuthGUIConfiguration;
    private final GeneralConfiguration generalConfiguration;
    private final PlayerKicker playerKicker;
    private final BookGUIManager bookGUIManager;
    private final PlayerService playerService = CoreAPI.getPlayerService();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        
        Player player = event.getPlayer();
        int random = ThreadLocalRandom.current().nextInt(1, 4);
        PlayerService.PlayerData playerData = CoreAPI.getPlayerService().findByUUID(player.getUniqueId());
       
        if (!playerData.isAuthorizationComplete()) {
            Bukkit.getScheduler().runTaskLater(Authentication.getInstance(), () -> {
                
                switch (random) {
                    case 1: {
                        PatternFinderAuthGUI patternFinderAuthGUI = new PatternFinderAuthGUI(
                                player, patternFinderAuthGUIConfiguration, playerKicker, generalConfiguration,
                                bookGUIManager, playerData
                        );
                        patternFinderAuthGUI.openInventory();
                        break;
                    }
                    case 2: {
                        PinPadAuthGUI pinPadAuthGUI = new PinPadAuthGUI(
                                pinPadAuthGUIConfiguration, player, playerKicker, generalConfiguration, bookGUIManager,
                                playerData);
                        pinPadAuthGUI.openInventory();
                        break;
                    }
                    case 3: {
                        PuzzleAuthGUI puzzleAuthGUI = new PuzzleAuthGUI(
                                player, puzzleAuthGUIConfiguration, playerKicker, generalConfiguration, bookGUIManager,
                                playerData);
                        puzzleAuthGUI.openInventory();
                        break;
                    }
                }
            }, 60L);
            return;
        }
        Coordinates velocity = generalConfiguration.getSpaceVelocity();
        player.setVelocity(new Vector(velocity.getX(), velocity.getY(), velocity.getZ()));
        Bukkit.getScheduler().runTaskLater(Authentication.getInstance(), () -> {
            ConnectionUtil.sendPlayerToServer(player, generalConfiguration.getSeverNameForTeleporting());
            
        }, 20L * generalConfiguration.getSpaceTeleportDelayInSeconds());
        
    }
}
