import javax.swing.*;

class Main{

    PlayRoom playRoom = new PlayRoom();

    public static void main(String[] args){
        //?? Funciona, só aceite :P
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               (new Main()).createAndShowGUI();
           }
        });
    }

    private void createAndShowGUI(){
        //Create and set up the window
        JFrame frame = new JFrame("Teste :P");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Button pR = new Button(playRoom);
        pR.setOpaque(true); //content panes must be opaque
        frame.setContentPane(pR);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
