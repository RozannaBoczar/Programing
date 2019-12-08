import javax.swing.*;
import java.awt.*;

public class ImageEx extends JFrame {
    public ImageEx() {
        initUI();
    }

    private void initUI() {
        add(new Board());

        pack();

        setTitle("RoziaImage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ImageEx ex = new ImageEx();
            ex.setVisible(true);
        });
    }
}
