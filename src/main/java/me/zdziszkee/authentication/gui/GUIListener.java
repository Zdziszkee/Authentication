package me.zdziszkee.authentication.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class GUIListener implements Listener {
    @EventHandler
    public void onGUIClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if (inventory == null) return;
        InventoryHolder inventoryHolder = inventory.getHolder();
        if (inventoryHolder == null) return;
        if (!(inventoryHolder instanceof GUI)) return;
        GUI gui = (GUI) inventoryHolder;
        gui.onClick(event);
        event.setCancelled(true);
    }

    @EventHandler
    public void onGUIOpen(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory == null) return;
        InventoryHolder inventoryHolder = inventory.getHolder();
        if (inventoryHolder == null) return;
        if (!(inventoryHolder instanceof GUI)) return;
        GUI gui = (GUI) inventoryHolder;
        gui.onOpen(event);
    }

    @EventHandler
    public void onGUIClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory == null) return;
        InventoryHolder inventoryHolder = inventory.getHolder();
        if (inventoryHolder == null) return;
        if (!(inventoryHolder instanceof GUI)) return;
        GUI gui = (GUI) inventoryHolder;
        gui.onClose(event);
    }

    @EventHandler
    public void onGUIDrag(InventoryDragEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory == null) return;
        InventoryHolder inventoryHolder = inventory.getHolder();
        if (inventoryHolder == null) return;
        if (!(inventoryHolder instanceof GUI)) return;
        event.setCancelled(true);
    }
}
