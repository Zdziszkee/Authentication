package me.zdziszkee.authentication.utils;


import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpaceUtil
{
    public static void connect(Player player, String serverName ){
        ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(player.getUniqueId());
        ServerInfo target = ProxyServer.getInstance().getServerInfo(serverName);
        proxiedPlayer.connect(target);
    }
}
