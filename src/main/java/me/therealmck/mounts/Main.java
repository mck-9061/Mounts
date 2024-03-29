package me.therealmck.mounts;

import me.therealmck.mounts.listeners.*;
import me.therealmck.mounts.commands.Mounts;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
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
    public static FileConfiguration messages;
    public static File messagesFile;
    public static Plugin instance;
    public static List<Vehicle> currentHorses = new ArrayList<>();
    public static List<Player> cooldown = new ArrayList<>();

    @Override
    public void onEnable() {
        createMountsConfig();
        createMessagesConfig();

        instance = this;

        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new DismountListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new DisconnectListener(), this);
        getServer().getPluginManager().registerEvents(new MoveListener(), this);

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


    public static FileConfiguration getMessagesConfig() {
        return messages;
    }

    private void createMessagesConfig() {
        messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            messagesFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }

        messages = new YamlConfiguration();
        try {
            messages.load(messagesFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveMessagesConfig() {
        try {
            messages.save(messagesFile);
        } catch (Exception e) {e.printStackTrace();}
    }
}
