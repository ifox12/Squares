import java.awt.*;

public class Square {
    private Color main = new Color(0, 255, 0);
    private Color border = new Color(0, 180, 0);
    private int width = 50;
    private int height = 50;
    private int x;
    private int y;
    private Point base;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.base = new Point(x, y);
    }

    public void reposition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(main);
        g.fillRect(x, y, width, height);
        g.setColor(border);
        g.drawRect(x, y, width, height);

        g.setColor(Color.BLACK);
        g.drawString("x: " + x, 10, 10);
        g.drawString("y: " + y, 10, 25);
        g.drawString("w: " + width, 10, 40);
        g.drawString("h: " + height, 10, 55);
        g.drawString("baseSquare: (" + base.x + "," + base.y + ")", 10, 70);
    }

    public Rectangle getMoveRectangle() {
        return new Rectangle(x + 3, y + 3, width - 6, height - 6);
    }

    public Rectangle getResizeRectangle() {
        return new Rectangle(x - 3, y - 3, width + 6, height + 6);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void resize(Point newPointerPosition, Point squareBase) {
        this.base = squareBase;

        if (newPointerPosition.x > squareBase.x) {
            this.width = newPointerPosition.x - squareBase.x;
        }
        if (newPointerPosition.y > squareBase.y) {
            this.height = newPointerPosition.y - squareBase.y;
        }
        if (newPointerPosition.x < squareBase.x) {
            this.x = newPointerPosition.x;
            this.width = squareBase.x - newPointerPosition.x;
        }
        if (newPointerPosition.y < squareBase.y) {
            this.y = newPointerPosition.y;
            this.height = squareBase.y - newPointerPosition.y;
        }

    }
}
