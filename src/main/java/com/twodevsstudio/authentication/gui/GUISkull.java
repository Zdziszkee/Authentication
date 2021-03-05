package com.twodevsstudio.authentication.gui;

import com.twodevsstudio.authentication.utils.SkullCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GUISkull  {
    private final String headSkinInBase64;
    private  String displayName;
    private  List<String> lore;

    public GUISkull(String headSkinInBase64,String displayName,String... lore) {
        this.displayName = displayName;
        this.lore= Arrays.asList(lore);
        this.headSkinInBase64 = headSkinInBase64;
    }

    public GUISkull(String headSkinInBase64,String displayName,List<String> lore) {
        this.displayName = displayName;
        this.lore= lore;
        this.headSkinInBase64 = headSkinInBase64;
    }
    @Override
    public GUISkull clone(){
        return new GUISkull(headSkinInBase64,displayName,lore);
    }

    public ItemStack getItemStack(){
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM,1,(short) 3);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.values());
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',displayName));
        itemMeta.setLore(lore.stream().map(s -> ChatColor.translateAlternateColorCodes('&',s)).collect(Collectors.toList()));
        itemStack.setItemMeta(itemMeta);
        return SkullCreator.getCustomTextureHead(itemStack,headSkinInBase64);
    }
    public GUISkull replacePlaceHolder(String placeHolder,String value){
        this.displayName = displayName.replace(placeHolder,value);
        this.lore = lore.stream().map(s -> s.replace(placeHolder,value)).collect(Collectors.toList());
        return this;
    }
}
