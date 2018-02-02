import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    public static void main(String[] args){
        new Main();
    }

    public Main(){
        this.setTitle("Cryptonite");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel lblHello = new JLabel("Hello World! Let's get cracking!");
        Container contentPane = this.getContentPane();
        contentPane.add(lblHello);
        this.setSize(800, 600);
        this.setVisible(true);

    }

}
