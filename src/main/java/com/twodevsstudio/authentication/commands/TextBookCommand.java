package com.twodevsstudio.authentication.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import com.twodevsstudio.authentication.Authentication;
import com.twodevsstudio.authentication.configuration.GeneralConfiguration;
import com.twodevsstudio.authentication.gui.space.BookGUIManager;
import com.twodevsstudio.authentication.gui.space.BookPages;
import com.twodevsstudio.authentication.utils.Coordinates;
import com.twodevsstudio.wyscore.database.service.CurrencyService;
import com.twodevsstudio.wyscore.database.service.PlayerService;
import com.twodevsstudio.wyscore.utils.ConnectionUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import xyz.upperlevel.spigot.book.BookUtil;

@RequiredArgsConstructor
@CommandAlias( "textbook" )
public class TextBookCommand extends BaseCommand {
    private final BookGUIManager bookGUIManager;
    private final PlayerService playerService;
    private final GeneralConfiguration generalConfiguration;
    private final CurrencyService currencyService;
    
    @Subcommand( "page" )
    public void openPage(String[] args) {
    
        if (args.length != 2) {
            return;
        }
        String number = args[1];
        if (!StringUtils.isNumeric(number)) {
            return;
        }
        int page = Integer.parseInt(number);
        String playerName = args[0];
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            return;
        }
        switch (page) {
            case 1:
                bookGUIManager.removePlayer(player);
                BookUtil.openPlayer(player, BookPages.getFirstItem(player));
                bookGUIManager.addPlayer(player);
                break;
            case 2:
                bookGUIManager.removePlayer(player);
                BookUtil.openPlayer(player, BookPages.getSecondItem(player));
                bookGUIManager.addPlayer(player);
                break;
            case 3:
                bookGUIManager.removePlayer(player);
                BookUtil.openPlayer(player, BookPages.getThirdItem(player));
                bookGUIManager.addPlayer(player);
                break;
            case 4:
                bookGUIManager.removePlayer(player);
                BookUtil.openPlayer(player, BookPages.getFourthItem(player));
                bookGUIManager.addPlayer(player);
                break;
        }
        
    }
    
    @Subcommand( "gender" )
    public void setGender(String[] args) {
    
        if (args.length != 2) {
            return;
        }
        String playerName = args[0];
        String gender = args[1];
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            return;
        }
        PlayerService.PlayerData playerData = playerService.findByUUID(player.getUniqueId());
        if (gender.equalsIgnoreCase("girl")) {
            playerData.setBoy(false);
            
        } else if (gender.equalsIgnoreCase("boy")) {
            playerData.setBoy(true);
        }
        bookGUIManager.removePlayer(player);
        BookUtil.openPlayer(player, BookPages.getThirdItem(player));
        bookGUIManager.addPlayer(player);
        
    }
    
    @Subcommand( "start" )
    public void start(String[] args) {
    
        if (args.length != 1) {
            return;
        }
        String playerName = args[0];
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            return;
        }
        bookGUIManager.removePlayer(player);
        playerService.update(player.getUniqueId());
        currencyService.update(player.getUniqueId());
        Coordinates velocity = generalConfiguration.getSpaceVelocity();
        player.setVelocity(new Vector(velocity.getX(), velocity.getY(), velocity.getZ()));
        Bukkit.getScheduler().runTaskLater(Authentication.getInstance(), () -> {
            ConnectionUtil.sendPlayerToServer(player, generalConfiguration.getSeverNameForTeleporting());
        }, 20L * generalConfiguration.getSpaceTeleportDelayInSeconds());
        
    }
}
