import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class Kitchen extends Room{
    public Kitchen(){
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



