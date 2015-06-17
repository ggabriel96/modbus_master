import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class PlayRoom extends Room{
    public PlayRoom(){
        info = new int[4];
        name = new String[4];
        roomName = "Sala de Jogos";

        name[0] = "Lampada1";                info[0] =  0;
        name[1] = "Lampada2";                info[1] =  0;
        name[2] = "Sensor de Alarme";        info[2] =  0;
        name[3] = "Temperatura ambiente";    info[3] = 28;

        try{
            picture = ImageIO.read(new File("assets/playroom.jpg"));
        }catch(Exception e){
            System.err.println("Couldn't find file");
            System.exit(-1);
        }
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}
