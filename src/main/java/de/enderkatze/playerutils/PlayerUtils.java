package de.enderkatze.playerutils;

import de.enderkatze.playerutils.commands.PlayermenuCommand;
import de.enderkatze.playerutils.listeners.ChatMessageListener;
import de.enderkatze.playerutils.listeners.InventoryClickListener;
import de.enderkatze.playerutils.objects.MenuGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class PlayerUtils extends JavaPlugin {

    private static PlayerUtils playerUtils;
    public static PlayerUtils getInstance() {return playerUtils;}

    public MenuGUI menuGUI;

    public List<Player> cancelChatMessages = new ArrayList<>();

    @Override
    public void onLoad() {
        playerUtils = this;
        menuGUI = MenuGUI.getInstance("Placeholder");

    }

    @Override
    public void onEnable() {

        this.getCommand("playermenu").setExecutor(new PlayermenuCommand());

        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatMessageListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
