package com.twodevsstudio.authentication.gui.space;



import com.twodevsstudio.wyscore.database.service.PlayerService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataCache {
    private  final Map<UUID, PlayerService.PlayerData> playerCache = new HashMap<>();
    public void addPlayer(UUID uuid, PlayerService.PlayerData playerData){
        playerCache.put(uuid,playerData);
    }
    public void removePlayer(UUID uuid){
        playerCache.remove(uuid);
    }
    public PlayerService.PlayerData getPlayerData(UUID uuid){
        return playerCache.get(uuid);
    }
}
