package Classes;

import java.awt.*;


public class Rectangle extends Shapes implements Cloneable {

    private int x1, y1;
    private int x2, y2;

    public Rectangle(int x1, int y1, int x2, int y2, Color color) {
        super();
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
        // Path2D.rect()
        //  this.beginPath
        this.moveTo(x1,y1);
        this.lineTo(x1,y2);
        this.lineTo(x2,y2);
        this.lineTo(x2,y1);
        this.closePath();
        ((Graphics2D)g).drawRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));

        if(this.brushed) {

            this.setBrushColor(getBrushColor());
            this.brush(getBrushColor(), g);
        }
//        g.setColor(Color.CYAN);
//        ((Graphics2D) g).fill(this);

        //g.drawRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(x2-x1));

    }

    @Override
    public void shift(int xshift, int yshift) {
        setX1(this.x1+xshift);
        setX2(this.x2+xshift);
        setY1(this.y1+yshift);
        setY2(this.y2+yshift);

    }



    public Shapes deepCopy(){

        Shapes s= new Rectangle(this.x1,this.y1,this.x2,this.y2,this.getColor());
        if(this.brushed)
            s.brushed=true;
        return s;
    }


    private void brush(Color color,Graphics g){
        g.setColor(color);
        ((Graphics2D) g).fillRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
    }
}
