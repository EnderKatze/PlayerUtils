package de.enderkatze.playerutils.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackHelper {

    public static ItemStack CreatePlaceholderGlass(short color) {

        ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, color);

        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(" ");

        stack.setItemMeta(meta);

        return stack;
    }
}
