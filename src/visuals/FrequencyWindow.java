package visuals;

import javax.swing.*;
import java.awt.*;

public class FrequencyWindow extends JDialog{

    public FrequencyWindow(JFrame owner){
        super(owner, "Frequency Analysis");
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }

    public void open(){
        this.setVisible(true);
    }

}
