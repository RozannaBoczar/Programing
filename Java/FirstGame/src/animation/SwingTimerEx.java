package animation;

import javax.swing.*;
import java.awt.*;

public class SwingTimerEx extends JFrame {

    public SwingTimerEx(){
        initUI();
    }

    private void initUI() {

        add(new AnimationBoard());

        setResizable(false);
        pack();

        setTitle("RoziaIsMoving!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            SwingTimerEx ex = new SwingTimerEx();
            ex.setVisible(true);
        });
    }

}
