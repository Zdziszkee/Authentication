package me.zdziszkee.authentication.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import me.zdziszkee.authentication.gui.space.BookPages;
import me.zdziszkee.wyscore.api.CoreAPI;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.upperlevel.spigot.book.BookUtil;

@CommandAlias("textbook")
public class TextBookCommand extends BaseCommand {
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

        }

    }

    @Subcommand("gender")
    public void setGender(String[] args) {
        if (args.length != 2) return;
        String playerName = args[0];
        String gender = args[1];
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) return;
        if (gender.equalsIgnoreCase("girl")) {
        } else if (gender.equalsIgnoreCase("boy")) {

        }

    }
}
