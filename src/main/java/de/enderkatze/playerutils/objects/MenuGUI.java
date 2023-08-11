package de.enderkatze.playerutils.objects;

import de.enderkatze.playerutils.enums.Filter;
import de.enderkatze.playerutils.utils.ItemStackHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MenuGUI {


    private final String title;

    public String getTitle() {return title;}

    private static MenuGUI instance;

    private int mainPage;
    private HashMap<Filter, String> filters = new HashMap<>();

    private MenuGUI(String title) {

        this.title = title;

    }

    public static MenuGUI getInstance(String title) {

        if(instance == null) {

            instance = new MenuGUI(title);
        }

        return instance;
    }


    public void openMainWindow(Player player, int page, Filter filter, String filterInput) {

        Inventory mainWindow = updateMainWindow(page, player, getFilteredPlayers(filter, filterInput));

        player.openInventory(mainWindow);

    }

    private List<Player> getFilteredPlayers(Filter filter, String filterInput) {

        List<Player> filteredPlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

        HashMap<Filter, String> applyFilters = this.filters;

        applyFilters.put(filter, filterInput);

        for (Player player : filteredPlayers) {

            for(Filter listFilter: applyFilters.keySet()) {

                switch (listFilter) {

                    case NONE:
                        break;
                    case NAME:
                        if(!player.getName().equals(applyFilters.get(listFilter))) {

                            filteredPlayers.remove(player);
                        }
                        break;
                    case WORLD:
                        if(!player.getWorld().toString().equals(applyFilters.get(listFilter))) {
                            filteredPlayers.remove(player);
                        }
                        break;
                }
            }
        }

        return filteredPlayers;
    }


    public Inventory updateMainWindow(int page, Player sender, List<Player> playerList) {

        ItemStack placeholder = ItemStackHelper.CreatePlaceholderGlass((short) 15);

        Inventory resultWindow = Bukkit.createInventory(null, 54, this.title);

        int numberOfPages = Bukkit.getOnlinePlayers().size() / 45;


        if(page > 0) {

            ItemStack arrow = new ItemStack(Material.ARROW);
            ItemMeta meta = arrow.getItemMeta();
            meta.setDisplayName("Previous Page");
            arrow.setItemMeta(meta);

            resultWindow.setItem(0, arrow);

        }
        else {resultWindow.setItem(0, placeholder);}

        resultWindow.setItem(1, placeholder);
        resultWindow.setItem(2, placeholder);
        resultWindow.setItem(3, placeholder);
        resultWindow.setItem(4, new ItemStack(Material.SIGN));
        resultWindow.setItem(5, placeholder);
        resultWindow.setItem(6, placeholder);
        resultWindow.setItem(7, placeholder);

        if(page < numberOfPages) {
            ItemStack arrow = new ItemStack(Material.ARROW);
            ItemMeta meta = arrow.getItemMeta();
            meta.setDisplayName("Next Page");
            arrow.setItemMeta(meta);

            resultWindow.setItem(8, arrow);
        }
        else {resultWindow.setItem(8, placeholder);}


        List<Player> addPlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

        for(int i = 9+(page*45); i <= 54+(page*45) && i-9+(page*45) <= addPlayers.size()-1; i++) {

            Player player = addPlayers.get(i-9);


            ItemStack skull = new ItemStack(Material.SKULL_ITEM,1 , (byte) 3);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();

            List<String> lore = new ArrayList<>();

            double distanceToSender = Math.sqrt(Math.pow((player.getLocation().getX() - sender.getLocation().getX()), 2) +
                                                Math.pow((player.getLocation().getY() - sender.getLocation().getY()), 2) +
                                                Math.pow((player.getLocation().getZ() - sender.getLocation().getZ()), 2));

            lore.add("Distance: " + distanceToSender);

            meta.setLore(lore);

            meta.setOwner(player.getName());
            meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.BOLD + player.getName());

            skull.setItemMeta(meta);


            resultWindow.setItem(i, skull);
        }

        return resultWindow;
    }

}
