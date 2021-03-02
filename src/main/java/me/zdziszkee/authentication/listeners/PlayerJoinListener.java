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
import me.zdziszkee.authentication.utils.Coordinates;
import me.zdziszkee.authentication.utils.SpaceUtil;
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

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int random = ThreadLocalRandom.current().nextInt(4);

        if (!player.hasPlayedBefore()) {
            Bukkit.getScheduler().runTaskLater(Authentication.getInstance(), () -> {

                switch (random) {
                    case 1: {
                        PatternFinderAuthGUI patternFinderAuthGUI = new PatternFinderAuthGUI(player, patternFinderAuthGUIConfiguration, playerKicker, generalConfiguration);
                        patternFinderAuthGUI.openInventory();
                        break;
                    }
                    case 2: {
                        PinPadAuthGUI pinPadAuthGUI = new PinPadAuthGUI(pinPadAuthGUIConfiguration, player, playerKicker, generalConfiguration);
                        pinPadAuthGUI.openInventory();
                        break;
                    }
                    case 3: {
                        PuzzleAuthGUI puzzleAuthGUI = new PuzzleAuthGUI(player, puzzleAuthGUIConfiguration, playerKicker, generalConfiguration);
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
