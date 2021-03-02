package me.zdziszkee.authentication.listeners;

import lombok.RequiredArgsConstructor;
import me.zdziszkee.authentication.gui.space.BookGUIManager;
import me.zdziszkee.authentication.gui.space.PlayerDataCache;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
@RequiredArgsConstructor
public class PlayerQuitListener implements Listener {
    private final PlayerDataCache playerDataCache;
    private final BookGUIManager bookGUIManager;
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        playerDataCache.removePlayer(event.getPlayer().getUniqueId());
        bookGUIManager.removePlayer(event.getPlayer());
    }
}
