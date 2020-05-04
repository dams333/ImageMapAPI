package ch.dams333.imagemap.helpers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageHelper {

    public static boolean isURL(String path){

        return path.startsWith("http://") || path.startsWith("https://");

    }

    public static BufferedImage getImage(String path) throws IOException{

        if(isURL(path)){

            final URL url = new URL(path);

            return ImageIO.read(url);

        }else {

            final File imageFile = new File(path);

            if(imageFile.exists()){

                return ImageIO.read(imageFile);

            }else{
                throw new IOException("The specified image is nt found");
            }

        }

    }

}
