import java.awt.*;

public class Line extends Figure{

    /*A line that starts at point 1 (p1) and ends at point 2 (p2) */

    private Point p1, p2;

    //Default constructor
    public Line(Point p1, Point p2){
        super();
        this.p1 = p1;
        this.p2 = p2;
    }

    //Constructor with a pre-selected color
    public Line(Color color, Point p1, Point p2){
        super(color);
        this.p1 = p1;
        this.p2 = p2;
    }

    //Getters
    public Point getP1(){
        return p1;
    }

    public Point getP2(){
        return p2;
    }

    //Setters
    public void setP1(int x, int y){
        this.p1 = new Point(this.color, x, y);
    }

    public void setP2(int x, int y){
        this.p2 = new Point(this.color, x, y);
    }

    @Override
    //toString
    public String toString(){
        return super.toString() + "p1: " + p1 + "\n" + "p2: " + p2 + "\n";
    }
}
