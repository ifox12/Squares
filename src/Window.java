import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window {

    private boolean dragging = false;
    private boolean resizing = false;
    private Point cursorRelativeToSquare;
    private Point squareBase;
    private final DrawingPanel surface;


    Window() {
        JFrame frame = new JFrame();
        surface = new DrawingPanel();

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point mouse = getPointRelativeToDrawingPanel(e);
                if (surface.square.getMoveRectangle().contains(mouse)) {
                    cursorRelativeToSquare = new Point(mouse.x - surface.square.getX(), mouse.y - surface.square.getY());
                    dragging = true;
                }
                if (surface.square.getResizeRectangle().contains(mouse) && !surface.square.getMoveRectangle().contains(mouse)) {
                    cursorRelativeToSquare = new Point(mouse.x - surface.square.getX(), mouse.y - surface.square.getY());
                    squareBase = new Point(surface.square.getX(), surface.square.getY());
                    resizing = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragging = false;
                resizing = false;
            }
        });

        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    Point mouse = getPointRelativeToDrawingPanel(e);
                    surface.square.reposition(mouse.x - cursorRelativeToSquare.x, mouse.y - cursorRelativeToSquare.y);
                    surface.repaint();
                }
                if (resizing) {
                    Point mouse = getPointRelativeToDrawingPanel(e);
                    surface.square.resize(mouse, squareBase);
                    surface.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point mouse = getPointRelativeToDrawingPanel(e);
                if (surface.square.getMoveRectangle().contains(mouse)) {
                    surface.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else if (surface.square.getResizeRectangle().contains(mouse) && !surface.square.getMoveRectangle().contains(mouse)) {
                    surface.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                } else {
                    surface.setCursor(Cursor.getDefaultCursor());
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(surface);
        frame.pack();

        frame.setVisible(true);

    }

    private Point getPointRelativeToDrawingPanel(MouseEvent e) {
        Point result = e.getLocationOnScreen();
        SwingUtilities.convertPointFromScreen(result, surface);
        return result;
    }

    public static void main(String[] args) {
        new Window();
    }


}
