package de.enderkatze.playerutils.commands;

import de.enderkatze.playerutils.PlayerUtils;
import de.enderkatze.playerutils.enums.Filter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayermenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {

            PlayerUtils.getInstance().menuGUI.openMainWindow((Player) sender, 0, Filter.NONE, null);
        }

        return true;
    }
}
