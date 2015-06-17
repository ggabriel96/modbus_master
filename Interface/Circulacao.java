import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class Circulacao extends Room{
    public Circulacao(){
        super();

        newLamp(1);

        try{
            picture = ImageIO.read(new File("assets/circulacao.jpg"));
        }catch(Exception e){
            System.err.println("Couldn't find file");
            System.exit(-1);
        }
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}
