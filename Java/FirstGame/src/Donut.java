import javax.swing.*;
import java.awt.*;

public class Donut extends JFrame {

    public Donut(Color c){
        initUI(c);
    }

    private void initUI(Color c) {

        add(new Board(c));

        setSize(330,330);

        setTitle("Donut");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            Donut ex = new Donut(Color.pink);
            Donut ex2 = new Donut(Color.black);
            ex2.setVisible(true);
            ex.setVisible(true);
        });
    }


}
