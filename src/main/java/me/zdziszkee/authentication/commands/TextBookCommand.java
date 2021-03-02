package me.zdziszkee.authentication.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import lombok.RequiredArgsConstructor;
import me.zdziszkee.authentication.Authentication;
import me.zdziszkee.authentication.configuration.GeneralConfiguration;
import me.zdziszkee.authentication.gui.space.BookPages;
import me.zdziszkee.authentication.gui.space.PlayerDataCache;
import me.zdziszkee.authentication.utils.Coordinates;
import me.zdziszkee.authentication.utils.SpaceUtil;
import me.zdziszkee.wyscore.api.CoreAPI;
import me.zdziszkee.wyscore.database.service.PlayerService;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import xyz.upperlevel.spigot.book.BookUtil;

import java.time.LocalDate;

@RequiredArgsConstructor
@CommandAlias("textbook")
public class TextBookCommand extends BaseCommand {
    private final PlayerDataCache playerDataCache;
    private final GeneralConfiguration generalConfiguration;
    @Subcommand("page")
    public void openPage(String[] args) {
        if (args.length != 2) return;
        String number = args[1];
        if (!StringUtils.isNumeric(number)) return;
        int page = Integer.parseInt(number);
        String playerName = args[0];
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) return;
        switch (page) {
            case 1:
                BookUtil.openPlayer(player, BookPages.FIRST);
                break;
            case 2:
                BookUtil.openPlayer(player, BookPages.SECOND);
                break;
            case 3:
                BookUtil.openPlayer(player, BookPages.THIRD);
                break;
            case 4:
                BookUtil.openPlayer(player, BookPages.FOURTH);
                break;
        }

    }

    @Subcommand("gender")
    public void setGender(String[] args) {
        if (args.length != 2) return;
        String playerName = args[0];
        String gender = args[1];
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) return;
        PlayerService.PlayerData playerData = playerDataCache.getPlayerData(player.getUniqueId()) == null ? new PlayerService.PlayerData(LocalDate.now(), 0, false, true) : playerDataCache.getPlayerData(player.getUniqueId());
        if (gender.equalsIgnoreCase("girl")) {
            playerData.setBoy(false);

        } else if (gender.equalsIgnoreCase("boy")) {
            playerData.setBoy(true);
        }
        playerDataCache.addPlayer(player.getUniqueId(),playerData);
        BookUtil.openPlayer(player, BookPages.THIRD);
    }

    @Subcommand("start")
    public void start(String[] args) {
        if (args.length != 1) return;
        String playerName = args[0];
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) return;
        CoreAPI.getPlayerService().save(player.getUniqueId(), playerDataCache.getPlayerData(player.getUniqueId()));
        Coordinates velocity = generalConfiguration.getSpaceVelocity();
        player.setVelocity(new Vector(velocity.getX(),velocity.getY(),velocity.getZ()));
        Bukkit.getScheduler().runTaskLater(Authentication.getInstance(), () -> {
            SpaceUtil.connect(player,generalConfiguration.getSeverNameForTeleporting());
        },20L*generalConfiguration.getSpaceTeleportDelayInSeconds());

    }
}
