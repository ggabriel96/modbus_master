import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

//Igual a circulacao... não to curtindo isso =/
class Bathroom extends Room{
    public Bathroom(File image){
        super();

        newLamp(1);

        try{
            picture = ImageIO.read(image);
        }catch(Exception e){
            System.err.println("Couldn't find file");
            System.exit(-1);
        }
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}
