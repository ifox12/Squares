import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    public Square square;

    public DrawingPanel() {
        this.square = new Square(200, 200);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        square.draw(g);

    }

}
