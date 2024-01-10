import java.awt.*;

public class Circle extends Shape implements Movable {
    public Circle(int centerX, int centerY) {
        super(centerX, centerY);
        System.out.println("Circle: " + centerX + " " + centerY);
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(centerX-size, centerY-size, size*2, size*2);
    }

    public void moveUp(int amount) {
        centerY -= amount;
    }

    public void moveDown(int amount) {
        centerY += amount;
    }
}
