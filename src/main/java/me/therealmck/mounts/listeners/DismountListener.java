package me.therealmck.mounts.listeners;

import me.therealmck.mounts.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DismountListener implements Listener {
    @EventHandler
    public void onDismount(VehicleExitEvent event) {
        if (Main.currentHorses.contains(event.getVehicle())) {
            event.getVehicle().remove();
            Main.currentHorses.remove(event.getVehicle());
            Main.cooldown.add((Player) event.getExited());

            new BukkitRunnable() {
                @Override
                public void run() {
                    Main.cooldown.remove((Player) event.getExited());
                }
            }.runTaskLater(Main.instance, 100L);
        }
    }
}
