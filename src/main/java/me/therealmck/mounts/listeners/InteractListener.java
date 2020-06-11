package me.therealmck.mounts.listeners;

import me.therealmck.mounts.Main;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class InteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;
        if (item.getType() == null || item.getType().equals(Material.AIR)) return;

        if (event.getPlayer().isInsideVehicle()) return;

        ItemMeta meta = item.getItemMeta();
        if (meta.hasLore()) {
            String compare = meta.getLore().get(0);
            if (compare.startsWith("Mount: ")) {
                String mount = compare.replace("Mount: ", "");
                ConfigurationSection section = Main.getMountsConfig().getConfigurationSection(mount);

                if (section == null) return;

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        AbstractHorse horse = (AbstractHorse) event.getPlayer().getLocation().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.valueOf(section.getString("Type")));
                        horse.setJumpStrength(section.getDouble("Jump Range"));
                        horse.setOwner(event.getPlayer());
                        horse.setTamed(true);
                        horse.setAdult();
                        ((AbstractHorseInventory) horse.getInventory()).setSaddle(new ItemStack(Material.SADDLE));
                        horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(section.getDouble("Speed"));
                        horse.addPassenger(event.getPlayer());
                        Main.currentHorses.add(horse);
                    }
                }.runTask(Main.instance);
            } else return;
        } else return;
    }
}
