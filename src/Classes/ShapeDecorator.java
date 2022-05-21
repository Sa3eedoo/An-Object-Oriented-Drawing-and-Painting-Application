package Classes;

import java.awt.*;

public class ShapeDecorator extends Shapes{

    Shapes shape;
    Color color;

    public ShapeDecorator(Shapes shape, Color color) {
        this.shape = shape;
        this.color = color;
        redecorate();
    }

    private void redecorate() {
        shape.setColor(color);
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void shift(int x, int y) {

    }

    @Override
    public Shapes deepCopy() {
        return null;
    }
}
