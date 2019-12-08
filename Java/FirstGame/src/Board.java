//tu bedzie nasz plansza

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Board extends JPanel {
    private Color color;
    private Image i;

    public Board(Color c) {
        this.color = c;
    }

    public Board() {
        initBoard();
    }

    @Override
    public void paintComponent(Graphics g) {
        //do paczka
        // super.paintComponent(g);
        //drawDonut(g);

        g.drawImage(i, 0, 0, null);
    }

    private void initBoard() {
        loadImage();

        int w = i.getWidth(this);
        int h = i.getHeight(this);
        setPreferredSize(new Dimension(w, h));
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon("images/weiwuxian.jpg");
        i = ii.getImage();
    }

    //rysowanie donuta mlem
    private void drawDonut(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //Graphics2D rozszerza klase Graphics
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();

        Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(this.color);

        for (double deg = 0; deg < 360; deg += 10) {
            AffineTransform at = AffineTransform.getTranslateInstance(w / 2, h / 2);
            at.rotate(Math.toRadians(deg));
            g2d.draw(at.createTransformedShape(e));
        }

    }

    public Color getColor() {
        return color;
    }

    public Image getImage() {
        return i;
    }
}
