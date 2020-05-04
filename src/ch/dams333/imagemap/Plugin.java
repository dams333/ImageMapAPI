package ch.dams333.imagemap;

import ch.dams333.imagemap.commands.CommandMap;
import ch.dams333.imagemap.commands.CommandSetMessage;
import ch.dams333.imagemap.core.DataLoader;
import ch.dams333.imagemap.core.ImageMapManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plugin extends JavaPlugin {

    public static File IMAGE_DIR;
    public static File IMAGE_MAP_DIR;
    public static Plugin INSTANCE;

    public static ImageMapManager IMAGE_MAP_MANAGER;

    public static Map<Player, List<Short>> mapsToGive = new HashMap<>();
    public static Map<Player, Integer> rows = new HashMap<>();
    public static Map<Player, Integer> cols = new HashMap<>();

    public static Map<Player, String> msgToSave = new HashMap<>();

    @Override
    public void onEnable(){

        INSTANCE = this;

        getDataFolder().mkdir();

        IMAGE_DIR = new File(getDataFolder(), "images");
        IMAGE_MAP_DIR = new File(getDataFolder(), "maps");

        IMAGE_MAP_MANAGER = new ImageMapManager();

        try {
            DataLoader.loadMaps();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getCommand("map").setExecutor(new CommandMap());
        getCommand("setmessage").setExecutor(new CommandSetMessage());

        getServer().getPluginManager().registerEvents(new ListenerClass(this), this);

    }

    @Override
    public void onDisable(){



    }

}
