import java.awt.*;

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
    public Circle(Point p1, Point p2, Color color) {
        super(color);
        this.p1 = p1;
        this.p2 = p2;
    }

    public Circle(Point p1, int diameter, Color colorOut, Color colorIn) {
        super(colorOut, colorIn);
        this.p1 = p1;
        this.diameter = diameter;
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

    //Calculate the dimensions of the circle
    public void calculateDimensions()
    {
        int deltaX, deltaY;

        // calculating deltaX
        deltaX = p1.getX() - p2.getX();

        // calculating deltaY
        deltaY = p1.getY() - p2.getY();

        if (deltaX < 0 && deltaY > 0) {
            this.p1.setY(this.p2.getY());
            deltaX = -deltaX;
        } else if (deltaX > 0 && deltaY > 0) {
            this.setP1(this.p2.getX(), this.p2.getY());
        } else if (deltaX > 0 && deltaY < 0) {
            this.p1.setX(this.p2.getX());
            deltaY = -deltaY;
        } else if (deltaX < 0 && deltaY < 0) {
            deltaX = -deltaX;
            deltaY = -deltaY;
        }

        if (deltaX == deltaY) { // if deltas are the same, it doesn't matter
            this.diameter = deltaX;
        } else { // if the deltas are different, calculate the mean value of them and use it as
                 // the size
            double averageDouble = (deltaX + deltaY) / 2;
            int averageInt = (int) averageDouble;
            this.diameter = averageInt;
        }
    }

    //Draw the circle
    @Override
    public void draw(Graphics g, Color fc){
        g.setColor(this.colorOut); //border color
        calculateDimensions();
        g.drawOval(p1.getX(), p1.getY(), //start point
                   diameter, diameter);
        g.setColor(fc); //filling color
        colorIn = fc;
        g.fillOval(p1.getX()+1, p1.getY()+1, //adding  1 to starting point to avoid overdrawing
                   diameter-1, diameter-1);
    }

    @Override
    public void draw(Graphics g){
        g.setColor(this.colorOut); //border color
        g.drawOval(p1.getX(), p1.getY(), //start point
                   diameter, diameter);
        g.setColor(this.colorIn); //filling color
        g.fillOval(p1.getX()+1, p1.getY()+1, //adding  1 to starting point to avoid overdrawing
                   diameter-1, diameter-1);
    }
}
