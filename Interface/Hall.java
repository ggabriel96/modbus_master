import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class Hall extends Room{
    public Hall(){
        super();

        newLamp(1);
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}
