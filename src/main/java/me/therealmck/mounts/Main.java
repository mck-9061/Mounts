package me.therealmck.mounts;

import me.therealmck.mounts.commands.Mounts;
import me.therealmck.mounts.listeners.DismountListener;
import me.therealmck.mounts.listeners.InteractListener;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Vehicle;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    public static FileConfiguration config;
    public static File configFile;
    public static Plugin instance;
    public static List<Vehicle> currentHorses = new ArrayList<>();

    @Override
    public void onEnable() {
        createMountsConfig();

        instance = this;

        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new DismountListener(), this);

        this.getCommand("mounts").setExecutor(new Mounts());
    }

    @Override
    public void onDisable() {
        for (Vehicle v : currentHorses) v.remove();
    }

    public static FileConfiguration getMountsConfig() {
        return config;
    }

    private void createMountsConfig() {
        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveMountsConfig() {
        try {
            config.save(configFile);
        } catch (Exception e) {e.printStackTrace();}
    }
}
