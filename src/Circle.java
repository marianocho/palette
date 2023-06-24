import java.awt.*;
import java.util.StringTokenizer;

public class Circle extends Figure {
    protected Point p1,p2;
    protected int diameter;
    
    //Default constructor
    public Circle(Point p1, Point p2) {
        super();
        this.p1 = p1;
        this.p2 = p2;
    }

    //Constructor with a pre-selected color
    public Circle(Point p1, Point p2, int diameter, Color cor) {
        super(cor);
        this.p1 = p1;
        this.p2 = p2;
    }

    //Getters
    public Point getP1()
    {
        return this.p1;
    }

    public Point getP2()
    {
        return this.p2;
    }

    public int getDiameter(){
        return this.diameter;
    }

    //Setters
    public void setP1(int x, int y)
    {
        this.p1.setX(x);
        this.p1.setY(y);
    }

    public void setP2(int x, int y)
    {
        this.p1.setX(x);
        this.p1.setY(y);
    }

    public void setDiameter(int diameter){
        this.diameter = diameter;
    }
    
    //toString
    @Override
    public String toString() {
        return  super.toString() + 
                "p1: " + p1 + "\n" + 
                "p2: " + p2 + "\n" +
                "diameter: " + diameter;
    }

    //Calculate the dimensions of the circle
    private void calculateDimensions()
    {
        int deltaX, deltaY;
        
        //calculating deltaX
        deltaX = this.p1.getX() - this.p2.getX();
        if(deltaX < 0) {
            deltaX = -deltaX; //modulo
        }
        //calculating deltaX
        deltaY = this.p1.getY() - this.p2.getY();
        if(deltaY < 0) {
            deltaY = -deltaY;
        }
        if(deltaX >= deltaY) //check which delta is bigger
        {
            this.diameter = deltaX;                              
                                                                
        }
        else 
        {
            this.diameter = deltaY; 
        }
    }

    //Draw the circle
    @Override
    public void draw(Graphics g, Color fc){
        g.setColor(this.color); //contour color
        calculateDimensions();
        g.drawOval(p1.getX(), p1.getY(), //start point
                   diameter, diameter);
        g.setColor(fc); //filling color
        g.fillOval(p1.getX()+1, p1.getY()+1, //adding  1 to starting point to avoid overdrawing
                   diameter, diameter);
    }
}
