package Classes;
import java.awt.*;

public class Circle extends Shapes implements Cloneable {

    private int x,y,x2,y2;

    public Circle(int x, int y,int x2,int y2,Color color) {
        super();
        setColor(color);
        this.x=x;
        this.y=y;
        this.x2=x2;
        this.y2=y2;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getX() {
        return x;
    }

    public int getX2() {
        return x2;
    }

    public int getY() {
        return y;
    }

    public int getY2() {
        return y2;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }


    public void draw(Graphics g) {
        g.setColor(this.getColor());

        float radius = (float) ((this.x2-this.x)/2.0);
        float rX= this.x+radius,rY=this.y+radius;


        g.drawOval(x,y,Math.abs(x-x2),Math.abs(x-x2));

        if(this.brushed) {

            this.setBrushColor(getBrushColor());
            this.brush(getBrushColor(), g);
        }
    }

    @Override
    public boolean contains(int x,int y) {
//        super.contains(x,y);
        System.out.println("the dimensions "+this.x+" "+this.y+" "+this.x2+" "+this.y2);

        float radius = (float) ((this.x2-this.x)/2.0);
        float rX= this.x+radius,rY=this.y+radius;
        System.out.println("inside circle contain method,radius squared= "+Math.pow(radius, 2) +" dist bw centre and point "+Math.pow((rX - x), 2) + Math.pow((rY - y), 2));
        return (Math.pow((rX - x), 2) + Math.pow((rY - y), 2)) <= Math.pow(radius, 2);
    }

    public void shift(int xshift, int yshift) {
        setX(this.x+xshift);
        setX2(this.x2+xshift);
        setY(this.y+yshift);
        setY2(this.y2+yshift);

    }

    private void brush(Color color,Graphics g){
        g.setColor(color);
        ((Graphics2D) g).fillOval(x,y,Math.abs(x-x2),Math.abs(x-x2));
    }

    public Shapes deepCopy(){
        Shapes s = new Circle(this.x,this.y,this.x2,this.y2,this.getColor());
        if(this.brushed)
            s.brushed=true;
        return s;
    }
}
