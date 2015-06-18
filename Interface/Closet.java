import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class Closet extends Room{
    public Closet(){
        super();

        newLamp(1);
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}




