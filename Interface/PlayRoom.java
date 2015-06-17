import javax.swing.*;

class PlayRoom extends Room{
    public PlayRoom(){
        info = new int[4];
        name = new String[4];
        roomName = "Sala de Jogos";

        name[0] = "Lampada1";                info[0] =  0;
        name[1] = "Lampada2";                info[1] =  0;
        name[2] = "Sensor de Alarme";        info[2] =  0;
        name[3] = "Temperatura ambiente";    info[3] = 28;
    }

    public JButton createButton(){
        return new JButton("Play Room");
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}
