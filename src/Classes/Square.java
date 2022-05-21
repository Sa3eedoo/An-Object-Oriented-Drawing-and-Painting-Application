package Classes;

import java.awt.*;

public class Square extends Rectangle implements Cloneable {
    private int x1,y1,x2,y2;

    public Square(int x1, int y1, int x2, int y2, Color color) {


        super(x1, y1, x2, y2, color);

    }

    private void setDim(){
        this.x1 = super.getX1();
        this.x2 = super.getX2();
        this.y1 = super.getY1();
        this.y2 = super.getY2();
    }


    @Override
    public void draw(Graphics g) {
        setDim();
        g.setColor(getColor());
        this.moveTo(x1,y1);
        this.lineTo(x1,y2);
        this.lineTo(x2,y2);
        this.lineTo(x2,y1);
        this.closePath();
        g.drawRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(x2-x1));

        if(this.brushed) {
            this.setBrushColor(getBrushColor());
            this.brush(getBrushColor(), g);
        }

    }

    private void brush(Color color,Graphics g){
        g.setColor(color);
        ((Graphics2D) g).fillRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(x2-x1));
    }
}
