package com.twodevsstudio.authentication.gui.space;


import org.bukkit.entity.Player;

import java.util.*;

public class BookGUIManager {
    private final List<UUID> bookGuiViewers = new ArrayList<>();
    public void addPlayer(Player player){
        bookGuiViewers.add(player.getUniqueId());
    }
    public void removePlayer(Player player){
        bookGuiViewers.remove(player.getUniqueId());
    }
    public boolean isPlayerViewingPage(Player player){
        return bookGuiViewers.contains(player.getUniqueId());
    }
}
