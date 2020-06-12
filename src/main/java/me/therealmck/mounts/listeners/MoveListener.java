package me.therealmck.mounts.listeners;

import me.therealmck.mounts.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getPlayer().isInsideVehicle()) {
            if (Main.currentHorses.contains(event.getPlayer().getVehicle())) {
                List<Material> dismounters = Arrays.asList(Material.WATER, Material.PORTAL, Material.ENDER_PORTAL);
                Block b = event.getPlayer().getLocation().getBlock();

                if (dismounters.contains(b.getType())) {
                    Main.currentHorses.remove(event.getPlayer().getVehicle());
                    event.getPlayer().getVehicle().remove();
                }
            }
        }
    }
}
