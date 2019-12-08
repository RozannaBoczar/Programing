import java.awt.*;
import javax.swing.JFrame;

public class Application extends JFrame {
    public Application(){
        initUI();
    }

    private void initUI() {

        add(new Board(Color.BLACK));

        setSize(250,200);

        setTitle("RoziaApplication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}
