import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class DiningRoom extends Room{
    public DiningRoom(){
        super();

        newLamp(1);
        newLamp(2);
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}




