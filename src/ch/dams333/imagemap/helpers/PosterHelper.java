package ch.dams333.imagemap.helpers;

import ch.dams333.imagemap.Plugin;
import org.bukkit.Location;
import org.bukkit.entity.ItemFrame;

import java.util.UUID;

public class PosterHelper {

    public static void writeItemFrameInConfig(ItemFrame itemFrame, UUID uuid) {

            Location loc = itemFrame.getLocation();
            String w = loc.getWorld().getName();
            String x = String.valueOf(Math.round(loc.getX()));
            String y = String.valueOf(Math.round(loc.getY()));
            String z = String.valueOf(Math.round(loc.getZ()));
            String l = w + "/" + x + "/" + y + "/" + z;

            Plugin.INSTANCE.getConfig().set(l, String.valueOf(uuid));
            Plugin.INSTANCE.saveConfig();


    }

    public static UUID readUUIDOfItemFrame(ItemFrame itemFrame){

        Location loc = itemFrame.getLocation();
        String w = loc.getWorld().getName();
        String x = String.valueOf(Math.round(loc.getX()));
        String y = String.valueOf(Math.round(loc.getY()));
        String z = String.valueOf(Math.round(loc.getZ()));
        String l = w + "/" + x + "/" + y + "/" + z;
            UUID uuid = null;

            if(Plugin.INSTANCE.getConfig().getKeys(false).contains(l)){

                uuid = UUID.fromString(Plugin.INSTANCE.getConfig().getString(l));


        }

        return uuid;

    }

    public static void saveMessageForUUID(UUID uuid, String message){

        Plugin.INSTANCE.getConfig().set(uuid.toString(), message);
        Plugin.INSTANCE.saveConfig();

    }

    public static String getMsgFromUUID(UUID uuid){

        if(Plugin.INSTANCE.getConfig().getKeys(false).contains(String.valueOf(uuid))){

            String msg = Plugin.INSTANCE.getConfig().getString(String.valueOf(uuid));

            return msg;

        }

        return null;

    }

}
