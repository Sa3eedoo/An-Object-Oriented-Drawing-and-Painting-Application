package View;


import Classes.*;
import Classes.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener, MouseMotionListener {

    private ShapesFactory shapesFactory = ShapesFactory.getInstance();
    protected Shapes rem;
    protected Stack<Shapes> shapes = new Stack<Shapes>();
    protected Stack<Shapes> temp= new Stack<Shapes>();
    protected Stack<Shapes> removed= new Stack<Shapes>();

    Color currentColor = Color.BLACK;
    int mode ;//0 for line 1 for rect
    int clicksNumber = 0;

    int x1, y1;
    int x2, y2;
    int x3, y3;
    boolean first = false;
    Shapes selected=null;

    ArrayList<Shapes> x = new ArrayList<Shapes>();


    public Board() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        if(mode==-1) {
            java.awt.Rectangle r= this.getBounds();
            g.clearRect(r.x, r.y, r.width, r.height);

            return;
        }

//        for(Shapes s:x)
//           s.draw(g);
        while(!shapes.empty()) {
            rem= shapes.pop();
            rem.draw(g2D);
            temp.push(rem);
        }
        while(!temp.empty()) {
            shapes.push(temp.pop());
        }

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("inside mouse clicked");
//        x1 = me.getX();
//        y1 = me.getY();

        if (mode == 2 && clicksNumber == 0) {
            x1 = me.getX();
            y1 = me.getY();
            Triangle t = (Triangle) shapesFactory.createShape("triangle", x1, y1, currentColor);
            //Triangle t = new Triangle(x1, y1, x1, y1, x1, y1, currentColor);
            clicksNumber++;
            x.add(t);
            shapes.push(t);
            repaint();
        } else if (mode == 2 && clicksNumber == 1){
            x2 = me.getX();
            y2 = me.getY();
            clicksNumber++;
            Shape s = x.get(x.size() - 1);
            if (s instanceof Triangle) {
                Triangle t = (Triangle) s;
                t.setX2(x2);
                t.setY2(y2);
            }
        } else if (mode == 2 && clicksNumber == 2){
            x3 = me.getX();
            y3 = me.getY();
            clicksNumber = 0;
            Shape s = x.get(x.size() - 1);
            if (s instanceof Triangle) {
                Triangle t = (Triangle) s;
                t.setX3(x3);
                t.setY3(y3);
            }
            repaint();
        } else {
            clicksNumber = 0;
        }
    }


    @Override
    public void mousePressed(MouseEvent me) {

        System.out.println("inside mouse pressed"+x1+" "+y1+" with mode "+mode);

        if (mode == 0) {
            x1 = me.getX();
            y1 = me.getY();
            Line l = (Line) shapesFactory.createShape("line", x1, y1, currentColor);
            x.add(l);
            shapes.push(l);
            repaint();
        } else if (mode == 1) {
            x1 = me.getX();
            y1 = me.getY();
            Rectangle r = (Rectangle) shapesFactory.createShape("rectangle", x1, y1, currentColor);
            x.add(r);
            shapes.push(r);
            repaint();
        }else if (mode == 3) {
            x1 = me.getX();
            y1 = me.getY();
            Circle c = (Circle) shapesFactory.createShape("circle", x1, y1, currentColor);
            x.add(c);
            shapes.push(c);
            repaint();
        }
        else if (mode == 4) {
            x1 = me.getX();
            y1 = me.getY();
            Square c = (Square) shapesFactory.createShape("square", x1, y1, currentColor);
            x.add(c);
            shapes.push(c);
            repaint();
        }
        else if (mode == 9 && x.size() != 0 ){
            if(selected != null) {
                x2 = me.getX();
                y2 = me.getY();
                int xshift = x2 - x1;
                int yshift = y2 - y1;

                Shapes clone = selected.deepCopy();
                clone.shift(xshift, yshift);

                x.add(clone);
                shapes.push(clone);
                erase(selected);

                selected=null;
                repaint();
            }
            else{
                x1 = me.getX();
                y1 = me.getY();
                selected= select(x1,y1);

            }

        }

        else if (mode == 14) {
            selected = select(me.getX(), me.getY());
        }

        else if (mode == 10 && x.size() != 0 ){
            if(selected != null) {
                x2 = me.getX();
                y2 = me.getY();

                Shapes clone= selected.deepCopy();

                System.out.println(clone);
                int xshift = x2 - x1;
                int yshift = y2 - y1;

                clone.shift(xshift, yshift);
                x.add(clone);
                shapes.push(clone);
                selected = null;


                repaint();
            }
            else{
                x1 = me.getX();
                y1 = me.getY();
                selected= select(x1,y1);

            }



        }

        else if (mode == 12) {
            x1=me.getX(); y1=me.getY();
            selected= select(x1,y1);

            if(selected != null)
            {
                selected.setBrushColor(currentColor);
                selected.brushNow = true;
                selected.brushed=true;
            }
            selected = null;
            repaint();
        }

        else if (mode ==15){
            x1=me.getX(); y1=me.getY();
            selected=select(x1,y1);

            erase(selected);
            repaint();
        }


    }

    @Override
    public void mouseReleased(MouseEvent me) {

        if (mode == 14) {
            selected = null;
        }

//        else if(mode == 12)
//            selected.brushed=false
    }



    @Override
    public void mouseDragged(MouseEvent me) {
        System.out.println("inside mouse dragged");
        if (mode == 0) {
            x2 = me.getX();
            y2 = me.getY();
            Shape s = x.get(x.size() - 1);
            if (s instanceof Line) {
                Line l = (Line) s;
                l.setX2(x2);
                l.setY2(y2);
            }

            repaint();
            first = false;
        }

        else if (mode == 1) {
            x2 = me.getX();
            y2 = me.getY();
            Shapes s = x.get(x.size() - 1);
            if (s instanceof Rectangle) {
                Rectangle r = (Rectangle) s;
                r.setX2(x2);
                r.setY2(y2);
            }

            repaint();
            first = false;
            // mode=10;
        }

        else if (mode == 3) {
            x2 = me.getX();
            y2 = me.getY();
            Shapes s = x.get(x.size() - 1);
            if (s instanceof Circle) {
                Circle c = (Circle) s;
                c.setX2(x2);
                c.setY2(y2);
            }

            repaint();
            first = false;
        }

        else if (mode == 4) {
            x2 = me.getX();
            y2 = me.getY();
            Shapes s = x.get(x.size() - 1);
            if (s instanceof Square) {
                Square c = (Square) s;
                c.setX2(x2);
                c.setY2(y2);
            }

            repaint();
            first = false;
        }

        else if (mode == 14) {
            if (selected instanceof Square) {
                ((Square) selected).setX2(me.getX());
                ((Square) selected).setY2(me.getY());
                repaint();
                first = false;
            }
            else if (selected instanceof Rectangle) {
                ((Rectangle) selected).setX2(me.getX());
                ((Rectangle) selected).setY2(me.getY());
                repaint();
                first = false;
            }
            else if (selected instanceof Circle) {
                ((Circle) selected).setX2(me.getX());
                ((Circle) selected).setY2(me.getY());
                repaint();
                first = false;
            }

            else if (selected instanceof Line) {
                ((Line) selected).setX2(me.getX());
                ((Line) selected).setY2(me.getY());
                repaint();
                first = false;
            }
            else if (selected instanceof Triangle) {
                ((Triangle) selected).setX3(me.getX());
                ((Triangle) selected).setY3(me.getY());
                repaint();
                first = false;
            }
        }
        System.out.println("after ifs in  mouse dragged"+x1+" "+y1+" "+x2+" "+y2);

    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    public void clear() {
        x.clear();
        shapes.clear();
        repaint();
    }

//    public void undo() {
//        System.out.println("inside undo");
//        if(!undo.empty()) {
//            System.out.println("undo is not empty");
//            System.out.println(undo);
//buffer=(ArrayList<Shapes>) undo.pop().clone();
//            //buffer= (ArrayList<Shapes>) x.clone();
//            redo.push(buffer);
//            System.out.println(undo);
//            x=undo.pop();
//
//
//        }
//        System.out.println("x= "+x);
//        repaint();
//    }
//
//    public void redo() {
//        System.out.println("inside redo");
//        if(!redo.empty()) {
//            System.out.println("redo is not empty");
//            x = redo.pop();
//            System.out.println(redo);
//            //redo.clear();
//        }
//            repaint();
//
//    }
public void undo() {
    if(!shapes.empty()) {
        removed.push(shapes.pop());
        repaint();
    }
}

    public void redo() {
        if(!removed.empty()) {
            shapes.push(removed.pop());
            repaint();
        }
    }


    private Shapes select(int x1,int y1){
        Iterator<Shapes> shapesIterator = x.iterator();
        while (shapesIterator.hasNext()) {
            Shapes s = shapesIterator.next();
            System.out.println(s);
            if (s.contains(x1, y1)) {
                return s;
            }
        }
        System.out.println(x1+","+y1+"not contained anywhere");
        return null;
    }

    public void erase(Shapes s){
        shapes.removeIf(x -> x.equals(s));
        x.removeIf(x ->x.equals(s));
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}

