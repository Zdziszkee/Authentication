package me.zdziszkee.authentication.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpaceUtil
{
    public static void connect(Player player, String server, JavaPlugin javaPlugin){
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        player.sendPluginMessage(javaPlugin,"BungeeCord",output.toByteArray());
    }
}
