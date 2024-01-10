import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyShapeTester extends JFrame {
    JButton up, down;
    MyCanvas canvas;
    Shape shape;

    public static void main(String[] args) {
        new MyShapeTester();
    }

    public MyShapeTester() {
        super("My Shape Tester");   
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        up = new JButton("^");
        down = new JButton("v");
        canvas = new MyCanvas();
        getContentPane().add(up, BorderLayout.NORTH);
        getContentPane().add(canvas, BorderLayout.CENTER);
        getContentPane().add(down, BorderLayout.SOUTH);
        setSize(250, 250);

        shape = new Circle(250 / 2, 170 / 2);
        canvas.setShape(shape);

        up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.moveUp(10);
            }
        });

        down.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.moveDown(10);
            }
        });
        setResizable(false);
        setVisible(true);
    }
}

class MyCanvas extends JPanel {
    Shape shape;
    public void setShape(Shape shape) {
        this.shape = shape;
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        shape.draw(g);
    }
    public void moveUp(int amount) {
        if (shape instanceof Movable)
            ((Movable) shape).moveUp(amount);
        repaint();
    }
    public void moveDown(int amount) {
        if (shape instanceof Movable)
            ((Movable) shape).moveDown(amount);
        repaint();
    }
}

