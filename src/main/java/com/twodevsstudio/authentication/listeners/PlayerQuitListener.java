package com.twodevsstudio.authentication.listeners;

import com.twodevsstudio.wyscore.database.service.CurrencyService;
import com.twodevsstudio.wyscore.database.service.PlayerService;
import lombok.RequiredArgsConstructor;
import com.twodevsstudio.authentication.gui.space.BookGUIManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
@RequiredArgsConstructor
public class PlayerQuitListener implements Listener {
    private final BookGUIManager bookGUIManager;

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
    
        Player player = event.getPlayer();
        bookGUIManager.removePlayer(player);
    }
}
