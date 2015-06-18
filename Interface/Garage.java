import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class Garage extends Room{
    public Garage(){
        super();

        newLamp(1);
        newAlarm();
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}

