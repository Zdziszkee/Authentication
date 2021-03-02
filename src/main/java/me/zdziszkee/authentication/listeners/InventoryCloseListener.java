package me.zdziszkee.authentication.listeners;

import lombok.RequiredArgsConstructor;
import me.zdziszkee.authentication.gui.auth.PlayerKicker;
import me.zdziszkee.authentication.gui.space.BookGUIManager;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
@RequiredArgsConstructor
public class InventoryCloseListener implements Listener {
    private final BookGUIManager bookGUIManager;
    private final PlayerKicker playerKicker;
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        HumanEntity player = event.getPlayer();
        if (!bookGUIManager.isPlayerViewingPage((Player) player)) {
            return;
        }

        playerKicker.kickPlayer((Player) player);
    }
}
