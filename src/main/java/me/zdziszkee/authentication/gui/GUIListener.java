package me.zdziszkee.authentication.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class GUIListener implements Listener {
    @EventHandler
    public void onGUIClick(InventoryClickEvent event) {
        GUI gui = processEvent(event);
        if(gui==null)return;
        gui.onClick(event);
        event.setCancelled(true);
    }

    @EventHandler
    public void onGUIOpen(InventoryOpenEvent event) {
        GUI gui = processEvent(event);
        if(gui==null)return;
        gui.onOpen(event);
    }

    @EventHandler
    public void onGUIClose(InventoryCloseEvent event) {
        GUI gui = processEvent(event);
        if(gui==null)return;
        gui.onClose(event);
    }

    @EventHandler
    public void onGUIDrag(InventoryDragEvent event) {
        GUI gui = processEvent(event);
        if(gui==null)return;
        event.setCancelled(true);
    }
    private GUI processEvent(InventoryEvent event){
        Inventory inventory = event.getInventory();
        if (inventory == null) return null;
        InventoryHolder inventoryHolder = inventory.getHolder();
        if (inventoryHolder == null) return null;
        if (!(inventoryHolder instanceof GUI)) return null;
        return (GUI) inventoryHolder;
    }
}
