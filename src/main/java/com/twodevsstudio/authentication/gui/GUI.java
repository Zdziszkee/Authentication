package com.twodevsstudio.authentication.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

public interface GUI extends InventoryHolder {
    default void onClick(InventoryClickEvent inventoryClickEvent){}
    default void onClose(InventoryCloseEvent inventoryCloseEvent){}
    default void onOpen(InventoryOpenEvent inventoryOpenEvent){}

}
