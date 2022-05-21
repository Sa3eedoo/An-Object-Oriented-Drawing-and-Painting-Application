package Classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Path2D;



public abstract class Shapes extends Path2D.Float {
    private Color color;
    public boolean brushNow,brushed;
    private Color BrushColor;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setBrushColor(Color color){
        this.BrushColor=color;
    }

    public Color getBrushColor(){
        return BrushColor;
    }

    public Shapes(){
        super();
    }

    public abstract void draw(Graphics g);

    public  boolean contains(int x, int y){
        return super.contains(x,y);
    }

    abstract public void  shift (int x,int y);

    abstract public Shapes deepCopy();


//public abstract boolean contains(int x,int y);

}
