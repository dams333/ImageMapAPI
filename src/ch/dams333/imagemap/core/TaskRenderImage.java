package ch.dams333.imagemap.core;

import ch.dams333.imagemap.Plugin;
import ch.dams333.imagemap.helpers.ImageHelper;
import ch.dams333.imagemap.helpers.RenderHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class TaskRenderImage extends BukkitRunnable {

    private Player player;
    private String path;

    public TaskRenderImage(Player player, String path){
        this.player = player;
        this.path = path;
    }

    @Override
    public void run() {
        try {

            final ArrayList<Short> mapsIDs = new ArrayList<>();

            final BufferedImage image = ImageHelper.getImage(path);
            final int row = image.getHeight() / 128;
            final int cols = image.getWidth() / 128;

            if(Plugin.cols.containsKey(player)){
                Plugin.cols.remove(player);
            }
            if(Plugin.rows.containsKey(player)){
                Plugin.rows.remove(player);
            }
            Plugin.cols.put(player, cols);
            Plugin.rows.put(player, row);

            player.sendMessage(ChatColor.GREEN + "Découpage fait en " + row + " lignes de " + cols + " colonnes");

            MapView map ;

            for(int i = 0; i < row; i++){

                for(int j = 0; j < cols; j++){

                    map = Bukkit.createMap(player.getWorld());
                    map = RenderHelper.resetRenderers(map);
                    map.setScale(MapView.Scale.FARTHEST);
                    map.setUnlimitedTracking(false);
                    map.addRenderer(new ImageMapRenderer(image.getSubimage(j*128, i*128, 128,128)));

                    mapsIDs.add(map.getId());

                }

            }


            if(Plugin.mapsToGive.containsKey(player)){
                Plugin.mapsToGive.remove(player);
            }
            Plugin.mapsToGive.put(player, mapsIDs);

            final ImageMap imageMap = new ImageMap(UUID.randomUUID(), path, mapsIDs);

            final imageMapYML imageMapYML = new imageMapYML(imageMap.getUuid());
            imageMapYML.write(imageMap);

            Plugin.IMAGE_MAP_MANAGER.addImageMap(imageMap);

            player.sendMessage(ChatColor.GREEN + "Rendu terminé. Clique droit sur l'itemframe en haut à gauche");

        } catch (IOException e) {
            Plugin.INSTANCE.getLogger().warning("Cannot load image at " + path + ".");
            Plugin.INSTANCE.getLogger().warning(e.getMessage());
        }

    }
}
