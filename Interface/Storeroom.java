import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class Storeroom extends Room{
    public Storeroom(){
        super();

        newLamp(1);
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}





