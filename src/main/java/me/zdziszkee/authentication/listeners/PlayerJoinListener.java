package me.zdziszkee.authentication.listeners;

import lombok.RequiredArgsConstructor;
import me.zdziszkee.authentication.Authentication;
import me.zdziszkee.authentication.configuration.PatternFinderAuthGUIConfiguration;
import me.zdziszkee.authentication.configuration.PinPadAuthGUIConfiguration;
import me.zdziszkee.authentication.configuration.PuzzleAuthGUIConfiguration;
import me.zdziszkee.authentication.gui.auth.PatternFinderAuthGUI;
import me.zdziszkee.authentication.gui.auth.PinPadAuthGUI;
import me.zdziszkee.authentication.gui.auth.PlayerKicker;
import me.zdziszkee.authentication.gui.auth.PuzzleAuthGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {
    private final PatternFinderAuthGUIConfiguration patternFinderAuthGUIConfiguration;
    private final PinPadAuthGUIConfiguration pinPadAuthGUIConfiguration;
    private final PuzzleAuthGUIConfiguration puzzleAuthGUIConfiguration;
    private final PlayerKicker playerKicker;
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int random = ThreadLocalRandom.current().nextInt(4);


        Bukkit.getScheduler().runTaskLater(Authentication.getInstance(), () -> {

            switch (random) {
                case 1:
                    PatternFinderAuthGUI patternFinderAuthGUI = new PatternFinderAuthGUI(player, patternFinderAuthGUIConfiguration, playerKicker);
                    patternFinderAuthGUI.openInventory();
                    break;
                case 2:
                    PinPadAuthGUI pinPadAuthGUI = new PinPadAuthGUI(pinPadAuthGUIConfiguration, player, playerKicker);
                    pinPadAuthGUI.openInventory();
                    break;
                case 3:
                    PuzzleAuthGUI puzzleAuthGUI = new PuzzleAuthGUI(player, puzzleAuthGUIConfiguration, playerKicker);
                    puzzleAuthGUI.openInventory();
                    break;
            }
        },20);


    }
}
