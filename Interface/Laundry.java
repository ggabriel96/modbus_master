import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

//Igual a circulacao... não to curtindo isso =/
class Laundry extends Room{
    public Laundry(){
        super();

        newLamp(1);
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}


