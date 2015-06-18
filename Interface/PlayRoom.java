import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class PlayRoom extends Room{
    public PlayRoom(){
        super();

        newLamp(1);
        newLamp(2);
        newAlarm();
        newTemperature();
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}
