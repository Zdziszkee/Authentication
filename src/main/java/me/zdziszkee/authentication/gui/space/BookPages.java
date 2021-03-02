package me.zdziszkee.authentication.gui.space;

import net.md_5.bungee.protocol.packet.Chat;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

public class BookPages {
    public static final ItemStack FIRST =  BookUtil.writtenBook().pages(new BookUtil.PageBuilder()
                .add(new BookUtil.TextBuilder().color(ChatColor.RED).text("Welcome to").build())
                .add(new BookUtil.TextBuilder().color(ChatColor.GOLD).style(ChatColor.BOLD).text("Wystern").build())
                .add(new BookUtil.TextBuilder().color(ChatColor.RED).text("!").build())
            .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).style(ChatColor.STRIKETHROUGH).build())
            .newLine()
            .newLine().add(new BookUtil.TextBuilder().color(ChatColor.LIGHT_PURPLE).style(ChatColor.BOLD).text("Your journey").build())
            .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("is about to begin!").build())
            .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("But there are few").build())
            .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("things to do before").build())
            .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("that.Let's get").build())
            .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("started, shall we?").build())
            .newLine()
            .newLine()
                .add(BookUtil.TextBuilder.of("- ").style(ChatColor.BOLD).color(ChatColor.BLACK).build())
                .add(BookUtil.TextBuilder.of("Click to continue").onClick(BookUtil.ClickAction.runCommand("/textbook page 2")).build()).build())
            .build();
    public static final ItemStack SECOND =  BookUtil.writtenBook().pages(
            new BookUtil.PageBuilder().
                    add(new BookUtil.TextBuilder().color(ChatColor.RED).text("Genders in Wystern").build())
                    .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).style(ChatColor.STRIKETHROUGH).build())
                    .newLine()
                    .newLine().add(new BookUtil.TextBuilder().color(ChatColor.LIGHT_PURPLE).style(ChatColor.BOLD).text("What are the").build())
                    .newLine().add(new BookUtil.TextBuilder().color(ChatColor.LIGHT_PURPLE).style(ChatColor.BOLD).text("genders?").build())
                    .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("To get to know the").build())
                    .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("community members").build())
                    .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("more! We make it").build())
                    .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("easier! Click below to").build())
                    .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("choose your gender").build())
                    .newLine()
                    .newLine()
                        .add(BookUtil.TextBuilder.of("- ").style(ChatColor.BOLD).color(ChatColor.BLACK).build())
                        .add(BookUtil.TextBuilder.of("Boy").style(ChatColor.BOLD).color(ChatColor.BLUE).onClick(BookUtil.ClickAction.runCommand("/textbook page 2")).onHover(BookUtil.HoverAction.showText(ChatColor.RED+"Be careful while choosing your gender!")).build())
                    .newLine()
                        .add(BookUtil.TextBuilder.of("- ").style(ChatColor.BOLD).color(ChatColor.BLACK).build())
                        .add(BookUtil.TextBuilder.of("Girl").style(ChatColor.BOLD).color(ChatColor.LIGHT_PURPLE).onClick(BookUtil.ClickAction.runCommand("/textbook page 2")).onHover(BookUtil.HoverAction.showText(ChatColor.RED+"Be careful while choosing your gender!")).build()).build())
            .build();
    public static final ItemStack THIRD =  BookUtil.writtenBook().pages(new BookUtil.PageBuilder()
            .add(new BookUtil.TextBuilder().color(ChatColor.RED).text("Can't wait to finish?").build())
            .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).style(ChatColor.STRIKETHROUGH).build())
            .newLine()
            .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("Nothing left for you").build())
            .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("to do! You may now go").build())
            .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("play/explore Wystern").build())
            .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("Have a nice journey!").build())
            .newLine()
            .newLine()
            .add(BookUtil.TextBuilder.of("- ").style(ChatColor.BOLD).color(ChatColor.BLACK).build())
            .add(BookUtil.TextBuilder.of("Start my journey").color(ChatColor.LIGHT_PURPLE).style(ChatColor.BOLD).onClick(BookUtil.ClickAction.runCommand("/textbook page 2")).build()).build()

    ).build();






}
