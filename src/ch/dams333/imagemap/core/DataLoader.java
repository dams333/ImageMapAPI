package ch.dams333.imagemap.core;

import ch.dams333.imagemap.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataLoader {

    public static void loadMaps() throws IOException{

        final File imageDir = Plugin.IMAGE_DIR;
        final File imageMapDir = Plugin.IMAGE_MAP_DIR;

        if(!imageMapDir.exists()){

            if(!imageDir.mkdir()){

                throw new IOException("Cannot create image directory");

            }

        }
        if(!imageMapDir.exists()){

            if(!imageMapDir.mkdir()){

                throw new IOException("Cannot create image map directory");

            }

        }


        final File[] files = imageMapDir.listFiles();
        if(files != null){

            ImageMap imageMap;
            imageMapYML imageMapYML;

            for(File file : files){

                if(file.getName().endsWith(".yml")){

                    imageMapYML = new imageMapYML(UUID.fromString(file.getName().replaceAll(".yml", "")));
                    imageMap = imageMapYML.read();

                    Plugin.IMAGE_MAP_MANAGER.addImageMap(imageMap);
                    new TaskUpdateImage(imageMap).runTaskAsynchronously(Plugin.INSTANCE);

                }

            }

        }

    }

}
