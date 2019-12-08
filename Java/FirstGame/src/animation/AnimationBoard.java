package animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class AnimationBoard extends JPanel implements ActionListener {

    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INITIAL_X = 0;
    private final int INITIAL_Y = 30;
    private final int DELAY = 25;
    private int move_x = 1;
    private int move_y = 1;

    private Image star;
    private Timer timer;
    private int x,y;

    public AnimationBoard(){
        initAnimationBoard();
    }

    private void loadImage(){
        ImageIcon ii = new ImageIcon("src/images/star.png");
        star = ii.getImage();
    }
    
    private void initAnimationBoard() {
        setBackground(Color.black);
        setPreferredSize(new Dimension(B_WIDTH,B_HEIGHT));
        
        loadImage();
        
        x = INITIAL_X;
        y = INITIAL_Y;
        
        timer = new Timer(DELAY,this); //co opóźnienie, wywołana bedzie funkcja actionPerformed()
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        drawStart(g);
    }

    private void drawStart(Graphics g) {
        g.drawImage(star,x,y,this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x+=move_x;
        y+=move_y;

        if(y>B_HEIGHT-40 || y<0){
           move_y = -move_y;
           //move_x = -move_x;
            //x=-x;
        }
        if(x>B_WIDTH-40 || x<0){
            move_x = -move_x;
            //move_y = -move_y;
            //x=-x;
        }
        repaint();

    }
}
