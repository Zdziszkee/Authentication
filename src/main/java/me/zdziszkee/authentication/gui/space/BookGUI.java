package me.zdziszkee.authentication.gui.space;

import me.zdziszkee.authentication.configuration.BookGUIConfiguration;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

public class BookGUI {
    private final Player player;
    private final BookGUIConfiguration bookGUIConfiguration;

    public BookGUI(Player player, BookGUIConfiguration bookGUIConfiguration) {
        this.player = player;
        this.bookGUIConfiguration = bookGUIConfiguration;
    }

    public void openInventory() {

    }
}
