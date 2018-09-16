package com.company;
import java.awt.image.ColorConvertOp;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedImage photo= ImageIO.read(new File("Images/photo.jpg"));
        File output=new File("Images/output.jpg"); //Input Photo File
        //create the detector
        CannyFilter detector = new CannyFilter();

        //adjust its parameters as desired
        detector.setLowThreshold(0.5f);
        detector.setHighThreshold(1f);

        //apply it to an image
        detector.setSourceImage(photo);
        detector.process();
        BufferedImage edges = detector.getEdgesImage();
        //ImageIO.write(edges,"JPG",output);
        BufferedImage rgbImage = new BufferedImage(edges.getWidth(),
                edges.getHeight(), BufferedImage.TYPE_INT_RGB);

        ColorConvertOp op = new ColorConvertOp(null);
        op.filter(edges, rgbImage);
        ImageIO.write(rgbImage, "JPEG", output);
    }
}
