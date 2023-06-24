import java.awt.*;

/*A line that starts at point 1 (p1) and ends at point 2 (p2) */
public class Line extends Figure{
    private Point p1, p2;

    //Default constructor
    public Line(Point p1, Point p2){
        super();
        this.p1 = p1;
        this.p2 = p2;
    }

    //Constructor with a pre-selected color
    public Line(Point p1, Point p2, Color color){
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
        this.p1 = new Point(x, y, this.color);
    }

    public void setP2(int x, int y){
        this.p2 = new Point(x, y, this.color);
    }

    //toString
    @Override
    public String toString(){
        return super.toString() + "p1: " + p1 + "\n" + "p2: " + p2 + "\n";
    }

    //Draw the line
    @Override
    public void draw(Graphics g, Color color){
        g.setColor(this.color); //defining the color of the drawing
        g.drawLine(p1.getX(), p1.getY(), //point 1
                   p2.getX(), p2.getY()  //point 2
                   );
    }
}
