package de.enderkatze.playerutils.listeners;

import de.enderkatze.playerutils.PlayerUtils;
import de.enderkatze.playerutils.enums.Filter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatMessageListener implements Listener {

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {

        if(PlayerUtils.getInstance().cancelChatMessages.contains(event.getPlayer())) {

            event.setCancelled(true);

            String name = event.getMessage();

            event.getPlayer().sendMessage(name);

            PlayerUtils.getInstance().menuGUI.openMainWindow(event.getPlayer(), 0, Filter.NONE, null);

            PlayerUtils.getInstance().cancelChatMessages.remove(event.getPlayer());


        }
    }
}
