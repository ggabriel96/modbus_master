import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Button extends JPanel
                        implements ActionListener {
    protected JButton b1;
    private Room room;

    public Button(Room r) {
        /*try{

            BufferedImage pic = ImageIO.read(new File("teste.png"));
            ImageIcon picIcon = new ImageIcon(pic);
            this.b1 = new JButton(picIcon);

        }catch(Exception e){
            return;
        }*/

        this.room = r;
        this.b1 = new JButton(r.roomName);
        //b1.setMnemonic(KeyEvent.VK_D); //Associa uma tecla de atalho (Desnecessario)
        b1.setActionCommand("showInfo"); //Cria uma ação para o botão

        //Listen for actions on buttons 1 and 3.
        b1.addActionListener(this);

        b1.setToolTipText("Click this button to open a new window.");

        //Add Components to this container, using the default FlowLayout.
        add(b1);
    }

    public void actionPerformed(ActionEvent e) {
        if ("showInfo".equals(e.getActionCommand())) {
            room.openInfoWindow();
        }
    }

}
