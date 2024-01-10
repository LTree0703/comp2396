import java.awt.*;

public abstract class Shape {
    protected int centerX, centerY;
    protected int size = 50;

    public abstract void draw(Graphics g);

    public Shape(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }
}