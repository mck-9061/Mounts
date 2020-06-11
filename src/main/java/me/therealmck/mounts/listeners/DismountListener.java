package me.therealmck.mounts.listeners;

import me.therealmck.mounts.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class DismountListener implements Listener {
    @EventHandler
    public void onDismount(VehicleExitEvent event) {
        if (Main.currentHorses.contains(event.getVehicle())) {
            event.getVehicle().remove();
            Main.currentHorses.remove(event.getVehicle());
        }
    }
}
