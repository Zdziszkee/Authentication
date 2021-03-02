package me.zdziszkee.authentication.listeners;

import lombok.RequiredArgsConstructor;
import me.zdziszkee.authentication.Authentication;
import me.zdziszkee.authentication.configuration.GeneralConfiguration;
import me.zdziszkee.authentication.configuration.PatternFinderAuthGUIConfiguration;
import me.zdziszkee.authentication.configuration.PinPadAuthGUIConfiguration;
import me.zdziszkee.authentication.configuration.PuzzleAuthGUIConfiguration;
import me.zdziszkee.authentication.gui.auth.PatternFinderAuthGUI;
import me.zdziszkee.authentication.gui.auth.PinPadAuthGUI;
import me.zdziszkee.authentication.gui.auth.PlayerKicker;
import me.zdziszkee.authentication.gui.auth.PuzzleAuthGUI;
import me.zdziszkee.authentication.gui.space.BookGUIManager;
import me.zdziszkee.authentication.gui.space.PlayerDataCache;
import me.zdziszkee.authentication.utils.Coordinates;
import me.zdziszkee.authentication.utils.SpaceUtil;
import me.zdziszkee.wyscore.api.CoreAPI;
import me.zdziszkee.wyscore.database.service.PlayerService;
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
    private final PlayerDataCache playerDataCache;
    private  final BookGUIManager bookGUIManager;
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int random = ThreadLocalRandom.current().nextInt(1,4);
        PlayerService.PlayerData byUUID = CoreAPI.getPlayerService().findByUUID(player.getUniqueId());
        if(!byUUID.isAuthorizationComplete()) {
            Bukkit.getScheduler().runTaskLater(Authentication.getInstance(), () -> {

                switch (random) {
                    case 1: {
                        PatternFinderAuthGUI patternFinderAuthGUI = new PatternFinderAuthGUI(player, patternFinderAuthGUIConfiguration, playerKicker, generalConfiguration, bookGUIManager,playerDataCache);
                        patternFinderAuthGUI.openInventory();
                        break;
                    }
                    case 2: {
                        PinPadAuthGUI pinPadAuthGUI = new PinPadAuthGUI(pinPadAuthGUIConfiguration, player, playerKicker, generalConfiguration, bookGUIManager,playerDataCache);
                        pinPadAuthGUI.openInventory();
                        break;
                    }
                    case 3: {
                        PuzzleAuthGUI puzzleAuthGUI = new PuzzleAuthGUI(player, puzzleAuthGUIConfiguration, playerKicker, generalConfiguration, bookGUIManager,playerDataCache);
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
            SpaceUtil.connect(player, generalConfiguration.getSeverNameForTeleporting());

        }, 20L * generalConfiguration.getSpaceTeleportDelayInSeconds());

    }
}
