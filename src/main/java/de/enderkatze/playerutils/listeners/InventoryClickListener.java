package de.enderkatze.playerutils.listeners;

import de.enderkatze.playerutils.PlayerUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if(event.getView().getTitle() == PlayerUtils.getInstance().menuGUI.getTitle() && event.getSlotType() != InventoryType.SlotType.OUTSIDE) {

            event.setCancelled(true);

            ItemStack item = event.getCurrentItem();
            ClickType clickType = event.getClick();
            Material material = item.getType();

            int slot = event.getSlot();

            if (material == Material.SIGN_POST || material == Material.SIGN) {
                Player p = (Player) event.getWhoClicked();

                p.sendMessage("Placeholder. Enter username in chat");

                PlayerUtils.getInstance().cancelChatMessages.add(p);
                p.closeInventory();
            } else if (slot >= 9) {

              if(material != Material.AIR) {

                event.getWhoClicked().sendMessage(material.name());
              }
            } else if(slot == 0) {


            }
        }
    }
}
