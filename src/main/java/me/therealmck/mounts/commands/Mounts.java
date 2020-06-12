package me.therealmck.mounts.commands;

import me.therealmck.mounts.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class Mounts implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("mounts.admin")) {
            try {
                Player toGive = Bukkit.getPlayer(strings[1]);
                ConfigurationSection section = Main.getMountsConfig().getConfigurationSection(strings[2]);

                ItemStack item = new ItemStack(Material.valueOf(section.getString("Item")));
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(section.getString("Item Name"));
                item.setItemMeta(meta);

                toGive.getInventory().addItem(item);
            } catch (Exception e) {

            }
        }


        return true;
    }
}
