package ch.dams333.imagemap.core;

import ch.dams333.imagemap.Plugin;
import ch.dams333.imagemap.helpers.ImageHelper;
import ch.dams333.imagemap.helpers.RenderHelper;
import org.bukkit.Bukkit;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class TaskUpdateImage extends BukkitRunnable {

    private ImageMap imageMap;

    public TaskUpdateImage(ImageMap imageMap){
        this.imageMap = imageMap;
    }

    @Override
    public void run() {
        try {

            final BufferedImage image = ImageHelper.getImage(imageMap.getPath());
            final int row = image.getHeight() / 128;
            final int cols = image.getWidth() / 128;

            MapView map ;
            int index = 0;

            for(int i = 0; i < row; i++){

                for(int j = 0; j < cols; j++){

                    map = Bukkit.getMap(imageMap.getMapIDs().get(index));
                    map = RenderHelper.resetRenderers(map);
                    map.setScale(MapView.Scale.FARTHEST);
                    map.setUnlimitedTracking(false);
                    map.addRenderer(new ImageMapRenderer(image.getSubimage(j*128, i*128, 128,128)));

                    index++;

                }

            }

        } catch (IOException e) {
            Plugin.INSTANCE.getLogger().warning("Cannot load image at " + imageMap.getPath() + ".");
            Plugin.INSTANCE.getLogger().warning(e.getMessage());
        }

    }
}
