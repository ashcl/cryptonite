import visuals.FrequencyWindow;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    //Child Windows
    FrequencyWindow freqWindow;

    public static void main(String[] args){
        new Main();
    }

    public Main(){
        this.setTitle("Cryptonite");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.createChildWindows();
        this.setUpGui();

        this.setSize(800, 600);
        this.setVisible(true);

    }

    private void createChildWindows() {
        freqWindow = new FrequencyWindow(this);
    }

    private void setUpGui(){
        JLabel lblHello = new JLabel("Hello World! Let's get cracking!");
        Container contentPane = this.getContentPane();
        contentPane.add(lblHello);
    }

}
