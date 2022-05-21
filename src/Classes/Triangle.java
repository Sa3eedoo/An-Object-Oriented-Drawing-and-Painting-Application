package Classes;

import java.awt.*;

public class Triangle extends Shapes implements Cloneable {
    int[] x = new int[3];
    int[] y = new int[3];
    int x1, y1, x2, y2, x3, y3;

    public Triangle(int x1,int y1,int x2,int y2,int x3,int y3,Color color) {
        setColor(color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
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
    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }
    public int getY3() {
        return y3;
    }
    public void setY3(int y3) {
        this.y3 = y3;
    }

    private float getSlope(int x1, int y1, int x2, int y2){
        return ((float)y1-y2)/(x1-x2);
    }

    @Override
    public void draw(Graphics g) {
        x[0] = x1;
        y[0] = y1;
        x[1] = x2;
        y[1] = y2;
        x[2] = x3;
        y[2] = y3;
        g.setColor(this.getColor());
        this.moveTo(x1,y1);
        this.lineTo(x2,y2);
        this.lineTo(x3,y3);
        this.closePath();
        g.drawPolygon(this.x,this.y,3);

        if(this.brushed) {
            this.setBrushColor(getBrushColor());
            this.brush(getBrushColor(), g);
        }
    }

    public void shift(int xshift, int yshift) {
        setX1(this.x1+xshift);
        setX2(this.x2+xshift);
        setY1(this.y1+yshift);
        setY2(this.y2+yshift);
        setX3(this.x3+xshift);
        setY3(this.y3+yshift);
    }


    private void brush(Color color,Graphics g){
        g.setColor(color);
        ((Graphics2D) g).fillPolygon(this.x,this.y,3);
    }


    public Shapes deepCopy(){
        Shapes s= new Triangle(this.x1,this.y1,this.x2,this.y2,this.x3,this.y3,this.getColor());
        if(this.brushed)
            s.brushed=true;

        return s;
    }
}
