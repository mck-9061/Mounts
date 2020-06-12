package me.therealmck.mounts.listeners;

import me.therealmck.mounts.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity().isInsideVehicle()) {
            if (Main.currentHorses.contains(event.getEntity().getVehicle())) {
                Main.currentHorses.remove(event.getEntity().getVehicle());
                event.getEntity().getVehicle().remove();
            }
        }
    }
}
