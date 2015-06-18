import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class LivingRoom extends Room{
    public LivingRoom(){
        super();

        newLamp(1);
        newLamp(2);
        newLamp(3);
        newAlarm();
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}




