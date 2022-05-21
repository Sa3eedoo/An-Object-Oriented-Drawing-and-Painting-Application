package Classes;

import java.awt.*;

public class ShapesFactory {
    private static ShapesFactory shapesFactory = null;

    private ShapesFactory() {
    }

    public static ShapesFactory getInstance() {
        if (shapesFactory == null)
            shapesFactory = new ShapesFactory();
        return shapesFactory;
    }

    public Shapes createShape(String shapeName, int x, int y, Color color) {
        if(shapeName == "rectangle")
            return new Rectangle(x , y , x , y, color);
        else if(shapeName == "line")
            return new Line(x , y , x , y, color);
        else if(shapeName == "triangle")
            return new Triangle(x , y , x , y, x, y, color);
        else if(shapeName == "circle")
            return new Circle(x , y , x , y, color);
        else if(shapeName == "square")
            return new Square(x , y , x , y, color);
        else
            return null;

    }
}
