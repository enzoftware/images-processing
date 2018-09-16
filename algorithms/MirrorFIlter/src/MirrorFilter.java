/**
 * Created by enzoftware on 5/29/17.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.*;

public class MirrorFilter {

    public static void main(String[] args) throws Throwable {
        int r,g,b;
        int r1,g1,b1;
        Color color;
        Color color1;
        File output=new File("Images/output.jpg"); //Input Photo File

        BufferedImage photo= ImageIO.read(new File("Images/photo.jpg"));
        int width = photo.getWidth()-1;
        for(int i=0;i< ( photo.getWidth() ) / 2;i++){
            for(int j=0;j<photo.getHeight();j++){
                //se obtiene el color del pixel
                color1 = new Color(photo.getRGB(i,j));
                color = new Color(photo.getRGB(width, j));
                //se extraen los valores RGB
                r = color.getRed();
                g = color.getGreen(); // ultima pos
                b = color.getBlue();

                r1 = color1.getRed();
                g1 = color1.getGreen(); // primera pos
                b1 = color1.getBlue();

                //se coloca en la nueva imagen con los valores invertidos
                photo.setRGB(i,j, new Color(r,g,b).getRGB());
                photo.setRGB(width, j, new Color(r1,g1,b1).getRGB());
            }
            width--;
        }
        ImageIO.write(photo,"jpg",output);
    }
}

