package com.twodevsstudio.authentication.gui.space;

import com.twodevsstudio.authentication.Authentication;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

public class BookPages {
    public static ItemStack getFirstItem(Player player){
        return BookUtil.writtenBook().pages(new BookUtil.PageBuilder()
                .add(new BookUtil.TextBuilder().color(ChatColor.RED).text("Welcome to ").build())
                .add(new BookUtil.TextBuilder().color(ChatColor.GOLD).style(ChatColor.BOLD).text("Wystern").build())
                .add(new BookUtil.TextBuilder().color(ChatColor.RED).text("!").build())
                .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).style(ChatColor.STRIKETHROUGH).text("------------------").build())
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
                .add(BookUtil.TextBuilder.of("Click to continue").color(ChatColor.GRAY).onClick(BookUtil.ClickAction.runCommand("/textbook page "+player.getName()+" 2")).onHover(BookUtil.HoverAction.showText(ChatColor.GRAY+"Click to continue")).build()).build())
                .build();


    }
    public static ItemStack getSecondItem(Player player){
        return BookUtil.writtenBook().pages(
                new BookUtil.PageBuilder().
                        add(new BookUtil.TextBuilder().color(ChatColor.RED).text("Genders in Wystern").build())
                        .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).style(ChatColor.STRIKETHROUGH).build())
                        .newLine()
                        .newLine().add(new BookUtil.TextBuilder().color(ChatColor.LIGHT_PURPLE).style(ChatColor.BOLD).text("What are the").onHover(BookUtil.HoverAction.showText(ChatColor.GRAY + "Click here to read more about genders")).onClick(BookUtil.ClickAction.openUrl(Authentication.getInstance().getBookGUIConfiguration().getGenderInfo())).build())
                        .newLine().add(new BookUtil.TextBuilder().color(ChatColor.LIGHT_PURPLE).style(ChatColor.BOLD).text("genders?").onHover(BookUtil.HoverAction.showText(ChatColor.GRAY + "Click here to read more about genders")).onClick(BookUtil.ClickAction.openUrl(Authentication.getInstance().getBookGUIConfiguration().getGenderInfo())).build())
                        .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("To get to know the").build())
                        .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("community members").build())
                        .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("more! We make it").build())
                        .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("easier! Click below to").build())
                        .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("choose your gender").build())
                        .newLine()
                        .newLine()
                        .add(BookUtil.TextBuilder.of("- ").style(ChatColor.BOLD).color(ChatColor.BLACK).build())
                        .add(BookUtil.TextBuilder.of("Boy").style(ChatColor.BOLD).color(ChatColor.BLUE).onClick(BookUtil.ClickAction.runCommand("/textbook gender "+player.getName()+" boy")).onHover(BookUtil.HoverAction.showText(ChatColor.RED + "Be careful while choosing your gender!")).build())
                        .newLine().newLine()
                        .add(BookUtil.TextBuilder.of("- ").style(ChatColor.BOLD).color(ChatColor.BLACK).build())
                        .add(BookUtil.TextBuilder.of("Girl").style(ChatColor.BOLD).color(ChatColor.LIGHT_PURPLE).onClick(BookUtil.ClickAction.runCommand("/textbook gender "+player.getName()+" girl")).onHover(BookUtil.HoverAction.showText(ChatColor.RED + "Be careful while choosing your gender!")).build()).build())
                .build();


    }

public static ItemStack getThirdItem(Player player){
    return BookUtil.writtenBook().pages(new BookUtil.PageBuilder()
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
            .add(BookUtil.TextBuilder.of("Start my journey").color(ChatColor.LIGHT_PURPLE).style(ChatColor.BOLD).onClick(BookUtil.ClickAction.runCommand("/textbook start "+player.getName())).onHover(BookUtil.HoverAction.showText(ChatColor.GRAY + "Don't worry! You may return and choose your gender")).build())
            .newLine()
            .newLine()
            .add(BookUtil.TextBuilder.of("- ").style(ChatColor.BOLD).color(ChatColor.BLACK).build())
            .add(BookUtil.TextBuilder.of("Click to reset").color(ChatColor.RED).style(ChatColor.BOLD).onClick(BookUtil.ClickAction.runCommand("/textbook page "+player.getName() + " 2")).onHover(BookUtil.HoverAction.showText(ChatColor.GRAY + "Don't worry! You may return and choose your gender"))
                    .build()

            ).build()).build();

}


    public static ItemStack getFourthItem(Player player) {
        return BookUtil.writtenBook().pages(new BookUtil.PageBuilder()
                .add(new BookUtil.TextBuilder().color(ChatColor.RED).text("Rules of Wystern?").build())
                .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).style(ChatColor.STRIKETHROUGH).text("------------------").build())
                .newLine().add(new BookUtil.TextBuilder().color(ChatColor.LIGHT_PURPLE).style(ChatColor.BOLD).text("What").onHover(BookUtil.HoverAction.showText(ChatColor.GRAY + "Click here to read the rules/guidelines of Wystern")).onClick(BookUtil.ClickAction.openUrl("https://wystern.com/rules")).build())
                .newLine().add(new BookUtil.TextBuilder().color(ChatColor.LIGHT_PURPLE).style(ChatColor.BOLD).text("rules?").onHover(BookUtil.HoverAction.showText(ChatColor.GRAY + "Click here to read the rules/guidelines of Wystern")).onClick(BookUtil.ClickAction.openUrl("https://wystern.com/rules")).build())
                .newLine()
                .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("To make sure").build())
                .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("everyone has a great").build())
                .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("time in Wystern, We").build())
                .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("made guidelines and!").build())
                .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("made guidelines and").build())
                .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("must be followed by").build())
                .newLine().add(new BookUtil.TextBuilder().color(ChatColor.BLACK).text("all players").build())
                .newLine()
                .newLine()
                .add(BookUtil.TextBuilder.of("- ").style(ChatColor.BOLD).color(ChatColor.BLACK).build())
                .add(BookUtil.TextBuilder.of("Click to continue").color(ChatColor.BLACK).onClick(BookUtil.ClickAction.runCommand("/textbook page "+player.getName()+" 1")).onHover(BookUtil.HoverAction.showText(ChatColor.GRAY+"Click to continue")).build())
                .build()

        ).build();


    }


}
