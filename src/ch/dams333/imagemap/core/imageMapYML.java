package ch.dams333.imagemap.core;

import ch.dams333.imagemap.Plugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class imageMapYML {

    private UUID imageMapUUID;
    private File cofigFile;
    private YamlConfiguration yamlConfiguration;

    public imageMapYML(UUID imageMapUUID) {
        this.imageMapUUID = imageMapUUID;
        this.cofigFile = new File(Plugin.IMAGE_MAP_DIR, imageMapUUID.toString() + ".yml");
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(cofigFile);
    }

    public void write(ImageMap imageMap){

        final ConfigurationSection confg = this.yamlConfiguration.createSection("image");

        confg.set("uuid", imageMap.getUuid().toString());
        confg.set("path", imageMap.getPath());
        confg.set("ids", imageMap.getMapIDs());

        save();

    }

    public ImageMap read(){

        final ConfigurationSection confg = this.yamlConfiguration.getConfigurationSection("image");

        final String uuidStr = confg.getString("uuid");
        final String path = confg.getString("path");
        final ArrayList<Short> ids = (ArrayList<Short>) confg.getShortList("ids");

        return new ImageMap(UUID.fromString(uuidStr), path, ids);

    }

    private void save(){

        try {
            yamlConfiguration.save(cofigFile);
        } catch (IOException e) {
            Plugin.INSTANCE.getLogger().severe("Cannot save image map config file: " + imageMapUUID.toString() + ".yml");
        }

    }

}
