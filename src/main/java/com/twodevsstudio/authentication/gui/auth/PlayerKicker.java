package com.twodevsstudio.authentication.gui.auth;

import com.twodevsstudio.authentication.configuration.GeneralConfiguration;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class PlayerKicker {
    private final GeneralConfiguration generalConfiguration;
    public  void kickPlayer(Player player){
        StringBuilder stringBuilder = new StringBuilder();
        for (String reasonLine : generalConfiguration.getKickMessage()) {
            stringBuilder.append(reasonLine).append("\n");
        }
        String reason = ChatColor.translateAlternateColorCodes('&',stringBuilder.toString());
        player.kickPlayer(reason);
    }
}
