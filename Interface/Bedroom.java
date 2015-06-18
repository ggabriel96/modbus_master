import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class Bedroom extends Room{
    public Bedroom(File image){
        super();

        newLamp(1);
        newAlarm();
        newTemperature();
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}
