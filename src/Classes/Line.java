package Classes;

import java.awt.*;

public class Line extends Shapes implements Cloneable {

    private int x1, y1;
    private int x2, y2;

    public Line(int x1, int y1, int x2, int y2, Color color) {
        setColor(color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g= (Graphics2D)g;
        g.drawLine(x1, y1, x2, y2);
    }

    private float getSlope(int x1, int y1, int x2, int y2){
        return ((float)y1-y2)/(x1-x2);
    }
    //
    @Override
    public boolean contains(int x, int y) {
        //return super.contains(x,y);

        if((x==this.x1 || x==this.x2) &&(y==this.y1 || y== this.y2) ) {
            return true;
        }
        float slope1=getSlope(this.x1,this.y1,this.x2,this.y2);
        float slope=getSlope(this.x1,this.y1,x,y);
        return Math.abs(slope - slope1) <= 0.09;
    }

    public void shift(int xshift, int yshift) {
        setX1(this.x1+xshift);
        setX2(this.x2+xshift);
        setY1(this.y1+yshift);
        setY2(this.y2+yshift);

    }

    public Shapes deepCopy(){
        return new Line(this.x1,this.y1,this.x2,this.y2,this.getColor());
    }
}
