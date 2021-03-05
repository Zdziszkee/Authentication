package com.twodevsstudio.authentication.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GUIItemStack {
    private  String displayName;
    private  List<String> lore;
    private final Material material;

    public GUIItemStack(String displayName, Material material, String... lore) {
        this.displayName = displayName;
        this.lore = Arrays.asList(lore);
        this.material = material;
    }

    public ItemStack getItemStack(){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.values());
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',displayName));
        itemMeta.setLore(lore.stream().map(s -> ChatColor.translateAlternateColorCodes('&',s)).collect(Collectors.toList()));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public GUIItemStack replacePlaceHolder(String placeHolder,String value){
        this.displayName = displayName.replace(placeHolder,value);
        this.lore = lore.stream().map(s -> s.replace(placeHolder,value)).collect(Collectors.toList());
        return this;
    }
}
