package me.therealmck.mounts.listeners;

import me.therealmck.mounts.Main;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class DisconnectListener implements Listener {
    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        if (event.getPlayer().isInsideVehicle()) {
            Vehicle vehicle = (Vehicle) event.getPlayer().getVehicle();
            if (Main.currentHorses.contains(vehicle)) {
                Main.currentHorses.remove(vehicle);
                vehicle.remove();
            }
        }
    }
}
